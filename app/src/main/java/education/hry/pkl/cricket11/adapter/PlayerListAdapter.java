package education.hry.pkl.cricket11.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.model.PlayersListResponse;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder> {

    ArrayList<PlayersListResponse.Datum> mValues = new ArrayList<PlayersListResponse.Datum>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;

    public PlayerListAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView,gpstime;
        public ImageView imageView;
        SimpleDraweeView my_image_view;
        public RelativeLayout relativeLayout;
        PlayersListResponse.Datum item;
        public int currposition;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.txtcaption);
            gpstime = (TextView) v.findViewById(R.id.gpstime);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            my_image_view = (SimpleDraweeView) v.findViewById(R.id.my_image_view);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);

        }

        public void setData(PlayersListResponse.Datum item, int currposition) {
            this.currposition = currposition;
            this.item = item;
            textView.setText(item.getPlayerName());
            my_image_view.setImageURI(item.getFilePath());
        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item, currposition);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_itemuserplantedshown, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        currposition = position;
        holder.setData(mValues.get(position), currposition);

    }


    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(PlayersListResponse.Datum item, int currposition);
    }
}