package education.hry.pkl.cricket11.ui.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;

import androidx.databinding.DataBindingUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.SpinnerAllTeamAdapter;
import education.hry.pkl.cricket11.allinterfaces.GetAllTeamList_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityIndivisualMatchDetailAddingBinding;
import education.hry.pkl.cricket11.model.AllTeamListResponse;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;
import education.hry.pkl.cricket11.utility.MyLoaders;
import education.hry.pkl.cricket11.utility.NetworkUtil;

public class IndivisualMatchDetailAddingActivity extends BaseActivity implements GetAllTeamList_interface, AdapterView.OnItemSelectedListener {
    ActivityIndivisualMatchDetailAddingBinding binding;
    private List<AllTeamListResponse.Datum> allteamlist = new ArrayList<AllTeamListResponse.Datum>();
    SpinnerAllTeamAdapter SpinnerAllTeamAdapter;

    int spnOpponentteamCurrentPosition, spnteamdheCurrentPosition = 23, spnmomteamnameCurrentPosition;
    private MyLoaders myLoaders;

    String OpponentteamID, teamdhe, uname, Registration_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_indivisual_match_detail_adding);


        uname = CSPreferences.readString(IndivisualMatchDetailAddingActivity.this, "User_name");

        Registration_Id = CSPreferences.readString(IndivisualMatchDetailAddingActivity.this, "User_Id");


        if (NetworkUtil.isConnected(IndivisualMatchDetailAddingActivity.this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.allTeamlistMethod(IndivisualMatchDetailAddingActivity.this, IndivisualMatchDetailAddingActivity.this, "2", IndivisualMatchDetailAddingActivity.this, binding.simpleSwipeRefreshLayout);


        } else {
            GlobalClass.showtost(IndivisualMatchDetailAddingActivity.this, "No Internet Available.Plz check your internet connection.");
        }

        binding.spnOpponentteam.setOnItemSelectedListener(this);
        binding.spnteamdhe.setOnItemSelectedListener(this);

    }

    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("Add Indivisual Match Record");
    }

    @Override
    public void initListeners() {

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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


                DatePickerDialog datePickerDialog = new DatePickerDialog(IndivisualMatchDetailAddingActivity.this,
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


        binding.spnteamdhe.setSelection(23);
        binding.spnteamdhe.setEnabled(false);
        binding.spnteamdhe.setClickable(false);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}