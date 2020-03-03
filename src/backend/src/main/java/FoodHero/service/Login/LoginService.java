package FoodHero.service.Login;

import FoodHero.dao.LoginRepository;
import FoodHero.model.Account;
import FoodHero.model.Login;
import FoodHero.service.Account.AccountService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    AccountService accountService;

    public int getIdByEmail(String email) {
        return loginRepository.getByEmail(email).get().getId();
    }

    public HttpStatus createLogin(Map<String, Object> payload) {
        if (payload.get("email") == null || payload.get("password") == null || payload.get("email").equals("") || payload.get("password").equals("")) {
            return HttpStatus.BAD_REQUEST;
        }
        if (!loginRepository.getByEmail(String.valueOf(payload.get("email"))).isPresent()) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(String.valueOf(payload.get("password")));
            Login login = new Login(String.valueOf(payload.get("email")), password);
            login = loginRepository.save(login);
            Account account = new Account(login);
            accountService.createAccount(account);


            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS

            Session session = Session.getInstance(prop,
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
                        InternetAddress.parse((String) payload.get("email"))
                );
                message.setSubject("Potwierdź utworzenie konta w FoodHero!");
                message.setText("Kliknij w poniższy link, aby aktywować konto:,"
                        + "\n\n tu bedzie link");

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                e.printStackTrace();
            }

            return HttpStatus.OK;
        }
        return HttpStatus.CONFLICT;
    }

    public Optional<Login> getLogin(int id) {
        return loginRepository.findById(id);
    }

    public HttpStatus updateLogin(int id, Map<String, Object> payload) {
        Optional<Login> optionalLogin = loginRepository.findById(id);
        if (!optionalLogin.isPresent()) {
            return HttpStatus.NOT_FOUND;
        }
        Login login = optionalLogin.get();
        if (payload.get("email") != null && !payload.get("email").equals("")) {
            if (!loginRepository.getByEmail(String.valueOf(payload.get("email"))).isPresent()) {
                login.setEmail((String) payload.get("email"));
            } else {
                return HttpStatus.CONFLICT;
            }
        }
        if (payload.get("password") != null && !payload.get("password").equals("")) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(String.valueOf(payload.get("password")));
            login.setPassword(password);
        }
        if (payload.get("is_active") != null && !payload.get("is_active").equals("")) {
            login.setIs_active((Boolean) payload.get("is_active"));
        }
        loginRepository.save(login);
        return HttpStatus.OK;
    }

    public HttpStatus deleteLogin(int id) {
        Optional<Login> login = loginRepository.findById(id);
        if (login.isPresent()) {
            loginRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    public HttpStatus updateLoginEmail(int id, Map<String, Object> payload) {
        Optional<Login> optionalLogin = loginRepository.findById(id);
        if (!optionalLogin.isPresent()) {
            return HttpStatus.NOT_FOUND;
        }
        Login login = optionalLogin.get();
        if (payload.get("email") != null && !payload.get("email").equals("")) {
            if (!loginRepository.getByEmail(String.valueOf(payload.get("email"))).isPresent()) {
                String jws = createToken("email", id, String.valueOf(payload.get("email")));
                sendEmail("email", login.getEmail(), jws);
                return HttpStatus.OK;
            } else {
                return HttpStatus.CONFLICT;
            }
        }
        return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus activateLogin(String token){
        return null;
    }

    public HttpStatus confirmUpdateEmail(String token) {
        if (token != null && !token.equals("")) {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(DatatypeConverter.parseBase64Binary("test"))
                        .parseClaimsJws(token).getBody();
                String currentEmail = String.valueOf(claims.get("current"));
                String pendingEmail = String.valueOf(claims.get("pending"));
                Optional<Login> optionalLogin = loginRepository.getByEmail(currentEmail);
                if (optionalLogin.isPresent()) {
                    Login login = optionalLogin.get();
                    login.setEmail(pendingEmail);
                    loginRepository.save(login);
                    return HttpStatus.OK;
                }
                return HttpStatus.BAD_REQUEST;
            } catch (Exception e) {
                return HttpStatus.BAD_REQUEST;
            }

        }
        return HttpStatus.BAD_REQUEST;
    }

    public void sendEmail(String type, String emailTo, String jws) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
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
            //TODO ladniejsze komunikaty napisac i podpiac pod front jak juz bedzie
            switch (type) {
                case "forgetPassword":
                    message.setSubject("Potwierdź zmianę adresu email w FoodHero!");
                    message.setText("Kliknij w poniższy link, aby zresetować hasło: "
                            + jws);
                case "emailChange":
                    message.setSubject("Potwierdź zmianę adresu email w FoodHero!");
                    message.setText("Kliknij w poniższy link, aby zmienić email: "
                            + jws);
                    break;
                case "activate":
                    message.setSubject("Potwierdź utworzenie konta w FoodHero!");
                    message.setText("Kliknij w poniższy link, aby aktywować konto: "
                            + jws);
                    break;
            }
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
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
                TextCodec.BASE64.decode("test")
        );
        return jws.compact();
    }
}
