package KoffieAppDatabase.models;

public class Drink {
    private int drinkId;
    private String name;

    // Getters and setters
    public int getDrinkId() {
        return drinkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Constructors
    public Drink() {}

    public Drink(String name) {
        this.name = name;
    }

    public Drink(int drinkId, String name) {
        this.drinkId = drinkId;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
