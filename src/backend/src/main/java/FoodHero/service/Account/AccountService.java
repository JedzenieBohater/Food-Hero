package FoodHero.service.Account;

import FoodHero.dao.AccountRepository;
import FoodHero.model.Account;
import FoodHero.model.Dish;
import FoodHero.model.Offers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public void createAccount(Account account) {
        if(accountRepository.findById(account.getId()).orElse(null) == null) {
            accountRepository.save(account);
        }
    }

    public Optional<Account> getAccount(int id){
        return accountRepository.findById(id);
    }

    public List<Offers> getAccountOffers(int id) {
        return accountRepository.findAllOffersByAccountId(id);
    }

    public List<Dish> getAccountDishes(int id) {
        return accountRepository.findAllDishesByAccountId(id);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    public Optional<List<Account>> getAccounts() {
        return Optional.ofNullable(accountRepository.findAll());
    }

}
