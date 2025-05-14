package com.nghilinh.connectors;

import com.nghilinh.models.Customer;
import com.nghilinh.models.ListCustomer;

import java.util.ArrayList;
import java.util.List;

public class CustomerConnector {
    ListCustomer listCustomer;
    public CustomerConnector()
    {
        listCustomer=new ListCustomer();
        listCustomer.generate_sample_dataset();
    }
    public ArrayList<Customer> get_all_customers()
    {
        if (listCustomer==null)
        {
            listCustomer=new ListCustomer();
            listCustomer.generate_sample_dataset();
        }
        return listCustomer.getCustomers();
    }
    public ArrayList<Customer> get_customers_by_provider(String provider)
    {
        if (listCustomer==null)
        {
            listCustomer=new ListCustomer();
            listCustomer.generate_sample_dataset();
        }
        ArrayList<Customer>result=new ArrayList<>();
        for(Customer c : listCustomer.getCustomers())
        {
            if (c.getPhone().startsWith(provider))
            {
                result.add(c);
            }
        }
        return result;
    }
}
