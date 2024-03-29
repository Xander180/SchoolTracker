package com.wguc196.schooltracker.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.entities.Term;
import com.wguc196.schooltracker.helpers.SampleData;
import com.wguc196.schooltracker.helpers.TextFormatting;

import java.text.ParseException;
import java.util.Date;


public class TermEditActivity extends AppCompatActivity {

    private int termID;
    EditText termTitle;
    EditText termStartDate;
    EditText termEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_term_edit);

        setTitle(getIntent().getStringExtra("title"));
        termID = getIntent().getIntExtra("termID", -1);
        termTitle = findViewById(R.id.termTitleEditText);
        termStartDate = findViewById(R.id.termStartEditText);
        termEndDate = findViewById(R.id.termEndEditText);
        termTitle.setText(getIntent().getStringExtra("title"));
        termStartDate.setText(getIntent().getStringExtra("startDate"));
        termEndDate.setText(getIntent().getStringExtra("endDate"));
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
            Term term;
            try {
                if (termID == -1) {
                    if (repository.getmAllTerms().isEmpty()) termID = 1;
                    else termID = repository.getmAllTerms().get(repository.getmAllTerms().size() - 1).getTermID() + 1;
                    term = new Term(termID, termTitle.getText().toString(), TextFormatting.fullDateFormat.parse(termStartDate.getText().toString()), TextFormatting.fullDateFormat.parse(termEndDate.getText().toString()));
                    repository.insert(term);
                } else {
                    term = new Term(termID, termTitle.getText().toString(), TextFormatting.fullDateFormat.parse(termStartDate.getText().toString()), TextFormatting.fullDateFormat.parse(termEndDate.getText().toString()));
                    repository.update(term);
                }
            } catch (ParseException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            Toast.makeText(TermEditActivity.this, "Data has been saved.", Toast.LENGTH_LONG).show();
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}