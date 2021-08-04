package education.hry.pkl.cricket11.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.CareerStatisticsAdapter;
import education.hry.pkl.cricket11.allinterfaces.GetCareerStatistcsDetail_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;

import education.hry.pkl.cricket11.databinding.ActivityCareerStatisticsBinding;
import education.hry.pkl.cricket11.model.CareerStatisticsResponse;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;

public class CareerStatisticsActivity extends BaseActivity implements CareerStatisticsAdapter.ItemListener, GetCareerStatistcsDetail_interface {

    ArrayList<CareerStatisticsResponse.Datum> arrayList = new ArrayList<CareerStatisticsResponse.Datum>();
    CareerStatisticsAdapter adapter;
    ActivityCareerStatisticsBinding binding;
    String Registration_Id, token;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_career_statistics);

        try {
            Registration_Id = CSPreferences.readString(this, "User_Id");
            token = CSPreferences.readString(this, "token");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GlobalClass.isNetworkConnected(this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.CareerStatisticsDataMethod(this, this, token, this, binding.rvplayerCareer);


        } else {

            Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("Player Career Statistics");


        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initListeners() {

    }


    @Override
    public void GetCareerStatisticsDetail_list(List<CareerStatisticsResponse.Datum> list) {

        if (list != null) {

            arrayList.clear();
            arrayList.addAll(list);

            binding.rvplayerCareer.setVisibility(View.VISIBLE);

            manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            binding.rvplayerCareer.setLayoutManager(manager);
            adapter = new CareerStatisticsAdapter(this, (ArrayList) arrayList, this);
            binding.rvplayerCareer.setAdapter(adapter);


        } else {

            binding.rvplayerCareer.setVisibility(View.GONE);
            GlobalClass.dailogwarring(CareerStatisticsActivity.this, "Empty List", "No any Player found!");

        }
    }

    @Override
    public void onItemClick(CareerStatisticsResponse.Datum item, int currposition, String plyrhist) {

        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();

        Intent playerhistoryintent = new Intent(this, PlayerHistoryActivity.class);
        playerhistoryintent.putExtra("playerId", item.getPlayerId());
        startActivity(playerhistoryintent);


    }
}