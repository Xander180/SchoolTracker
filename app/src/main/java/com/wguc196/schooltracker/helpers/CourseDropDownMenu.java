package com.wguc196.schooltracker.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.entities.Course;

import java.util.List;

public class CourseDropDownMenu  extends PopupWindow {
    private final Context mContext;
    private final List<Course> mCourses;
    private CourseDropDownAdapter courseDropDownAdapter;

    public CourseDropDownMenu(Context mContext, List<Course> mCourses) {
        super(mContext);
        this.mContext = mContext;
        this.mCourses = mCourses;
        setupView();
    }

    public void setCourseSelectedListener(CourseDropDownAdapter.CourseSelectedListener courseSelectedListener) {
        courseDropDownAdapter.setCourseSelectedListener(courseSelectedListener);
    }

    private void setupView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dropdown_menu, null);

        RecyclerView recyclerViewPopup = view.findViewById(R.id.dropdownMenu);
        recyclerViewPopup.setHasFixedSize(true);
        recyclerViewPopup.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerViewPopup.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));

        courseDropDownAdapter = new CourseDropDownAdapter(mCourses);
        recyclerViewPopup.setAdapter(courseDropDownAdapter);

        setContentView(view);
    }
}
