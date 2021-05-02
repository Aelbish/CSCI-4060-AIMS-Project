package csci4060.project.aimsmobileapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import csci4060.project.aimsmobileapp.R;
import csci4060.project.aimsmobileapp.UI.Activities.DriverInputSiteActivity;
import csci4060.project.aimsmobileapp.UI.Activities.DriverInputSourceActivity;
import csci4060.project.aimsmobileapp.UI.Activities.TripActivity;
import csci4060.project.aimsmobileapp.UI.Fragments.navigation.RouteFragment;
import csci4060.project.aimsmobileapp.model.TripInfoModel;
public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.ItemsViewHolder> {


    private List<TripInfoModel> tripInfoModelList;
    private Context mcontext;
    private String DestinationType;

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

        //itemsViewHolder.txtTripId.setText("Trip id: " + tripInfoModel.getTripId());
        itemsViewHolder.txtTripName.setText("Trip name: " + tripInfoModel.getTripName());
        itemsViewHolder.txtTripWaypoint.setText("Location Type: " + tripInfoModel.getWaypoint());
        //itemsViewHolder.txtTripSeq.setText("Load: " + tripInfoModel.getSeqNum());
        itemsViewHolder.txtDestinationName.setText("Destination: " + tripInfoModel.getDestinationName());
        itemsViewHolder.txtAddress.setText("Address: " + tripInfoModel.getAddress());

        itemsViewHolder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btn_start){
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new RouteFragment();
                    ((RouteFragment) myFragment).setDestination(tripInfoModel.getLatitude(), tripInfoModel.getLongitude());
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
                }
            }
        });

        itemsViewHolder.btnDisplayForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DestinationType = tripInfoModel.getWaypoint();
                Log.i("Destination", DestinationType);
                if(DestinationType.equals("Source")){
                    if(v.getId()==R.id.btn_display_form){
                        Intent driverSourceInput= new Intent(mcontext, DriverInputSourceActivity.class);
                        driverSourceInput.putExtra("TripId", String.valueOf(tripInfoModel.getTripId()));
                        driverSourceInput.putExtra("SeqNum", String.valueOf(tripInfoModel.getSeqNum()));

                        driverSourceInput.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mcontext.startActivity(driverSourceInput);
                    }
                }
                else if(DestinationType.equals("Site Container")){
                    if(v.getId()==R.id.btn_display_form){
                        Intent driverSiteInput= new Intent(mcontext, DriverInputSiteActivity.class);
                        driverSiteInput.putExtra("TripId", String.valueOf(tripInfoModel.getTripId()));
                        driverSiteInput.putExtra("SeqNum", String.valueOf(tripInfoModel.getSeqNum()));

                        driverSiteInput.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mcontext.startActivity(driverSiteInput);
                    }
                }
            }
        });

        itemsViewHolder.btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn_summary) {
                    Intent intent = new Intent(mcontext, TripActivity.class);
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

        public TextView txtTripName, txtTripWaypoint, txtDestinationName, txtAddress;
        public Button btnSummary, btnStart, btnDisplayForm;

        public ItemsViewHolder(@NonNull View itemView) {

            super(itemView);

            //txtTripId = itemView.findViewById(R.id.txtTripId);
            txtTripName = itemView.findViewById(R.id.txtTripName);
            txtTripWaypoint = itemView.findViewById(R.id.txtTripWaypoint);
            //txtTripSeq = itemView.findViewById(R.id.txtTripSeq);
            txtDestinationName = itemView.findViewById(R.id.txtDestinationName);
            txtAddress = itemView.findViewById(R.id.txtAddress);

            btnSummary = itemView.findViewById(R.id.btn_summary);
            btnStart = itemView.findViewById(R.id.btn_start);
            btnDisplayForm = itemView.findViewById(R.id.btn_display_form);

        }

    }
}