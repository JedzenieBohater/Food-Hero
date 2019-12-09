package FoodHero.service;

import FoodHero.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class LoginDetailsService implements UserDetailsService {

    @Autowired
    private LoginService loginService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Login login = loginService.getLogin(email);
        if (login == null)
        {
            throw new UsernameNotFoundException(email);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet< >();
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(login.getEmail(), login.getPassword(),
                grantedAuthorities);
        System.out.println(user);
        return user;
    }
}
