package cmreliefdund.kushkumardhawan.com.relieffund.HTTP;

/**
 * Created by kuush on 10/29/2016.
 */

import android.accounts.NetworkErrorException;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Clock;

import cmreliefdund.kushkumardhawan.com.relieffund.Helper.Security;
import cmreliefdund.kushkumardhawan.com.relieffund.Modal.SharedPrefrencesPOJO;
import cmreliefdund.kushkumardhawan.com.relieffund.Utils.EConstants;

public class HttpManager {


    public String GetData(String url) {
        System.out.print(url);
        BufferedReader reader = null;

        try {
            URL url_ = new URL(url);
            HttpURLConnection con = (HttpURLConnection) url_.openConnection();

            if (con.getResponseCode() != 200) {
                return "Timeout";
            }


            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            con.disconnect();
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Timeout";
        } finally {
            if (reader != null) {
                try {
                    reader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error";
                }
            }
        }
    }

    public String PostData_SOS(String... params){

        URL url_ = null;
        HttpURLConnection conn_ = null;
        StringBuilder sb = null;
        JSONStringer userJson = null;

        String URL = null;
        String stringLatitude = null;
        String stringLongitude = null;
        String name = null;
        String Mobile_No = null;
        String IMEI = null;
        String IP_Address = null;
        String Date_Time = null;
        String Action_Status = null;
        String Remarks = null;
        String ActionDatenTime = null;

        try {

            URL = params[1];
            stringLatitude = params[2];
            stringLongitude = params[3];
            name = params[4];
            Mobile_No = params[5];
            IMEI = params[6];
            IP_Address = params[7];
            Date_Time = params[8];
            Action_Status = params[9];
            Remarks = params[10];
            ActionDatenTime = params[11];


            url_ =new URL(URL);
            conn_ = (HttpURLConnection)url_.openConnection();
            conn_.setDoOutput(true);
            conn_.setRequestMethod("POST");
            conn_.setUseCaches(false);
            conn_.setConnectTimeout(10000);
            conn_.setReadTimeout(10000);
            conn_.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn_.connect();

            userJson = new JSONStringer()
                    .object().key("SOSRequest")
                    .object()
                    .key("Latitude").value(stringLatitude)
                    .key("Longitutde").value(stringLongitude)
                    .key("Name").value(name)
                    .key("Mobile").value(Mobile_No)
                    .key("IMEI").value(IMEI)
                    .key("IPAddress").value(IP_Address)
                    .key("Datetime").value(Date_Time)
                    .key("ActionStatus").value(Action_Status)
                    .key("Remark").value(Remarks)
                    .key("ActionDatetime").value(ActionDatenTime)
                    .endObject()
                    .endObject();

            System.out.println(userJson.toString());
            Log.e("Object",userJson.toString());
            OutputStreamWriter out = new OutputStreamWriter(conn_.getOutputStream());
            out.write(userJson.toString());
            out.close();

            try{
                int HttpResult =conn_.getResponseCode();
                if(HttpResult !=HttpURLConnection.HTTP_OK){
                    return "Timeout.";

                }else{
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn_.getInputStream(),"utf-8"));
                    String line = null;
                    sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    System.out.println(sb.toString());
                }

            } catch(Exception e){
                return "Error";
            }

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{
            if(conn_!=null)
                conn_.disconnect();
        }
        return sb.toString();
    }

    public String PostData_Registration(String... params){
        URL url_ = null;
        HttpURLConnection conn_ = null;
        StringBuilder sb = null;
        JSONStringer userJson = null;

        String URL = null;
        String ResName = null;
        String LastName = null;
        String Mobile = null;
        String EMail = null;


        try {

            URL = params[1];
            ResName = params[2];
            LastName = params[3];
            EMail = params[4];
            Mobile = params[5];


            url_ =new URL(URL);
            conn_ = (HttpURLConnection)url_.openConnection();
            conn_.setDoOutput(true);
            conn_.setRequestMethod("POST");
            conn_.setUseCaches(false);
            conn_.setConnectTimeout(10000);
            conn_.setReadTimeout(10000);
            conn_.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


                conn_.connect();






//            JSONObject registrationUser = new JSONObject("{\"imsge\":\"+ResPhoto+\"}");
            userJson = new JSONStringer()
                    .object().key("UsersRegistration")
                    .object()
                    .key("FirstName").value(ResName)
                    .key("LastName").value(LastName)
                    .key("Mobile").value(Mobile)
                    .key("EMail").value(EMail)
                    .endObject()
                    .endObject();





            System.out.println(userJson.toString());
            Log.e("User Registration Obj",userJson.toString());



            OutputStreamWriter out = new OutputStreamWriter(conn_.getOutputStream());
            out.write(userJson.toString());
            out.close();

            try{
                int HttpResult =conn_.getResponseCode();
                if(HttpResult !=HttpURLConnection.HTTP_OK){
                    return "Timeout.";

                }else{
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn_.getInputStream(),"utf-8"));
                    String line = null;
                    sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    System.out.println(sb.toString());
                }

            } catch(Exception e){
                return "Error";
            }

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{
            if(conn_!=null)
                conn_.disconnect();
        }

         return sb.toString();
        //return userJson.toString();

    }


    public String PostMobileNumber(String... params){

        URL url_ = null;
        HttpURLConnection conn_ = null;
        StringBuilder sb = null;
        JSONStringer userJson = null;

        String URL = null;

        String mobileNumber = null;


        try {

            URL = params[1];
            System.out.println("========================= URL"+ URL);

            mobileNumber = params[2];
            System.out.println("========================= Mobile Number"+ mobileNumber);

            String mobileNumber_Md5 = Security.md5(mobileNumber.trim());
            String mobileNumber_Md5_Sha1_Key  = null;
            try {
                mobileNumber_Md5_Sha1_Key = Security.sha1Hash(mobileNumber_Md5, EConstants.API_HASH_KEY);
            } catch (Exception e) {
                e.printStackTrace();
            }

            userJson = new JSONStringer()
                    .object()
                    .key("api_hash").value(mobileNumber_Md5_Sha1_Key)
                    .key("mobile_number").value(mobileNumber)
                    .key("source").value("M")
                    .endObject();

            System.out.println(userJson.toString());
            Log.e("Object",userJson.toString());


            url_ =new URL(URL);
            conn_ = (HttpURLConnection)url_.openConnection();
            conn_.setDoOutput(true);
            conn_.setRequestMethod("POST");
            conn_.setUseCaches(false);
            conn_.setConnectTimeout(10000);
            conn_.setReadTimeout(10000);
            conn_.setRequestProperty("Content-Type", "application/json"); //application/json

            try {
                conn_.connect();
            }catch(Exception ex){
                System.out.println("========================= Error is "+ ex.getCause());
            }

            OutputStreamWriter out = new OutputStreamWriter(conn_.getOutputStream());
            out.write(userJson.toString());
            out.close();

            try{
                int HttpResult = conn_.getResponseCode();
                System.out.println("========================= Response Code"+ Integer.toString(HttpResult));
                System.out.println("========================= User Json"+ userJson.toString());
                if(HttpResult !=HttpURLConnection.HTTP_OK){
                    Log.e("Object",Integer.toString(HttpResult));
                    return "Timeout.";

                }else{
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn_.getInputStream(),"utf-8"));
                    String line = null;
                    sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    System.out.println(sb.toString());
                }

            } catch(Exception e){
                return "Error";
            }

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{
            if(conn_!=null)
                conn_.disconnect();
        }
        return sb.toString();





    }


    public String GetHistory(String... params){



        URL url_ = null;
        HttpURLConnection conn_ = null;
        StringBuilder sb = null;
        JSONStringer userJson = null;

        String URL = null;


        String  api_hash =null;
        String source = null;
        String token = null;
        String uid  = null;
        String user_mobile = null;




        /**
         * GET History:
         *     URL: http://cmrelief.eypoc.com/api/v1/GetPaymentHistory
         *     Params:  "api_hash","source","token","uid","mobile_number"
         *     Hash Params: $post['mobile_number'].$post['token'].$post['uid']
         */


        try {
            URL = params[1];
            api_hash = params[2];
            source = params[3];
            token = params[4];
            uid = params[5];
            user_mobile = params[6];




            String hashString = user_mobile;
            hashString= hashString.concat(token).concat(uid);

            Log.e("Hash String",hashString);
            String MD5_Data = Security.md5(hashString);
            String HashData=  Security.sha1Hash(MD5_Data, EConstants.API_HASH_KEY);


            userJson = new JSONStringer()
                    .object()
                    .key("api_hash").value(HashData)
                    .key("source").value(source)
                    .key("token").value(token)
                    .key("uid").value(uid)
                    .key("mobile_number").value(user_mobile)



                    .endObject();

            System.out.println(userJson.toString());
            Log.e("Object",userJson.toString());


            url_ =new URL(URL);
            conn_ = (HttpURLConnection)url_.openConnection();
            conn_.setDoOutput(true);
            conn_.setRequestMethod("POST");
            conn_.setUseCaches(false);
            conn_.setConnectTimeout(10000);
            conn_.setReadTimeout(10000);
            conn_.setRequestProperty("Content-Type", "application/json"); //application/json

            try {
                conn_.connect();
            }catch(Exception ex){
                System.out.println("========================= Error is "+ ex.getCause());
            }

            OutputStreamWriter out = new OutputStreamWriter(conn_.getOutputStream());
            out.write(userJson.toString());
            out.close();

            try{
                int HttpResult = conn_.getResponseCode();
                System.out.println("========================= Response Code"+ Integer.toString(HttpResult));
                System.out.println("========================= User Json"+ userJson.toString());
                if(HttpResult !=HttpURLConnection.HTTP_OK){
                    Log.e("Object",Integer.toString(HttpResult));
                    return "Timeout.";

                }else{
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn_.getInputStream(),"utf-8"));
                    String line = null;
                    sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    System.out.println(sb.toString());
                }

            } catch(Exception e){
                return "Error";
            }

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(conn_!=null)
                conn_.disconnect();
        }
        return sb.toString();

    }





    public String Appeal(String... params){





        URL url_ = null;
        HttpURLConnection conn_ = null;
        StringBuilder sb = null;
        JSONStringer userJson = null;

        String URL = null;


        String  api_hash =null;
        String source = null;
        String token = null;
        String uid  = null;
        String user_mobile = null;
        String user_email = null;
        String apeal_category = null;
        String user_district = null;
        String user_block = null;
        String appeal_description = null;






        try {
            URL = params[1];
            api_hash = params[2];
            source = params[3];
            token = params[4];
            uid = params[5];
            user_mobile = params[6];
            user_email  = params[7];
            apeal_category = params[8];
            user_district = params[9];
            user_block = params[10];
            appeal_description = params[11];



            String hashString = user_mobile;
            hashString= hashString.concat(token).concat(uid);

            Log.e("Hash String",hashString);
            String MD5_Data = Security.md5(hashString);
            String HashData=  Security.sha1Hash(MD5_Data, EConstants.API_HASH_KEY);


            userJson = new JSONStringer()
                    .object()
                    .key("api_hash").value(HashData)
                    .key("source").value(source)
                    .key("token").value(token)
                    .key("uid").value(uid)
                    .key("user_mobile").value(user_mobile)
                    .key("user_email").value(user_email)
                    .key("apeal_category").value(apeal_category)
                    .key("user_district").value(user_district)
                    .key("user_block").value(user_block)
                    .key("appeal_description").value(appeal_description)


                    .endObject();

            System.out.println(userJson.toString());
            Log.e("Object",userJson.toString());


            url_ =new URL(URL);
            conn_ = (HttpURLConnection)url_.openConnection();
            conn_.setDoOutput(true);
            conn_.setRequestMethod("POST");
            conn_.setUseCaches(false);
            conn_.setConnectTimeout(10000);
            conn_.setReadTimeout(10000);
            conn_.setRequestProperty("Content-Type", "application/json"); //application/json

            try {
                conn_.connect();
            }catch(Exception ex){
                System.out.println("========================= Error is "+ ex.getCause());
            }

            OutputStreamWriter out = new OutputStreamWriter(conn_.getOutputStream());
            out.write(userJson.toString());
            out.close();

            try{
                int HttpResult = conn_.getResponseCode();
                System.out.println("========================= Response Code"+ Integer.toString(HttpResult));
                System.out.println("========================= User Json"+ userJson.toString());
                if(HttpResult !=HttpURLConnection.HTTP_OK){
                    Log.e("Object",Integer.toString(HttpResult));
                    return "Timeout.";

                }else{
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn_.getInputStream(),"utf-8"));
                    String line = null;
                    sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    System.out.println(sb.toString());
                }

            } catch(Exception e){
                return "Error";
            }

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(conn_!=null)
                conn_.disconnect();
        }
        return sb.toString();

    }


    public String UpdateProfile(String... params){

        URL url_ = null;
        HttpURLConnection conn_ = null;
        StringBuilder sb = null;
        JSONStringer userJson = null;

        String URL = null;


        String  api_hash =null;

        String mobile_number = null;

        String source = null;

        String full_name = null;

        String email_id  = null;

        String token = null;

        String uid = null;




        try {

            URL = params[1];
              api_hash = params[2];

             mobile_number = params[3];

             source = params[4];

             full_name = params[5];

             email_id  = params[6];

             token = params[7];

             uid = params[8];

            String hashString = mobile_number;
            hashString= hashString.concat(token).concat(email_id).concat(full_name).concat(uid);

            Log.e("Hash String",hashString);
          String MD5_Data = Security.md5(hashString);
           String HashData=  Security.sha1Hash(MD5_Data, EConstants.API_HASH_KEY);


            userJson = new JSONStringer()
                    .object()
                    .key("api_hash").value(HashData)
                    .key("mobile_number").value(mobile_number)
                    .key("source").value(source)
                    .key("full_name").value(full_name)
                    .key("email_id").value(email_id)
                    .key("token").value(token)
                    .key("uid").value(uid)


                    .endObject();

            System.out.println(userJson.toString());
            Log.e("Object",userJson.toString());


            url_ =new URL(URL);
            conn_ = (HttpURLConnection)url_.openConnection();
            conn_.setDoOutput(true);
            conn_.setRequestMethod("POST");
            conn_.setUseCaches(false);
            conn_.setConnectTimeout(10000);
            conn_.setReadTimeout(10000);
            conn_.setRequestProperty("Content-Type", "application/json"); //application/json

            try {
                conn_.connect();
            }catch(Exception ex){
                System.out.println("========================= Error is "+ ex.getCause());
            }

            OutputStreamWriter out = new OutputStreamWriter(conn_.getOutputStream());
            out.write(userJson.toString());
            out.close();

            try{
                int HttpResult = conn_.getResponseCode();
                System.out.println("========================= Response Code"+ Integer.toString(HttpResult));
                System.out.println("========================= User Json"+ userJson.toString());
                if(HttpResult !=HttpURLConnection.HTTP_OK){
                    Log.e("Object",Integer.toString(HttpResult));
                    return "Timeout.";

                }else{
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn_.getInputStream(),"utf-8"));
                    String line = null;
                    sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    System.out.println(sb.toString());
                }

            } catch(Exception e){
                return "Error";
            }

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(conn_!=null)
                conn_.disconnect();
        }
        return sb.toString();

    }


    public String VerifyOtp(String... params){

        URL url_ = null;
        HttpURLConnection conn_ = null;
        StringBuilder sb = null;
        JSONStringer userJson = null;

        String URL = null;

        String mobileNumber = null;
        String OTP = null;

        try {

            URL = params[1];
            System.out.println("========================= URL"+ URL);

            mobileNumber = params[2];
            System.out.println("========================= Mobile Number"+ mobileNumber);

            OTP = params[3];
            System.out.println("========================= OTP "+ OTP);

            String Mobile_Otp = mobileNumber.trim()+OTP.trim();

            String mobileNumber_Otp_Md5 = Security.md5(Mobile_Otp);
            String mobileNumber_Otp_Md5_Sha1_Key  = null;
            try {
                mobileNumber_Otp_Md5_Sha1_Key = Security.sha1Hash(mobileNumber_Otp_Md5,"Hemant908#CMFUNDRELIEF");
            } catch (Exception e) {
                e.printStackTrace();
            }

            userJson = new JSONStringer()
                    .object()
                    .key("api_hash").value(mobileNumber_Otp_Md5_Sha1_Key)
                    .key("mobile_number").value(mobileNumber)
                    .key("source").value("M")
                    .key("mobile_otp").value(OTP)
                    .endObject();

            System.out.println(userJson.toString());
            Log.e("Object",userJson.toString());


            url_ =new URL(URL);
            conn_ = (HttpURLConnection)url_.openConnection();
            conn_.setDoOutput(true);
            conn_.setRequestMethod("POST");
            conn_.setUseCaches(false);
            conn_.setConnectTimeout(10000);
            conn_.setReadTimeout(10000);
            conn_.setRequestProperty("Content-Type", "application/json"); //application/json

            try {
                conn_.connect();
            }catch(Exception ex){
                System.out.println("========================= Error is "+ ex.getCause());
            }

            OutputStreamWriter out = new OutputStreamWriter(conn_.getOutputStream());
            out.write(userJson.toString());
            out.close();

            try{
                int HttpResult = conn_.getResponseCode();
                System.out.println("========================= Response Code"+ Integer.toString(HttpResult));
                System.out.println("========================= User Json"+ userJson.toString());
                if(HttpResult !=HttpURLConnection.HTTP_OK){
                    Log.e("Object",Integer.toString(HttpResult));
                    return "Timeout.";

                }else{
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn_.getInputStream(),"utf-8"));
                    String line = null;
                    sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    System.out.println(sb.toString());
                }

            } catch(Exception e){
                return "Error";
            }

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{
            if(conn_!=null)
                conn_.disconnect();
        }
        return sb.toString();





    }


}