package FoodHero.dao;

import FoodHero.model.Account;
import FoodHero.model.Dish;
import FoodHero.model.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Override
    void deleteById(Integer id);
    @Query("SELECT o FROM offers o JOIN Account a ON a.id = o.id_konta WHERE a.id = :id")
    List<Offers> findAllOffersByAccountId(@Param("id") int id);
    @Query("SELECT d FROM dish d JOIN Account a ON a.id = d.id_account WHERE a.id = :id")
    List<Dish> findAllDishesByAccountId(@Param("id") int id);
}
