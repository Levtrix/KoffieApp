package Models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "employee_table")
public class SQLEmployee {
    @Id
    @Column(name = "employeeId", nullable = false)
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
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
}
