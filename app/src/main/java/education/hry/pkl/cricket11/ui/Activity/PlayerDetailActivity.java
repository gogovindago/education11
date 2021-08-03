package education.hry.pkl.cricket11.ui.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.PlayerListAdapter;
import education.hry.pkl.cricket11.allinterfaces.GetPlayerListDetail_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityPlayerDetailBinding;
import education.hry.pkl.cricket11.model.PlayersListResponse;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;

public class PlayerDetailActivity extends BaseActivity implements PlayerListAdapter.ItemListener, GetPlayerListDetail_interface {

    ActivityPlayerDetailBinding binding;

    ArrayList<PlayersListResponse.Datum> arrayList = new ArrayList<PlayersListResponse.Datum>();
    String Registration_Id, token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player_detail);



        TextView tv_toolbarTitle = findViewById(R.id.tv_toolbarTitle);
        tv_toolbarTitle.setText("Player List");
        ImageButton back = findViewById(R.id.back);


        try {
            Registration_Id = CSPreferences.readString(this, "User_Id");
            token = CSPreferences.readString(this, "token");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GlobalClass.isNetworkConnected(this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.PlayerListDataMethod(this, this, token, this);


        } else {

            Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        binding.rvplayer.setLayoutManager(manager);


    }


    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void onItemClick(PlayersListResponse.Datum item, int currposition) {

    }


    @Override
    public void GetPlayerListDetail_list(List<PlayersListResponse.Datum> list) {

        if (list != null) {

            arrayList.clear();
            arrayList.addAll(list);
            binding.rvplayer.setVisibility(View.VISIBLE);

            PlayerListAdapter adaptermain = new PlayerListAdapter(this, arrayList, this);
            binding.rvplayer.setAdapter(adaptermain);


        } else {

            binding.rvplayer.setVisibility(View.GONE);
            GlobalClass.dailogwarring(PlayerDetailActivity.this, "Empty List", "No any Player found!");

        }

    }
}