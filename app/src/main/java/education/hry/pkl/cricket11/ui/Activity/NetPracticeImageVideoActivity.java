package education.hry.pkl.cricket11.ui.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.allinterfaces.GetNetImageVideoDetail_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityNetPracticeImageVideoBinding;
import education.hry.pkl.cricket11.model.NetImageVideoResponse;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;

public class NetPracticeImageVideoActivity extends BaseActivity implements GetNetImageVideoDetail_interface {


    ActivityNetPracticeImageVideoBinding binding;
    String Registration_Id, token;
    int playerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_net_practice_image_video);

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


        if (GlobalClass.isNetworkConnected(NetPracticeImageVideoActivity.this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.allNetImageVideolistMethod(NetPracticeImageVideoActivity.this, NetPracticeImageVideoActivity.this, "Image", NetPracticeImageVideoActivity.this, binding.simpleSwipeRefreshLayout);

        } else {

            Toast.makeText(NetPracticeImageVideoActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(NetPracticeImageVideoActivity.this)) {

                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.allNetImageVideolistMethod(NetPracticeImageVideoActivity.this, NetPracticeImageVideoActivity.this, "Video", NetPracticeImageVideoActivity.this, binding.simpleSwipeRefreshLayout);

                } else {

                    Toast.makeText(NetPracticeImageVideoActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    @Override
    public void initData() {


    }

    @Override
    public void initListeners() {

    }

    @Override
    public void GetNetImageVideoPlayerListDetail_list(List<NetImageVideoResponse.Datum> list) {

    }
}