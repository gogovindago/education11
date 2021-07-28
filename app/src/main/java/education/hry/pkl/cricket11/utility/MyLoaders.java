package education.hry.pkl.cricket11.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import education.hry.pkl.cricket11.R;


public class MyLoaders {
    private Context mContext;
    public static ProgressDialog pd;

    public MyLoaders(Context context){
        this.mContext=context;

    }


    public void showSnackBar(View view, String contentMsg) {
        if (mContext != null && view != null) {
            Snackbar.make(view, contentMsg, Snackbar.LENGTH_LONG)
                    .setAction(mContext.getString(R.string.action_okay),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            })
                    .setActionTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
                    .show();
        }
    }

    public void showprogressdialog(Activity activity){

        try {
            pd=new ProgressDialog(activity);
            pd.setMessage("Loading Data...");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setIndeterminate(true);
            pd.setCanceledOnTouchOutside(false);
            pd.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void dismissdialog(){
        pd.dismiss();
    }

    public boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

}
