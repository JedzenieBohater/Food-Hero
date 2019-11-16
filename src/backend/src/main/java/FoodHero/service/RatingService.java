package FoodHero.service;

import FoodHero.dao.RatingRepository;
import FoodHero.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public void createRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public Rating getRating(int id){
        return ratingRepository.getOne(id);
    }

    public void updateRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public void deleteRating(int id) {
        ratingRepository.deleteById(id);
    }

    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }
}
