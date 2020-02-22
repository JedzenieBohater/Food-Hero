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
        //Todo trzeba zrobić obsługę błędów przy parsowaniu pustego/nieprawidlowego
        double minPriceQuery = Double.parseDouble(minPrice);
        double maxPriceQuery = Double.parseDouble(minPrice);
        double minRatingQuery = Double.parseDouble(minPrice);
        double maxRatingQuery = Double.parseDouble(minPrice);
        boolean statusQuery = Boolean.parseBoolean(status);

        return new ResponseEntity<>(offerService.getOffersWithFilters(minPriceQuery, maxPriceQuery, minRatingQuery, maxRatingQuery, category, statusQuery, localization, searchName), HttpStatus.OK);
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
