package FoodHero.controller;

import FoodHero.model.Dish;
import FoodHero.model.Offers;
import FoodHero.service.Dish.DishService;
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
    public ResponseEntity<Object> getAllDishesMatchFilter(@RequestParam("Price") String price, @RequestParam("MinRating") String minRating){
        ArrayList<List<Offers>> offers = new ArrayList<>();
        if(price != null) {
            offers.add(offersService.getAllDishesWithPrice(Double.parseDouble(price)));
        }
        if(minRating != null) {
            offers.add(offersService.getAllDishesWithMinRating(Double.parseDouble(minRating)));
        }

        for (int i=1; i < offers.size(); i++) {
            offers.get(0).retainAll(offers.get(i));
        }
        offersService.getAllDishes();
        return new ResponseEntity<>("XD", HttpStatus.OK);
        // return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/raw")
    public ResponseEntity<Object> raw(){
        return new ResponseEntity<>(offersService.getAllDishes(), HttpStatus.OK);
    }
}
