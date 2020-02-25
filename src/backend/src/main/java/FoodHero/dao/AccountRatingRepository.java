package FoodHero.dao;

import FoodHero.model.AccountRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRatingRepository extends JpaRepository<AccountRating, Integer> {

}
