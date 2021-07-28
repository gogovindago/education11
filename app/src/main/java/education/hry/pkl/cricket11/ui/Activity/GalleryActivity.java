package education.hry.pkl.cricket11.ui.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.GalleryAdapter;
import education.hry.pkl.cricket11.allinterfaces.GetGalleryDetail_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.model.GalleryResponse;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;

public class GalleryActivity extends BaseActivity implements GalleryAdapter.ItemListener, GetGalleryDetail_interface {


    RecyclerView recyclerView;
    ArrayList<GalleryResponse.Datum> arrayList= new ArrayList<GalleryResponse.Datum>();
    String Registration_Id, token;
    Button plantationnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        TextView tv_toolbarTitle = findViewById(R.id.tv_toolbarTitle);
        tv_toolbarTitle.setText("Gallery");
        ImageButton back = findViewById(R.id.back);
        recyclerView =  findViewById(R.id.usertotalplantrv);
        plantationnow = findViewById(R.id.plantationnow);


        try {
            Registration_Id = CSPreferences.readString(this, "User_Id");
            token = CSPreferences.readString(this, "token");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GlobalClass.isNetworkConnected(this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.GalleryDataMethod(this, this, token,this);


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
        recyclerView.setLayoutManager(manager);
        plantationnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });


    }


    @Override
    public void GetGalleryDetail_list(List<GalleryResponse.Datum> list) {

        if (list != null) {

            arrayList.clear();
            arrayList.addAll(list);
            plantationnow.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            GalleryAdapter adaptermain = new GalleryAdapter(this, arrayList, this);
            recyclerView.setAdapter(adaptermain);


        } else {

            plantationnow.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            GlobalClass.dailogwarring(GalleryActivity.this, "No Gallery", "No Gallery found!");

        }


    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void onItemClick(GalleryResponse.Datum item, int currposition) {

    }
}