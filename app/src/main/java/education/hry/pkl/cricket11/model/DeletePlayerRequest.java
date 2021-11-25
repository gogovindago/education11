package education.hry.pkl.cricket11.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeletePlayerRequest {
    @SerializedName("Player_Id")
    @Expose
    private String Player_Id;
    @SerializedName("PhoneNumber")
    @Expose
    private String PhoneNumber;

    public String getPlayer_Id() {
        return Player_Id;
    }

    public void setPlayer_Id(String player_Id) {
        Player_Id = player_Id;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
