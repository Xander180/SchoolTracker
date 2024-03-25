package com.wguc196.schooltracker.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.entities.Course;

import java.util.List;

public class CoursesListActivity extends AppCompatActivity {

    List<Course> allCourses;
    Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FloatingActionButton addButton;
        RecyclerView recyclerView;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_courses_list);

        addButton = findViewById(R.id.courseAddButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(CoursesListActivity.this, CourseEditActivity.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.coursesRecyclerView);
        repository = new Repository(getApplication());
        try {
            allCourses = repository.getmAllCourses();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(allCourses);
    }
}