package cmreliefdund.kushkumardhawan.com.relieffund.Modal;

import java.io.Serializable;

public class History_POJO implements Serializable {

           private String payment_id;
    private String payment_brn;
    private String    status_code;
    private String    status;
    private String    amount;
    private String    payment_transaction;
    private String    payment_date_time;
    private String    user_id;
    private String    user_mobile;
    private String    payment_cid;
    private String    payment_type;
    private String      payment_currency;
    private String       checksum;
    private String       created_date_time;


    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getPayment_brn() {
        return payment_brn;
    }

    public void setPayment_brn(String payment_brn) {
        this.payment_brn = payment_brn;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayment_transaction() {
        return payment_transaction;
    }

    public void setPayment_transaction(String payment_transaction) {
        this.payment_transaction = payment_transaction;
    }

    public String getPayment_date_time() {
        return payment_date_time;
    }

    public void setPayment_date_time(String payment_date_time) {
        this.payment_date_time = payment_date_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getPayment_cid() {
        return payment_cid;
    }

    public void setPayment_cid(String payment_cid) {
        this.payment_cid = payment_cid;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getPayment_currency() {
        return payment_currency;
    }

    public void setPayment_currency(String payment_currency) {
        this.payment_currency = payment_currency;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getCreated_date_time() {
        return created_date_time;
    }

    public void setCreated_date_time(String created_date_time) {
        this.created_date_time = created_date_time;
    }

    @Override
    public String toString() {
        return "History_POJO{" +
                "payment_id='" + payment_id + '\'' +
                ", payment_brn='" + payment_brn + '\'' +
                ", status_code='" + status_code + '\'' +
                ", status='" + status + '\'' +
                ", amount='" + amount + '\'' +
                ", payment_transaction='" + payment_transaction + '\'' +
                ", payment_date_time='" + payment_date_time + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_mobile='" + user_mobile + '\'' +
                ", payment_cid='" + payment_cid + '\'' +
                ", payment_type='" + payment_type + '\'' +
                ", payment_currency='" + payment_currency + '\'' +
                ", checksum='" + checksum + '\'' +
                ", created_date_time='" + created_date_time + '\'' +
                '}';
    }
}
