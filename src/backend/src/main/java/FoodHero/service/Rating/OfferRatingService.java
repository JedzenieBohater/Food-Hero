package FoodHero.service.Rating;

import FoodHero.dao.OfferRatingRepository;
import FoodHero.model.OfferRating;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferRatingService {
    private OfferRatingRepository offerRatingRepository;
    private static final Logger LOGGER = LogManager.getLogger(OfferRatingService.class);


    @Autowired
    public OfferRatingService(@Lazy OfferRatingRepository offerRatingRepository){
        this.offerRatingRepository = offerRatingRepository;
    }

    public void createOfferRating(OfferRating offerRating) {
        offerRatingRepository.save(offerRating);
    }

    public Optional<OfferRating> getOfferRating(int id){
        return Optional.of(offerRatingRepository.getOne(id));
    }

    public void updateOfferRating(OfferRating offerRating) {
        offerRatingRepository.save(offerRating);
    }

    public void deleteOfferRating(int id) {
        offerRatingRepository.deleteById(id);
    }

    public Optional<List<OfferRating>> getOfferRatings() {
        return Optional.of(offerRatingRepository.findAll());
    }
}
