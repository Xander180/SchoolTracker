package com.wguc196.schooltracker.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wguc196.schooltracker.R;

public class InstructorDetailsActivity extends AppCompatActivity {

    TextView instructorEmail;
    TextView instructorPhone;
    FloatingActionButton editInstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_instructor_details);

        editInstructor = findViewById(R.id.instructorEditButton);
        editInstructor.setOnClickListener(v -> {
            Intent intent = new Intent(InstructorDetailsActivity.this, InstructorEditActivity.class);
            intent.putExtra("instructorID", getIntent().getIntExtra("instructorID", -1));
            intent.putExtra("name", getIntent().getStringExtra("name"));
            intent.putExtra("email", getIntent().getStringExtra("email"));
            intent.putExtra("phone", getIntent().getStringExtra("phone"));
            startActivity(intent);
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