package com.wguc196.schooltracker.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wguc196.schooltracker.R;


public class AssessmentDetailsActivity extends AppCompatActivity {

    TextView assessmentStart;
    TextView assessmentType;
    FloatingActionButton editAssessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assessment_details);

        editAssessment = findViewById(R.id.assessmentEditButton);
        editAssessment.setOnClickListener(v -> {
            Intent intent = new Intent(AssessmentDetailsActivity.this, AssessmentEditActivity.class);
            intent.putExtra("assessmentID", getIntent().getIntExtra("assessmentID", -1));
            intent.putExtra("title", getIntent().getStringExtra("title"));
            intent.putExtra("date", getIntent().getStringExtra("date"));
            intent.putExtra("assessmentType", getIntent().getStringExtra("assessmentType"));
            startActivity(intent);
            this.finish();
        });

        assessmentStart = findViewById(R.id.assessmentStartTextView);
        assessmentType = findViewById(R.id.assessmentTypeTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle(getIntent().getStringExtra("title"));

        assessmentStart.setText(getIntent().getStringExtra("date"));
        assessmentType.setText(getIntent().getStringExtra("assessmentType"));

    }
}