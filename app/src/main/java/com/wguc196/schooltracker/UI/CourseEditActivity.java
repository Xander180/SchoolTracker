package com.wguc196.schooltracker.UI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.entities.Course;
import com.wguc196.schooltracker.entities.CourseStatus;
import com.wguc196.schooltracker.entities.Term;
import com.wguc196.schooltracker.helpers.TextFormatting;

import java.text.ParseException;


public class CourseEditActivity extends AppCompatActivity {

    private int courseID;
    ArrayAdapter<CourseStatus> courseStatusArrayAdapter;
    EditText courseTitle;
    EditText courseStartDate;
    EditText courseEndDate;
    Spinner courseStatus;
    EditText note;
    Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_edit);
        repository = new Repository(getApplication());

        setTitle(getIntent().getStringExtra("title"));
        courseID = getIntent().getIntExtra("courseID", -1);
        courseTitle = findViewById(R.id.courseTitleEditText);
        courseStartDate = findViewById(R.id.courseStartEditText);
        courseEndDate = findViewById(R.id.courseEndEditText);
        courseStatus = findViewById(R.id.courseStatusSpinner);
        note = findViewById(R.id.courseNoteEditText);

        setSpinnerItems();
        Course course;
        try {
            course = repository.getCourse(courseID);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        courseTitle.setText(getIntent().getStringExtra("title"));
        courseStartDate.setText(getIntent().getStringExtra("startDate"));
        courseEndDate.setText(getIntent().getStringExtra("endDate"));
        int position = getSpinnerPosition(course.getCourseStatus());
        courseStatus.setSelection(position);
        note.setText(getIntent().getStringExtra("note"));


    }

    public void setSpinnerItems() {
        courseStatusArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, CourseStatus.values());
        courseStatus.setAdapter(courseStatusArrayAdapter);
    }

    private int getSpinnerPosition(CourseStatus courseStatus) {
        return courseStatusArrayAdapter.getPosition(courseStatus);
    }

    private CourseStatus getSpinnerValue() {
        return (CourseStatus) courseStatus.getSelectedItem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.save_data) {
            Course course;
            try {
                if (courseID == -1) {
                    if (repository.getmAllCourses().isEmpty()) courseID = 1;
                    else courseID = repository.getmAllCourses().get(repository.getmAllCourses().size() - 1).getCourseID() + 1;
                    course = new Course(courseID, courseTitle.getText().toString(), TextFormatting.fullDateFormat.parse(courseStartDate.getText().toString()),
                            TextFormatting.fullDateFormat.parse(courseEndDate.getText().toString()), getSpinnerValue(), note.getText().toString());
                    repository.insert(course);
                } else {
                    course = new Course(courseID, courseTitle.getText().toString(), TextFormatting.fullDateFormat.parse(courseStartDate.getText().toString()),
                            TextFormatting.fullDateFormat.parse(courseEndDate.getText().toString()), getSpinnerValue(), note.getText().toString());
                    repository.update(course);
                }
            } catch (ParseException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            Toast.makeText(CourseEditActivity.this, "Data has been saved.", Toast.LENGTH_LONG).show();
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}