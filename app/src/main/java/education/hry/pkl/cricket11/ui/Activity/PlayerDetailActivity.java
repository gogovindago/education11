package education.hry.pkl.cricket11.ui.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.PlayerListAdapter;
import education.hry.pkl.cricket11.allinterfaces.GetPlayerListDetail_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityPlayerDetailBinding;
import education.hry.pkl.cricket11.model.DeletePlayerRequest;
import education.hry.pkl.cricket11.model.DeleteTeamDetailsRequest;
import education.hry.pkl.cricket11.model.GalleryResponse;
import education.hry.pkl.cricket11.model.PlayersListResponse;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;
import education.hry.pkl.cricket11.utility.NetworkUtil;

public class PlayerDetailActivity extends BaseActivity implements PlayerListAdapter.ItemListener, GetPlayerListDetail_interface {

    ActivityPlayerDetailBinding binding;

    ArrayList<PlayersListResponse.Datum> arrayList = new ArrayList<PlayersListResponse.Datum>();
    String Registration_Id, token;
    String role;
    private SweetAlertDialog sweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_player_detail);
        role = CSPreferences.readString(PlayerDetailActivity.this, "role");


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

        //  GlobalClass.showtost(PlayerDetailActivity.this, "AAyaa  " + item.getTeamId());

        sweetAlertDialog = new SweetAlertDialog(PlayerDetailActivity.this);
        sweetAlertDialog.setTitle("Alert Player Detail Deleting !");
        sweetAlertDialog.setContentText("Are you sure want to delete this Player?");
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

                if (NetworkUtil.isConnected(PlayerDetailActivity.this)) {
                    sweetAlertDialog.dismiss();

                    DeletePlayerRequest request = new DeletePlayerRequest();
                    request.setPlayer_Id(String.valueOf(item.getPlayerId()));
                    request.setPhoneNumber(String.valueOf(item.getPhoneNumber()));
                    // request.setDeletedBy(CSPreferences.readString(PlayerDetailActivity.this, "User_name"));

                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.DeletePlayerPostDataMethod(PlayerDetailActivity.this, PlayerDetailActivity.this, request);

                } else {
                    GlobalClass.showtost(PlayerDetailActivity.this, "No Internet Available.Plz check your internet connection.");
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




    @Override
    public void onItemZoom(PlayersListResponse.Datum item, int currposition) {
        openDialog(item);
    }


    @Override
    public void GetPlayerListDetail_list(List<PlayersListResponse.Datum> list) {

        if (list != null) {

            arrayList.clear();
            arrayList.addAll(list);
            binding.rvplayer.setVisibility(View.VISIBLE);

            PlayerListAdapter adaptermain = new PlayerListAdapter(this, arrayList, role, this);
            binding.rvplayer.setAdapter(adaptermain);


        } else {

            binding.rvplayer.setVisibility(View.GONE);
            GlobalClass.dailogwarring(PlayerDetailActivity.this, "Empty List", "No any Player found!");

        }

    }


    public void openDialog(PlayersListResponse.Datum item) {


        final Dialog dialog = new Dialog(this, android.R.style.Theme_Light); // Context, this, etc.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_demo);

        //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ImageView dialog_img = dialog.findViewById(R.id.dialog_img);

       /* ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(item.getEventImage()))
                .setResizeOptions(new ResizeOptions(150, 150))
                .build();
        dialog_img.setController(

                Fresco.newDraweeControllerBuilder()
                        .setOldController(dialog_img.getController())
                        .setImageRequest(request)
                        .build());*/

        //dialog_img.setImageURI(Uri.parse(item.getEventImage()));

        Glide.with(this).load(item.getFilePath())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(dialog_img);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialog_ok);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }
}