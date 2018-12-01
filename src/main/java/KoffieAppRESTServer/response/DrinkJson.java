package KoffieAppRESTServer.response;

public class DrinkJson {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DrinkJson(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
