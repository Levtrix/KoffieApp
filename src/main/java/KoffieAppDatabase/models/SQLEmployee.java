package KoffieAppDatabase.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "employee_table")
public class SQLEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    // Getters and setters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return  lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SQLEmployee() {}

    public SQLEmployee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public SQLEmployee(int employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
