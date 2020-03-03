package FoodHero.service.Dish;

import FoodHero.dao.DishRepository;
import FoodHero.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    public void createDish(Dish dish) {
        dishRepository.save(dish);
    }

    public Optional<Dish> getDish(int id){
        return Optional.ofNullable(dishRepository.getOne(id));
    }

    public void updateDish(Dish dish) {
        dishRepository.save(dish);
    }

    public void deleteDish(int id) {
        dishRepository.deleteById(id);
    }

    public List<Dish> getAllDishRaw() {
        return dishRepository.findAll();
    }
}