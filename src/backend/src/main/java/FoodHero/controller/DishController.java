package FoodHero.controller;

import FoodHero.model.Dish;
import FoodHero.service.Dish.DishService;
import FoodHero.service.Utils.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//TODO Cors do poprawy wszedzie
@CrossOrigin(origins = "http://localhost:13000")
@RequestMapping("/dish")
public class DishController {
    @Autowired
    DishService dishService;

    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> createDish(@PathVariable("id") int id, @RequestBody Map<String, Object> payload) {
        if (payload == null) {
            return new ResponseEntity<>(ReturnCode.MISSING_ARG.toString() + "\nLack of json payload.", HttpStatus.BAD_REQUEST);
        }
        ReturnCode returnCode = dishService.createDish(id, payload);
        if (returnCode == ReturnCode.NOT_FOUND) {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nAccount not found", HttpStatus.NOT_FOUND);
        } else if (returnCode == ReturnCode.INCORRECT_DATA) {
            return new ResponseEntity<>(ReturnCode.INCORRECT_DATA.toString() + "\nWrong json payload.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ReturnCode.OK.toString() + "\nDish created successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getDish(@PathVariable("id") int id) {
        Dish dish = dishService.getDish(id);
        if (dish == null) {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nDish not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateDish(@PathVariable("id") int id, Map<String, Object> payload){
        if (payload == null) {
            return new ResponseEntity<>(ReturnCode.MISSING_ARG.toString() + "\nLack of json payload.", HttpStatus.BAD_REQUEST);
        }
        ReturnCode returnCode = dishService.updateDish(id, payload);
        if(returnCode == ReturnCode.NOT_FOUND){
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nDish not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ReturnCode.OK.toString() + "\nDish updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        ReturnCode returnCode = dishService.deleteDish(id);
        if (returnCode == ReturnCode.NOT_FOUND) {
            return new ResponseEntity<>("Dish deleted successfully", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ReturnCode.OK.toString() + "\nDish deleted successfully", HttpStatus.OK);
    }
}