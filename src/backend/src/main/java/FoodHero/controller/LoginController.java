package FoodHero.controller;

import FoodHero.model.Login;
import FoodHero.service.Login.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    @GetMapping(value = "/status", produces = "application/json")
    public ResponseEntity<Map> getStatus(Principal principal) {
        Map<String, String> data = new HashMap<>();
        data.put("userID", String.valueOf(loginService.getIdByEmail(principal.getName())));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> createLogin(@RequestBody Map<String, Object> payload) {
        if (payload == null) {
            return new ResponseEntity<>("Lack of json", HttpStatus.BAD_REQUEST);
        }
        if (loginService.createLogin(payload) == HttpStatus.OK) {
            LOGGER.info("Utworzono konto kurde");
            LOGGER.warn("PYK");
            LOGGER.error("XDDDDD");
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
    public ResponseEntity<Object> updateLogin(@PathVariable("id") int id, @RequestBody Map<String, Object> payload) {
        HttpStatus httpStatus = loginService.updateLogin(id, payload);
        if (httpStatus == HttpStatus.CONFLICT) {
            return new ResponseEntity<>("Email is not available", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Login updated successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/forget")
    public ResponseEntity<Object> forgetPassword(@RequestBody Map<String, Object> payload) {
        HttpStatus httpStatus = loginService.forgetPassword(payload);
        if (httpStatus == HttpStatus.NOT_FOUND) {
            return new ResponseEntity<>("Email has not been found", HttpStatus.NOT_FOUND);
        }
        else if (httpStatus == HttpStatus.BAD_REQUEST){
            return new ResponseEntity<>("Payload not included", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Message sent to email", HttpStatus.OK);
    }

    //TODO oba poniższe trzeba przypiąć
    @PostMapping(value = "/forget/confirm")
    public ResponseEntity<Object> forgetPasswordConfirm(@RequestParam("token") String token) {
        return null;
    }

    @PostMapping(value = "/activate")
    public ResponseEntity<Object> createLoginConfirm(@RequestParam("token") String token) {
        return null;
    }

    @PostMapping(value = "/email/confirm")
    public ResponseEntity<Object> updateLoginEmailConfirm(@RequestParam("token") String token) {
        HttpStatus httpStatus = loginService.confirmUpdateEmail(token);
        if (httpStatus == HttpStatus.BAD_REQUEST) {
            return new ResponseEntity<>("Token is not correct or is not attached", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Change email confirmed", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteLogin(@PathVariable("id") int id) {
        HttpStatus httpStatus = loginService.deleteLogin(id);
        if (httpStatus == HttpStatus.OK) {
            return new ResponseEntity<>("Login deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Login not found", HttpStatus.NOT_FOUND);
    }
}
