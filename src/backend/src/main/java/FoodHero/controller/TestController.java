package FoodHero.controller;

import FoodHero.model.Account;
import FoodHero.model.Test;
import FoodHero.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/")
    public String index(){
        return "No witam witam";
    }

    @GetMapping(value = "/test/{id}")
    public ResponseEntity<Test> test(@PathVariable("id") int id){
        Optional<Test> test = testService.getTest(id);
        if(test.isPresent())
        {
            return new ResponseEntity<>(test.get(), HttpStatus.OK);
        }
        else
        {
            return (ResponseEntity<Test>) ResponseEntity.notFound();
        }
    }
}
