package education.hry.pkl.cricket11.ui.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.SpinnerAllTeamAdapter;
import education.hry.pkl.cricket11.adapter.SpinnerPlayerRoleAdapter;
import education.hry.pkl.cricket11.allinterfaces.GetAllPlayerRoleList_interface;
import education.hry.pkl.cricket11.allinterfaces.GetAllTeamList_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityRegisterUserBinding;
import education.hry.pkl.cricket11.model.AllTeamListResponse;
import education.hry.pkl.cricket11.model.GetPlayerRoleResponse;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.GlobalClass;
import education.hry.pkl.cricket11.utility.ImagePickerActivity;
import education.hry.pkl.cricket11.utility.MyLoaders;
import education.hry.pkl.cricket11.utility.NetworkUtil;

public class RegisterUserActivity extends BaseActivity implements GetAllTeamList_interface, AdapterView.OnItemSelectedListener, GetAllPlayerRoleList_interface {
    ActivityRegisterUserBinding binding;
    private MyLoaders myLoaders;
    File imagefile;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int REQUEST_IMAGE = 100;

    private List<AllTeamListResponse.Datum> allteamlist = new ArrayList<AllTeamListResponse.Datum>();
    private List<GetPlayerRoleResponse.Datum> allPlayerRolelist = new ArrayList<GetPlayerRoleResponse.Datum>();
    SpinnerAllTeamAdapter SpinnerAllTeamAdapter;
    SpinnerPlayerRoleAdapter roleAdapter;

    int spnteamnameCurrentPosition, spnPlayingRoleCurrentPosition;

