package com.tahn.quizapplicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;

public class AddItemActivity extends AppCompatActivity {

    private FloatingActionButton fabCheck;
    private EditText edtNewWord;
    private EditText edtDesc;
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        edtNewWord = findViewById(R.id.edtNewWord);
        edtDesc = findViewById(R.id.edtExplain);
        fabCheck = findViewById(R.id.check);

        dbManager = new DatabaseManager(this);
        dbManager.open();

        fabCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newWord = edtNewWord.getText().toString();
                String desc = edtDesc.getText().toString();
                if (newWord.isEmpty() || desc.isEmpty()) {
                    Snackbar.make(v, "Please fill in both form!", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    dbManager.insert(newWord, desc);
                    Intent intent = new Intent(AddItemActivity.this, NoteActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }
}
