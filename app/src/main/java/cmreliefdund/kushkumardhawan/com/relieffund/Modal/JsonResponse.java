package cmreliefdund.kushkumardhawan.com.relieffund.Modal;

import java.io.Serializable;

public class JsonResponse  implements Serializable {

    private String STATUS;
    private String RESPONSE;
    private String msg;
    private String token;
    private String uid;

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getRESPONSE() {
        return RESPONSE;
    }

    public void setRESPONSE(String RESPONSE) {
        this.RESPONSE = RESPONSE;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "STATUS='" + STATUS + '\'' +
                ", RESPONSE='" + RESPONSE + '\'' +
                ", msg='" + msg + '\'' +
                ", token='" + token + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
