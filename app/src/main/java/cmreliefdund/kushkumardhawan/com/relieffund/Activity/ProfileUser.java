package cmreliefdund.kushkumardhawan.com.relieffund.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cmreliefdund.kushkumardhawan.com.relieffund.Enum.TaskType;
import cmreliefdund.kushkumardhawan.com.relieffund.Helper.AppStatus;
import cmreliefdund.kushkumardhawan.com.relieffund.Interfaces.AsyncTaskListener;
import cmreliefdund.kushkumardhawan.com.relieffund.JsonManager.JsonParser;
import cmreliefdund.kushkumardhawan.com.relieffund.Modal.JsonResponse;
import cmreliefdund.kushkumardhawan.com.relieffund.Presentation.Custom_Dialog;
import cmreliefdund.kushkumardhawan.com.relieffund.R;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.EConstants;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.Generic_Async_Post_Activity;

public class ProfileUser extends AppCompatActivity implements AsyncTaskListener {

    private EditText Email, name;
    private TextView mobile;
    private Button back,submit;
    Custom_Dialog CD = new Custom_Dialog();

    String Token,Hash,UserId = null;
    String Mobile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        SharedPreferences settings = getSharedPreferences(EConstants.PREF_SHARED, 0); // 0 - for private mode
        Token = settings.getString(EConstants.TOKEN_LOGIN, null);
         Mobile = settings.getString(EConstants.USER_MOBILE, null);
        Hash = settings.getString(EConstants.HASH, null);
        UserId = settings.getString(EConstants.USER_ID, null);

        Email=findViewById(R.id.email);
        name=findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        back = findViewById(R.id.back);
        submit = findViewById(R.id.submit);




        mobile.setText(Mobile);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileUser.this.finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check NAme
                if(!name.getText().toString().trim().isEmpty()){

                    //Check Email
                    if(!Email.getText().toString().trim().isEmpty()) {
                        if (AppStatus.getInstance(ProfileUser.this).isOnline()) {

                            new Generic_Async_Post_Activity(ProfileUser.this, ProfileUser.this, TaskType.UPDATE_PROFILE).execute(
                                    "updateProfile",
                                    EConstants.URL + "UpdateProfile",
                                    Hash,
                                    Mobile,
                                    "M",
                                    name.getText().toString().trim(),
                                    Email.getText().toString(),
                                    Token,
                                    UserId);

                        } else CD.showDialog(ProfileUser.this, "Network isn't available");
                    }else{
                        CD.showDialog(ProfileUser.this, "Please enter email address");
                    }
                }else{
                    CD.showDialog(ProfileUser.this, "Please enter Name ");
                }
            }
        });
    }

    @Override
    public void onTaskCompleted(String result, TaskType taskType) {


        Log.e("Server Message", result);

        JsonResponse response = null;
        if (taskType == TaskType.UPDATE_PROFILE) {

            JsonParser JP = new JsonParser();
            response = JP.ParseStringUser(result);

            if(response.getSTATUS().equalsIgnoreCase("200")){
                CD.showDialog(ProfileUser.this, response.getMsg());
            }else{
                CD.showDialog(ProfileUser.this, response.getMsg());
            }


        }

    }
}
