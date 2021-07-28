package education.hry.pkl.cricket11.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.model.DataModel;

public class RvNotificationAdapter extends RecyclerView.Adapter<RvNotificationAdapter.ViewHolder> {

    ArrayList<DataModel> mValues = new ArrayList<DataModel>();
    Context mContext;
    protected ItemListener mListener;
    public int currposition;
    public RvNotificationAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int currposition;
        public TextView textView,text;
        public ImageView imageView;
        public LinearLayout ll;
        DataModel item;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.title);
            text = (TextView) v.findViewById(R.id.text);
            imageView = (ImageView) v.findViewById(R.id.image);
            ll = (LinearLayout) v.findViewById(R.id.ll);

        }

        public void setData(DataModel item,  int currposition) {
            this.item = item;
            this.currposition = currposition;

            textView.setText(item.text);
            imageView.setImageResource(item.drawable);
            ll.setBackgroundColor(Color.parseColor(item.color));

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

        View view = LayoutInflater.from(mContext).inflate(R.layout.notification_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        currposition=position;
        holder.setData(mValues.get(position),currposition);

    }


    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(DataModel item, int currposition);
    }
}