package FoodHero.controller;

import FoodHero.model.Account;
import FoodHero.service.Account.AccountService;
import FoodHero.service.Account.POJOS.AccountDetails;
import FoodHero.service.Login.LoginService;
import FoodHero.service.Utils.ReturnCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:13000")
@RequestMapping("/account")
public class AccountController {

    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);
    AccountService accountService;
    LoginService loginService;

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
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateAccount(@PathVariable("id") int id, @RequestBody Map<String, Object> payload, Principal principal) {
        int userID = 0;
        if (principal != null) {
            userID = loginService.getIdByEmail(principal.getName());
        } else {
            userID = -1;
        }

        if ((userID != -1 && userID == id) || (loginService.getLogin(userID).isPresent() && loginService.getLogin(userID).get().getIs_admin())) {
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

    @PostMapping("/{id}/upload")
    public ResponseEntity<Object> uploadImage(@RequestParam("imageFile") MultipartFile file, @PathVariable("id") int id, Principal principal) {
        if (accountService.getAccount(id) == null) {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nAccount not found", HttpStatus.NOT_FOUND);
        }
        if (principal != null && (id == loginService.getIdByEmail(principal.getName()) || loginService.getLogin(loginService.getIdByEmail(principal.getName())).get().getIs_admin())) {
            ReturnCode code = accountService.uploadImage(file, id);
            if (code == ReturnCode.INCORRECT_DATA) {
                return new ResponseEntity<>(ReturnCode.INCORRECT_DATA.toString() + "\nWrong extension", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(ReturnCode.OK.toString() + "\nImage uploaded", HttpStatus.OK);
        }
        return new ResponseEntity<>(ReturnCode.NO_ACCESS.toString() + "\nYou have no permissions to post image to this account", HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "/{id}/image")
    public ResponseEntity<Object> getImage(@PathVariable("id") int id) {
        File file = accountService.getImage(id);
        if (file != null) {
            InputStreamResource resource = null;
            try {
                resource = new InputStreamResource(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getName() + "\"").contentLength(file.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
        }
        return new ResponseEntity<Object>(ReturnCode.NOT_FOUND + "\nImage not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Account>> getAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
    }
}
