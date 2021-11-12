package education.hry.pkl.cricket11.ui.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.AllTeamListAdapter;
import education.hry.pkl.cricket11.allinterfaces.GetAllTeamList_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityAllTeamListBinding;
import education.hry.pkl.cricket11.model.AllTeamListResponse;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.GlobalClass;
import education.hry.pkl.cricket11.utility.NetworkUtil;


public class AllTeamListActivity extends BaseActivity implements GetAllTeamList_interface, AllTeamListAdapter.ItemListener {
    ActivityAllTeamListBinding binding;
    private List<AllTeamListResponse.Datum> data = new ArrayList<AllTeamListResponse.Datum>();
    private AllTeamListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_team_list);


        if (NetworkUtil.isConnected(AllTeamListActivity.this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.allTeamlistMethod(AllTeamListActivity.this, AllTeamListActivity.this, "2", AllTeamListActivity.this, binding.simpleSwipeRefreshLayout);


        } else {
            GlobalClass.showtost(AllTeamListActivity.this, "No Internet Available.Plz check your internet connection.");
        }


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {


                if (NetworkUtil.isConnected(AllTeamListActivity.this)) {

                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.allTeamlistMethod(AllTeamListActivity.this, AllTeamListActivity.this, "2", AllTeamListActivity.this, binding.simpleSwipeRefreshLayout);


                } else {
                    GlobalClass.showtost(AllTeamListActivity.this, "No Internet Available.Plz check your internet connection.");
                }

                binding.simpleSwipeRefreshLayout.setRefreshing(false);
            }
        });


    }

    @Override
    public void initData() {

        binding.toolbar.tvToolbarTitle.setText("All Team");

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
    public void GetAllTeamListDetail_list(List<AllTeamListResponse.Datum> list) {


        data.clear();
        data = new ArrayList();
        data.addAll(list);
        GridLayoutManager adminimagemanager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        adapter = new AllTeamListAdapter(this, (ArrayList) data, this);
        binding.rvteamList.setAdapter(adapter);
        binding.rvteamList.setLayoutManager(adminimagemanager);


    }

    @Override
    public void onItemClick(AllTeamListResponse.Datum item, int currposition) {


    }
}