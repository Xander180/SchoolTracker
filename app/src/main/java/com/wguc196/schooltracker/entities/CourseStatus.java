package com.wguc196.schooltracker.entities;

import androidx.annotation.NonNull;

public enum CourseStatus {
    IN_PROGRESS {
        @NonNull
        @Override
        public String toString() {
            return "In Progress";
        }
    },

    COMPLETED {
        @NonNull
        @Override
        public String toString() {
            return "Completed";
        }
    },

    DROPPED {
        @NonNull
        @Override
        public String toString() {
            return "Dropped";
        }
    },

    PLAN_TO_TAKE {
        @NonNull
        @Override
        public String toString() {
            return "Plan to take";
        }
    }
}
