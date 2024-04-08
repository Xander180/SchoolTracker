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
import com.wguc196.schooltracker.entities.Assessment;

import java.util.List;

public class AssessmentsListActivity extends AppCompatActivity {


    FloatingActionButton addButton;
    RecyclerView recyclerView;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assessments_list);

        addButton = findViewById(R.id.assessmentAddButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(AssessmentsListActivity.this, AssessmentEditActivity.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.assessmentsRecyclerView);
        repository = new Repository(getApplication());
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            List<Assessment> allAssessments = repository.getmAllAssessments();
            RecyclerView recyclerView = findViewById(R.id.assessmentsRecyclerView);
            final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
            recyclerView.setAdapter(assessmentAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            assessmentAdapter.setAssessments(allAssessments);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}