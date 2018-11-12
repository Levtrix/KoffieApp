package dal.repository;

import models.Employee;

public class EmployeeRepository extends AbstractRepository<Employee, Integer> {
    @Override
    public Class<Employee> getDomainClass() {
        return Employee.class;
    }
}
