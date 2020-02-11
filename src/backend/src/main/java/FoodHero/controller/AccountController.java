package FoodHero.controller;

import FoodHero.model.Account;
import FoodHero.model.Dish;
import FoodHero.model.Offers;
import FoodHero.service.Account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:13000")
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping(value = "/")
    public ResponseEntity<Object> createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Account> getAccount(@PathVariable("id") int id) {
        Optional<Account> account = accountService.getAccount(id);
        if (account.isPresent()) {
            return new ResponseEntity<>(account.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}/offers", produces = "application/json")
    public ResponseEntity<List<Offers>> getAccountOffers(@PathVariable("id") int id) {
        Optional<Account> account = accountService.getAccount(id);
        if (account.isPresent()) {
            return new ResponseEntity<>(accountService.getAccountOffers(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}/dishes", produces = "application/json")
    public ResponseEntity<List<Dish>> getAccountDishes(@PathVariable("id") int id) {
        Optional<Account> account = accountService.getAccount(id);
        if (account.isPresent()) {
            return new ResponseEntity<>(accountService.getAccountDishes(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<Object> updateAccount(@RequestBody Account account) {
        accountService.updateAccount(account);
        return new ResponseEntity<>("Account updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable("id") int id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = "application/json")
    public String getAccounts() {

        //TODO tutaj coś nie bangla bo wypluwa taką ilość damych, że aż java się zapycha
        //return new ResponseEntity<List<Account>(accountService.getAccounts(), HttpStatus.OK);
        return null;
    }
}
