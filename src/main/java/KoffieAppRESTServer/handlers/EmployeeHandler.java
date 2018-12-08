package KoffieAppRESTServer.handlers;

import KoffieAppRESTServer.response.EmployeeJson;
import KoffieAppRESTServer.response.ErrorJson;
import KoffieAppRESTServer.response.Reply;
import KoffieAppRESTServer.response.Status;
import com.google.gson.Gson;
import KoffieAppDal.repository.EmployeeRepository;
import logging.Logger;
import models.Employee;

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
            //addEmployees();
            List<EmployeeJson> employeeResponse = new ArrayList<>();

            for (Employee e : employeeRepository.findAll()) {
                employeeResponse.add(new EmployeeJson(e.getId(), e.getFirstName(), e.getLastName()));
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
        Employee employee = employeeRepository.findOne(employeeId);

        if (employee != null) {
            String json = gson.toJson(employee);

            return new Reply(Status.OK, json);
        }

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    @Override
    public Reply saveEmployee(Employee employee) {
        Employee saved = employeeRepository.save(employee);

        if (saved.getId() == employee.getId()) {
            return new Reply(Status.OK, gson.toJson(saved));
        }

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    private void addEmployees() {
        Employee employee1 = new Employee("Henk", "Ruijter");
        Employee employee2 = new Employee("Sanne", "Pell");

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
    }
}
