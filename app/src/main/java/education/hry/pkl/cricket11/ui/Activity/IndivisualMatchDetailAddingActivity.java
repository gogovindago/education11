package education.hry.pkl.cricket11.ui.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.adapter.SpinnerAllDhePlayerAdapter;
import education.hry.pkl.cricket11.adapter.SpinnerAllTeamAdapter;
import education.hry.pkl.cricket11.allinterfaces.GetAllTeamList_interface;
import education.hry.pkl.cricket11.allinterfaces.GetPlayerListDetail_interface;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityIndivisualMatchDetailAddingBinding;
import education.hry.pkl.cricket11.model.AllTeamListResponse;
import education.hry.pkl.cricket11.model.InsertMatchRecordIndivisualRequest;
import education.hry.pkl.cricket11.model.PlayersListResponse;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;
import education.hry.pkl.cricket11.utility.MyLoaders;
import education.hry.pkl.cricket11.utility.NetworkUtil;

public class IndivisualMatchDetailAddingActivity extends BaseActivity implements GetAllTeamList_interface, AdapterView.OnItemSelectedListener, GetPlayerListDetail_interface {
    ActivityIndivisualMatchDetailAddingBinding binding;
    private List<AllTeamListResponse.Datum> allteamlist = new ArrayList<AllTeamListResponse.Datum>();
    ArrayList<PlayersListResponse.Datum> dheTeamPlayerList = new ArrayList<PlayersListResponse.Datum>();
    SpinnerAllTeamAdapter SpinnerAllTeamAdapter;
    SpinnerAllDhePlayerAdapter spinnerAllDhePlayerAdapter;

    int spnOpponentteamCurrentPosition, spnteamdheCurrentPosition = 23, spnAllDhePlayerCurrentPosition;
    private MyLoaders myLoaders;

