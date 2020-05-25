package FoodHero.service.Order;

import FoodHero.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(@Lazy OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
}
