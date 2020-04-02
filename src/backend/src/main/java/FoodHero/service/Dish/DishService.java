package FoodHero.service.Dish;

import FoodHero.controller.OfferController;
import FoodHero.dao.DishRepository;
import FoodHero.model.Account;
import FoodHero.model.Dish;
import FoodHero.service.Account.AccountService;
import FoodHero.service.Utils.ReturnCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DishService {

    private DishRepository dishRepository;
    private AccountService accountService;
    private static final Logger LOGGER = LogManager.getLogger(DishService.class);


    @Autowired
    public DishService(DishRepository dishRepository, AccountService accountService){
        this.dishRepository = dishRepository;
        this.accountService = accountService;
    }

    public ReturnCode createDish(int id, Map<String, Object> payload) {
        Account account = accountService.getAccount(id);
        if (account == null) {
            return ReturnCode.NOT_FOUND;
        }
        Dish dish = new Dish();
        if (payload.get("name") != null && !payload.get("name").equals("") &&
                payload.get("category") != null && !payload.get("category").equals("") &&
                payload.get("description") != null && !payload.get("description").equals("")) {
            dish.setAccount(account);
            dish.setName((String) payload.get("name"));
            dish.setCategory((String) payload.get("category"));
            dish.setDescription((String) payload.get("description"));
            dishRepository.save(dish);
            return ReturnCode.OK;
        }
        return ReturnCode.INCORRECT_DATA;
    }

    public Dish getDish(int id) {
        if(dishRepository.findById(id).isPresent()){
            return dishRepository.findById(id).get();
        }
        return null;
    }

    public ReturnCode updateDish(int id, Map<String, Object> payload) {
        //TODO przy przepinaniu dań między użytkownikami trzeba zadbać o to aby tylko admin mogl to zrobić.
        //jak narazie tego nei dodaje
        Optional<Dish> dishOptional = dishRepository.findById(id);
        if(!dishOptional.isPresent()){
            return ReturnCode.NOT_FOUND;
        }
        Dish dish = dishOptional.get();
        if (payload.get("name") != null && !payload.get("name").equals("")){
            dish.setName((String) payload.get("name"));
        }
        if(payload.get("category") != null && !payload.get("category").equals("")){
            dish.setCategory((String) payload.get("category"));
        }
        if(payload.get("description") != null && !payload.get("description").equals("")) {
            dish.setDescription((String) payload.get("description"));
        }
        dishRepository.save(dish);
        return ReturnCode.OK;
    }

    public ReturnCode deleteDish(int id) {
        Optional<Dish> dish = dishRepository.findById(id);
        if (dish.isPresent()) {
            dishRepository.deleteById(id);
            return ReturnCode.OK;
        }
        return ReturnCode.NOT_FOUND;
    }

    public List<Dish> getAllDishRaw() {
        return dishRepository.findAll();
    }
}