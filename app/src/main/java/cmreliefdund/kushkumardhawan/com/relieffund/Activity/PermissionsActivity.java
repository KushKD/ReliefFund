package cmreliefdund.kushkumardhawan.com.relieffund.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cmreliefdund.kushkumardhawan.com.relieffund.Presentation.Custom_Dialog;
import cmreliefdund.kushkumardhawan.com.relieffund.R;

import cmreliefdund.kushkumardhawan.com.permissions.PermissionResponse;
import cmreliefdund.kushkumardhawan.com.permissions.Permissions;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.EConstants;

public class PermissionsActivity extends AppCompatActivity implements View.OnClickListener, Permissions.OnRequestPermissionsBack{

    private static final String TAG = "MainActivity";

    private Button checkButton;
    Custom_Dialog CD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        initViews();

        ActivityCompat.requestPermissions(
                PermissionsActivity.this,
                new String[]{
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.INTERNET,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECEIVE_SMS


                        },
                2);
    }

    private void initViews() {

        checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        try {

            new Permissions.Builder(this)
                    .withPermissions(

                            Manifest.permission.CALL_PHONE,
                            Manifest.permission.SEND_SMS,
                            Manifest.permission.INTERNET,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.READ_SMS,
                            Manifest.permission.RECEIVE_SMS)
                    .requestId(1)
                    .setListener(this)
                    .check();


        } catch (Exception e) {
           // Log.e("Error",e.getLocalizedMessage());
            //Toast.makeText(this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            CD.showDialog(PermissionsActivity.this, "Something Went wrong while setting the permissions.");
        }

    }

    @Override
    public void onRequestBack(PermissionResponse RakshamResponse) {

//        SharedPreferences settings = getSharedPreferences(EConstants.PREF_SHARED, 0); // 0 - for private mode
//        SharedPreferences.Editor editor = settings.edit();
//        //Set "hasLoggedIn" to true
//        editor.putBoolean("PermissionsFlag", true);
//        editor.commit();

        Intent i = new Intent(PermissionsActivity.this, Login.class);   //Working
        // Intent i = new Intent(Permissions.this,MainActivity_Navigation_Drawer.class);
        startActivity(i);
        PermissionsActivity.this.finish();
      /*  if(RakshamResponse.isGranted(Manifest.permission.CAMERA)) {
            camera.setText("Allow");
            camera.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        }
        if(RakshamResponse.isGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            gps.setText("Allow");
            gps.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        }
        if(RakshamResponse.isGranted(Manifest.permission.CALL_PHONE)) {
            call.setText("Allow");
            call.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } if(RakshamResponse.isGranted(Manifest.permission.SEND_SMS)) {
            sms_status.setText("Allow");
            sms_status.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        }if(RakshamResponse.isGranted(Manifest.permission.INTERNET)) {
            internet_status.setText("Allow");
            internet_status.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        }if(RakshamResponse.isGranted(Manifest.permission.READ_PHONE_STATE)) {
            internet_status.setText("Allow");
            internet_status.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        }
        if(RakshamResponse.isOnNeverAskAgain(Manifest.permission.CAMERA))
            Log.d(TAG, "onRequestBack: CAMERA");*/
    }
}
