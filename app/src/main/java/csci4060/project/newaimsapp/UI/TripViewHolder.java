package csci4060.project.newaimsapp.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import csci4060.project.newaimsapp.R;

public class TripViewHolder extends RecyclerView.ViewHolder {

    private final TextView tripItemView;

    public TripViewHolder(@NonNull View itemView) {
        super(itemView);

        tripItemView = itemView.findViewById(R.id.tripTextView);
    }

    public void bind(String text) {
        tripItemView.setText(text);
    }

    static TripViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_trip, parent, false);
        return new TripViewHolder(view);
    }
}
