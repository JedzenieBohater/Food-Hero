package FoodHero.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DishRating extends Rating {
    @ManyToOne
    @JoinColumn(name = "dish_id")
    private int id_dish;

    public int getId_dish() {
        return id_dish;
    }

    public void setId_dish(int id_dish) {
        this.id_dish = id_dish;
    }
}
