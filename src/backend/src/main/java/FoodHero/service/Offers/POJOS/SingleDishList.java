package FoodHero.service.Offers.POJOS;

public class SingleDishList {

    private String url = "notFoundUrlKucze";
    private String firstname;
    private String lastname;
    private String dishName;
    private String dishDescription;
    private String dishCategory;
    private double grade;
    private String offerDay;
    private String offerLocation;
    private String price;
    private String prepareTime;
    private int limit;

    public SingleDishList(String url, String firstname, String lastname, String dishName, String dishDescription, String dishCategory, double grade, String offerDay, String offerLocation, String price, String prepareTime, int limit) {
        this.url = url;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dishName = dishName;
        this.dishDescription = dishDescription;
        this.dishCategory = dishCategory;
        this.grade = grade;
        this.offerDay = offerDay;
        this.offerLocation = offerLocation;
        this.price = price;
        this.prepareTime = prepareTime;
        this.limit = limit;
    }

    public SingleDishList(){

    }
}
