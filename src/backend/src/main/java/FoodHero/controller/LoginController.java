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

    @PostMapping(value = "/")
    public ResponseEntity<Object> createAccount(@RequestBody Login login) {
        loginService.createLogin(login);
        return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
    }

    @PostMapping(value = "/find")
    public ResponseEntity<Object> getLogin(@RequestBody Login login)
    {
        System.out.println(login.getEmail());
        boolean loginExists = false;
        if(loginService.getLogin(login.getEmail()) != null)
        {
            loginExists = true;
            System.out.println("XD" + loginService.getLogin(login.getEmail()));

        }
        else
        {
            System.out.println("XD" + loginService.getLogin(login.getEmail()));
        }
        if(loginExists)
        {
            boolean passCorect = false;
            if(loginService.getLogin(login.getEmail()).getPassword().equals(login.getPassword()))
            {
                passCorect = true;
            }
            if(passCorect)
            {
                return new ResponseEntity<>(login, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(login, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getLoginById(@PathVariable("id") int id) {
        Login login = loginService.getLogin(id);
        return new ResponseEntity<>(login, HttpStatus.OK);
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

    @GetMapping(value = "/")
    public ResponseEntity<Object> getAccounts() {
        return new ResponseEntity<>(loginService.getLogins(), HttpStatus.OK);
    }
}
