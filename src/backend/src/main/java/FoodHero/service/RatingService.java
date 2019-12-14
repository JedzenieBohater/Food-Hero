package FoodHero.service;

import FoodHero.dao.RatingRepository;
import FoodHero.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public void createRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public Optional<Rating> getRating(int id){
        return Optional.ofNullable(ratingRepository.getOne(id));
    }

    public void updateRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public void deleteRating(int id) {
        ratingRepository.deleteById(id);
    }

    public Optional<List<Rating>> getRatings() {
        return Optional.ofNullable(ratingRepository.findAll());
    }
}
