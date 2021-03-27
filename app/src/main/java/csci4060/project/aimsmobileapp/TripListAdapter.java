package csci4060.project.aimsmobileapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


        itemsViewHolder.txtTripId.setText("Trip id: " + tripInfoModel.getTripId());
        itemsViewHolder.txtTripName.setText("Trip name: " + tripInfoModel.getTripName());
        itemsViewHolder.btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn_summary) {
                    Intent intent = new Intent(mcontext, TripActivity.class);
                    intent.putExtra("TripId", String.valueOf(tripInfoModel.getTripId()));
                    intent.putExtra("DriverCode", tripInfoModel.getDriverCode());
                    intent.putExtra("DriverName", tripInfoModel.getDriverName());
                    intent.putExtra("TruckId", tripInfoModel.getTruckId());
                    intent.putExtra("TruckCode", tripInfoModel.getTruckCode());
                    intent.putExtra("TruckDesc", tripInfoModel.getTruckDesc());
                    intent.putExtra("TrailerId", tripInfoModel.getTrailerId());
                    intent.putExtra("TrailerCode", tripInfoModel.getTrailerCode());
                    intent.putExtra("TrailerDesc", tripInfoModel.getTrailerDesc());
                    intent.putExtra("TripName", tripInfoModel.getTripName());
                    intent.putExtra("TripDate", tripInfoModel.getTripDate());
                    intent.putExtra("DestinationCode", tripInfoModel.getDestinationCode());
                    intent.putExtra("Address", tripInfoModel.getAddress());
                    intent.putExtra("City", tripInfoModel.getCity());
                    intent.putExtra("PostalCode", tripInfoModel.getPostalCode());
                    intent.putExtra("ProductId", tripInfoModel.getProductId());
                    intent.putExtra("ProductDesc", tripInfoModel.getProductDesc());
                    intent.putExtra("RequestedQty", tripInfoModel.getRequestedQty());
                    intent.putExtra("UOM", tripInfoModel.getUOM());
                    intent.putExtra("Fill", tripInfoModel.getFill());

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return tripInfoModelList.size();
    }


    public class ItemsViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTripId, txtTripName;
        public Button btnSummary;

        public ItemsViewHolder(@NonNull View itemView) {

            super(itemView);

            txtTripId = itemView.findViewById(R.id.txtTripId);
            txtTripName = itemView.findViewById(R.id.txtTripName);
            btnSummary = itemView.findViewById(R.id.btn_summary);


        }

    }
}