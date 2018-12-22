package cmreliefdund.kushkumardhawan.com.relieffund.Presentation;

/**
 * Created by kuush on 10/29/2016.
 */


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import cmreliefdund.kushkumardhawan.com.relieffund.Activity.Help;
import cmreliefdund.kushkumardhawan.com.relieffund.Activity.Login;
import cmreliefdund.kushkumardhawan.com.relieffund.Activity.MainActivity;
import cmreliefdund.kushkumardhawan.com.relieffund.Activity.SplashScreen;
import cmreliefdund.kushkumardhawan.com.relieffund.R;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.EConstants;


/**
 * Created by kuush on 6/16/2018.
 */
public class Custom_Dialog  {



    public void showDialog(final Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom);

        TextView text = (TextView)dialog.findViewById(R.id.dialog_result);
        text.setText(msg);

        Button dialog_ok = (Button)dialog.findViewById(R.id.dialog_ok);
        Button dialog_dismiss = (Button)dialog.findViewById(R.id.dialog_dismiss);


        dialog_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // activity.finish();
                dialog.dismiss();
            }
        });

        dialog.show();

    }



    public void showDialog_Vehicle_IN_OUT(final Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom);

        TextView text = (TextView) dialog.findViewById(R.id.dialog_result);
        text.setText(msg);

        Button dialog_dismiss = (Button)dialog.findViewById(R.id.dialog_dismiss);


        dialog_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button dialog_ok = (Button)dialog.findViewById(R.id.dialog_ok);

        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void showDialog_Logout(final Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom);

        TextView text = (TextView) dialog.findViewById(R.id.dialog_result);
        text.setText(msg);

        Button dialog_ok = (Button)dialog.findViewById(R.id.dialog_ok);
        Button dialog_dismiss = (Button)dialog.findViewById(R.id.dialog_dismiss);


        dialog_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Clear the shared Prefrence File
                SharedPreferences preferences = activity.getSharedPreferences(EConstants.PREF_SHARED, 0);
                preferences.edit().remove(EConstants.LOG_IN).commit();
                preferences.edit().remove(EConstants.USER_MOBILE).commit();
                preferences.edit().remove(EConstants.TOKEN_LOGIN).commit();
                preferences.edit().remove(EConstants.TUTORIALS_FLAG).commit();

                Intent mainIntent = new Intent(activity, Login.class);
             activity.startActivity(mainIntent);
                activity.finish();
                dialog.dismiss();
            }
        });

        dialog.show();

    }


}