package csci4060.project.aimsmobileapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private final TextView userItemView;

    private UserViewHolder(View itemView) {
        super(itemView);
        userItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        userItemView.setText(text);
    }

    static UserViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new UserViewHolder(view);
    }
}
