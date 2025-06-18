package com.nghilinh.k22411csampleproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nghilinh.adapters.SQLiteConnector;
import com.nghilinh.connectors.EmployeeConnector;
import com.nghilinh.models.Employee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName;
    EditText edtPassword;
    CheckBox chkSaveLogin;
    TextView txtNetworkType;


    String DATABASE_NAME="Sales.Database.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;

    BroadcastReceiver networkReceiver=null;

    Button btnLogin;


    private static final int DOUBLE_BACK_PRESS_INTERVAL = 500; // 0.5 seconds
    private boolean isBackPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        addViews();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.edt_customer_id), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        processCopy();

        setupBroadcastReceiver();
    }

    private void setupBroadcastReceiver() {
        networkReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
//                chút nua se tu dong nhay vao day khi internet thay doi trang thai
//
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                // Check if the network is connected
                if (networkInfo != null && networkInfo.isConnected()) {
                    if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                        // Set background color to green for Wi-Fi in the TextView
                        findViewById(R.id.txtNetworkType).setBackgroundColor(Color.parseColor("#4CAF50")); // Green (Wi-Fi)
                    } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                        // Check if mobile data is 4G or 3G and set color accordingly
                        if (networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE) {
                            // Set background color to yellow for 4G in the TextView
                            findViewById(R.id.txtNetworkType).setBackgroundColor(Color.parseColor("#FFEB3B")); // Yellow (4G)
                        } else {
                            // Set background color to blue for other mobile data types in the TextView
                            findViewById(R.id.txtNetworkType).setBackgroundColor(Color.parseColor("#2196F3")); // Blue (Mobile Data)
                        }
                    }
                } else {
                    // Set background color to red for no internet connection in the TextView
                    findViewById(R.id.txtNetworkType).setBackgroundColor(Color.parseColor("#F44336")); // Red (No internet)
                }
            }
        };
//            if(networkInfo != null && networkInfo.isConnected())
//                {
//                    btnLogin.setVisibility(View.VISIBLE);
//                }
//            else
//                {
//                    btnLogin.setVisibility(View.INVISIBLE);
//                }
//            }
//        };
    }

    private void addViews() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        chkSaveLogin = findViewById(R.id.chkSaveLoginInfor);
        btnLogin = findViewById(R.id.btnLogin);
    }

    public void do_login(View view) {
        String usr = edtUserName.getText().toString();
        String pwd = edtPassword.getText().toString();
        EmployeeConnector ec = new EmployeeConnector();

        SQLiteConnector sqLiteConnector=new SQLiteConnector(this);
        sqLiteConnector.openDatabase();

//        Employee emp = ec.login(sqLiteConnector.getDatabase(), usr, pwd);
        Employee emp = ec.login(new SQLiteConnector(this).openDatabase(), usr, pwd);
        if (emp != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this,
                    "Login failed - please check your account again!",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isBackPressedOnce) {
            // If back is pressed twice within 0.5 seconds, show the exit confirmation dialog
            showExitDialog();
        } else {
            isBackPressedOnce = true;
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> isBackPressedOnce = false, DOUBLE_BACK_PRESS_INTERVAL);
        }
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        Resources res = getResources();
        // Set the title
        builder.setTitle(res.getText(R.string.confirm_exit_title));
        builder.setMessage(res.getText(R.string.confirm_exit_message));
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        // Set up YES interaction
        builder.setPositiveButton(res.getText(R.string.confirm_exit_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();  // Close the activity when the user confirms exit
            }
        });

        // Set up NO interaction
        builder.setNegativeButton(res.getText(R.string.confirm_exit_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();  // Cancel the dialog if the user chooses "NO"
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);  // Prevent closing the dialog when tapping outside
        dialog.show();
    }

    public void do_exit(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        Resources res = getResources();
        // Set the title
        builder.setTitle(res.getText(R.string.confirm_exit_title));
        builder.setMessage(res.getText(R.string.confirm_exit_message));
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        // Set up YES interaction
        builder.setPositiveButton(res.getText(R.string.confirm_exit_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();  // Close the activity when the user confirms exit
            }
        });

        // Set up NO interaction
        builder.setNegativeButton(res.getText(R.string.confirm_exit_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();  // Cancel the dialog if the user chooses "NO"
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);  // Prevent closing the dialog when tapping outside
        dialog.show();
    }

    public void saveLoginInformation()
    {
        SharedPreferences preferences=getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        String usr = edtUserName.getText().toString();
        String pwd = edtPassword.getText().toString();
        boolean isSave=chkSaveLogin.isChecked();
        editor.putString("USERNAME", usr);
        editor.putString("PASSWORD", pwd);
        editor.putBoolean("SAVED",isSave);
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveLoginInformation();

        if (networkReceiver!=null)
        {
            unregisterReceiver(networkReceiver);
        }
    }

    public void restoreLoginInformation()
    {
        SharedPreferences preferences=getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        String usr=preferences.getString("USERNAME", "");
        String pwd=preferences.getString("PASSWORD","");
        boolean isSave=preferences.getBoolean("SAVED", true);
        if (isSave)
        {
            edtUserName.setText(usr);
            edtPassword.setText(pwd);
            chkSaveLogin.setChecked(isSave);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreLoginInformation();

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
    }

    private void processCopy() {
        //private app
        File dbFile = getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists())
        {
            try
            {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder", Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }

    public void CopyDataBaseFromAsset()
    {
        try {
            InputStream myInput;

            myInput = getAssets().open(DATABASE_NAME);


            // Path to the just created empty db
            String outFileName = getDatabasePath();

            // if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
//             TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
