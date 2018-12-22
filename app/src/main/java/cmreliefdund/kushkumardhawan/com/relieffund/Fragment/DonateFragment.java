package cmreliefdund.kushkumardhawan.com.relieffund.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cmreliefdund.kushkumardhawan.com.relieffund.Activity.Donate;
import cmreliefdund.kushkumardhawan.com.relieffund.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonateFragment extends Fragment {


    LinearLayout donate, appeal;
    public DonateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_donate, container, false);
        // group = (ImageView) v.findViewById(R.id.group_image);
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_profile, container, false);

        // OTP_Server_et = (EditText)findViewById(R.id.otp_server);
        donate = (LinearLayout) v.findViewById(R.id.donate);
//        appeal = (LinearLayout) v.findViewById(R.id.appeal);


        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(),Donate.class);
                getActivity().startActivity(it);

            }
        });

//        appeal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(getContext(),Appeal.class);
//                getActivity().startActivity(it);
//
//            }
//        });



        return v;
    }

}
