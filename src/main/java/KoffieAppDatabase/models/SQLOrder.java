package KoffieAppDatabase.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "order_table")
public class SQLOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @Column(name = "employeeId", nullable = false)
    private int employeeId;

    @Column(name = "drinkId", nullable = false)
    private int drinkId;

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

    public int getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getDrinkId() {
        return this.drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public int getMilkAmount() {
        return milkAmount;
    }

    public void setMilkAmount(int milkAmount) {
        this.milkAmount = milkAmount;
    }

    public SQLOrder() {}

    public SQLOrder(int sugarAmount, int milkAmount, int employeeId, int drinkId) {
        this.sugarAmount = sugarAmount;
        this.milkAmount = milkAmount;
        this.employeeId = employeeId;
        this.drinkId = drinkId;
    }

    public SQLOrder(int orderId, int sugarAmount, int milkAmount, int employeeId, int drinkId) {
        this.orderId = orderId;
        this.sugarAmount = sugarAmount;
        this.milkAmount = milkAmount;
        this.employeeId = employeeId;
        this.drinkId = drinkId;
    }

}
