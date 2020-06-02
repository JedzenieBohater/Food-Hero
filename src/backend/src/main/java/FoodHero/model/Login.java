package FoodHero.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "login")
public class Login {
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
    @NotNull
    private boolean is_active;
    @JsonIgnore
    @OneToOne(mappedBy = "login", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Account account;

    public Login() {
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
        this.setIs_active(true);
    }

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

    public boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
