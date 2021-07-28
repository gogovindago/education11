package education.hry.pkl.cricket11.ui.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.databinding.ActivityNotificationDetailBinding;

public class NotificationDetailActivity extends AppCompatActivity {
ActivityNotificationDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_notification_detail);
        binding.toolbar.tvToolbarTitle.setText("Notification Detail");


        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}