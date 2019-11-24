package FoodHero.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AccountRating extends Rating {
    @ManyToOne
    @JoinColumn(name = "idReceiver", referencedColumnName = "id")
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
