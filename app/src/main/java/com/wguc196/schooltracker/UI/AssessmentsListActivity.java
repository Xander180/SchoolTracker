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
import com.wguc196.schooltracker.entities.Term;
import com.wguc196.schooltracker.helpers.AssessmentAdapter;
import com.wguc196.schooltracker.helpers.TermAdapter;

import java.util.List;

public class AssessmentsListActivity extends AppCompatActivity {

    List<Assessment> allAssessments;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FloatingActionButton addButton;
        RecyclerView recyclerView;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assessments_list);

        addButton = findViewById(R.id.assessmentAddButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(AssessmentsListActivity.this, AssessmentEditActivity.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.assessments_recycler_vew);
        repository = new Repository(getApplication());
        try {
            allAssessments = repository.getmAllAssessments();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(allAssessments);
    }
}