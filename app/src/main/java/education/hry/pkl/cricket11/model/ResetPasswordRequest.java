package education.hry.pkl.cricket11.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordRequest {

/*
{
    "MobileNoNo":"7018401818",
    "Password":"1234",
    "OldPassword":"12345"
}*/
    @SerializedName("MobileNo")
    @Expose
    private String mobile;

  @SerializedName("Password")
    @Expose
    private String Password;

    @SerializedName("OldPassword")
    @Expose
    private String OldPassword;


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String regId) {
        OldPassword = regId;
    }

    public String getMobileNo() {
        return mobile;
    }

    public void setMobileNo(String mobile) {
        this.mobile = mobile;
    }
}
