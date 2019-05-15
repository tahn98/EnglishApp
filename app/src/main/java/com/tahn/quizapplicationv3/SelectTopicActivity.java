package com.tahn.quizapplicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SelectTopicActivity extends AppCompatActivity {
    Button[] buttons = new Button[5];
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_topic);

        buttons[0]= (Button) findViewById(R.id.buttonAnimal);
        buttons[1]= (Button) findViewById(R.id.buttonFruit);
        buttons[2]= (Button) findViewById(R.id.buttonJob);
        buttons[3]= (Button) findViewById(R.id.buttonFamily);
        buttons[4]= (Button) findViewById(R.id.buttonZodiac);
        backButton= (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        for(int i=0;i<3;i++){
            final int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(SelectTopicActivity.this,ActivityVocab.class);
                    if(finalI ==0){
                        myIntent.putExtra("key1","animal");
                        myIntent.putExtra("key2","con");
                    }else if(finalI==1){
                        myIntent.putExtra("key1","fruit");
                        myIntent.putExtra("key2","quả");
                    }else if(finalI==2){
                        myIntent.putExtra("key1","job");
                        myIntent.putExtra("key2","");
                    }

                    startActivity(myIntent);
                }
            });
        }

    }
}
