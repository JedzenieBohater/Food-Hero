package FoodHero.service.Dish;

import FoodHero.dao.DishRepository;
import FoodHero.model.Account;
import FoodHero.model.Dish;
import FoodHero.service.Account.AccountService;
import FoodHero.service.Offers.OfferService;
import FoodHero.service.Utils.ReturnCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DishService {

    private static final Logger LOGGER = LogManager.getLogger(DishService.class);
    private DishRepository dishRepository;
    private AccountService accountService;
    private OfferService offerService;


    @Autowired
    public DishService(@Lazy DishRepository dishRepository, @Lazy AccountService accountService) {
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
            Dish savedDish = dishRepository.save(dish);
            ReturnCode returnCode = ReturnCode.OK;
            returnCode.setDescription(String.valueOf(savedDish.getId()));
            return returnCode;
        }
        return ReturnCode.INCORRECT_DATA;
    }

    public Dish getDish(int id) {
        if (dishRepository.findById(id).isPresent()) {
            return dishRepository.findById(id).get();
        }
        return null;
    }

    public ReturnCode updateDish(int id, Map<String, Object> payload) {
        Optional<Dish> dishOptional = dishRepository.findById(id);
        if (!dishOptional.isPresent()) {
            return ReturnCode.NOT_FOUND;
        }
        Dish dish = dishOptional.get();
        if (payload.get("name") != null && !payload.get("name").equals("")) {
            dish.setName((String) payload.get("name"));
        }
        if (payload.get("category") != null && !payload.get("category").equals("")) {
            dish.setCategory((String) payload.get("category"));
        }
        if (payload.get("description") != null && !payload.get("description").equals("")) {
            dish.setDescription((String) payload.get("description"));
        }
        dishRepository.save(dish);
        return ReturnCode.OK;
    }

    public ReturnCode deleteDish(int id) {
        Optional<Dish> dish = dishRepository.findById(id);
        if (dish.isPresent()) {
            //List<Offer> offers = offer

            //dishRepository.deleteById(id);
            dishRepository.delete(dish.get());
            return ReturnCode.OK;
        }
        return ReturnCode.NOT_FOUND;
    }

    public ReturnCode uploadImage(MultipartFile file, int id) {
        String extension;
        if (file != null && file.getOriginalFilename() != null && file.getOriginalFilename().lastIndexOf(".") == -1) {
            return ReturnCode.INCORRECT_DATA;
        }
        new File("./dishImages/" + id).mkdir();
        extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
        Path filepath = Paths.get("dishImages/" + id + "/image" + extension);
        try {
            OutputStream outputStream = Files.newOutputStream(filepath);
            outputStream.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ReturnCode.OK;
    }

    public File getImage(int id) {
        File file = new File("dishImages/" + id + "/image.png");
        if(file.exists())
        {
            return file;
        }
        return null;
    }

    public List<Dish> getAllDishRaw() {
        return dishRepository.findAll();
    }
}