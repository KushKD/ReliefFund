package cmreliefdund.kushkumardhawan.com.relieffund.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import cmreliefdund.kushkumardhawan.com.relieffund.Enum.TaskType;
import cmreliefdund.kushkumardhawan.com.relieffund.Helper.AppStatus;
import cmreliefdund.kushkumardhawan.com.relieffund.Interfaces.AsyncTaskListener;
import cmreliefdund.kushkumardhawan.com.relieffund.JsonManager.JsonParser;
import cmreliefdund.kushkumardhawan.com.relieffund.Modal.AppealPOJO;
import cmreliefdund.kushkumardhawan.com.relieffund.Modal.JsonResponse;
import cmreliefdund.kushkumardhawan.com.relieffund.Presentation.Custom_Dialog;
import cmreliefdund.kushkumardhawan.com.relieffund.R;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.EConstants;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.Generic_Async_Post_Activity;

public class Appeal extends AppCompatActivity implements AsyncTaskListener {

    private TextView Mobile_TextView,name,email;
    private Spinner  spinner , district, block, village;
    private Button back , submit;
    private EditText  description;

    Custom_Dialog CD = new Custom_Dialog();

    String Token = null;
    String Hash = null;
    String UserId = null;
    String Mobile = null;
    String apeal_category = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appeal);

         spinner = (Spinner) findViewById(R.id.category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        SharedPreferences settings = getSharedPreferences(EConstants.PREF_SHARED, 0); // 0 - for private mode
        Token = settings.getString(EConstants.TOKEN_LOGIN, null);
         Mobile = settings.getString(EConstants.USER_MOBILE, null);
        Hash = settings.getString(EConstants.HASH, null);
        UserId = settings.getString(EConstants.USER_ID, null);


        Mobile_TextView = findViewById(R.id.mobile);
        back = (Button)findViewById(R.id.back);
        submit = findViewById(R.id.submit);
        name= findViewById(R.id.name);
        email = findViewById(R.id.email);
        description = findViewById(R.id.decription);

         apeal_category = spinner.getSelectedItem().toString();



        Mobile_TextView.setText(Mobile);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Appeal.this.finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TO DO

                AppealPOJO AP = new AppealPOJO();
                //"api_hash","source","token","uid","user_mobile","user_email","apeal_category","user_district","user_block","appeal_description"
                //   Hash Params: $post['user_mobile'].$post['token'].$post['uid'],$post["api_hash"]

                //Mobile_TextView
                //name , email, Mobile_TextView , category , distict, block, village, description


                //Check NAme
                if(!name.getText().toString().trim().isEmpty()){

                    //Check Email
                    if(!email.getText().toString().trim().isEmpty()) {
                        if(!Mobile.isEmpty()) {

                            if (!description.getText().toString().trim().isEmpty()) {
                                if (AppStatus.getInstance(Appeal.this).isOnline()) {

                                    new Generic_Async_Post_Activity(Appeal.this, Appeal.this, TaskType.APPEAL).execute(
                                            "appeal",
                                            EConstants.URL + "GenerateAppeal",
                                            Hash,
                                            "M",
                                            Token,
                                            UserId,
                                            Mobile,
                                            email.getText().toString().trim(),
                                            apeal_category,
                                            "1",
                                            "1",
                                            description.getText().toString()
                                    );

                                } else CD.showDialog(Appeal.this, "Network isn't available");
                            } else {
                                CD.showDialog(Appeal.this, "Please enter Description for the Appeal.");
                            }
                        }else{
                            CD.showDialog(Appeal.this, "Please enter a Valid Mobile Number");
                        }


                    }else{
                        CD.showDialog(Appeal.this, "Please enter email address");
                    }
                }else{
                    CD.showDialog(Appeal.this, "Please enter Name ");
                }




            }
        });

    }

    @Override
    public void onTaskCompleted(String result, TaskType taskType) {

        JsonResponse response = null;
        if(taskType == TaskType.APPEAL){
            JsonParser JP = new JsonParser();
            response = JP.ParseAppeal(result);

            if(response.getSTATUS().equalsIgnoreCase("200")){
                CD.showDialog(Appeal.this, response.getMsg());
            }else{
                CD.showDialog(Appeal.this, response.getMsg());
            }
        }
    }
}
