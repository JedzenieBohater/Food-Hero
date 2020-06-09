package FoodHero.controller;

import FoodHero.model.Dish;
import FoodHero.service.Dish.DishService;
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
import java.util.Map;

@RestController
//@CrossOrigin(origins = "http://localhost:13000")
@RequestMapping("/dish")
public class DishController {

    private static final Logger LOGGER = LogManager.getLogger(DishController.class);
    DishService dishService;
    LoginService loginService;


    @Autowired
    public DishController(DishService dishService, LoginService loginService) {
        this.dishService = dishService;
        this.loginService = loginService;
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<Object> uploadImage(@RequestParam("imageFile") MultipartFile file, @PathVariable("id") int id, Principal principal) {
        if (dishService.getDish(id) == null) {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nDish not found", HttpStatus.NOT_FOUND);
        }
        if (principal != null && (dishService.getDish(id).getAccount().getId() == loginService.getIdByEmail(principal.getName())) || loginService.getLogin(loginService.getIdByEmail(principal.getName())).get().getIs_admin()) {
            ReturnCode code = dishService.uploadImage(file, id);
            if (code == ReturnCode.INCORRECT_DATA) {
                return new ResponseEntity<>(ReturnCode.INCORRECT_DATA.toString() + "\nWrong extension", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(ReturnCode.OK.toString() + "\nImage uploaded", HttpStatus.OK);
        }
        return new ResponseEntity<>(ReturnCode.NO_ACCESS.toString() + "\nYou have no permissions to post image to this dish", HttpStatus.FORBIDDEN);

    }

    @GetMapping(value = "/{id}/image")
    public ResponseEntity<Object> getImage(@PathVariable("id") int id) {
        File file = dishService.getImage(id);
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

    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> createDish(@PathVariable("id") int id, @RequestBody Map<String, Object> payload, Principal principal) {
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
            ReturnCode returnCode = dishService.createDish(id, payload);
            if (returnCode == ReturnCode.NOT_FOUND) {
                return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nAccount not found", HttpStatus.NOT_FOUND);
            } else if (returnCode == ReturnCode.INCORRECT_DATA) {
                return new ResponseEntity<>(ReturnCode.INCORRECT_DATA.toString() + "\nWrong json payload.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(ReturnCode.OK.toString() + "\nDish created successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ReturnCode.NO_ACCESS.toString() + "\nYou have no permissions to post dish", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getDish(@PathVariable("id") int id) {
        Dish dish = dishService.getDish(id);
        if (dish == null) {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nDish not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateDish(@PathVariable("id") int id, Map<String, Object> payload, Principal principal) {
        if (payload == null) {
            return new ResponseEntity<>(ReturnCode.MISSING_ARG.toString() + "\nLack of json payload.", HttpStatus.BAD_REQUEST);
        }
        int userID = 0;
        if (principal != null) {
            userID = loginService.getIdByEmail(principal.getName());
        } else {
            userID = -1;
        }
        Dish dish = dishService.getDish(id);
        if (dish != null) {
            if ((userID != -1 && userID == dish.getAccount().getId()) || (loginService.getLogin(userID).isPresent() && loginService.getLogin(userID).get().getIs_admin())) {
                ReturnCode returnCode = dishService.updateDish(id, payload);
                if (returnCode == ReturnCode.NOT_FOUND) {
                    return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nDish not found", HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(ReturnCode.OK.toString() + "\nDish updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ReturnCode.NO_ACCESS.toString() + "\nYou have no permissions to modify dish", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nDish not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id, Principal principal) {
        int userID = 0;
        if (principal != null) {
            userID = loginService.getIdByEmail(principal.getName());
        } else {
            userID = -1;
        }
        Dish dish = dishService.getDish(id);
        if (dish != null) {
            if ((userID != -1 && userID == dish.getAccount().getId()) || (loginService.getLogin(userID).isPresent() && loginService.getLogin(userID).get().getIs_admin())) {
                ReturnCode returnCode = dishService.deleteDish(id);
                if (returnCode == ReturnCode.NOT_FOUND) {
                    return new ResponseEntity<>("Dish not found", HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(ReturnCode.OK.toString() + "\nDish deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ReturnCode.NO_ACCESS.toString() + "\nYou have no permissions to delete dish", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nDish not found", HttpStatus.NOT_FOUND);
        }
    }
}