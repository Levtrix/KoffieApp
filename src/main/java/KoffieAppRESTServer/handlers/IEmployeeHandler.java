package KoffieAppRESTServer.handlers;

import KoffieAppRESTServer.response.Reply;
import KoffieAppDatabase.models.Employee;

public interface IEmployeeHandler {
    Reply getEmployees();
    Reply getEmployee(int employeeId);
    Reply saveEmployee(Employee employee);
}
