package FoodHero.dao;

import FoodHero.model.OfferRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRatingRepository extends JpaRepository<OfferRating, Integer> {
    @Override
    void deleteById(Integer integer);
}