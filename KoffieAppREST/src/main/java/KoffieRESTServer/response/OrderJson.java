package KoffieRESTServer.response;

import models.Drink;
import models.Employee;

public class OrderJson {
    private int orderId;
    private Employee employee;
    private Drink drink;
    private int sugarAmount;
    private int milkAmount;

    public int getOrderId() {
        return orderId;
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

    public OrderJson(int orderId, Employee employee, Drink drink, int sugarAmount, int milkAmount) {
        this.orderId = orderId;
        this.employee = employee;
        this.drink = drink;
        this.sugarAmount = sugarAmount;
        this.milkAmount = milkAmount;
    }
}
