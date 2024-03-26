package com.wguc196.schooltracker.UI;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.wguc196.schooltracker.R;


public class AssessmentEditActivity extends AppCompatActivity {

    EditText assessmentTitle;
    EditText startDate;
    Spinner assessmentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assessment_edit);

        assessmentTitle = findViewById(R.id.assessmentTitleEditText);
        startDate = findViewById(R.id.assessmentStartEditText);
        assessmentType = findViewById(R.id.assessmentTypeSpinner);

        setTitle(getIntent().getStringExtra("title"));
        assessmentTitle.setText(getIntent().getStringExtra("title"));
        startDate.setText(getIntent().getStringExtra("date"));
        // assessmentType

    }
}