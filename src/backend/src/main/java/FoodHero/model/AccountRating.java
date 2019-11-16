package FoodHero.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AccountRating extends Rating {
    @ManyToOne
    @JoinColumn(name = "account_id")
    private int id_account;

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }
}
