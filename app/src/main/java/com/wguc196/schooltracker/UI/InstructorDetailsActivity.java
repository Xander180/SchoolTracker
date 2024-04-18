package com.wguc196.schooltracker.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.entities.Instructor;

public class InstructorDetailsActivity extends AppCompatActivity {

    TextView instructorEmail;
    TextView instructorPhone;
    FloatingActionButton editInstructor;
    FloatingActionButton deleteInstructor;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_instructor_details);
        repository = new Repository(getApplication());

        editInstructor = findViewById(R.id.instructorEditButton);
        editInstructor.setOnClickListener(v -> {
            Intent intent = new Intent(InstructorDetailsActivity.this, InstructorEditActivity.class);
            intent.putExtra("instructorID", getIntent().getIntExtra("instructorID", -1));
            intent.putExtra("name", getIntent().getStringExtra("name"));
            intent.putExtra("email", getIntent().getStringExtra("email"));
            intent.putExtra("phone", getIntent().getStringExtra("phone"));
            intent.putExtra("courseID", getIntent().getIntExtra("courseID", -1));
            startActivity(intent);
        });

        deleteInstructor = findViewById(R.id.deleteButton);
        deleteInstructor.setOnClickListener(v -> {
            try {
                for (Instructor instructor : repository.getmAllInstructors()) {
                    Instructor currentInstructor;
                    if (instructor.getInstructorID() == getIntent().getIntExtra("instructorID", -1)) {
                        currentInstructor = instructor;
                        repository.delete(currentInstructor);
                        Toast.makeText(InstructorDetailsActivity.this, currentInstructor.getName() + " has been deleted.", Toast.LENGTH_LONG).show();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        instructorEmail = findViewById(R.id.instructorEmailTextView);
        instructorPhone = findViewById(R.id.instructorPhoneTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle(getIntent().getStringExtra("name"));
        instructorEmail.setText(getIntent().getStringExtra("email"));
        instructorPhone.setText(getIntent().getStringExtra("phone"));
    }
}