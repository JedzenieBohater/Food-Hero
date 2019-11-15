package FoodHero.controller;

import FoodHero.model.Dish;
import FoodHero.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/dishes")
public class DishController {
    @Autowired
    DishService dishService;

    @PostMapping("/")
    public ResponseEntity<Object> createProduct(@RequestBody Dish dish) {
        dishService.createDish(dish);
        return new ResponseEntity<>("Dish created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Dish dish) {
        dishService.updateDish(dish);
        return new ResponseEntity<>("Dish updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        dishService.deleteDish(id);
        return new ResponseEntity<>("Dish deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDish(@PathVariable("id") String id) {
        Dish dish = dishService.getDish(id);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getDishes() {
        return new ResponseEntity<>(dishService.getDishes(), HttpStatus.OK);
    }
}