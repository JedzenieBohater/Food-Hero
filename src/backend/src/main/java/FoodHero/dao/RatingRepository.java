package FoodHero.dao;

import FoodHero.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    @Override
    void deleteById(Integer integer);
}
