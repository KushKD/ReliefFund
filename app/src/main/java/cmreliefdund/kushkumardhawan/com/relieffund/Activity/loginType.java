package cmreliefdund.kushkumardhawan.com.relieffund.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import cmreliefdund.kushkumardhawan.com.relieffund.R;

public class loginType extends Activity {


    private LinearLayout  department_user , general_user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_type);

        department_user = (LinearLayout)findViewById(R.id.department_user);
        general_user = (LinearLayout)findViewById(R.id.general_user);


        department_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Go To Department Login  Screen
               // Intent mainIntent2= new Intent(loginType.this, loginType.class);
               // loginType.this.startActivity(mainIntent2);
               // loginType.this.finish();

            }
        });

        general_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Go to General User Login Screen
                Intent mainIntent2= new Intent(loginType.this, Login.class);
                loginType.this.startActivity(mainIntent2);
                loginType.this.finish();


            }
        });

    }

}
