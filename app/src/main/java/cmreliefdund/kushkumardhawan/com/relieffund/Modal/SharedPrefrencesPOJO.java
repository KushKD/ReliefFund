package cmreliefdund.kushkumardhawan.com.relieffund.Modal;

import java.io.Serializable;

public class SharedPrefrencesPOJO implements Serializable {

    private String token;
    private String mobile_number;
    private String imei;
    private String uid;
    private String API_HASH;

    public String getAPI_HASH() {
        return API_HASH;
    }

    public void setAPI_HASH(String API_HASH) {
        this.API_HASH = API_HASH;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImei() {
        return imei;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }


    @Override
    public String toString() {
        return "SharedPrefrencesPOJO{" +
                "token='" + token + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                ", imei='" + imei + '\'' +
                ", uid='" + uid + '\'' +
                ", API_HASH='" + API_HASH + '\'' +
                '}';
    }
}
