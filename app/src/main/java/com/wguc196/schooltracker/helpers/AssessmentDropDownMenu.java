package com.wguc196.schooltracker.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.entities.Assessment;

import java.util.List;

public class AssessmentDropDownMenu extends PopupWindow {
    private final Context mContext;
    private final List<Assessment> mAssessments;
    private AssessmentDropDownAdapter assessmentDropDownAdapter;

    public AssessmentDropDownMenu(Context mContext, List<Assessment> mAssessments) {
        super(mContext);
        this.mContext = mContext;
        this.mAssessments = mAssessments;
        setupView();
    }

    public void setAssessmentSelectedListener(AssessmentDropDownAdapter.AssessmentSelectedListener assessmentSelectedListener) {
        assessmentDropDownAdapter.setAssessmentSelectedListener(assessmentSelectedListener);
    }

    private void setupView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dropdown_menu, null);

        RecyclerView recyclerViewPopup = view.findViewById(R.id.dropdownMenu);
        recyclerViewPopup.setHasFixedSize(true);
        recyclerViewPopup.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerViewPopup.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));

        assessmentDropDownAdapter = new AssessmentDropDownAdapter(mAssessments);
        recyclerViewPopup.setAdapter(assessmentDropDownAdapter);

        setContentView(view);
    }
}
