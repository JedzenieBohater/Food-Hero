package FoodHero.service.Offers;

import FoodHero.dao.OfferRepository;
import FoodHero.model.Offer;
import FoodHero.service.Dish.DishService;
import FoodHero.service.Offers.POJOS.AvailableOffer;
import FoodHero.service.Offers.POJOS.FilteredOffer;
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


    public List<AvailableOffer> getAllActive() {
        List<Offer> offers = offerRepository.findAll();
        List<AvailableOffer> availableOffers = new ArrayList<>();
        for (Offer offer: offers){
            if(offer.isStatus()){
                AvailableOffer availableOffer = new AvailableOffer(offer);
                availableOffers.add(availableOffer);
            }
        }
        return availableOffers;
    }

    public int getNumberOfActiveOffersAccount(int id){
        List<Offer> offers = offerRepository.findAll();
        int active = 0;
        for (Offer offer: offers){
            if(offer.isStatus() && offer.getAccount().getId() == id){
                active++;
            }
        }
        return active;
    }

    public List<FilteredOffer> getOffersWithFilters(double minPrice, double maxPrice, double minRating, double maxRating, String category, boolean status, String localization, String searchName) {
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
            offers.get(0).retainAll(offers.get(i));
        }
        List<FilteredOffer> filteredOffers = new ArrayList<>();
        for (Offer offer: offers.get(0)){
            FilteredOffer filteredOffer = new FilteredOffer(offer);
            filteredOffers.add(filteredOffer);
        }

        return filteredOffers;
    }

    public Optional<Offer> getOffer(int id) {
        return offerRepository.findById(id);
    }

    public List<Offer> getAllOfferRaw(){
        return offerRepository.findAll();
    }
}
