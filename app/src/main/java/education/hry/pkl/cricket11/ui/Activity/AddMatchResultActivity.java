package education.hry.pkl.cricket11.ui.Activity;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
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

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
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

import cn.pedant.SweetAlert.SweetAlertDialog;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.SpinnerAllTeamAdapter;
import education.hry.pkl.cricket11.allinterfaces.GetAllTeamList_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityAddMatchResultBinding;
import education.hry.pkl.cricket11.model.AllTeamListResponse;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.FileUtils;
import education.hry.pkl.cricket11.utility.GlobalClass;
import education.hry.pkl.cricket11.utility.ImagePickerActivity;
import education.hry.pkl.cricket11.utility.MyLoaders;
import education.hry.pkl.cricket11.utility.NetworkUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddMatchResultActivity extends BaseActivity implements GetAllTeamList_interface, AdapterView.OnItemSelectedListener {

    ActivityAddMatchResultBinding binding;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int REQUEST_IMAGE = 100;
    private List<AllTeamListResponse.Datum> allteamlist = new ArrayList<AllTeamListResponse.Datum>();
    SpinnerAllTeamAdapter SpinnerAllTeamAdapter;

    int spnOpponentteamCurrentPosition, spnteamdheCurrentPosition = 23, spnmomteamnameCurrentPosition;
    private MyLoaders myLoaders;
    File imagefile;
    String OpponentteamID, teamdhe, momteamId, fcm_MessageTitle, Fcm_MessageBody,
            teamdheName;
    File scorecardfile;
    private int REQUEST_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_match_result);
        myLoaders = new MyLoaders(getApplicationContext());
        loadProfileDefault();

        // Clearing older images from cache directory
        // don't call this line if you want to choose multiple images in the same activity
        // call this once the bitmap(s) usage is over
        ImagePickerActivity.clearCache(this);


        if (NetworkUtil.isConnected(AddMatchResultActivity.this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.allTeamlistMethod(AddMatchResultActivity.this, AddMatchResultActivity.this, "2", AddMatchResultActivity.this, binding.simpleSwipeRefreshLayout);


        } else {
            GlobalClass.showtost(AddMatchResultActivity.this, "No Internet Available.Plz check your internet connection.");
        }


        binding.spnOpponentteam.setOnItemSelectedListener(this);
        binding.spnteamdhe.setOnItemSelectedListener(this);
        binding.spnmomteamname.setOnItemSelectedListener(this);


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {


                if (NetworkUtil.isConnected(AddMatchResultActivity.this)) {

                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.allTeamlistMethod(AddMatchResultActivity.this, AddMatchResultActivity.this, "2", AddMatchResultActivity.this, binding.simpleSwipeRefreshLayout);


                } else {
                    GlobalClass.showtost(AddMatchResultActivity.this, "No Internet Available.Plz check your internet connection.");
                }

                binding.simpleSwipeRefreshLayout.setRefreshing(false);
            }
        });


    }


    private void loadProfile(String url) {
        Log.d(TAG, "Image cache path: " + url);

        Glide.with(this).load(url)
                .into(binding.myImageView);
        binding.myImageView.setColorFilter(ContextCompat.getColor(this, android.R.color.transparent));
    }

    private void loadProfileDefault() {
        Glide.with(this).load(R.mipmap.ic_launcher_round)
                .into(binding.myImageView);
        binding.myImageView.setColorFilter(ContextCompat.getColor(this, R.color.bglight));
    }

    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("Add Match Result");
    }

    @Override
    public void initListeners() {
        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.llAssignment.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                checkpermissions(AddMatchResultActivity.this);
                REQUEST_CODE = 123;
                showFileChooser(REQUEST_CODE);
            }
        });


        binding.edtMatchDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(AddMatchResultActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());


                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);

                                String selectedDate = dateFormat.format(calendar.getTime());


                                binding.edtMatchDate.setText(selectedDate);


                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();

            }
        });


        binding.myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(AddMatchResultActivity.this)
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


        binding.btnaddmatchdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalClass.closeKeyboard(AddMatchResultActivity.this);


                if (Check_Data(v)) {


                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddMatchResultActivity.this);
                    sweetAlertDialog.setTitle("Alert Match Detail Adding !");
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
                            if (NetworkUtil.isConnected(AddMatchResultActivity.this)) {
                                sweetAlertDialog.dismiss();
                                Fcm_MessageBody = teamdheName + "-" + binding.edtdhescore.getText().toString().trim() + "/" + binding.edtdheWicket.getText().toString().trim() + "( " + binding.edtdheover.getText().toString().trim() + ")";

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

                                RequestBody scorecardfilerequestFile = RequestBody.create(MediaType.parse("multipart/form-data"), scorecardfile);
                                MultipartBody.Part scorecardfilebody = MultipartBody.Part.createFormData("ScoreCardFileName", scorecardfile.getName(), scorecardfilerequestFile);

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
                                        imagefilebody, scorecardfilebody);

                            } else {
                                GlobalClass.showtost(AddMatchResultActivity.this, "No Internet Available.Plz check your internet connection.");
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


    }


    public void checkpermissions(Activity context) {
        if (Build.VERSION.SDK_INT >= 23) {
            new TedPermission(context)
                    .setPermissionListener(permissionListener)
                    //.setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(
                            INTERNET,
                            READ_EXTERNAL_STORAGE,
                            WRITE_EXTERNAL_STORAGE

                    )
                    .check();
        }


    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {

        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            checkpermissions(AddMatchResultActivity.this);
        }

    };


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showFileChooser(int REQUEST_CODE) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        // Update with mime types
        intent.setType("*/*");

        String[] mimeTypes = {"application/pdf"};

        // Update with additional mime types here using a String[].
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);

        // Only pick openable and local files. Theoretically we could pull files from google drive
        // or other applications that have networked files, but that's unnecessary for this example.
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

        // REQUEST_CODE = <some-integer>
        startActivityForResult(intent, REQUEST_CODE);
    }


    public boolean Check_Data(View view) {


        if (TextUtils.isEmpty(binding.edtMatchTitle.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Match Title");
            return false;

        } else if (TextUtils.isEmpty(binding.edtMatchDate.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Select Date");
            return false;
        } else if (spnteamdheCurrentPosition == 0) {
            myLoaders.showSnackBar(view, "Please Select Dhe Team");
            return false;
        } else if (TextUtils.isEmpty(binding.edtdhescore.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter DHE Total score");
            return false;
        } else if (TextUtils.isEmpty(binding.edtdheover.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter DHE Total Played Over");
            return false;
        } else if (TextUtils.isEmpty(binding.edtdheWicket.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter DHE Total Wicket Out");
            return false;
        } else if (spnOpponentteamCurrentPosition == 0) {
            myLoaders.showSnackBar(view, "Please Select Opponent Team");
            return false;
        } else if (TextUtils.isEmpty(binding.edtOpponentscore.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Opponent Total score");
            return false;
        } else if (TextUtils.isEmpty(binding.edtOpponentover.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Opponent Total Played Over");
            return false;
        } else if (TextUtils.isEmpty(binding.edtOpponentWicket.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Opponent Total Wicket Out");
            return false;
        } else if (imagefile == null) {
            myLoaders.showSnackBar(view, "Please Select MOM Player Photo");
            return false;
        } else if (TextUtils.isEmpty(binding.edtplayerName.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter MOM Player Name");
            return false;
        } else if (spnmomteamnameCurrentPosition == 0) {
            myLoaders.showSnackBar(view, "Please Select MOM Team");
            return false;
        } else if (TextUtils.isEmpty(binding.edtResultRemarks.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Match Result.");
            return false;
        } else if (scorecardfile == null) {
            myLoaders.showSnackBar(view, "Please Select Score-card Of Match");
            return false;
        }

        return true;
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
        Intent intent = new Intent(AddMatchResultActivity.this, ImagePickerActivity.class);
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
        Intent intent = new Intent(AddMatchResultActivity.this, ImagePickerActivity.class);
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
        } else if (requestCode == 123) {

            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                String path = "";
                int currentVersion = android.os.Build.VERSION.SDK_INT;
                if (currentVersion >= android.os.Build.VERSION_CODES.N) {
                    // Do something for lollipop and above versions
                    path = FileUtils.getFilePathForN(uri, this);
                } else {
                    // do something for phones running an SDK before lollipop
                    path = FileUtils.getPath(this, uri);
                }
                // "file:///mnt/sdcard/FileName.mp3"
                Log.d("PATHS : ", path);
                File file = null;
                try {
                    file = new File(path);
                    scorecardfile = file;
                    // binding.txtupload.setText(imagefile.toString());
                    binding.txtAssignment.setText(scorecardfile.getName());
                    binding.txtAssignment.setTextColor(getResources().getColor(R.color.drkgreeen));

                    binding.attachedpdfAssignment.setVisibility(View.VISIBLE);
                    binding.llAssignment.setBackgroundResource(R.drawable.spinner_bordergreen);

                    Log.d("PDF", file.getAbsolutePath());
                    Log.d("PDF", "" + file.getTotalSpace());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

            }


        }
    }


    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddMatchResultActivity.this);
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


        binding.spnOpponentteam.setAdapter(SpinnerAllTeamAdapter);
        binding.spnteamdhe.setAdapter(SpinnerAllTeamAdapter);
        binding.spnmomteamname.setAdapter(SpinnerAllTeamAdapter);

        binding.spnteamdhe.setSelection(23);
        binding.spnteamdhe.setEnabled(false);
        binding.spnteamdhe.setClickable(false);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {


        int id = parent.getId();
        if (id == R.id.spnOpponentteam) {

            if (position != 0) {

                spnOpponentteamCurrentPosition = position;
                String mystring = allteamlist.get(position).getTeamName();
                OpponentteamID = String.valueOf(allteamlist.get(position).getTeamId());

                String arr[] = mystring.split(" ", 3);

                String firstWord = arr[0];   //the
                String theRest2 = arr[1];     //quick brown fox
                String theRest3 = arr[2];     //quick brown fox
                binding.tlOpponentscore.setHint(firstWord + " " + theRest2 + " Total Score");
                binding.tlOpponentover.setHint(firstWord + " " + theRest2 + " Over played");

                fcm_MessageTitle = teamdheName + " Vs " + mystring;


            } else {
                binding.tlOpponentscore.setHint("Total Score");
                binding.tlOpponentover.setHint("Over played");
                spnOpponentteamCurrentPosition = position;
            }


        } else if (id == R.id.spnteamdhe) {

            if (position != 0) {
                spnteamdheCurrentPosition = position;
                teamdhe = String.valueOf(allteamlist.get(position).getTeamId());
                teamdheName = allteamlist.get(position).getTeamName();


            } else {

                spnteamdheCurrentPosition = position;
            }


        } else if (id == R.id.spnmomteamname) {
            if (position != 0) {
                spnmomteamnameCurrentPosition = position;

                momteamId = String.valueOf(allteamlist.get(position).getTeamId());

            } else {
                spnmomteamnameCurrentPosition = position;
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}