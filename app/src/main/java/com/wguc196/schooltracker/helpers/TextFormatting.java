package com.wguc196.schooltracker.helpers;

import java.text.SimpleDateFormat;

public class TextFormatting {
    public static String cardPattern = "MMM d yyyy";
    private static final String fullPattern = "MM/dd/yyyy";
    public static SimpleDateFormat cardDateFormat = new SimpleDateFormat(cardPattern);
    public static SimpleDateFormat fullDateFormat = new SimpleDateFormat(fullPattern);
}
