package com.wguc196.schooltracker.UI;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.wguc196.schooltracker.R;


public class TermEditActivity extends AppCompatActivity {

    EditText termTitle;
    EditText termStartDate;
    EditText termEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_term_edit);

        setTitle(getIntent().getStringExtra("title"));
        termTitle = findViewById(R.id.termTitleEditText);
        termStartDate = findViewById(R.id.termStartEditText);
        termEndDate = findViewById(R.id.termEndEditText);
        termTitle.setText(getIntent().getStringExtra("title"));
        termStartDate.setText(getIntent().getStringExtra("startDate"));
        termEndDate.setText(getIntent().getStringExtra("endDate"));
    }
}