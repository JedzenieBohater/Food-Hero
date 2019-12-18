package FoodHero.service;

import FoodHero.dao.LoginRepository;
import FoodHero.model.Login;
import FoodHero.model.LoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Login> login = loginRepository.getByEmail(email);

        login.orElseThrow(() -> new UsernameNotFoundException("Not found account associated : " + email));

        return login.map(LoginDetails::new).get();
    }
}
