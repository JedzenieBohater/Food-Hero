package FoodHero.service.Account;

import FoodHero.dao.AccountRepository;
import FoodHero.dao.DishRepository;
import FoodHero.dao.OfferRepository;
import FoodHero.model.Account;
import FoodHero.model.Dish;
import FoodHero.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    DishRepository dishRepository;

    public void createAccount(Account account) {
        if(accountRepository.findById(account.getId()).orElse(null) == null) {
            accountRepository.save(account);
        }
    }

    public Optional<Account> getAccount(int id){
        return accountRepository.findById(id);
    }

    public List<Offer> getAccountOffers(int id) {
        List<Offer> allOffers = offerRepository.findAll();
        List<Offer> accountOffers = new ArrayList<>();
        for (Offer offer: allOffers){
            if(offer.getAccount().getId() == id){
                accountOffers.add(offer);
            }
        }
        return accountOffers;
    }

    public List<Dish> getAccountDishes(int id) {
        List<Dish> allDishes = dishRepository.findAll();
        List<Dish> accountDishes = new ArrayList<>();
        for (Dish dish: allDishes){
            if(dish.getAccount().getId() == id){
                accountDishes.add(dish);
            }
        }
        return accountDishes;
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    public Optional<List<Account>> getAccounts() {
        return Optional.of(accountRepository.findAll());
    }

}
