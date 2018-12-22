package cmreliefdund.kushkumardhawan.com.relieffund.Interfaces;


import cmreliefdund.kushkumardhawan.com.relieffund.Enum.TaskType;

/**
 * Created by kuush on 10/29/2016.
 */

public interface AsyncTaskListener {

    public void onTaskCompleted(String result, TaskType taskType);
}