package cmreliefdund.kushkumardhawan.com.relieffund.Fragment;


import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import cmreliefdund.kushkumardhawan.com.relieffund.Activity.Appeal;
import cmreliefdund.kushkumardhawan.com.relieffund.Activity.Donate;
import cmreliefdund.kushkumardhawan.com.relieffund.Presentation.Custom_Dialog;
import cmreliefdund.kushkumardhawan.com.relieffund.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    private LinearLayout health, girl,disaster,education,needy, appeal;
    private TextView discription,cmReliefFund;
    private String text_discription;
    Custom_Dialog CD = new Custom_Dialog();
    private LinearLayout button_donate;


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_about, container, false);
        // group = (ImageView) v.findViewById(R.id.group_image);
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_profile, container, false);

        // OTP_Server_et = (EditText)findViewById(R.id.otp_server);
        health = (LinearLayout) v.findViewById(R.id.health);
        girl = (LinearLayout) v.findViewById(R.id.girl);
        disaster = (LinearLayout) v.findViewById(R.id.disaster);
        education = (LinearLayout) v.findViewById(R.id.education);
        needy = (LinearLayout) v.findViewById(R.id.needy);
        discription = (TextView) v.findViewById(R.id.discription);
       // cmReliefFund=(TextView) v.findViewById(R.id.cmReliefFund);
        button_donate = v.findViewById(R.id.donate);
        appeal = v.findViewById(R.id.appeal);


        appeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(),Appeal.class);
                getActivity().startActivity(it);

            }
        });

        text_discription = "Grant of relief in Health cases for the following categories:- \n \t 1). Medical treatment in certain serious cases. \n\t 2).Premature death of the earning members of \tthe families.";
        discription.setText(text_discription);

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discription.setVisibility(View.VISIBLE);
                text_discription = "";
                text_discription = "Grant of relief in Health cases for the following categories:- \n \t 1). Medical treatment in certain serious cases. \n\t 2).Premature death of the earning members of \tthe families.";
                discription.setText(text_discription);

            }
        });

        girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discription.setVisibility(View.VISIBLE);
                text_discription = "";
                text_discription = "Grant of relief in Girl Child cases for the following categories:- \n \t 1). Relief to children of political sufferers  \t keeping in view their meagre financial resources.";
                discription.setText(text_discription);

            }
        });

        disaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discription.setVisibility(View.VISIBLE);
                text_discription = "";
                text_discription = "Grant of relief in exceptional cases for the following categories:- \n \t 1). Losses sustained as a result of natural \t calamities.\n \t 2). Premature death of the earning members of \t the families.";
                discription.setText(text_discription);

            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discription.setVisibility(View.VISIBLE);
                text_discription = "";
                text_discription = "Relief to poor but brilliant students pursuing technical professional studies.In cases where the students belong to Schedule Caste, the Chief Minister may consider the request for grant of relief sympathetically without insisting on their being brilliant in Studies.";
                discription.setText(text_discription);

            }
        });

        needy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discription.setVisibility(View.VISIBLE);
                text_discription = "";
                text_discription = "Relief to widows having no source of income for the maintenance of their children ";
                discription.setText(text_discription);

            }
        });

        button_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(),Donate.class);
                getActivity().startActivity(it);
            }
        });


//        cmReliefFund.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              CD.showDialog(getActivity(),"C.M. Relief Fund is Fund at State Headquarters at the Disposal of Hon'ble C.M . Moreover, There is a District Relief Fund at each of the District Headquarters in the State at the Disposal of Deputy Commissioner. Expenditure from the Chief Minister's Relief Fund may be incurred at Chief Minister's discretion for such purposes as may be approved by him.");
//
//            }
//        });



        // Inflate the layout for this fragment
        return v;
    }

}
