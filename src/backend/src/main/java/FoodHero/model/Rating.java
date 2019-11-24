package FoodHero.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;
    @NotNull
    private int rating;
    @Size(max = 256)
    private String comment;
    @ManyToOne // usunięcie konta = usunięcie wszystkich recenzji?
    @JoinColumn(name = "idReviever", referencedColumnName = "id")
    private Account reviewer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Account getReviewer() {
        return reviewer;
    }

    public void seReviewer(Account reviewer) {
        this.reviewer = reviewer;
    }
}
