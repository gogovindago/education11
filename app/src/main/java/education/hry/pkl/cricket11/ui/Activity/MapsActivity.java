package education.hry.pkl.cricket11.ui.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.UserAllplantedPlantAdapter;
import education.hry.pkl.cricket11.allinterfaces.GetPlantationDetail_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.model.GetPlantDetailsResponse;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, UserAllplantedPlantAdapter.ItemListener, GetPlantationDetail_interface {


    RecyclerView recyclerView;
    ArrayList<GetPlantDetailsResponse.GetPlantDetailsData> arrayList;
    private GoogleMap map;
    LatLngBounds.Builder builder;
    CameraUpdate cu;
    double plantedLat, plantedLong;
    private Toolbar toolbar;
    String Registration_Id;
    LocationManager locationManager = null;
    boolean gps_enabled = false;
    boolean network_enabled = false;
    RecyclerView usertotalplantrv;
    Button plantationnow;
    private List<GetPlantDetailsResponse.GetPlantDetailsData> data = null;

    @Override
    protected void onStart() {
        super.onStart();
        usertotalplantrv = findViewById(R.id.usertotalplantrv);
        plantationnow = findViewById(R.id.plantationnow);

        checkpermissions(this);
        checkGPSStatus();


        try {
            plantedLat = Double.parseDouble(CSPreferences.readString(this, "lativale"));

            plantedLong = Double.parseDouble(CSPreferences.readString(this, "longivalue"));

        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        checkpermissions(this);


        TextView tv_toolbarTitle = findViewById(R.id.tv_toolbarTitle);
        tv_toolbarTitle.setText("Map");
        ImageButton back = findViewById(R.id.back);


        try {
            Registration_Id = CSPreferences.readString(this, "User_Id");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GlobalClass.isNetworkConnected(this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.getPlantDetailsMethod(this, this, Registration_Id);


        } else {

            Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        try {
            plantedLat = Double.parseDouble(CSPreferences.readString(this, "lativale"));
            plantedLong = Double.parseDouble(CSPreferences.readString(this, "longivalue"));

        } catch (Exception e) {

            e.printStackTrace();
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        mapFragment.getMapAsync(this);


        recyclerView = (RecyclerView) findViewById(R.id.usertotalplantrv);
        plantationnow = findViewById(R.id.plantationnow);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        arrayList = new ArrayList<GetPlantDetailsResponse.GetPlantDetailsData>();


//        arrayList.add(new DataModel("Plantation", R.drawable.tree, "#FFFFFF"));
//        arrayList.add(new DataModel("Harit Haryana", R.drawable.tree, "#FFFFFF"));
//        arrayList.add(new DataModel("Vriksha-Bandhan", R.drawable.tree, "#FFFFFF"));
//        arrayList.add(new DataModel("Admissions", R.drawable.admission, "#FFFFFF"));
//        arrayList.add(new DataModel("Plantation", R.drawable.tree, "#FFFFFF"));
//        arrayList.add(new DataModel("Harit Haryana", R.drawable.tree, "#FFFFFF"));
//        arrayList.add(new DataModel("Vriksha-Bandhan", R.drawable.tree, "#FFFFFF"));
//        arrayList.add(new DataModel("Admissions", R.drawable.admission, "#FFFFFF"));
//        arrayList.add(new DataModel("Blood Donation", R.drawable.bloodonate, "#FFFFFF"));

        // UserAllplantedPlantAdapter plantAdapter = new UserAllplantedPlantAdapter(MapsActivity.this, arrayList, this);
        //  recyclerView.setAdapter(plantAdapter);


        plantationnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(MapsActivity.this, CapturePlantationActivity.class);
//               // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                finish();

            }
        });


    }


    public void checkpermissions(Activity context) {
        if (Build.VERSION.SDK_INT >= 23) {
            new TedPermission(context)

                    .setPermissionListener(permissionListener)
                    //.setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(
                            android.Manifest.permission.INTERNET,
                            ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_NETWORK_STATE
                    )

                    .setGotoSettingButton(true)
                    .check();

        }


    }

    private void checkGPSStatus() {

        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        if (!gps_enabled && !network_enabled) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MapsActivity.this);
            dialog.setMessage("GPS not enabled");

            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //this will navigate user to the device location settings screen
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            AlertDialog alert = dialog.create();
            alert.setCanceledOnTouchOutside(false);
            alert.show();
        }
    }


    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {


//            if (String.valueOf(plantedLat).equalsIgnoreCase("0.0")) {
//
//
//                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MapsActivity.this);
//                sweetAlertDialog.setTitle("You did not plant yet any plant!");
//                sweetAlertDialog.setContentText("Please  plant  first then  it will show here automatic.");
//                sweetAlertDialog.setVolumeControlStream(2);
//                sweetAlertDialog.getAlerType();
//                sweetAlertDialog.changeAlertType(1);
//                sweetAlertDialog.setCanceledOnTouchOutside(false);
//                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        sweetAlertDialog.dismiss();
//                        finish();
//                    }
//                });
//                sweetAlertDialog.show();
//            } else {
//                mSetUpMap();
//            }


        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            checkpermissions(MapsActivity.this);


        }

    };

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(map., 151);
        // map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //  mSetUpMap();


    }

    /**
     * create method to set map view
     */
    public void mSetUpMap() {

        map.clear();
        map.isMyLocationEnabled();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
        map.setIndoorEnabled(true);


        //  Now get all the marker in the Markers
//seachModelsList is the list of all markers
        Marker[] allMarkers = new Marker[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++) {
            LatLng latLng = new LatLng(Double.parseDouble(arrayList.get(i).getLatitude()), Double.parseDouble(arrayList.get(i).getLongitude()));


            // googleMap.setOnMarkerClickListener(this);
            allMarkers[i] = map.addMarker(new MarkerOptions().position(latLng));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.0f));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));


        }






        /*  *//**clear the map before redraw to them*//*
        map.clear();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


  map.isMyLocationEnabled();
        map.setMyLocationEnabled(true);
        map.setIndoorEnabled(true);
        *//**Create dummy Markers List*//*

        List<Marker> markersList = new ArrayList<Marker>();
        Marker[] allMarkers = new Marker[arrayList.size()];
        for(int i = 0 ; i < arrayList.size() ; i++) {

             allMarkers[i] = map.addMarker(new MarkerOptions().position(new LatLng(
                   Double.parseDouble(arrayList.get(i).getLatitude()),  Double.parseDouble(arrayList.get(i).getLongitude())))
                    .title(arrayList.get(i).getDescription()));
            markersList.add(allMarkers[i]);
        }

//        Marker PlantLocation = map.addMarker(new MarkerOptions().position(new LatLng(
//                plantedLat, plantedLong))
//                .title("Planted plant Location"));

//        Marker Chaandigarh = map.addMarker(new MarkerOptions().position(new LatLng(
//                30.75, 76.78)).title("Chandigarh"));
//        Marker SriLanka = map.addMarker(new MarkerOptions().position(new LatLng(
//                7.000, 81.0000)).title("Sri Lanka"));
//        Marker America = map.addMarker(new MarkerOptions().position(new LatLng(
//                38.8833, 77.0167)).title("America"));
//        Marker Arab = map.addMarker(new MarkerOptions().position(new LatLng(
//                24.000, 45.000)).title("Arab"));

        *//**Put all the markers into arraylist*//*



//        markersList.add(SriLanka);
//        markersList.add(America);
//        markersList.add(Arab);
//        markersList.add(Chaandigarh);

        *//**create for loop for get the latLngbuilder from the marker list*//*

        builder = new LatLngBounds.Builder();

        if (markersList.size() > 0) {
            for (Marker m : markersList) {
                builder.include(m.getPosition());
            }
        } else {

            Toast.makeText(this, "You do not Planted any plant yet.", Toast.LENGTH_SHORT).show();
        }

        *//**initialize the padding for map boundary*//*
        int padding = 50;
        *//**create the bounds from latlngBuilder to set into map camera*//*
        LatLngBounds bounds = builder.build();
        *//**create the camera with bounds and padding to set into map*//*
        cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        *//**call the map call back to know map is loaded or not*//*

        map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                *//**set animated zoom camera into map*//*
                map.animateCamera(cu);

            }
        });
*/
    }


    @Override
    public void onItemClick(GetPlantDetailsResponse.GetPlantDetailsData item, int currposition) {

    }

    @Override
    public void GetPlantationDetail_list(List<GetPlantDetailsResponse.GetPlantDetailsData> list) {

        if (list != null) {

            arrayList.clear();
            arrayList.addAll(list);
            plantationnow.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            UserAllplantedPlantAdapter adaptermain = new UserAllplantedPlantAdapter(this, arrayList, this);
            recyclerView.setAdapter(adaptermain);
            mSetUpMap();

        } else {

            plantationnow.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            GlobalClass.dailogwarring(MapsActivity.this, "No Planting", "No Record found!");

        }

 /*
try {

    if (list.isEmpty()) {
        GlobalClass.showtost(MapsActivity.this, "No Record found!");

    } else {


    }

}catch (Exception e){
    GlobalClass.dailogwarring(MapsActivity.this,"No Planting", "No Record found!");

    e.printStackTrace();

}*/


    }
}