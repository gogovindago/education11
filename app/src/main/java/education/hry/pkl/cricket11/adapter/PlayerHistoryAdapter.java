package education.hry.pkl.cricket11.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.model.PlayerHistoryResponse;

public class PlayerHistoryAdapter extends RecyclerView.Adapter<PlayerHistoryAdapter.ViewHolder> {

    ArrayList<PlayerHistoryResponse.Datum> mValues = new ArrayList<PlayerHistoryResponse.Datum>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;
    String mrole;

    public PlayerHistoryAdapter(Context context, ArrayList values, String role, ItemListener itemListener) {

        mrole = role;
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

        public TextView textView;
        public RelativeLayout relativeLayout;
        PlayerHistoryResponse.Datum item;

        public int currposition;
        Button btndeleterecord;
        LinearLayout llrecordDelete;

        public ViewHolder(View v) {

            super(v);


            llrecordDelete = v.findViewById(R.id.llrecordDelete);
            btndeleterecord = v.findViewById(R.id.btndeleterecord);
            textNotOutsvalue = (TextView) v.findViewById(R.id.textNotOutsvalue);
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
            //   imageView = (ImageView) v.findViewById(R.id.imageView);
            imgplayername = (SimpleDraweeView) v.findViewById(R.id.imgplayername);


            // v.setOnClickListener(this);
            if (mrole.equalsIgnoreCase("Admin")) {

                llrecordDelete.setVisibility(View.VISIBLE);
                btndeleterecord.setOnClickListener(this);


            }else {
                llrecordDelete.setVisibility(View.GONE);


            }
        }

        @SuppressLint("ResourceAsColor")
        public void setData(PlayerHistoryResponse.Datum item, int currposition) throws Exception {
            this.currposition = currposition;
            this.item = item;

            txtplayerName.setText(String.valueOf(++currposition) + ". " + "Vs " + String.valueOf(item.getVersus() + " | " + item.getDate()));
            // txtInningsvalue.setText("Vs "+String.valueOf(item.getVersus()+ " | "+item.getDate()));
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
            //textBowlAveragevalue.setText(String.valueOf(item.getBowlAverage()));
            // textclassvalue.setText(String.valueOf(item.getBowlAverage()));
            if (item.getNotOut() == 1) {

                //  textNotOutsvalue.setText(String.valueOf(item.getNotOut()));
                textNotOutsvalue.setText("Not Out");
                textNotOutsvalue.setTextColor(Color.parseColor("#FFFFFF"));


            } else {

                //  textNotOutsvalue.setText(String.valueOf(item.getNotOut()));
                textNotOutsvalue.setText("OUT");
                textNotOutsvalue.setTextColor(Color.parseColor("#F00000"));

            }


            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date parse = null;
            try {
                parse = sdf.parse(item.getDob());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar c = Calendar.getInstance();
            c.setTime(parse);
            System.out.println(c.get(Calendar.MONTH) + c.get(Calendar.DATE) + c.get(Calendar.YEAR));
//getAge(c);

            textView.setText(item.getPlayerName() + "(" + c.get(Calendar.YEAR) + ")");
            // textView.setText(item.getPlayerName()+"("+ String.valueOf(getAge(c))+")"+"\n"+ item.getPlayingRole());
            // my_image_view.setImageURI(item.getFilePath());


        }

        // Returns age given the date of birth
        public int getAge(Calendar dob) throws Exception {
            Calendar today = Calendar.getInstance();

            int curYear = today.get(Calendar.YEAR);
            int dobYear = dob.get(Calendar.YEAR);

            int age = curYear - dobYear;

            // if dob is month or day is behind today's month or day
            // reduce age by 1
            int curMonth = today.get(Calendar.MONTH);
            int dobMonth = dob.get(Calendar.MONTH);
            if (dobMonth > curMonth) { // this year can't be counted!
                age--;
            } else if (dobMonth == curMonth) { // same month? check for day
                int curDay = today.get(Calendar.DAY_OF_MONTH);
                int dobDay = dob.get(Calendar.DAY_OF_MONTH);
                if (dobDay > curDay) { // this year can't be counted!
                    age--;
                }
            }

            return age;
        }


        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.btndeleterecord:

                    if (mListener != null) {

                        mListener.onItemClick(item, currposition);


                    }
                    break;
            }


        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.plyr_history_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        currposition = position;
        try {
            holder.setData(mValues.get(position), currposition);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(PlayerHistoryResponse.Datum item, int currposition);
    }
}