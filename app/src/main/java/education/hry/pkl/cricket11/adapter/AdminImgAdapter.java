package education.hry.pkl.cricket11.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.model.BannerResponse;

public class AdminImgAdapter extends RecyclerView.Adapter<AdminImgAdapter.ViewHolder> {


    ArrayList<BannerResponse.DashboardOfficer> mValues = new ArrayList<BannerResponse.DashboardOfficer>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;

    public AdminImgAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtName;
        public ImageView imageView;
        BannerResponse.DashboardOfficer item;
        LinearLayout llmain;
        public int currposition;
        CardView maincard;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            maincard =  v.findViewById(R.id.maincard);
            llmain =  v.findViewById(R.id.llmain);
            txtName = (TextView) v.findViewById(R.id.txtName);
            imageView = (ImageView) v.findViewById(R.id.ivThumb);


        }

        public void setData(BannerResponse.DashboardOfficer item, int currposition) {
            this.currposition = currposition;
            this.item = item;
            txtName.setText(item.getPlayerName()+"\n"+item.getDesignation());
            if (item.getPlayerId() == 1017) {
                maincard.setBackgroundResource(R.drawable.edit_text_borderdash);
               // imageView.setImageResource(item.drawable);
                Glide.with(itemView)
                        .load(item.getFilePath())
                        .fitCenter()
                        .into(imageView);

            } else {
                Glide.with(itemView)
                        .load(item.getFilePath())
                        .fitCenter()
                        .into(imageView);

            }
            //  imageView.setImageResource(item.drawable);

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onadminimgItemClick(item, currposition);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_itemadminimg_row, parent, false);

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
        void onadminimgItemClick(BannerResponse.DashboardOfficer item, int currposition);
    }
}