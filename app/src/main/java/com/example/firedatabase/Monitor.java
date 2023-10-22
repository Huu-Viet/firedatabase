package com.example.firedatabase;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class Monitor {

    Spinner spinner;
    TextView textView;
    Button button;
    public Monitor(Spinner spinner, TextView textView, Button button) {
        this.spinner = spinner;
        this.textView = textView;
        this.button = button;
    }

    public TextView getTextView() {
        return textView;
    }
}
