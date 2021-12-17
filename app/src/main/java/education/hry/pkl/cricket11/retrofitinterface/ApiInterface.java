package education.hry.pkl.cricket11.retrofitinterface;

import education.hry.pkl.cricket11.model.AddMatchResultRequest;
import education.hry.pkl.cricket11.model.AddMatchResultResponse;
import education.hry.pkl.cricket11.model.AddNewTeamsRequest;
import education.hry.pkl.cricket11.model.AddNewTeamsResponse;
import education.hry.pkl.cricket11.model.AllTeamListResponse;
import education.hry.pkl.cricket11.model.ApprovalPlayerRequest;
import education.hry.pkl.cricket11.model.ApprovalPlayerResponse;
import education.hry.pkl.cricket11.model.BannerResponse;
import education.hry.pkl.cricket11.model.CareerStatisticsResponse;
import education.hry.pkl.cricket11.model.DeleteIndivisualMatchDetailsRequest;
import education.hry.pkl.cricket11.model.DeleteIndivisualMatchDetailsResponse;
import education.hry.pkl.cricket11.model.DeletePlayerRequest;
import education.hry.pkl.cricket11.model.DeletePlayerResponse;
import education.hry.pkl.cricket11.model.DeleteRequest;
import education.hry.pkl.cricket11.model.DeleteResponse;
import education.hry.pkl.cricket11.model.DeleteTeamDetailsRequest;
import education.hry.pkl.cricket11.model.DeleteTeamDetailsResponse;
import education.hry.pkl.cricket11.model.DeleteTotalMatchDetailsRequest;
import education.hry.pkl.cricket11.model.DeleteTotalMatchDetailsResponse;
import education.hry.pkl.cricket11.model.ForgotPasswordRequest;
import education.hry.pkl.cricket11.model.ForgotPasswordResponse;
import education.hry.pkl.cricket11.model.GalleryResponse;
import education.hry.pkl.cricket11.model.GetPlayerRoleResponse;
import education.hry.pkl.cricket11.model.InsertMatchRecordIndivisualRequest;
import education.hry.pkl.cricket11.model.InsertMatchRecordIndivisualResponse;
import education.hry.pkl.cricket11.model.LoginRequest;
import education.hry.pkl.cricket11.model.LoginRespone;
import education.hry.pkl.cricket11.model.MatchDetailResponse;
import education.hry.pkl.cricket11.model.NetImageVideoResponse;
import education.hry.pkl.cricket11.model.PlayerHistoryResponse;
import education.hry.pkl.cricket11.model.PlayersListResponse;
import education.hry.pkl.cricket11.model.ProfilePicSaveResponse;
import education.hry.pkl.cricket11.model.RegistrationRespone;
import education.hry.pkl.cricket11.model.ResetPasswordRequest;
import education.hry.pkl.cricket11.model.ResetPaswordResponse;
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


    @Multipart
    @POST("RegisterUser")
    Call<RegistrationRespone> RegistrationApi(@Part("UserRole") RequestBody UserRole,
                                              @Part("PlayerName") RequestBody PlayerName,
                                              @Part("PhoneNumber") RequestBody PhoneNumber,
                                              @Part("Password") RequestBody Password,
                                              @Part("EmailId") RequestBody EmailId,
                                              @Part("DOB") RequestBody DOB,
                                              @Part("TeamId") RequestBody TeamId,
                                              @Part("PlayingRole") RequestBody PlayingRole,
                                              @Part("FCMToken") RequestBody FCMToken,
                                              @Part("MessageBody") RequestBody MessageBody,
                                              @Part("MessageTitle") RequestBody MatchTitle,
                                              @Part MultipartBody.Part FileName);

    /*
    {
        "VersusTeam2Id":"1",
            "ScoreTeam1":"34",
            "ScoreTeam2":"65",
            "WicketsTeam1":"23",
            "WicketsTeam2":"50",
            "OverTeam1":"6.2",
            "OverTeam2":"7",
            "MatchDate":"12-20-2021",
            "MatchTitle":"ABC",
            "ResultRemarks":"Demo Remarks",
            "CreatedBy":"PQR",
            "PlayerName":"",
            "ManOfTheMatchTeamId":"",
            "FileName":""
    }*/
    @Multipart
    @POST("TotalMatchDetails")
    Call<AddMatchResultResponse> addmatchResultApi(@Part("MessageTitle") RequestBody MessageTitle,
                                                   @Part("MessageBody") RequestBody MessageBody,
                                                   @Part("MatchTitle") RequestBody MatchTitle,
                                                   @Part("MatchDate") RequestBody MatchDate,
                                                   @Part("ScoreTeam1") RequestBody ScoreTeam1,
                                                   @Part("OverTeam1") RequestBody OverTeam1,
                                                   @Part("WicketsTeam1") RequestBody WicketsTeam1,
                                                   @Part("VersusTeam2Id") RequestBody VersusTeam2Id,
                                                   @Part("ScoreTeam2") RequestBody ScoreTeam2,
                                                   @Part("OverTeam2") RequestBody OverTeam2,
                                                   @Part("WicketsTeam2") RequestBody WicketsTeam2,
                                                   @Part("ResultRemarks") RequestBody ResultRemarks,
                                                   @Part("CreatedBy") RequestBody CreatedBy,
                                                   @Part("PlayerName") RequestBody PlayerName,
                                                   @Part("ManOfTheMatchTeamId") RequestBody ManOfTheMatchTeamId,
                                                   @Part MultipartBody.Part FileName,
                                                   @Part MultipartBody.Part ScoreCardFileName);

    @POST("InsertMatchRecord")
    Call<InsertMatchRecordIndivisualResponse> InsertMatchRecordIndivisualApi(@Body InsertMatchRecordIndivisualRequest request);


    @POST("DeletePlayerDetails")
    Call<DeleteIndivisualMatchDetailsResponse> DeletePlayerDetailsApi(@Body DeleteIndivisualMatchDetailsRequest request);

    @POST("Deletenetpractice")
    Call<DeleteResponse> DeletenetpracticeApi(@Body DeleteRequest request);

    @POST("ApprovePlayer")
    Call<ApprovalPlayerResponse> ApprovePlayerPlayerApi(@Body ApprovalPlayerRequest request);

    @POST("DeletePlayer")
    Call<DeletePlayerResponse> DeletePlayerApi(@Body DeletePlayerRequest request);


    @POST("DeleteTeams")
    Call<DeleteTeamDetailsResponse> DeleteTeamDetailsApi(@Body DeleteTeamDetailsRequest request);

    @POST("DeleteTotalMatchDetails")
    Call<DeleteTotalMatchDetailsResponse> DeleteTotalMatchDetailsApi(@Body DeleteTotalMatchDetailsRequest request);

    @POST("InsertTeams")
    Call<AddNewTeamsResponse> addNewTeamApi(@Body AddNewTeamsRequest request);


    @GET("HomeData")
    Call<BannerResponse> getBannnerAPi();

    @Multipart
    @POST("UpdatePlayerdetails")
    Call<ProfilePicSaveResponse> userUpdatePlayerdetailsUploading(
                                                                  @Part("DOB") RequestBody dob,
                                                                  @Part("playerRole") RequestBody MessageBody,
                                                                  @Part("Emailld") RequestBody MatchTitle,
                                                                  @Part("PhoneNumber") RequestBody MatchDate,
                                                                  @Part("Player_ld") RequestBody ScoreTeam1,
                                                                  @Part MultipartBody.Part FileName);

    @POST("TotalMatchDetails")
    Call<AddMatchResultResponse> MatchResultDetails(@Body AddMatchResultRequest request);

    @POST("login")
    Call<LoginRespone> LoginUser(@Body LoginRequest request);


    @POST("ForgetPassword")
    Call<ForgotPasswordResponse> forgotPassword(@Body ForgotPasswordRequest request);


    @POST("ResetPassword")
    Call<ResetPaswordResponse> ResetforgotPassword(@Body ResetPasswordRequest request);


////http://112.196.99.108/AndroidTesting/api/StudentSignin

    @POST("Login")
    Call<LoginRespone> LoginWithPasswordUser(@Body LoginRequest request);


    @GET("Gallery")
    Call<GalleryResponse> GalleryApiCall(@Header("Authorization") String token);

    @GET("GetPlayerRole")
    Call<GetPlayerRoleResponse> PlayerRoleListApiCall();

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

    //https://cricketapi.highereduhry.ac.in/api/commonapi/NetPractice/Image
    @GET("TeamList/{typeData}")
    Call<AllTeamListResponse> AllTeamListResponseApiCall(@Path("typeData") String typeData);


}
