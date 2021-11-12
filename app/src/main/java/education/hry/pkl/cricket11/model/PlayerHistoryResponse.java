package education.hry.pkl.cricket11.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlayerHistoryResponse {
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


        @SerializedName("match_Id")
        @Expose
        private int match_Id;


        @SerializedName("player_Id")
        @Expose
        private int playerId;
        @SerializedName("playerName")
        @Expose
        private String playerName;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("versus")
        @Expose
        private String versus;
        @SerializedName("score")
        @Expose
        private int score;
        @SerializedName("balls")
        @Expose
        private int balls;
        @SerializedName("fours")
        @Expose
        private int fours;
        @SerializedName("six")
        @Expose
        private int six;
        @SerializedName("overs")
        @Expose
        private float overs;
        @SerializedName("maiden")
        @Expose
        private int maiden;
        @SerializedName("run")
        @Expose
        private int run;
        @SerializedName("wickets")
        @Expose
        private int wickets;
        @SerializedName("catches")
        @Expose
        private int catches;
        @SerializedName("notOut")
        @Expose
        private int notOut;
        @SerializedName("batStrikeRate")
        @Expose
        private Object batStrikeRate;
        @SerializedName("ecoRate")
        @Expose
        private float ecoRate;

        public int getMatch_Id() {
            return match_Id;
        }

        public void setMatch_Id(int match_Id) {
            this.match_Id = match_Id;
        }

        public int getPlayerId() {
            return playerId;
        }

        public void setPlayerId(int playerId) {
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

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getBalls() {
            return balls;
        }

        public void setBalls(int balls) {
            this.balls = balls;
        }

        public int getFours() {
            return fours;
        }

        public void setFours(int fours) {
            this.fours = fours;
        }

        public int getSix() {
            return six;
        }

        public void setSix(int six) {
            this.six = six;
        }

        public float getOvers() {
            return overs;
        }

        public void setOvers(float overs) {
            this.overs = overs;
        }

        public int getMaiden() {
            return maiden;
        }

        public void setMaiden(int maiden) {
            this.maiden = maiden;
        }

        public int getRun() {
            return run;
        }

        public void setRun(int run) {
            this.run = run;
        }

        public int getWickets() {
            return wickets;
        }

        public void setWickets(int wickets) {
            this.wickets = wickets;
        }

        public int getCatches() {
            return catches;
        }

        public void setCatches(int catches) {
            this.catches = catches;
        }

        public int getNotOut() {
            return notOut;
        }

        public void setNotOut(int notOut) {
            this.notOut = notOut;
        }

        public Object getBatStrikeRate() {
            return batStrikeRate;
        }

        public void setBatStrikeRate(Object batStrikeRate) {
            this.batStrikeRate = batStrikeRate;
        }

        public float getEcoRate() {
            return ecoRate;
        }

        public void setEcoRate(float ecoRate) {
            this.ecoRate = ecoRate;
        }

    }
}
