package education.hry.pkl.cricket11.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginWithPasswordRespone {

    @SerializedName("StatusCode")
    @Expose
    private int StatusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("OTP")
    @Expose
    private String OTP;

    @SerializedName("RegistrationId")
    @Expose
    private int RegistrationId;

    @SerializedName("StundentName")
    @Expose
    private String StundentName;

    @SerializedName("Mobile")
    @Expose
    private String Mobile;


    @SerializedName("Email")
    @Expose
    private String Email;


     @SerializedName("Latitude")
    @Expose
    private String Latitude;


     @SerializedName("Longitude")
    @Expose
    private String Longitude;

     @SerializedName("AdmissionportalUrl")
    @Expose
    private String AdmissionportalUrl;

    @SerializedName("ProfilePic")
    @Expose
    private String ProfilePic ;

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getAdmissionportalUrl() {
        return AdmissionportalUrl;
    }

    public void setAdmissionportalUrl(String admissionportalUrl) {
        AdmissionportalUrl = admissionportalUrl;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public int getRegistrationId() {
        return RegistrationId;
    }

    public void setRegistrationId(int registrationId) {
        RegistrationId = registrationId;
    }

   /*"OTP": "698153",
    "RegistrationId": 1000,
    "StatusCode": 200,
    "Message": "Login Successfully!!",
    "StundentName": "GOVIND KUMAR",
    "Mobile": "8269970959",
    "Email": "govind224556@gmail.com"
    "Latitude": "30.6989409",
    "Longitude": "76.860478"
}
    /
    */

    public String getStundentName() {
        return StundentName;
    }

    public void setStundentName(String stundentName) {
        StundentName = stundentName;
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




    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }
}