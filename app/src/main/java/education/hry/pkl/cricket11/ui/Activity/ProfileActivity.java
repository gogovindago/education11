package education.hry.pkl.cricket11.ui.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.loader.content.CursorLoader;

import com.bumptech.glide.Glide;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.allinterfaces.StudentProfileData_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityProfileBinding;
import education.hry.pkl.cricket11.model.StudentProfileResponse;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileActivity extends BaseActivity implements View.OnClickListener, StudentProfileData_interface {
    ActivityProfileBinding binding;
    private File imagefile;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;
    String Registration_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        try {


            /* CSPreferences.putString(this, "id", Registration_Id);
            CSPreferences.putString(this, "token", token);
            CSPreferences.putString(this, "User_Id", Registration_Id);
            CSPreferences.putString(this, "User_mobile", userMobileNumber);
            CSPreferences.putString(this, "User_email", userEmailId);
            CSPreferences.putString(this, "User_name", username);
            CSPreferences.putBolean(this, "firstTimelogin", firstTimelogin);*/



            Registration_Id = CSPreferences.readString(ProfileActivity.this, "User_Id");
            binding.edtRegistraionId.setText(Registration_Id);


            String string = CSPreferences.readString(this,"User_name");
            String[] parts = string.split(" ");
            String part1 = parts[0]; // 004
            String part2 = parts[1]; // 034556


            binding.edtfirstname.setText(part1);
            binding.edtlastname.setText(part2);


            binding.edtmobile.setText(CSPreferences.readString(this,"User_mobile"));
            binding.edtemail.setText(CSPreferences.readString(this,"User_email"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GlobalClass.isNetworkConnected(ProfileActivity.this)) {
            WebAPiCall webapiCall = new WebAPiCall();
           // webapiCall.StudentProfileDataMethod(ProfileActivity.this, ProfileActivity.this, this, Registration_Id);

        } else {

            Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

        binding.edit.setOnClickListener(this);
        binding.save.setOnClickListener(this);
        binding.btnUpdate.setOnClickListener(this);
        binding.takePhoto.setOnClickListener(this);

        binding.takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 23) {

                    checkpermissions(ProfileActivity.this);
                }else {
                    selectImage();
                }
            }
        });


        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RequestBody customer_id = RequestBody.create(MediaType.parse("multipart/form-data"), Registration_Id);
                RequestBody imagerequestFile;
                MultipartBody.Part imagebody = null;


                if (imagefile == null) {
                    GlobalClass.showtost(ProfileActivity.this, " You did not select any Photo to change your profile Photo yet, please select your image");

                } else {
                    imagerequestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imagefile);
                    imagebody = MultipartBody.Part.createFormData("imagepath", imagefile.getName(), imagerequestFile);

                    if (GlobalClass.isNetworkConnected(ProfileActivity.this)) {

                        WebAPiCall aPiCall = new WebAPiCall();
                        aPiCall.profilepicPostDataMethod(ProfileActivity.this, ProfileActivity.this, customer_id, imagebody);

                    } else {

                        Toast.makeText(ProfileActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }

                }


            }
        });

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
                binding.edit.setVisibility(View.GONE);
                binding.save.setVisibility(View.VISIBLE);
//                binding.edtfirstname.setEnabled(true);
//                binding.edtlastname.setEnabled(true);
//                binding.edtemail.setEnabled(true);
//                binding.edtmobile.setEnabled(true);
//                binding.edtcourseName.setEnabled(true);
//                binding.edtfirstname.setEnabled(true);

                break;
            case R.id.save:
                // binding.btnUpdate.setVisibility(View.GONE);
                binding.edit.setVisibility(View.VISIBLE);
                binding.save.setVisibility(View.GONE);
                binding.edtfirstname.setEnabled(false);

                break;
            case R.id.btn_update:
                break;
            default:
                break;

        }

    }

    @Override
    public void StudentProfileData(List<StudentProfileResponse.StudentProfile> list) {

        if (list.get(0).getAccountType().equalsIgnoreCase("Student")) {
            binding.edtfacultyDesignation.setVisibility(View.GONE);
            binding.edtfirstname.setText(list.get(0).getFirstName());
            binding.edtlastname.setText(list.get(0).getLastName());
            binding.edtmobile.setText(list.get(0).getMobile());
            binding.edtemail.setText(list.get(0).getEmail());
            binding.edtgender.setText(list.get(0).getGender());
            binding.edtdistrict.setText(list.get(0).getDistrict());
            binding.edtcollegeName.setText(list.get(0).getCollege());
            // binding.edtdistrict.setText(String.valueOf(list.get(0).getDistrictId()));
            // binding.edtcollegeName.setText(String.valueOf(list.get(0).getCollegeId()));
            binding.edtcourseName.setText(list.get(0).getCourseName());
            binding.edtcoursetype.setText(list.get(0).getCourseType());
            binding.edtyearName.setText(list.get(0).getCourseYear());
            binding.txtAccountCreatedat.setText("Account Created at: " + list.get(0).getEntryDate());
            Glide.with(this)
                    .load(list.get(0).getProfilePic()) // image url
                    .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                    .error(R.mipmap.ic_launcher_round)  // any image in case of error
                    .override(140, 140) // resizing
                    .centerCrop()
                    .into(binding.profileImage);
            binding.llstudentdata.setVisibility(View.VISIBLE);


        } else if (list.get(0).getAccountType().equalsIgnoreCase("OTHER")) {


            binding.otherdistricll.setVisibility(View.VISIBLE);
            binding.otherstatell.setVisibility(View.VISIBLE);
            binding.otherprofessionll.setVisibility(View.VISIBLE);

            binding.allll.setVisibility(View.GONE);
            binding.alldesignation.setVisibility(View.GONE);
            binding.allcollege.setVisibility(View.GONE);

            binding.edtfacultyDesignation.setVisibility(View.VISIBLE);
            binding.edtfirstname.setText(list.get(0).getFirstName());
            binding.edtlastname.setText(list.get(0).getLastName());
            binding.edtmobile.setText(list.get(0).getMobile());
            binding.edtemail.setText(list.get(0).getEmail());
            binding.edtgender.setText(list.get(0).getGender());
            binding.otherState.setText(list.get(0).getStateName());
            binding.otherdistrict.setText(list.get(0).getDistrict());
            binding.llstudentdata.setVisibility(View.GONE);
            binding.otherprofession.setText(list.get(0).getProfession());

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

            binding.edtfacultyDesignation.setVisibility(View.VISIBLE);
            binding.edtfirstname.setText(list.get(0).getFirstName());
            binding.edtlastname.setText(list.get(0).getLastName());
            binding.edtmobile.setText(list.get(0).getMobile());
            binding.edtemail.setText(list.get(0).getEmail());
            binding.edtgender.setText(list.get(0).getGender());
            binding.edtdistrict.setText(list.get(0).getDistrict());
            binding.edtcollegeName.setText(list.get(0).getCollege());
            binding.edtfacultyDesignation.setText(list.get(0).getDesignation());
            binding.llstudentdata.setVisibility(View.GONE);

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
}