package cmreliefdund.kushkumardhawan.com.relieffund.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import cmreliefdund.kushkumardhawan.com.relieffund.R;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.EConstants;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar progressBar;
    private Timer timer;
    private int i=0;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(0);

        textView=(TextView)findViewById(R.id.textView3);
        textView.setText("");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences settings = getSharedPreferences(EConstants.PREF_SHARED, 0);


                int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP) {


                    boolean Flag_Registration = settings.getBoolean(EConstants.LOG_IN, false);  //Registration flag is the login one


                        if (Flag_Registration) {
                            Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                            SplashScreen.this.startActivity(mainIntent);
                            SplashScreen.this.finish();
                        } else {
                            Intent mainIntent2= new Intent(SplashScreen.this, Login.class);  //LoginType
                            SplashScreen.this.startActivity(mainIntent2);
                            SplashScreen.this.finish();
                        }

                }




            }
        }, 9000);   //2000


        final long period = 100;
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //this repeats every 100 ms
                if (i<100){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("Loading Please Wait.."+String.valueOf(i)+"%");
                        }
                    });
                    progressBar.setProgress(i);
                    i++;
                }
            }
        }, 0, period);
    }
}
