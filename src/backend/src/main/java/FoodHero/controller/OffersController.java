package FoodHero.controller;

import FoodHero.model.Offers;
import FoodHero.service.Offers.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/offers")
public class OffersController {
    @Autowired
    OffersService offersService;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<Object> getAllOffersMatchFilter(@RequestParam("MinPrice") String minPrice,
                                                          @RequestParam("MaxPrice") String maxPrice,
                                                          @RequestParam("MinRating") String minRating,
                                                          @RequestParam("MaxRating") String maxRating,
                                                          @RequestParam("Category") String category,
                                                          @RequestParam("Status") String status,
                                                          @RequestParam("Localization") String localization,
                                                          @RequestParam("SearchName") String searchName){
        ArrayList<List<Offers>> offers = new ArrayList<>();
        System.out.println("XDDDDDDDDDDDDDDDDDD");

        offers.add(offersService.getAllDishes());
        if(minPrice != null) {
            offers.add(offersService.getAllOffersWithMinPrice(Double.parseDouble(minPrice)));
        }
        if(maxPrice != null) {
            offers.add(offersService.getAllOffersWithMaxPrice(Double.parseDouble(maxPrice)));
        }
        if(minRating != null) {
            offers.add(offersService.getAllOffersWithMinRating(Double.parseDouble(minRating)));
        }
        if(maxRating != null) {
            offers.add(offersService.getAllOffersWithMaxRating(Double.parseDouble(maxRating)));
        }
        if(category != null) {
            offers.add(offersService.getAllOffersWithCategory(category));
        }
        if(status != null) {
            offers.add(offersService.getAllOffersWithStatus(status));
        }
        if(localization != null) {
            offers.add(offersService.getAllOffersWithLocalization(localization));
        }
        if(searchName != null) {
            offers.add(offersService.getAllOffersWithSearchName(searchName));
        }
        for (int i=1; i < offers.size(); i++) {
            offers.get(0).retainAll(offers.get(i)); // cześć wspólna wszystkich filtrów
        }
        //return new ResponseEntity<>("XD", HttpStatus.OK);
        System.out.println(offers);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getOffer(@PathVariable("id") int id) {
        Optional<Offers> offer = offersService.getOffer(id);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @GetMapping("/raw")
    public ResponseEntity<Object> raw(){
        return new ResponseEntity<>(offersService.getAllDishes(), HttpStatus.OK);
    }
}
