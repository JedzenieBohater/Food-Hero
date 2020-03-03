package FoodHero.model;

import javax.persistence.*;

@Entity
@Table(name = "offer")
public class Offer {

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
    private double price;
    private String localization;
    private boolean status;
    private boolean periodic;
    @Column(name = "limitation")
    private int limit;
    private int preparation;
    private int deliverycost;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localisation) {
        this.localization = localisation;
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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPreparation() {
        return preparation;
    }

    public void setPreparation(int preparation) {
        this.preparation = preparation;
    }

    public int getDeliverycost() {
        return deliverycost;
    }

    public void setDeliverycost(int deliverycost) {
        this.deliverycost = deliverycost;
    }

}
