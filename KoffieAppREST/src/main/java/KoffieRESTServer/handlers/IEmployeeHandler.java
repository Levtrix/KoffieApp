package KoffieRESTServer.handlers;

import KoffieRESTServer.response.Reply;
import models.Employee;

public interface IEmployeeHandler {
    Reply getEmployees();
    Reply getEmployee(int employeeId);
    Reply saveEmployee(Employee employee);
}
