package FoodHero.service.Account.POJOS;

import FoodHero.model.Account;
import FoodHero.service.AccountRatingRepository.POJOS.RatingAccountPojo;

import java.util.Date;
import java.util.List;

public class AccountDetails {
    private String firstname;
    private String lastname;
    private String email;
    private String description;
    private Date creation_date;
    private String phone;
    private String bank_account;
    private String specialization;
    private Double grade;
    private int activeOffers;
    private int pendingOffers;
    private List<RatingAccountPojo> accountRatings;

    public AccountDetails(Account account, int activeOffers, List<RatingAccountPojo> ratingAccountPojoList) {
        this.firstname = account.getFirstname();
        this.lastname = account.getLastname();
        this.email = account.getLogin().getEmail();
        this.description = account.getDescription();
        this.creation_date = account.getCreation_date();
        this.phone = account.getPhone();
        this.bank_account = account.getBank_account();
        this.specialization = account.getSpecialization();
        this.grade = account.getGrade();
        this.activeOffers = activeOffers;
        this.pendingOffers = 0;
        this.accountRatings = ratingAccountPojoList;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
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

    public int getActiveOffers() {
        return activeOffers;
    }

    public void setActiveOffers(int activeOffers) {
        this.activeOffers = activeOffers;
    }

    public int getPendingOffers() {
        return pendingOffers;
    }

    public void setPendingOffers(int pendingOffers) {
        this.pendingOffers = pendingOffers;
    }

    public List<RatingAccountPojo> getAccountRatings() {
        return accountRatings;
    }

    public void setAccountRatings(List<RatingAccountPojo> accountRatings) {
        this.accountRatings = accountRatings;
    }
}
