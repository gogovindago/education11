package education.hry.pkl.cricket11.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BannerResponse {

    @SerializedName("response")
    @Expose
    private int response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data {

        @SerializedName("dashboardOfficers")
        @Expose
        private List<DashboardOfficer> dashboardOfficers = new ArrayList<DashboardOfficer>();

        @SerializedName("matchDetails")
        @Expose
        private List<MatchDetail> matchDetails = new ArrayList<MatchDetail>();

        @SerializedName("banners")
        @Expose
        private List<Banner> banners = new ArrayList<Banner>();

        @SerializedName("topBowl")
        @Expose
        private List<TopBowl> topBowl = new ArrayList<TopBowl>();
        @SerializedName("topBat")
        @Expose
        private List<TopBat> topBat = new ArrayList<TopBat>();
        @SerializedName("mostWicket")
        @Expose
        private List<MostWicket> mostWicket = new ArrayList<MostWicket>();
        @SerializedName("mostRun")
        @Expose
        private List<MostRun> mostRun = new ArrayList<MostRun>();
        @SerializedName("mostSix")
        @Expose
        private List<MostSix> mostSix = new ArrayList<MostSix>();
        @SerializedName("mostFour")
        @Expose
        private List<MostFour> mostFour = new ArrayList<MostFour>();

        public List<MatchDetail> getMatchDetails() {
            return matchDetails;
        }

        public void setMatchDetails(List<MatchDetail> matchDetails) {
            this.matchDetails = matchDetails;
        }

        public List<DashboardOfficer> getDashboardOfficers() {
            return dashboardOfficers;
        }

        public void setDashboardOfficers(List<DashboardOfficer> dashboardOfficers) {
            this.dashboardOfficers = dashboardOfficers;
        }

        public List<Banner> getBanners() {
            return banners;
        }

        public void setBanners(List<Banner> banners) {
            this.banners = banners;
        }

        public List<TopBowl> getTopBowl() {
            return topBowl;
        }

        public void setTopBowl(List<TopBowl> topBowl) {
            this.topBowl = topBowl;
        }

        public List<TopBat> getTopBat() {
            return topBat;
        }

        public void setTopBat(List<TopBat> topBat) {
            this.topBat = topBat;
        }

        public List<MostWicket> getMostWicket() {
            return mostWicket;
        }

        public void setMostWicket(List<MostWicket> mostWicket) {
            this.mostWicket = mostWicket;
        }

        public List<MostRun> getMostRun() {
            return mostRun;
        }

        public void setMostRun(List<MostRun> mostRun) {
            this.mostRun = mostRun;
        }

        public List<MostSix> getMostSix() {
            return mostSix;
        }

        public void setMostSix(List<MostSix> mostSix) {
            this.mostSix = mostSix;
        }

        public List<MostFour> getMostFour() {
            return mostFour;
        }

        public void setMostFour(List<MostFour> mostFour) {
            this.mostFour = mostFour;
        }
        @SerializedName("NewsCode")
        @Expose
        private Integer newsCode;
        @SerializedName("message")
        @Expose
        private String message;

        public Integer getNewsCode() {
            return newsCode;
        }

        public void setNewsCode(Integer newsCode) {
            this.newsCode = newsCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public class MatchDetail {

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
        private String scoreTeam1;
        @SerializedName("scoreTeam2")
        @Expose
        private String scoreTeam2;
        @SerializedName("wicketsTeam1")
        @Expose
        private String wicketsTeam1;
        @SerializedName("wicketsTeam2")
        @Expose
        private String wicketsTeam2;
        @SerializedName("overTeam1")
        @Expose
        private String overTeam1;
        @SerializedName("overTeam2")
        @Expose
        private String overTeam2;
        @SerializedName("matchDate")
        @Expose
        private String matchDate;
        @SerializedName("matchTitle")
        @Expose
        private String matchTitle;
        @SerializedName("resultRemarks")
        @Expose
        private String resultRemarks;

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

    }

    public class DashboardOfficer {

        @SerializedName("player_Id")
        @Expose
        private int playerId;
        @SerializedName("playerName")
        @Expose
        private String playerName;
        @SerializedName("fileName")
        @Expose
        private String fileName;
        @SerializedName("filePath")
        @Expose
        private String filePath;
        @SerializedName("designation")
        @Expose
        private String designation;

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

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

    }
    public class MostFour {

        @SerializedName("player_Id")
        @Expose
        private int playerId;
        @SerializedName("playerName")
        @Expose
        private String playerName;
        @SerializedName("innings")
        @Expose
        private String innings;
        @SerializedName("fours")
        @Expose
        private String fours;
        @SerializedName("filePath")
        @Expose
        private String filePath;

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

        public String getInnings() {
            return innings;
        }

        public void setInnings(String innings) {
            this.innings = innings;
        }

        public String getFours() {
            return fours;
        }

        public void setFours(String fours) {
            this.fours = fours;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

    }

    public class MostRun {

        @SerializedName("player_Id")
        @Expose
        private int playerId;
        @SerializedName("playerName")
        @Expose
        private String playerName;
        @SerializedName("innings")
        @Expose
        private String innings;
        @SerializedName("runs")
        @Expose
        private String runs;
        @SerializedName("batAverage")
        @Expose
        private String batAverage;
        @SerializedName("batStrikeRate")
        @Expose
        private String batStrikeRate;
        @SerializedName("filePath")
        @Expose
        private String filePath;

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

        public String getInnings() {
            return innings;
        }

        public void setInnings(String innings) {
            this.innings = innings;
        }

        public String getRuns() {
            return runs;
        }

        public void setRuns(String runs) {
            this.runs = runs;
        }

        public String getBatAverage() {
            return batAverage;
        }

        public void setBatAverage(String batAverage) {
            this.batAverage = batAverage;
        }

        public String getBatStrikeRate() {
            return batStrikeRate;
        }

        public void setBatStrikeRate(String batStrikeRate) {
            this.batStrikeRate = batStrikeRate;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

    }

    public class MostSix {

        @SerializedName("player_Id")
        @Expose
        private int playerId;
        @SerializedName("playerName")
        @Expose
        private String playerName;
        @SerializedName("innings")
        @Expose
        private String innings;
        @SerializedName("sixes")
        @Expose
        private String sixes;
        @SerializedName("filePath")
        @Expose
        private String filePath;

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

        public String getInnings() {
            return innings;
        }

        public void setInnings(String innings) {
            this.innings = innings;
        }

        public String getSixes() {
            return sixes;
        }

        public void setSixes(String sixes) {
            this.sixes = sixes;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

    }

    public class MostWicket {

        @SerializedName("player_Id")
        @Expose
        private int playerId;
        @SerializedName("playerName")
        @Expose
        private String playerName;
        @SerializedName("innings")
        @Expose
        private String innings;
        @SerializedName("wickets")
        @Expose
        private String wickets;
        @SerializedName("bowlAverage")
        @Expose
        private String bowlAverage;
        @SerializedName("ecoRate")
        @Expose
        private String ecoRate;
        @SerializedName("filePath")
        @Expose
        private String filePath;

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

        public String getInnings() {
            return innings;
        }

        public void setInnings(String innings) {
            this.innings = innings;
        }

        public String getWickets() {
            return wickets;
        }

        public void setWickets(String wickets) {
            this.wickets = wickets;
        }

        public String getBowlAverage() {
            return bowlAverage;
        }

        public void setBowlAverage(String bowlAverage) {
            this.bowlAverage = bowlAverage;
        }

        public String getEcoRate() {
            return ecoRate;
        }

        public void setEcoRate(String ecoRate) {
            this.ecoRate = ecoRate;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

    }

    public class TopBat {

        @SerializedName("player_Id")
        @Expose
        private int playerId;
        @SerializedName("playerName")
        @Expose
        private String playerName;
        @SerializedName("innings")
        @Expose
        private String innings;
        @SerializedName("score")
        @Expose
        private String score;
        @SerializedName("batAverage")
        @Expose
        private String batAverage;
        @SerializedName("batStrikeRate")
        @Expose
        private String batStrikeRate;
        @SerializedName("filePath")
        @Expose
        private String filePath;

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

        public String getInnings() {
            return innings;
        }

        public void setInnings(String innings) {
            this.innings = innings;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getBatAverage() {
            return batAverage;
        }

        public void setBatAverage(String batAverage) {
            this.batAverage = batAverage;
        }

        public String getBatStrikeRate() {
            return batStrikeRate;
        }

        public void setBatStrikeRate(String batStrikeRate) {
            this.batStrikeRate = batStrikeRate;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

    }

    public class TopBowl {

        @SerializedName("player_Id")
        @Expose
        private int playerId;
        @SerializedName("playerName")
        @Expose
        private String playerName;
        @SerializedName("innings")
        @Expose
        private String innings;
        @SerializedName("wickets")
        @Expose
        private String wickets;
        @SerializedName("bowlingAverage")
        @Expose
        private String bowlingAverage;
        @SerializedName("ecoRate")
        @Expose
        private String ecoRate;
        @SerializedName("filePath")
        @Expose
        private String filePath;

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

        public String getInnings() {
            return innings;
        }

        public void setInnings(String innings) {
            this.innings = innings;
        }

        public String getWickets() {
            return wickets;
        }

        public void setWickets(String wickets) {
            this.wickets = wickets;
        }

        public String getBowlingAverage() {
            return bowlingAverage;
        }

        public void setBowlingAverage(String bowlingAverage) {
            this.bowlingAverage = bowlingAverage;
        }

        public String getEcoRate() {
            return ecoRate;
        }

        public void setEcoRate(String ecoRate) {
            this.ecoRate = ecoRate;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

    }

    public class Banner {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("fileName")
        @Expose
        private String fileName;
        @SerializedName("filePath")
        @Expose
        private String filePath;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

    }

}
