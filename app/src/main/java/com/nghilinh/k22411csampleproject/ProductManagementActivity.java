package com.nghilinh.k22411csampleproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.nghilinh.connectors.ProductConnector;
import com.nghilinh.models.Product;

import java.util.List;

public class ProductManagementActivity extends AppCompatActivity {

    ListView lvProduct;
    ArrayAdapter<Product> adapter;
    ProductConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);

        addViews();
        addEvents();
    }

    private void addViews() {
        lvProduct = findViewById(R.id.lvProduct);

        adapter = new ArrayAdapter<>(ProductManagementActivity.this, android.R.layout.simple_list_item_1);
        connector = new ProductConnector();

        lvProduct.setAdapter(adapter);
    }

    private void addEvents() {
        List<Product> products = connector.get_all_products();
        adapter.addAll(products);
    }

    // Optionally, handle long clicks to remove products
//    private void addLongClickListener() {
//        lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Product selectedProduct = adapter.getItem(position);
//                adapter.remove(selectedProduct);
//                return false;
//            }
//        });
//    }
}
