package com.tahn.quizapplicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tahn.quizapplicationv3.ConstructData.ConstructListen;
import com.tahn.quizapplicationv3.CustomAdapter.ListenAdapter;
import com.tahn.quizapplicationv3.objectClass.Listen;

import java.util.ArrayList;

public class ListenActivity extends AppCompatActivity {

    ListView lvListen;
    ArrayList<Listen> arrayListen = new ArrayList<>();
    ListenAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
        lvListen = findViewById(R.id.lstListen);
        arrayListen= ConstructListen.returnArrayListenData();
        adapter = new ListenAdapter(this, R.layout.listview_listen_custom,arrayListen);
        lvListen.setAdapter(adapter);
        lvListen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListenActivity.this, ListenAudioActivity.class);
                switch (position){
                    case 0:
                        intent.putExtra("Listen_Case", 0);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("Listen_Case", 1);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("Listen_Case", 2);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra("Listen_Case", 3);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.putExtra("Listen_Case", 4);
                        startActivity(intent);
                        break;
                    case 5:
                        intent.putExtra("Listen_Case", 5);
                        startActivity(intent);
                        break;
                    case 6:
                        intent.putExtra("Listen_Case", 6);
                        startActivity(intent);
                        break;
                    case 7:
                        intent.putExtra("Listen_Case", 7);
                        startActivity(intent);
                        break;
                    case 8:
                        intent.putExtra("Listen_Case", 8);
                        startActivity(intent);
                        break;
                    case 9:
                        intent.putExtra("Listen_Case", 9);
                        startActivity(intent);
                        break;
                }

            }
        });
    }
}
