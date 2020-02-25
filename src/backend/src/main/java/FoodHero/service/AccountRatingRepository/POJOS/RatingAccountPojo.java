package FoodHero.service.AccountRatingRepository.POJOS;

import FoodHero.model.AccountRating;

public class RatingAccountPojo {

    private String author;
    private String comment;
    private int grade;

    public RatingAccountPojo(AccountRating accountRating){
        this.author = accountRating.getReviewer().getFirstname();
        if(this.author == null || this.author.equals("")){
            this.author = "Anonim";
        }
        this.comment = accountRating.getComment();
        this.grade = accountRating.getGrade();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
