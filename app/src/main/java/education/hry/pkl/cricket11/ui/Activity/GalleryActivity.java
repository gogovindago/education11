package education.hry.pkl.cricket11.ui.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

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
    ArrayList<GalleryResponse.Datum> arrayList = new ArrayList<GalleryResponse.Datum>();
    String Registration_Id, token;
    Button plantationnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        TextView tv_toolbarTitle = findViewById(R.id.tv_toolbarTitle);
        tv_toolbarTitle.setText("Gallery");
        ImageButton back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.usertotalplantrv);
        plantationnow = findViewById(R.id.plantationnow);


        try {
            Registration_Id = CSPreferences.readString(this, "User_Id");
            token = CSPreferences.readString(this, "token");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GlobalClass.isNetworkConnected(this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.GalleryDataMethod(this, this, token, this);


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

        openDialog(item);

    }

    public void openDialog(GalleryResponse.Datum item) {

/*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Get Pro", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setNegativeButton("No thanks", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        final AlertDialog dialog = builder.create();
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.go_pro_dialog_layout, null);
        dialog.setView(dialogLayout);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.show();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface d) {

                ImageView image = (ImageView) dialog.findViewById(R.id.goProDialogImage);

                Bitmap icon = BitmapFactory.decodeResource(GalleryActivity.this.getResources(),
                        R.drawable.back);

                float imageWidthInPX = (float) image.getWidth();

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Math.round(imageWidthInPX),
                        Math.round(imageWidthInPX * (float) icon.getHeight() / (float) icon.getWidth()));

                image.setLayoutParams(layoutParams);

                Glide.with(GalleryActivity.this).load(item.getPhotoPath())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(image);



            }
        });
    }*/

        final Dialog dialog = new Dialog(this, android.R.style.Theme_Light); // Context, this, etc.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_demo);

       // dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ImageView dialog_img = dialog.findViewById(R.id.dialog_img);

       /* ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(item.getEventImage()))
                .setResizeOptions(new ResizeOptions(150, 150))
                .build();
        dialog_img.setController(

                Fresco.newDraweeControllerBuilder()
                        .setOldController(dialog_img.getController())
                        .setImageRequest(request)
                        .build());
*/
        //dialog_img.setImageURI(Uri.parse(item.getEventImage()));

        Glide.with(this).load(item.getPhotoPath())
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
