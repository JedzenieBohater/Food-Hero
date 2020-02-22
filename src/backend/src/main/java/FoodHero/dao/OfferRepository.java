package FoodHero.dao;

import FoodHero.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

//    @Query("SELECT o FROM Offers o WHERE o.price > :price")
//    List<Offers> findAllByMinPrice(@Param("price") double price);
//    @Query("SELECT o FROM Offers o WHERE o.price < :price")
//    List<Offers> findAllByMaxPrice(@Param("price") double price);
//    @Query("SELECT o FROM Offers o JOIN Dish d ON o.id_dish = d.id WHERE d.grade > :rating")
//    List<Offers> findAllByMinRating(@Param("rating") double rating);
//    @Query("SELECT o FROM Offers o JOIN Dish d ON o.id_dish = d.id WHERE d.grade < :rating")
//    List<Offers> findAllByMaxRating(@Param("rating") double rating);
//    @Query("SELECT o FROM Offers o JOIN Dish d ON o.id_dish = d.id WHERE d.category = :category")
//    List<Offers> findAllByCategory(@Param("category") String category);
//    @Query("SELECT o FROM Offers o WHERE o.status = :status")
//    List<Offers> findAllByStatus(@Param("status") String status);
//    @Query("SELECT o FROM Offers o WHERE o.localisation = :localisation")
//    List<Offers> findAllByLocalization(@Param("localisation") String localisation);
//    @Query("SELECT o FROM Offers o JOIN Dish d ON o.id_dish = d.id WHERE d.name LIKE CONCAT('%',:searchName,'%')")
//    List<Offers> findAllBySearchName(@Param("searchName") String searchName);

    @Query("SELECT o FROM Offer o")
    List<Offer> findAllByMinPrice(@Param("price") double price);
    @Query("SELECT o FROM Offer o")
    List<Offer> findAllByMaxPrice(@Param("price") double price);
    @Query("SELECT o FROM Offer o")
    List<Offer> findAllByMinRating(@Param("rating") double rating);
    @Query("SELECT o FROM Offer o")
    List<Offer> findAllByMaxRating(@Param("rating") double rating);
    @Query("SELECT o FROM Offer o")
    List<Offer> findAllByCategory(@Param("category") String category);
    @Query("SELECT o FROM Offer o")
    List<Offer> findAllByStatus(@Param("status") String status);
    @Query("SELECT o FROM Offer o")
    List<Offer> findAllByLocalization(@Param("localisation") String localisation);
    @Query("SELECT o FROM Offer o")
    List<Offer> findAllBySearchName(@Param("searchName") String searchName);
}
