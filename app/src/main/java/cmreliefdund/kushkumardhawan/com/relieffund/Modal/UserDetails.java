package cmreliefdund.kushkumardhawan.com.relieffund.Modal;

import java.io.Serializable;

/**
 * Created by kuush on 11/3/2016.
 */

public class UserDetails implements Serializable {


    private String Name;
    private String LastName;
    private String IMEI;
    private String Mobile;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    private String Email;


    @Override
    public String toString() {
        return "UserDetails{" +
                "Name='" + Name + '\'' +
                ", LastName='" + LastName + '\'' +
                ", IMEI='" + IMEI + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
