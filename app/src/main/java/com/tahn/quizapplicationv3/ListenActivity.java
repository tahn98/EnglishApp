package com.tahn.quizapplicationv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tahn.quizapplicationv3.CustomAdapter.ListenAdapter;
import com.tahn.quizapplicationv3.objectClass.Listen;

import java.util.ArrayList;
import java.util.List;

public class ListenActivity extends AppCompatActivity {

    ListView lvListen;
    ArrayList<Listen> arrayListen;
    ListenAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
        lvListen = (ListView) findViewById(R.id.lstListen);
        arrayListen = new ArrayList<>();
        arrayListen.add(new Listen("Photo",R.drawable.frame));
        arrayListen.add(new Listen("Question & Response", R.drawable.dialogue));
        arrayListen.add(new Listen("Short Talk",R.drawable.conversation));
        adapter = new ListenAdapter(this,R.layout.listview_listen_custom,arrayListen);
        lvListen.setAdapter(adapter);
        lvListen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListenActivity.this,ListenAudioActivity.class);
                startActivity(intent);
            }
        });
    }
}
