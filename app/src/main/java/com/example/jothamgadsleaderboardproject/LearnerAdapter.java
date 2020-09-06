package com.example.jothamgadsleaderboardproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LearnerAdapter extends  RecyclerView.Adapter<LearnerAdapter.LearnerViewHolder> {

    ArrayList<Learner> learners;
    public LearnerAdapter(ArrayList<Learner> learners) {
        this.learners = learners;
    }

    @NonNull
    @Override
    public LearnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.learner_list_item, parent, false);
        return new LearnerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerViewHolder holder, int position) {
        Learner learner = learners.get(position);
        holder.bind(learner);
    }

    @Override
    public int getItemCount() {
        return learners.size();
    }

    public class LearnerViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvHours;

        public LearnerViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvHours = (TextView) itemView.findViewById(R.id.tvHours);
        }

        public void bind (Learner learner) {
            tvName.setText(learner.name);
            tvHours.setText(learner.hours + " Learner Hours, " + learner.country);
        }

    }
}
