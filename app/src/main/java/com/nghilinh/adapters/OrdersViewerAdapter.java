package com.nghilinh.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nghilinh.k22411csampleproject.R;
import com.nghilinh.models.OrdersViewer;

public class OrdersViewerAdapter extends ArrayAdapter<OrdersViewer>
{
    Activity context;
    int resource;
    public OrdersViewerAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View item=inflater.inflate(this.resource,null);

        TextView txtOrderCode = item.findViewById(R.id.txtOrderCode);
        TextView txtOrderDate = item.findViewById(R.id.txtOrderDate);
        TextView txtEmployeeName = item.findViewById(R.id.txtEmployeeName);
        TextView txtCustomerName = item.findViewById(R.id.txtCustomerName);
        TextView txtTotalOrderValue = item.findViewById(R.id.txtTotalOrderValue);

        OrdersViewer ov=getItem(position);
        txtOrderCode.setText(ov.getCode());
        txtOrderDate.setText(ov.getOrderDate());
        txtEmployeeName.setText(ov.getEmployeeName());
        txtCustomerName.setText(ov.getCustomerName());
        txtTotalOrderValue.setText(ov.getTotalOrderValue() + " VNĐ");

        return item;
    }
}
