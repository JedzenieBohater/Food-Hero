package FoodHero.service.Account;

import FoodHero.dao.AccountRepository;
import FoodHero.model.Account;
import FoodHero.model.Dish;
import FoodHero.model.Offer;
import FoodHero.service.Account.POJOS.AccountDetails;
import FoodHero.service.AccountRatingRepository.AccountRatingService;
import FoodHero.service.AccountRatingRepository.POJOS.RatingAccountPojo;
import FoodHero.service.Dish.DishService;
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
import java.util.*;

@Service
public class AccountService {
    private static final Logger LOGGER = LogManager.getLogger(AccountService.class);
    AccountRepository accountRepository;
    DishService dishService;
    AccountRatingService accountRatingService;
    OfferService offerService;


    @Autowired
    public AccountService(@Lazy AccountRepository accountRepository, @Lazy DishService dishService, @Lazy AccountRatingService accountRatingService, @Lazy OfferService offerService) {
        this.accountRepository = accountRepository;
        this.dishService = dishService;
        this.accountRatingService = accountRatingService;
        this.offerService = offerService;
    }

    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    public AccountDetails getAccountWithGrades(int id) {
        if (!accountRepository.findById(id).isPresent()) {
            return null;
        }
        Account account = accountRepository.findById(id).get();
        List<RatingAccountPojo> ratingAccountPojoList = accountRatingService.getOneAccountRatings(account.getId());
        int activeOffers = offerService.getNumberOfActiveOffersAccount(id);
        return new AccountDetails(account, activeOffers, ratingAccountPojoList);
    }

    public Account getAccount(int id) {
        if (accountRepository.findById(id).isPresent()) {
            return accountRepository.findById(id).get();
        }
        return null;
    }

    public List<Offer> getAccountOffers(int id) {
        List<Offer> allOffers = offerService.getAllOfferRaw();
        List<Offer> accountOffers = new ArrayList<>();
        for (Offer offer : allOffers) {
            if (offer.getAccount().getId() == id) {
                accountOffers.add(offer);
            }
        }
        return accountOffers;
    }

    public List<Dish> getAccountDishes(int id) {
        List<Dish> allDishes = dishService.getAllDishRaw();
        List<Dish> accountDishes = new ArrayList<>();
        for (Dish dish : allDishes) {
            if (dish.getAccount().getId() == id) {
                accountDishes.add(dish);
            }
        }
        return accountDishes;
    }

    public ReturnCode updateAccount(int id, Map<String, Object> payload) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (!accountOptional.isPresent()) {
            return ReturnCode.NOT_FOUND;
        }
        Account account = accountOptional.get();
        if (payload.get("firstname") != null && !payload.get("firstname").equals("")) {
            account.setFirstname((String) payload.get("firstname"));
        }
        if (payload.get("lastname") != null && !payload.get("lastname").equals("")) {
            account.setLastname((String) payload.get("lastname"));
        }
        if (payload.get("description") != null && !payload.get("description").equals("")) {
            account.setDescription((String) payload.get("description"));
        }
        if (payload.get("bank_account") != null && !payload.get("bank_account").equals("")) {
            account.setBank_account((String) payload.get("bank_account"));
        }
        if (payload.get("phone") != null && !payload.get("phone").equals("")) {
            account.setPhone((String) payload.get("phone"));
        }
        if (payload.get("specialization") != null && !payload.get("specialization").equals("")) {
            account.setSpecialization((String) payload.get("specialization"));
        }
        if (payload.get("cook_status") != null && !payload.get("cook_status").equals("")) {
            account.setCookStatus((Boolean) payload.get("cook_status"));
        }
        if (payload.get("cook_status") != null && !payload.get("cook_status").equals("")) {
            account.setCookStatus((Boolean) payload.get("cook_status"));
        }
        Set<String> languages = new HashSet<>(Arrays.asList("pl", "en"));
        if (payload.get("language") != null && !payload.get("language").equals("")) {
            if (languages.contains(payload.get("language"))) {
                account.setLastname((String) payload.get("language"));
            }
        }
        accountRepository.save(account);
        return ReturnCode.OK;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public ReturnCode uploadImage(MultipartFile file, int id){
        String extension;
        if(file != null && file.getOriginalFilename() != null && file.getOriginalFilename().lastIndexOf(".") == -1){
            return ReturnCode.INCORRECT_DATA;
        }
        new File("./accountImages/" + id).mkdir();
        extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
        Path filepath = Paths.get("./accountImages/" + id + "/image" + extension);
        try {
            OutputStream outputStream = Files.newOutputStream(filepath);
            outputStream.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ReturnCode.OK;
    }

    public File getImage(int id){
        File file = new File("accountImages/" + id + "/image.png");
        if(file.exists())
        {
            return file;
        }
        return null;
    }

}
