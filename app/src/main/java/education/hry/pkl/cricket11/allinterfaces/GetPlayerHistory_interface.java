package education.hry.pkl.cricket11.allinterfaces;

import java.util.List;

import education.hry.pkl.cricket11.model.PlayerHistoryResponse;


public interface GetPlayerHistory_interface {
    void GetPlayerHistory_list(List<PlayerHistoryResponse.Datum> list);
}

