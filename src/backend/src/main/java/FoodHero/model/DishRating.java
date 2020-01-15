package FoodHero.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="dish_grades")
public class DishRating extends Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idDish", referencedColumnName = "id")
    private Dish dish;
    private int grade;
    private String comment;



    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

}
