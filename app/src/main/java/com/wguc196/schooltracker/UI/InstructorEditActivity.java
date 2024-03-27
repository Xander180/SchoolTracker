package com.wguc196.schooltracker.UI;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.wguc196.schooltracker.R;

public class InstructorEditActivity extends AppCompatActivity {

    EditText instructorName;
    EditText instructorEmail;
    EditText instructorPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_instructor_edit);

        instructorName = findViewById(R.id.instructorNameEditText);
        instructorEmail = findViewById(R.id.instructorEmailEditText);
        instructorPhone = findViewById(R.id.instructorPhoneEditText);

        setTitle(getIntent().getStringExtra("name"));
        instructorName.setText(getIntent().getStringExtra("name"));
        instructorEmail.setText(getIntent().getStringExtra("email"));
        instructorPhone.setText(getIntent().getStringExtra("phone"));
    }
}