package cmreliefdund.kushkumardhawan.com.relieffund.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import cmreliefdund.kushkumardhawan.com.relieffund.Enum.TaskType;
import cmreliefdund.kushkumardhawan.com.relieffund.HTTP.HttpManager;
import cmreliefdund.kushkumardhawan.com.relieffund.Interfaces.AsyncTaskListener;


/**
 * Created by kuush on 11/2/2016.
 */

public class Generic_Async_Post_Activity extends AsyncTask<String,Void ,String> {

    String outputStr;
    ProgressDialog dialog;
    Context context;
    AsyncTaskListener taskListener;
    TaskType taskType;

    public Generic_Async_Post_Activity(Context context, AsyncTaskListener taskListener, TaskType taskType){
        this.context = context;
        this.taskListener = taskListener;
        this.taskType = taskType;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Loading", "Connecting to Server .. Please Wait", true);
           dialog.setCancelable(false);
    }

    @Override
    protected String doInBackground(String... params) {
        String Data_From_Server = null;
        HttpManager http_manager = null;
        try{
            http_manager = new HttpManager();
            if(params[0].equalsIgnoreCase("getSOSRequest_JSON")){
                Data_From_Server = http_manager.PostData_SOS(params);
                return Data_From_Server;
            }else if(params[0].equalsIgnoreCase("getUserRegistration_JSON")){
                Data_From_Server = http_manager.PostData_Registration(params);
                return Data_From_Server;
            }else if(params[0].equalsIgnoreCase("getOtp")){
                Data_From_Server = http_manager.PostMobileNumber(params);
                Log.e("Response",Data_From_Server);
                return Data_From_Server;
            }else if(params[0].equalsIgnoreCase("veryfyOtp")){
                Data_From_Server = http_manager.VerifyOtp(params);
                Log.e("Response",Data_From_Server);
                return Data_From_Server;
            }else if(params[0].equalsIgnoreCase("updateProfile")){
                Data_From_Server = http_manager.UpdateProfile(params);
                Log.e("Response",Data_From_Server);
                return Data_From_Server;
            }else if(params[0].equalsIgnoreCase("appeal")){
                Data_From_Server = http_manager.Appeal(params);
                Log.e("Response",Data_From_Server);
                return Data_From_Server;
            }else if(params[0].equalsIgnoreCase("history")){
                Data_From_Server = http_manager.GetHistory(params);
                Log.e("Response",Data_From_Server);
                return Data_From_Server;
            }



            else{
                return "Error";
            }

        }catch(Exception e){
            return e.getLocalizedMessage().toString().trim();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        taskListener.onTaskCompleted(result, taskType);
        dialog.dismiss();
    }


}
