package education.hry.pkl.cricket11.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.model.PlayersListResponse;


public class SpinnerAllDhePlayerAdapter extends BaseAdapter {
    Context context;
    private List<PlayersListResponse.Datum> statelist = new ArrayList<PlayersListResponse.Datum>();

    LayoutInflater inflter;

    public SpinnerAllDhePlayerAdapter(Context applicationContext, List<PlayersListResponse.Datum> statelist) {
        this.context = applicationContext;
        this.statelist = statelist;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return statelist.size();
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


        if (i == 0) {
            icon.setImageResource(R.drawable.ic_baseline_groups_24);
        } else {

            Glide.with(context).load(statelist.get(i).getFilePath())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(icon);
        }

        names.setText(statelist.get(i).getPlayerName());


        return view;
    }
}