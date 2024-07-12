package com.example.uitmsa_sport;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.SportViewHolder> {
    private List<Sport> sports;

    public SportAdapter(List<Sport> sports) {
        this.sports = sports;
    }

    @NonNull
    @Override
    public SportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sport_item, parent, false);
        return new SportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportViewHolder holder, int position) {
        Sport sport = sports.get(position);
        holder.tvSportName.setText(sport.getName());
        holder.ivSportImage.setImageResource(sport.getImageResId());
        holder.tvSportDescription.setText(sport.getDescription());
    }

    @Override
    public int getItemCount() {
        return sports.size();
    }

    public static class SportViewHolder extends RecyclerView.ViewHolder {
        TextView tvSportName;
        ImageView ivSportImage;
        TextView tvSportDescription;

        public SportViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSportName = itemView.findViewById(R.id.tvSportName);
            ivSportImage = itemView.findViewById(R.id.ivSportImage);
            tvSportDescription = itemView.findViewById(R.id.tvSportDescription);
        }
    }
}
