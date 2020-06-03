package FoodHero.controller;

import FoodHero.model.Order;
import FoodHero.model.Payment;
import FoodHero.service.Login.LoginService;
import FoodHero.service.Payment.PaymentService;
import FoodHero.service.Utils.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;
    private LoginService loginService;

    @Autowired
    public PaymentController(PaymentService paymentService, LoginService loginService) {
        this.paymentService = paymentService;
        this.loginService = loginService;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable("id") int id, Principal principal){
        int userID = 0;
        if (principal != null) {
            userID = loginService.getIdByEmail(principal.getName());
        } else {
            userID = -1;
        }
        Payment payment = paymentService.getOne(id);
        if (payment != null) {
            if ((userID != -1 && userID == payment.getCustomer().getId()) || (loginService.getLogin(userID).isPresent() && loginService.getLogin(userID).get().getIs_admin())) {
                ReturnCode returnCode = paymentService.deletePayment(id);
                if (returnCode == ReturnCode.NOT_FOUND) {
                    return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(ReturnCode.OK.toString() + "\nPayment deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ReturnCode.NO_ACCESS.toString() + "\nYou have no permissions to delete payment", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(ReturnCode.NOT_FOUND.toString() + "\nPayment not found", HttpStatus.NOT_FOUND);
        }
    }
}
