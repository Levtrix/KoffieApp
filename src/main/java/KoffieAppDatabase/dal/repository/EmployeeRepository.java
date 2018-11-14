package KoffieAppDatabase.dal.repository;

import KoffieAppDatabase.models.SQLEmployee;

public class EmployeeRepository extends AbstractRepository<SQLEmployee, Integer> {
    @Override
    public Class<SQLEmployee> getDomainClass() {
        return SQLEmployee.class;
    }
}
