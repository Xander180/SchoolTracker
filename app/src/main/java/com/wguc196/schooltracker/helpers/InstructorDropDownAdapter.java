package com.wguc196.schooltracker.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.entities.Instructor;

import java.util.List;

public class InstructorDropDownAdapter extends RecyclerView.Adapter<InstructorDropDownAdapter.InstructorViewHolder> {
    private final List<Instructor> mInstructors;
    private InstructorDropDownAdapter.InstructorSelectedListener instructorSelectedListener;

    public InstructorDropDownAdapter(List<Instructor> mInstructors) {
        super();
        this.mInstructors = mInstructors;
    }

    public void setInstructorSelectedListener(InstructorDropDownAdapter.InstructorSelectedListener instructorSelectedListener) {
        this.instructorSelectedListener = instructorSelectedListener;
    }

    @NonNull
    @Override
    public InstructorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dropdown, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructorViewHolder holder, int position) {
        final Instructor instructor = mInstructors.get(position);
        holder.instructorName.setText(instructor.getName());
        holder.itemView.setOnClickListener(v -> {
            if (instructorSelectedListener != null) {
                instructorSelectedListener.onInstructorSelected(position, instructor);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInstructors.size();
    }

    static class InstructorViewHolder extends RecyclerView.ViewHolder {
        TextView instructorName;

        public InstructorViewHolder(@NonNull View itemView) {
            super(itemView);
            instructorName = itemView.findViewById(R.id.dropDownTitle);
        }
    }

    public interface InstructorSelectedListener {
        void onInstructorSelected(int position, Instructor instructor);
    }
}
