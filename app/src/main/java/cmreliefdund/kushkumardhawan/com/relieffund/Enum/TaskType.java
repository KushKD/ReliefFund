package cmreliefdund.kushkumardhawan.com.relieffund.Enum;

/**
 * Created by kuush on 10/29/2016.
 */

public enum TaskType {

    SOS(1),
    REGISTRATION(2),
    LOGIN(3),
    VERIFY_OTP(4),
    VACATIONTRAVELDETAILS(5),
    UPDATE_PROFILE(6),
    APPEAL(7),
    HISTORY(8);
    int value;

    private TaskType(int value) { this.value = value; }
}
