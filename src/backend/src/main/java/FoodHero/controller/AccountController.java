package FoodHero.controller;

import FoodHero.model.Account;
import FoodHero.model.Login;
import FoodHero.service.Account.AccountService;
import FoodHero.service.Account.POJOS.AccountDetails;
import FoodHero.service.Login.LoginService;
import FoodHero.service.Utils.ReturnCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:13000")
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;
    LoginService loginService;
    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);

    @Autowired
    public AccountController(AccountService accountService, LoginService loginService) {
        this.accountService = accountService;
        this.loginService = loginService;
    }

    @GetMapping(value = "/grades/{id}", produces = "application/json")
    public ResponseEntity<Object> getAccountWithGrades(@PathVariable("id") int id) {
        AccountDetails accountDetails = accountService.getAccountWithGrades(id);
        if (accountDetails != null) {
            return new ResponseEntity<>(accountDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nThere is no user with given id.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getAccount(@PathVariable("id") int id) {
        Account account = accountService.getAccount(id);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\n There is no user with given id.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}/offers", produces = "application/json")
    public ResponseEntity<Object> getAccountOffers(@PathVariable("id") int id) {
        Account account = accountService.getAccount(id);
        if (account != null) {
            return new ResponseEntity<>(accountService.getAccountOffers(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\n There is no user with given id.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}/dishes", produces = "application/json")
    public ResponseEntity<Object> getAccountDishes(@PathVariable("id") int id) {
        Account account = accountService.getAccount(id);
        if (account != null) {
            return new ResponseEntity<>(accountService.getAccountDishes(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\n There is no user with given id.", HttpStatus.NOT_FOUND);
        }
    }

    //TODO do przemyslenia czy nie mapowac od razu na obiekt czy moze jednak zostac przy mapie obiektow
    //TODO tutaj jest przykladowe blokowanie dla userow ktorzy nie maja dostepow, sprawdzic jak to dziala i dorobic dla reszty
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateAccount(@PathVariable("id") int id, @RequestBody Map<String, Object> payload, Principal principal) {
        int userID = loginService.getIdByEmail(principal.getName());
        if (userID == id || (loginService.getLogin(userID).isPresent() && !loginService.getLogin(userID).get().getIs_admin())) {
            if (payload == null) {
                return new ResponseEntity<>(ReturnCode.MISSING_ARG.toString() + "\nLack of json payload.", HttpStatus.BAD_REQUEST);
            }
            ReturnCode returnCode = accountService.updateAccount(id, payload);
            if (returnCode == ReturnCode.NOT_FOUND) {
                return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nAccount not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(ReturnCode.OK.toString() + "\nAccount updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ReturnCode.NO_ACCESS.toString() + "\nYou have no permissions to modify account", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
    }
}
