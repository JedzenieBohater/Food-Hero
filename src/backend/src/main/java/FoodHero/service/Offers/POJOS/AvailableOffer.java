package FoodHero.service.Offers.POJOS;

import FoodHero.model.Offer;

public class AvailableOffer {
    private int id;
    private String url;
    private String name;
    private String category;
    private String description;
    private double grade;
    private double price;
    private double deliverycost;

    public AvailableOffer(Offer offer) {
        this.id = offer.getId();
        this.url = "www.MozeKiedysToZrobimy.pl";
        this.name = offer.getDish().getName();
        this.category = offer.getDish().getCategory();
        this.description = offer.getDish().getDescription();
        this.grade = offer.getDish().getGrade();
        this.price = offer.getPrice();
        this.deliverycost = offer.getDeliverycost();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDeliverycost() {
        return deliverycost;
    }

    public void setDeliverycost(double deliverycost) {
        this.deliverycost = deliverycost;
    }
}
