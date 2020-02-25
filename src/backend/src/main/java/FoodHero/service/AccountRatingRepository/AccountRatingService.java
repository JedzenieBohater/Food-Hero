package FoodHero.service.AccountRatingRepository;

import FoodHero.dao.AccountRatingRepository;
import FoodHero.model.AccountRating;
import FoodHero.service.AccountRatingRepository.POJOS.RatingAccountPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountRatingService {
    @Autowired
    AccountRatingRepository accountRatingRepository;

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
