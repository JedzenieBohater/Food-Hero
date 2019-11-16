package FoodHero.service;

import FoodHero.dao.LoginRepository;
import FoodHero.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    public void createLogin(Login login) {
        if(loginRepository.findById(login.getId()).orElse(null) == null) { // potrzebne to?
            loginRepository.save(login);
        }
    }

    public Login getLogin(int id){
        return loginRepository.getOne(id);
    }

    public void updateLogin(Login account) {
        loginRepository.save(account);
    }

    public void deleteLogin(int id) {
        loginRepository.deleteById(id);
    }

    public List<Login> getLogins() {
        return loginRepository.findAll();
    }
}
