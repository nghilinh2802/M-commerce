package com.nghilinh.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nghilinh.k22411csampleproject.R;
import com.nghilinh.models.Product;

public class ProductAdapter extends ArrayAdapter<Product> {

    Activity context;
    int resource;
    public ProductAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
//        nhan ban giao dien theo tung vi tri position ma du lieu duoc duyet qua
        View item=inflater.inflate(this.resource,null);
//        luc nay, toan bo view nam trong layout resource (item_advanced_product) se duoc mo hinh hoa doi tuong va duoc quan ly boi bien item tuc la tong tai view
//        muon truy xuat toi cac view con trong no can phai thong qua cac bien item
        ImageView imgProduct=item.findViewById(R.id.imgProduct);
        TextView txtProductID=item.findViewById(R.id.txtProductID);
        TextView txtProductName=item.findViewById(R.id.txtProductName);
        TextView txtProductQuantity=item.findViewById(R.id.txtProductQuantity);
        TextView txtProductPrice=item.findViewById(R.id.txtProductPrice);
        ImageView imgCart=item.findViewById(R.id.imgCart);

//        lay mo hinh doi tuong dang duoc nhan ban o vi tri position (doi so 1)
        Product p=getItem(position);
//        rai du lieu cua product len giao dien trong item
        imgProduct.setImageResource(p.getImg_id());
        txtProductID.setText(p.getId()+"");
        txtProductName.setText(p.getName());
        txtProductQuantity.setText(p.getQuantity()+"");
        txtProductPrice.setText(p.getPrice()+"");

        return item;
    }
}
