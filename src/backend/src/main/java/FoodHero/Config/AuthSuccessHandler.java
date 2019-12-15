package FoodHero.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        authentication.getPrincipal();
        httpServletResponse.setStatus(HttpStatus.OK.value());
        Map<String, String> data = new HashMap<>();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        data.put("userID", userDetails.getUsername());
        httpServletResponse.getOutputStream().println(objectMapper.writeValueAsString(data));
    }
}
