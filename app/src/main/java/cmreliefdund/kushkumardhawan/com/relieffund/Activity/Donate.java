package cmreliefdund.kushkumardhawan.com.relieffund.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cmreliefdund.kushkumardhawan.com.relieffund.Helper.AppStatus;
import cmreliefdund.kushkumardhawan.com.relieffund.Modal.UserDetails;
import cmreliefdund.kushkumardhawan.com.relieffund.Presentation.CircularTextView;
import cmreliefdund.kushkumardhawan.com.relieffund.Presentation.Custom_Dialog;
import cmreliefdund.kushkumardhawan.com.relieffund.R;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.EConstants;
import cmreliefdund.kushkumardhawan.com.ruleview.MoneySelectRuleView;

public class Donate extends AppCompatActivity {



    private MoneySelectRuleView msrvMoney;
    private CircularTextView tvMoney;
    private EditText etMoney, mobile ,email ,name;
    private Button submit , back;


    private float moneyBalance;
    private boolean isMoneySloped;
    Custom_Dialog CD = new Custom_Dialog();
    String Token = null;
    String Hash = null;
    String UserId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences settings = getSharedPreferences(EConstants.PREF_SHARED, 0); // 0 - for private mode
         Token = settings.getString(EConstants.TOKEN_LOGIN, null);
        String Mobile = settings.getString(EConstants.USER_MOBILE, null);
         Hash = settings.getString(EConstants.HASH, null);
        UserId = settings.getString(EConstants.USER_ID, null);


        //Toast.makeText(Donate.this, lanSettings, Toast.LENGTH_LONG).show();

        tvMoney = findViewById(R.id.tv_money);
        etMoney = findViewById(R.id.et_new_money);
        mobile = findViewById(R.id.mobile);
        msrvMoney = findViewById(R.id.msrv_money);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        submit = findViewById(R.id.submit);
        back = findViewById(R.id.back);

        tvMoney.setStrokeWidth(5);
        tvMoney.setStrokeColor("#F2F2F2");
        tvMoney.setSolidColor("#90DFAA");

        mobile.setText(Mobile);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Check NAme
                if(!name.getText().toString().trim().isEmpty()){

                    //Check Email
                    if(!email.getText().toString().trim().isEmpty()){

                        //Check Mobile
                        if(!mobile.getText().toString().trim().isEmpty()){

                            //Check Money
                            if(!etMoney.getText().toString().trim().isEmpty()){


                                if(AppStatus.getInstance(Donate.this).isOnline()){
                                    Intent intent = new Intent(Donate.this, WebViewActivity.class);
                                    intent.putExtra("URL",getUrl());
                                    startActivity(intent);
                                }else{
                                    CD.showDialog(Donate.this,"Please connect to Internet before making a donation.");
                                }

                            }else{
                                CD.showDialog(Donate.this,"Please Enter the amount of money to Donate !");
                            }

                        }else{
                            CD.showDialog(Donate.this,"Please Enter vaild Mobile  number !");
                        }
                    }else{
                        CD.showDialog(Donate.this,"Please Enter vaild email !");
                    }

                }else{
                    CD.showDialog(Donate.this,"Please Enter Name !");
                }






            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Donate.this.finish();
            }
        });


        tvMoney.setText(Integer.toString(msrvMoney.getValue()));
        moneyBalance = msrvMoney.getBalance();
        msrvMoney.setOnValueChangedListener(new MoneySelectRuleView.OnValueChangedListener() {
            @Override
            public void onValueChanged(int newValue) {
                tvMoney.setText(Integer.toString(newValue));
                etMoney.setText(Integer.toString(newValue));


            }
        });




    }

    private String getUrl(){
        StringBuilder SB = new StringBuilder();
        SB.append("http://mining.eypoc.com/payment/Cmrahatkosh/pay/");
        SB.append("amount/");
        SB.append(etMoney.getText().toString().trim());
        SB.append("/");
        SB.append("email/");
        SB.append(email.getText().toString().trim());
        SB.append("/");
        SB.append("mobile/");
        SB.append(mobile.getText().toString().trim());
        SB.append("/");
        SB.append("token/");
        SB.append(Token);
        SB.append("/");
        SB.append("hash/");
        SB.append(Hash);
        SB.append("/");
        SB.append("fullName/");
        SB.append(name.getText().toString().trim());
        SB.append("/");
        SB.append("userId/");
        SB.append(UserId);
        Log.e("String ",SB.toString());
        return SB.toString();
    }


    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_money_indicator:
                toggleSettingsShow(R.id.ll_money_settings);
                break;
            case R.id.btn_set_money:
                float money = getMoney();
                msrvMoney.setValue(money);
               // CD.showDialog(Donate.this,"Payment Gateway coming soon....");
                break;

            default: break;
        }
    }

    private void toggleSettingsShow(@IdRes int layoutId) {
        LinearLayout llSettings = findViewById(layoutId);
        llSettings.setVisibility(llSettings.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
    }



    private float getMoney() {
        String moneyStr = etMoney.getText().toString();
        if (moneyStr.isEmpty()) {
            moneyStr = "0";
        }
        return Float.parseFloat(moneyStr);
    }



}
