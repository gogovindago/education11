package education.hry.pkl.cricket11.ui.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.loader.content.CursorLoader;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.SpinnerPlayerRoleAdapter;
import education.hry.pkl.cricket11.allinterfaces.GetAllPlayerRoleList_interface;
import education.hry.pkl.cricket11.allinterfaces.StudentProfileData_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityProfileBinding;
import education.hry.pkl.cricket11.model.GetPlayerRoleResponse;
import education.hry.pkl.cricket11.model.StudentProfileResponse;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;
import education.hry.pkl.cricket11.utility.MyLoaders;
import education.hry.pkl.cricket11.utility.NetworkUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileActivity extends BaseActivity implements View.OnClickListener, StudentProfileData_interface, GetAllPlayerRoleList_interface, AdapterView.OnItemSelectedListener {
    ActivityProfileBinding binding;
    private File imagefile;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;
    String Registration_Id, Profilepicurl, playerRoleName;

    String
            AccountType,
            UserRole = null,
            PlayerName = null,
            PhoneNumber = null,
            Password = null,
            EmailId = null,
            DOB = null,
            TeamId = null,
            PlayingRole = null,
            User_mobile = null,
            FCMToken = null,
            MessageBody = null,
            MatchTitle = null,
            FileName = null;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private MyLoaders myLoaders;
    private List<GetPlayerRoleResponse.Datum> allPlayerRolelist = new ArrayList<GetPlayerRoleResponse.Datum>();

    SpinnerPlayerRoleAdapter roleAdapter;

    int spnPlayingRoleCurrentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);


        myLoaders = new MyLoaders(getApplicationContext());

        try {


            /* CSPreferences.putString(this, "id", Registration_Id);
            CSPreferences.putString(this, "token", token);
            CSPreferences.putString(this, "User_Id", Registration_Id);
            CSPreferences.putString(this, "User_mobile", userMobileNumber);
            CSPreferences.putString(this, "User_email", userEmailId);
            CSPreferences.putString(this, "User_name", username);
            CSPreferences.putBolean(this, "firstTimelogin", firstTimelogin);*/


            AccountType = CSPreferences.readString(ProfileActivity.this, "role");
            Registration_Id = CSPreferences.readString(ProfileActivity.this, "User_Id");
            binding.edtRegistraionId.setText(Registration_Id);
            Profilepicurl = CSPreferences.readString(this, "Profilepicurl");
            PlayingRole = CSPreferences.readString(this, "PlayingRole");
            User_mobile = CSPreferences.readString(this, "User_mobile");
            String string = CSPreferences.readString(this, "User_name");
            String[] parts = string.split(" ");
            String part1 = parts[0]; // 004
            String part2 = parts[1]; // 034556


            binding.edtfirstname.setText(part1);
            binding.edtlastname.setText(part2);


            if (AccountType.equalsIgnoreCase("Player") || AccountType.equalsIgnoreCase("admin")) {

                binding.llPlayingRole.setVisibility(View.VISIBLE);
                binding.TILPlayingRole.setVisibility(View.VISIBLE);
                binding.TILDOB.setVisibility(View.VISIBLE);

            } else {

                binding.llPlayingRole.setVisibility(View.GONE);
                binding.TILPlayingRole.setVisibility(View.GONE);
                binding.TILDOB.setVisibility(View.GONE);


            }
            binding.edtmobile.setText(CSPreferences.readString(this, "User_mobile"));
            binding.edtemail.setText(CSPreferences.readString(this, "User_email"));
            binding.edtPlayingRole.setText(CSPreferences.readString(this, "PlayingRole"));

            Glide.with(this)
                    .load(Profilepicurl) // image url
                    .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                    .error(R.mipmap.ic_launcher_round)  // any image in case of error
                    .override(140, 140) // resizing
                    .centerCrop()
                    .into(binding.profileImage);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GlobalClass.isNetworkConnected(ProfileActivity.this)) {
            WebAPiCall webapiCall = new WebAPiCall();
            // webapiCall.StudentProfileDataMethod(ProfileActivity.this, ProfileActivity.this, this, Registration_Id);

        } else {

            Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        if (NetworkUtil.isConnected(ProfileActivity.this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.PlayerRoleListDataMethod(ProfileActivity.this, ProfileActivity.this, ProfileActivity.this);


        } else {
            GlobalClass.showtost(ProfileActivity.this, "No Internet Available.Plz check your internet connection.");
        }

        binding.spnPlayingRole.setOnItemSelectedListener(this);


    }


    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

        binding.profileImage.setOnClickListener(this);
        binding.edit.setOnClickListener(this);
        binding.save.setOnClickListener(this);
        binding.btnUpdate.setOnClickListener(this);
        binding.takePhoto.setOnClickListener(this);

        binding.takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 23) {

                    checkpermissions(ProfileActivity.this);
                } else {
                    selectImage();
                }
            }
        });


        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                GlobalClass.closeKeyboard(ProfileActivity.this);


                if (Check_Data(view)) {


                    if ((AccountType.equalsIgnoreCase("Admin")) || (AccountType.equalsIgnoreCase("Player"))) {


                        PlayerName = binding.edtfirstname.getText().toString().trim() + " " + binding.edtlastname.getText().toString().trim();
                        PhoneNumber = binding.edtmobile.getText().toString().trim();
                        // Password = binding.edtc.getText().toString().trim();
                        EmailId = binding.edtemail.getText().toString().trim();
                        DOB = binding.edtBirthdayDate.getText().toString().trim();
                        // TeamId = teamId;
                        PlayingRole = playerRoleName;

                    } else if ((AccountType.equalsIgnoreCase("Player"))) {


                        PlayerName = binding.edtfirstname.getText().toString().trim() + " " + binding.edtlastname.getText().toString().trim();
                        PhoneNumber = binding.edtmobile.getText().toString().trim();
                        // Password = binding.edtc.getText().toString().trim();
                        EmailId = binding.edtemail.getText().toString().trim();
                        DOB = binding.edtBirthdayDate.getText().toString().trim();
                        // TeamId = teamId;
                        PlayingRole = playerRoleName;

                    } else if ((AccountType.equalsIgnoreCase("Guest"))) {


                        PlayerName = binding.edtfirstname.getText().toString().trim() + " " + binding.edtlastname.getText().toString().trim();
                        PhoneNumber = binding.edtmobile.getText().toString().trim();
                        // Password = binding.edtconfirmpass.getText().toString().trim();


                        EmailId = "";
                        DOB = "01/01/1900";
                        TeamId = " ";
                        PlayingRole = " ";


                        FileName = null;
                        imagefile = null;

                    }


                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ProfileActivity.this);
                    sweetAlertDialog.setTitle("Alert Updating Detail Adding !");
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
                            if (NetworkUtil.isConnected(ProfileActivity.this)) {
                                sweetAlertDialog.dismiss();
/*DOB
playerRole
Emailld
PhoneNumber
Player_ld
FileName*/
                                RequestBody rq_DOB = RequestBody.create(MediaType.parse("multipart/form-data"), DOB);
                                RequestBody rq_PlayingRole = RequestBody.create(MediaType.parse("multipart/form-data"), playerRoleName);//PlayingRole
                                RequestBody rq_EmailId = RequestBody.create(MediaType.parse("multipart/form-data"), EmailId);
                                RequestBody rq_PhoneNumber = RequestBody.create(MediaType.parse("multipart/form-data"), User_mobile);
                                RequestBody rq_Registration_Id = RequestBody.create(MediaType.parse("multipart/form-data"), Registration_Id);

                                RequestBody imagefilerequestFile;
                                MultipartBody.Part imagefilebody = null;

                                if (imagefile == null) {
                                    //GlobalClass.showtost(SignupActivity.this, "Select your image");
                                    imagefilebody = null;
                                } else {


                                    try {
                                        imagefilerequestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imagefile);
                                        imagefilebody = MultipartBody.Part.createFormData("FileName", imagefile.getName(), imagefilerequestFile);
                                    } catch (Exception e) {

                                        e.printStackTrace();
                                    }
                                }



                                /*DOB
playerRole
Emailld
PhoneNumber
Player_ld
FileName*/

                                WebAPiCall aPiCall = new WebAPiCall();
                                aPiCall.profilepicPostDataMethod(ProfileActivity.this, ProfileActivity.this,
                                        rq_DOB,
                                        rq_PlayingRole,
                                        rq_EmailId,
                                        rq_PhoneNumber,
                                        rq_Registration_Id,
                                        imagefilebody);


                            } else {
                                GlobalClass.showtost(ProfileActivity.this, "No Internet Available.Plz check your internet connection.");
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

               /* RequestBody customer_id = RequestBody.create(MediaType.parse("multipart/form-data"), Registration_Id);
                RequestBody imagerequestFile;
                MultipartBody.Part imagebody = null;


                if (imagefile == null) {
                    GlobalClass.showtost(ProfileActivity.this, " You did not select any Photo to change your profile Photo yet, please select your image");

                } else {


                    imagerequestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imagefile);
                    imagebody = MultipartBody.Part.createFormData("imagepath", imagefile.getName(), imagerequestFile);

                    if (GlobalClass.isNetworkConnected(ProfileActivity.this)) {

                        WebAPiCall aPiCall = new WebAPiCall();
                        // aPiCall.profilepicPostDataMethod(ProfileActivity.this, ProfileActivity.this, customer_id, imagebody);

                    } else {

                        Toast.makeText(ProfileActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }

                }*/


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


                DatePickerDialog datePickerDialog = new DatePickerDialog(ProfileActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());


                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);

                                String selectedDate = dateFormat.format(calendar.getTime());


                                binding.edtBirthdayDate.setText(selectedDate);


                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();

            }
        });


    }


    public boolean Check_Data(View view) {
        if (!(AccountType == null)) {

            if ((AccountType.equalsIgnoreCase("Player")) || (AccountType.equalsIgnoreCase("Admin"))) {
                if (TextUtils.isEmpty(binding.edtfirstname.getText().toString().trim())) {
                    GlobalClass.dailogError(ProfileActivity.this, "Missing First Name", "Please Enter User First Name");
                    return false;
                } else if (TextUtils.isEmpty(binding.edtlastname.getText().toString().trim())) {
                    GlobalClass.dailogError(ProfileActivity.this, "Missing Last Name", "Please Enter User Last Name");

                    return false;
                } else if (TextUtils.isEmpty(binding.edtmobile.getText().toString().trim())) {
                    GlobalClass.dailogError(ProfileActivity.this, "Missing Mobile Number", "Please Enter Mobile Number");

                    return false;
                } else if (!isValidMobile(binding.edtmobile.getText().toString().trim())) {
                    GlobalClass.dailogError(ProfileActivity.this, "Missing 10 digits Mobile Number", "Please Enter 10 digits Mobile Number");

                    return false;

                } else if (!binding.edtemail.getText().toString().trim().matches(emailPattern)) {
                    GlobalClass.dailogError(ProfileActivity.this, "Missing Email-Id", "Please Enter Correct Email");

                    return false;

                } else if (TextUtils.isEmpty(binding.edtBirthdayDate.getText().toString().trim())) {
                    GlobalClass.dailogError(ProfileActivity.this, "Missing DOB", "Please Enter Date Of Birthday");
                    return false;

                } else if (spnPlayingRoleCurrentPosition == 0) {
                    myLoaders.showSnackBar(view, "Please Select Your Playing Role In Your Team.");
                    return false;
                }
                if (imagefile == null) {
                    myLoaders.showSnackBar(view, "Please Select Your Profile Photo.");
                    return false;
                }

            } else if ((AccountType.equalsIgnoreCase("Guest"))) {
                if (TextUtils.isEmpty(binding.edtfirstname.getText().toString().trim())) {
                    GlobalClass.dailogError(ProfileActivity.this, "Missing First Name", "Please Enter User First Name");
                    return false;
                } else if (TextUtils.isEmpty(binding.edtlastname.getText().toString().trim())) {
                    GlobalClass.dailogError(ProfileActivity.this, "Missing Last Name", "Please Enter User Last Name");

                    return false;
                } else if (TextUtils.isEmpty(binding.edtmobile.getText().toString().trim())) {
                    GlobalClass.dailogError(ProfileActivity.this, "Missing Mobile Number", "Please Enter Mobile Number");

                    return false;
                } else if (!isValidMobile(binding.edtmobile.getText().toString().trim())) {
                    GlobalClass.dailogError(ProfileActivity.this, "Missing 10 digits Mobile Number", "Please Enter 10 digits Mobile Number");

                    return false;

                } else if (!binding.edtemail.getText().toString().trim().matches(emailPattern)) {
                    GlobalClass.dailogError(ProfileActivity.this, "Missing Email-Id", "Please Enter Correct Email");

                    return false;

                }

            }

            return true;

        } else {

            GlobalClass.dailogError(ProfileActivity.this, "Missing User Type", "Please Select Your User Type First.");

            return false;
        }
    }


    private boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() >= 10 && phone.length() < 11;
            //return phone.length()==10;
        }
        return false;
    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {

            selectImage();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            checkpermissions(ProfileActivity.this);


        }

    };

    public void checkpermissions(Activity context) {
        if (Build.VERSION.SDK_INT >= 23) {

            new TedPermission(context)
                    .setPermissionListener(permissionListener)
                    //.setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(
                            android.Manifest.permission.INTERNET,
                            android.Manifest.permission.CAMERA,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE

                    )
                    .setGotoSettingButton(true)
                    .check();

        }
    }


    // Select image from camera and gallery
    private void selectImage() {
        try {
            PackageManager pm = getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA);
                        } else if (options[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (this.PICK_IMAGE_GALLERY == requestCode && resultCode == RESULT_OK) {


            Uri picUri = data.getData();
            imagefile = new File(getPaths(picUri));
            Log.d("imagegallary", String.valueOf(imagefile));
            binding.profileImage.setImageURI(picUri);

        }
        if (requestCode == PICK_IMAGE_CAMERA && resultCode == Activity.RESULT_OK && data != null) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            binding.profileImage.setImageBitmap(photo);
            Uri tempUri = getImageUri(this, photo);
            imagefile = new File(getRealPathFromURI(tempUri));
            Log.d("imagecamera", String.valueOf(imagefile));

        }

    }


    private String getPaths(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }


    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {


        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String inActiveDate = null;
        inActiveDate = format1.format(date);
        System.out.println(inActiveDate);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        //String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title"+Calendar.getInstance().getTime(), null);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title" + inActiveDate, null);
        return Uri.parse(path);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.edit:
                // binding.btnUpdate.setVisibility(View.VISIBLE);

                if (AccountType.equalsIgnoreCase("Player") || AccountType.equalsIgnoreCase("admin")) {

                    binding.llPlayingRole.setVisibility(View.VISIBLE);
                    binding.TILPlayingRole.setVisibility(View.VISIBLE);
                    binding.TILDOB.setVisibility(View.VISIBLE);

                    binding.edtfirstname.setEnabled(true);
                    binding.edtlastname.setEnabled(true);
                    binding.edtemail.setEnabled(true);
                    binding.edtmobile.setEnabled(true);
                    binding.edtBirthdayDate.setEnabled(true);

                } else {

                    binding.llPlayingRole.setVisibility(View.GONE);
                    binding.TILPlayingRole.setVisibility(View.GONE);
                    binding.TILDOB.setVisibility(View.GONE);

                    binding.edtfirstname.setEnabled(true);
                    binding.edtlastname.setEnabled(true);
                    binding.edtemail.setEnabled(true);
                    binding.edtmobile.setEnabled(true);
                    binding.edtBirthdayDate.setEnabled(true);


                }


                binding.edit.setVisibility(View.GONE);
                binding.save.setVisibility(View.VISIBLE);


                break;
            case R.id.save:
                // binding.btnUpdate.setVisibility(View.GONE);
                binding.edit.setVisibility(View.VISIBLE);
                binding.save.setVisibility(View.GONE);
                binding.edtfirstname.setEnabled(false);
                binding.edtlastname.setEnabled(false);
                binding.edtemail.setEnabled(false);
                binding.edtmobile.setEnabled(false);
                binding.edtBirthdayDate.setEnabled(false);


                break;
            case R.id.btn_update:
                break;


            case R.id.profile_image:

                openDialog();
                break;
            default:
                break;

        }

    }


    public void openDialog() {


        final Dialog dialog = new Dialog(this, android.R.style.Theme_Light); // Context, this, etc.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_demo);

        //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ImageView dialog_img = dialog.findViewById(R.id.dialog_img);

       /* ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(item.getEventImage()))
                .setResizeOptions(new ResizeOptions(150, 150))
                .build();
        dialog_img.setController(

                Fresco.newDraweeControllerBuilder()
                        .setOldController(dialog_img.getController())
                        .setImageRequest(request)
                        .build());*/

        //dialog_img.setImageURI(Uri.parse(item.getEventImage()));

        Glide.with(this).load(Profilepicurl)
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


    @Override
    public void StudentProfileData(List<StudentProfileResponse.StudentProfile> list) {

        if (list.get(0).getAccountType().equalsIgnoreCase("Student")) {

            binding.edtfirstname.setText(list.get(0).getFirstName());
            binding.edtlastname.setText(list.get(0).getLastName());
            binding.edtmobile.setText(list.get(0).getMobile());
            binding.edtemail.setText(list.get(0).getEmail());

            // binding.edtdistrict.setText(String.valueOf(list.get(0).getDistrictId()));
            // binding.edtcollegeName.setText(String.valueOf(list.get(0).getCollegeId()));

            binding.txtAccountCreatedat.setText("Account Created at: " + list.get(0).getEntryDate());
            Glide.with(this)
                    .load(list.get(0).getProfilePic()) // image url
                    .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                    .error(R.mipmap.ic_launcher_round)  // any image in case of error
                    .override(140, 140) // resizing
                    .centerCrop()
                    .into(binding.profileImage);


        } else if (list.get(0).getAccountType().equalsIgnoreCase("OTHER")) {


            binding.edtfirstname.setText(list.get(0).getFirstName());
            binding.edtlastname.setText(list.get(0).getLastName());
            binding.edtmobile.setText(list.get(0).getMobile());
            binding.edtemail.setText(list.get(0).getEmail());


            // binding.edtcollegeName.setText(list.get(0).getCollege());
            // binding.edtdistrict.setText(String.valueOf(list.get(0).getDistrictId()));
            // binding.edtcollegeName.setText(String.valueOf(list.get(0).getCollegeId()));
            // binding.edtcourseName.setText(list.get(0).getCourseName());
            // binding.edtcoursetype.setText(list.get(0).getCourseType());
            // binding.edtyearName.setText(list.get(0).getCourseYear());
            binding.txtAccountCreatedat.setText("Account Created at: " + list.get(0).getEntryDate());

            Glide.with(this)
                    .load(list.get(0).getProfilePic())
                    .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                    .error(R.mipmap.ic_launcher_round)  // any image in case of error
                    .override(140, 140) // resizing
                    .centerCrop()
                    .into(binding.profileImage);


        } else if (list.get(0).getAccountType().equalsIgnoreCase("Faculty")) {

            binding.edtfirstname.setText(list.get(0).getFirstName());
            binding.edtlastname.setText(list.get(0).getLastName());
            binding.edtmobile.setText(list.get(0).getMobile());
            binding.edtemail.setText(list.get(0).getEmail());


            // binding.edtdistrict.setText(String.valueOf(list.get(0).getDistrictId()));
            // binding.edtcollegeName.setText(String.valueOf(list.get(0).getCollegeId()));
            // binding.edtcourseName.setText(list.get(0).getCourseName());
            //  binding.edtcoursetype.setText(list.get(0).getCourseType());
            //  binding.edtyearName.setText(list.get(0).getCourseYear());
            binding.txtAccountCreatedat.setText("Account Created at: " + list.get(0).getEntryDate());
            Glide.with(this)
                    .load(list.get(0).getProfilePic()) // image url
                    .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                    .error(R.mipmap.ic_launcher_round)  // any image in case of error
                    .override(140, 140) // resizing
                    .centerCrop()
                    .into(binding.profileImage);


        }

//        binding.edtfirstname.setText(list.get(0).getFirstName());
//        binding.edtlastname.setText(list.get(0).getLastName());
//        binding.edtmobile.setText(list.get(0).getMobile());
//        binding.edtemail.setText(list.get(0).getEmail());
//        binding.edtgender.setText(list.get(0).getGender());
//        binding.edtdistrict.setText(list.get(0).getDistrict());
//        binding.edtcollegeName.setText(list.get(0).getCollege());
//       // binding.edtdistrict.setText(String.valueOf(list.get(0).getDistrictId()));
//       // binding.edtcollegeName.setText(String.valueOf(list.get(0).getCollegeId()));
//        binding.edtcourseName.setText(list.get(0).getCourseName());
//        binding.edtcoursetype.setText(list.get(0).getCourseType());
//        binding.edtyearName.setText(list.get(0).getCourseYear());
//        binding.txtAccountCreatedat.setText("Account Created at: "+list.get(0).getEntryDate());


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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        int id = parent.getId();

        if (id == R.id.spnPlayingRole) {

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
}