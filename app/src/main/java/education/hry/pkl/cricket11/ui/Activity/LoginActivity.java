package education.hry.pkl.cricket11.ui.Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.allinterfaces.GetOtpInterface;
import education.hry.pkl.cricket11.allinterfaces.LoginData_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.model.LoginRequest;
import education.hry.pkl.cricket11.model.LoginRespone;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;
import education.hry.pkl.cricket11.utility.MyLoaders;
import ir.samanjafari.easycountdowntimer.EasyCountDownTextview;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginData_interface, GoogleApiClient.ConnectionCallbacks,
        GetOtpInterface, GoogleApiClient.OnConnectionFailedListener {

    public int counter;
    @TargetApi(Build.VERSION_CODES.O)

    GoogleApiClient mGoogleApiClient;

    private int RESOLVE_HINT = 2;
    TextView generateotp, createaccount, txtforget, lointxt, txtmsg;
    private TextInputEditText edtMobileNumber, txtpaswrd;
    private TextInputEditText edtpass;
    LinearLayout llotpbox;
    private String refreshedToken, userMobileNumber, username, userEmailId, lativale, longivalue, admissionURL, Profilepicurl,Registration_Id;
    private static final String TAG = "LoginActivity";
    private MyLoaders myLoaders;
    EasyCountDownTextview countDownTextview;
    in.aabhasjindal.otptextview.OtpTextView otpBox;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // requestSMSPermission();
        myLoaders = new MyLoaders(getApplicationContext());
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        findViews();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.CREDENTIALS_API)
                .build();

        getHintPhoneNumber();



        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
//                startActivity(i);

            }
        });
    }


    private void findViews() {
        countDownTextview = (EasyCountDownTextview) findViewById(R.id.easyCountDownTextview);
        txtmsg = (TextView) findViewById(R.id.txtmsg);
        txtforget = (TextView) findViewById(R.id.txtforget);
        lointxt = (TextView) findViewById(R.id.lointxt);
        otpBox = (in.aabhasjindal.otptextview.OtpTextView) findViewById(R.id.otp_view);

        generateotp = (TextView) findViewById(R.id.button);
        llotpbox = findViewById(R.id.llotpbox);
        createaccount = (TextView) findViewById(R.id.button2);
        String styledText = "<u><font color='blue'>Create Account</font></u>";
        createaccount.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
        edtMobileNumber = (TextInputEditText) findViewById(R.id.textInputEditText);
        txtpaswrd = (TextInputEditText) findViewById(R.id.txtpaswrd);
        // edtpass = (TextInputEditText) findViewById(R.id.textInputEditText2);
        generateotp.setOnClickListener(this);
        lointxt.setOnClickListener(this);
        txtforget.setOnClickListener(this);
    }

    public boolean Check_Data(View view) {

        if (edtMobileNumber.getText().toString().length() != 10) {
            myLoaders.showSnackBar(view, getString(R.string.tencorrectmobile));
            return false;

        } else if (TextUtils.isEmpty(txtpaswrd.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Password");
            return false;
        }

        return true;
    }


    @Override
    public void onClick(View v) {
        if (v == generateotp) {
            if (Check_Data(v)) {


                userMobileNumber = edtMobileNumber.getText().toString().trim();
                LoginRequest request = new LoginRequest();
                request.setMobile(userMobileNumber);
                request.setFcmToken(refreshedToken);

                if (GlobalClass.isNetworkConnected(LoginActivity.this)) {
                    WebAPiCall webapiCall = new WebAPiCall();

                    Intent intent = new Intent(this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    // webapiCall.loginPostDataMethod(LoginActivity.this, LoginActivity.this, this, request);

                } else {

                    Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }


            }


        } else if (v == lointxt) {


            if (Check_Data(v)) {


                userMobileNumber = edtMobileNumber.getText().toString().trim();
                LoginRequest request = new LoginRequest();
                request.setMobile(userMobileNumber);
                request.setFcmToken(refreshedToken);
                request.setPassword(txtpaswrd.getText().toString().trim());

                if (GlobalClass.isNetworkConnected(LoginActivity.this)) {
                    WebAPiCall webapiCall = new WebAPiCall();

                    webapiCall.loginWithPasswordPostDataMethod(LoginActivity.this, LoginActivity.this, this, request);

                } else {

                    Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }


            }




        } else if (v == txtforget) {



            Intent i = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
            startActivity(i);

        }
    }


    @Override
    public void alluserdata(LoginRespone.Data data) {

        if (!data.getToken().isEmpty()) {


            Registration_Id = data.getId();
            username = data.getName();
            userMobileNumber = data.getMobile();
            userEmailId = data.getEmailId();
//            lativale = Lati;
//            longivalue = Longi;
//            Profilepicurl = Profilepic;
            edtMobileNumber.setEnabled(false);
            boolean firstTimelogin = true;

            CSPreferences.putString(this, "id", Registration_Id);
            CSPreferences.putString(this, "User_Id", Registration_Id);
            CSPreferences.putString(this, "User_mobile", userMobileNumber);
            CSPreferences.putString(this, "User_email", userEmailId);
            CSPreferences.putString(this, "User_name", username);
            CSPreferences.putBolean(this, "firstTimelogin", firstTimelogin);
//            CSPreferences.putString(this, "lativale", lativale);
//            CSPreferences.putString(this, "longivalue", longivalue);
//            CSPreferences.putString(this, "Profilepicurl", Profilepicurl);

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


        } else {
            GlobalClass.showtost(this, "This  Number is Not Registered with Us.");
            edtMobileNumber.setEnabled(true);
            lointxt.setVisibility(View.GONE);
            generateotp.setVisibility(View.VISIBLE);
            llotpbox.setVisibility(View.GONE);
            txtmsg.setVisibility(View.GONE);
        }

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
                    edtMobileNumber.setText(mobilenumberwithcountrycode);

                }
            }
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
        otpBox.setOTP(otp);

    }

    @Override
    public void onOtpTimeout() {

    }


}
