package FoodHero.service.AccountRatingRepository;

import FoodHero.controller.OfferController;
import FoodHero.dao.AccountRatingRepository;
import FoodHero.model.AccountRating;
import FoodHero.service.AccountRatingRepository.POJOS.RatingAccountPojo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountRatingService {
    AccountRatingRepository accountRatingRepository;
    private static final Logger LOGGER = LogManager.getLogger(AccountRatingService.class);


    @Autowired
    public AccountRatingService(AccountRatingRepository accountRatingRepository){
        this.accountRatingRepository = accountRatingRepository;
    }

    public List<AccountRating> getAllAccountRatings(){
        return accountRatingRepository.findAll();
    }

    public List<RatingAccountPojo> getOneAccountRatings(int id){
        List<AccountRating> accountRatings = getAllAccountRatings();
        List<RatingAccountPojo> ratingAccountPojoList = new ArrayList<>();
        for (AccountRating accountRating: accountRatings){
            if(accountRating.getAccount().getId() == id){
                RatingAccountPojo ratingAccountPojo = new RatingAccountPojo(accountRating);
                ratingAccountPojoList.add(ratingAccountPojo);
            }
        }
        return ratingAccountPojoList;
    }

}
