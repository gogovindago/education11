package education.hry.pkl.cricket11.apicall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Objects;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.allinterfaces.BannerData_interface;
import education.hry.pkl.cricket11.allinterfaces.GetCareerStatistcsDetail_interface;
import education.hry.pkl.cricket11.allinterfaces.GetGalleryDetail_interface;
import education.hry.pkl.cricket11.allinterfaces.GetPlayerListDetail_interface;
import education.hry.pkl.cricket11.allinterfaces.LoginData_interface;
import education.hry.pkl.cricket11.allinterfaces.ResetForget_interface;
import education.hry.pkl.cricket11.allinterfaces.StudentProfileData_interface;
import education.hry.pkl.cricket11.model.BannerResponse;
import education.hry.pkl.cricket11.model.CareerStatisticsResponse;
import education.hry.pkl.cricket11.model.ForgotPasswordRequest;
import education.hry.pkl.cricket11.model.ForgotPasswordResponse;
import education.hry.pkl.cricket11.model.GalleryResponse;
import education.hry.pkl.cricket11.model.LoginRequest;
import education.hry.pkl.cricket11.model.LoginRespone;
import education.hry.pkl.cricket11.model.PlayersListResponse;
import education.hry.pkl.cricket11.model.ProfilePicSaveResponse;
import education.hry.pkl.cricket11.model.StudentProfileResponse;
import education.hry.pkl.cricket11.retrofitinterface.ApiClient;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebAPiCall {

    ProgressDialog pDialog;

    public void dailogsuccessWithActivity(final Context context, final Activity activity, String msgtitle, String msgcontentText) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // set the custom layout
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.customviewsuccess, null);
        builder.setView(dialogView);


        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        TextView txtsuccess = dialogView.findViewById(R.id.txtsuccess);
        TextView txtmsg = dialogView.findViewById(R.id.txtmsg);
        txtsuccess.setText(msgtitle + " ! ");
        txtmsg.setText(msgcontentText);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                activity.finish();
            }
        });
        alertDialog.show();


