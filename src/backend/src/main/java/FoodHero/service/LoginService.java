package FoodHero.service;

import FoodHero.dao.LoginRepository;
import FoodHero.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements UserDetailsService {
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

    public Login getLogin(String email){
        return loginRepository.getByEmail(email);
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Login login = getLogin(email);
        if (login == null) {
            throw new UsernameNotFoundException("No user found with email: " + email);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<String> role = new ArrayList<>();

        if (login.getIs_admin())
        {
            role.add("ADMIN");
        }
        else
        {
            role.add("USER");
        }

        return  new org.springframework.security.core.userdetails.User
                (login.getEmail(),
                        login.getPassword(), enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked,
                        getAuthorities(role));
    }

    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Autowired
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
