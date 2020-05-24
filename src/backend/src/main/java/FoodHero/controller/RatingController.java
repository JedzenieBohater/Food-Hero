package FoodHero.controller;

import FoodHero.model.Rating;
import FoodHero.service.Rating.RatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:13000")
@RequestMapping("/rating")
public abstract class RatingController {

    RatingService ratingService;
    private static final Logger LOGGER = LogManager.getLogger(RatingController.class);


    @Autowired
    public RatingController(RatingService ratingService){
        this.ratingService = ratingService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> createRating(@RequestBody Rating rating) {
        ratingService.createRating(rating);
        return new ResponseEntity<>("Rating created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Rating rating) {
        ratingService.updateRating(rating);
        return new ResponseEntity<>("Rating updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        ratingService.deleteRating(id);
        return new ResponseEntity<>("Rating deleted successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getRating(@PathVariable("id") int id) {
        Optional<Rating> rating = ratingService.getRating(id);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<Object> getRatings() {
        return new ResponseEntity<>(ratingService.getRatings(), HttpStatus.OK);
    }
}
