package KoffieAppRESTServer.response;

public class DrinkJson {
    private int drinkId;
    private String name;

    public int getDrinkId() {
        return drinkId;
    }

    public String getName() {
        return name;
    }

    public DrinkJson(int drinkId, String name) {
        this.drinkId = drinkId;
        this.name = name;
    }
}
