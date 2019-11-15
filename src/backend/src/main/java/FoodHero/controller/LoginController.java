package FoodHero.controller;

import FoodHero.model.Login;
import FoodHero.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping(value = "/xd")
    public String test() {
        return "xd";
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> createAccount(@RequestBody Login login) {
        loginService.createLogin(login);
        return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getLogin(@PathVariable("id") String id) {
        Login login = loginService.getLogin(id);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Object> updateAccount(@RequestBody Login login) {
        loginService.updateLogin(login);
        return new ResponseEntity<>("Dish updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable("id") String id) {
        loginService.deleteLogin(id);
        return new ResponseEntity<>("Dish deleted successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> getAccounts() {
        return new ResponseEntity<>(loginService.getLogins(), HttpStatus.OK);
    }
}
