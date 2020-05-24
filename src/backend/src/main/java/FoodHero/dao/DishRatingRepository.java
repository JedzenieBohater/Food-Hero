package FoodHero.dao;

import FoodHero.model.DishRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRatingRepository extends JpaRepository<DishRating, Integer> {
    @Override
    void deleteById(Integer integer);
}