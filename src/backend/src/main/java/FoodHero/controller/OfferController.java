package FoodHero.controller;

import FoodHero.model.Offer;
import FoodHero.service.Offers.OfferService;
import FoodHero.service.Offers.POJOS.AvailableOffer;
import FoodHero.service.Utils.ReturnCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/offers")
public class OfferController {

    OfferService offerService;
    private static final Logger LOGGER = LogManager.getLogger(OfferController.class);


    @Autowired
    public OfferController(OfferService offerService){
        this.offerService = offerService;
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<Object> getAllOffersMatchFilter(@RequestParam(required = false, name = "MinPrice") String minPrice,
                                                          @RequestParam(required = false, name = "MaxPrice") String maxPrice,
                                                          @RequestParam(required = false, name = "MinRating") String minRating,
                                                          @RequestParam(required = false, name = "MaxRating") String maxRating,
                                                          @RequestParam(required = false, name = "Category") String category,
                                                          @RequestParam(required = false, name = "Status") String status,
                                                          @RequestParam(required = false, name = "Localization") String localization,
                                                          @RequestParam(required = false, name = "SearchName") String searchName){
        double minPriceQuery = 0;
        double maxPriceQuery = 0;
        double minRatingQuery = 0;
        double maxRatingQuery = 0;
        String categoryQuery = "";
        String localizationQuery = "";
        String searchNameQuery = "";
        boolean statusQuery = true;

        if(minPrice != null && !minPrice.equals(""))
        {
            minPriceQuery = Double.parseDouble(minPrice);
        }
        if(maxPrice != null && !maxPrice.equals(""))
        {
            maxPriceQuery = Double.parseDouble(maxPrice);
        }
        if(minRating != null && !minRating.equals(""))
        {
            minRatingQuery = Double.parseDouble(minRating);
        }
        if(maxRating != null && !maxRating.equals(""))
        {
            maxRatingQuery = Double.parseDouble(maxRating);
        }
        if(status != null && !status.equals(""))
        {
            statusQuery = Boolean.parseBoolean(status);
        }
        if(category != null)
        {
            category = category.trim();
            if(!category.equals("")){
                categoryQuery = category;
            }
        }
        if(localization != null)
        {
            localization = localization.trim();
            if(!localization.equals("")){
                localizationQuery = localization;
            }
        }
        if(searchName != null)
        {
            searchName = searchName.trim();
            if(!searchName.equals("")){
                searchNameQuery = searchName;
            }
        }

        return new ResponseEntity<>(offerService.getOffersWithFilters(minPriceQuery, maxPriceQuery, minRatingQuery, maxRatingQuery, categoryQuery, statusQuery, localizationQuery, searchNameQuery), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> createOffer(@PathVariable("id") int id, @RequestBody Map<String, Object> payload){
        if (payload == null) {
            return new ResponseEntity<>(ReturnCode.MISSING_ARG.toString() + "\nLack of json payload.", HttpStatus.BAD_REQUEST);
        }
        ReturnCode returnCode = offerService.createOffer(id, payload);
        if (returnCode == ReturnCode.NOT_FOUND) {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nAccount or dish not found", HttpStatus.NOT_FOUND);
        } else if (returnCode == ReturnCode.INCORRECT_DATA) {
            return new ResponseEntity<>(ReturnCode.INCORRECT_DATA.toString() + "\nWrong json payload.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ReturnCode.OK.toString() + "\nOffer created successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getOffer(@PathVariable("id") int id) {
        Offer offer = offerService.getOffer(id);
        if(offer == null){
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nOffer not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @GetMapping(value = "/active", produces = "application/json")
    public ResponseEntity<Object> activeOffers(){
        List<AvailableOffer> availableOffers = offerService.getAllActive();
        return new ResponseEntity<>(availableOffers, HttpStatus.OK);
    }
}
