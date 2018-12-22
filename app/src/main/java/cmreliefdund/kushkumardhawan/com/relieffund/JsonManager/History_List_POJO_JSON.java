package cmreliefdund.kushkumardhawan.com.relieffund.JsonManager;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

import cmreliefdund.kushkumardhawan.com.relieffund.Modal.History_POJO;


public class History_List_POJO_JSON {

    public static List<History_POJO> parseFeed(String content) {

        try {

            String g_Table = null;
            Object json = new JSONTokener(content).nextValue();
            if (json instanceof JSONObject){
                //  Log.d("Json ", "Object");
                JSONObject obj = new JSONObject(content);
                g_Table = obj.optString("RESPONSE");
                Log.d("Table===",g_Table);
            }
            //you have an object
            else if (json instanceof JSONArray){
            }

            JSONArray ar = new JSONArray(g_Table);
            List<History_POJO>userList = new ArrayList<>();


            for (int i = 0; i < ar.length(); i++) {
                JSONObject obj = ar.getJSONObject(i);
                History_POJO parking = new History_POJO();
                parking.setPayment_date_time( obj.getString("payment_date_time"));
                parking.setAmount(obj.getString("amount"));

                userList.add(parking);
            }
            return userList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
