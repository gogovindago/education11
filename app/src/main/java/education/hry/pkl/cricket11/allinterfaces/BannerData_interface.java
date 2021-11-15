package education.hry.pkl.cricket11.allinterfaces;



import java.util.List;

import education.hry.pkl.cricket11.model.BannerResponse;


public interface BannerData_interface {
    void allBanner_list(List<BannerResponse.Banner> list);
    void allhigherauthority_list(List<BannerResponse.DashboardOfficer> list);
    void allrecentmatches_list(List<BannerResponse.MatchDetail> list);
    void allTopbattingAvg_list(List<BannerResponse.TopBat> list);
    void allTopBowlingAvg_list(List<BannerResponse.TopBowl> list);
    void allMostWicket_list(List<BannerResponse.MostWicket> list);
    void allMostRun_list(List<BannerResponse.MostRun> list);

    void allMostSixes_list(List<BannerResponse.MostSix> list);
    void allMostFours_list(List<BannerResponse.MostFour> list);

    void BestBatsman_list(List<BannerResponse.BestBatsman> list);
    void BestBowler_list(List<BannerResponse.BestBowler> list);
    void UpcomingBirthday_list(List<BannerResponse.UpcomingBirthday> list);

}
