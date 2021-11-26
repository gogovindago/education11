package education.hry.pkl.cricket11.ui.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import cn.pedant.SweetAlert.SweetAlertDialog;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.databinding.ActivityAddNewTeamBinding;
import education.hry.pkl.cricket11.model.AddNewTeamsRequest;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.GlobalClass;
import education.hry.pkl.cricket11.utility.MyLoaders;
import education.hry.pkl.cricket11.utility.NetworkUtil;

public class AddNewTeamActivity extends BaseActivity {
    ActivityAddNewTeamBinding binding;
    private MyLoaders myLoaders;
    SweetAlertDialog sweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_team);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_team);
        myLoaders = new MyLoaders(getApplicationContext());

    }

    @Override
    public void initData() {


    }

    @Override
    public void initListeners() {

        binding.imageButtonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.btnAddteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalClass.closeKeyboard(AddNewTeamActivity.this);

                if (Check_Data(v)) {

                    sweetAlertDialog = new SweetAlertDialog(AddNewTeamActivity.this);
                    sweetAlertDialog.setTitle("Alert Team Detail Adding !");
                    sweetAlertDialog.setContentText("Make Sure you have filled all detail about team correctly.");
                    sweetAlertDialog.setVolumeControlStream(2);
                    sweetAlertDialog.setCancelable(true);
                    sweetAlertDialog.setCancelText("No");
                    sweetAlertDialog.setCustomImage(R.mipmap.ic_launcher_round);

                    sweetAlertDialog.changeAlertType(3);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                            if (NetworkUtil.isConnected(AddNewTeamActivity.this)) {
                                sweetAlertDialog.dismiss();
                                AddNewTeamsRequest request = new AddNewTeamsRequest();
                                request.setTeamName(binding.edtteamName.getText().toString().trim());
                                request.setTeamGroup("B");


                                WebAPiCall aPiCall = new WebAPiCall();
                                aPiCall.addNewTeamPostDataMethod(AddNewTeamActivity.this, AddNewTeamActivity.this, request);


                            } else {
                                GlobalClass.showtost(AddNewTeamActivity.this, "No Internet Available.Plz check your internet connection.");
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

        if (TextUtils.isEmpty(binding.edtteamName.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Team Name");
            return false;
        }

        return true;
    }
}