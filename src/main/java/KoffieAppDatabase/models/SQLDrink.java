package KoffieAppDatabase.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "drink_table")
public class SQLDrink {
    @Id
    @Column(name = "drinkId", nullable = false)
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int drinkId;

    @Column(name = "name", nullable = false)
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

    public SQLDrink() {}

    public SQLDrink(String name) {
        this.name = name;
    }

    public SQLDrink(int drinkId, String name) {
        this.drinkId = drinkId;
        this.name = name;
    }
}
