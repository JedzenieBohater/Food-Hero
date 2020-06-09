package FoodHero.service.Offers.POJOS;

import FoodHero.model.DishRating;
import FoodHero.model.Offer;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class SingleOffer {

    private int id;
    private String firstname;
    private String lastname;
    private String name;
    private String description;
    private String category;
    private double grade;
    private List<Pair<String, Integer>> gradesList;
    private int id_dish;
    private String day;
    private String localization;
    private double price;
    private int preparationTime;
    private int limit;

    public SingleOffer(Offer offer) {
        this.id = offer.getId();
        this.firstname = offer.getAccount().getFirstname();
        this.lastname = offer.getAccount().getLastname();
        this.name = offer.getDish().getName();
        this.description = offer.getDish().getDescription();
        this.category = offer.getDish().getCategory();
        this.grade = offer.getDish().getGrade();
        this.gradesList = new ArrayList<>();
        for (DishRating dishRating : offer.getDish().getRatingList()) {
            String comment = "";
            if (dishRating.getComment() != null) {
                comment = dishRating.getComment();
            }
            Pair<String, Integer> gradePair = Pair.of(comment, dishRating.getGrade());
            this.gradesList.add(gradePair);
        }
        this.id_dish = offer.getDish().getId();
        this.day = offer.getDay();
        this.localization = offer.getLocalization();
        this.price = offer.getPrice();
        this.preparationTime = offer.getPreparation();
        this.limit = offer.getLimit();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public List<Pair<String, Integer>> getGradesList() {
        return gradesList;
    }

    public void setGradesList(List<Pair<String, Integer>> gradesList) {
        this.gradesList = gradesList;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getId_dish() {
        return id_dish;
    }

    public void setId_dish(int id_dish) {
        this.id_dish = id_dish;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
