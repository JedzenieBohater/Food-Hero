package FoodHero.service.Offers;

import FoodHero.dao.OffersRepository;
import FoodHero.model.Offers;
import FoodHero.service.Dish.DishService;
import FoodHero.service.Offers.POJOS.SingleDishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Offers> getAllOffersWithMinPrice(double price) {
        return offersRepository.findAllByMinPrice(price);
    }

    public List<Offers> getAllOffersWithMaxPrice(double price) {
        return offersRepository.findAllByMaxPrice(price);
    }

    public List<Offers> getAllOffersWithMinRating(double rating) {
        return offersRepository.findAllByMinRating(rating);
    }

    public List<Offers> getAllOffersWithMaxRating(double rating) {
        return offersRepository.findAllByMaxRating(rating);
    }

    public List<Offers> getAllOffersWithCategory(String category) {
        return offersRepository.findAllByCategory(category);
    }

    public List<Offers> getAllOffersWithStatus(String status) {
        return offersRepository.findAllByStatus(status);
    }

    public List<Offers> getAllOffersWithLocalization(String localization) {
        return offersRepository.findAllByLocalization(localization);
    }

    public List<Offers> getAllOffersWithSearchName(String searchName) {
        return offersRepository.findAllBySearchName(searchName);
    }

    public Optional<Offers> getOffer(int id) {
        return offersRepository.findById(id);
    }
}
