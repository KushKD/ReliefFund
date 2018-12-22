package cmreliefdund.kushkumardhawan.com.relieffund.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cmreliefdund.kushkumardhawan.com.relieffund.Adapter.HistoryAdapter;
import cmreliefdund.kushkumardhawan.com.relieffund.Enum.TaskType;
import cmreliefdund.kushkumardhawan.com.relieffund.Helper.AppStatus;
import cmreliefdund.kushkumardhawan.com.relieffund.Interfaces.AsyncTaskListener;
import cmreliefdund.kushkumardhawan.com.relieffund.JsonManager.History_List_POJO_JSON;
import cmreliefdund.kushkumardhawan.com.relieffund.Modal.History_POJO;
import cmreliefdund.kushkumardhawan.com.relieffund.Presentation.Custom_Dialog;
import cmreliefdund.kushkumardhawan.com.relieffund.R;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.EConstants;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.Generic_Async_Post_Activity;

public class HistoryUser extends AppCompatActivity  implements AsyncTaskListener {


    private String token, mobile_number, api_hash, uid = null;


    Custom_Dialog CD = new Custom_Dialog();
    List<History_POJO> Donation_list_Server;   // change the list
    ListView listv;
    HistoryAdapter adapter;  // change the adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_user);

        SharedPreferences settings = getSharedPreferences(EConstants.PREF_SHARED, 0); // 0 - for private mode
        token = settings.getString(EConstants.TOKEN_LOGIN, null);
        mobile_number = settings.getString(EConstants.USER_MOBILE, null);
        api_hash = settings.getString(EConstants.HASH, null);
        uid = settings.getString(EConstants.USER_ID, null);

        listv = (ListView) findViewById(R.id.list);


        /**
         * GET History:
         *     URL: http://cmrelief.eypoc.com/api/v1/GetPaymentHistory
         *     Params:  "api_hash","source","token","uid","mobile_number"
         *     Hash Params: $post['mobile_number'].$post['token'].$post['uid'],$post["api_hash"]
         */


        if (AppStatus.getInstance(HistoryUser.this).isOnline()) {

            new Generic_Async_Post_Activity(HistoryUser.this, HistoryUser.this, TaskType.HISTORY).execute(
                    "history",
                    EConstants.URL + "GetPaymentHistory",
                    api_hash,
                    "M",
                    token,
                    uid,
                    mobile_number

            );

        } else CD.showDialog(HistoryUser.this, "Network isn't available");
    }

    @Override
    public void onTaskCompleted(String result, TaskType taskType) {

        if (taskType == TaskType.HISTORY) {
           // CD.showDialog(HistoryUser.this, result);
            //Parse Result

            Donation_list_Server = History_List_POJO_JSON.parseFeed(result);
            if (Donation_list_Server.isEmpty()) {
                Toast.makeText(getApplicationContext(), "List Empty", Toast.LENGTH_LONG).show();
            } else {
                updateDisplay();

            }

        }


    }

    private void updateDisplay() {

        // LGone.setVisibility(View.VISIBLE);   //Adapter needs to be changed
        adapter = new HistoryAdapter(this, R.layout.item_inbox, Donation_list_Server);
        listv.setAdapter(adapter);
        //  adapter.notifyDataSetChanged();
        // listv.setTextFilterEnabled(true);

    }

}
