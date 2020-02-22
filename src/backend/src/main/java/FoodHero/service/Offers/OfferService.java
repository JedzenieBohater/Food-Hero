package FoodHero.service.Offers;

import FoodHero.dao.OfferRepository;
import FoodHero.model.Offer;
import FoodHero.service.Dish.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    OfferRepository offerRepository;
    @Autowired
    DishService dishService;


    public List<Offer> getAllDishes() {
        return offerRepository.findAll();
    }

    public ArrayList<List<Offer>> getOffersWithFilters(double minPrice, double maxPrice, double minRating, double maxRating, String category, boolean status, String localization, String searchName){
        ArrayList<List<Offer>> offers = new ArrayList<>();

        offers.add(offerRepository.findAll());
        if(minPrice > 0) {
            List<Offer> subList = new ArrayList<>();
            for(Offer offer: offers.get(0)){
                if(offer.getPrice() > minPrice){
                    subList.add(offer);
                }
            }
            offers.add(subList);
        }
        //TODO trzeba to skończyć i skleić z innymi wartościami tak jak opisane w endpoint -- SingleDishList
//        if(maxPrice > 0) {
//            offers.add(offerService.getAllOffersWithMaxPrice(Double.parseDouble(maxPrice)));
//        }
//        if(minRating > 0) {
//            offers.add(offerService.getAllOffersWithMinRating(Double.parseDouble(minRating)));
//        }
//        if(maxRating > 0) {
//            offers.add(offerService.getAllOffersWithMaxRating(Double.parseDouble(maxRating)));
//        }
//        if(category != null) {
//            offers.add(offerService.getAllOffersWithCategory(category));
//        }
//
//        offers.add(offerService.getAllOffersWithStatus(status));
//
//        if(localization != null) {
//            offers.add(offerService.getAllOffersWithLocalization(localization));
//        }
//        if(searchName != null) {
//            offers.add(offerService.getAllOffersWithSearchName(searchName));
//        }
        for (int i=1; i < offers.size(); i++) {
            offers.get(0).retainAll(offers.get(i)); // cześć wspólna wszystkich filtrów
        }
        return offers;
    }

    public List<Offer> getAllOffersWithMinPrice(double price) {
        return offerRepository.findAllByMinPrice(price);
    }

    public List<Offer> getAllOffersWithMaxPrice(double price) {
        return offerRepository.findAllByMaxPrice(price);
    }

    public List<Offer> getAllOffersWithMinRating(double rating) {
        return offerRepository.findAllByMinRating(rating);
    }

    public List<Offer> getAllOffersWithMaxRating(double rating) {
        return offerRepository.findAllByMaxRating(rating);
    }

    public List<Offer> getAllOffersWithCategory(String category) {
        return offerRepository.findAllByCategory(category);
    }

    public List<Offer> getAllOffersWithStatus(String status) {
        return offerRepository.findAllByStatus(status);
    }

    public List<Offer> getAllOffersWithLocalization(String localization) {
        return offerRepository.findAllByLocalization(localization);
    }

    public List<Offer> getAllOffersWithSearchName(String searchName) {
        return offerRepository.findAllBySearchName(searchName);
    }

    public Optional<Offer> getOffer(int id) {
        return offerRepository.findById(id);
    }
}
