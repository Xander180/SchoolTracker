package com.wguc196.schooltracker.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wguc196.schooltracker.R;

public class CoursesListActivity extends AppCompatActivity {

    FloatingActionButton addButton;

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
    }
}