package FoodHero.service.Offers;

import FoodHero.dao.OfferRepository;
import FoodHero.model.Account;
import FoodHero.model.Dish;
import FoodHero.model.Offer;
import FoodHero.service.Account.AccountService;
import FoodHero.service.AccountRatingRepository.AccountRatingService;
import FoodHero.service.Dish.DishService;
import FoodHero.service.Offers.POJOS.AvailableOffer;
import FoodHero.service.Offers.POJOS.FilteredOffer;
import FoodHero.service.Utils.ReturnCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OfferService {

    OfferRepository offerRepository;
    DishService dishService;
    AccountService accountService;
    private static final Logger LOGGER = LogManager.getLogger(OfferService.class);


    @Autowired
    public OfferService(@Lazy OfferRepository offerRepository, @Lazy DishService dishService, @Lazy AccountService accountService) {
        this.offerRepository = offerRepository;
        this.dishService = dishService;
        this.accountService = accountService;
    }

    public List<AvailableOffer> getAllActive() {
        List<Offer> offers = offerRepository.findAll();
        List<AvailableOffer> availableOffers = new ArrayList<>();
        for (Offer offer : offers) {
            if (offer.isStatus()) {
                AvailableOffer availableOffer = new AvailableOffer(offer);
                availableOffers.add(availableOffer);
            }
        }
        return availableOffers;
    }

    public int getNumberOfActiveOffersAccount(int id) {
        List<Offer> offers = offerRepository.findAll();
        int active = 0;
        for (Offer offer : offers) {
            if (offer.isStatus() && offer.getAccount().getId() == id) {
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
                if (offer.getLocalization().matches("(.*)" + localization + "(.*)")) {
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
        for (Offer offer : offers.get(0)) {
            FilteredOffer filteredOffer = new FilteredOffer(offer);
            filteredOffers.add(filteredOffer);
        }

        return filteredOffers;
    }

    public ReturnCode createOffer(int id, Map<String, Object> payload) {
        Account account = accountService.getAccount(id);
        if (payload.get("id_dish") == null || payload.get("id_dish").equals("")) {
            return ReturnCode.INCORRECT_DATA;
        }
        //TODO trzeba tez zrobic sprawdzanie czy nie ma juz podpietego dania pod dana oferte
        Dish dish = dishService.getDish((Integer) payload.get("id_dish"));
        if (account == null || dish == null) {
            return ReturnCode.NOT_FOUND;
        }
        Offer offer = new Offer();
        if (payload.get("hours") != null && !payload.get("hours").equals("") &&
                payload.get("day") != null && !payload.get("day").equals("") &&
                payload.get("price") != null && !payload.get("price").equals("") &&
                payload.get("localization") != null && !payload.get("localization").equals("") &&
                payload.get("status") != null && !payload.get("status").equals("") &&
                payload.get("periodic") != null && !payload.get("periodic").equals("") &&
                payload.get("limitation") != null && !payload.get("limitation").equals("") &&
                payload.get("preparation") != null && !payload.get("preparation").equals("") &&
                payload.get("deliverycost") != null && !payload.get("deliverycost").equals("")) {


            try {
                offer.setAccount(account);
                offer.setDish(dish);
                offer.setHours((String) payload.get("hours"));
                offer.setDay((String) payload.get("day"));
                offer.setPrice((Double) payload.get("price"));
                offer.setLocalization((String) payload.get("localization"));
                offer.setStatus((Boolean) payload.get("status"));
                offer.setPeriodic((Boolean) payload.get("periodic"));
                offer.setLimit((Integer) payload.get("limitation"));
                offer.setPreparation((Integer) payload.get("preparation"));
                offer.setDeliverycost((Integer) payload.get("deliverycost"));
            } catch (NumberFormatException e) {
                return ReturnCode.INCORRECT_DATA;
            }
            offerRepository.save(offer);
            return ReturnCode.OK;
        }
        return ReturnCode.INCORRECT_DATA;
    }

    public ReturnCode deleteOffer(int id) {
        if (offerRepository.findById(id).isPresent()) {
            offerRepository.deleteById(id);
            return ReturnCode.OK;
        } else {
            return ReturnCode.NOT_FOUND;
        }
    }

    public Offer getOffer(int id) {
        if (offerRepository.findById(id).isPresent()) {
            return offerRepository.findById(id).get();
        }
        return null;
    }

    public List<Offer> getAllOfferRaw() {
        return offerRepository.findAll();
    }
}
