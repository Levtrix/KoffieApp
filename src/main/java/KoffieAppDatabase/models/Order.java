package KoffieAppDatabase.models;

public class Order {
    private int orderId;
    private Employee employee;
    private Drink drink;
    private int sugarAmount;
    private int milkAmount;

    // Getters and setters
    public int getOrderId() {
        return orderId;
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

    public Order(int orderId, Employee employee, Drink drink, int sugarAmount, int milkAmount) {
        this.orderId = orderId;
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
