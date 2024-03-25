package com.wguc196.schooltracker.UI;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.entities.Assessment;
import com.wguc196.schooltracker.entities.Course;
import com.wguc196.schooltracker.entities.Instructor;

import java.util.List;


public class CourseDetailsActivity extends AppCompatActivity {

    TextView courseStartDate;
    TextView courseEndDate;
    TextView status;
    TextView note;
    List<Assessment> associatedAssessments;
    List<Instructor> associatedInstructors;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView assessmentsRecyclerView;
        RecyclerView instructorsRecyclerView;
        FloatingActionButton editCourse;
        FloatingActionButton delete;
        FloatingActionButton addAssessment;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_details);

        setTitle(getIntent().getStringExtra("title"));
        courseStartDate = findViewById(R.id.courseStartTextView);
        courseEndDate = findViewById(R.id.courseEndTextView);
        status = findViewById(R.id.courseStatusTextView);
        note = findViewById(R.id.courseNoteTextView);
        courseStartDate.setText(getIntent().getStringExtra("startDate"));
        courseEndDate.setText(getIntent().getStringExtra("endDate"));
        status.setText(getIntent().getStringExtra("courseStatus"));
        note.setText(getIntent().getStringExtra("note"));

        assessmentsRecyclerView = findViewById(R.id.associatedAssessmentsRecyclerView);
        instructorsRecyclerView = findViewById(R.id.associatedInstructorsRecyclerView);
        repository = new Repository(getApplication());
        try {
            associatedAssessments = repository.getmAssociatedAssessments(getIntent().getIntExtra("courseID", 1));
            associatedInstructors = repository.getmAssociatedInstructors(getIntent().getIntExtra("courseID", 1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        final InstructorAdapter instructorAdapter = new InstructorAdapter(this);

        assessmentsRecyclerView.setAdapter(assessmentAdapter);
        instructorsRecyclerView.setAdapter(instructorAdapter);

        assessmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        instructorsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        assessmentAdapter.setAssessments(associatedAssessments);
        instructorAdapter.setInstructors(associatedInstructors);


    }
}