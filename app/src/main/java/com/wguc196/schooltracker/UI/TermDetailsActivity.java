package com.wguc196.schooltracker.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.entities.Course;
import com.wguc196.schooltracker.entities.Term;
import com.wguc196.schooltracker.helpers.CourseDropDownMenu;

import java.util.ArrayList;
import java.util.List;


public class TermDetailsActivity extends AppCompatActivity {

    TextView termStartDate;
    TextView termEndDate;
    FloatingActionButton editTerm;
    FloatingActionButton deleteTerm;
    FloatingActionButton addCourse;
    List<Course> associatedCourses;
    List<Course> allCourses;
    List<Course> unassignedCourses;
    RecyclerView recyclerView;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_term_details);
        repository = new Repository(getApplication());

        editTerm = findViewById(R.id.editTermButton);
        editTerm.setOnClickListener(v -> {
            Intent intent = new Intent(TermDetailsActivity.this, TermEditActivity.class);
            intent.putExtra("termID", getIntent().getIntExtra("termID", -1));
            intent.putExtra("title", getIntent().getStringExtra("title"));
            intent.putExtra("startDate", getIntent().getStringExtra("startDate"));
            intent.putExtra("endDate", getIntent().getStringExtra("endDate"));
            startActivity(intent);
            finish();
        });

        deleteTerm = findViewById(R.id.deleteButton);
        deleteTerm.setOnClickListener(v -> {
            try {
                for (Term term : repository.getmAllTerms()) {
                    Term currentTerm;
                    if (term.getTermID() == getIntent().getIntExtra("termID", -1)) {
                        currentTerm = term;
                        if (associatedCourses.isEmpty()) {
                            repository.delete(currentTerm);
                            Toast.makeText(TermDetailsActivity.this, currentTerm.getTitle() + " has been deleted.", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(TermDetailsActivity.this, "Cannot delete term with courses!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        addCourse = findViewById(R.id.courseAddButton);
        addCourse.setOnClickListener(v -> {
            if (!unassignedCourses.isEmpty()) {
                int termID = getIntent().getIntExtra("termID", -1);
                final CourseDropDownMenu menu = new CourseDropDownMenu(this, unassignedCourses);
                menu.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                menu.setWidth(getPxFromDp());
                menu.setOutsideTouchable(true);
                menu.setFocusable(true);
                menu.showAsDropDown(addCourse);
                menu.setCourseSelectedListener((position, course) -> {
                    menu.dismiss();
                    course.setTermID(termID);
                    repository.update(course);
                    onResume();
                });
            } else {
                Toast.makeText(getApplicationContext(), "There are no unassigned courses.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTermData() {
        setTitle(getIntent().getStringExtra("title"));
        termStartDate = findViewById(R.id.termStartTextView);
        termEndDate = findViewById(R.id.termEndTextView);
        termStartDate.setText(getIntent().getStringExtra("startDate"));
        termEndDate.setText(getIntent().getStringExtra("endDate"));

        if (associatedCourses.isEmpty()) {
            unassignedCourses.addAll(allCourses);
        } else {
            for (Course course : allCourses) {
                for (Course associatedCourse : associatedCourses) {
                    if (course.getTermID() != associatedCourse.getTermID()) {
                        unassignedCourses.add(course);
                    }
                    break;
                }
            }
        }
    }

    private void loadRepositoryData() {
        recyclerView = findViewById(R.id.associatedCoursesRecyclerView);
        repository = new Repository(getApplication());
        try {
            associatedCourses = repository.getmAssociatedCourses(getIntent().getIntExtra("termID", 1));
            allCourses = repository.getmAllCourses();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(associatedCourses);
    }

    private int getPxFromDp() {
        return (int) (200 * getResources().getDisplayMetrics().density);
    }

    @Override
    protected void onResume() {
        super.onResume();
        unassignedCourses = new ArrayList<>();
        loadRepositoryData();
        loadTermData();
    }
}