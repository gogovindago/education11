package education.hry.pkl.cricket11.ui.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.MatchesDetailAdapter;
import education.hry.pkl.cricket11.allinterfaces.MatchesDetailData_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityMatchDetailsBinding;
import education.hry.pkl.cricket11.model.MatchDetailResponse;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;

public class MatchDetailsActivity extends BaseActivity implements MatchesDetailData_interface, MatchesDetailAdapter.ItemListener {
    String Registration_Id, token;

    ArrayList<MatchDetailResponse.Datum> arrayList = new ArrayList<MatchDetailResponse.Datum>();
    MatchesDetailAdapter adapter;

    ActivityMatchDetailsBinding binding;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_match_details);





        try {
            Registration_Id = CSPreferences.readString(this, "User_Id");
            token = CSPreferences.readString(this, "token");



        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GlobalClass.isNetworkConnected(this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.MatchDetailDataMethod(this, this, token, this);

        } else {

            Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("All Matches Details");


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
    public void allmatches_list(List<MatchDetailResponse.Datum> list) {



        if (list != null) {

            arrayList.clear();
            arrayList.addAll(list);

            binding.rvmatchDetail.setVisibility(View.VISIBLE);

            manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            binding.rvmatchDetail.setLayoutManager(manager);
            adapter = new MatchesDetailAdapter(this, (ArrayList) arrayList, this);
            binding.rvmatchDetail.setAdapter(adapter);


        } else {

            binding.rvmatchDetail.setVisibility(View.GONE);
            GlobalClass.dailogwarring(MatchDetailsActivity.this, "Empty List", "No any Player found!");

        }


    }

    @Override
    public void onItemClick(MatchDetailResponse.Datum item, int currposition) {

    }
}