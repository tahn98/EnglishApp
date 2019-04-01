package com.tahn.quizapplicationv3;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tahn.quizapplicationv3.CustomAdapter.GrammarAdapter;
import com.tahn.quizapplicationv3.objectClass.Grammar;
import com.tahn.quizapplicationv3.ConstructData.ConstructGrammar;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ListView listView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        listView = findViewById(R.id.lvGrammar);
        ArrayList<Grammar> grammarArrayList = new ArrayList<>();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.quiz:
                                callQuizActivity();
                            case R.id.grammar:
                                break;
                        }
                        return true;
                    }
                });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        grammarArrayList = ConstructGrammar.returnArrayGrammarData();

        final GrammarAdapter grammarAdapter = new GrammarAdapter(this, grammarArrayList);
        listView.setAdapter(grammarAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(HomeActivity.this, "vi tri 0", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(HomeActivity.this, "vi tri 1", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void callQuizActivity(){
        Intent intent = new Intent(HomeActivity.this, QuizStartActivity.class);
        startActivity(intent);
    }
}
