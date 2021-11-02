package education.hry.pkl.cricket11.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.model.NetpracticeTypeData;

public class NetpracticeTypeAdapter extends BaseAdapter {
    Context context;
    int flags[];
    ArrayList<NetpracticeTypeData> NetpracticeTypeList;
    LayoutInflater inflter;

    public NetpracticeTypeAdapter(Context applicationContext, ArrayList<NetpracticeTypeData> NetpracticeTypeList) {
        this.context = applicationContext;
        this.flags = flags;
        this.NetpracticeTypeList = NetpracticeTypeList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return NetpracticeTypeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        icon.setImageResource(NetpracticeTypeList.get(i).getImage());
        names.setText(NetpracticeTypeList.get(i).getName());
        return view;
    }
}