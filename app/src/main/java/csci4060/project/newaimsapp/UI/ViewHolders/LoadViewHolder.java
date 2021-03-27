package csci4060.project.newaimsapp.UI.ViewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import csci4060.project.newaimsapp.R;

public class LoadViewHolder extends RecyclerView.ViewHolder {

    private final TextView loadSequenceNumberView;
    private final TextView loadWaypointDescriptionView;

    public LoadViewHolder(@NonNull View itemView) {
        super(itemView);

        loadSequenceNumberView = itemView.findViewById(R.id.load_sequence_number);
        loadWaypointDescriptionView = itemView.findViewById(R.id.load_destination);
    }

    public void bind(String load_sequence_number, String load_destination) {
        loadSequenceNumberView.setText(load_sequence_number);
        loadWaypointDescriptionView.setText(load_destination);
    }

    public static LoadViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_loads, parent, false);
        return new LoadViewHolder(view);
    }
}
