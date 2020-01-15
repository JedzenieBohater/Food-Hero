package FoodHero.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="offers")
public class Offers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "id_dish")
    private Dish dish;
    private String hours;
    private String day;
    private String localisation;
    private boolean status;
    private boolean periodic;
    @JsonIgnore
    @OneToMany(mappedBy = "dish", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<DishRating> ratingList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLocalization() {
        return localisation;
    }

    public void setLocalization(String localisation) {
        this.localisation = localisation;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isPeriodic() {
        return periodic;
    }

    public void setPeriodic(boolean periodic) {
        this.periodic = periodic;
    }

    public List<DishRating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<DishRating> ratingList) {
        this.ratingList = ratingList;
    }
}
