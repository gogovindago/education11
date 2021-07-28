package education.hry.pkl.cricket11.ui.Activity;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import cn.pedant.SweetAlert.SweetAlertDialog;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.allinterfaces.GetOtpInterface;
import education.hry.pkl.cricket11.allinterfaces.LoginData_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityForgetPasswordBinding;
import education.hry.pkl.cricket11.model.ForgotPasswordRequest;
import education.hry.pkl.cricket11.model.LoginRequest;
import education.hry.pkl.cricket11.model.LoginRespone;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.GlobalClass;
import education.hry.pkl.cricket11.utility.MyLoaders;

public class ForgetPasswordActivity extends BaseActivity implements LoginData_interface, GoogleApiClient.ConnectionCallbacks,
        GetOtpInterface, GoogleApiClient.OnConnectionFailedListener {
    public int counter;

    ActivityForgetPasswordBinding binding;
    private String userMobileNumber, UserGetOtp;
    private MyLoaders myLoaders;
    @TargetApi(Build.VERSION_CODES.O)

    GoogleApiClient mGoogleApiClient;

    private int RESOLVE_HINT = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_forget_password);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_password);
        myLoaders = new MyLoaders(getApplicationContext());
        // requestSMSPermission();


        // init broadcast receiver
        //  mySMSBroadCastReceiver = new MySMSBroadCastReceiver();

        //set google api client for hint request
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.CREDENTIALS_API)
                .build();

        // get mobile number from phone
        getHintPhoneNumber();
        //start SMS listner
        //  smsListener();

    }

    @Override
    public void initData() {

        binding.toolbar.tvToolbarTitle.setText("Forgot password");


    }

    @Override
    public void initListeners() {

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.generateotpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Check_Data(v)) {


                    userMobileNumber = binding.txtmobile.getText().toString().trim();
                    LoginRequest request = new LoginRequest();
                    request.setMobile(userMobileNumber);


                    if (GlobalClass.isNetworkConnected(ForgetPasswordActivity.this)) {
                        WebAPiCall webapiCall = new WebAPiCall();
                        webapiCall.loginPostDataMethod(ForgetPasswordActivity.this, ForgetPasswordActivity.this, ForgetPasswordActivity.this, request);

                    } else {

                        Toast.makeText(ForgetPasswordActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }


                }


            }
        });


        binding.btnpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Check_Data(v)) {


                    userMobileNumber = binding.txtmobile.getText().toString().trim();
                    ForgotPasswordRequest request = new ForgotPasswordRequest();
                    request.setMobile(userMobileNumber);


                    if (GlobalClass.isNetworkConnected(ForgetPasswordActivity.this)) {
                        WebAPiCall webapiCall = new WebAPiCall();
                        webapiCall.forgotPostDataMethod(ForgetPasswordActivity.this, ForgetPasswordActivity.this, request);

                    } else {

                        Toast.makeText(ForgetPasswordActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }


                }


            }
        });


        binding.verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (binding.otpView.getOTP() == null) {

                    Toast.makeText(ForgetPasswordActivity.this, R.string.plzotp, Toast.LENGTH_LONG).show();

                } else if ((binding.otpView.getOTP().trim().equalsIgnoreCase(UserGetOtp))) {


//                    CSPreferences.putString(this, "id", userMobileNumber);
//                    CSPreferences.putString(this, "User_Id", Registration_Id);
//                    CSPreferences.putString(this, "User_mobile", userMobileNumber);
//                    CSPreferences.putString(this, "User_email", userEmailId);
//                    CSPreferences.putString(this, "User_name", username);
//
//                    CSPreferences.putString(this, "lativale", lativale);
//                    CSPreferences.putString(this, "longivalue", longivalue);
//                    CSPreferences.putString(this, "admissionURL", admissionURL);
//                    CSPreferences.putString(this, "AdmissionportalUrl", AdmissionportalUrl);
//                    CSPreferences.putString(this, "Profilepicurl", Profilepicurl);

                    Intent intent = new Intent(ForgetPasswordActivity.this, ResetPasswordActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                } else {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ForgetPasswordActivity.this);
                    sweetAlertDialog.setTitle("Incorrect Otp!");
                    sweetAlertDialog.setContentText("You have Entered WRONG OTP. Please enter correct OTP.");
                    sweetAlertDialog.setVolumeControlStream(2);
                    sweetAlertDialog.getAlerType();
                    sweetAlertDialog.changeAlertType(1);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    });
                    sweetAlertDialog.show();

                    GlobalClass.showtost(ForgetPasswordActivity.this, "You have Entered WRONG OTP. Please enter correct OTP.");
                }


            }
        });


    }


    public boolean Check_Data(View view) {

        if (binding.txtmobile.getText().toString().length() != 10) {
            myLoaders.showSnackBar(view, getString(R.string.tencorrectmobile));
            return false;

       /* } else if (TextUtils.isEmpty(edtpass.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Password");
            return false;
        }*/
        }
        return true;
    }



    public void smsListener() {

        SmsRetrieverClient mClient = SmsRetriever.getClient(this);
        Task<Void> mTask = mClient.startSmsRetriever();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(ForgetPasswordActivity.this, "SMS Retriever Started", Toast.LENGTH_LONG).show();
            }
        });
        mTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ForgetPasswordActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getHintPhoneNumber() {
        HintRequest hintRequest =
                new HintRequest.Builder()
                        .setPhoneNumberIdentifierSupported(true)
                        .build();
        PendingIntent mIntent = Auth.CredentialsApi.getHintPickerIntent(mGoogleApiClient, hintRequest);
        try {
            startIntentSenderForResult(mIntent.getIntentSender(), RESOLVE_HINT, null, 0, 0, 0);

        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Result if we want hint number
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                    // credential.getId();  <-- will need to process phone number string
                    String mobilenumberwithcountrycode = credential.getId().substring(3);
                    binding.txtmobile.setText(mobilenumberwithcountrycode);

                }
            }
        }
    }


    private void requestSMSPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;

        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;

            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onOtpReceived(String otp) {
        binding.otpView.setOTP(otp);

        if ((binding.otpView.getOTP().trim().equalsIgnoreCase(UserGetOtp))) {


//                    CSPreferences.putString(this, "id", userMobileNumber);
//                    CSPreferences.putString(this, "User_Id", Registration_Id);
//                    CSPreferences.putString(this, "User_mobile", userMobileNumber);
//                    CSPreferences.putString(this, "User_email", userEmailId);
//                    CSPreferences.putString(this, "User_name", username);
//
//                    CSPreferences.putString(this, "lativale", lativale);
//                    CSPreferences.putString(this, "longivalue", longivalue);
//                    CSPreferences.putString(this, "admissionURL", admissionURL);
//                    CSPreferences.putString(this, "AdmissionportalUrl", AdmissionportalUrl);
//                    CSPreferences.putString(this, "Profilepicurl", Profilepicurl);

            Intent intent = new Intent(ForgetPasswordActivity.this, ResetPasswordActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


        }


    }

    @Override
    public void onOtpTimeout() {

    }

    @Override
    public void alluserdata(LoginRespone.Data data) {

    }
}