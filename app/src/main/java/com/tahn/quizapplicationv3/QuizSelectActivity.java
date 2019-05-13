package com.tahn.quizapplicationv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class QuizSelectActivity extends AppCompatActivity {

    Button imgToeic;
    Button imgFunny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_select);

        imgToeic = findViewById(R.id.imgToeic);
        imgFunny = findViewById(R.id.imgFunny);

        imgFunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizSelectActivity.this, QuizStartActivity.class);
                startActivity(intent);
            }
        });

        imgToeic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizSelectActivity.this, Q4Activity.class);
                startActivity(intent);
            }
        });

    }
}
