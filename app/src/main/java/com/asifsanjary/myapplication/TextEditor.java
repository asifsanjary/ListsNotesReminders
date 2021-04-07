package com.asifsanjary.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.ViewModelProvider;

import com.asifsanjary.myapplication.note_view.NoteViewModel;
import com.asifsanjary.myapplication.repository.database.Note;

import static androidx.core.text.HtmlCompat.TO_HTML_PARAGRAPH_LINES_INDIVIDUAL;

public class TextEditor extends AppCompatActivity {

    private EditText editTextNoteTitle;
    private EditText editTextNoteContent;
    private NoteViewModel mNoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_editor);

        initView();

    }

    private void initView() {
        editTextNoteTitle = findViewById(R.id.note_title_edit_text);
        editTextNoteContent = findViewById(R.id.note_content_edit_text);
        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        mNoteViewModel.initViewModel(getApplication());
        //TODO: Load text from server
    }

    public void buttonBold(View view){
        Spannable spannableString = new SpannableStringBuilder(editTextNoteContent.getText());
        spannableString.setSpan(new StyleSpan(Typeface.BOLD),
                editTextNoteContent.getSelectionStart(),
                editTextNoteContent.getSelectionEnd(),
                0);

        editTextNoteContent.setText(spannableString);
    }
    public void buttonItalics(View view){
        Spannable spannableString = new SpannableStringBuilder(editTextNoteContent.getText());
        spannableString.setSpan(new StyleSpan(Typeface.ITALIC),
                editTextNoteContent.getSelectionStart(),
                editTextNoteContent.getSelectionEnd(),
                0);

        editTextNoteContent.setText(spannableString);

    }
    public void buttonUnderline(View view){
        Spannable spannableString = new SpannableStringBuilder(editTextNoteContent.getText());
        spannableString.setSpan(new UnderlineSpan(),
                editTextNoteContent.getSelectionStart(),
                editTextNoteContent.getSelectionEnd(),
                0);

        editTextNoteContent.setText(spannableString);
    }

    public void buttonNoFormat(View view){
        String stringText = editTextNoteContent.getText().toString();
        editTextNoteContent.setText(stringText);
    }


    public void buttonAlignmentLeft(View view){
        editTextNoteContent.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        Spannable spannableString = new SpannableStringBuilder(editTextNoteContent.getText());
        editTextNoteContent.setText(spannableString);
    }

    public void buttonAlignmentCenter(View view){
        editTextNoteContent.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Spannable spannableString = new SpannableStringBuilder(editTextNoteContent.getText());
        editTextNoteContent.setText(spannableString);
    }

    public void buttonAlignmentRight(View view){
        editTextNoteContent.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        Spannable spannableString = new SpannableStringBuilder(editTextNoteContent.getText());
        editTextNoteContent.setText(spannableString);
    }

    public void saveAndExit(View view) {
        String noteTitle = editTextNoteTitle.getText().toString();
        Spannable spannableString = new SpannableStringBuilder(editTextNoteContent.getText());
        // TODO: use HTMLCompact
        String noteContent = Html.toHtml(spannableString);

        Note note = new Note();
        note.noteTitle = noteTitle;
        note.noteContent = noteContent;

        mNoteViewModel.insertNote(note);

        // TODO: Handle this, not following best practices
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}