package models;

import javax.persistence.*;

@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(optional =  false)
    private Employee employee;

    @ManyToOne(optional = false)
    private Drink drink;

    @Column(name = "sugaramount", nullable = false)
    private int sugarAmount;

    @Column(name = "milkamount", nullable = false)
    private int milkAmount;

    // Getters and setters
    public int getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Drink getDrink() {
        return  drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public int getSugarAmount() {
        return sugarAmount;
    }

    public void setSugarAmount(int sugarAmount) {
        this.sugarAmount = sugarAmount;
    }

    public int getMilkAmount() {
        return milkAmount;
    }

    public void setMilkAmount(int milkAmount) {
        this.milkAmount = milkAmount;
    }

    // Constructors
    public Order() {}

    public Order(Employee employee, Drink drink, int sugarAmount, int milkAmount) {
        this.employee = employee;
        this.drink = drink;
        this.sugarAmount = sugarAmount;
        this.milkAmount = milkAmount;
    }

    public Order(int id, Employee employee, Drink drink, int sugarAmount, int milkAmount) {
        this.id = id;
        this.employee = employee;
        this.drink = drink;
        this.sugarAmount = sugarAmount;
        this.milkAmount = milkAmount;
    }

    @Override
    public String toString() {
        return employee.getFirstName() + " " + employee.getLastName() + ": " + drink.getName() + ", "
                + sugarAmount + " sugar, " + milkAmount + " milk";
    }
}
