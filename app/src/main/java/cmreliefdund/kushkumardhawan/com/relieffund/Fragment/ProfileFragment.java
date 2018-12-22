package cmreliefdund.kushkumardhawan.com.relieffund.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import cmreliefdund.kushkumardhawan.com.relieffund.Activity.Appeal;
import cmreliefdund.kushkumardhawan.com.relieffund.Activity.Help;
import cmreliefdund.kushkumardhawan.com.relieffund.Activity.HistoryUser;
import cmreliefdund.kushkumardhawan.com.relieffund.Activity.ProfileUser;
import cmreliefdund.kushkumardhawan.com.relieffund.Enum.TaskType;
import cmreliefdund.kushkumardhawan.com.relieffund.Helper.AppStatus;
import cmreliefdund.kushkumardhawan.com.relieffund.Interfaces.AsyncTaskListener;
import cmreliefdund.kushkumardhawan.com.relieffund.Modal.UserDetails;
import cmreliefdund.kushkumardhawan.com.relieffund.Presentation.Custom_Dialog;
import cmreliefdund.kushkumardhawan.com.relieffund.R;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.EConstants;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.Generic_Async_Post_Activity;

public class ProfileFragment extends Fragment  {

    private LinearLayout help, tax_form,logout,settings,history, profile;



    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        help = (LinearLayout) v.findViewById(R.id.help);
        tax_form = (LinearLayout) v.findViewById(R.id.tax_form);
        logout = (LinearLayout) v.findViewById(R.id.logout);
        settings = (LinearLayout) v.findViewById(R.id.settings);
        history = (LinearLayout) v.findViewById(R.id.history);
        profile = (LinearLayout) v.findViewById(R.id.profile);


        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent help = new Intent(getContext(),Help.class);
                getActivity().startActivity(help);

            }
        });

        tax_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Intent history = new Intent(getContext(),Help.class);
                // getActivity().startActivity(history);
                Custom_Dialog CD  = new Custom_Dialog();
                CD.showDialog(getActivity(),"TODO");

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Custom_Dialog CD = new Custom_Dialog();
                CD.showDialog_Logout(getActivity(),"Are you Sure You want to Log Out");



            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent dialogIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(dialogIntent);

            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent history = new Intent(getContext(),HistoryUser.class);
                getActivity().startActivity(history);

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent profile = new Intent(getContext(),ProfileUser.class);
                getActivity().startActivity(profile);

            }
        });


        return v;
    }









}

