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
import education.hry.pkl.cricket11.model.GetPlantDetailsResponse;

public class UserAllplantedPlantAdapter extends RecyclerView.Adapter<UserAllplantedPlantAdapter.ViewHolder> {

    ArrayList<GetPlantDetailsResponse.GetPlantDetailsData> mValues = new ArrayList<GetPlantDetailsResponse.GetPlantDetailsData>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;

    public UserAllplantedPlantAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView,gpstime;
        public ImageView imageView;
        SimpleDraweeView my_image_view;
        public RelativeLayout relativeLayout;
        GetPlantDetailsResponse.GetPlantDetailsData item;
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

        public void setData(GetPlantDetailsResponse.GetPlantDetailsData item, int currposition) {
            this.currposition = currposition;
            this.item = item;
            textView.setText(item.getDescription());
           // imageView.setImageResource(item.drawable);

           // my_image_view.setImageResource(item.drawable);
            my_image_view.setImageURI(item.getImagePath());


           // SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
           // String currentDateandTime = sdf.format(item.getImageDate());
            gpstime.setText("Time:- " + item.getImageDate());

          //  relativeLayout.setBackgroundColor(Color.parseColor(item.color));

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
        void onItemClick(GetPlantDetailsResponse.GetPlantDetailsData item, int currposition);
    }
}