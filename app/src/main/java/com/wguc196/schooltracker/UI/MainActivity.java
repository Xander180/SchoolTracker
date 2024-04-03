package com.wguc196.schooltracker.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.helpers.SampleData;


public class MainActivity extends AppCompatActivity {

    public static int numAlert;
    Button termsButton;
    Button coursesButton;
    Button assessmentsButton;
    Button instructorsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        termsButton = findViewById(R.id.termsButton);
        coursesButton = findViewById(R.id.coursesButton);
        assessmentsButton = findViewById(R.id.assessmentsButton);
        instructorsButton = findViewById(R.id.instructorsButton);
        termsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TermsListActivity.class);
            startActivity(intent);
        });

        coursesButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CoursesListActivity.class);
            startActivity(intent);
        });

        assessmentsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AssessmentsListActivity.class);
            startActivity(intent);
        });

        instructorsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InstructorsListActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.sample_data) {
            Repository repository = new Repository(getApplication());
            SampleData.insertSampleData(repository);
            Toast.makeText(MainActivity.this, "Sample data has been loaded", Toast.LENGTH_LONG).show();
            return true;
        } else if (menuItem.getItemId() == R.id.delete_data) {
            Toast.makeText(MainActivity.this, "All data has been deleted", Toast.LENGTH_LONG).show();
            return true;
        }
        return true;
    }
}