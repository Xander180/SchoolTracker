package com.wguc196.schooltracker.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wguc196.schooltracker.R;

public class AssessmentsList extends AppCompatActivity {

    FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assessments);

        addButton = findViewById(R.id.assessmentAddButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(AssessmentsList.this, AssessmentEditActivity.class);
            startActivity(intent);
        });
    }
}