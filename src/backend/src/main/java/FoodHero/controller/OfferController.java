package FoodHero.controller;

import FoodHero.model.Login;
import FoodHero.model.Offer;
import FoodHero.service.Login.LoginService;
import FoodHero.service.Offers.OfferService;
import FoodHero.service.Offers.POJOS.SingleOffer;
import FoodHero.service.Utils.ReturnCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private static final Logger LOGGER = LogManager.getLogger(OfferController.class);
    OfferService offerService;
    LoginService loginService;


    @Autowired
    public OfferController(OfferService offerService, LoginService loginService) {
        this.offerService = offerService;
        this.loginService = loginService;
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Object> getAllOffersMatchFilter(@RequestParam(required = false, name = "MinPrice") String minPrice,
                                                          @RequestParam(required = false, name = "MaxPrice") String maxPrice,
                                                          @RequestParam(required = false, name = "MinRating") String minRating,
                                                          @RequestParam(required = false, name = "MaxRating") String maxRating,
                                                          @RequestParam(required = false, name = "Category") String category,
                                                          @RequestParam(required = false, name = "Status") String status,
                                                          @RequestParam(required = false, name = "Localization") String localization,
                                                          @RequestParam(required = false, name = "SearchName") String searchName) {
        double minPriceQuery = 0;
        double maxPriceQuery = 0;
        double minRatingQuery = 0;
        double maxRatingQuery = 0;
        String categoryQuery = "";
        String localizationQuery = "";
        String searchNameQuery = "";
        boolean statusQuery = true;

        if (minPrice != null && !minPrice.equals("")) {
            minPriceQuery = Double.parseDouble(minPrice);
        }
        if (maxPrice != null && !maxPrice.equals("")) {
            maxPriceQuery = Double.parseDouble(maxPrice);
        }
        if (minRating != null && !minRating.equals("")) {
            minRatingQuery = Double.parseDouble(minRating);
        }
        if (maxRating != null && !maxRating.equals("")) {
            maxRatingQuery = Double.parseDouble(maxRating);
        }
        if (status != null && !status.equals("")) {
            statusQuery = Boolean.parseBoolean(status);
        }
        if (category != null) {
            category = category.trim();
            if (!category.equals("")) {
                categoryQuery = category;
            }
        }
        if (localization != null) {
            localization = localization.trim();
            if (!localization.equals("")) {
                localizationQuery = localization;
            }
        }
        if (searchName != null) {
            searchName = searchName.trim();
            if (!searchName.equals("")) {
                searchNameQuery = searchName;
            }
        }
        return new ResponseEntity<>(offerService.getOffersWithFilters(minPriceQuery, maxPriceQuery, minRatingQuery, maxRatingQuery, categoryQuery, statusQuery, localizationQuery, searchNameQuery), HttpStatus.OK);
    }

//    @PostMapping(value = "/image/{id}")
//    public ResponseEntity<Object> setImage(@RequestParam("file") MultipartFile file, @PathVariable("id") int id, Principal principal){
//        Offer offer = offerService.getOffer(id);
//        Optional<Login> optionalLogin = loginService.getLogin(loginService.getIdByEmail(principal.getName()));
//        if(!optionalLogin.isPresent()){
//            return ReturnCode.
//        }
//        if(offer.getId() == loginService.getIdByEmail(principal.getName()) || loginService.getLogin(loginService.getIdByEmail(principal.getName())).get().getIs_admin()){
//            return ReturnCode.NOT_FOUND;
//        }
//                System.out.println(file.getName());
//        try {
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get("./dishImages/" + file.getOriginalFilename());
//            Files.write(path, bytes);
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> createOffer(@PathVariable("id") int id, @RequestBody Map<String, Object> payload, Principal principal) {
        if (payload == null) {
            return new ResponseEntity<>(ReturnCode.MISSING_ARG.toString() + "\nLack of json payload.", HttpStatus.BAD_REQUEST);
        }
        int userID = 0;
        if (principal != null) {
            userID = loginService.getIdByEmail(principal.getName());
        } else {
            userID = -1;
        }
        if ((userID != -1 && userID == id) || (loginService.getLogin(userID).isPresent() && loginService.getLogin(userID).get().getIs_admin())) {
            ReturnCode returnCode = offerService.createOffer(id, payload);
            if (returnCode == ReturnCode.NOT_FOUND) {
                return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nAccount or dish not found", HttpStatus.NOT_FOUND);
            } else if (returnCode == ReturnCode.INCORRECT_DATA) {
                return new ResponseEntity<>(ReturnCode.INCORRECT_DATA.toString() + "\nWrong json payload.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(ReturnCode.OK.toString() + "\nOffer created successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ReturnCode.NO_ACCESS.toString() + "\nYou have no permissions to create offer", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getOffer(@PathVariable("id") int id) {
        SingleOffer singleOffer = offerService.getSingleOffer(id);
        if (singleOffer == null) {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nOffer not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(singleOffer, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateOffer(@PathVariable("id") int id, @RequestBody Map<String, Object> payload, Principal principal) {
        int userID = 0;
        if (principal != null) {
            userID = loginService.getIdByEmail(principal.getName());
        } else {
            userID = -1;
        }
        Offer offer = offerService.getOffer(id);
        if (offer != null) {
            if ((userID != -1 && userID == offer.getAccount().getId()) || (loginService.getLogin(userID).isPresent() && loginService.getLogin(userID).get().getIs_admin())) {
                ReturnCode returnCode = offerService.updateOffer(id, payload);
                if (returnCode == ReturnCode.NOT_FOUND) {
                    return new ResponseEntity<>("Offer not found", HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(ReturnCode.OK.toString() + "\nOffer updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ReturnCode.NO_ACCESS.toString() + "\nYou have no permissions to update offer", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nOffer not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteOffer(@PathVariable("id") int id, Principal principal) {
        int userID = 0;
        if (principal != null) {
            userID = loginService.getIdByEmail(principal.getName());
        } else {
            userID = -1;
        }
        Offer offer = offerService.getOffer(id);
        if (offer != null) {
            if ((userID != -1 && userID == offer.getAccount().getId()) || (loginService.getLogin(userID).isPresent() && loginService.getLogin(userID).get().getIs_admin())) {
                ReturnCode returnCode = offerService.deleteOffer(id);
                if (returnCode == ReturnCode.NOT_FOUND) {
                    return new ResponseEntity<>("Offer not found", HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(ReturnCode.OK.toString() + "\nOffer deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ReturnCode.NO_ACCESS.toString() + "\nYou have no permissions to delete offer", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nOffer not found", HttpStatus.NOT_FOUND);

        }
    }
}
