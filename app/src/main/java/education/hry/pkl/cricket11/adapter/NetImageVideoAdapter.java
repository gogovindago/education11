package education.hry.pkl.cricket11.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.Html;
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
import education.hry.pkl.cricket11.model.NetImageVideoResponse;

public class NetImageVideoAdapter extends RecyclerView.Adapter<NetImageVideoAdapter.ViewHolder> {


    ArrayList<NetImageVideoResponse.Datum> mValues = new ArrayList<NetImageVideoResponse.Datum>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;

    public NetImageVideoAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtName;
        public ImageView imageView;
        NetImageVideoResponse.Datum item;
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

        public void setData(NetImageVideoResponse.Datum item, int currposition) {
            this.currposition = currposition;
            this.item = item;



            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {


                txtName.setText(Html.fromHtml("  <strong style='color:red';>   "+item.getTitle()+ "  </strong><br>" + item.getDescription(), Html.FROM_HTML_MODE_COMPACT));



            } else {

                txtName.setText(Html.fromHtml("<strong style='color:red';>   "+item.getTitle()+ "  </strong><br>" + item.getDescription()));
            }



                Glide.with(itemView)
                        .load(item.getFilePath())
                        .fitCenter()
                        .into(imageView);

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
        void onItemClick(NetImageVideoResponse.Datum item, int currposition);
    }
}