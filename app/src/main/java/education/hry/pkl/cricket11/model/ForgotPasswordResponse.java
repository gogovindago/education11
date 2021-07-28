package education.hry.pkl.cricket11.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordResponse {



    @SerializedName("OTP")
    @Expose
    private String oTP;
    @SerializedName("RegistrationId")
    @Expose
    private Integer registrationId;
    @SerializedName("StatusCode")
    @Expose
    private Integer StatusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("StundentName")
    @Expose
    private String stundentName;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("AdmissionportalUrl")
    @Expose
    private String admissionportalUrl;
    @SerializedName("Extraurl")
    @Expose
    private String extraurl;
    @SerializedName("ProfilePic")
    @Expose
    private String profilePic;

    public String getOTP() {
        return oTP;
    }

    public void setOTP(String oTP) {
        this.oTP = oTP;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Integer getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(Integer StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStundentName() {
        return stundentName;
    }

    public void setStundentName(String stundentName) {
        this.stundentName = stundentName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdmissionportalUrl() {
        return admissionportalUrl;
    }

    public void setAdmissionportalUrl(String admissionportalUrl) {
        this.admissionportalUrl = admissionportalUrl;
    }

    public String getExtraurl() {
        return extraurl;
    }

    public void setExtraurl(String extraurl) {
        this.extraurl = extraurl;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
