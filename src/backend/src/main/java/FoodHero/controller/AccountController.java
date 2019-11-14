package FoodHero.controller;

import FoodHero.model.Account;
import FoodHero.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/cooks", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Account account) {
        accountService.createAccount(account);
        return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cooks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Account account) {
        accountService.updateAccount(account);
        return new ResponseEntity<>("Dish updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/cooks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Dish deleted successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/cooks")
    public ResponseEntity<Object> getCooks() {
        return new ResponseEntity<>(accountService.getCooks(), HttpStatus.OK);
    }
}
