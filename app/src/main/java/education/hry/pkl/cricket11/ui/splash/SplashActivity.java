package education.hry.pkl.cricket11.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.ui.Activity.MainActivity;
import education.hry.pkl.cricket11.ui.Activity.WelcomeActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;


public class SplashActivity extends AppCompatActivity {

    private static int splashTimeOut = 2000;
    private ImageView logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // String auth_key = CSPreferences.readString(SplashActivity.this, "auth_key");
                String auth_key = CSPreferences.readString(SplashActivity.this, "id");
                if (!auth_key.isEmpty()) {
                    // Intent i = new Intent(SplashActivity.this, ExampleActivity.class);
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    // Intent i = new Intent(SplashActivity.this, ExampleActivity.class);
                    //Intent i = new Intent(SplashActivity.this, CapturePlantationActivity.class);
                    //Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
                    startActivity(i);
                    finish();
                }


            }
        }, splashTimeOut);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mysplashanimation);
        logo.startAnimation(myanim);
    }
}
