package com.wguc196.schooltracker.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.entities.Assessment;
import java.util.List;

public class AssessmentDropDownAdapter extends RecyclerView.Adapter<AssessmentDropDownAdapter.AssessmentViewHolder> {
    private final List<Assessment> mAssessments;
    private AssessmentDropDownAdapter.AssessmentSelectedListener assessmentSelectedListener;

    public AssessmentDropDownAdapter(List<Assessment> mAssessments) {
        super();
        this.mAssessments = mAssessments;
    }

    public void setAssessmentSelectedListener(AssessmentDropDownAdapter.AssessmentSelectedListener assessmentSelectedListener) {
        this.assessmentSelectedListener = assessmentSelectedListener;
    }

    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AssessmentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dropdown, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
        final Assessment assessment = mAssessments.get(position);
        holder.assessmentTitle.setText(assessment.getTitle());
        holder.itemView.setOnClickListener(v -> {
            if (assessmentSelectedListener != null) {
                assessmentSelectedListener.onAssessmentSelected(position, assessment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAssessments.size();
    }

    static class AssessmentViewHolder extends RecyclerView.ViewHolder {
        TextView assessmentTitle;

        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assessmentTitle = itemView.findViewById(R.id.dropDownTitle);
        }
    }

    public interface AssessmentSelectedListener {
        void onAssessmentSelected(int position, Assessment assessment);
    }
}
