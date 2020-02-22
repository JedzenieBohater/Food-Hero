package FoodHero.dao;

import FoodHero.model.Account;
import FoodHero.model.Dish;
import FoodHero.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Override
    void deleteById(Integer id);
}
