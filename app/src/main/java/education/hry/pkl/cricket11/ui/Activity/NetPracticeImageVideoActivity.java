package education.hry.pkl.cricket11.ui.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.NetImageVideoAdapter;
import education.hry.pkl.cricket11.adapter.NetpracticeTypeAdapter;
import education.hry.pkl.cricket11.allinterfaces.GetNetImageVideoDetail_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityNetPracticeImageVideoBinding;
import education.hry.pkl.cricket11.model.NetImageVideoResponse;
import education.hry.pkl.cricket11.model.NetpracticeTypeData;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;

public class NetPracticeImageVideoActivity extends BaseActivity implements GetNetImageVideoDetail_interface, AdapterView.OnItemSelectedListener, NetImageVideoAdapter.ItemListener {

    NetImageVideoAdapter netImageVideoAdapter;
    ActivityNetPracticeImageVideoBinding binding;
    String Registration_Id, token, netdatatype;
    int playerId;
    ArrayList<NetpracticeTypeData> netpracticelist = new ArrayList<NetpracticeTypeData>();
    ArrayList<NetImageVideoResponse.Datum> data = new ArrayList<NetImageVideoResponse.Datum>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_net_practice_image_video);

        NetpracticeTypeData netpracticeTypeDatadefault = new NetpracticeTypeData();

        netpracticeTypeDatadefault.setImage(R.drawable.photo);
        netpracticeTypeDatadefault.setName("Please Select data type");
        netpracticelist.add(0, netpracticeTypeDatadefault);

        NetpracticeTypeData netpracticeTypeData = new NetpracticeTypeData();

        netpracticeTypeData.setImage(R.drawable.photo);
        netpracticeTypeData.setName("Image");
        netpracticelist.add(1, netpracticeTypeData);

        NetpracticeTypeData netpracticevideoeData = new NetpracticeTypeData();

        netpracticevideoeData.setImage(R.drawable.ic_baseline_videocam_24);
        netpracticevideoeData.setName("Video");
        netpracticelist.add(2, netpracticevideoeData);


        NetpracticeTypeAdapter adapter = new NetpracticeTypeAdapter(getApplicationContext(), netpracticelist);
        binding.spnContentType.setAdapter(adapter);
        binding.spnContentType.setOnItemSelectedListener(this);


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


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(NetPracticeImageVideoActivity.this)) {

                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.allNetImageVideolistMethod(NetPracticeImageVideoActivity.this, NetPracticeImageVideoActivity.this, netdatatype, NetPracticeImageVideoActivity.this, binding.simpleSwipeRefreshLayout);

                } else {

                    Toast.makeText(NetPracticeImageVideoActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("Net Practice");

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
    public void GetNetImageVideoPlayerListDetail_list(List<NetImageVideoResponse.Datum> list) {

        data.clear();
        data = new ArrayList();
        data.addAll(list);

        netImageVideoAdapter = new NetImageVideoAdapter(this, (ArrayList) data, this);
        binding.rvnetimgvideo.setAdapter(netImageVideoAdapter);
        GridLayoutManager adminimagemanager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        binding.rvnetimgvideo.setLayoutManager(adminimagemanager);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        if (position != 0) {
            netdatatype = netpracticelist.get(position).getName();
            if (GlobalClass.isNetworkConnected(NetPracticeImageVideoActivity.this)) {

                WebAPiCall aPiCall = new WebAPiCall();
                aPiCall.allNetImageVideolistMethod(NetPracticeImageVideoActivity.this, NetPracticeImageVideoActivity.this, netdatatype, NetPracticeImageVideoActivity.this, binding.simpleSwipeRefreshLayout);

            } else {

                Toast.makeText(NetPracticeImageVideoActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
            }

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(NetImageVideoResponse.Datum item, int currposition) {


    }
}