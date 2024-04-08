package com.wguc196.schooltracker.UI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.entities.Instructor;
import com.wguc196.schooltracker.entities.Term;
import com.wguc196.schooltracker.helpers.TextFormatting;

import java.text.ParseException;

public class InstructorEditActivity extends AppCompatActivity {

    private int instructorID;
    private int courseID;
    EditText instructorName;
    EditText instructorEmail;
    EditText instructorPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_instructor_edit);

        instructorID = getIntent().getIntExtra("instructorID", -1);
        instructorName = findViewById(R.id.instructorNameEditText);
        instructorEmail = findViewById(R.id.instructorEmailEditText);
        instructorPhone = findViewById(R.id.instructorPhoneEditText);
        courseID = getIntent().getIntExtra("courseID", -1);

        setTitle(getIntent().getStringExtra("name"));
        instructorName.setText(getIntent().getStringExtra("name"));
        instructorEmail.setText(getIntent().getStringExtra("email"));
        instructorPhone.setText(getIntent().getStringExtra("phone"));
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
            Repository repository = new Repository(getApplication());
            Instructor instructor;
            try {
                if (instructorID == -1) {
                    if (repository.getmAllInstructors().isEmpty()) instructorID = 1;
                    else instructorID = repository.getmAllInstructors().get(repository.getmAllTerms().size() - 1).getInstructorID() + 1;
                    instructor = new Instructor(instructorID, instructorName.getText().toString(), instructorPhone.getText().toString(), instructorEmail.getText().toString(), courseID);
                    repository.insert(instructor);
                } else {
                    instructor = new Instructor(instructorID, instructorName.getText().toString(), instructorPhone.getText().toString(), instructorEmail.getText().toString(), courseID);
                    repository.update(instructor);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Toast.makeText(InstructorEditActivity.this, "Instructor has been saved.", Toast.LENGTH_LONG).show();
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}