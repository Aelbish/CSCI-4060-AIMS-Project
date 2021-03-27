package csci4060.project.newaimsapp.UI.ViewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import csci4060.project.newaimsapp.R;

public class TripViewHolder extends RecyclerView.ViewHolder {

    private final TextView tripIdView;
    private final TextView tripNameView;

    public TripViewHolder(@NonNull View itemView) {
        super(itemView);

        tripIdView = itemView.findViewById(R.id.trip_id);
        tripNameView = itemView.findViewById(R.id.trip_name);
    }

    public void bind(String trip_id, String trip_name) {
        tripIdView.setText(trip_id);
        tripNameView.setText(trip_name);
    }

    public static TripViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_trips, parent, false);
        return new TripViewHolder(view);
    }
}
