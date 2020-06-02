package FoodHero.controller;

import FoodHero.service.Login.LoginService;
import FoodHero.service.Order.OrderService;
import FoodHero.service.Utils.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    private LoginService loginService;

    @Autowired
    public OrderController(@Lazy OrderService orderService, @Lazy LoginService loginService) {
        this.orderService = orderService;
        this.loginService = loginService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> createOrder(@RequestBody Map<String, String> payload, Principal principal) {
        if (payload == null) {
            return new ResponseEntity<>(ReturnCode.MISSING_ARG.toString() + "\nLack of json payload.", HttpStatus.BAD_REQUEST);
        }
        int userID = 0;
        if (principal == null) {
            return new ResponseEntity<>(ReturnCode.NO_ACCESS.toString() + "\nYou have no permissions to create order", HttpStatus.FORBIDDEN);
        }
        ReturnCode code = orderService.createOrder(payload, loginService.getIdByEmail(principal.getName()));
        if (code == ReturnCode.INCORRECT_DATA) {
            return new ResponseEntity<>(ReturnCode.INCORRECT_DATA.toString() + "\nIncorrect seller/offer/customer while creating order", HttpStatus.BAD_REQUEST);
        } else if (code == ReturnCode.INVALID_AMOUNT_2M) {
            return new ResponseEntity<>(ReturnCode.INVALID_AMOUNT_2M.toString() + "\n", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ReturnCode.OK + "\nOrder created", HttpStatus.OK);
    }
}
