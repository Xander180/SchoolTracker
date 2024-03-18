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
import com.wguc196.schooltracker.UI.InstructorDetailsActivity;
import com.wguc196.schooltracker.entities.Course;
import com.wguc196.schooltracker.entities.Instructor;

import java.util.List;

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.InstructorViewHolder> {

    private List<Instructor> mInstructors;
    private final Context context;
    private final LayoutInflater mInflater;

    public InstructorAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public class InstructorViewHolder extends RecyclerView.ViewHolder {
        private final TextView instructorItemView;
        public InstructorViewHolder(@NonNull View itemView) {
            super(itemView);
            instructorItemView = itemView.findViewById(R.id.item_button);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Instructor current = mInstructors.get(position);
                    Intent intent = new Intent(context, InstructorDetailsActivity.class);
                    intent.putExtra("instructorID", current.getInstructorID());
                    intent.putExtra("title", current.getName());
                    intent.putExtra("phone", current.getPhone());
                    intent.putExtra("email", current.getEmail());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public InstructorAdapter.InstructorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_primary, parent, false);
        return new InstructorAdapter.InstructorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructorAdapter.InstructorViewHolder holder, int position) {
        if (mInstructors != null) {
            Instructor current = mInstructors.get(position);
            String name = current.getName();
            holder.instructorItemView.setText(name);
        } else {
            holder.instructorItemView.setText("No course title");
        }
    }

    @Override
    public int getItemCount() {
        if (mInstructors != null) {
            return mInstructors.size();
        }

        return 0;
    }

    public void setInstructors(List<Instructor> instructors) {
        mInstructors = instructors;
        notifyDataSetChanged();
    }
}
