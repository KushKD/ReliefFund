package cmreliefdund.kushkumardhawan.com.relieffund.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

import cmreliefdund.kushkumardhawan.com.autoreadsms.ReliefFundOnSmsCatchListener;
import cmreliefdund.kushkumardhawan.com.autoreadsms.ReliefFundSmsVerifyCatcher;
import cmreliefdund.kushkumardhawan.com.instructions.MaterialTutorialActivity;
import cmreliefdund.kushkumardhawan.com.instructions.TutorialItem;
import cmreliefdund.kushkumardhawan.com.relieffund.Enum.TaskType;
import cmreliefdund.kushkumardhawan.com.relieffund.Helper.AppStatus;
import cmreliefdund.kushkumardhawan.com.relieffund.Helper.Security;
import cmreliefdund.kushkumardhawan.com.relieffund.Interfaces.AsyncTaskListener;
import cmreliefdund.kushkumardhawan.com.relieffund.JsonManager.JsonParser;
import cmreliefdund.kushkumardhawan.com.relieffund.Modal.JsonResponse;
import cmreliefdund.kushkumardhawan.com.relieffund.Modal.SharedPrefrencesPOJO;
import cmreliefdund.kushkumardhawan.com.relieffund.Presentation.Custom_Dialog;
import cmreliefdund.kushkumardhawan.com.relieffund.R;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.EConstants;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.Generic_Async_Post_Activity;

public class Login extends AppCompatActivity implements AsyncTaskListener {

    private ReliefFundSmsVerifyCatcher smsVerifyCatcher;
    private static final int REQUEST_CODE = 1234;

    private TextView signUpTextView;
    private EditText otp;
    String _OTP = null;
    private Button login;
    Custom_Dialog CM = new Custom_Dialog();
    String MobileNumber = null;
    String IMEI = null;
    AutoCompleteTextView phone;
    ImageView imageView1;
    private SharedPrefrencesPOJO SP = new SharedPrefrencesPOJO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        // check flag for introduvtion
        SharedPreferences settings = getSharedPreferences(EConstants.PREF_SHARED, 0);
        boolean Flag_Turotials = settings.getBoolean(EConstants.TUTORIALS_FLAG, false);

        if (!Flag_Turotials) {
            loadTutorial();
        }


        //Permissions
        // if (ContextCompat.checkSelfPermission(Login.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

        ActivityCompat.requestPermissions(
                Login.this,
                new String[]{
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.INTERNET,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_NETWORK_STATE,


                },
                2);
        // }else{
        //   Toast.makeText(Login.this, "Permission already granted", Toast.LENGTH_LONG).show();
        // }


        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        login = (Button) findViewById(R.id.login);

        phone = (AutoCompleteTextView) findViewById(R.id.phone_number);
        phone.addTextChangedListener(GET_OTP);
        otp = (EditText) findViewById(R.id.otp);
        imageView1 = (ImageView) findViewById(R.id.imageView1);

        //init ReliefFundSmsVerifyCatcher
        smsVerifyCatcher = new ReliefFundSmsVerifyCatcher(this, new ReliefFundOnSmsCatchListener<String>() {
            @Override
            public void onSmsCatch(String message) {
                //String code = parseCode(message);//Parse verification code
                String code = message.substring(message.lastIndexOf(':') + 1).trim();
                Log.e("SMS is:- ", code);
                otp.setText(code);//set code in edit text
                //then you can send verification code to server
            }
        });     // Working

        //set phone number filter if needed
        String Filter = " /^[a-zA-Z][a-zA-Z]-HPGOVT/";

