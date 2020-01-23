package FoodHero.service.Offers;

import FoodHero.dao.OffersRepository;
import FoodHero.model.Offers;
import FoodHero.service.Dish.DishService;
import FoodHero.service.Offers.POJOS.SingleDishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OffersService {

    @Autowired
    OffersRepository offersRepository;
    @Autowired
    DishService dishService;


    public List<Offers> getAllDishes() {
        List<SingleDishList> singleDishLists = new ArrayList<>();
        List<Offers> listOffers = offersRepository.findAll();
        for (Offers offer : listOffers) {

        }
        SingleDishList singleDishList = new SingleDishList();
        return offersRepository.findAll();
    }

    public List<Offers> getAllDishesWithPrice(double price) {
        return offersRepository.findByPrice(price);
    }
}
