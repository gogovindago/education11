package education.hry.pkl.cricket11.retrofitinterface;

import education.hry.pkl.cricket11.model.AddMatchResultRequest;
import education.hry.pkl.cricket11.model.AddMatchResultResponse;
import education.hry.pkl.cricket11.model.BannerResponse;
import education.hry.pkl.cricket11.model.CareerStatisticsResponse;
import education.hry.pkl.cricket11.model.ForgotPasswordRequest;
import education.hry.pkl.cricket11.model.ForgotPasswordResponse;
import education.hry.pkl.cricket11.model.GalleryResponse;
import education.hry.pkl.cricket11.model.LoginRequest;
import education.hry.pkl.cricket11.model.LoginRespone;
import education.hry.pkl.cricket11.model.MatchDetailResponse;
import education.hry.pkl.cricket11.model.NetImageVideoResponse;
import education.hry.pkl.cricket11.model.PlayerHistoryResponse;
import education.hry.pkl.cricket11.model.PlayersListResponse;
import education.hry.pkl.cricket11.model.ProfilePicSaveResponse;
import education.hry.pkl.cricket11.model.StudentEventDataSaveResponse;
import education.hry.pkl.cricket11.model.StudentProfileResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {


    @GET("HomeData")
    Call<BannerResponse> getBannnerAPi();


    @Multipart
    @POST("Saveprofile")
    Call<ProfilePicSaveResponse> userProfilePicUploading(@Part("Registration_Id") RequestBody customer_id,
                                                         @Part MultipartBody.Part image);

    @POST("TotalMatchDetails")
    Call<AddMatchResultResponse> MatchResultDetails(@Body AddMatchResultRequest request);

    @POST("login")
    Call<LoginRespone> LoginUser(@Body LoginRequest request);


    @POST("ForgetPassword")
    Call<ForgotPasswordResponse> forgotPassword(@Body ForgotPasswordRequest request);


    @POST("ResetPassword")
    Call<ForgotPasswordResponse> ResetforgotPassword(@Body ForgotPasswordRequest request);


////http://112.196.99.108/AndroidTesting/api/StudentSignin

    @POST("Login")
    Call<LoginRespone> LoginWithPasswordUser(@Body LoginRequest request);


    @GET("StudentProfile")
    Call<StudentProfileResponse> STUDENT_PROFILE_DATA_RESPONSE_CALL(@Query("Registration_Id") String Registration_Id);

    @FormUrlEncoded
    @POST("student")
    Call<StudentEventDataSaveResponse> STUDENT_EVENT_DATA_SAVE_RESPONSE_CALL(@Field("Registration_Id") String Registration_Id,
                                                                             @Field("Description") String Description,
                                                                             @Field("Longitude") String Longitude,
                                                                             @Field("Latitude") String Latitude,
                                                                             @Field("ImageDate") String ImageDate,
                                                                             @Field("ImagePath") String ImagePath);


    @GET("Gallery")
    Call<GalleryResponse> GalleryApiCall(@Header("Authorization") String token);

    @GET("PlayerList")
    Call<PlayersListResponse> PlayersListApiCall(@Header("Authorization") String token);

    @GET("CareerStatistics")
    Call<CareerStatisticsResponse> CareerStatisticsApiCall(@Header("Authorization") String token);

    @GET("MatchDetails")
    Call<MatchDetailResponse> MatchDetailsApiCall(@Header("Authorization") String token);

    @GET("PlayerStatistics/{playerId}")
    Call<PlayerHistoryResponse> PlayerHistoryResponseApiCall(@Header("Authorization") String token, @Path("playerId") String plyeID);


    //https://cricketapi.highereduhry.ac.in/api/commonapi/NetPractice/Image
    @GET("NetPractice/{typeData}")
    Call<NetImageVideoResponse> PlayerNetImageVideoApiCall(@Path("typeData") String typeData);


}
