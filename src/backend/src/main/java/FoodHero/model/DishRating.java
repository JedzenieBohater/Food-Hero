package FoodHero.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DishRating extends Rating {
    @ManyToOne
    @JoinColumn(name = "idDish", referencedColumnName = "id")
    private Dish dish;

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
