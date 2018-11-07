package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "order_table")
public class SQLOrder {
    @Id
    @Column(name = "orderId", nullable = false)
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int orderId;

    //private SQLEmployee employee;
    //private SQLDrink drink;

    @Column(name = "sugarAmount", nullable = false)
    private int sugarAmount;

    @Column(name = "milkAmount", nullable = false)
    private int milkAmount;

    // Getters and setters
    public int getOrderId() {
        return orderId;
    }

    public int getSugarAmount() {
        return sugarAmount;
    }

    public void setSugarAmount(int sugarAmount) {
        this.sugarAmount = sugarAmount;
    }

    //TODO: add employee and drink relations

    public int getMilkAmount() {
        return milkAmount;
    }

    public void setMilkAmount(int milkAmount) {
        this.milkAmount = milkAmount;
    }

}
