package com.nghilinh.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nghilinh.models.Category;
import com.nghilinh.models.ListCategory;
import com.nghilinh.models.Product;


public class ProductManagementActivity extends AppCompatActivity {

    Spinner spinnerCategory;
    ArrayAdapter<Category>adapterCategory;
    ListCategory listCategory;
    ListView lvProduct;
    ArrayAdapter<Product>adapterProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.edt_customer_id), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category c=adapterCategory.getItem(i);
                displayProductByCategories(c);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = adapterProduct.getItem(position);
                if (selectedProduct != null)
                {
                    Intent intent = new Intent(ProductManagementActivity.this, ProductDetailActivity.class);
                    intent.putExtra("SELECTED_PRODUCT", selectedProduct);
                    startActivity(intent);
                }
            }
        });
    }

    private void displayProductByCategories(Category c) {
//        xoa du lieu cu trong listview di
        adapterProduct.clear();
//        nap moi lai du lieu
        adapterProduct.addAll(c.getProducts());
    }

    private void addViews() {
        spinnerCategory =findViewById(R.id.spinnerCategory);
        adapterCategory=new ArrayAdapter<>(
                ProductManagementActivity.this, android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);

        listCategory=new ListCategory();
        listCategory.generate_simple_product_dataset();
        adapterCategory.addAll(listCategory.getCategories());

        lvProduct=findViewById(R.id.lvProduct);
        adapterProduct=new ArrayAdapter<>(
                ProductManagementActivity.this, android.R.layout.simple_list_item_1);
        lvProduct.setAdapter(adapterProduct);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_product,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_new_product) {
            Toast.makeText(ProductManagementActivity.this, "Mở màn hình thêm mới sản phẩm", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_refresh_product) {
            Toast.makeText(ProductManagementActivity.this, "Làm mới danh sách sản phẩm", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_search_product) {
            Toast.makeText(ProductManagementActivity.this, "Tìm kiếm sản phẩm", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_filter_product) {
            Toast.makeText(ProductManagementActivity.this, "Lọc sản phẩm theo danh mục", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_compare_product) {
            Toast.makeText(ProductManagementActivity.this, "So sánh hai hoặc nhiều sản phẩm", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_export_product) {
            Toast.makeText(ProductManagementActivity.this, "Xuất danh sách sản phẩm ra file", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_help_product) {
            Toast.makeText(ProductManagementActivity.this, "Mở hướng dẫn sử dụng ứng dụng", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
