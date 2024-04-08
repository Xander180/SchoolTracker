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

    FloatingActionButton addButton;
    RecyclerView recyclerView;
    Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            List<Course> allCourses = repository.getmAllCourses();
            RecyclerView recyclerView = findViewById(R.id.coursesRecyclerView);
            final CourseAdapter courseAdapter = new CourseAdapter(this);
            recyclerView.setAdapter(courseAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            courseAdapter.setCourses(allCourses);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}