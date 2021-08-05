package education.hry.pkl.cricket11.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.model.MatchDetailResponse;

public class MatchesDetailAdapter extends RecyclerView.Adapter<MatchesDetailAdapter.ViewHolder> {


    ArrayList<MatchDetailResponse.Datum> mValues = new ArrayList<MatchDetailResponse.Datum>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;

    public MatchesDetailAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtName, txtteamA, txtTeamAscore,txtteamB,txtTeamBscore,txtResult;
        public CircleImageView imageView;
        MatchDetailResponse.Datum item;
        RelativeLayout llmain;
        public int currposition;
        CardView maincard;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            maincard = v.findViewById(R.id.maincard);
            llmain = v.findViewById(R.id.llmain);
            txtName = v.findViewById(R.id.txtName);
            txtteamA = v.findViewById(R.id.txtteamA);
            txtteamB = v.findViewById(R.id.txtteamB);
            txtTeamAscore = v.findViewById(R.id.txtTeamAscore);
            txtTeamBscore = v.findViewById(R.id.txtTeamBscore);
            txtResult = v.findViewById(R.id.txtResult);
            imageView = v.findViewById(R.id.ivThumb);


        }

        public void setData(MatchDetailResponse.Datum item, int currposition) {
            this.currposition = currposition;
            this.item = item;
            txtName.setText(item.getMatchTitle() + " | " + item.getMatchDate());

            txtTeamAscore.setText(item.getScoreTeam1() + " - " + item.getWicketsTeam1() + "(" + item.getOverTeam1() + ")");
            txtTeamBscore.setText(item.getScoreTeam2() + " - " + item.getWicketsTeam2() + "(" + item.getOverTeam2() + ")");
            txtteamA.setText(item.getTeam1());
            txtteamB.setText(item.getVersusTeam2());
            txtResult.setText(item.getResultRemarks());
           /* if (item.getPlayerId() == 1017) {
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

            }*/
            //  imageView.setImageResource(item.drawable);

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

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_itemrecentmatchimg_row, parent, false);

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
        void onItemClick(MatchDetailResponse.Datum item, int currposition);
    }
}