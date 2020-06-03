package FoodHero.service.Payment;

import FoodHero.dao.PaymentRepository;
import FoodHero.model.Order;
import FoodHero.model.Payment;
import FoodHero.service.Utils.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(@Lazy PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment getOne(int id){
        if(paymentRepository.findById(id).isPresent()){
            return paymentRepository.findById(id).get();
        }
        return null;
    }

    public ReturnCode deletePayment(int id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            paymentRepository.delete(optionalPayment.get());
            return ReturnCode.OK;
        }
        return ReturnCode.NOT_FOUND;
    }
}
