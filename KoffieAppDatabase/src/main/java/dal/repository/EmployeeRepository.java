package dal.repository;

import models.SQLEmployee;

public class EmployeeRepository extends AbstractRepository<SQLEmployee, Integer> {
    @Override
    public Class<SQLEmployee> getDomainClass() {
        return SQLEmployee.class;
    }
}
