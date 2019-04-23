package com.tahn.quizapplicationv3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Q4Activity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    public static final String SHARED_PREFS_Q4="sharedPreft";
    public static final String KEY_HIGHSCORE_Q4="keyHighscore";
    private TextView tvmaxscore;
    private int highscore;
    Button startQ4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q4);
        startQ4=(Button) findViewById(R.id.Q4start);
        tvmaxscore=(TextView) findViewById(R.id.Q4maxscore);
        loadhighscore();
        startQ4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startquizQ4();
            }
        });
    }
    private  void startquizQ4(){
        Intent intent= new Intent(Q4Activity.this,ActivityanswersQ4.class);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE)
            if(resultCode==RESULT_OK)
            {
                int score=data.getIntExtra(ActivityanswersQ4.EXTRA_SCORE,0);
                if(score>highscore)
                    updatehighscore(score);
            }
    }
    private void updatehighscore(int score)
    {
        highscore=score;
        tvmaxscore.setText("Best Score: "+highscore);
        SharedPreferences prefs= getSharedPreferences(SHARED_PREFS_Q4,MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putInt(KEY_HIGHSCORE_Q4,highscore);
        editor.apply();

    }
    private void loadhighscore()
    {
        SharedPreferences prefs=getSharedPreferences(SHARED_PREFS_Q4,MODE_PRIVATE);
        highscore=prefs.getInt(KEY_HIGHSCORE_Q4,0);
        tvmaxscore.setText("Best Score: "+highscore);
    }
}