        Toast.makeText(Login.this, "We are Here", Toast.LENGTH_LONG).show();
        smsVerifyCatcher.setFilter(Filter);  //working   //Need To Change


        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Set Flag to Tutorial Screen
                SharedPreferences settings = getSharedPreferences(EConstants.PREF_SHARED, 0); // 0 - for private mode
                SharedPreferences.Editor editor = settings.edit();
                //Set "hasLoggedIn" to true
                editor.putBoolean(EConstants.TUTORIALS_FLAG, true);
                editor.commit();
                Intent i = new Intent(Login.this, Registration.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Set Flag to Tutorial Screen
                SharedPreferences settings = getSharedPreferences(EConstants.PREF_SHARED, 0); // 0 - for private mode
                SharedPreferences.Editor editor = settings.edit();
                //Set "hasLoggedIn" to true
                editor.putBoolean(EConstants.TUTORIALS_FLAG, true);
                editor.commit();
                try {
                    VerifyOtp();
                } catch (Exception ex) {
                    CM.showDialog(Login.this, "Something really went bad. Please try again later.");
                }

            }
        });
    }

    public void loadTutorial() {
        Intent mainAct = new Intent(this, MaterialTutorialActivity.class);
        mainAct.putParcelableArrayListExtra(MaterialTutorialActivity.MATERIAL_TUTORIAL_ARG_TUTORIAL_ITEMS, getTutorialItems(this));
        startActivityForResult(mainAct, REQUEST_CODE);

    }

    private ArrayList<TutorialItem> getTutorialItems(Context context) {
        TutorialItem tutorialItem1 = new TutorialItem("Health", "Life and death are in the hands of God but to help the needy during illness is our moral responsibility. In my state there should not be any case where due to lack of fund the treatment of the sick person could not be done. I appeal to the citizens to donate for the cause generously so that the needy people can be helped. ",
                R.color.g_dark_blue, R.drawable.slide1);

        TutorialItem tutorialItem2 = new TutorialItem("Education", "Education Content...",
                R.color.slide_1,  R.drawable.slide2);

        TutorialItem tutorialItem3 = new TutorialItem("Disaster", "Disaster Content...",
                R.color.slide_4, R.drawable.slide3);

        TutorialItem tutorialItem4 = new TutorialItem("Needy", "Needy Content...",
                R.color.slide_3, R.drawable.slide4);

        TutorialItem tutorialItem5 = new TutorialItem("Girl Child", "Girl Child Content ....",
                R.color.slide_3, R.drawable.splash_back);

        ArrayList<TutorialItem> tutorialItems = new ArrayList<>();
        tutorialItems.add(tutorialItem1);
        tutorialItems.add(tutorialItem2);
        tutorialItems.add(tutorialItem3);
        tutorialItems.add(tutorialItem4);
        tutorialItems.add(tutorialItem5);

        return tutorialItems;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        //    super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
//            Toast.makeText(this, "All Set", Toast.LENGTH_LONG).show();
//
//        }
//    }


    private void VerifyOtp() {

        _OTP = otp.getText().toString().trim();
        String _Mobile = phone.getText().toString().trim();
        if (!_OTP.isEmpty()) {
            if (_OTP.length() == 5) {
                if (AppStatus.getInstance(Login.this).isOnline()) {


                    new Generic_Async_Post_Activity(Login.this, Login.this, TaskType.VERIFY_OTP).execute(
                            "veryfyOtp",
                            EConstants.URL + "VerifyOTP",
                            _Mobile,
                            _OTP);
                } else {
                    CM.showDialog(Login.this, "Network isn't available");

                }
            } else {
                CM.showDialog(Login.this, "Please Enter a valid OTP");
            }
        } else {
            CM.showDialog(Login.this, "OTP Cannot be empty.");
        }

    }

    private final TextWatcher GET_OTP = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // textView.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            if (s.length() == 10 && Integer.parseInt(s.toString().substring(0, 1)) > 6) {

                MobileNumber();
            } else {
                // aadhaar_et.setBackgroundResource(R.drawable.rounded_edittext);
                // CM.showDialog(Login.this,"Please Enter a valid Phone Number.");
                Log.e("Aadhaar ", "Not Valid");
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        smsVerifyCatcher.onStart();
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        smsVerifyCatcher.onStop();
//    }


    private void MobileNumber() {
        MobileNumber = phone.getText().toString().trim();
        if (!MobileNumber.isEmpty()) {
            if (MobileNumber.length() == 10) {

                if (AppStatus.getInstance(Login.this).isOnline()) {





                    // new Generic_Async_Post(Login.this, Login.this, TaskType.LOGIN).execute(url);

                    new Generic_Async_Post_Activity(Login.this, Login.this, TaskType.LOGIN).execute(
                            "getOtp",
                            EConstants.URL + "doLogin",
                            MobileNumber);


                } else CM.showDialog(Login.this, "Network isn't available");
            } else CM.showDialog(Login.this, "Please Enter a valid Mobile Number");
        } else CM.showDialog(Login.this, "Mobile Number cannot be empty.");
    }


    @Override
    public void onTaskCompleted(String result, TaskType taskType) {

        Log.e("Server Message", result);

        JsonResponse response = null;
        if (taskType == TaskType.LOGIN) {
             CM.showDialog(Login.this, result);
            JsonParser JP = new JsonParser();
            response = JP.ParseString(result);
            if (response.getRESPONSE().length() != 0) {
                SP.setMobile_number(phone.getText().toString().trim());
                CM.showDialog(Login.this, response.getRESPONSE());

            } else {
                CM.showDialog(Login.this, response.getRESPONSE());
            }

        } else if (taskType == TaskType.VERIFY_OTP) {
            // String finalResult = null;
            //CM.showDialog(Login.this, result);
            Log.e("Result",result);
            JsonParser JP = new JsonParser();
            response = JP.ParseStringToken(result);
            if (response.getSTATUS().equalsIgnoreCase("200")) {
               // CM.showDialog(Login.this, response.getRESPONSE());

                try {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_LONG).show();

                    SP.setToken(response.getToken());
                    SP.setUid(response.getUid());


                    SP.setImei(AppStatus.getUniqueIMEIId(Login.this));
                    //Save Token to SharedPrfrences
                    saveDataSharedPref(SP);


                } catch (Exception e) {
                    CM.showDialog(Login.this, "Data Isn't Saved");
                }
            } else {
                CM.showDialog(Login.this, response.getRESPONSE());
            }
        } else {
            CM.showDialog(Login.this, "Something is not Good");
        }

    }


    private void saveDataSharedPref(SharedPrefrencesPOJO PGP) {


        /**
         *
         *  String mobileNumber_Md5 = Security.md5(mobileNumber.trim());
         *             String mobileNumber_Md5_Sha1_Key  = null;
         *             try {
         *                 mobileNumber_Md5_Sha1_Key = Security.sha1Hash(mobileNumber_Md5, EConstants.API_HASH_KEY);
         *             } catch (Exception e) {
         *                 e.printStackTrace();
         *             }
         */


        try {
            // User has successfully logged in, save this information
            //  We need an Editor object to make preference changes.
            SharedPreferences settings = getSharedPreferences(EConstants.PREF_SHARED, 0); // 0 - for private mode
            SharedPreferences.Editor editor = settings.edit();
            //Set "hasLoggedIn" to true





            editor.putBoolean(EConstants.LOG_IN, true);
            editor.putString(EConstants.TOKEN_LOGIN, PGP.getToken());
            editor.putString(EConstants.USER_MOBILE, PGP.getMobile_number());
            editor.putString(EConstants.MOBILE_IMEI, PGP.getImei());
            editor.putString(EConstants.USER_ID,PGP.getUid());
            editor.putString(EConstants.HASH,Security.sha1Hash(PGP.getMobile_number(), EConstants.API_HASH_KEY));





            // Commit the edits!
            editor.commit();

            Log.d("Shared PrefRences Data" , settings.toString());


            Toast.makeText(Login.this, "Data Saved Successfully", Toast.LENGTH_LONG).show();
            Intent i = new Intent(Login.this, MainActivity.class);
            startActivity(i);
            Login.this.finish();

        } catch (Exception e) {

            CM.showDialog(Login.this, "Something went wrong. Please restart the application. ");
        }
    }

}


