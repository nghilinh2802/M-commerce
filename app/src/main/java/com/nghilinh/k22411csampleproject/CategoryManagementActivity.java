package com.nghilinh.k22411csampleproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.nghilinh.connectors.CategoryConnector;
import com.nghilinh.models.Category;

import java.util.List;

public class CategoryManagementActivity extends AppCompatActivity {

    ListView lvCategory;
    ArrayAdapter<Category> adapter;
    CategoryConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);

        addViews();
        addEvents();
    }

    private void addViews() {
        lvCategory = findViewById(R.id.lvCategory);

        // Initialize the adapter and connector
        adapter = new ArrayAdapter<>(CategoryManagementActivity.this, android.R.layout.simple_list_item_1);
        connector = new CategoryConnector();

        // Set the adapter for the ListView
        lvCategory.setAdapter(adapter);
    }

    private void addEvents() {
        List<Category> categories = connector.get_all_categories();
        adapter.addAll(categories);  // Add categories to the ListView
    }

    // Optionally, handle long clicks to remove categories
//    private void addLongClickListener() {
//        lvCategory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Category selectedCategory = adapter.getItem(position);
//                adapter.remove(selectedCategory);
//                return false;
//            }
//        });
    }

