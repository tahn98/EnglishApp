package com.tahn.quizapplicationv3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class ActivityVocab extends AppCompatActivity {

    private static Context context =null;

    public int i=0;
    public int ans=5;
    public int correctAns=5;
    LinearLayout[] ln = new LinearLayout[4];

    Button[] btn = new Button[4];
    public Button checkButton;
    public Button cancelButton;
    public Button barButton;
    public TextView question;
    TextView[] tvWord = new TextView[4];

    String[] word = new String[4];
    String[] mean = new String[4];
    int[] drawableResourceId = new int[4];
    int[] rawResourceId = new int[4];

    private SoundPool soundPool;
    private  int[] sound = new int[4];


    private static void setWidth(Button button, int i){
        int size= Resources.getSystem().getDisplayMetrics().widthPixels;
        size= size/11*i;
        LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(size,LinearLayout.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(lp);
    }
    private static void shuffleArray(int[] array)
    {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
    //function
    public void onClick(int index, final LinearLayout[] ln, int sound,SoundPool soundPool){
        for(int i=0;i<4;i++){
            ln[i].setBackgroundResource(R.drawable.custom_button);
        }
        ln[index].setBackgroundResource(R.drawable.onclickbutton);
        soundPool.play(sound, 1, 1, 0, 0, 1);
        ans=index;
        checkButton.setBackgroundResource(R.drawable.custom_button1);
    }
    private int showQuestion(int i, int[] solutionArray, DatabaseAccessVocab databaseAccessVocab, String str1, String str2){
        for (int j = 0; j < 4; j++) {
            int n = solutionArray[i * 4 + j];
            word[j] = databaseAccessVocab.getWord(n,str1);
            mean[j] = databaseAccessVocab.getMean(n,str1);
            drawableResourceId[j] = this.getResources().getIdentifier(word[j], "drawable", this.getPackageName());
            rawResourceId[j] = this.getResources().getIdentifier(word[j], "raw", this.getPackageName());
            sound[j] = soundPool.load(this, rawResourceId[j], 1);
            tvWord[j].setText(word[j]);
            btn[j].setBackgroundResource(drawableResourceId[j]);
        }
        Random rand = new Random();
        int n = rand.nextInt(4);
        question.setText("Hình nào là \" "+str2+" " + mean[n] + "\"");
        for (int j = 0; j < 4; j++) {
            final int finalJ = j;
            btn[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ans = finalJ;
                    ActivityVocab.this.onClick(finalJ, ln, sound[finalJ], soundPool);
                }
            });
            ln[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ans = finalJ;
                    ActivityVocab.this.onClick(finalJ, ln, sound[finalJ], soundPool);
                }
            });
        }
        return n;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab);

        Intent intent= getIntent();
        final String str1=intent.getStringExtra("key1");
        final String str2=intent.getStringExtra("key2");
        //Set button
        btn[0] = findViewById(R.id.button1);
        btn[1] = findViewById(R.id.button2);
        btn[2] = findViewById(R.id.button3);
        btn[3] = findViewById(R.id.button4);
        checkButton = findViewById(R.id.checkButton);
        cancelButton = findViewById(R.id.cancel);
        barButton = findViewById(R.id.barButton);

        tvWord[0] = findViewById(R.id.tvWord1);
        tvWord[1] = findViewById(R.id.tvWord2);
        tvWord[2] = findViewById(R.id.tvWord3);
        tvWord[3] = findViewById(R.id.tvWord4);
        ln[0] = findViewById(R.id.ln1);
        ln[1] = findViewById(R.id.ln2);
        ln[2] = findViewById(R.id.ln3);
        ln[3] = findViewById(R.id.ln4);
        question = findViewById(R.id.question);

        //khai báo các biến
        final int[] solutionArray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32};
        shuffleArray(solutionArray);
        final Boolean[] flagCancel = {false};


        //Set audio

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }else{
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }

        final int soundCorrect=soundPool.load(this,R.raw.correct,1);
        final int soundIncorrect=soundPool.load(this,R.raw.incorrect,1);
        //click vào button cancel
        final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        final AlertDialog.Builder dlgSuccess = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Are you sure?");
        dlgAlert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dlgAlert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dlgSuccess.setMessage("congratulations");
        dlgSuccess.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlgAlert.show();
            }
        });

        final DatabaseAccessVocab databaseAccessVocab = DatabaseAccessVocab.getInstance(getApplicationContext());
        databaseAccessVocab.open();

        correctAns =showQuestion(i,solutionArray, databaseAccessVocab,str1,str2);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans==5){
                    return;
                }
                if(ans== correctAns){
                    soundPool.play(soundCorrect, 1, 1, 0, 0, 1);
                    i+=1;
                    setWidth(barButton,i);
                    if(i==8){
                        dlgSuccess.show();
                    }else {
                        checkButton.setBackgroundResource(R.drawable.custom_button3);
                        ln[0].setBackgroundResource(R.drawable.custom_button);
                        ln[1].setBackgroundResource(R.drawable.custom_button);
                        ln[2].setBackgroundResource(R.drawable.custom_button);
                        ln[3].setBackgroundResource(R.drawable.custom_button);

                        correctAns =showQuestion(i,solutionArray, databaseAccessVocab,str1,str2);
                    }
                }else{
                    checkButton.setBackgroundResource(R.drawable.custom_button5);
                    soundPool.play(soundIncorrect, 1, 1, 0, 0, 1);
                }
            }
        });




    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
