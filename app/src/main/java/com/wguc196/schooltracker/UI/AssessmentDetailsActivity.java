package com.wguc196.schooltracker.UI;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.entities.Assessment;
import com.wguc196.schooltracker.entities.Course;
import com.wguc196.schooltracker.helpers.Receiver;
import com.wguc196.schooltracker.helpers.TextFormatting;

import java.text.ParseException;
import java.util.Date;


public class AssessmentDetailsActivity extends AppCompatActivity {

    TextView assessmentStart;
    TextView assessmentType;
    FloatingActionButton editAssessment;
    FloatingActionButton deleteAssessment;
    Repository repository;

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
            intent.putExtra("courseID", getIntent().getIntExtra("courseID", -1));
            startActivity(intent);
            this.finish();
        });

        deleteAssessment = findViewById(R.id.deleteButton);
        deleteAssessment.setOnClickListener(v -> {
            try {
                for (Assessment assessment : repository.getmAllAssessments()) {
                    Assessment currentAssessment;
                    if (assessment.getAssessmentID() == getIntent().getIntExtra("assessmentID", -1)) {
                        currentAssessment = assessment;
                        repository.delete(currentAssessment);
                        Toast.makeText(AssessmentDetailsActivity.this, currentAssessment.getTitle() + " has been deleted.", Toast.LENGTH_LONG).show();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        assessmentStart = findViewById(R.id.assessmentStartTextView);
        assessmentType = findViewById(R.id.assessmentTypeTextView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_set_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.set_reminder) {
            String startDateText = assessmentStart.getText().toString();
            Date startDate;
            try {
                startDate = TextFormatting.fullDateFormat.parse(startDateText);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            assert startDate != null;
            long startTrigger = startDate.getTime();

            Intent intent = new Intent(AssessmentDetailsActivity.this, Receiver.class);
            intent.putExtra("reminder",  getIntent().getStringExtra("title") + " is now due!");
            PendingIntent sender = PendingIntent.getBroadcast(AssessmentDetailsActivity.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_MUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, startTrigger, sender);
        } else {
            getOnBackPressedDispatcher().onBackPressed();
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle(getIntent().getStringExtra("title"));

        assessmentStart.setText(getIntent().getStringExtra("date"));
        assessmentType.setText(getIntent().getStringExtra("assessmentType"));
    }
}