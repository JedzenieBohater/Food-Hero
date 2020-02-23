package FoodHero.controller;

import FoodHero.model.Offer;
import FoodHero.service.Offers.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/offers")
public class OfferController {
    @Autowired
    OfferService offerService;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<Object> getAllOffersMatchFilter(@RequestParam("MinPrice") String minPrice,
                                                          @RequestParam("MaxPrice") String maxPrice,
                                                          @RequestParam("MinRating") String minRating,
                                                          @RequestParam("MaxRating") String maxRating,
                                                          @RequestParam("Category") String category,
                                                          @RequestParam("Status") String status,
                                                          @RequestParam("Localization") String localization,
                                                          @RequestParam("SearchName") String searchName){
        //Todo trzeba zrobić obsługę błędów przy parsowaniu pustego/nieprawidlowego/brakujacego
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

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getOffer(@PathVariable("id") int id) {
        Optional<Offer> offer = offerService.getOffer(id);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @GetMapping("/raw")
    public ResponseEntity<Object> raw(){
        return new ResponseEntity<>(offerService.getAllDishes(), HttpStatus.OK);
    }
}
