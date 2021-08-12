package education.hry.pkl.cricket11.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.RecyclerViewAdapter;
import education.hry.pkl.cricket11.adapter.RvNotificationAdapter;
import education.hry.pkl.cricket11.databinding.ActivityNotificationsBinding;
import education.hry.pkl.cricket11.model.DataModel;

public class NotificationsActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemListener, RvNotificationAdapter.ItemListener {
    ArrayList arrayList;
    ActivityNotificationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_notifications);
        binding.toolbar.tvToolbarTitle.setText("Notification");

      binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              onBackPressed();
          }
      });



        arrayList = new ArrayList();
//        arrayList.add(new DataModel("Plantation", R.drawable.notifications, "#4CAF50"));
//        arrayList.add(new DataModel("Admmision", R.drawable.notifications, "#673BB7"));
//        arrayList.add(new DataModel("Result Declare", R.drawable.notifications, "#F94336"));
//        arrayList.add(new DataModel("New Events", R.drawable.notifications, "#4CAF50"));
//        arrayList.add(new DataModel("Item 5", R.drawable.notifications, "#4CAF50"));
//        arrayList.add(new DataModel("Item 2", R.drawable.notifications, "#3E51B1"));
//        arrayList.add(new DataModel("Item 3", R.drawable.notifications, "#673BB7"));
//        arrayList.add(new DataModel("Result Declare", R.drawable.notifications, "#F94336"));
//        arrayList.add(new DataModel("New Events", R.drawable.notifications, "#4CAF50"));
//        arrayList.add(new DataModel("Item 5", R.drawable.notifications, "#4CAF50"));
//        arrayList.add(new DataModel("Item 4", R.drawable.notifications, "#4BAA50"));
//        arrayList.add(new DataModel("Item 1", R.drawable.notifications, "#09A9FF"));
//        arrayList.add(new DataModel("Item 2", R.drawable.notifications, "#3E51B1"));
//        arrayList.add(new DataModel("Item 3", R.drawable.notifications, "#673BB7"));
//        arrayList.add(new DataModel("Item 4", R.drawable.notifications, "#4BAA50"));
//        arrayList.add(new DataModel("Item 5", R.drawable.notifications, "#F94336"));
//        arrayList.add(new DataModel("Item 2", R.drawable.notifications, "#3E51B1"));
//        arrayList.add(new DataModel("Item 3", R.drawable.notifications, "#673BB7"));
//        arrayList.add(new DataModel("Item 4", R.drawable.notifications, "#4BAA50"));
//        arrayList.add(new DataModel("Item 5", R.drawable.notifications, "#F94336"));
//        arrayList.add(new DataModel("Item 6", R.drawable.notifications, "#0A9B88"));

        if (arrayList.isEmpty()){


            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(NotificationsActivity.this);
            sweetAlertDialog.setTitle("Empty Notification tray!");
            sweetAlertDialog.setContentText("Your Notification List is Empty.");
            sweetAlertDialog.setVolumeControlStream(2);
           // sweetAlertDialog.getAlerType();
            sweetAlertDialog.changeAlertType(1);
            sweetAlertDialog.setCanceledOnTouchOutside(false);
            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismiss();
                    finish();
                }
            });
            sweetAlertDialog.show();
        }else {

            RvNotificationAdapter adaptermain = new RvNotificationAdapter(this, arrayList, this);
            binding.recyclerView.setAdapter(adaptermain);


        }



        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        /*AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        binding.recyclerView.setLayoutManager(layoutManager);*/


        /**
         Simple GridLayoutManager that spans two columns
         **/
        LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onItemClick(DataModel item, int currposition) {

        Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
        Intent captureplant = new Intent(this, NotificationDetailActivity.class);
        startActivity(captureplant);
    }


}