package FoodHero.service.Login;

import FoodHero.dao.LoginRepository;
import FoodHero.model.Account;
import FoodHero.model.Login;
import FoodHero.service.Account.AccountService;
import FoodHero.service.Utils.ReturnCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;

@Service
public class LoginService {

    private static final Logger LOGGER = LogManager.getLogger(LoginService.class);
    LoginRepository loginRepository;
    AccountService accountService;


    @Autowired
    public LoginService(@Lazy LoginRepository loginRepository, @Lazy AccountService accountService) {
        this.loginRepository = loginRepository;
        this.accountService = accountService;
    }

    public int getIdByEmail(String email) {
        if (loginRepository.getByEmail(email).isPresent()) {
            return loginRepository.getByEmail(email).get().getId();
        }
        return -1;
    }

    public ReturnCode createLogin(Map<String, Object> payload, String language) {
        if (payload.get("email") == null || payload.get("password") == null || payload.get("email").equals("") || payload.get("password").equals("")) {
            return ReturnCode.MISSING_ARG;
        }
        if (!loginRepository.getByEmail(String.valueOf(payload.get("email"))).isPresent()) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(String.valueOf(payload.get("password")));
            Login login = new Login(String.valueOf(payload.get("email")), password);
            login = loginRepository.save(login);
            Account account = new Account(login);
            accountService.createAccount(account);

            int id = loginRepository.getByEmail(String.valueOf(payload.get("email"))).get().getId();
            String jwt = createToken("activate", id, null);
            sendEmail("activate", String.valueOf(payload.get("email")), jwt, language);

            return ReturnCode.OK;
        }
        return ReturnCode.CONFLICT_WITH_DB;
    }

    public ReturnCode forgetPassword(Map<String, Object> payload) {
        if (payload.get("email") == null || payload.get("email").equals("")) {
            return ReturnCode.INCORRECT_DATA;
        }
        if (!loginRepository.getByEmail(String.valueOf(payload.get("email"))).isPresent()) {
            int id = loginRepository.getByEmail(String.valueOf(payload.get("email"))).get().getId();
            String jwt = createToken("activate", id, null);
            sendEmail("activate", String.valueOf(payload.get("email")), jwt, accountService.getAccount(id).getLanguage());
            return ReturnCode.OK;
        }
        return ReturnCode.NOT_FOUND;
    }

    public Optional<Login> getLogin(int id) {
        return loginRepository.findById(id);
    }

    public ReturnCode updateLogin(int id, Map<String, Object> payload) {
        Optional<Login> optionalLogin = loginRepository.findById(id);
        if (!optionalLogin.isPresent()) {
            return ReturnCode.NOT_FOUND;
        }
        Login login = optionalLogin.get();
        if (payload.get("email") != null && !payload.get("email").equals("")) {
            if (!loginRepository.getByEmail(String.valueOf(payload.get("email"))).isPresent()) {
                String jws = createToken("emailChange", id, String.valueOf(payload.get("email")));
                sendEmail("emailChange", login.getEmail(), jws, accountService.getAccount(id).getLanguage());
                return ReturnCode.OK;
            } else {
                return ReturnCode.CONFLICT_WITH_DB;
            }
        }
        if (payload.get("password") != null && !payload.get("password").equals("")) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(String.valueOf(payload.get("password")));
            login.setPassword(password);
            loginRepository.save(login);
            return ReturnCode.OK;
        }
        return ReturnCode.INCORRECT_DATA;
    }

    public ReturnCode deleteLogin(int id) {
        Optional<Login> login = loginRepository.findById(id);
        if (login.isPresent()) {
            loginRepository.deleteById(id);
            return ReturnCode.OK;
        }
        return ReturnCode.NOT_FOUND;
    }

    //TODO trzeba zrobić odbieranie tokenu przy tworzeniu konta/zmianie maila/resecie hasła
    public ReturnCode activateLogin(String token) {
        return null;
    }

    public ReturnCode confirmPasswordReset(String token) {
        return null;
    }

    public ReturnCode confirmUpdateEmail(String token) {
        if (token != null && !token.equals("")) {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(DatatypeConverter.parseBase64Binary("2f9e5043a872a6b35f08ed33d2df5df1118ea7cc092318026db6495c403fac00"))
                        .parseClaimsJws(token).getBody();
                String currentEmail = String.valueOf(claims.get("current"));
                String pendingEmail = String.valueOf(claims.get("pending"));
                Optional<Login> optionalLogin = loginRepository.getByEmail(currentEmail);

                if (optionalLogin.isPresent()) {
                    Login login = optionalLogin.get();
                    login.setEmail(pendingEmail);
                    loginRepository.save(login);
                    return ReturnCode.OK;
                }
                return ReturnCode.INVALID_TOKEN;
            } catch (Exception e) {
                return ReturnCode.INVALID_TOKEN;
            }

        }
        return ReturnCode.INVALID_TOKEN;
    }

    public void sendEmail(String type, String emailTo, String jwt, String language) {
        Properties mailProps = new Properties();
        mailProps.put("mail.smtp.host", "smtp.gmail.com");
        mailProps.put("mail.smtp.port", "587");
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(mailProps,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("foodhero7@gmail.com", "student123!");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("foodhero7@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailTo)
            );

            Properties textProps = new Properties();
            switch (language) {
                case "en":
                    textProps.load(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("/languages/en.properties")), StandardCharsets.ISO_8859_1));
                    break;
                case "pl":
                    textProps.load(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("/languages/pl.properties")), StandardCharsets.ISO_8859_1));
                    break;
            }
            //TODO przerobic na front adresy
            String content;
            switch (type) {
                case "forgetPassword":
                    message.setSubject(textProps.getProperty("subjectReset"));
                    content = textProps.getProperty("infoReset");
                    content = MessageFormat.format(content, "<br><a target=\"_blank\" href = \"localhost:18080/login/forget/confirm?token=" + jwt + "\">foodhero.com</a>");
                    message.setContent(content + "<br>" + textProps.getProperty("ending"), "text/html;charset=UTF-8");
                    break;
                case "emailChange":
                    message.setSubject(textProps.getProperty("subjectConfirm"));
                    message.setContent("Kliknij w poniższy link, aby zresetować hasło: "
                            + "<a target=\"_blank\" href = \"localhost:18080/login/forget/confirm?token=" + jwt + "\">foodhero.com</a>", "text/html;charset=UTF-8");
                    content = textProps.getProperty("infoConfirm");
                    content = MessageFormat.format(content, "<br><a target=\"_blank\" href = \"localhost:18080/login/email/confirm?token=" + jwt + "\">foodhero.com</a>");
                    message.setContent(content + "<br>" + textProps.getProperty("ending"), "text/html;charset=UTF-8");
                    break;
                case "activate":
                    message.setSubject(textProps.getProperty("subjectActivate"));
                    content = textProps.getProperty("infoActivate");
                    content = MessageFormat.format(content, "<br><a target=\"_blank\" href = \"localhost:18080/login/activate?token=" + jwt + "\">foodhero.com</a>");
                    message.setContent(content + "<br>" + textProps.getProperty("ending"), "text/html;charset=UTF-8");
                    break;
            }

            Transport.send(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    public String createToken(String type, int id, String newEmail) {
        long currentTime = System.currentTimeMillis();
        JwtBuilder jws = Jwts.builder()
                .setIssuer(String.valueOf(id));

        switch (type) {
            case "forgetPassword":
                jws.setSubject("forgetPassword");
                jws.claim("email", loginRepository.getOne(id).getEmail());
                break;
            case "emailChange":
                jws.setSubject("email");
                jws.claim("current", loginRepository.getOne(id).getEmail());
                jws.claim("pending", newEmail);
                break;
            case "activate":
                jws.setSubject("activate");
                jws.claim("email", loginRepository.getOne(id).getEmail());
                break;
        }

        jws.setIssuedAt(new Date(currentTime));
        jws.setExpiration(new Date(currentTime + 21600000));
        jws.signWith(
                SignatureAlgorithm.HS256,
                TextCodec.BASE64.decode("2f9e5043a872a6b35f08ed33d2df5df1118ea7cc092318026db6495c403fac00")
        );
        return jws.compact();
    }
}
