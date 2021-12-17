package education.hry.pkl.cricket11.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RegistrationRespone {

    @SerializedName("response")
    @Expose
    private long response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();

    public long getResponse() {
        return response;
    }

    public void setResponse(long response) {
        this.response = response;
    }

    public String getSysMessage() {
        return sysMessage;
    }

    public void setSysMessage(String sysMessage) {
        this.sysMessage = sysMessage;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }



    public class Datum {

        @SerializedName("user_Id")
        @Expose
        private long userId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("mobileNo")
        @Expose
        private String mobileNo;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("role")
        @Expose
        private String role;
        @SerializedName("isActive")
        @Expose
        private boolean isActive;
        @SerializedName("fcmToken")
        @Expose
        private String fcmToken;
        @SerializedName("player_Id")
        @Expose
        private long playerId;
        @SerializedName("playerName")
        @Expose
        private String playerName;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("playingRole")
        @Expose
        private String playingRole;
        @SerializedName("fileName")
        @Expose
        private String fileName;
        @SerializedName("filePath")
        @Expose
        private String filePath;
        @SerializedName("emailId")
        @Expose
        private String emailId;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("isDeleted")
        @Expose
        private long isDeleted;
        @SerializedName("fcmToken1")
        @Expose
        private Object fcmToken1;
        @SerializedName("designation")
        @Expose
        private Object designation;
        @SerializedName("teamId")
        @Expose
        private long teamId;
        @SerializedName("isApproved")
        @Expose
        private long isApproved;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public boolean isIsActive() {
            return isActive;
        }

        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }

        public String getFcmToken() {
            return fcmToken;
        }

        public void setFcmToken(String fcmToken) {
            this.fcmToken = fcmToken;
        }

        public long getPlayerId() {
            return playerId;
        }

        public void setPlayerId(long playerId) {
            this.playerId = playerId;
        }

        public String getPlayerName() {
            return playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getPlayingRole() {
            return playingRole;
        }

        public void setPlayingRole(String playingRole) {
            this.playingRole = playingRole;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public long getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(long isDeleted) {
            this.isDeleted = isDeleted;
        }

        public Object getFcmToken1() {
            return fcmToken1;
        }

        public void setFcmToken1(Object fcmToken1) {
            this.fcmToken1 = fcmToken1;
        }

        public Object getDesignation() {
            return designation;
        }

        public void setDesignation(Object designation) {
            this.designation = designation;
        }

        public long getTeamId() {
            return teamId;
        }

        public void setTeamId(long teamId) {
            this.teamId = teamId;
        }

        public long getIsApproved() {
            return isApproved;
        }

        public void setIsApproved(long isApproved) {
            this.isApproved = isApproved;
        }

    }

}
