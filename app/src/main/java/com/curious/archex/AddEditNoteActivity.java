package com.curious.archex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    
    public static final String EXTRA_ID = "com.curious.archex.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.curious.archex.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.curious.archex.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.curious.archex.EXTRA_PRIORITY";

    private EditText titleEditText;
    private EditText descriptionEditText;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleEditText = findViewById(R.id.titleEditTextID);
        descriptionEditText = findViewById(R.id.descriptionEditTextID);
        numberPicker = findViewById(R.id.numberPickerID);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            titleEditText.setText(intent.getStringExtra(EXTRA_TITLE));
            descriptionEditText.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_TITLE, 1));
        }else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    private void saveNote(){
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString();
        int priority = numberPicker.getValue();

        if (description.trim().isEmpty()){
            Toast.makeText(this, "Insert title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1){
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.saveNoteID:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}















