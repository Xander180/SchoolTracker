package com.wguc196.schooltracker.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.entities.Assessment;
import com.wguc196.schooltracker.entities.Course;
import com.wguc196.schooltracker.entities.Instructor;
import com.wguc196.schooltracker.entities.Term;
import com.wguc196.schooltracker.helpers.SampleData;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Repository repository = new Repository();
    private final List<Term> sampleTerms = SampleData.getTerms();

    Button termsButton;
    Button coursesButton;
    Button assessmentsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        termsButton = findViewById(R.id.termsButton);
        coursesButton = findViewById(R.id.coursesButton);
        assessmentsButton = findViewById(R.id.assessmentsButton);
        termsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TermsList.class);
                startActivity(intent);
            }
        });

        coursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CoursesList.class);
                startActivity(intent);
            }
        });

        assessmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AssessmentsList.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_terms_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.sample_data) {
            for (Term term : sampleTerms) {
                repository.insert(term);
            }
            Toast.makeText(MainActivity.this, "Sample data has been loaded", Toast.LENGTH_LONG).show();
            return true;
        } else if (menuItem.getItemId() == R.id.delete_data) {
            Toast.makeText(MainActivity.this, "All data has been deleted (not really, yet)", Toast.LENGTH_LONG).show();
            return true;
        }
        return true;
    }
}