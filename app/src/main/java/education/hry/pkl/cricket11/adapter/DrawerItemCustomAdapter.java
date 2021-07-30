package education.hry.pkl.cricket11.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.model.DataModelLeftNew;

public class DrawerItemCustomAdapter extends ArrayAdapter<DataModelLeftNew> {

    Context mContext;
    int layoutResourceId;
    List<DataModelLeftNew> data ;

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, List<DataModelLeftNew> data) {

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

        DataModelLeftNew folder = data.get(position);


        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);

        return listItem;
    }
}