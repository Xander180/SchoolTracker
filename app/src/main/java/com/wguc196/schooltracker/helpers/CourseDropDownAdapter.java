package com.wguc196.schooltracker.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.entities.Course;

import java.util.List;

public class CourseDropDownAdapter extends RecyclerView.Adapter<CourseDropDownAdapter.CourseViewHolder> {

    private final List<Course> mCourses;
    private CourseSelectedListener courseSelectedListener;

    public CourseDropDownAdapter(List<Course> mCourses) {
        super();
        this.mCourses = mCourses;
    }

    public void setCourseSelectedListener(CourseDropDownAdapter.CourseSelectedListener courseSelectedListener) {
        this.courseSelectedListener = courseSelectedListener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_dropdown, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        final Course course = mCourses.get(position);
        holder.courseTitle.setText(course.getTitle());
        holder.itemView.setOnClickListener(v -> {
            if (courseSelectedListener != null) {
                courseSelectedListener.onCourseSelected(position, course);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView courseTitle;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.dropDownTitle);
        }
    }

    public interface CourseSelectedListener {
        void onCourseSelected(int position, Course course);
    }

}


