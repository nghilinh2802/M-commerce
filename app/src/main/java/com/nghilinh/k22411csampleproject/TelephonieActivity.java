//package com.nghilinh.k22411csampleproject;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.ContactsContract;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.nghilinh.adapters.TelephonieInforAdapter;
//import com.nghilinh.models.TelephonieInfor;
//
//public class TelephonieActivity extends AppCompatActivity {
//
//    ListView lvTelephonie;
////    ArrayAdapter<TelephonieInfor> adapter;
//
//    TelephonieInforAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_telephonie);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//        addViews();
//        addEvents();
//        getAllContacts();
//    }
//
//    private void addEvents() {
//        lvTelephonie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                TelephonieInfor ti=adapter.getItem(i);
//                makeAPhoneCall(ti);
//            }
//        });
//    }
//
//    private void makeAPhoneCall(TelephonieInfor ti) {
//        Uri uri=Uri.parse("tel: "+ti.getPhone());
//        Intent intent=new Intent(Intent.ACTION_CALL);
//        intent.setData(uri);
//        startActivity(intent);
//    }
//
//    private void addViews()
//    {
//        lvTelephonie=findViewById(R.id.lvTelephonieInfor);
////        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
//
//        adapter=new TelephonieInforAdapter(this,R.layout.item_telephonie_infor);
//
//        lvTelephonie.setAdapter(adapter);
//    }
//
//    public void directCall(TelephonieInfor ti)
//    {
//        Uri uri=Uri.parse("tel:"+ti.getPhone());
//        Intent intent=new Intent(Intent.ACTION_CALL);
//        intent.setData(uri);
//        startActivity(intent);
//    }
//
//    public void dialupCall(TelephonieInfor ti)
//    {
//        Uri uri=Uri.parse("tel:"+ti.getPhone());
//        Intent intent=new Intent(Intent.ACTION_DIAL);
//        intent.setData(uri);
//        startActivity(intent);
//    }
//    private void getAllContacts()
//        {
//            Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//            Cursor cursor = getContentResolver().query(uri,null, null, null, null);
//
//            adapter.clear();
//
//            while (cursor.moveToNext()){
//                int nameIndex =cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
//                String name = cursor.getString(nameIndex); //Get Name
//                int phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds. Phone.NUMBER);
//                String phone = cursor.getString(phoneIndex); //Get Phone Number
//                //Todo something …
//
//                TelephonieInfor ti=new TelephonieInfor();
//                ti.setName(name);
//                ti.setPhone(phone);
//                adapter.add(ti);
//            }
//            cursor.close();
//        }
//}


package com.nghilinh.k22411csampleproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nghilinh.adapters.TelephonieInforAdapter;
import com.nghilinh.models.TelephonieInfor;

import java.util.ArrayList;

public class TelephonieActivity extends AppCompatActivity {

    ListView lvTelephonie;
    TelephonieInforAdapter adapter;
    Spinner spNetworkFilter;
    ArrayList<TelephonieInfor> allContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephonie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        allContacts = new ArrayList<>();
        addViews();
        addEvents();
        getAllContacts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_telephonie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_viettel) {
            filterContacts("Viettel");
            return true;
        } else if (id == R.id.menu_mobifone) {
            filterContacts("Mobifone");
            return true;
        } else if (id == R.id.menu_other) {
            filterContacts("Other");
            return true;
        } else if (id == R.id.menu_all) {
            filterContacts("All");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addEvents() {
        lvTelephonie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TelephonieInfor ti = adapter.getItem(i);
                makeAPhoneCall(ti);
            }
        });

        spNetworkFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String filter = spNetworkFilter.getSelectedItem().toString();
                filterContacts(filter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                filterContacts("All");
            }
        });
    }

    private void makeAPhoneCall(TelephonieInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    private void addViews() {
        lvTelephonie = findViewById(R.id.lvTelephonieInfor);
        spNetworkFilter = findViewById(R.id.spNetworkFilter);

        adapter = new TelephonieInforAdapter(this, R.layout.item_telephonie_infor);
        lvTelephonie.setAdapter(adapter);

        // Thiết lập spinner
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.network_filters,
                android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNetworkFilter.setAdapter(spinnerAdapter);
    }

    public void directCall(TelephonieInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    public void dialupCall(TelephonieInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }

    private void getAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        adapter.clear();
        allContacts.clear();

        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            String name = cursor.getString(nameIndex);
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String phone = cursor.getString(phoneIndex);

            TelephonieInfor ti = new TelephonieInfor();
            ti.setName(name);
            ti.setPhone(phone);
            allContacts.add(ti);
            adapter.add(ti);
        }
        cursor.close();
    }

    private void filterContacts(String network) {
        adapter.clear();
        for (TelephonieInfor ti : allContacts) {
            if (network.equals("All") || ti.getNetworkProvider().equals(network)) {
                adapter.add(ti);
            }
        }
        adapter.notify();
    }
}
