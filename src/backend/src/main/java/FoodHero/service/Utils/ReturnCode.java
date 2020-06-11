package FoodHero.service.Utils;

public enum ReturnCode {
    OK(0, "Request processed successfully."),
    MISSING_ARG(101, "Missing arguments of request."),
    CONFLICT_WITH_DB(102, "Conflict in database."),
    INCORRECT_DATA(103, "Incorrect data in json payload."),
    NOT_FOUND(104, "Requested data not found"),
    INVALID_TOKEN(105, "Invalid token"),
    NO_ACCESS(106, "User has not got access to requested resource"),
    INVALID_AMOUNT_2M(107, "User ordered more than is available");


    private final int code;
    private String description;

    ReturnCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        if (this.code != 0)
            return "Error: " + getCode() + " " + getDescription();
        else
            return "Code: " + getCode() + " " + getDescription();

    }
}
