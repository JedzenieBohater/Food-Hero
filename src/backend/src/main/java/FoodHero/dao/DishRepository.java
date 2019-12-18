package FoodHero.dao;

import FoodHero.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Override
    void deleteById(Integer id);
}