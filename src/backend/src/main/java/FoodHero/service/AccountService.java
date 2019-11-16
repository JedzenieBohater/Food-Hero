package FoodHero.service;

import FoodHero.dao.AccountRepository;
import FoodHero.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public void createAccount(Account account) {
        if(accountRepository.findById(account.getId()).orElse(null) == null) { // potrzebne to?
            accountRepository.save(account);
        }
    }

    public Account getAccount(int id){
        return accountRepository.getOne(id);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
