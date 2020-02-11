package FoodHero.dao;

import FoodHero.model.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffersRepository extends JpaRepository<Offers, Integer> {
    @Query("SELECT o FROM offers o WHERE o.price > :price")
    List<Offers> findAllByMinPrice(@Param("price") double price);
    @Query("SELECT o FROM offers o WHERE o.price < :price")
    List<Offers> findAllByMaxPrice(@Param("price") double price);
    @Query("SELECT o FROM offers o JOIN dish d ON o.id_dish = d.id WHERE d.grade > :rating")
    List<Offers> findAllByMinRating(@Param("rating") double rating);
    @Query("SELECT o FROM offers o JOIN dish d ON o.id_dish = d.id WHERE d.grade < :rating")
    List<Offers> findAllByMaxRating(@Param("rating") double rating);
    @Query("SELECT o FROM offers o JOIN dish d ON o.id_dish = d.id WHERE d.category = :category")
    List<Offers> findAllByCategory(@Param("category") String category);
    @Query("SELECT o FROM offers o WHERE o.status = :status")
    List<Offers> findAllByStatus(@Param("status") String status);
    @Query("SELECT o FROM offers o WHERE o.localization = :localization")
    List<Offers> findAllByLocalization(@Param("localization") String localization);
    @Query("SELECT o FROM offers o JOIN dish d ON o.id_dish = d.id WHERE d.name LIKE CONCAT('%',:searchName,'%')")
    List<Offers> findAllBySearchName(@Param("searchName") String searchName);
}
