package KoffieRESTServer.handlers;

import KoffieRESTServer.response.EmployeeJson;
import KoffieRESTServer.response.ErrorJson;
import KoffieRESTServer.response.Reply;
import KoffieRESTServer.response.Status;
import com.google.gson.Gson;
import dal.repository.EmployeeRepository;
import logging.Logger;
import models.Employee;
import models.SQLEmployee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeHandler implements IEmployeeHandler {
    private EmployeeRepository employeeRepository;
    private Gson gson;

    public EmployeeHandler(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.gson = new Gson();
    }


    @Override
    public Reply getEmployees() {
        try {
            List<Employee> employees = new ArrayList<>();
            List<EmployeeJson> employeeResponse = new ArrayList<>();

            for (SQLEmployee e : employeeRepository.findAll()) {
                employees.add(new Employee(e.getEmployeeId(), e.getFirstName(), e.getLastName()));
            }

            for (Employee e : employees) {
                employeeResponse.add(new EmployeeJson(e.getEmployeeId(), e.getFirstName(), e.getLastName()));
            }

            String json = gson.toJson(employeeResponse);
            return new Reply(Status.OK, json);
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");

            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    @Override
    public Reply getEmployee(int employeeId) {
        SQLEmployee tmp = employeeRepository.findOne(employeeId);
        Employee employee = new Employee(tmp.getEmployeeId(), tmp.getFirstName(), tmp.getFirstName());

        if (employee != null) {
            String json = gson.toJson(employee);

            return new Reply(Status.OK, json);
        }

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    @Override
    public Reply saveEmployee(Employee employee) {
        SQLEmployee saved = employeeRepository.save(new SQLEmployee(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName()));

        if (saved.getEmployeeId() == employeeRepository.findAll().size() +1) {
            return new Reply(Status.OK, gson.toJson(new Employee(saved.getEmployeeId(), saved.getFirstName(), saved.getLastName())));
        }

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }
}
