package com.example.jothamgadsleaderboardproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder> {

    ArrayList<Skill> skills;
    public SkillAdapter(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.skill_list_item, parent, false);
        return new SkillViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        Skill skill = skills.get(position);
        holder.bind(skill);
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    public class SkillViewHolder extends RecyclerView.ViewHolder {
        TextView tvSkillName;
        TextView tvSkillIQ;

        public SkillViewHolder(View itemView) {
            super(itemView);
            tvSkillName = (TextView) itemView.findViewById(R.id.tvSkillName);
            tvSkillIQ = (TextView) itemView.findViewById(R.id.tvSkillIQ);

        }

        public void bind (Skill skill) {
            tvSkillName.setText(skill.name);
            tvSkillIQ.setText(skill.score + " Skill IQ Score, " + skill.country);

        }
    }
}
