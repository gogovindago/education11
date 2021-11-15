package education.hry.pkl.cricket11.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.model.CareerStatisticsResponse;

public class CareerStatisticsAdapter extends RecyclerView.Adapter<CareerStatisticsAdapter.ViewHolder> {
    String imageurl = "https://i.picsum.photos/id/599/200/200.jpg?hmac=2WLKs3sxIsaEQ-6WZaa6YMxgl6ZC4cNnid0aqupm2is";

    ArrayList<CareerStatisticsResponse.Datum> mValues = new ArrayList<CareerStatisticsResponse.Datum>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;

    public CareerStatisticsAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtplayerName, txtInningsvalue,
                txtScorevalue, textCatchesvalue, textRunsvalue, textNotOutsvalue,
                txtBallsvalue, txtFoursvalue, txtSixvalue, txtWicketsvalue, textBowlAveragevalue, textclassvalue,
                txtBatStrikeRatevalue, txtOversvalue, textMaidenvalue;
        public ImageView imageView;
        SimpleDraweeView imgplayername;
        public RelativeLayout relativeLayout;
        CareerStatisticsResponse.Datum item;
        public int currposition;
        Button btnplyrhist;

        public ViewHolder(View v) {

            super(v);

            textNotOutsvalue = (TextView) v.findViewById(R.id.textNotOutsvalue);
            btnplyrhist = v.findViewById(R.id.btnplyrhist);
            textclassvalue = (TextView) v.findViewById(R.id.textclassvalue);
            txtWicketsvalue = (TextView) v.findViewById(R.id.txtWicketsvalue);
            textRunsvalue = (TextView) v.findViewById(R.id.textRunsvalue);
            textMaidenvalue = (TextView) v.findViewById(R.id.textMaidenvalue);
            txtOversvalue = (TextView) v.findViewById(R.id.txtOversvalue);
            txtBatStrikeRatevalue = (TextView) v.findViewById(R.id.txtBatStrikeRatevalue);
            txtSixvalue = (TextView) v.findViewById(R.id.txtSixvalue);
            txtFoursvalue = (TextView) v.findViewById(R.id.txtFoursvalue);
            txtBallsvalue = (TextView) v.findViewById(R.id.txtBallsvalue);
            textCatchesvalue = (TextView) v.findViewById(R.id.textCatchesvalue);
            txtScorevalue = (TextView) v.findViewById(R.id.txtScorevalue);
            txtInningsvalue = (TextView) v.findViewById(R.id.txtInningsvalue);
            txtplayerName = (TextView) v.findViewById(R.id.txtplayerName);
            textBowlAveragevalue = (TextView) v.findViewById(R.id.textBowlAveragevalue);
            imgplayername = (SimpleDraweeView) v.findViewById(R.id.imgplayername);

          //  v.setOnClickListener(this);


            btnplyrhist.setOnClickListener(this);



        }

        @SuppressLint("ResourceAsColor")
        public void setData(CareerStatisticsResponse.Datum item, int currposition) {
            this.currposition = currposition;
            this.item = item;


            String string = item.getPlayerName();
            String[] parts = string.split(" ");
            String part1 = parts[0]; // 004
           // String part2 = parts[1]; // 034556

            btnplyrhist.setText(part1+" indivisual career statistics");
            txtplayerName.setText(String.valueOf(++currposition) + " .  " + item.getPlayerName());
            txtInningsvalue.setText(String.valueOf(item.getInnings()));
            txtScorevalue.setText(String.valueOf(item.getScore()));
            textCatchesvalue.setText(String.valueOf(item.getCatches()));
            txtBallsvalue.setText(String.valueOf(item.getBalls()));
            txtFoursvalue.setText(String.valueOf(item.getFours()));
            txtSixvalue.setText(String.valueOf(item.getSix()));
            txtBatStrikeRatevalue.setText(String.valueOf(item.getBatStrikeRate()));
            txtOversvalue.setText(String.valueOf(item.getOvers()));
            textMaidenvalue.setText(String.valueOf(item.getMaiden()));
            textRunsvalue.setText(String.valueOf(item.getRun()));
            txtWicketsvalue.setText(String.valueOf(item.getWickets()));
            textBowlAveragevalue.setText(String.valueOf(item.getBowlAverage()));
            textclassvalue.setText(String.valueOf(item.getBowlAverage()));
            textNotOutsvalue.setText(String.valueOf(item.getNotOut()));
           /* if (item.getNotOut()==1) {

                //
                textNotOutsvalue.setText("Not Out");
               // textNotOutsvalue.setTextColor( R.color.white);
                textNotOutsvalue.setTextColor(Color.parseColor("#FFFFFF"));

            }else {

                //  textNotOutsvalue.setText(String.valueOf(item.getNotOut()));
                textNotOutsvalue.setText("OUT");
              //  textNotOutsvalue.setTextColor(R.color.red);
                textNotOutsvalue.setTextColor(Color.parseColor("#F00000"));
            }*/

              imgplayername.setImageURI(imageurl);

        }


        @Override
        public void onClick(View view) {


                switch (view.getId()){

                    case R.id.btnplyrhist:

                        if (mListener != null) {

                            mListener.onItemClick(item, currposition,"plyrhist");


                        }
                        break;



            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.career_statistics_row, parent, false);

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
        void onItemClick(CareerStatisticsResponse.Datum item, int currposition,String plyrhist);
    }
}