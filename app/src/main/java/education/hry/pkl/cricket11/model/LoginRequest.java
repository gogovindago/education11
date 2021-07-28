package education.hry.pkl.cricket11.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {


    @SerializedName("Mobile")
    @Expose
    private String Mobile;


    @SerializedName("Password")
    @Expose
    private String Password;



    @SerializedName("FcmToken")
    @Expose
    private String FcmToken;

    public String getFcmToken() {
        return FcmToken;
    }

    public void setFcmToken(String fcmToken) {
        FcmToken = fcmToken;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

   /* public String getFcmTokenId() {
        return FcmToken;
    }

    public void setFcmTokenId(String fcmTokenId) {
        FcmToken = fcmTokenId;
    }*/

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}
