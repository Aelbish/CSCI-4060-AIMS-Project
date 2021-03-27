package csci4060.project.newaimsapp.UI.ViewHolders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import csci4060.project.newaimsapp.AIMSApp;
import csci4060.project.newaimsapp.R;

/**
 * This class takes in values from the adapter class through the bind method and sets the text in the appropriate xml file
 * The onclick method is not fully functional yet as the trip_id is not passed into it properly
 */
public class TripViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final TextView tripIdView;
    private final TextView tripNameView;
    private String trip_id = "";

    /**
     * Sets an onclicklistener to tell when the user taps on the trip in the list and it does something as described in the onClick method
     */
    public TripViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        //Finds the textviews in the xml file to set the text
        tripIdView = itemView.findViewById(R.id.trip_id);
        tripNameView = itemView.findViewById(R.id.trip_name);
    }

    //Sets the text we want to show in the xml file
    public void bind(String trip_id, String trip_name) {
        tripIdView.setText("Trip ID: " + trip_id);
        tripNameView.setText(trip_name);
        this.trip_id = trip_id;
    }

    //This will show our xml file to the user
    public static TripViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_trips, parent, false);
        return new TripViewHolder(view);
    }

    //Currently this method will send a popup saying which trip they selected (for debugging purposes)
    // and then it queries the database to set the is_selected field for the trip_id to 1
    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "You selected " + tripNameView.getText(), Toast.LENGTH_LONG).show();
        AIMSApp.repository.updateIsSelected(Integer.parseInt(trip_id));
    }
}
