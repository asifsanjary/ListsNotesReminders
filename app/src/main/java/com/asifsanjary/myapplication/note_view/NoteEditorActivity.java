package com.asifsanjary.myapplication.note_view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.asifsanjary.myapplication.MainActivity;
import com.asifsanjary.myapplication.R;
import com.asifsanjary.myapplication.repository.database.entity.Note;

public class NoteEditorActivity extends AppCompatActivity {

    public static final String NOTE_KEY = "NOTE_KEY_37Y73";
    public static final String NOTE_ID_KEY = "NOTE_ID_KEY_37Y73";
    public static final String NOTE_TITLE_KEY = "NOTE_TITLE_KEY_37Y73";
    public static final String NOTE_CONTENT_KEY = "NOTE_CONTENT_KEY_37Y73";

    private EditText editTextNoteTitle;
    private EditText editTextNoteContent;
    private NoteViewModel mNoteViewModel;

    private boolean noteFound = false;
    private String foundNoteTitle;
    private String foundNoteContent;
    private int foundNoteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_editor_activity_layout);

        Bundle bundle = getIntent().getBundleExtra(NOTE_KEY);
        if(bundle != null) {
            foundNoteId = bundle.getInt(NOTE_ID_KEY);
            foundNoteTitle = bundle.getString(NOTE_TITLE_KEY);
            foundNoteContent = bundle.getString(NOTE_CONTENT_KEY);
            Log.d("Text_Editor_sanjary ", foundNoteId + " " + foundNoteTitle + " " + foundNoteContent);
            if (!foundNoteTitle.isEmpty()) noteFound = true;
        }

        initView();

    }

    private void initView() {
        editTextNoteTitle = findViewById(R.id.note_title_edit_text);
        editTextNoteContent = findViewById(R.id.note_content_edit_text);

        if(noteFound) {
            editTextNoteTitle.setText(foundNoteTitle);
            SpannableString spannableString = new SpannableString(Html.fromHtml(foundNoteContent));
            editTextNoteContent.setText(spannableString);
        }

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
        if(noteFound) {
            note.uid = foundNoteId;
        }
        note.noteTitle = noteTitle;
        note.noteContent = noteContent;

        mNoteViewModel.insertNote(note);

        // TODO: Handle this, not following best practices
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}