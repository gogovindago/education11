package education.hry.pkl.cricket11.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.utility.DataModelLeft;

public class DrawerItemCustomAdapter extends ArrayAdapter<DataModelLeft> {

    Context mContext;
    int layoutResourceId;
    DataModelLeft data[] = null;


    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, DataModelLeft[] data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        View listItem = convertView;


        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);
        RelativeLayout rrbg = (RelativeLayout) listItem.findViewById(R.id.rrbg2);

        DataModelLeft folder = data[position];
        if (folder.name.equalsIgnoreCase("Logout")) {



            final int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {

                rrbg.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.logout_border) );
            } else {
                rrbg.setBackground(ContextCompat.getDrawable(mContext, R.drawable.logout_border));
            }
        }


        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);

        return listItem;
    }
}
