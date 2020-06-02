package FoodHero.service.Order;

import FoodHero.dao.AccountRepository;
import FoodHero.dao.OfferRepository;
import FoodHero.dao.OrderRepository;
import FoodHero.model.Account;
import FoodHero.model.Offer;
import FoodHero.model.Order;
import FoodHero.service.Utils.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private AccountRepository accountRepository;
    private OfferRepository offerRepository;

    @Autowired
    public OrderService(@Lazy OrderRepository orderRepository, @Lazy AccountRepository accountRepository, @Lazy OfferRepository offerRepository) {
        this.orderRepository = orderRepository;
        this.accountRepository = accountRepository;
        this.offerRepository = offerRepository;
    }

    public Order getOrder(int id) {
        if (orderRepository.findById(id).isPresent()) {
            return orderRepository.findById(id).get();
        }
        return null;
    }

    public ReturnCode deleteOrder(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            orderRepository.delete(optionalOrder.get());
            return ReturnCode.OK;
        }
        return ReturnCode.NOT_FOUND;
    }

    public ReturnCode createOrder(Map<String, String> payload, int userID) {
        int amount;
        Account seller;
        Account customer;
        Offer offer = null;
        try {
            if (payload.get("id_seller") == null || payload.get("id_seller").equals("")) {
                return ReturnCode.INCORRECT_DATA;
            }
            seller = accountRepository.getOne(Integer.valueOf(payload.get("id_seller")));
            if (payload.get("id_customer") == null || payload.get("id_customer").equals("")) {
                return ReturnCode.INCORRECT_DATA;
            }
            if (seller.getId() != userID) {
                return ReturnCode.INCORRECT_DATA;
            }
            customer = accountRepository.getOne(Integer.valueOf(payload.get("id_customer")));
            if (payload.get("id_offer") == null || payload.get("id_offer").equals("")) {
                return ReturnCode.INCORRECT_DATA;
            }
            if (offerRepository.findById(Integer.valueOf(payload.get("id_offer"))).isPresent()) {
                offer = offerRepository.findById(Integer.valueOf(payload.get("id_offer"))).get();
            } else {
                return ReturnCode.NOT_FOUND;
            }
            amount = Integer.parseInt(payload.get("amount"));
        } catch (NumberFormatException e) {
            return ReturnCode.INCORRECT_DATA;
        }
        if (amount > offer.getLimit()) {
            return ReturnCode.INVALID_AMOUNT_2M;
        }
        Date date = new Date();
        Order order = new Order();

        order.setAmount(amount);
        order.setCustomer(customer);
        order.setOffer(offer);
        order.setOrderDate(date);
        order.setSeller(seller);

        orderRepository.save(order);
        return ReturnCode.OK;
    }
}
