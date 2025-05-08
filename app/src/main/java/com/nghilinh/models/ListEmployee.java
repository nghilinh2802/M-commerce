package com.nghilinh.models;

import java.util.ArrayList;

public class ListEmployee {
    private ArrayList<Employee> employees;

    public ListEmployee() {
        employees=new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
    public void  gen_dataset()
    {
        Employee e1=new Employee();
        e1.setId(1);
        e1.setName("John");
        e1.setEmail("John@gmail.com");
        e1.setPhone("0987654321");
        e1.setUsername("john");
        e1.setPassword("123");
        employees.add(e1);

        Employee e2=new Employee();
        e2.setId(2);
        e2.setName("Alice");
            e2.setEmail("Alice@gmail.com");
        e2.setPhone("0999554321");
        e2.setUsername("alice");
        e2.setPassword("1234");
        employees.add(e2);

        Employee e3=new Employee();
        e3.setId(3);
        e3.setName("Luna");
        e3.setEmail("Luna@gmail.com");
        e3.setPhone("0944526271");
        e3.setUsername("luna");
        e3.setPassword("123456");
        employees.add(e3);
    }
}
