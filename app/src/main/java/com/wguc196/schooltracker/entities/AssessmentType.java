package com.wguc196.schooltracker.entities;

import androidx.annotation.NonNull;

public enum AssessmentType {
    OA {
        @NonNull
        @Override
        public String toString() {
            return "Objective Assessment";
        }
    },

    PA {
        @NonNull
        @Override
        public String toString() {
            return "Performance Assessment";
        }
    }
}
