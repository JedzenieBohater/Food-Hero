package FoodHero.service;

import FoodHero.dao.DishRepository;
import FoodHero.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    public void createDish(Dish dish) {
        dishRepository.save(dish);
    }

    public void updateDish(String id, Dish dish) {
        dishRepository.save(dish);
    }

    public void deleteDish(String id) {
        dishRepository.delete(dishRepository.findById(id));
    }

    public Collection<Dish> getDishes() {
        return (List<Dish>) dishRepository.findAll();
    }
}