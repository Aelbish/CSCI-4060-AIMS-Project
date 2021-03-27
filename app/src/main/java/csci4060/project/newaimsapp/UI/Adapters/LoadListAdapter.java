package csci4060.project.newaimsapp.UI.Adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import csci4060.project.newaimsapp.UI.ViewHolders.LoadViewHolder;
import csci4060.project.newaimsapp.database.entity.Load;

public class LoadListAdapter extends ListAdapter<Load, LoadViewHolder> {

    public LoadListAdapter(@NonNull DiffUtil.ItemCallback<Load> loadItemCallback) {
        super(loadItemCallback);
    }

    @Override
    public LoadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return LoadViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull LoadViewHolder holder, int position) {
        Load current = getItem(position);
        holder.bind(String.valueOf(current.getSequence_number()), current.getWaypoint_description());
    }

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
