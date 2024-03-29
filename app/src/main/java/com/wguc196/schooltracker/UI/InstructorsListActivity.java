package com.wguc196.schooltracker.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.database.Repository;
import com.wguc196.schooltracker.entities.Instructor;
import com.wguc196.schooltracker.entities.Term;

import java.util.List;

public class InstructorsListActivity extends AppCompatActivity {

    List<Instructor> allInstructors;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FloatingActionButton addButton;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_instructors_list);

        addButton = findViewById(R.id.instructorsAddButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(InstructorsListActivity.this, InstructorEditActivity.class);
            startActivity(intent);
        });
    }

    private void loadData() {
        try {
            repository = new Repository(getApplication());
            allInstructors = repository.getmAllInstructors();
            RecyclerView recyclerView = findViewById(R.id.instructorsRecyclerView);
            final InstructorAdapter instructorAdapter = new InstructorAdapter(this);
            recyclerView.setAdapter(instructorAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            instructorAdapter.setInstructors(allInstructors);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}