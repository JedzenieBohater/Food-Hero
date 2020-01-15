package FoodHero.controller;

import FoodHero.model.Login;
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

    @GetMapping(value = "/status")
    public ResponseEntity<Map> getStatus(Principal principal) {
        Map<String, String> data = new HashMap<>();
        data.put("userID", principal.getName());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity createAccount(@RequestBody Login login) {
        loginService.createLogin(login);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getLoginById(@PathVariable("id") int id) {
        Optional<Login> account = loginService.getLogin(id);
        System.out.println(account.isPresent());
        if (account.isPresent()) {
            return new ResponseEntity<>(account.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<Object> updateAccount(@RequestBody Login login) {
        loginService.updateLogin(login);
        return new ResponseEntity<>("Dish updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable("id") int id) {
        loginService.deleteLogin(id);
        return new ResponseEntity<>("Dish deleted successfully", HttpStatus.OK);
    }
}
