package com.wguc196.schooltracker.UI;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.entities.Course;
import com.wguc196.schooltracker.entities.Term;
import com.wguc196.schooltracker.helpers.CourseAdapter;
import com.wguc196.schooltracker.helpers.TermAdapter;
import com.wguc196.schooltracker.helpers.TextFormatting;

import java.util.Date;
import java.util.List;


public class TermDetailsActivity extends AppCompatActivity {

    TextView termStartDate;
    TextView termEndDate;
    List<Course> associatedCourses;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerView;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_term_details);

        setTitle(getIntent().getStringExtra("title"));
        termStartDate = findViewById(R.id.termStartTextView);
        termEndDate = findViewById(R.id.termEndTextView);
        termStartDate.setText(getIntent().getStringExtra("startDate"));
        termEndDate.setText(getIntent().getStringExtra("endDate"));

        recyclerView = findViewById(R.id.associatedCoursesRecyclerView);
        repository = new Repository(getApplication());
        try {
            associatedCourses = repository.getmAssociatedCourses(getIntent().getIntExtra("termID", 1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(associatedCourses);
    }
}