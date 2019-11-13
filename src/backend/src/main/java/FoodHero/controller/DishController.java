package FoodHero.controller;

import FoodHero.model.Dish;
import FoodHero.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DishController {
    @Autowired
    DishService dishService;

    @RequestMapping(value = "/dishes", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Dish dish) {
        dishService.createDish(dish);
        return new ResponseEntity<>("Dish created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/dishes/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Dish dish) {
        dishService.updateDish(dish);
        return new ResponseEntity<>("Dish updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/dishes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        dishService.deleteDish(id);
        return new ResponseEntity<>("Dish deleted successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/dishes")
    public ResponseEntity<Object> getDishes() {
        return new ResponseEntity<>(dishService.getDishes(), HttpStatus.OK);
    }
}