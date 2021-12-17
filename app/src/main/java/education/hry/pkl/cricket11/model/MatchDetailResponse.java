package education.hry.pkl.cricket11.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MatchDetailResponse {
    @SerializedName("response")
    @Expose
    private int response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }


    public class Datum {


        @SerializedName("scoreCardFileName")
        @Expose
        private String scoreCardFileName;

        @SerializedName("scoreCardFilePath")
        @Expose
        private String scoreCardFilePath;

        @SerializedName("playerName")
        @Expose
        private String playerName;


        @SerializedName("filePath")
        @Expose
        private String filePath;

        @SerializedName("manoftheMatchTeam")
        @Expose
        private String manoftheMatchTeam;




        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("team1")
        @Expose
        private String team1;
        @SerializedName("versusTeam2")
        @Expose
        private String versusTeam2;
        @SerializedName("scoreTeam1")
        @Expose
        private int scoreTeam1;
        @SerializedName("scoreTeam2")
        @Expose
        private int scoreTeam2;
        @SerializedName("wicketsTeam1")
        @Expose
        private int wicketsTeam1;
        @SerializedName("wicketsTeam2")
        @Expose
        private int wicketsTeam2;
        @SerializedName("overTeam1")
        @Expose
        private double overTeam1;
        @SerializedName("overTeam2")
        @Expose
        private double overTeam2;
        @SerializedName("matchDate")
        @Expose
        private String matchDate;
        @SerializedName("matchTitle")
        @Expose
        private String matchTitle;
        @SerializedName("resultRemarks")
        @Expose
        private String resultRemarks;


        public String getScoreCardFileName() {
            return scoreCardFileName;
        }

        public void setScoreCardFileName(String scoreCardFileName) {
            this.scoreCardFileName = scoreCardFileName;
        }

        public String getScoreCardFilePath() {
            return scoreCardFilePath;
        }

        public void setScoreCardFilePath(String scoreCardFilePath) {
            this.scoreCardFilePath = scoreCardFilePath;
        }

        public String getPlayerName() {
            return playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getManoftheMatchTeam() {
            return manoftheMatchTeam;
        }

        public void setManoftheMatchTeam(String manoftheMatchTeam) {
            this.manoftheMatchTeam = manoftheMatchTeam;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTeam1() {
            return team1;
        }

        public void setTeam1(String team1) {
            this.team1 = team1;
        }

        public String getVersusTeam2() {
            return versusTeam2;
        }

        public void setVersusTeam2(String versusTeam2) {
            this.versusTeam2 = versusTeam2;
        }

        public int getScoreTeam1() {
            return scoreTeam1;
        }

        public void setScoreTeam1(int scoreTeam1) {
            this.scoreTeam1 = scoreTeam1;
        }

        public int getScoreTeam2() {
            return scoreTeam2;
        }

        public void setScoreTeam2(int scoreTeam2) {
            this.scoreTeam2 = scoreTeam2;
        }

        public int getWicketsTeam1() {
            return wicketsTeam1;
        }

        public void setWicketsTeam1(int wicketsTeam1) {
            this.wicketsTeam1 = wicketsTeam1;
        }

        public int getWicketsTeam2() {
            return wicketsTeam2;
        }

        public void setWicketsTeam2(int wicketsTeam2) {
            this.wicketsTeam2 = wicketsTeam2;
        }

        public double getOverTeam1() {
            return overTeam1;
        }

        public void setOverTeam1(double overTeam1) {
            this.overTeam1 = overTeam1;
        }

        public double getOverTeam2() {
            return overTeam2;
        }

        public void setOverTeam2(double overTeam2) {
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

    }
}
