package FoodHero.controller;

import FoodHero.model.Login;
import FoodHero.service.Account.AccountService;
import FoodHero.service.Login.LoginService;
import com.sun.mail.smtp.SMTPTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping(value = "/status", produces = "application/json")
    public ResponseEntity<Map> getStatus(Principal principal) {
        Map<String, String> data = new HashMap<>();
        data.put("userID", principal.getName());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> createAccount(@RequestBody Map<String, Object> payload) {
        if (payload == null) {
            return new ResponseEntity<>("Lack of json", HttpStatus.BAD_REQUEST);
        }
        if (loginService.createLogin(payload) == HttpStatus.OK) {
//            Properties prop = System.getProperties();
//            prop.put("mail.smtp.host", "poczta.interia.pl"); //optional, defined in SMTPTransport
//            prop.put("mail.smtp.auth", "true");
//            prop.put("mail.smtp.port", "465"); // default port 25
//            prop.put("mail.smtp.starttls.enable", "true");
//
//            Session session = Session.getInstance(prop, null);
//            Message msg = new MimeMessage(session);
//
//            try {
//
//                // from
//                try {
//                    msg.setFrom(new InternetAddress("food.hero@interia.pl"));
//                } catch (MessagingException e) {
//                    e.printStackTrace();
//                }
//
//                // to
//                msg.setRecipients(Message.RecipientType.TO,
//                        InternetAddress.parse("kajgore@vp.pl", false));
//
//                // subject
//                msg.setSubject("test");
//
//                // content
//                msg.setText("testtest");
//
//                msg.setSentDate(new Date());
//
//                // Get SMTPTransport
//                SMTPTransport t = null;
//                try {
//                    t = (SMTPTransport) session.getTransport("smtp");
//                } catch (NoSuchProviderException e) {
//                    e.printStackTrace();
//                }
//
//                // connect
//                t.connect("poczta.interia.pl", "food.hero@interia.pl", "student123");
//
//                // send
//                t.sendMessage(msg, msg.getAllRecipients());
//
//                System.out.println("Response: " + t.getLastServerResponse());
//
//                t.close();
//
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }




            return new ResponseEntity<>("Login created successfully", HttpStatus.OK);
        } else if (loginService.createLogin(payload) == HttpStatus.CONFLICT) {
            return new ResponseEntity<>("Email is being used", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Wrong json payload", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getLoginById(@PathVariable("id") int id) {
        Optional<Login> login = loginService.getLogin(id);
        if (login.isPresent()) {
            return new ResponseEntity<>(login.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> updateAccount(@PathVariable("id") int id, @RequestBody Map<String, Object> payload) {
        HttpStatus httpStatus = loginService.updateLogin(id, payload);
        if (httpStatus == HttpStatus.CONFLICT) {
            return new ResponseEntity<>("Email is not available", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Login updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable("id") int id) {
        HttpStatus httpStatus = loginService.deleteLogin(id);
        if (httpStatus == HttpStatus.OK) {
            return new ResponseEntity<>("Login deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Login not found", HttpStatus.NOT_FOUND);
    }
}
