package com.wguc196.schooltracker.UI;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.wguc196.schooltracker.R;


public class CourseEditActivity extends AppCompatActivity {

    EditText courseTitle;
    EditText courseStartDate;
    EditText courseEndDate;
    Spinner courseStatus;
    EditText note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_edit);

        setTitle(getIntent().getStringExtra("title"));
        courseTitle = findViewById(R.id.courseTitleEditText);
        courseStartDate = findViewById(R.id.courseStartEditText);
        courseEndDate = findViewById(R.id.courseEndEditText);
        courseStatus = findViewById(R.id.courseStatusSpinner);
        note = findViewById(R.id.courseNoteEditText);

        courseTitle.setText(getIntent().getStringExtra("title"));
        courseStartDate.setText(getIntent().getStringExtra("startDate"));
        courseEndDate.setText(getIntent().getStringExtra("endDate"));
        // courseStatus
        note.setText(getIntent().getStringExtra("note"));

    }
}