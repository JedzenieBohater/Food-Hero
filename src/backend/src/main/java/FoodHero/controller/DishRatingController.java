package FoodHero.controller;

import FoodHero.model.DishRating;
import FoodHero.service.Rating.DishRatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "http://localhost:13000")
@RequestMapping("/dish_rating")
public class DishRatingController {
    DishRatingService dishRatingService;
    private static final Logger LOGGER = LogManager.getLogger(DishRatingController.class);


    @Autowired
    public DishRatingController(DishRatingService dishRatingService){
        this.dishRatingService = dishRatingService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> createRating(@RequestBody DishRating dishRating) {
        dishRatingService.createDishRating(dishRating);
        return new ResponseEntity<>("Dish rating created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody DishRating dishRating) {
        dishRatingService.updateDishRating(dishRating);
        return new ResponseEntity<>("Dish rating updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        dishRatingService.deleteDishRating(id);
        return new ResponseEntity<>("Dish rating deleted successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getDishRating(@PathVariable("id") int id) {
        Optional<DishRating> dishRating = dishRatingService.getDishRating(id);
        return new ResponseEntity<>(dishRating, HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<Object> getDishRatings() {
        return new ResponseEntity<>(dishRatingService.getDishRatings(), HttpStatus.OK);
    }
}
