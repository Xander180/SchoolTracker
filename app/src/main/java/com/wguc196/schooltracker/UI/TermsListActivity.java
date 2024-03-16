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
import com.wguc196.schooltracker.helpers.TermAdapter;

import java.util.List;

public class TermsListActivity extends AppCompatActivity {

    List<Term> allTerms;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FloatingActionButton addButton;
        RecyclerView recyclerView;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_terms_list);

        addButton = findViewById(R.id.termAddButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(TermsListActivity.this, TermEditActivity.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.terms_recycler_vew);
        repository = new Repository(getApplication());
        try {
            allTerms = repository.getmAllTerms();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);

    }
}