package com.nghilinh.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nghilinh.models.Customer;
import com.nghilinh.models.ListCustomer;
import com.nghilinh.models.OrdersViewer;

import java.util.ArrayList;

public class OrdersViewerConnector {
    public ArrayList<OrdersViewer>getAllOrdersViewer(SQLiteDatabase database)
    {
      ArrayList<OrdersViewer> datasets=new ArrayList<>();

      StringBuilder builder=new StringBuilder();

      builder.append(" SELECT ");
      builder.append(" o.Id AS OrderId,  ");
      builder.append(" o.Code AS OrderCode,  ");
      builder.append(" o.OrderDate,  ");
      builder.append(" e.Name AS EmployeeName,  ");
      builder.append(" c.Name AS CustomerName,  ");
      builder.append(" SUM((od.Quantity * od.Price - od.Discount * od.Quantity * od.Price) * (1 + od.VAT)) AS TotalOrderValue  ");

      String sql=builder.toString();
      Cursor cursor = database.rawQuery(sql, null);
      while(cursor.moveToNext()) {
          int id = cursor.getInt(0);
          String code = cursor.getString(1);
          String orderdate = cursor.getString(2);
          String employeeName = cursor.getString(3);
          String customerName = cursor.getString(4);
          double totalvalue = cursor.getDouble(5);

          OrdersViewer ov=new OrdersViewer();
          ov.setId(id);
          ov.setCode(code);
          ov.setOrderDate(orderdate);
          ov.setEmployeeName(employeeName);
          ov.setCustomerName(customerName);
          ov.setTotalOrderValue(totalvalue);
          datasets.add(ov);
            }
            cursor.close();
            return datasets;
        }
}