    String OpponentteamID, teamdhe, teamId, fcm_MessageTitle, Fcm_MessageBody,
            teamdheName, refreshedToken, AccountType, playerRoleName;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_user);


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            //  Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        refreshedToken = task.getResult();
                        // Log.d("fcm",refreshedToken);
                    }
                });
        myLoaders = new MyLoaders(getApplicationContext());
        loadProfileDefault();

        // Clearing older images from cache directory
        // don't call this line if you want to choose multiple images in the same activity
        // call this once the bitmap(s) usage is over
        ImagePickerActivity.clearCache(this);


        if (NetworkUtil.isConnected(RegisterUserActivity.this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.allTeamlistMethod(RegisterUserActivity.this, RegisterUserActivity.this, "2", RegisterUserActivity.this, binding.simpleSwipeRefreshLayout);
            aPiCall.PlayerRoleListDataMethod(RegisterUserActivity.this, RegisterUserActivity.this, RegisterUserActivity.this);


        } else {
            GlobalClass.showtost(RegisterUserActivity.this, "No Internet Available.Plz check your internet connection.");
        }


//        binding.spnOpponentteam.setOnItemSelectedListener(this);
//        binding.spnteamdhe.setOnItemSelectedListener(this);
        binding.spnteamname.setOnItemSelectedListener(this);
        binding.spnPlayingRole.setOnItemSelectedListener(this);


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {


                if (NetworkUtil.isConnected(RegisterUserActivity.this)) {

                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.allTeamlistMethod(RegisterUserActivity.this, RegisterUserActivity.this, "2", RegisterUserActivity.this, binding.simpleSwipeRefreshLayout);


                } else {
                    GlobalClass.showtost(RegisterUserActivity.this, "No Internet Available.Plz check your internet connection.");
                }

                binding.simpleSwipeRefreshLayout.setRefreshing(false);
            }
        });


    }


    private boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() >= 10 && phone.length() < 11;
            //return phone.length()==10;
        }
        return false;
    }

    public boolean Check_Data(View view) {
        if (!(AccountType == null)) {

            if ((AccountType.equalsIgnoreCase("Player"))) {
                if (TextUtils.isEmpty(binding.edtusername.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Missing First Name", "Please Enter User First Name");
                    return false;
                } else if (TextUtils.isEmpty(binding.edtlastname.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Missing Last Name", "Please Enter User Last Name");

                    return false;
                } else if (TextUtils.isEmpty(binding.edtmobile.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Missing Mobile Number", "Please Enter Mobile Number");

                    return false;
                } else if (!isValidMobile(binding.edtmobile.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Missing 10 digits Mobile Number", "Please Enter 10 digits Mobile Number");

                    return false;

                } else if (TextUtils.isEmpty(binding.edtpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Missing Password", "Please Enter Password");

                    return false;

                } else if (TextUtils.isEmpty(binding.edtconfirmpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Missing Confirm Password", "Please Enter Confirm Password");
                    return false;

                } else if (!binding.edtpass.getText().toString().trim().equals(binding.edtconfirmpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Miss-match Password and Confirm-password", "Your Password and Confirm-password does not match.");

                    return false;

                } else if (!binding.edtemail.getText().toString().trim().matches(emailPattern)) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Missing Email-Id", "Please Enter Correct Email");

                    return false;

                } else if (TextUtils.isEmpty(binding.edtBirthdayDate.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Missing DOB", "Please Enter Date Of Birthday");
                    return false;

                } else if (spnPlayingRoleCurrentPosition == 0) {
                    myLoaders.showSnackBar(view, "Please Select Your Playing Role In Your Team.");
                    return false;
                } else if (spnteamnameCurrentPosition == 0) {
                    myLoaders.showSnackBar(view, "Please Select Your Team.");
                    return false;
                } if (imagefile == null) {
                    myLoaders.showSnackBar(view, "Please Select Your Profile Photo.");
                    return false;
                }

            } else if ((AccountType.equalsIgnoreCase("Guest"))) {
                if (TextUtils.isEmpty(binding.edtusername.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Missing First Name", "Please Enter User First Name");
                    return false;
                } else if (TextUtils.isEmpty(binding.edtlastname.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Missing Last Name", "Please Enter User Last Name");

                    return false;
                } else if (TextUtils.isEmpty(binding.edtmobile.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Missing Mobile Number", "Please Enter Mobile Number");

                    return false;
                } else if (!isValidMobile(binding.edtmobile.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Missing 10 digits Mobile Number", "Please Enter 10 digits Mobile Number");

                    return false;

                } else if (TextUtils.isEmpty(binding.edtpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Missing Password", "Please Enter Password");

                    return false;

                } else if (TextUtils.isEmpty(binding.edtconfirmpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Missing Confirm Password", "Please Enter Confirm Password");
                    return false;

                } else if (!binding.edtpass.getText().toString().trim().equals(binding.edtconfirmpass.getText().toString().trim())) {
                    GlobalClass.dailogError(RegisterUserActivity.this, "Miss-match Password and Confirm-password", "Your Password and Confirm-password does not match.");

                    return false;

                }

            }

            return true;

        } else {

            GlobalClass.dailogError(RegisterUserActivity.this, "Missing User Type", "Please Select Your User Type First.");

            return false;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {


        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Check_Data(view)) {


                    if ((AccountType.equalsIgnoreCase("Player"))) {




                    }else if ((AccountType.equalsIgnoreCase("Guest"))) {




                    }

























                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(RegisterUserActivity.this);
                    sweetAlertDialog.setTitle("Alert Registration Detail Adding !");
                    sweetAlertDialog.setContentText("Make Sure you have filled all detail correctly.");
                    sweetAlertDialog.setVolumeControlStream(2);
                    sweetAlertDialog.setCancelable(true);
                    sweetAlertDialog.setCancelText("No");
                    sweetAlertDialog.setCustomImage(R.mipmap.ic_launcher_round);

                    sweetAlertDialog.changeAlertType(3);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            if (NetworkUtil.isConnected(RegisterUserActivity.this)) {
                                sweetAlertDialog.dismiss();


                                /*   Fcm_MessageBody = teamdheName + "-" + binding.edtdhescore.getText().toString().trim() + "/" + binding.edtdheWicket.getText().toString().trim() + "( " + binding.edtdheover.getText().toString().trim() + ")";

                                RequestBody rq_fcm_MessageTitle = RequestBody.create(MediaType.parse("multipart/form-data"), fcm_MessageTitle);
                                RequestBody rq_Fcm_MessageBody = RequestBody.create(MediaType.parse("multipart/form-data"), Fcm_MessageBody);


                                RequestBody rq_MatchTitle = RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtMatchTitle.getText().toString().trim());
                                RequestBody rq_MatchDate = RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtMatchDate.getText().toString().trim());


                                RequestBody rq_ScoreTeam1 = RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtdhescore.getText().toString().trim());
                                RequestBody rq_OverTeam1 = RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtdheover.getText().toString().trim());
                                RequestBody rq_WicketsTeam1 = RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtdheWicket.getText().toString().trim());


                                RequestBody rq_VersusTeam2Id = RequestBody.create(MediaType.parse("multipart/form-data"), OpponentteamID);
                                RequestBody rq_ScoreTeam2 = RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtOpponentscore.getText().toString().trim());
                                RequestBody rq_OverTeam2 = RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtOpponentover.getText().toString().trim());
                                RequestBody rq_WicketsTeam2 = RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtOpponentWicket.getText().toString().trim());


                                RequestBody rq_ManOfTheMatchTeamId = RequestBody.create(MediaType.parse("multipart/form-data"), momteamId);

                                RequestBody rq_MOMPlayerName = RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtplayerName.getText().toString().trim());

                                RequestBody rq_ResultRemarks = RequestBody.create(MediaType.parse("multipart/form-data"), binding.edtResultRemarks.getText().toString().trim());


                                RequestBody rq_CreatedBy = RequestBody.create(MediaType.parse("multipart/form-data"), CSPreferences.readString(AddMatchResultActivity.this, "User_name"));

                                RequestBody imagefilerequestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imagefile);
                                MultipartBody.Part imagefilebody = MultipartBody.Part.createFormData("FileName", imagefile.getName(), imagefilerequestFile);

                                WebAPiCall aPiCall = new WebAPiCall();
                                aPiCall.addMatchResultPostDataMethod(AddMatchResultActivity.this, AddMatchResultActivity.this, rq_fcm_MessageTitle,
                                        rq_Fcm_MessageBody, rq_MatchTitle,
                                        rq_MatchDate,
                                        rq_ScoreTeam1,
                                        rq_OverTeam1,
                                        rq_WicketsTeam1,
                                        rq_VersusTeam2Id,
                                        rq_ScoreTeam2,
                                        rq_OverTeam2,
                                        rq_WicketsTeam2,
                                        rq_ResultRemarks,
                                        rq_CreatedBy,
                                        rq_MOMPlayerName,
                                        rq_ManOfTheMatchTeamId,
                                        imagefilebody);

                            } else {
                                GlobalClass.showtost(AddMatchResultActivity.this, "No Internet Available.Plz check your internet connection.");
                            }


                        }
                    });*/













































                            } else {
                                GlobalClass.showtost(RegisterUserActivity.this, "No Internet Available.Plz check your internet connection.");
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
            }
        });


        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });


        binding.takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(RegisterUserActivity.this)
                        .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    showImagePickerOptions();
                                }

                                if (report.isAnyPermissionPermanentlyDenied()) {
                                    showSettingsDialog();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();

            }

        });

        binding.edtBirthdayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterUserActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());


                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);

                                String selectedDate = dateFormat.format(calendar.getTime());


                                binding.edtBirthdayDate.setText(selectedDate);


                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();

            }
        });


        binding.GuestTypePic.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                AccountType = "Guest";
                binding.GuestTypePic.setBorderWidth(5);
                binding.GuestTypePic.setBorderColor(Color.parseColor("#228c22"));
                binding.PlayerTypePic.setBorderColor(Color.parseColor("#ffffff"));

                binding.btnRegister.setVisibility(View.VISIBLE);
                binding.cardGuest.setVisibility(View.VISIBLE);
                binding.GuestTypeIcon.setVisibility(View.VISIBLE);

                binding.PlayerTypeIcon.setVisibility(View.GONE);
                binding.materialCardView.setVisibility(View.GONE);
                binding.cardplayer.setVisibility(View.GONE);

            }
        });

        binding.PlayerTypePic.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {


                AccountType = "Player";
                binding.PlayerTypePic.setBorderWidth(5);

                binding.PlayerTypePic.setBorderColor(Color.parseColor("#228c22"));
                binding.GuestTypePic.setBorderColor(Color.parseColor("#ffffff"));


                binding.cardGuest.setVisibility(View.GONE);
                binding.GuestTypeIcon.setVisibility(View.GONE);
                binding.cardGuest.setVisibility(View.VISIBLE);
                binding.PlayerTypeIcon.setVisibility(View.VISIBLE);
                binding.materialCardView.setVisibility(View.VISIBLE);
                binding.cardplayer.setVisibility(View.VISIBLE);
                binding.btnRegister.setVisibility(View.VISIBLE);
            }
        });

    }

    private void loadProfile(String url) {
        Log.d(TAG, "Image cache path: " + url);

        Glide.with(this).load(url)
                .into(binding.profilePic);
        binding.profilePic.setColorFilter(ContextCompat.getColor(this, android.R.color.transparent));
    }

    private void loadProfileDefault() {
        Glide.with(this).load(R.mipmap.ic_launcher_round)
                .into(binding.profilePic);
        binding.profilePic.setColorFilter(ContextCompat.getColor(this, R.color.bglight));
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(RegisterUserActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(RegisterUserActivity.this, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {

                Uri uri = data.getParcelableExtra("path");
                String path = "";

                try {
                    // You can update this bitmap to your server
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    // loading profile image from local cache
                    loadProfile(uri.toString());
                    imagefile = new File(uri.getPath());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUserActivity.this);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }


    @Override
    public void GetAllTeamListDetail_list(List<AllTeamListResponse.Datum> list) {

        allteamlist.clear();
        allteamlist.addAll(list);

        AllTeamListResponse.Datum datum = new AllTeamListResponse.Datum();
        datum.setTeamName("Select Team.");
        datum.setTeamId(0);
        datum.setTeamGroup("A");
        allteamlist.add(0, datum);

        SpinnerAllTeamAdapter = new SpinnerAllTeamAdapter(getApplicationContext(), allteamlist);


        // binding.spnOpponentteam.setAdapter(SpinnerAllTeamAdapter);
        //  binding.spnteamdhe.setAdapter(SpinnerAllTeamAdapter);
        binding.spnteamname.setAdapter(SpinnerAllTeamAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        int id = parent.getId();
        if (id == R.id.spnteamname) {

            if (position != 0) {
                spnteamnameCurrentPosition = position;

                teamId = String.valueOf(allteamlist.get(position).getTeamId());

            } else {
                spnteamnameCurrentPosition = position;
            }

        } else if (id == R.id.spnPlayingRole) {

            if (position != 0) {
                spnPlayingRoleCurrentPosition = position;

                playerRoleName = String.valueOf(allPlayerRolelist.get(position).getPlayerRole());

            } else {
                spnPlayingRoleCurrentPosition = position;
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void GetAllPlayerRoleDetail_list(List<GetPlayerRoleResponse.Datum> list) {


        allPlayerRolelist.clear();
        allPlayerRolelist.addAll(list);

        GetPlayerRoleResponse.Datum playerRolepojo = new GetPlayerRoleResponse.Datum();
        playerRolepojo.setPlayerRole("Select Player Role.");
        playerRolepojo.setId(0);

        allPlayerRolelist.add(0, playerRolepojo);

        roleAdapter = new SpinnerPlayerRoleAdapter(getApplicationContext(), allPlayerRolelist);


        // binding.spnOpponentteam.setAdapter(SpinnerAllTeamAdapter);
        //  binding.spnteamdhe.setAdapter(SpinnerAllTeamAdapter);
        binding.spnPlayingRole.setAdapter(roleAdapter);


    }
}