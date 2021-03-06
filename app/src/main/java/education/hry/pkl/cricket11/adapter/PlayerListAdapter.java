package education.hry.pkl.cricket11.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.model.PlayersListResponse;
import education.hry.pkl.cricket11.ui.Activity.PlayerDetailActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder> {

    ArrayList<PlayersListResponse.Datum> mValues = new ArrayList<PlayersListResponse.Datum>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;
    String mrole;

    public PlayerListAdapter(Context context, ArrayList values, String role, ItemListener itemListener) {
        mrole = role;
        mValues = values;
        mContext = context;
        mListener = itemListener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView, txtdeleteplayer, txtApproveplayer;
        public ImageView imageView, imgZoom;
        SimpleDraweeView my_image_view;
        public RelativeLayout relativeLayout;
        PlayersListResponse.Datum item;
        public int currposition;
        ImageView imgApproveplayer;

        public ViewHolder(View v) {

            super(v);

//            v.setOnClickListener(this);
            txtApproveplayer = (TextView) v.findViewById(R.id.txtApproveplayer);
            textView = (TextView) v.findViewById(R.id.txtcaption);
            txtdeleteplayer = (TextView) v.findViewById(R.id.txtdeleteplayer);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            imgZoom = (ImageView) v.findViewById(R.id.imgZoom);
            my_image_view = (SimpleDraweeView) v.findViewById(R.id.my_image_view);
            imgApproveplayer = v.findViewById(R.id.imgApproveplayer);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);

            imgZoom.setOnClickListener(this);
            textView.setOnClickListener(this);










            if (mrole.equalsIgnoreCase("Admin")) {


               // txtApproveplayer.setVisibility(View.VISIBLE);
                txtdeleteplayer.setVisibility(View.VISIBLE);
              //  imgApproveplayer.setVisibility(View.VISIBLE);

                txtdeleteplayer.setOnClickListener(this);
                txtApproveplayer.setOnClickListener(this);


            } else {
             //   imgApproveplayer.setVisibility(View.GONE);


                txtdeleteplayer.setVisibility(View.GONE);
                txtApproveplayer.setVisibility(View.GONE);


            }

        }

        public void setData(PlayersListResponse.Datum item, int currposition) throws Exception {
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

            //  textView.setText(item.getPlayerName() + "(" + c.get(Calendar.YEAR) + ")");
            textView.setText(item.getPlayerName() + "(" + String.valueOf(getAge(c)) + ")" + "\n" + item.getPlayingRole());
            my_image_view.setImageURI(item.getFilePath());

            if (item.getIsApproved().equalsIgnoreCase("1")) {

                imgApproveplayer.setVisibility(View.VISIBLE);

            } else {

                imgApproveplayer.setVisibility(View.GONE);



            }




            if (mrole.equalsIgnoreCase("Admin")) {

                if (item.getIsApproved().equalsIgnoreCase("1")) {

                  //  txtApproveplayer.setBackground(ContextCompat.getDrawable(mContext, R.drawable.green_button_background));
                   // txtApproveplayer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.verified, 0, 0, 0);

                    imgApproveplayer.setVisibility(View.VISIBLE);
//                    txtApproveplayer.setText("Verified");
//                    txtApproveplayer.setClickable(false);
//                    txtApproveplayer.setEnabled(false);


                } else {

                    imgApproveplayer.setVisibility(View.GONE);
                    txtApproveplayer.setVisibility(View.VISIBLE);
                   // txtApproveplayer.setBackground(ContextCompat.getDrawable(mContext, R.drawable.buttonstylered));

                   // txtApproveplayer.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    txtApproveplayer.setText("Verify");
                    txtApproveplayer.setClickable(true);
                    txtApproveplayer.setEnabled(true);

                }


            }

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

                case R.id.txtdeleteplayer:

                    if (mListener != null) {

                        mListener.onItemClick(item, currposition);


                    }
                    break;

                case R.id.imgZoom:

                    if (mListener != null) {

                        mListener.onItemZoom(item, currposition);


                    }
                    break;


                case R.id.txtApproveplayer:

                    if (mListener != null) {

                        mListener.onPlayerApprovalItemClick(item, currposition);


                    }
                    break;

                    case R.id.txtcaption:

                    if (mListener != null) {

                        mListener.onItemClickForDetail(item, currposition);


                    }
                    break;
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_player_row, parent, false);

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
        void onItemClick(PlayersListResponse.Datum item, int currposition);
        void onItemClickForDetail(PlayersListResponse.Datum item, int currposition);

        void onPlayerApprovalItemClick(PlayersListResponse.Datum item, int currposition);

        void onItemZoom(PlayersListResponse.Datum item, int currposition);
    }
}