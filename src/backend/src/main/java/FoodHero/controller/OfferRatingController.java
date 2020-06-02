package FoodHero.controller;

import FoodHero.model.OfferRating;
import FoodHero.service.Rating.OfferRatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:13000")
@RequestMapping("/offer_rating")
public abstract class OfferRatingController {

    private static final Logger LOGGER = LogManager.getLogger(OfferRatingController.class);
    OfferRatingService offerRatingService;


    @Autowired
    public OfferRatingController(OfferRatingService offerRatingService) {
        this.offerRatingService = offerRatingService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> createRating(@RequestBody OfferRating offerRating) {
        offerRatingService.createOfferRating(offerRating);
        return new ResponseEntity<>("Offer rating created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody OfferRating offerRating) {
        offerRatingService.updateOfferRating(offerRating);
        return new ResponseEntity<>("Offer rating updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        offerRatingService.deleteOfferRating(id);
        return new ResponseEntity<>("Offer rating deleted successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getOfferRating(@PathVariable("id") int id) {
        Optional<OfferRating> rating = offerRatingService.getOfferRating(id);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<Object> getOfferRatings() {
        return new ResponseEntity<>(offerRatingService.getOfferRatings(), HttpStatus.OK);
    }
}
