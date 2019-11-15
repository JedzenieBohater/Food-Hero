package FoodHero.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;
    private String firstname;
    private String lastname;
    @Size(max = 256)
    private String description;
    @Size(max = 256)
    private String bank_account;
    @NotNull
    private Date creation_date;
    @Size(max = 9)
    private String phone;
    private String specialization;
    private Double grade;
    @NotNull
    private boolean cookStatus;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private Login id_login;

    // private List<String> localization;
    // private Rating rating;
    // private Login login;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public boolean isCookStatus() {
        return cookStatus;
    }

    public void setCookStatus(boolean cookStatus) {
        this.cookStatus = cookStatus;
    }

    public Login getId_login() {
        return id_login;
    }

    public void setId_login(Login id_login) {
        this.id_login = id_login;
    }
}
