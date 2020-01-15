package FoodHero.controller;

import FoodHero.model.Dish;
import FoodHero.service.Dish.DishService;
import FoodHero.service.Offers.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/offers")
public class OffersController {
    @Autowired
    OffersService offersService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllDishes(){
        offersService.getAllDishes();
        return new ResponseEntity<>("XD", HttpStatus.OK);
    }

    @GetMapping("/raw")
    public ResponseEntity<Object> raw(){
        return new ResponseEntity<>(offersService.getAllDishes(), HttpStatus.OK);
    }
}
