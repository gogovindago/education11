package education.hry.pkl.cricket11.ui.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.SpinnerLanguageAdapter;
import education.hry.pkl.cricket11.databinding.ActivityWelcomeBinding;
import education.hry.pkl.cricket11.model.DummyData;
import education.hry.pkl.cricket11.utility.BaseActivity;


public class WelcomeActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    ActivityWelcomeBinding binding;
    ArrayList<DummyData> languagelist;
    String SelectedLanguage = "en";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);


        languagelist = new ArrayList<DummyData>();
        for (int i = 0; i <= 3; i++) {
            DummyData dummyData = new DummyData();
            if (i == 0) {
                dummyData.setImage(R.drawable.ic_baseline_language_24);
                dummyData.setName(getString(R.string.selectlanguage));
                languagelist.add(dummyData);
            } else if (i == 1) {
                dummyData.setImage(R.drawable.ic_baseline_language_24);
                dummyData.setName(getString(R.string.english));
                languagelist.add(dummyData);
            }/* else if (i == 2) {
                dummyData.setImage(R.drawable.ic_baseline_language_24);
                dummyData.setName("Hindi");
                languagelist.add(dummyData);
            } else if (i == 3) {
                dummyData.setImage(R.drawable.ic_baseline_language_24);
                dummyData.setName("Haryanvi");
                languagelist.add(dummyData);
            } */ else {

            }
            SpinnerLanguageAdapter spnGenderAdapter = new SpinnerLanguageAdapter(getApplicationContext(), languagelist);
            binding.languageSpinner.setAdapter(spnGenderAdapter);

        }
    }


   /* fun setLocale(lang: String) {
        val myLocale = Locale(lang)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
    }*/


    public String getAppVersion() {
        String versionCode = "";
        try {
            versionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return versionCode;
    }

    @Override
    public void initData() {

        binding.txtversion.setText(" Version :- " + getAppVersion());

    }

    @Override
    public void initListeners() {

        binding.languageSpinner.setOnItemSelectedListener(this);

        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentlogin = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intentlogin);

            }
        });

        binding.btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intentsignup = new Intent(WelcomeActivity.this, SignupActivityold.class);
                Intent intentsignup = new Intent(WelcomeActivity.this, RegisterUserActivity.class);
                startActivity(intentsignup);

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
