package com.wguc196.schooltracker.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.entities.Instructor;

import java.util.List;

public class InstructorDropDownMenu extends PopupWindow {
    private final Context mContext;
    private final List<Instructor> mInstructors;
    private InstructorDropDownAdapter instructorDropDownAdapter;

    public InstructorDropDownMenu(Context mContext, List<Instructor> mInstructors) {
        super(mContext);
        this.mContext = mContext;
        this.mInstructors = mInstructors;
        setupView();
    }

    public void setInstructorSelectedListener(InstructorDropDownAdapter.InstructorSelectedListener instructorSelectedListener) {
        instructorDropDownAdapter.setInstructorSelectedListener(instructorSelectedListener);
    }

    private void setupView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dropdown_menu, null);

        RecyclerView recyclerViewPopup = view.findViewById(R.id.dropdownMenu);
        recyclerViewPopup.setHasFixedSize(true);
        recyclerViewPopup.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerViewPopup.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));

        instructorDropDownAdapter = new InstructorDropDownAdapter(mInstructors);
        recyclerViewPopup.setAdapter(instructorDropDownAdapter);

        setContentView(view);
    }
}
