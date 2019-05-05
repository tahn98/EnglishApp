package com.tahn.quizapplicationv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class ListenAudioActivity extends AppCompatActivity {
    Button btn;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_audio);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        key = (String) bundle.get("Listen_Case");
        btn=(Button) findViewById(R.id.button);
        switch (key){
            case "1":
                btn.setText("1");
                break;
            case "2":
                btn.setText("2");
                break;
            case "3":
                btn.setText("3");
                break;
        }

    }
}
