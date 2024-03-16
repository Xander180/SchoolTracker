package com.wguc196.schooltracker.helpers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.UI.AssessmentDetailsActivity;
import com.wguc196.schooltracker.entities.Assessment;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    private List<Assessment> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public class AssessmentViewHolder extends RecyclerView.ViewHolder {

        private final TextView assessmentItemView;

        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.item_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Assessment current = mAssessments.get(position);
                    Intent intent = new Intent(context, AssessmentDetailsActivity.class);
                    intent.putExtra("assessmentID", current.getAssessmentID());
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra("date", current.getDate());
                    intent.putExtra("assessmentType", current.getAssessmentType());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_primary, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (mAssessments != null) {
            Assessment current = mAssessments.get(position);
            String name = current.getTitle();
            holder.assessmentItemView.setText(name);
        } else {
            holder.assessmentItemView.setText("No assessment title");
        }
    }

    @Override
    public int getItemCount() {
        if (mAssessments != null) {
            return mAssessments.size();
        }
        return 0;
    }

    public void setAssessments(List<Assessment> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }
}
