package FoodHero.service.Rating;

import FoodHero.dao.DishRatingRepository;
import FoodHero.model.DishRating;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishRatingService {
    private static final Logger LOGGER = LogManager.getLogger(DishRatingService.class);
    private DishRatingRepository dishRatingRepository;


    @Autowired
    public DishRatingService(@Lazy DishRatingRepository dishRatingRepository) {
        this.dishRatingRepository = dishRatingRepository;
    }

    public void createDishRating(DishRating dishRating) {
        dishRatingRepository.save(dishRating);
    }

    public Optional<DishRating> getDishRating(int id) {
        return Optional.of(dishRatingRepository.getOne(id));
    }

    public void updateDishRating(DishRating dishRating) {
        dishRatingRepository.save(dishRating);
    }

    public void deleteDishRating(int id) {
        dishRatingRepository.deleteById(id);
    }

    public Optional<List<DishRating>> getDishRatings() {
        return Optional.of(dishRatingRepository.findAll());
    }
}
