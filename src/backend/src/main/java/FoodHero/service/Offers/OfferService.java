package FoodHero.service.Offers;

import FoodHero.dao.OfferRepository;
import FoodHero.model.Account;
import FoodHero.model.Dish;
import FoodHero.model.Offer;
import FoodHero.service.Account.AccountService;
import FoodHero.service.Dish.DishService;
import FoodHero.service.Offers.POJOS.SingleOffer;
import FoodHero.service.Utils.ReturnCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OfferService {

    private static final Logger LOGGER = LogManager.getLogger(OfferService.class);
    OfferRepository offerRepository;
    DishService dishService;
    AccountService accountService;


    @Autowired
    public OfferService(@Lazy OfferRepository offerRepository, @Lazy DishService dishService, @Lazy AccountService accountService) {
        this.offerRepository = offerRepository;
        this.dishService = dishService;
        this.accountService = accountService;
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

    public List<SingleOffer> getOffersWithFilters(double minPrice, double maxPrice, double minRating, double maxRating, String category, boolean status, String localization, String searchName) {
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
        List<SingleOffer> singleOffers = new ArrayList<>();
        for (Offer offer : offers.get(0)) {
            SingleOffer singleOffer = new SingleOffer(offer);
            singleOffers.add(singleOffer);
        }

        return singleOffers;
    }

    public ReturnCode createOffer(int id, Map<String, Object> payload) {
        Account account = accountService.getAccount(id);
        if (payload.get("id_dish") == null || payload.get("id_dish").equals("")) {
            return ReturnCode.INCORRECT_DATA;
        }
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

    public ReturnCode updateOffer(int id, Map<String, Object> payload) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if (!optionalOffer.isPresent()) {
            return ReturnCode.NOT_FOUND;
        }
        Offer offer = optionalOffer.get();

        try {
            if (payload.get("day") != null && !payload.get("day").equals("")) {
                offer.setDay((String) payload.get("day"));
            }
            if (payload.get("price") != null && !payload.get("price").equals("")) {
                offer.setPrice((Double) payload.get("price"));
            }
            if (payload.get("localization") != null && !payload.get("localization").equals("")) {
                offer.setLocalization((String) payload.get("localization"));
            }
            if (payload.get("status") != null && !payload.get("status").equals("")) {
                offer.setStatus((Boolean) payload.get("status"));
            }
            if (payload.get("periodic") != null && !payload.get("periodic").equals("")) {
                offer.setPeriodic((Boolean) payload.get("periodic"));
            }
            if (payload.get("limitation") != null && !payload.get("limitation").equals("")) {
                offer.setLimit((Integer) payload.get("limitation"));
            }
            if (payload.get("preparation") != null && !payload.get("preparation").equals("")) {
                offer.setPreparation((Integer) payload.get("preparation"));
            }
            if (payload.get("deliverycost") != null && !payload.get("deliverycost").equals("")) {
                offer.setPreparation((Integer) payload.get("preparation"));
            }
        } catch (NumberFormatException e) {
            return ReturnCode.INCORRECT_DATA;
        }
        return ReturnCode.OK;
    }

    public ReturnCode deleteOffer(int id) {
        if (offerRepository.findById(id).isPresent()) {
            offerRepository.deleteById(id);
            return ReturnCode.OK;
        } else {
            return ReturnCode.NOT_FOUND;
        }
    }

    public SingleOffer getSingleOffer(int id) {
        if (offerRepository.findById(id).isPresent()) {
            return new SingleOffer(offerRepository.findById(id).get());
        }
        return null;
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
