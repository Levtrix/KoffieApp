package KoffieAppRESTServer.response;

import models.Drink;
import models.Employee;

public class OrderJson {
    private int id;
    private Employee employee;
    private Drink drink;
    private int sugarAmount;
    private int milkAmount;

    public int getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Drink getDrink() {
        return drink;
    }

    public int getSugarAmount() {
        return sugarAmount;
    }

    public int getMilkAmount() {
        return milkAmount;
    }

    public OrderJson(int id, Employee employee, Drink drink, int sugarAmount, int milkAmount) {
        this.id = id;
        this.employee = employee;
        this.drink = drink;
        this.sugarAmount = sugarAmount;
        this.milkAmount = milkAmount;
    }
}
