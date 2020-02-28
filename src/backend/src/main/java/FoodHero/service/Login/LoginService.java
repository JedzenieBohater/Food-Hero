package FoodHero.service.Login;

import FoodHero.dao.LoginRepository;
import FoodHero.model.Account;
import FoodHero.model.Login;
import FoodHero.service.Account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    AccountService accountService;

    public int getIdByEmail(String email){
        return loginRepository.getByEmail(email).get().getId();
    }

    public HttpStatus createLogin(Map<String, Object> payload) {
        if (payload.get("email") == null || payload.get("password") == null || payload.get("email").equals("") || payload.get("password").equals("")) {
            return HttpStatus.BAD_REQUEST;
        }
        if (!loginRepository.getByEmail(String.valueOf(payload.get("email"))).isPresent()) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(String.valueOf(payload.get("password")));
            Login login = new Login(String.valueOf(payload.get("email")), password);
            login = loginRepository.save(login);
            Account account = new Account(login);
            accountService.createAccount(account);
            return HttpStatus.OK;
        }
        return HttpStatus.CONFLICT;
    }

    public Optional<Login> getLogin(int id) {
        return loginRepository.findById(id);
    }

    public HttpStatus updateLogin(int id, Map<String, Object> payload) {
        Optional<Login> optionalLogin = loginRepository.findById(id);
        if(!optionalLogin.isPresent()){
            return HttpStatus.NOT_FOUND;
        }
        Login login = optionalLogin.get();
        if (payload.get("email") != null && !payload.get("email").equals("")) {
            if (!loginRepository.getByEmail(String.valueOf(payload.get("email"))).isPresent()) {
                login.setEmail((String) payload.get("email"));
            }
            else
            {
                return HttpStatus.CONFLICT;
            }
        }
        if (payload.get("password") != null && !payload.get("password").equals("")) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(String.valueOf(payload.get("password")));
            login.setPassword(password);
        }
        if (payload.get("is_active") != null && !payload.get("is_active").equals("")) {
            login.setIs_active((Boolean) payload.get("is_active"));
        }
        loginRepository.save(login);
        return HttpStatus.OK;
    }

    public HttpStatus deleteLogin(int id) {
        Optional<Login> login = loginRepository.findById(id);
        if (login.isPresent()) {
            loginRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }
}
