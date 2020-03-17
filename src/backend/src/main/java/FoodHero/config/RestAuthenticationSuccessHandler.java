package FoodHero.config;

import FoodHero.service.Login.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    LoginService loginService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        clearAuthenticationAttributes(request);

        ObjectMapper objectMapper = new ObjectMapper();

        authentication.getPrincipal();
        response.setStatus(HttpStatus.OK.value());
        Map<String, String> data = new HashMap<>();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        data.put("userID", String.valueOf(loginService.getIdByEmail(userDetails.getUsername())));
        response.getOutputStream().println(objectMapper.writeValueAsString(data));
    }
}
