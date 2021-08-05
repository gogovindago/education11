package education.hry.pkl.cricket11.ui.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.CareerStatisticsAdapter;
import education.hry.pkl.cricket11.adapter.PlayerHistoryAdapter;
import education.hry.pkl.cricket11.allinterfaces.GetPlayerHistory_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityPlayerHistoryBinding;
import education.hry.pkl.cricket11.model.PlayerHistoryResponse;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;

public class PlayerHistoryActivity extends BaseActivity implements PlayerHistoryAdapter.ItemListener, GetPlayerHistory_interface {

    ActivityPlayerHistoryBinding binding;
    ArrayList<PlayerHistoryResponse.Datum> arrayList = new ArrayList<PlayerHistoryResponse.Datum>();
    String Registration_Id, token;
    int playerId;
    private PlayerHistoryAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player_history);


        try {
            Registration_Id = CSPreferences.readString(this, "User_Id");
            token = CSPreferences.readString(this, "token");


            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                playerId = extras.getInt("playerId");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GlobalClass.isNetworkConnected(this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.PlayerHistoryDataMethod(this, this, token, String.valueOf(playerId), this);


        } else {

            Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void initData() {
       // binding.toolbar.tvToolbarTitle.setText("History");

    }

    @Override
    public void initListeners() {
        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onItemClick(PlayerHistoryResponse.Datum item, int currposition) {

    }

    @Override
    public void GetPlayerHistory_list(List<PlayerHistoryResponse.Datum> list) {


        if (list != null) {
            binding.toolbar.tvToolbarTitle.setText(list.get(0).getPlayerName()+" History");

            arrayList.clear();
            arrayList.addAll(list);
            binding.rvplayer.setVisibility(View.VISIBLE);
            manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            binding.rvplayer.setLayoutManager(manager);
            adapter = new PlayerHistoryAdapter(this, (ArrayList) arrayList, this);
            binding.rvplayer.setAdapter(adapter);


        } else {

            binding.rvplayer.setVisibility(View.GONE);
            GlobalClass.dailogwarring(PlayerHistoryActivity.this, "Empty List", "No Data found!");

        }

    }
}