package csci4060.project.newaimsapp.UI;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import csci4060.project.newaimsapp.database.entity.Trip;

public class TripListAdapter extends ListAdapter<Trip, TripViewHolder> {

    public TripListAdapter(@NonNull DiffUtil.ItemCallback<Trip> tripItemCallback) {
        super(tripItemCallback);
    }

    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TripViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(TripViewHolder holder, int position) {
        Trip current = getItem(position);
        holder.bind(String.valueOf(current.getTrip_id()));
    }

    static class TripDiff extends DiffUtil.ItemCallback<Trip> {

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
