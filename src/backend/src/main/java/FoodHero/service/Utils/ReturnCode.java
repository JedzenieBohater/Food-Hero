package FoodHero.service.Utils;

public enum ReturnCode {
    OK(0, "Request processed successfully."),
    MISSING_ARG(101, "Missing arguments of request."),
    CONFLICT_WITH_DB(102, "Conflict in database."),
    INCORRECT_DATA(103, "Incorrect data in json payload."),
    NOT_FOUND(104, "Requested data not found"),
    INVALID_TOKEN(105, "Invalid token");


    private final int code;
    private final String description;

    private ReturnCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Error: " + getCode() + " " + getDescription();
    }
}
