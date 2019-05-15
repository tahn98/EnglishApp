package com.tahn.quizapplicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

public class NoteActivity extends AppCompatActivity {

    WordFragment wordFragment;
    ExplainFragment explainFragment;
    FloatingActionButton fab;
    String word;
    String descp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Note");

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });

        wordFragment = new WordFragment();
        explainFragment = new ExplainFragment();

        goToFragment(wordFragment, true);
        wordFragment.setOnFragmnentListener(new FragmentListener() {
            @Override
            public void onItemClick(String word, String descp) {
                goToFragment(ExplainFragment.getNewInstance(word, descp), false);
            }
        });
    }

    public void goToFragment(Fragment fragment, boolean isTop){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frameMainLayout, fragment);
        if(!isTop){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
