package FoodHero.controller;

import FoodHero.model.Account;
import FoodHero.model.Dish;
import FoodHero.model.Offer;
import FoodHero.service.Account.AccountService;
import FoodHero.service.Account.POJOS.AccountDetails;
import FoodHero.service.Utils.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:13000")
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/grades/{id}", produces = "application/json")
    public ResponseEntity<Object> getAccountWithGrades(@PathVariable("id") int id) {
        AccountDetails accountDetails = accountService.getAccountWithGrades(id);
        if (accountDetails != null) {
            return new ResponseEntity<>(accountDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nThere is no user with given id.",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getAccount(@PathVariable("id") int id) {
        Optional<Account> account = accountService.getAccount(id);
        if (account.isPresent()) {
            return new ResponseEntity<>(account.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\n There is no user with given id.",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}/offers", produces = "application/json")
    public ResponseEntity<Object> getAccountOffers(@PathVariable("id") int id) {
        Optional<Account> account = accountService.getAccount(id);
        if (account.isPresent()) {
            return new ResponseEntity<>(accountService.getAccountOffers(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\n There is no user with given id.",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}/dishes", produces = "application/json")
    public ResponseEntity<Object> getAccountDishes(@PathVariable("id") int id) {
        Optional<Account> account = accountService.getAccount(id);
        if (account.isPresent()) {
            return new ResponseEntity<>(accountService.getAccountDishes(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\n There is no user with given id.",HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> updateAccount(@PathVariable("id") int id, @RequestBody Map<String, Object> payload) {

        ReturnCode returnCode = accountService.updateAccount(id, payload);
        if (returnCode == ReturnCode.NOT_FOUND) {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nAccount not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ReturnCode.OK.toString() + "\nAccount updated successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
    }
}
