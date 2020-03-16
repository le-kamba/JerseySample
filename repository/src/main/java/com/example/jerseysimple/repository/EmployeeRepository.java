package com.example.jerseysimple.repository;


import com.example.jerseysimple.repository.exceptions.DuplicateIdException;
import com.example.jerseysimple.repository.exceptions.EmployeeNameNotFoundException;
import com.example.jerseysimple.repository.exceptions.EmployeeNotFoundException;
import com.example.jerseysimple.repository.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private static EmployeeRepository instance = new EmployeeRepository();

    private List<Employee> employeeList;

    private EmployeeRepository() {
        employeeList = new ArrayList<>();
        employeeList.add(new Employee(3, "Cupcake"));
        employeeList.add(new Employee(4, "Donuts"));
        employeeList.add(new Employee(5, "Eclair"));
        employeeList.add(new Employee(8, "Froyo"));
        employeeList.add(new Employee(9, "Gingerbread"));
    }

    static public EmployeeRepository getInstance(){
        return instance;
    }

    public List<Employee> selectAll() {
        return employeeList;
    }

    public Employee select(int id){
        for (Employee employee : employeeList) {
            if(employee.getId()==id){
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public synchronized void insert(int id, String firstName){
        try{
            select(id);
        }catch (EmployeeNotFoundException e){
            // いなければ追加できる
            employeeList.add(new Employee(id, firstName));
            return;
        }
        // 同じIDが存在したら追加できない
        throw new DuplicateIdException();
    }

    public synchronized void update(int id, String firstName){
        Employee employee = select(id);
        employee.setFirstName(firstName);
    }

    public synchronized void delete(int id){
        Employee employee = select(id);
        employeeList.remove(employee);
    }

    public List<Employee> search(String name) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employeeList) {
            if(employee.getFirstName().contains(name)){
                list.add(employee);
            }
        }
        if(list.size()>0) return list;
        throw new EmployeeNameNotFoundException(name);
    }
}
