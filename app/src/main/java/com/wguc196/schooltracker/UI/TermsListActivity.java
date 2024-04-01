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
import com.wguc196.schooltracker.entities.Term;

import java.util.List;

public class TermsListActivity extends AppCompatActivity {

    FloatingActionButton addButton;
    RecyclerView recyclerView;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_terms_list);

        addButton = findViewById(R.id.termAddButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(TermsListActivity.this, TermEditActivity.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.termsRecyclerView);
        repository = new Repository(getApplication());
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            List<Term> allTerms = repository.getmAllTerms();
            RecyclerView recyclerView = findViewById(R.id.termsRecyclerView);
            final TermAdapter termAdapter = new TermAdapter(this);
            recyclerView.setAdapter(termAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            termAdapter.setTerms(allTerms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}