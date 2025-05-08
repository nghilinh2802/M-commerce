package com.nghilinh.connectors;

import com.nghilinh.models.Employee;
import com.nghilinh.models.ListEmployee;

public class EmployeeConnector {
    public Employee login(String usr,String pwd)
    {
        ListEmployee le=new ListEmployee();
        le.gen_dataset();
        for (Employee emp : le.getEmployees())
        {
            if (emp.getUsername().equalsIgnoreCase(usr) && emp.getPassword().equals(pwd))
            {
                return emp;
            }
        }
        return null;
    }
}
