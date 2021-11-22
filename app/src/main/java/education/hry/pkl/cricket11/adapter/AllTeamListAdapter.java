package education.hry.pkl.cricket11.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.model.AllTeamListResponse;

public class AllTeamListAdapter extends RecyclerView.Adapter<AllTeamListAdapter.ViewHolder> {


    List<AllTeamListResponse.Datum> mValues = new ArrayList<AllTeamListResponse.Datum>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;
    String mrole;

    public AllTeamListAdapter(Context context, ArrayList values, String role, ItemListener itemListener) {

        mrole = role;
        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtName, txtteamLogo;
        public ImageView imageView;
        AllTeamListResponse.Datum item;
        LinearLayout llmain;
        public int currposition;
        CardView maincard;
        Button btnDeleteTeam;

        public ViewHolder(View v) {

            super(v);

            //   v.setOnClickListener(this);
            maincard = v.findViewById(R.id.maincard);
            llmain = v.findViewById(R.id.llmain);
            txtName = (TextView) v.findViewById(R.id.txtName);
            txtteamLogo = (TextView) v.findViewById(R.id.txtteamLogo);
            imageView = (ImageView) v.findViewById(R.id.ivThumb);
            btnDeleteTeam = v.findViewById(R.id.btnDeleteTeam);

            // v.setOnClickListener(this);
            if (mrole.equalsIgnoreCase("Admin")) {

                btnDeleteTeam.setVisibility(View.VISIBLE);
                btnDeleteTeam.setOnClickListener(this);


            }else {
                btnDeleteTeam.setVisibility(View.GONE);


            }



        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void setData(AllTeamListResponse.Datum item, int currposition) {
            this.currposition = currposition;
            this.item = item;


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {


                txtName.setText(Html.fromHtml("  <strong style='color:red';>   " + item.getTeamName() + "  </strong><br>", Html.FROM_HTML_MODE_COMPACT));

                txtteamLogo.setText(Html.fromHtml("  <strong style='color:red';>   " + firstLetterWord(item.getTeamName().replaceAll("[^a-zA-Z0-9]", " ")) + "  </strong><br>", Html.FROM_HTML_MODE_COMPACT));


            } else {


                txtName.setText(Html.fromHtml("<strong style='color:red';>   " + item.getTeamName() + "  </strong><br>"));


                txtteamLogo.setText(Html.fromHtml("<strong style='color:red';>   " + firstLetterWord(item.getTeamName().replaceAll("[^a-zA-Z0-9]", " ")) + "  </strong><br>"));


            }


        }

        // Function to find string which has first
        // character of each word.
        String firstLetterWord(String str) {
            String result = "";

            // Traverse the string.
            boolean v = true;
            for (int i = 0; i < str.length(); i++) {
                // If it is space, set v as true.
                if (str.charAt(i) == ' ') {
                    v = true;
                }

                // Else check if v is true or not.
                // If true, copy character in output
                // string and set v as false.
                else if (str.charAt(i) != ' ' && v == true) {
                    result += (str.charAt(i));
                    v = false;
                }
            }

            return result;
        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.btnDeleteTeam:

                    if (mListener != null) {

                        mListener.onItemClick(item, currposition);


                    }
                    break;
            }


        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item_allteam_row, parent, false);

        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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
        void onItemClick(AllTeamListResponse.Datum item, int currposition);
    }
}