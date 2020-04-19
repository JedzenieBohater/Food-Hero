package FoodHero.service.Rating;

import FoodHero.dao.RatingRepository;
import FoodHero.model.Rating;
import FoodHero.service.AccountRatingRepository.AccountRatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private RatingRepository ratingRepository;
    private static final Logger LOGGER = LogManager.getLogger(RatingService.class);


    @Autowired
    public RatingService(@Lazy RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    public void createRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public Optional<Rating> getRating(int id){
        return Optional.of(ratingRepository.getOne(id));
    }

    public void updateRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public void deleteRating(int id) {
        ratingRepository.deleteById(id);
    }

    public Optional<List<Rating>> getRatings() {
        return Optional.of(ratingRepository.findAll());
    }
}
