package FoodHero.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "login")
public class Login{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;
    @NotNull
    private String email;
    @NotNull
    @Column(length = 128)
    private String password;
    @NotNull
    private boolean is_admin;
    @OneToOne(mappedBy = "login", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Account account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}