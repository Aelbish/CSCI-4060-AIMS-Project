package csci4060.project.newaimsapp.UI.Adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import csci4060.project.newaimsapp.UI.ViewHolders.LoadViewHolder;
import csci4060.project.newaimsapp.database.entity.Load;

/**
 * This class does two things: 1. it provides a subclass to check the difference between the previous and current item
 * to make sure that there are no duplicate list items
 *                             2. it gets a list of all the trips and iterates through them and then passes off the data
 * we want to show to the viewholder class for it to set the text in the xml file
 */
public class LoadListAdapter extends ListAdapter<Load, LoadViewHolder> {

    public LoadListAdapter(@NonNull DiffUtil.ItemCallback<Load> loadItemCallback) {
        super(loadItemCallback);
    }

    @Override
    public LoadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return LoadViewHolder.create(parent);
    }

    /**
     * For the current item it is iterating on, it will call the bind method of the viewholder to set the text so the user can see it
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull LoadViewHolder holder, int position) {
        Load current = getItem(position);
        holder.bind(String.valueOf(current.getSequence_number()), current.getWaypoint_description());
    }

    /**
     * Subclass to see if the items object ids are the same and then checks to see if their primary key is the same
     */
    public static class LoadDiff extends DiffUtil.ItemCallback<Load> {
        @Override
        public boolean areItemsTheSame(@NonNull Load oldItem, @NonNull Load newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Load oldItem, @NonNull Load newItem) {
            return oldItem.getSequence_number() == newItem.getSequence_number();
        }
    }
}
