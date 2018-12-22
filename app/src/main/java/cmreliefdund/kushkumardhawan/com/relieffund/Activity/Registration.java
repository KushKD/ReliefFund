package cmreliefdund.kushkumardhawan.com.relieffund.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cmreliefdund.kushkumardhawan.com.relieffund.Enum.TaskType;
import cmreliefdund.kushkumardhawan.com.relieffund.Helper.AppStatus;
import cmreliefdund.kushkumardhawan.com.relieffund.Interfaces.AsyncTaskListener;
import cmreliefdund.kushkumardhawan.com.relieffund.Modal.UserDetails;
import cmreliefdund.kushkumardhawan.com.relieffund.Presentation.Custom_Dialog;
import cmreliefdund.kushkumardhawan.com.relieffund.R;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.EConstants;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.Generic_Async_Post_Activity;

public class Registration extends AppCompatActivity implements AsyncTaskListener {

    EditText  etmobile_et, etname_et, email_et, etlastname_et;

    Button back, register;
    Custom_Dialog CD = new Custom_Dialog();
    UserDetails userDetails = new UserDetails();





    public static int flag = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // OTP_Server_et = (EditText)findViewById(R.id.otp_server);
        etmobile_et = (EditText) findViewById(R.id.etmobile);
        etname_et = (EditText) findViewById(R.id.etname);
        etlastname_et = (EditText) findViewById(R.id.etlastname);
        email_et = (EditText) findViewById(R.id.email_server);





        back = (Button) findViewById(R.id.back);
        register = (Button) findViewById(R.id.register);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration.this.finish();
            }
        });







        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String PhoneNumber_Service = etmobile_et.getText().toString().trim();
                String Name_Service = etname_et.getText().toString().trim();
                String Last_Name_Service = etlastname_et.getText().toString().trim();
                String Email_Service = email_et.getText().toString().trim();
                //  String IMEI_SERVER = AppStatus.GetIMEI(Registration.this);


                if (Name_Service.length() != 0 && Name_Service != null) {
                    if (Name_Service.length() != 0 && Name_Service != null) {
                        if (PhoneNumber_Service.length() == 10 && Integer.parseInt(PhoneNumber_Service.substring(0, 1)) > 6) {


                            if (AppStatus.getInstance(Registration.this).isOnline()) {

                                try {

                                     encodeImagetoString(Name_Service,Last_Name_Service, PhoneNumber_Service, Email_Service);
                                } catch (Exception ex) {
                                    Log.e("Image Error", ex.getLocalizedMessage().toString());
                                }

                            } else {
                                CD.showDialog(Registration.this, "Please Connect to Internet");
                            }


                        } else {
                            CD.showDialog(Registration.this, "Please enter a valid 10 digit Mobile number");
                        }


                    } else {
                        CD.showDialog(Registration.this, "Please enter your Last Name.");
                    }


                } else {
                    CD.showDialog(Registration.this, "Please enter your First Name.");
                }
            }
            });


    }







    @Override
    public void onTaskCompleted(String result, TaskType taskType) {

        if (taskType == TaskType.REGISTRATION) {
            Log.e("Getting Result", result);
            CD.showDialog(Registration.this, result);

//            if (result.equalsIgnoreCase("Timeout")) {
//                CD.showDialog(Registration.this, result);
//            } else {
//                String Result_to_Show = null;
//               // Result_to_Show = JsonParser.Registration_Parse(result);
//
//                CD.showDialog(Registration.this, Result_to_Show);
//                Registration.this.finish();
//
//            }
        } else {
            CD.showDialog(Registration.this, "Something went wrong. Please try again.");
        }

    }







    // AsyncTask - To convert Image to String
    public void encodeImagetoString(String Name_Service, String Last_Name_Service,  String PhoneNumber_Service,  String Email_Service) {


        userDetails.setName(Name_Service);
        userDetails.setLastName(Last_Name_Service);
        userDetails.setMobile(PhoneNumber_Service);
        userDetails.setEmail(Email_Service);

        triggerImageUpload(userDetails);

    }

    public void triggerImageUpload(UserDetails user) {

        try {

            new Generic_Async_Post_Activity(Registration.this, Registration.this, TaskType.REGISTRATION).execute(
                    "getUserRegistration_JSON",
                    EConstants.URL + "getUserRegistration_JSON",
                    user.getName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getMobile() );

        } catch (Exception ex) {
            CD.showDialog(Registration.this, ex.getLocalizedMessage().toString());
        }
    }


}
