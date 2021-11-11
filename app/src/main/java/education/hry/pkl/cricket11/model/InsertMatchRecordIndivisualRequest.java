package education.hry.pkl.cricket11.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertMatchRecordIndivisualRequest {

    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Versus")
    @Expose
    private String versus;
    @SerializedName("Player_Id")
    @Expose
    private String playerId;
    @SerializedName("Score")
    @Expose
    private String score;
    @SerializedName("Balls")
    @Expose
    private String balls;
    @SerializedName("Fours")
    @Expose
    private String fours;
    @SerializedName("Six")
    @Expose
    private String six;
    @SerializedName("Overs")
    @Expose
    private String overs;
    @SerializedName("Maiden")
    @Expose
    private String maiden;
    @SerializedName("Run")
    @Expose
    private String run;
    @SerializedName("Wickets")
    @Expose
    private String wickets;
    @SerializedName("Catches")
    @Expose
    private String catches;
    @SerializedName("IsNotOut")
    @Expose
    private String isNotOut;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVersus() {
        return versus;
    }

    public void setVersus(String versus) {
        this.versus = versus;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getBalls() {
        return balls;
    }

    public void setBalls(String balls) {
        this.balls = balls;
    }

    public String getFours() {
        return fours;
    }

    public void setFours(String fours) {
        this.fours = fours;
    }

    public String getSix() {
        return six;
    }

    public void setSix(String six) {
        this.six = six;
    }

    public String getOvers() {
        return overs;
    }

    public void setOvers(String overs) {
        this.overs = overs;
    }

    public String getMaiden() {
        return maiden;
    }

    public void setMaiden(String maiden) {
        this.maiden = maiden;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getWickets() {
        return wickets;
    }

    public void setWickets(String wickets) {
        this.wickets = wickets;
    }

    public String getCatches() {
        return catches;
    }

    public void setCatches(String catches) {
        this.catches = catches;
    }

    public String getIsNotOut() {
        return isNotOut;
    }

    public void setIsNotOut(String isNotOut) {
        this.isNotOut = isNotOut;
    }



}
