package csci4060.project.newaimsapp.UI.Adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import csci4060.project.newaimsapp.UI.ViewHolders.TripViewHolder;
import csci4060.project.newaimsapp.database.entity.Trip;

/**
 * This class does two things: 1. it provides a subclass to check the difference between the previous and current item
 * to make sure that there are no duplicate list items
 *                             2. it gets a list of all the trips and iterates through them and then passes off the data
 * we want to show to the viewholder class for it to set the text in the xml file
 */
public class TripListAdapter extends ListAdapter<Trip, TripViewHolder> {


    public TripListAdapter(@NonNull DiffUtil.ItemCallback<Trip> tripItemCallback) {
        super(tripItemCallback);
    }

    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TripViewHolder.create(parent);
    }

    /**
     * For the current item it is iterating on, it will call the bind method of the viewholder to set the text so the user can see it
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(TripViewHolder holder, int position) {
        Trip current = getItem(position);
        holder.bind(String.valueOf(current.getTrip_id()), current.getTrip_name());
    }

    /**
     * Subclass to see if the items object ids are the same and then checks to see if their primary key is the same
     */
    public static class TripDiff extends DiffUtil.ItemCallback<Trip> {

        @Override
        public boolean areItemsTheSame(@NonNull Trip oldItem, @NonNull Trip newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Trip oldItem, @NonNull Trip newItem) {
            return oldItem.getTrip_id() == newItem.getTrip_id();
        }
    }
}