    String OpponentteamID,OpponentteamName, teamdhe, token, uname, Registration_Id, IsBatsmanOUtorNOt, SelectedPlayerforinsertRecordName = "", SelectedPlayerforinsertRecordID;
    RadioGroup btnRadiogroup;
    RadioButton checkedRadioButton;
    private SweetAlertDialog sweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_indivisual_match_detail_adding);
        myLoaders = new MyLoaders(getApplicationContext());

        uname = CSPreferences.readString(IndivisualMatchDetailAddingActivity.this, "User_name");

        Registration_Id = CSPreferences.readString(IndivisualMatchDetailAddingActivity.this, "User_Id");
        token = CSPreferences.readString(IndivisualMatchDetailAddingActivity.this, "token");


        if (NetworkUtil.isConnected(IndivisualMatchDetailAddingActivity.this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.PlayerListDataMethod(this, this, token, this);


        } else {

            GlobalClass.showtost(IndivisualMatchDetailAddingActivity.this, "No Internet Available.Plz check your internet connection.");
        }


        if (NetworkUtil.isConnected(IndivisualMatchDetailAddingActivity.this)) {

            WebAPiCall aPiCall = new WebAPiCall();
            aPiCall.allTeamlistMethod(IndivisualMatchDetailAddingActivity.this, IndivisualMatchDetailAddingActivity.this, "2", IndivisualMatchDetailAddingActivity.this, binding.simpleSwipeRefreshLayout);


        } else {
            GlobalClass.showtost(IndivisualMatchDetailAddingActivity.this, "No Internet Available.Plz check your internet connection.");
        }

        binding.spnOpponentteam.setOnItemSelectedListener(this);
        binding.spnteamdhe.setOnItemSelectedListener(this);
        binding.spnteamplayerdhe.setOnItemSelectedListener(this);


        btnRadiogroup = (RadioGroup) findViewById(R.id.btnRadiogroup);
        checkedRadioButton = (RadioButton) btnRadiogroup.findViewById(btnRadiogroup.getCheckedRadioButtonId());

        btnRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton) btnRadiogroup.findViewById(checkedId);
                // This puts the value (true/false) into the variable

                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...llCollegestudent
                if (isChecked) {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    switch (checkedId) {

                        case R.id.rdbOUt:


                            IsBatsmanOUtorNOt = "0";
                            break;

                        case R.id.rdbNotOUt:

                            IsBatsmanOUtorNOt = "1";

                            break;


                    }
                    Toast.makeText(IndivisualMatchDetailAddingActivity.this, checkedRadioButton.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public void initData() {

        binding.toolbar.tvToolbarTitle.setText("Add Match Record of " + SelectedPlayerforinsertRecordName);

        binding.btnaddmatchdetail.setText("Add Match Record for " + SelectedPlayerforinsertRecordName);

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

                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
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


        binding.btnaddmatchdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_Data(v)) {

                    sweetAlertDialog = new SweetAlertDialog(IndivisualMatchDetailAddingActivity.this);
                    sweetAlertDialog.setTitle("Alert Indivisual Match Deatil of  " + SelectedPlayerforinsertRecordName + "  Adding !");
                    sweetAlertDialog.setContentText("Make Sure you have filled all  Indivisual Match Deatil of  " + SelectedPlayerforinsertRecordName + "  correctly.");
                    sweetAlertDialog.setVolumeControlStream(2);
                    sweetAlertDialog.setCancelable(true);
                    sweetAlertDialog.setCancelText("No");
                    sweetAlertDialog.setCustomImage(R.mipmap.ic_launcher_round);

                    sweetAlertDialog.changeAlertType(3);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                            if (NetworkUtil.isConnected(IndivisualMatchDetailAddingActivity.this)) {
                                sweetAlertDialog.dismiss();

                                InsertMatchRecordIndivisualRequest request = new InsertMatchRecordIndivisualRequest();

                                request.setVersus(OpponentteamName);
                                request.setPlayerId(SelectedPlayerforinsertRecordID);



                                request.setDate(binding.edtMatchDate.getText().toString().trim());
                                request.setScore(binding.edtScored.getText().toString().trim());
                                request.setBalls(binding.edtBallFaced.getText().toString().trim());
                                request.setFours(binding.edt4s.getText().toString().trim());
                                request.setSix(binding.edt6s.getText().toString().trim());


                                request.setIsNotOut(IsBatsmanOUtorNOt);


                                request.setOvers(binding.edtOverbowled.getText().toString().trim());
                                request.setMaiden(binding.edtMaidenOver.getText().toString().trim());
                                request.setRun(binding.edtRun.getText().toString().trim());
                                request.setWickets(binding.edtWicketTaken.getText().toString().trim());
                                request.setScore(binding.edtdoneRunOut.getText().toString().trim());
                                request.setCatches(binding.edttakeCatch.getText().toString().trim());


                                WebAPiCall aPiCall = new WebAPiCall();
                                aPiCall.InsertMatchRecordIndivisualPostDataMethod(IndivisualMatchDetailAddingActivity.this, IndivisualMatchDetailAddingActivity.this, request);


                            } else {
                                GlobalClass.showtost(IndivisualMatchDetailAddingActivity.this, "No Internet Available.Plz check your internet connection.");
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

                } else {


                }


            }
        });


    }


    public boolean Check_Data(View view) {

        if (spnAllDhePlayerCurrentPosition == 0) {
            myLoaders.showSnackBar(view, "Please Select DHE Player Name.");
            return false;
        } else if (TextUtils.isEmpty(binding.edtMatchDate.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Select Match Date.");
            return false;
        } else if (spnOpponentteamCurrentPosition == 0) {
            myLoaders.showSnackBar(view, "Please Select Opponent Team Name.");
            return false;
        } else if (TextUtils.isEmpty(binding.edtScored.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Scored of Batsman.");
            return false;
        } else if (TextUtils.isEmpty(binding.edtBallFaced.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Nos. of Ball faced by Batsman.");
            return false;
        } else if (TextUtils.isEmpty(binding.edt4s.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Nos. of 4s  by Batsman.");
            return false;
        } else if (TextUtils.isEmpty(binding.edt6s.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Nos. of 6s  by Batsman.");
            return false;
        } else if (IsBatsmanOUtorNOt==null) {
            myLoaders.showSnackBar(view, "Please Select  ( Out/not-out ) Batsman status.");
            return false;
        } else if (TextUtils.isEmpty(binding.edtOverbowled.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Over Bowled By bowler.");
            return false;
        } else if (TextUtils.isEmpty(binding.edtMaidenOver.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Maiden Over Bowled By bowler.");
            return false;
        } else if (TextUtils.isEmpty(binding.edtRun.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter 4s Given By bowler.");
            return false;
        } else if (TextUtils.isEmpty(binding.edtWicketTaken.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Wicket took By bowler.");
            return false;
        } else if (TextUtils.isEmpty(binding.edtdoneRunOut.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Nos. of Run out did.");
            return false;
        } else if (TextUtils.isEmpty(binding.edttakeCatch.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Nos. of Catch taken.");
            return false;
        } else {

        }

        return true;
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        int id = parent.getId();
        if (id == R.id.spnOpponentteam) {

            if (position != 0) {

                spnOpponentteamCurrentPosition = position;
                OpponentteamName = allteamlist.get(position).getTeamName();
                OpponentteamID = String.valueOf(allteamlist.get(position).getTeamId());

//                String arr[] = mystring.split(" ", 3);
//
//                String firstWord = arr[0];   //the
//                String theRest2 = arr[1];     //quick brown fox
//                String theRest3 = arr[2];     //quick brown fox
//                binding.tlOpponentscore.setHint(firstWord + " " + theRest2 + " Total Score");
//                binding.tlOpponentover.setHint(firstWord + " " + theRest2 + " Over played");


            } else {
//                binding.tlOpponentscore.setHint("Total Score");
//                binding.tlOpponentover.setHint("Over played");
                spnOpponentteamCurrentPosition = position;
            }


        } else if (id == R.id.spnteamplayerdhe) {
            if (position != 0) {

                spnAllDhePlayerCurrentPosition = position;
                SelectedPlayerforinsertRecordName = dheTeamPlayerList.get(position).getPlayerName();
                SelectedPlayerforinsertRecordID = String.valueOf(dheTeamPlayerList.get(position).getPlayerId());

                String arr[] = SelectedPlayerforinsertRecordName.split(" ", 2);

                String firstWord = arr[0];   //the
                String theRest2 = arr[1];

                binding.toolbar.tvToolbarTitle.setText("Add Match Record of " + firstWord);

                binding.btnaddmatchdetail.setText("Add Record for " + firstWord);


                //quick brown fox
                //              String theRest3 = arr[2];     //quick brown fox
//                binding.tlOpponentscore.setHint(firstWord + " " + theRest2 + " Total Score");
//                binding.tlOpponentover.setHint(firstWord + " " + theRest2 + " Over played");


            } else {
//                binding.tlOpponentscore.setHint("Total Score");
//                binding.tlOpponentover.setHint("Over played");
                spnAllDhePlayerCurrentPosition = position;
            }
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void GetPlayerListDetail_list(List<PlayersListResponse.Datum> list) {

        dheTeamPlayerList.clear();
        dheTeamPlayerList.addAll(list);

        PlayersListResponse.Datum datum = new PlayersListResponse.Datum();
        datum.setPlayerName("Select Dhe Team Player.");
        datum.setPlayerId(0);
        dheTeamPlayerList.add(0, datum);

        spinnerAllDhePlayerAdapter = new SpinnerAllDhePlayerAdapter(getApplicationContext(), dheTeamPlayerList);


        binding.spnteamplayerdhe.setAdapter(spinnerAllDhePlayerAdapter);
    }
}