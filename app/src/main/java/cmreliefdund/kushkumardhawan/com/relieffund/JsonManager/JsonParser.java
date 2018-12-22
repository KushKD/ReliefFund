package cmreliefdund.kushkumardhawan.com.relieffund.JsonManager;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import cmreliefdund.kushkumardhawan.com.relieffund.Modal.JsonResponse;

/**
 * Created by kuush on 10/29/2016.
 */

public class JsonParser {
    public static String VehicleIn_Parse(String s) {

        String g_Table = null;
        try {
            Object json = new JSONTokener(s).nextValue();
            if (json instanceof JSONObject) {
                JSONObject obj = new JSONObject(s);
                g_Table = obj.optString("getParkingTransaction_JSONResult");
                return g_Table;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String Vehicle_Out_Parse(String s) {

        String g_Table = null;
        try {
            Object json = new JSONTokener(s).nextValue();
            if (json instanceof JSONObject) {
                JSONObject obj = new JSONObject(s);
                g_Table = obj.optString("getParkingOut_JSONResult");
                return g_Table;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }






        public JsonResponse ParseString(String s) {

            JsonResponse JR = new JsonResponse();
            try {
                Object json = new JSONTokener(s).nextValue();
                if (json instanceof JSONObject) {
                    JSONObject obj = new JSONObject(s);
                    System.out.println("Object IS"+obj);
                    JR.setMsg(obj.optString("msg"));
                    JR.setRESPONSE(obj.optString("RESPONSE"));
                    JR.setSTATUS(obj.optString("STATUS"));
                    return JR;
                } else {
                    return null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }


    public JsonResponse ParseStringUser(String s){
        JsonResponse JR = new JsonResponse();
        try {
            Object json = new JSONTokener(s).nextValue();
            if (json instanceof JSONObject) {
                JSONObject obj = new JSONObject(s);
                System.out.println("Object IS"+obj);
                JR.setMsg(obj.optString("msg"));
                JR.setRESPONSE(obj.optString("RESPONSE"));
                JR.setSTATUS(obj.optString("STATUS"));
                return JR;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JsonResponse ParseAppeal(String s){
        JsonResponse JR = new JsonResponse();
        try {
            Object json = new JSONTokener(s).nextValue();
            if (json instanceof JSONObject) {
                JSONObject obj = new JSONObject(s);
                System.out.println("Object IS"+obj);
                JR.setMsg(obj.optString("msg"));
                JR.setRESPONSE(obj.optString("RESPONSE"));
                JR.setSTATUS(obj.optString("STATUS"));
                return JR;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    //ParseAppeal

    public JsonResponse ParseStringToken(String s) {

        JsonResponse JR = new JsonResponse();
        try {
            Object json = new JSONTokener(s).nextValue();
            if (json instanceof JSONObject) {
                JSONObject obj = new JSONObject(s);
                System.out.println("Object IS"+obj);
                JR.setMsg(obj.optString("msg"));
                JR.setRESPONSE(obj.optString("RESPONSE"));
                JR.setSTATUS(obj.optString("STATUS"));
               // JR.setToken(obj.optString("token"));
                if(JR.getSTATUS().equalsIgnoreCase("200")){
                    String S = obj.optString("token");
                    Log.e("Token Object is:- ", S);
                    //Second Object Json
                    JSONObject Object2 = new JSONObject(S);
                    JR.setUid(Object2.optString("uid"));
                    JR.setToken(Object2.optString("token"));
                    Log.e("Json Response ", JR.toString());
                    return JR;
                }else{
                    return JR;
                }

            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
