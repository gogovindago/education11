package education.hry.pkl.cricket11.ui.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.AllTeamListAdapter;
import education.hry.pkl.cricket11.allinterfaces.GetAllTeamList_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityAllTeamListBinding;
import education.hry.pkl.cricket11.model.AllTeamListResponse;
import education.hry.pkl.cricket11.model.DeleteTeamDetailsRequest;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;
import education.hry.pkl.cricket11.utility.NetworkUtil;


public class AllTeamListActivity extends BaseActivity implements GetAllTeamList_interface, AllTeamListAdapter.ItemListener {
    ActivityAllTeamListBinding binding;
    private List<AllTeamListResponse.Datum> data = new ArrayList<AllTeamListResponse.Datum>();
    private AllTeamListAdapter adapter;
    private SweetAlertDialog sweetAlertDialog;
String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_team_list);

        role = CSPreferences.readString(AllTeamListActivity.this, "role");




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

        adapter = new AllTeamListAdapter(this, (ArrayList) data, role, this);
        binding.rvteamList.setAdapter(adapter);
        binding.rvteamList.setLayoutManager(adminimagemanager);


    }

    @Override
    public void onItemClick(AllTeamListResponse.Datum item, int currposition) {


      //  GlobalClass.showtost(AllTeamListActivity.this, "AAyaa  " + item.getTeamId());

        sweetAlertDialog = new SweetAlertDialog(AllTeamListActivity.this);
        sweetAlertDialog.setTitle("Alert Team Detail Deleting !");
        sweetAlertDialog.setContentText("Are you sure want to delete this Team Record?");
        sweetAlertDialog.setVolumeControlStream(2);
        sweetAlertDialog.setCancelable(true);
        sweetAlertDialog.setCancelText("No");
        sweetAlertDialog.setConfirmText("Yes");
        sweetAlertDialog.setCustomImage(R.mipmap.ic_launcher_round);

        sweetAlertDialog.changeAlertType(3);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                if (NetworkUtil.isConnected(AllTeamListActivity.this)) {
                    sweetAlertDialog.dismiss();

                    DeleteTeamDetailsRequest request = new DeleteTeamDetailsRequest();
                    request.setId(String.valueOf(item.getTeamId()));
                    // request.setDeletedBy(CSPreferences.readString(AllTeamListActivity.this, "User_name"));


                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.DeleteTeamDetailsPostDataMethod(AllTeamListActivity.this, AllTeamListActivity.this, request);


                } else {
                    GlobalClass.showtost(AllTeamListActivity.this, "No Internet Available.Plz check your internet connection.");
                }

            }
        });

        sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        sweetAlertDialog.show();


    }
}