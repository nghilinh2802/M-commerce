package com.nghilinh.k22411csampleproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nghilinh.adapters.PaymentMethodAdapter;
import com.nghilinh.adapters.SQLiteConnector;
import com.nghilinh.models.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodActivity extends AppCompatActivity {

    ListView lvPaymentMethod;
    PaymentMethodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_method);

        // Cấu hình cho padding của hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Gọi phương thức addViews để thiết lập các view
        addViews();
    }

    private void addViews() {
        // Thiết lập ListView và Adapter
        lvPaymentMethod = findViewById(R.id.lvPaymentMethod);
        adapter = new PaymentMethodAdapter(PaymentMethodActivity.this, R.layout.item_paymentmethod);
        lvPaymentMethod.setAdapter(adapter);

        // Mở cơ sở dữ liệu SQLite
        SQLiteConnector dbHelper = new SQLiteConnector(PaymentMethodActivity.this);
        SQLiteDatabase db = dbHelper.openDatabase();  // Mở cơ sở dữ liệu

        // Lấy dữ liệu từ cơ sở dữ liệu SQLite
        List<PaymentMethod> paymentMethods = getPaymentMethodsFromDatabase(db);

        // Kiểm tra nếu có dữ liệu từ SQLite thì thêm vào adapter
        if (paymentMethods != null && !paymentMethods.isEmpty()) {
            adapter.addAll(paymentMethods);  // Thêm danh sách vào adapter
        }

        // Đảm bảo đóng cơ sở dữ liệu sau khi hoàn thành
        dbHelper.setDatabase(db);  // Đảm bảo giữ đối tượng database
        db.close();
    }

    private List<PaymentMethod> getPaymentMethodsFromDatabase(SQLiteDatabase db) {
        List<PaymentMethod> paymentMethods = new ArrayList<>();

        // Truy vấn bảng PaymentMethods để lấy dữ liệu
        Cursor cursor = db.rawQuery("SELECT Id, Name, Description FROM PaymentMethod", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0); // Lấy Id
                String name = cursor.getString(1); // Lấy Name
                String description = cursor.getString(2); // Lấy Description

                // Tạo đối tượng PaymentMethod và thêm vào danh sách
                PaymentMethod pm = new PaymentMethod(id, name, description);
                paymentMethods.add(pm);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return paymentMethods;
    }
}
