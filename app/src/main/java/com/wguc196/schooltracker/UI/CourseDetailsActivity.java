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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.entities.Assessment;
import com.wguc196.schooltracker.entities.Course;
import com.wguc196.schooltracker.entities.Instructor;
import com.wguc196.schooltracker.entities.Term;
import com.wguc196.schooltracker.helpers.Receiver;
import com.wguc196.schooltracker.helpers.TextFormatting;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


public class CourseDetailsActivity extends AppCompatActivity {

    TextView courseStartDate;
    TextView courseEndDate;
    TextView status;
    TextView note;
    FloatingActionButton editCourse;
    FloatingActionButton deleteCourse;
    FloatingActionButton addAssessment;
    FloatingActionButton shareNote;
    List<Assessment> associatedAssessments;
    List<Instructor> associatedInstructors;
    Repository repository;
    Course course;
    RecyclerView assessmentsRecyclerView;
    RecyclerView instructorsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_details);

        editCourse = findViewById(R.id.editCourseButton);
        editCourse.setOnClickListener(v -> {
            Intent intent = new Intent(CourseDetailsActivity.this, CourseEditActivity.class);
            intent.putExtra("courseID", getIntent().getIntExtra("courseID", -1));
            intent.putExtra("title", getIntent().getStringExtra("title"));
            intent.putExtra("startDate", getIntent().getStringExtra("startDate"));
            intent.putExtra("endDate", getIntent().getStringExtra("endDate"));
            intent.putExtra("courseStatus", getIntent().getStringExtra("courseStatus"));
            intent.putExtra("note", getIntent().getStringExtra("note"));
            intent.putExtra("termID", getIntent().getIntExtra("termID", -1));
            startActivity(intent);
            this.finish();
        });

        deleteCourse = findViewById(R.id.deleteButton);
        deleteCourse.setOnClickListener(v -> {
            try {
                for (Course course : repository.getmAllCourses()) {
                    Course currentCourse;
                    if (course.getCourseID() == getIntent().getIntExtra("courseID", -1)) {
                        currentCourse = course;
                        if (associatedAssessments.isEmpty()) {
                            repository.delete(currentCourse);
                            Toast.makeText(CourseDetailsActivity.this, currentCourse.getTitle() + " has been deleted.", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(CourseDetailsActivity.this, "Cannot delete course with assessments!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        shareNote = findViewById(R.id.shareNoteButton);
        shareNote.setOnClickListener(v -> {
            Intent sentIntent = new Intent();
            sentIntent.setAction(Intent.ACTION_SEND);
            sentIntent.putExtra(Intent.EXTRA_TEXT, note.getText().toString());
            sentIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sentIntent, null);
            startActivity(shareIntent);
        });

        courseStartDate = findViewById(R.id.courseStartTextView);
        courseEndDate = findViewById(R.id.courseEndTextView);
        status = findViewById(R.id.courseStatusTextView);
        note = findViewById(R.id.courseNoteTextView);

        assessmentsRecyclerView = findViewById(R.id.associatedAssessmentsRecyclerView);
        instructorsRecyclerView = findViewById(R.id.associatedInstructorsRecyclerView);
    }

    private void loadCourseData() {
        setTitle(getIntent().getStringExtra("title"));

        courseStartDate.setText(getIntent().getStringExtra("startDate"));
        courseEndDate.setText(getIntent().getStringExtra("endDate"));
        status.setText(course.getCourseStatus().toString());
        note.setText(getIntent().getStringExtra("note"));
    }

    private void loadRepositoryData() {
        repository = new Repository(getApplication());
        try {
            course = repository.getCourse(getIntent().getIntExtra("courseID", -1));
            associatedAssessments = repository.getmAssociatedAssessments(getIntent().getIntExtra("courseID", 1));
            associatedInstructors = repository.getmAssociatedInstructors(getIntent().getIntExtra("courseID", 1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        final InstructorAdapter instructorAdapter = new InstructorAdapter(this);

        assessmentsRecyclerView.setAdapter(assessmentAdapter);
        instructorsRecyclerView.setAdapter(instructorAdapter);

        assessmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        instructorsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        assessmentAdapter.setAssessments(associatedAssessments);
        instructorAdapter.setInstructors(associatedInstructors);
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
            String startDateText = courseStartDate.getText().toString();
            String endDateText = courseEndDate.getText().toString();
            Date startDate;
            Date endDate;
            try {
                startDate = TextFormatting.fullDateFormat.parse(startDateText);
                endDate = TextFormatting.fullDateFormat.parse(endDateText);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            assert startDate != null;
            long startTrigger = startDate.getTime();
            assert endDate != null;
            long endTrigger = endDate.getTime();

            Intent intent = new Intent(CourseDetailsActivity.this, Receiver.class);
            intent.putExtra("reminder",  getIntent().getStringExtra("title") + " is now due!");
            PendingIntent sender = PendingIntent.getBroadcast(CourseDetailsActivity.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_MUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, startTrigger, sender);
            alarmManager.set(AlarmManager.RTC_WAKEUP, endTrigger, sender);
        } else {
            getOnBackPressedDispatcher().onBackPressed();
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRepositoryData();
        loadCourseData();
    }
}