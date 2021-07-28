package education.hry.pkl.cricket11.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.model.BannerResponse;

public class TopBowlAvgAdapter extends RecyclerView.Adapter<TopBowlAvgAdapter.ViewHolder> {


    ArrayList<BannerResponse.TopBowl> mValues = new ArrayList<BannerResponse.TopBowl>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;

    public TopBowlAvgAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtName, txtsr, topbatingStrrate, topbatingAvg, topbatingScore, topbatingings;
        public CircleImageView imageView;
        BannerResponse.TopBowl item;

        public int currposition;
        CardView maincard;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            maincard = v.findViewById(R.id.maincard);

            txtName = v.findViewById(R.id.txtName);
            txtsr = v.findViewById(R.id.txtsr);
            topbatingAvg = v.findViewById(R.id.topbatingAvg);
            topbatingStrrate = v.findViewById(R.id.topbatingStrrate);
            topbatingScore = v.findViewById(R.id.topbatingScore);
            topbatingings = v.findViewById(R.id.topbatingings);
            imageView = v.findViewById(R.id.imageView);


        }

        public void setData(BannerResponse.TopBowl item, int currposition) {
            this.currposition = currposition;
            this.item = item;
           /* if (currposition % 2 == 0) {

                maincard.setBackgroundResource(R.drawable.edit_text_borderdash);
            }*/
            String srno = String.valueOf(currposition + 1 + ".");
            txtsr.setText(srno);




            String string = item.getPlayerName();
            String[] parts = string.split(" ");
            String part1 = parts[0]; // 004
            String part2 = parts[1]; // 034556

            txtName.setText(part1);
           //txtName.setText(item.getPlayerName());

            topbatingStrrate.setText(item.getEcoRate());
            topbatingScore.setText(item.getWickets());

            topbatingAvg.setText(item.getBowlingAverage());
            topbatingings.setText(item.getInnings());


            Glide.with(itemView)
                    .load(item.getFilePath())
                    .fitCenter()
                    .into(imageView);


        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onTopBowltingAvgItemClick(item, currposition);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item_top_five_batsman_row, parent, false);

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
        void onTopBowltingAvgItemClick(BannerResponse.TopBowl item, int currposition);
    }
}