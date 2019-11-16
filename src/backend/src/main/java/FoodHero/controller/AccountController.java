package FoodHero.controller;

import FoodHero.model.Account;
import FoodHero.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping(value = "/")
    public ResponseEntity<Object> createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getAccount(@PathVariable("id") int id) {
        Account account = accountService.getAccount(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Object> updateAccount(@RequestBody Account account) {
        accountService.updateAccount(account);
        return new ResponseEntity<>("Dish updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable("id") int id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Dish deleted successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> getAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
    }
}
