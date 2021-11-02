package education.hry.pkl.cricket11.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddMatchResultRequest {

    @SerializedName("VersusTeam2Id")
    @Expose
    private String versusTeam2Id;
    @SerializedName("ScoreTeam1")
    @Expose
    private String scoreTeam1;
    @SerializedName("ScoreTeam2")
    @Expose
    private String scoreTeam2;
    @SerializedName("WicketsTeam1")
    @Expose
    private String wicketsTeam1;
    @SerializedName("WicketsTeam2")
    @Expose
    private String wicketsTeam2;
    @SerializedName("OverTeam1")
    @Expose
    private String overTeam1;
    @SerializedName("OverTeam2")
    @Expose
    private String overTeam2;
    @SerializedName("MatchDate")
    @Expose
    private String matchDate;
    @SerializedName("MatchTitle")
    @Expose
    private String matchTitle;
    @SerializedName("ResultRemarks")
    @Expose
    private String resultRemarks;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;

    public String getVersusTeam2Id() {
        return versusTeam2Id;
    }

    public void setVersusTeam2Id(String versusTeam2Id) {
        this.versusTeam2Id = versusTeam2Id;
    }

    public String getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(String scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public String getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(String scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public String getWicketsTeam1() {
        return wicketsTeam1;
    }

    public void setWicketsTeam1(String wicketsTeam1) {
        this.wicketsTeam1 = wicketsTeam1;
    }

    public String getWicketsTeam2() {
        return wicketsTeam2;
    }

    public void setWicketsTeam2(String wicketsTeam2) {
        this.wicketsTeam2 = wicketsTeam2;
    }

    public String getOverTeam1() {
        return overTeam1;
    }

    public void setOverTeam1(String overTeam1) {
        this.overTeam1 = overTeam1;
    }

    public String getOverTeam2() {
        return overTeam2;
    }

    public void setOverTeam2(String overTeam2) {
        this.overTeam2 = overTeam2;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchTitle() {
        return matchTitle;
    }

    public void setMatchTitle(String matchTitle) {
        this.matchTitle = matchTitle;
    }

    public String getResultRemarks() {
        return resultRemarks;
    }

    public void setResultRemarks(String resultRemarks) {
        this.resultRemarks = resultRemarks;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}
