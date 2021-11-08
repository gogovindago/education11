package education.hry.pkl.cricket11.allinterfaces;

import java.util.List;

import education.hry.pkl.cricket11.model.AllTeamListResponse;


public interface GetAllTeamList_interface {
    void GetAllTeamListDetail_list(List<AllTeamListResponse.Datum> list);
}

