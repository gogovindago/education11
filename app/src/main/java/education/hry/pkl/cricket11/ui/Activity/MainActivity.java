package education.hry.pkl.cricket11.ui.Activity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.AdminImgAdapter;
import education.hry.pkl.cricket11.adapter.DrawerItemCustomAdapter;
import education.hry.pkl.cricket11.adapter.MostFoursAdapter;
import education.hry.pkl.cricket11.adapter.MostSixesAdapter;
import education.hry.pkl.cricket11.adapter.MostWicketAdapter;
import education.hry.pkl.cricket11.adapter.RecentMatchesAdapter;
import education.hry.pkl.cricket11.adapter.RecyclerViewAdapter;
import education.hry.pkl.cricket11.adapter.SliderAdapter;
import education.hry.pkl.cricket11.adapter.TopBatAvgAdapter;
import education.hry.pkl.cricket11.adapter.TopBowlAvgAdapter;
import education.hry.pkl.cricket11.adapter.TopMostRunAdapter;
import education.hry.pkl.cricket11.allinterfaces.BannerData_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.app.MyApplication;
import education.hry.pkl.cricket11.model.BannerResponse;
import education.hry.pkl.cricket11.model.DataModel;
import education.hry.pkl.cricket11.model.DataModelLeftNew;
import education.hry.pkl.cricket11.model.NetpracticeTypeData;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;

public class MainActivity extends BaseActivity implements RecyclerViewAdapter.ItemListener, BannerData_interface, AdminImgAdapter.ItemListener, RecentMatchesAdapter.ItemListener, TopBatAvgAdapter.ItemListener, TopBowlAvgAdapter.ItemListener, MostWicketAdapter.ItemListener, TopMostRunAdapter.ItemListener, MostSixesAdapter.ItemListener, MostFoursAdapter.ItemListener {


    private AppUpdateManager mAppUpdateManager;
    private static final int RC_APP_UPDATE = 11;
    // List<SliderItem> sliderItemList;
    List<BannerResponse.Banner> sliderItemList;
    SwipeRefreshLayout mSwipeRefreshLayout;
    SliderView sliderView;
    SliderAdapter sliderAdapter;
    private static DrawerLayout mDrawerLayout;
    ImageView toggle, profile_image;
    TextView toolbartxt, uname, umobile, uemailId;
    Toolbar toolbar;
    Context context;
    AdminImgAdapter adapteradminimage;
    RecentMatchesAdapter recentMatchesAdapter;
    TopBatAvgAdapter topBatAvgAdapter;
    TopBowlAvgAdapter topBowlAvgAdapter;
    MostWicketAdapter mostWicketAdapter;
    TopMostRunAdapter topMostRunAdapter;
    MostSixesAdapter mostSixesAdapter;
    MostFoursAdapter mostFoursAdapter;
    private ListView mDrawerList;
    RecyclerView recyclerView, rvadminimage, rvTopBattingAvg, rvrecentmatch, rvWicketMost, rvRun, rvMostFour, rvMostSixes;
    ArrayList arrayList;
    LinearLayout llmain;
    List<DataModelLeftNew> dataModelLeftList;

    private List<BannerResponse.DashboardOfficer> adminimagelist = new ArrayList<BannerResponse.DashboardOfficer>();
    private List<BannerResponse.MatchDetail> matchDetailList = new ArrayList<BannerResponse.MatchDetail>();
    private List<BannerResponse.TopBat> topBatArrayList = new ArrayList<BannerResponse.TopBat>();
    private List<BannerResponse.TopBowl> topBowlArrayList = new ArrayList<BannerResponse.TopBowl>();
    private List<BannerResponse.MostWicket> mostWickets = new ArrayList<BannerResponse.MostWicket>();
    private List<BannerResponse.MostRun> mostRuns = new ArrayList<BannerResponse.MostRun>();

