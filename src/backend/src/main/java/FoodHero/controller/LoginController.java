package FoodHero.controller;

import FoodHero.model.Login;
import FoodHero.service.Account.AccountService;
import FoodHero.service.Login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
