package com.asifsanjary.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TextEditor extends AppCompatActivity {

    private EditText editTextTextMultiLine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_editor);

        initView();

    }

    private void initView() {
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        //TODO: Load text from server
    }

    public void buttonBold(View view){
        Spannable spannableString = new SpannableStringBuilder(editTextTextMultiLine.getText());
        spannableString.setSpan(new StyleSpan(Typeface.BOLD),
                editTextTextMultiLine.getSelectionStart(),
                editTextTextMultiLine.getSelectionEnd(),
                0);

        editTextTextMultiLine.setText(spannableString);
    }
    public void buttonItalics(View view){
        Spannable spannableString = new SpannableStringBuilder(editTextTextMultiLine.getText());
        spannableString.setSpan(new StyleSpan(Typeface.ITALIC),
                editTextTextMultiLine.getSelectionStart(),
                editTextTextMultiLine.getSelectionEnd(),
                0);

        editTextTextMultiLine.setText(spannableString);

    }
    public void buttonUnderline(View view){
        Spannable spannableString = new SpannableStringBuilder(editTextTextMultiLine.getText());
        spannableString.setSpan(new UnderlineSpan(),
                editTextTextMultiLine.getSelectionStart(),
                editTextTextMultiLine.getSelectionEnd(),
                0);

        editTextTextMultiLine.setText(spannableString);
    }

    public void buttonNoFormat(View view){
        String stringText = editTextTextMultiLine.getText().toString();
        editTextTextMultiLine.setText(stringText);
    }


    public void buttonAlignmentLeft(View view){
        editTextTextMultiLine.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        Spannable spannableString = new SpannableStringBuilder(editTextTextMultiLine.getText());
        editTextTextMultiLine.setText(spannableString);
    }

    public void buttonAlignmentCenter(View view){
        editTextTextMultiLine.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Spannable spannableString = new SpannableStringBuilder(editTextTextMultiLine.getText());
        editTextTextMultiLine.setText(spannableString);
    }

    public void buttonAlignmentRight(View view){
        editTextTextMultiLine.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        Spannable spannableString = new SpannableStringBuilder(editTextTextMultiLine.getText());
        editTextTextMultiLine.setText(spannableString);
    }
}