    private List<BannerResponse.MostSix> mostSixes = new ArrayList<BannerResponse.MostSix>();
    private List<BannerResponse.MostFour> mostFours = new ArrayList<BannerResponse.MostFour>();


    String imageurl = "https://i.picsum.photos/id/599/200/200.jpg?hmac=2WLKs3sxIsaEQ-6WZaa6YMxgl6ZC4cNnid0aqupm2is", role;
    private ActionBarDrawerToggle mDrawerToggle;


    public static void drawerCheck() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }


    private HashMap<String, Object> firebaseDefaultMap;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MyApplication.context;
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.simpleSwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        toggle = findViewById(R.id.toggle);
        toolbartxt = findViewById(R.id.toolbartxt);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);
        uname = findViewById(R.id.uname);
        umobile = findViewById(R.id.umobile);
        uemailId = findViewById(R.id.uemailId);
        profile_image = findViewById(R.id.profile_image);


        try {
            if (CSPreferences.getBoolean(this, "firstTimelogin")) {
                GlobalClass.dailogsuccess(MainActivity.this, "Login Successfull.", "Welcome to  Education 11, Haryana.");
                CSPreferences.putBolean(this, "firstTimelogin", false);
                mDrawerLayout.openDrawer(GravityCompat.START);
            } else {
                mDrawerLayout.closeDrawers();
            }

            uname.setText(CSPreferences.readString(MainActivity.this, "User_name"));
            uemailId.setText(CSPreferences.readString(MainActivity.this, "User_email"));
            umobile.setText(CSPreferences.readString(MainActivity.this, "User_mobile"));
            imageurl = CSPreferences.readString(MainActivity.this, "Profilepicurl");

            Picasso.get()
                    .load(imageurl)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.drawable.notattached)
                    .into(profile_image);

        } catch (Exception e) {
            e.printStackTrace();
        }


        setupToolbar();


        rvadminimage = (RecyclerView) findViewById(R.id.rvadminimage);
        rvrecentmatch = (RecyclerView) findViewById(R.id.rvrecentmatch);
        rvTopBattingAvg = (RecyclerView) findViewById(R.id.rvTopBattingAvg);
        rvWicketMost = (RecyclerView) findViewById(R.id.rvWicketMost);
        rvRun = (RecyclerView) findViewById(R.id.rvRun);
        rvMostSixes = (RecyclerView) findViewById(R.id.rvMostSixes);
        rvMostFour = (RecyclerView) findViewById(R.id.rvMostFour);
        llmain = (LinearLayout) findViewById(R.id.llmain);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        arrayList = new ArrayList();
        arrayList.add(new DataModel("Vriksha-Bandhan", R.drawable.personwhite, "#FFFFFF"));
        arrayList.add(new DataModel("Admissions", R.drawable.dashboard, "#FFFFFF"));
        RecyclerViewAdapter adaptermain = new RecyclerViewAdapter(this, arrayList, this);

        recyclerView.setAdapter(adaptermain);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (GlobalClass.isNetworkConnected(MainActivity.this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.allBanner_listMethod(MainActivity.this, MainActivity.this, MainActivity.this, llmain, mSwipeRefreshLayout);

        } else {

            Toast.makeText(MainActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {


                if (GlobalClass.isNetworkConnected(MainActivity.this)) {

                    WebAPiCall aPiCall = new WebAPiCall();
                    aPiCall.allBanner_listMethod(MainActivity.this, MainActivity.this, MainActivity.this, llmain, mSwipeRefreshLayout);

                } else {

                    Toast.makeText(MainActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }
                shuffle();
            }
        });


        sliderView = findViewById(R.id.imageSlider);

        sliderAdapter = new SliderAdapter(MainActivity.this);
        sliderItemList = new ArrayList<>();

        // renewItems();

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        // sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });

    }


    public void shuffle() {
        sliderItemList.clear();
        sliderAdapter.notifyDataSetChanged();

        //  renewItems();

    }


    @Override
    public void initData() {


        mAppUpdateManager = AppUpdateManagerFactory.create(this);

        mAppUpdateManager.registerListener(installStateUpdatedListener);

        mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {

            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE /*AppUpdateType.IMMEDIATE*/)) {

                try {
                    mAppUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo, AppUpdateType.IMMEDIATE /*AppUpdateType.IMMEDIATE*/, MainActivity.this, RC_APP_UPDATE);

                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }

            } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                popupSnackbarForCompleteUpdate();
            } else {
                Log.e(TAG, "checkForAppUpdateAvailability: something else");
            }
        });

        dataModelLeftList = new ArrayList<DataModelLeftNew>();
        dataModelLeftList.clear();

        DataModelLeftNew playersList = new DataModelLeftNew(R.drawable.ic_baseline_view_list_24, "Players List", 8);
        dataModelLeftList.add(playersList);

        DataModelLeftNew showMatchDetails = new DataModelLeftNew(R.drawable.ic_baseline_checklist_24, "Show Match Details", 9);
        dataModelLeftList.add(showMatchDetails);

        role = CSPreferences.readString(MainActivity.this, "role");


        if (role.equalsIgnoreCase("Admin")) {

            DataModelLeftNew addmatchresult = new DataModelLeftNew(R.drawable.ic_baseline_border_color_24, "Add Match Result", 99);
            dataModelLeftList.add(addmatchresult);

            DataModelLeftNew addnewteam = new DataModelLeftNew(R.drawable.ic_baseline_group_add_24, "Add New Team", 199);
            dataModelLeftList.add(addnewteam);


            DataModelLeftNew addIndivisualMatchRecord = new DataModelLeftNew(R.drawable.ic_baseline_group_add_24, "Add Indivisual Match Record", 198);
            dataModelLeftList.add(addIndivisualMatchRecord);


        }


        DataModelLeftNew careerStatistics = new DataModelLeftNew(R.drawable.carrer, "Career Statistics", 10);
        dataModelLeftList.add(careerStatistics);

        DataModelLeftNew publiclib = new DataModelLeftNew(R.drawable.ic_baseline_photo_library_24, "Gallery", 0);
        dataModelLeftList.add(publiclib);

        DataModelLeftNew netpracticeImagevideo = new DataModelLeftNew(R.drawable.ic_baseline_photo_library_24, "Net Practice Image/Video", 11);
        dataModelLeftList.add(netpracticeImagevideo);


        DataModelLeftNew my_profile = new DataModelLeftNew(R.drawable.personwhite, "My Profile", 1);
        dataModelLeftList.add(my_profile);


        DataModelLeftNew rateApp = new DataModelLeftNew(R.drawable.rate_review, "Rate App", 2);
        dataModelLeftList.add(rateApp);


        DataModelLeftNew share_app = new DataModelLeftNew(R.drawable.share, "Share App", 3);
        dataModelLeftList.add(share_app);


        DataModelLeftNew notification = new DataModelLeftNew(R.drawable.notifications, "My Notification", 4);
        dataModelLeftList.add(notification);

        DataModelLeftNew about_us = new DataModelLeftNew(R.drawable.rate_review, "About Us", 5);
        dataModelLeftList.add(about_us);

        DataModelLeftNew resetPassword = new DataModelLeftNew(R.drawable.lockmain, "Reset Password", 6);
        dataModelLeftList.add(resetPassword);

        DataModelLeftNew logout = new DataModelLeftNew(R.drawable.logout, "Logout", 7);
        dataModelLeftList.add(logout);


        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, dataModelLeftList);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();


    }

    InstallStateUpdatedListener installStateUpdatedListener = new
            InstallStateUpdatedListener() {
                @Override
                public void onStateUpdate(InstallState state) {
                    if (state.installStatus() == InstallStatus.DOWNLOADED) {
                        //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                        popupSnackbarForCompleteUpdate();
                    } else if (state.installStatus() == InstallStatus.INSTALLED) {
                        if (mAppUpdateManager != null) {
                            mAppUpdateManager.unregisterListener(installStateUpdatedListener);
                        }

                    } else {
                        Log.i(TAG, "InstallStateUpdatedListener: state: " + state.installStatus());
                    }
                }
            };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_APP_UPDATE) {
            if (resultCode != RESULT_OK) {
                Log.e(TAG, "onActivityResult: app download failed");
            }
        }
    }


    private void popupSnackbarForCompleteUpdate() {

        Snackbar snackbar =
                Snackbar.make(
                        mDrawerLayout,
                        "New app is ready!",
                        Snackbar.LENGTH_INDEFINITE);

        snackbar.setAction("Install", view -> {
            if (mAppUpdateManager != null) {
                mAppUpdateManager.completeUpdate();
            }
        });


        snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public void initListeners() {
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.drawerCheck();
            }
        });

    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (dataModelLeftList.get(position).id) {
            case 0:
                mDrawerLayout.closeDrawers();
                Intent intent2 = new Intent(this, GalleryActivity.class);
                startActivity(intent2);
                // fragment = new FixturesFragment();
                // fragment = new Upload_service();
                //  fragment = new ConnectFragment();
                break;
            case 1:
                mDrawerLayout.closeDrawers();
                Intent myprofile = new Intent(this, ProfileActivity.class);
                startActivity(myprofile);

                break;
            case 2:


                mDrawerLayout.closeDrawers();
                rateApp();
                //Intent intent = new Intent(this, RoomDBMainActivity.class);
                // startActivity(intent);
                // fragment = new My_chanlel();
                break;
            case 3:

                mDrawerLayout.closeDrawers();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Education 11");
                // String app_url = "https://play.google.com/store/apps/details?id=plantation.hr.cbse.haryanaplantation";
                String app_url = "https://cricket.highereduhry.ac.in/";
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, app_url);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
                break;

            case 4:

                mDrawerLayout.closeDrawers();
                Intent notification = new Intent(this, NotificationsActivity.class);
                startActivity(notification);
                break;


            case 6:

                mDrawerLayout.closeDrawers();
                Intent resetPassword = new Intent(this, ResetPasswordActivity.class);
                startActivity(resetPassword);
                break;

            case 8:

                mDrawerLayout.closeDrawers();
                Intent plylistIntent = new Intent(this, PlayerDetailActivity.class);
                startActivity(plylistIntent);
                break;


            case 9:

                mDrawerLayout.closeDrawers();
                Intent allmatchesdetailIntent = new Intent(this, MatchDetailsActivity.class);
                startActivity(allmatchesdetailIntent);
                break;

            case 99:

                mDrawerLayout.closeDrawers();
                Intent addmatchresult = new Intent(this, AddMatchResultActivity.class);
                startActivity(addmatchresult);
                break;

            case 198:

                mDrawerLayout.closeDrawers();
                Intent addIndivisualMatchRecordintent = new Intent(this, IndivisualMatchDetailAddingActivity.class);
                startActivity(addIndivisualMatchRecordintent);
                break;
            case 199:

                mDrawerLayout.closeDrawers();
                Intent addnewteam = new Intent(this, AddNewTeamActivity.class);
                startActivity(addnewteam);
                break;

            case 10:

                mDrawerLayout.closeDrawers();
                Intent plylistCareerStatisticsIntent = new Intent(this, CareerStatisticsActivity.class);
                startActivity(plylistCareerStatisticsIntent);
                break;

            case 11:

                mDrawerLayout.closeDrawers();
                Intent NetPracticeImageVideoIntent = new Intent(this, NetPracticeImageVideoActivity.class);
                startActivity(NetPracticeImageVideoIntent);

                // GlobalClass.showtost(MainActivity.this,"Coming soon...");
                break;
            case 7:

                mDrawerLayout.closeDrawers();
                logout();
                break;


            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            // setTitle(mNavigationDrawerItemTitles[position]);
            // mDrawerLayout.closeDrawer(mDrawerList);
            mDrawerLayout.closeDrawers();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }


    public void rateApp() {
        try {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        } catch (ActivityNotFoundException e) {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21) {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        } else {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        // hide the title bar  0
        setSupportActionBar(toolbar);
        (getSupportActionBar()).setDisplayShowHomeEnabled(false);
    }

    void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, null, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    @Override
    public void onItemClick(DataModel item, int currposition) {

        if (currposition == 0) {
            // Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

//            Intent captureplant = new Intent(this, CapturePlantationActivity.class);
//            startActivity(captureplant);


        } else if (currposition == 1) {

            // Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

//            Intent captureplant = new Intent(this, EducationWebsiteActivity.class);
//
//            startActivity(captureplant);

        } else {
        }


    }

    @Override
    public void allBanner_list(List<BannerResponse.Banner> list) {


        sliderItemList.clear();
        sliderAdapter = new SliderAdapter(this);
        sliderItemList.addAll(list);
        sliderAdapter.renewItems(list);


        //renewItems();

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        // sliderView.setIndicatorSelectedColor(Color.WHITE);
        // sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

    }

    @Override
    public void allhigherauthority_list(List<BannerResponse.DashboardOfficer> list) {

        adminimagelist.clear();
        adminimagelist = new ArrayList();
        adminimagelist.addAll(list);


        adapteradminimage = new AdminImgAdapter(this, (ArrayList) adminimagelist, this);

        rvadminimage.setAdapter(adapteradminimage);


        GridLayoutManager adminimagemanager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        rvadminimage.setLayoutManager(adminimagemanager);


    }

    @Override
    public void allrecentmatches_list(List<BannerResponse.MatchDetail> list) {

        matchDetailList.clear();
        matchDetailList = new ArrayList();
        matchDetailList.addAll(list);

        recentMatchesAdapter = new RecentMatchesAdapter(this, (ArrayList) matchDetailList, this);
        rvrecentmatch.setAdapter(recentMatchesAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        rvrecentmatch.setLayoutManager(layoutManager);

    }

    @Override
    public void allTopbattingAvg_list(List<BannerResponse.TopBat> list) {

        topBatArrayList.clear();
        topBatArrayList = new ArrayList();
        topBatArrayList.addAll(list);

        topBatAvgAdapter = new TopBatAvgAdapter(this, (ArrayList) topBatArrayList, this);
        rvTopBattingAvg.setAdapter(topBatAvgAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        rvTopBattingAvg.setLayoutManager(layoutManager);
    }

    @Override
    public void allTopBowlingAvg_list(List<BannerResponse.TopBowl> list) {

        topBowlArrayList.clear();
        topBowlArrayList = new ArrayList();
        topBowlArrayList.addAll(list);

        topBowlAvgAdapter = new TopBowlAvgAdapter(this, (ArrayList) topBowlArrayList, this);
        recyclerView.setAdapter(topBowlAvgAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void allMostWicket_list(List<BannerResponse.MostWicket> list) {
        mostWickets.clear();
        mostWickets = new ArrayList();
        mostWickets.addAll(list);

        mostWicketAdapter = new MostWicketAdapter(this, (ArrayList) mostWickets, this);
        rvWicketMost.setAdapter(mostWicketAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        rvWicketMost.setLayoutManager(layoutManager);
    }

    @Override
    public void allMostRun_list(List<BannerResponse.MostRun> list) {
        mostRuns.clear();
        mostRuns = new ArrayList();
        mostRuns.addAll(list);

        topMostRunAdapter = new TopMostRunAdapter(this, (ArrayList) mostRuns, this);
        rvRun.setAdapter(topMostRunAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        rvRun.setLayoutManager(layoutManager);

    }

    @Override
    public void allMostSixes_list(List<BannerResponse.MostSix> list) {
        mostSixes.clear();
        mostSixes = new ArrayList();
        mostSixes.addAll(list);

        mostSixesAdapter = new MostSixesAdapter(this, (ArrayList) mostSixes, this);
        rvMostSixes.setAdapter(mostSixesAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        rvMostSixes.setLayoutManager(layoutManager);

    }

    @Override
    public void allMostFours_list(List<BannerResponse.MostFour> list) {

        mostFours.clear();
        mostFours = new ArrayList();
        mostFours.addAll(list);

        mostFoursAdapter = new MostFoursAdapter(this, (ArrayList) mostFours, this);
        rvMostFour.setAdapter(mostFoursAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        rvMostFour.setLayoutManager(layoutManager);
    }

    @Override
    public void onadminimgItemClick(BannerResponse.DashboardOfficer item, int currposition) {

    }

    @Override
    public void onrecentmatchItemClick(BannerResponse.MatchDetail item, int currposition) {

    }

    @Override
    public void onTopBattingAvgItemClick(BannerResponse.TopBat item, int currposition) {

    }

    @Override
    public void onTopBowltingAvgItemClick(BannerResponse.TopBowl item, int currposition) {

    }

    @Override
    public void onMostWicketItemClick(BannerResponse.MostWicket item, int currposition) {

    }

    @Override
    public void onMostRuntingAvgItemClick(BannerResponse.MostRun item, int currposition) {

    }

    @Override
    public void onMostSixItemClick(BannerResponse.MostSix item, int currposition) {

    }

    @Override
    public void onMostFourItemClick(BannerResponse.MostFour item, int currposition) {

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);

        }

    }

    void logout() {
        mDrawerLayout.closeDrawers();
        new FancyGifDialog.Builder(this)
                .setTitle("Log out")
                .setMessage("Are you sure logout from Education 11,Haryana?")
                .setNegativeBtnText("No")
                .setPositiveBtnBackground("#FF4081")
                .setPositiveBtnText("Yes")
                .setNegativeBtnBackground("#FFA9A7A8")
                .setGifResource(R.drawable.logoutt)   //Pass your Gif here
                .isCancellable(true)
                .OnPositiveClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {

                      /*  if (GlobalClass.isNetworkConnected(MainActivity.this)) {
                            logout(MainActivity.this, CSPreferences.readString(MainActivity.this, "tooken"), CSPreferences.readString(MainActivity.this, "tooken"));

                        } else {

                            Toast.makeText(MainActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                        }*/

                        Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        CSPreferences.clearPref(MainActivity.this);
                        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        //onBackPressed();
                    }
                })
                .OnNegativeClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
    }

    // LogOut APi................

  /*  public void logout(final Context context, String auth, String tooken) {
        dailogshow(context);
        Call<Logout_model> userpost_responseCall = ApiClient.getClient().app_logout(GlobalClass.apikey, "Bearer " + auth, tooken);
        userpost_responseCall.enqueue(new Callback<Logout_model>() {
            @Override
            public void onResponse(Call<Logout_model> call, Response<Logout_model> response) {
                dailoghide(context);

                if (response.code() == 200) {
                    Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawers();
                    CSPreferences.clearPref(MainActivity.this);
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                } else {
                    GlobalClass.showtost(context, "" + response.message());
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Logout_model> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }*/

    ProgressDialog pd;

    public void dailogshow(Context context) {
        pd = new ProgressDialog(context);
        pd.setMessage("loading...");
        pd.setCancelable(false);
        pd.show();
    }

    public void dailoghide(Context context) {
        pd.dismiss();
    }

    private final BroadcastReceiver videocall = new BroadcastReceiver() {


        @Override
        public void onReceive(Context context, android.content.Intent intent) {


            Toast.makeText(context, "Sorry For Inconvenience! All Driver is Busy Right Now. Please Book After a few moment. ", Toast.LENGTH_SHORT).show();

            try {


            } catch (Exception e) {

            }


        }
    };


}
