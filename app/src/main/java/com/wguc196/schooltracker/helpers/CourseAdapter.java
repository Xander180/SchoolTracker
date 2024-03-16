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
import com.wguc196.schooltracker.UI.CourseDetailsActivity;
import com.wguc196.schooltracker.entities.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private List<Course> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseItemView;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseItemView = itemView.findViewById(R.id.item_button);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Course current = mCourses.get(position);
                    Intent intent = new Intent(context, CourseDetailsActivity.class);
                    intent.putExtra("courseID", current.getCourseID());
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                    intent.putExtra("note", current.getNote());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_primary, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        if (mCourses != null) {
            Course current = mCourses.get(position);
            String name = current.getTitle();
            holder.courseItemView.setText(name);
        } else {
            holder.courseItemView.setText("No course title");
        }
    }

    @Override
    public int getItemCount() {
        if (mCourses != null) {
            return mCourses.size();
        }

        return 0;
    }

    public void setCourses(List<Course> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }

}
