package FoodHero.dao;

import FoodHero.model.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffersRepository extends JpaRepository<Offers, Integer> {
    @Query("SELECT t FROM Thing t WHERE t.price = ?1")
    List<Offers> findAllByPrice(double price);
}
