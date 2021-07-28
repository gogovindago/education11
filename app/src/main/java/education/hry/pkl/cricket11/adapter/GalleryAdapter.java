package education.hry.pkl.cricket11.adapter;

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
import education.hry.pkl.cricket11.model.GalleryResponse;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    ArrayList<GalleryResponse.Datum> mValues = new ArrayList<GalleryResponse.Datum>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;

    public GalleryAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView,gpstime;
        public ImageView imageView;
        SimpleDraweeView my_image_view;
        public RelativeLayout relativeLayout;
        GalleryResponse.Datum item;
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

        public void setData(GalleryResponse.Datum item, int currposition) {
            this.currposition = currposition;
            this.item = item;
            textView.setText(item.getPhotoTitle());
            my_image_view.setImageURI(item.getPhotoPath());
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        currposition = position;
        holder.setData(mValues.get(position), currposition);

    }


    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(GalleryResponse.Datum item, int currposition);
    }
}