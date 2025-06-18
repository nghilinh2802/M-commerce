package com.nghilinh.k22411csampleproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nghilinh.adapters.OrdersViewerAdapter;
import com.nghilinh.adapters.SQLiteConnector;
import com.nghilinh.connectors.OrdersViewerConnector;
import com.nghilinh.models.OrdersViewer;

import java.util.ArrayList;

public class OrdersViewerActivity extends AppCompatActivity {

    ListView lvOrdersView;
    OrdersViewerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orders_viewer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvOrdersView=findViewById(R.id.lvOrdersViewer);
        adapter=new OrdersViewerAdapter(this,R.layout.item_ordersviewer);
        lvOrdersView.setAdapter(adapter);

        SQLiteConnector connector=new SQLiteConnector(this);
        OrdersViewerConnector ovc=new OrdersViewerConnector();
        ArrayList<OrdersViewer> dataset=ovc.getAllOrdersViewer(connector.openDatabase());
        adapter.addAll(dataset);
    }
}