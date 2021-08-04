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

    public PlayerHistoryAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView,gpstime;
        public ImageView imageView;
        SimpleDraweeView my_image_view;
        public RelativeLayout relativeLayout;
        PlayerHistoryResponse.Datum item;
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

        public void setData(PlayerHistoryResponse.Datum item, int currposition) throws Exception {
            this.currposition = currposition;
            this.item = item;




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

            textView.setText(item.getPlayerName()+"("+ c.get(Calendar.YEAR)+")");
           // textView.setText(item.getPlayerName()+"("+ String.valueOf(getAge(c))+")"+"\n"+ item.getPlayingRole());
           // my_image_view.setImageURI(item.getFilePath());





        }

        // Returns age given the date of birth
        public  int getAge(Calendar dob) throws Exception {
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