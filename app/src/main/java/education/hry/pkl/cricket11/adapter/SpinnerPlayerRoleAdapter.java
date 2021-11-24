package education.hry.pkl.cricket11.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.model.GetPlayerRoleResponse;


public class SpinnerPlayerRoleAdapter extends BaseAdapter {
    Context context;
    private List<GetPlayerRoleResponse.Datum> PlayerRoleList = new ArrayList<GetPlayerRoleResponse.Datum>();

    LayoutInflater inflter;

    public SpinnerPlayerRoleAdapter(Context applicationContext, List<GetPlayerRoleResponse.Datum> PlayerRoleList) {
        this.context = applicationContext;
        this.PlayerRoleList = PlayerRoleList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return PlayerRoleList.size();
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


        icon.setImageResource(R.drawable.ic_baseline_groups_24);


        names.setText(PlayerRoleList.get(i).getPlayerRole());
        return view;
    }
}