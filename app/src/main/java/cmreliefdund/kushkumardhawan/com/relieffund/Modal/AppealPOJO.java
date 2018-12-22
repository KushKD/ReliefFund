package cmreliefdund.kushkumardhawan.com.relieffund.Modal;

import java.io.Serializable;

public class AppealPOJO implements Serializable {


    private String name;
    private String mobile_number;
    private String email;
    private String category;
    private String district;
    private String village;
    private String block;
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AppealPOJO{" +
                "name='" + name + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                ", email='" + email + '\'' +
                ", category='" + category + '\'' +
                ", district='" + district + '\'' +
                ", village='" + village + '\'' +
                ", block='" + block + '\'' +
                ", description='" + description + '\'' +
                '}';
    }



}
