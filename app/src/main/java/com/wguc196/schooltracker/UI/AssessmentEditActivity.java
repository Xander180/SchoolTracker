package com.wguc196.schooltracker.UI;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.entities.Assessment;
import com.wguc196.schooltracker.entities.AssessmentType;
import com.wguc196.schooltracker.helpers.TextFormatting;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Objects;


public class AssessmentEditActivity extends AppCompatActivity {

    private int assessmentID;
    EditText assessmentTitle;
    ArrayAdapter<AssessmentType> assessmentTypeArrayAdapter;
    EditText assessmentDueDate;
    ImageButton assessmentStartButton;
    Spinner assessmentType;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assessment_edit);
        repository = new Repository(getApplication());

        assessmentStartButton = findViewById(R.id.assessmentStartEditButton);
        assessmentStartButton.setOnClickListener(v -> {
            Calendar calendarStart = Calendar.getInstance();
            if (!assessmentDueDate.getText().toString().isEmpty()) {
                try {
                    calendarStart.setTime(Objects.requireNonNull(TextFormatting.fullDateFormat.parse(assessmentDueDate.getText().toString())));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            DatePickerDialog.OnDateSetListener startDatePicker = (view, year, month, dayOfMonth) -> {
                calendarStart.set(Calendar.YEAR, year);
                calendarStart.set(Calendar.MONTH, month);
                calendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                assessmentDueDate.setText(TextFormatting.fullDateFormat.format(calendarStart.getTime()));
            };
            new DatePickerDialog(AssessmentEditActivity.this, startDatePicker, calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH), calendarStart.get(Calendar.DAY_OF_MONTH)).show();
        });

        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        assessmentTitle = findViewById(R.id.assessmentTitleEditText);
        assessmentDueDate = findViewById(R.id.assessmentStartEditText);
        assessmentType = findViewById(R.id.assessmentTypeSpinner);

        setSpinnerItems();
        Assessment assessment;
        assessment = repository.getmAssessment(assessmentID);

        setTitle(getIntent().getStringExtra("title"));
        assessmentTitle.setText(getIntent().getStringExtra("title"));
        assessmentDueDate.setText(getIntent().getStringExtra("date"));
        int position = getSpinnerPosition(assessment.getAssessmentType());
        assessmentType.setSelection(position);



    }

    public void setSpinnerItems() {
        assessmentTypeArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, AssessmentType.values());
        assessmentType.setAdapter(assessmentTypeArrayAdapter);
    }

    private int getSpinnerPosition(AssessmentType assessmentType) {
        return assessmentTypeArrayAdapter.getPosition(assessmentType);
    }

    private AssessmentType getSpinnerValue() {
        return (AssessmentType) assessmentType.getSelectedItem();
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
            Assessment assessment;
            try {
                if (assessmentID == -1) {
                    if (repository.getmAllAssessments().isEmpty()) assessmentID = 1;
                    else assessmentID = repository.getmAllAssessments().get(repository.getmAllAssessments().size() - 1).getAssessmentID() + 1;
                    assessment = new Assessment(assessmentID, assessmentTitle.getText().toString(), TextFormatting.fullDateFormat.parse(assessmentDueDate.getText().toString()),
                            getSpinnerValue());
                    repository.insert(assessment);
                } else {
                    assessment = new Assessment(assessmentID, assessmentTitle.getText().toString(), TextFormatting.fullDateFormat.parse(assessmentDueDate.getText().toString()),
                            getSpinnerValue());
                    repository.update(assessment);
                }
            } catch (ParseException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            Toast.makeText(AssessmentEditActivity.this, "Data has been saved.", Toast.LENGTH_LONG).show();
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}