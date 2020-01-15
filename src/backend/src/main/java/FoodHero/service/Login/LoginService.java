package FoodHero.service.Login;

import FoodHero.dao.LoginRepository;
import FoodHero.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService{
    
    @Autowired
    LoginRepository loginRepository;

    public void createLogin(Login login) {
        if(loginRepository.findById(login.getId()).orElse(null) == null) { // potrzebne to?
            loginRepository.save(login);
        }
    }

    public Optional<Login> getLogin(int id){
        return loginRepository.findById(id);
    }

    /*public Login getLogin(String email){
        return loginRepository.getByEmail(email);
    }*/

    public void updateLogin(Login account) {
        loginRepository.save(account);
    }

    public void deleteLogin(int id) {
        loginRepository.deleteById(id);
    }

    public Optional<List<Login>> getLogins() {
        return Optional.ofNullable(loginRepository.findAll());
    }

}