//
//        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
//        sweetAlertDialog.setTitle(msgtitle + "!");
//        sweetAlertDialog.setContentText(msgcontentText);
//        sweetAlertDialog.setVolumeControlStream(2);
//        sweetAlertDialog.changeAlertType(2);
//        sweetAlertDialog.setCancelable(false);
//
//        sweetAlertDialog.setCanceledOnTouchOutside(false);
//        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//            @Override
//            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                sweetAlertDialog.dismissWithAnimation();
//                activity.finish();
//            }
//        });
//        sweetAlertDialog.show();

    }

    public void dailogsuccess(final Activity activity, String msgtitle, String msgcontentText) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // set the custom layout
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.customviewsuccess, null);
        builder.setView(dialogView);


        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        TextView txtsuccess = dialogView.findViewById(R.id.txtsuccess);
        TextView txtmsg = dialogView.findViewById(R.id.txtmsg);
        txtsuccess.setText(msgtitle + " ! ");
        txtmsg.setText(msgcontentText);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                activity.finish();
            }
        });
        alertDialog.show();

       /* SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);

        sweetAlertDialog.setCancelable(false);

        sweetAlertDialog.changeAlertType(2);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        sweetAlertDialog.show();*/

    }

    public void dailogErrorFinish(final Context context, final Activity activity, String msgtitle, String msgcontentText) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // set the custom layout
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.customviewerror, null);
        builder.setView(dialogView);


        Button buttonOk = dialogView.findViewById(R.id.btnerrorOk);
        TextView txtsuccess = dialogView.findViewById(R.id.txterrortitle);
        TextView txtmsg = dialogView.findViewById(R.id.txterrormsg);
        txtsuccess.setText(msgtitle + " ! ");
        txtmsg.setText(msgcontentText);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                activity.finish();
            }
        });
        alertDialog.show();



      /*  SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);

        sweetAlertDialog.changeAlertType(1);
        sweetAlertDialog.setCancelable(false);

        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                activity.finish();

            }
        });
        sweetAlertDialog.show();*/

    }


    public void dailogError(final Activity activity, String msgtitle, String msgcontentText) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // set the custom layout
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.customviewerror, null);
        builder.setView(dialogView);


        Button buttonOk = dialogView.findViewById(R.id.btnerrorOk);
        TextView txtsuccess = dialogView.findViewById(R.id.txterrortitle);
        TextView txtmsg = dialogView.findViewById(R.id.txterrormsg);
        txtsuccess.setText(msgtitle + " ! ");
        txtmsg.setText(msgcontentText);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });
        alertDialog.show();



      /*  SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);

        sweetAlertDialog.changeAlertType(1);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        sweetAlertDialog.show();
*/
    }


    public void loadershowwithMsg(final Context context, String msg) {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage(msg);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void dailoghide(final Context context) {
        pDialog.dismiss();

    }


    public void allBanner_listMethod(final Context context, final Activity activity, final BannerData_interface dataInterface, SwipeRefreshLayout mSwipeRefreshLayout) {

        //loadershowwithMsg(context, "Getting All Notices...");
        mSwipeRefreshLayout.setRefreshing(true);

        Call<BannerResponse> userpost_responseCall = ApiClient.getClient().getBannnerAPi();
        userpost_responseCall.enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
                // dailoghide(context);
                mSwipeRefreshLayout.setRefreshing(false);


                if (response.isSuccessful() && Objects.requireNonNull(response.body()).getResponse() == 200) {


                  /*  if (response.body().getData().getNewsCode() == 1) {

                        dailogErrorFinish(activity, activity, " Cricket 11 ", response.body().getData().getMessage());


                    } else if (response.body().getData().getNewsCode() == 2) {

                        dailogsuccess(activity, " Cricket 11 ", response.body().getData().getMessage());

                    }*/
                    // GlobalClass.showtost(context, "" + response.message());

                    dataInterface.allBanner_list((ArrayList<BannerResponse.Banner>) response.body().getData().getBanners());
                    dataInterface.allhigherauthority_list((ArrayList<BannerResponse.DashboardOfficer>) response.body().getData().getDashboardOfficers());
                    dataInterface.allrecentmatches_list((ArrayList<BannerResponse.MatchDetail>) response.body().getData().getMatchDetails());
                    dataInterface.allTopbattingAvg_list((ArrayList<BannerResponse.TopBat>) response.body().getData().getTopBat());
                    dataInterface.allTopBowlingAvg_list((ArrayList<BannerResponse.TopBowl>) response.body().getData().getTopBowl());
                    dataInterface.allMostWicket_list((ArrayList<BannerResponse.MostWicket>) response.body().getData().getMostWicket());
                    dataInterface.allMostRun_list((ArrayList<BannerResponse.MostRun>) response.body().getData().getMostRun());
                    dataInterface.allMostSixes_list((ArrayList<BannerResponse.MostSix>) response.body().getData().getMostSix());
                    dataInterface.allMostFours_list((ArrayList<BannerResponse.MostFour>) response.body().getData().getMostFour());
                } else {
                    GlobalClass.showtost(activity, "" + response.message());
                }


            }

            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {

                // dailoghide(context);
                mSwipeRefreshLayout.setRefreshing(false);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void profilepicPostDataMethod(final Activity activity, final Context context, RequestBody registration_id, MultipartBody.Part image) {

        loadershowwithMsg(context, "Your profile photo uploading is going on...");

        Call<ProfilePicSaveResponse> userpost_responseCall = ApiClient.getClient().userProfilePicUploading(registration_id, image);

        userpost_responseCall.enqueue(new Callback<ProfilePicSaveResponse>() {
            @Override
            public void onResponse(Call<ProfilePicSaveResponse> call, Response<ProfilePicSaveResponse> response) {
                dailoghide(context);
                if (response.code() == 200) {
                    if (response.body().getResponse() == 200) {
                        CSPreferences.putString(activity, "Profilepicurl", response.body().getData());
                        dailogsuccessWithActivity(context, activity, "Profile photo uploading successful", "profile photo uploading process completed successful.");
                    } else {

                        dailogError(activity, "Error!", "Server is busy,profile photo uploading process failed!,Please try after sometimes");
                    }


                } else {
                    //  GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ProfilePicSaveResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void loginPostDataMethod(final Activity activity, final Context context, final LoginData_interface loginData_interface, LoginRequest request) {

        loadershowwithMsg(context, "We are veryfing your Mobile  to  send an OTP for forward application.");

        Call<LoginRespone> userpost_responseCall = ApiClient.getClient().LoginUser(request);
        userpost_responseCall.enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        dailogsuccess(activity, "OTP Sent.", "Please wait for OTP.");


                        loginData_interface.alluserdata(response.body().getData());


                    } else {
                        // GlobalClass.showtost(context, "This  Number is Not Registered with Us.");
                        dailogError(activity, "Mobile Number Not Found!", "This  Number is Not Registered with Us.");

                    }

                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void forgotPostDataMethod(final Activity activity, final Context context, ForgotPasswordRequest request) {

        loadershowwithMsg(context, "We are veryfing your Mobile to send password.");

        Call<ForgotPasswordResponse> userpost_responseCall = ApiClient.getClient().forgotPassword(request);
        userpost_responseCall.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getStatusCode() == 200) {

                        dailogsuccessWithActivity(context, activity, "Password Sent.", "Please check your mobile for 5 digits auto generated password, for login.");


                        //  loginData_interface.alluserdata(response.body().getOTP(), response.body().getRegistrationId(), response.body().getStundentName(), response.body().getMobile(), response.body().getEmail(), response.body().getLatitude(), response.body().getLongitude(), response.body().getAdmissionportalUrl(), response.body().getProfilePic());


                    } else {
                        // GlobalClass.showtost(context, "This  Number is Not Registered with Us.");
                        dailogError(activity, "Mobile Number Not Found!", "This  Number is Not Registered with Us.");

                    }

                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void resetforgotPostDataMethod(final Activity activity, final Context context, ResetForget_interface resetForget_interface, ForgotPasswordRequest request) {

        loadershowwithMsg(context, "We are veryfing your Mobile to Reset-password.");

        Call<ForgotPasswordResponse> userpost_responseCall = ApiClient.getClient().ResetforgotPassword(request);
        userpost_responseCall.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getStatusCode() == 200) {

                        //dailogsuccessWithActivity(context, activity, "Password Re-Set Done.", "Successfully Reset your Password.");
                        GlobalClass.showtost(context, "Password Reset Done.\n Successfully Reset your Password.");


                        resetForget_interface.resetpassword(response.body().getStatusCode());

                    } else {
                        // GlobalClass.showtost(context, "This  Number is Not Registered with Us.");
                        dailogError(activity, "Mobile Number Not Found!", "This  Number is Not Registered with Us.");

                    }

                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void loginWithPasswordPostDataMethod(final Activity activity, final Context context, final LoginData_interface loginData_interface, LoginRequest request) {

        loadershowwithMsg(context, "We are veryfing your Credentials for login.");

        Call<LoginRespone> userpost_responseCall = ApiClient.getClient().LoginWithPasswordUser(request);
        userpost_responseCall.enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        // dailogsuccess(context, "OTP Sent.", "Please wait for OTP.");


                        loginData_interface.alluserdata(response.body().getData());


                    } else {
                        // GlobalClass.showtost(context, "This  Number is Not Registered with Us.");
                        dailogError(activity, "Credentials Not match!", "This Mobile Number or password is incorrect.");

                    }

                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void StudentProfileDataMethod(final Activity activity, final Context context, final StudentProfileData_interface studentProfileData_interface, String Student_Reg_Id) {

        loadershowwithMsg(context, "We are Fetching your Data from server.");

        Call<StudentProfileResponse> studentProfileResponseCall = ApiClient.getClient().STUDENT_PROFILE_DATA_RESPONSE_CALL(Student_Reg_Id);
        studentProfileResponseCall.enqueue(new Callback<StudentProfileResponse>() {
            @Override
            public void onResponse(Call<StudentProfileResponse> call, Response<StudentProfileResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    // if (response.body().getStatusCode() == 200) {

                    //dailogsuccess(context, "OTP Sent.", "Please wait for OTP.");


                    studentProfileData_interface.StudentProfileData(response.body().getStudentProfile());

                    // GlobalClass.showtost(context, "This  Number is Not Registered with Us.");
                  /*  new SweetAlertDialog(context)
                            .setTitleText(response.message())
                            .show();*/

//                        Intent intent = new Intent(context, MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);

                   /* if (response.body().getStatus().equals("1")) {
                        // CSPreferences.putString(context,"auth_key",response.body().getProfile().getSessionId());
                        Intent intent2 = new Intent(context, AlloptionActivity.class);
                        context.startActivity(intent2);


                    } else {
                        // CSPreferences.putString(context,"auth_key",response.body().getProfile().getSessionId());

                        // Intent intent = new Intent(context, EmpLoginView.class);
                        // context.startActivity(intent);
                    }*/




                /*    CSPreferences.putString(context,"auth_key",response.body().getData().getAuthKey());
                    CSPreferences.putString(context,"id",response.body().getData().getUser().getId().toString());
                    CSPreferences.putString(context,"name",response.body().getData().getUser().getName());
                    CSPreferences.putString(context,"last_name",response.body().getData().getUser().getLastName());
                    CSPreferences.putString(context,"email",response.body().getData().getUser().getEmail());
                    CSPreferences.putString(context,"type",response.body().getData().getUser().getType());
     */            // CSPreferences.putString(context,"otp",response.body().getData().getUser().getOtp().toString());


//                    } else {
//                        // GlobalClass.showtost(context, "This  Number is Not Registered with Us.");
//                        dailogError(context, "Mobile Number Not Found!", "This  Number is Not Registered with Us.");
//
//                    }

                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<StudentProfileResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void GalleryDataMethod(final Activity activity, final Context context, String token, GetGalleryDetail_interface anInterface) {

        loadershowwithMsg(context, "Fetching all Photos...");

        Call<GalleryResponse> apiCall =
                ApiClient.getClient().GalleryApiCall("Bearer " + token);
        apiCall.enqueue(new Callback<GalleryResponse>() {
            @Override
            public void onResponse(Call<GalleryResponse> call, final Response<GalleryResponse> response) {

                if (response.isSuccessful()) {

                    dailoghide(context);


                    if (response.body().getResponse() == 200) {
                        GlobalClass.showtost(context, "" + response.body().getSysMessage());
                        anInterface.GetGalleryDetail_list(response.body().getData());


                    } else {
                        GlobalClass.showtost(context, "" + response.body().getSysMessage());

                    }


                } else {
                    dailoghide(context);
                    GlobalClass.showtost(context, "" + response.message());
                }
            }


            @Override
            public void onFailure(Call<GalleryResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void PlayerListDataMethod(final Activity activity, final Context context, String token, GetPlayerListDetail_interface anInterface) {

        loadershowwithMsg(context, "Fetching all Players details...");

        Call<PlayersListResponse> apiCall =
                ApiClient.getClient().PlayersListApiCall("Bearer " + token);
        apiCall.enqueue(new Callback<PlayersListResponse>() {
            @Override
            public void onResponse(Call<PlayersListResponse> call, final Response<PlayersListResponse> response) {

                if (response.isSuccessful()) {

                    dailoghide(context);


                    if (response.body().getResponse() == 200) {
                        GlobalClass.showtost(context, "" + response.body().getSysMessage());
                        anInterface.GetPlayerListDetail_list(response.body().getData());


                    } else {
                        GlobalClass.showtost(context, "" + response.body().getSysMessage());

                    }


                } else {
                    dailoghide(context);
                    GlobalClass.showtost(context, "" + response.message());
                }
            }


            @Override
            public void onFailure(Call<PlayersListResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void CareerStatisticsDataMethod(final Activity activity, final Context context, String token, GetCareerStatistcsDetail_interface anInterface, RecyclerView rvplayerCareer) {

        loadershowwithMsg(context, "Fetching all Players Career Statistics details...");

        Call<CareerStatisticsResponse> apiCall =
                ApiClient.getClient().CareerStatisticsApiCall("Bearer " + token);
        apiCall.enqueue(new Callback<CareerStatisticsResponse>() {
            @Override
            public void onResponse(Call<CareerStatisticsResponse> call, final Response<CareerStatisticsResponse> response) {

                if (response.isSuccessful()) {

                    dailoghide(context);


                    if (response.body().getResponse() == 200) {
                        rvplayerCareer.setVisibility(View.VISIBLE);
                        GlobalClass.showtost(context, "" + response.body().getSysMessage());
                        anInterface.GetCareerStatisticsDetail_list(response.body().getData());


                    } else {
                        GlobalClass.showtost(context, "" + response.body().getSysMessage());

                    }


                } else {
                    dailoghide(context);
                    GlobalClass.showtost(context, "" + response.message());
                }
            }


            @Override
            public void onFailure(Call<CareerStatisticsResponse> call, Throwable t) {
                dailoghide(context);
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


}
