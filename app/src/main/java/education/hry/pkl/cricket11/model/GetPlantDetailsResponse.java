package education.hry.pkl.cricket11.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Govind Kumar on 10/9/2020.
 * *  govind224556@gmail.com
 **/
public class GetPlantDetailsResponse {
    @SerializedName("response")
    @Expose
    private int response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private List<GetPlantDetailsData> data = null;

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public String getSysMessage() {
        return sysMessage;
    }

    public void setSysMessage(String sysMessage) {
        this.sysMessage = sysMessage;
    }

    public List<GetPlantDetailsData> getData() {
        return data;
    }

    public void setData(List<GetPlantDetailsData> data) {
        this.data = data;
    }


    public  static class GetPlantDetailsData {

    @SerializedName("GreenHaryana_Id")
    @Expose
    private int greenHaryanaId;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("Latitude")
    @Expose
    private String latitude;

    @SerializedName("ImagePath")
    @Expose
    private String imagePath;

    @SerializedName("Description")
    @Expose
    private String Description;



    @SerializedName("ImageDate")
    @Expose
    private String ImageDate;


        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getImageDate() {
            return ImageDate;
        }

        public void setImageDate(String imageDate) {
            ImageDate = imageDate;
        }

        public int getGreenHaryanaId() {
        return greenHaryanaId;
    }

    public void setGreenHaryanaId(int greenHaryanaId) {
        this.greenHaryanaId = greenHaryanaId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    }
}
