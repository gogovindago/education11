package education.hry.pkl.cricket11.ui.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.allinterfaces.ResetForget_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityResetPasswordBinding;
import education.hry.pkl.cricket11.model.ForgotPasswordRequest;
import education.hry.pkl.cricket11.model.ResetPasswordRequest;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;
import education.hry.pkl.cricket11.utility.MyLoaders;

public class ResetPasswordActivity extends BaseActivity implements ResetForget_interface {

    ActivityResetPasswordBinding binding;
    private String userMobileNumber,confirmoasswd,Oldpassword, Registration_Id, umobile;
    private MyLoaders myLoaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_forget_password);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        myLoaders = new MyLoaders(getApplicationContext());

        try {
            Registration_Id = CSPreferences.readString(ResetPasswordActivity.this, "User_Id");
            umobile = CSPreferences.readString(ResetPasswordActivity.this, "User_mobile");
            binding.edtmobile.setText(umobile);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initData() {

        binding.toolbar.tvToolbarTitle.setText("Reset password");

    }

    @Override
    public void initListeners() {

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.resetpasswdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Check_Data(v)) {


                    userMobileNumber = binding.edtmobile.getText().toString().trim();
                    confirmoasswd = binding.textInputEditText.getText().toString().trim();
                    Oldpassword = binding.edtOldpassword.getText().toString().trim();

                    ResetPasswordRequest request = new ResetPasswordRequest();
                    request.setMobileNo(userMobileNumber);
                    request.setOldPassword(Oldpassword);
                    request.setPassword(confirmoasswd);


                    if (GlobalClass.isNetworkConnected(ResetPasswordActivity.this)) {
                        WebAPiCall webapiCall = new WebAPiCall();
                        webapiCall.resetforgotPostDataMethod(ResetPasswordActivity.this, ResetPasswordActivity.this, ResetPasswordActivity.this, request);

                    } else {

                        Toast.makeText(ResetPasswordActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }

                }

            }
        });


    }


    public boolean Check_Data(View view) {

        if (binding.edtmobile.getText().toString().length() != 10) {
            myLoaders.showSnackBar(view, getString(R.string.tencorrectmobile));
            return false;

        } else if (TextUtils.isEmpty(binding.edtpassword.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Password");
            return false;
        } else if (TextUtils.isEmpty(binding.textInputEditText.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Confirm Password");
            return false;
        } else if (!binding.edtpassword.getText().toString().trim().equalsIgnoreCase(binding.textInputEditText.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter  same password and Confirm Password");
            return false;
        }

        return true;
    }

    @Override
    public void resetpassword(int statuscode) {

        if (statuscode == 200) {

            CSPreferences.clearPref(ResetPasswordActivity.this);
            Intent intent = new Intent(ResetPasswordActivity.this, WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


        } else {


        }


    }
}