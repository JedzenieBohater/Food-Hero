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

    //TODO trzeba to skończyć i skleić z innymi wartościami tak jak opisane w endpoint -- SingleDishList
    public List<Offer> getOffersWithFilters(double minPrice, double maxPrice, double minRating, double maxRating, String category, boolean status, String localization, String searchName) {
        List<List<Offer>> offers = new ArrayList<>();
        offers.add(offerRepository.findAll());
        if (minPrice > 0) {
            List<Offer> subList = new ArrayList<>();
            for (Offer offer : offers.get(0)) {
                if (offer.getPrice() >= minPrice) {
                    System.out.println(offer.getId());
                    subList.add(offer);
                }
            }
            offers.add(subList);
        }
        if (maxPrice > 0) {
            List<Offer> subList = new ArrayList<>();
            for (Offer offer : offers.get(0)) {
                if (offer.getPrice() <= maxPrice) {
                    subList.add(offer);
                }
            }
            offers.add(subList);
        }
        if (minRating > 0) {
            List<Offer> subList = new ArrayList<>();
            for (Offer offer : offers.get(0)) {
                if (offer.getDish().getGrade() >= minRating) {
                    subList.add(offer);
                }
            }
            offers.add(subList);
        }
        if (maxRating > 0) {
            List<Offer> subList = new ArrayList<>();
            for (Offer offer : offers.get(0)) {
                if (offer.getDish().getGrade() <= maxRating) {
                    subList.add(offer);
                }
            }
            offers.add(subList);
        }
        if (!category.equals("")) {
            List<Offer> subList = new ArrayList<>();
            for (Offer offer : offers.get(0)) {
                if (offer.getDish().getCategory().equals(category)) {
                    subList.add(offer);
                }
            }
            offers.add(subList);
        }

        if (!localization.equals("")) {
            List<Offer> subList = new ArrayList<>();
            for (Offer offer : offers.get(0)) {
                if (offer.getDish().getName().matches("(.*)" + localization + "(.*)")) {
                    subList.add(offer);
                }
            }
            offers.add(subList);
        }

        if (!searchName.equals("")) {
            List<Offer> subList = new ArrayList<>();
            for (Offer offer : offers.get(0)) {
                if (offer.getDish().getName().matches("(.*)" + searchName + "(.*)")) {
                    subList.add(offer);
                }
            }
            offers.add(subList);
        }

        List<Offer> subList = new ArrayList<>();
        for (Offer offer : offers.get(0)) {
            if (offer.isStatus() == status) {
                subList.add(offer);
            }
        }
        offers.add(subList);


        for (int i = 1; i < offers.size(); i++) {
            offers.get(0).retainAll(offers.get(i)); // cześć wspólna wszystkich filtrów
        }
//        for (int i = 1; i < offers.size(); i++) {
//            System.out.println(offers.get(i));
//        }
        return offers.get(0);
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
