package csci4060.project.aimsmobileapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.ItemsViewHolder> {


    private List<TripInfoModel> tripInfoModelList;
    private Context mcontext;

    public TripListAdapter(List<TripInfoModel> tripInfoModelList, Context context) {
        this.tripInfoModelList = tripInfoModelList;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public TripListAdapter.ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sample_trips_list, viewGroup, false);

        return new ItemsViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ItemsViewHolder itemsViewHolder, int i) {
        final TripInfoModel tripInfoModel = tripInfoModelList.get(i);


        itemsViewHolder.txtTripId.setText("Trip id: "+tripInfoModel.getTripId());
        itemsViewHolder.txtTripName.setText("Trip name: "+tripInfoModel.getTripName());


    }

    @Override
    public int getItemCount() {
        return tripInfoModelList.size();
    }


    public class ItemsViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTripId, txtTripName;

        public ItemsViewHolder(@NonNull View itemView) {

            super(itemView);

            txtTripId = itemView.findViewById(R.id.txtTripId);
            txtTripName = itemView.findViewById(R.id.txtTripName);
        }

    }
}
