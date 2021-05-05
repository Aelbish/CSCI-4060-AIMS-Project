package csci4060.project.aimsmobileapp.adapter;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import csci4060.project.aimsmobileapp.R;
import csci4060.project.aimsmobileapp.UI.Activities.TripDeliveriesActivity;
import csci4060.project.aimsmobileapp.model.TripInfoModel;

public class TripListAdapterWithoutDeliveries extends RecyclerView.Adapter<TripListAdapterWithoutDeliveries.ItemsViewHolder> {


    private List<TripInfoModel> tripInfoModelList;
    private Context mcontext;
    private String DestinationType;

    public TripListAdapterWithoutDeliveries(List<TripInfoModel> tripInfoModelList, Context context) {
        this.tripInfoModelList = tripInfoModelList;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public TripListAdapterWithoutDeliveries.ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sample_trips_list_without_deliveries, viewGroup, false);

        return new ItemsViewHolder(itemView);
    }

    //    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ItemsViewHolder itemsViewHolder, int i) {
        final TripInfoModel tripInfoModel = tripInfoModelList.get(i);

        itemsViewHolder.txtTripName.setText("Trip name: " + tripInfoModel.getTripName());



        itemsViewHolder.btn_trip_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn_delivery_details) {
                    Intent intent = new Intent(mcontext, TripDeliveriesActivity.class);
                    intent.putExtra("TripId", String.valueOf(tripInfoModel.getTripId()));
                    intent.putExtra("DriverCode", tripInfoModel.getDriverCode());
                    intent.putExtra("DriverName", tripInfoModel.getDriverName());
                    intent.putExtra("TruckId", Integer.toString(tripInfoModel.getTruckId()));
                    intent.putExtra("TruckCode", tripInfoModel.getTruckCode());
                    intent.putExtra("TruckDesc", tripInfoModel.getTruckDesc());
                    intent.putExtra("TrailerId", Integer.toString(tripInfoModel.getTrailerId()));
                    intent.putExtra("TrailerCode", tripInfoModel.getTrailerCode());
                    intent.putExtra("TrailerDesc", tripInfoModel.getTrailerDesc());
                    intent.putExtra("TripName", tripInfoModel.getTripName());
                    intent.putExtra("TripDate", tripInfoModel.getTripDate());
                    intent.putExtra("DestinationCode", tripInfoModel.getDestinationCode());
                    intent.putExtra("DestinationName", tripInfoModel.getDestinationName());
                    intent.putExtra("Address", tripInfoModel.getAddress());
                    intent.putExtra("City", tripInfoModel.getCity());
                    intent.putExtra("PostalCode", Integer.toString(tripInfoModel.getPostalCode()));
                    intent.putExtra("ProductId", Integer.toString(tripInfoModel.getProductId()));
                    intent.putExtra("ProductCode", tripInfoModel.getProductCode());
                    intent.putExtra("ProductDesc", tripInfoModel.getProductDesc());
                    intent.putExtra("RequestedQty", Double.toString(tripInfoModel.getRequestedQty()));
                    intent.putExtra("UOM", tripInfoModel.getUOM());
                    intent.putExtra("Fill", tripInfoModel.getFill());

                    intent.putExtra("Latitude", tripInfoModel.getLatitude());
                    intent.putExtra("Longitude", tripInfoModel.getLongitude());

//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);
                }
            }
        });

    }

    private AppCompatActivity unwrap(Context context) {
        while (!(context instanceof AppCompatActivity) && context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }

        return (AppCompatActivity) context;
    }

    @Override
    public int getItemCount() {
        return tripInfoModelList.size();
    }


    public class ItemsViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTripName;
        public Button  btn_trip_details;
        public LinearLayout cardViewForTrips;

        public ItemsViewHolder(@NonNull View itemView) {

            super(itemView);

            cardViewForTrips=itemView.findViewById(R.id.cardViewForTrips);

            txtTripName = itemView.findViewById(R.id.txtTripName);


            btn_trip_details = itemView.findViewById(R.id.btn_delivery_details);

        }

    }
}