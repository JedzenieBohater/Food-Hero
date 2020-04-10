package FoodHero.controller;

import FoodHero.model.Login;
import FoodHero.service.Login.LoginService;
import FoodHero.service.Utils.ReturnCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    LoginService loginService;
    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping(value = "/status", produces = "application/json")
    public ResponseEntity<Object> getStatus(Principal principal) {
        Map<String, String> data = new HashMap<>();
        data.put("userID", String.valueOf(loginService.getIdByEmail(principal.getName())));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> createLogin(@RequestBody Map<String, Object> payload) {
        if (payload == null) {
            return new ResponseEntity<>(ReturnCode.MISSING_ARG.toString() + "\nLack of json payload.", HttpStatus.BAD_REQUEST);
        }
        if (loginService.createLogin(payload) == ReturnCode.OK) {
            return new ResponseEntity<>(ReturnCode.OK.toString() + "\nLogin created successfully.", HttpStatus.OK);
        } else if (loginService.createLogin(payload) == ReturnCode.CONFLICT_WITH_DB) {
            return new ResponseEntity<>(ReturnCode.CONFLICT_WITH_DB.toString() + "\nEmail is being used.", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(ReturnCode.INCORRECT_DATA.toString() + "\nWrong json payload.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getLoginById(@PathVariable("id") int id, Principal principal) {
        int userID = 0;
        if (principal != null) {
            userID = loginService.getIdByEmail(principal.getName());
        } else {
            userID = -1;
        }
        if ((userID != -1 && userID == id) || (loginService.getLogin(userID).isPresent() && loginService.getLogin(userID).get().getIs_admin())) {
            Optional<Login> login = loginService.getLogin(id);
            if (login.isPresent()) {
                return new ResponseEntity<>(login.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nLogin not found.", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(ReturnCode.NO_ACCESS.toString() + "\nYou have no permissions to get login", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateLogin(@PathVariable("id") int id, @RequestBody Map<String, Object> payload, Principal principal) {
        int userID = 0;
        if (principal != null) {
            userID = loginService.getIdByEmail(principal.getName());
        } else {
            userID = -1;
        }
        if ((userID != -1 && userID == id) || (loginService.getLogin(userID).isPresent() && loginService.getLogin(userID).get().getIs_admin())) {
            if (payload == null) {
                return new ResponseEntity<>(ReturnCode.MISSING_ARG.toString() + "\nLack of json payload", HttpStatus.BAD_REQUEST);
            }
            ReturnCode returnCode = loginService.updateLogin(id, payload);
            if (returnCode == ReturnCode.CONFLICT_WITH_DB) {
                return new ResponseEntity<>(ReturnCode.CONFLICT_WITH_DB.toString() + "\nEmail is not available", HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(ReturnCode.OK.toString() + "\nLogin updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ReturnCode.NO_ACCESS.toString() + "\nYou have no permissions to update login", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(value = "/forget")
    public ResponseEntity<Object> forgetPassword(@RequestBody Map<String, Object> payload) {
        if (payload == null) {
            return new ResponseEntity<>(ReturnCode.MISSING_ARG.toString() + "\nLack of json payload", HttpStatus.BAD_REQUEST);
        }
        ReturnCode returnCode = loginService.forgetPassword(payload);
        if (returnCode == ReturnCode.NOT_FOUND) {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nEmail has not been found", HttpStatus.NOT_FOUND);
        } else if (returnCode == ReturnCode.INCORRECT_DATA) {
            return new ResponseEntity<>(ReturnCode.INCORRECT_DATA.toString() + "\nPayload not included", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ReturnCode.OK.toString() + "\nMessage sent to email", HttpStatus.OK);
    }

    //TODO oba poniższe trzeba przypiąć
    @PostMapping(value = "/forget/confirm")
    public ResponseEntity<Object> forgetPasswordConfirm(@RequestParam("token") String token) {
        return null;
    }

    @PostMapping(value = "/activate")
    public ResponseEntity<Object> createLoginConfirm(@RequestParam("token") String token) {
        return null;
    }

    @PostMapping(value = "/email/confirm")
    public ResponseEntity<Object> updateLoginEmailConfirm(@RequestParam("token") String token) {
        ReturnCode returnCode = loginService.confirmUpdateEmail(token);
        if (returnCode == ReturnCode.INVALID_TOKEN) {
            return new ResponseEntity<>(ReturnCode.INVALID_TOKEN.toString() + "\nToken is not correct or is not attached", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ReturnCode.OK.toString() + "\nChange email confirmed", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteLogin(@PathVariable("id") int id, Principal principal) {
        int userID = 0;
        if (principal != null) {
            userID = loginService.getIdByEmail(principal.getName());
        } else {
            userID = -1;
        }
        if ((userID != -1 && userID == id) || (loginService.getLogin(userID).isPresent() && loginService.getLogin(userID).get().getIs_admin())) {
            ReturnCode returnCode = loginService.deleteLogin(id);
            if (returnCode == ReturnCode.OK) {
                return new ResponseEntity<>(ReturnCode.OK.toString() + "\nLogin deleted successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nLogin not found", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(ReturnCode.NO_ACCESS.toString() + "\nYou have no permissions to delete login", HttpStatus.FORBIDDEN);
        }
    }
}
