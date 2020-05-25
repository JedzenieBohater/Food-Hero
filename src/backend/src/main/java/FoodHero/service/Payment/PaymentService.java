package FoodHero.service.Payment;

import FoodHero.dao.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(@Lazy PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }
}
