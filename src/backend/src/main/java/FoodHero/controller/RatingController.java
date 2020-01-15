package FoodHero.controller;

import FoodHero.model.Rating;
import FoodHero.service.Rating.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:13000")
@RequestMapping("/rating")
public abstract class RatingController {
    @Autowired
    RatingService ratingService;

    @PostMapping("/")
    public ResponseEntity<Object> createRating(@RequestBody Rating rating) {
        ratingService.createRating(rating);
        return new ResponseEntity<>("Rating created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Rating rating) {
        ratingService.updateRating(rating);
        return new ResponseEntity<>("Rating updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        ratingService.deleteRating(id);
        return new ResponseEntity<>("Rating deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRating(@PathVariable("id") int id) {
        Optional<Rating> rating = ratingService.getRating(id);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getRatings() {
        return new ResponseEntity<>(ratingService.getRatings(), HttpStatus.OK);
    }
}
