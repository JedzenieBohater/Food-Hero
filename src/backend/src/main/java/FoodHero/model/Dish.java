package FoodHero.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;
    private String name;
    private String category;
    private double grade;
    private String description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DishRating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<DishRating> ratingList) {
        this.ratingList = ratingList;
    }
}