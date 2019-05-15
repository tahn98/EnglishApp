package com.tahn.quizapplicationv3;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tahn.quizapplicationv3.dataController.DatabaseQ4Access;
import com.tahn.quizapplicationv3.objectClass.Question4;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ActivityanswersQ4 extends AppCompatActivity {
    public static final String EXTRA_SCORE="extraScore";
    public static final long COUNTDOWN_IN_MILLS=30000;
    private TextView tvQ4question;
    private TextView tvQ4count;
    private TextView tvQ4countdown;
    private TextView tvscore;
    private RadioGroup rgQ4;
    private RadioButton rbAQ4;
    private RadioButton rbBQ4;
    private RadioButton rbCQ4;
    private RadioButton rbDQ4;
    private Button btconfirmQ4;

    private List<Question4> listQ4;
    private ColorStateList textColordefaultrb;
    private final int totalQ4question=20;
    private int questioncounter=0;
    private Question4 curquestion;
    private int score=0;
    private boolean answer;
    private long backPressTime;
    private ColorStateList textColordefaultCd;
    private CountDownTimer cdtm;
    private long timeleft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityanswers_q4);
        anhxa();
        DatabaseQ4Access dbacessQ4= DatabaseQ4Access.getInstance(this);
        dbacessQ4.open();
        listQ4=dbacessQ4.get2question();
        dbacessQ4.close();
        Collections.shuffle(listQ4);
        textColordefaultrb=rbAQ4.getTextColors();
        textColordefaultCd=tvQ4countdown.getTextColors();
        shownextQuestion();
        btconfirmQ4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityanswersQ4.this,"hãy chọn đáp án",Toast.LENGTH_LONG);
                if(!answer) {

                    if(rbAQ4.isChecked() || rbBQ4.isChecked() ||rbCQ4.isChecked() ||rbDQ4.isChecked() )
                        checkanswer();
                    else
                        Toast.makeText(ActivityanswersQ4.this,"hãy chọn đáp án",Toast.LENGTH_LONG).show();
                }
                else
                    shownextQuestion();

            }
        });



    }
    private void anhxa()
    {
        tvQ4question =(TextView) findViewById(R.id.Q4question);
        tvQ4count=(TextView) findViewById(R.id.Q4count);
        tvQ4countdown=(TextView) findViewById(R.id.Q4countdown);
        tvscore=(TextView) findViewById(R.id.Q4score);
        rgQ4=(RadioGroup) findViewById(R.id.Q4radiogroup);
        rbAQ4=(RadioButton) findViewById(R.id.Q4A);
        rbBQ4=(RadioButton) findViewById(R.id.Q4B);
        rbCQ4=(RadioButton) findViewById(R.id.Q4C);
        rbDQ4=(RadioButton) findViewById(R.id.Q4D);
        btconfirmQ4=(Button) findViewById(R.id.Q4confirm);


    }
    private void shownextQuestion() {
        rbAQ4.setTextColor(textColordefaultrb);
        rbBQ4.setTextColor(textColordefaultrb);
        rbCQ4.setTextColor(textColordefaultrb);
        rbDQ4.setTextColor(textColordefaultrb);
        rgQ4.clearCheck();
        if (questioncounter < totalQ4question) {
            curquestion = listQ4.get(questioncounter);
            tvQ4question.setText(curquestion.getQuestion());
            rbAQ4.setText("A." + curquestion.getOption1());
            rbBQ4.setText("B." + curquestion.getOption2());
            rbCQ4.setText("C." + curquestion.getOption3());
            rbDQ4.setText("D." + curquestion.getOption4());

            answer = false;
            tvQ4count.setText("Question: " + (questioncounter + 1) + "/"+totalQ4question);
            questioncounter++;
            btconfirmQ4.setText("Xác nhận");
            timeleft = COUNTDOWN_IN_MILLS;
            startCountdown();
        } else {
            finishQuiz();
        }
    }
    private void startCountdown() {
        cdtm = new CountDownTimer(timeleft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleft=millisUntilFinished;
                updatecountdowntext(timeleft);


            }

            @Override
            public void onFinish() {
                timeleft=0;
                updatecountdowntext(timeleft);
                checkanswer();
            }

        };
        cdtm.start();

    }
    private void updatecountdowntext(long millisUntilFinished)
    {
        int minutes = (int) millisUntilFinished / 1000 / 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;
        String timformat = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvQ4countdown.setText(timformat);
        if (millisUntilFinished < 10000)
            tvQ4countdown.setTextColor(Color.RED);
        else
            tvQ4countdown.setTextColor(textColordefaultCd);
    }
    private void finishQuiz(){
        Intent itresult= new Intent();
        itresult.putExtra(EXTRA_SCORE,score);
        setResult(RESULT_OK,itresult);
        finish();
    }
    private void checkanswer(){
        answer=true;
        cdtm.cancel();
        RadioButton rbischeck=(RadioButton) findViewById(rgQ4.getCheckedRadioButtonId());
        int answeruser=rgQ4.indexOfChild(rbischeck);
        if(answeruser+1== curquestion.getAnswerNr()){
            score++;
            tvscore.setText("Score: "+score);
        }
        showsolution();
    }
    private void showsolution(){
        rbAQ4.setTextColor(Color.RED);
        rbBQ4.setTextColor(Color.RED);
        rbCQ4.setTextColor(Color.RED);
        rbDQ4.setTextColor(Color.RED);
        switch (curquestion.getAnswerNr())
        {
            case 1:{
                rbAQ4.setTextColor(Color.GREEN);
                tvQ4question.setText("Đáp án là A");
                break;
            }
            case 2:{
                rbBQ4.setTextColor(Color.GREEN);
                tvQ4question.setText("Đáp án là B");
                break;
            }
            case 3:{
                rbCQ4.setTextColor(Color.GREEN);
                tvQ4question.setText("Đáp án là C");
                break;
            }
            case 4:{
                rbDQ4.setTextColor(Color.GREEN);
                tvQ4question.setText("Đáp án là D");
                break;
            }

        }
        if(questioncounter< totalQ4question)
            btconfirmQ4.setText("Câu tiếp theo");
        else            btconfirmQ4.setText("Kết thúc");
    }
    @Override
    public void onBackPressed() {
        if(backPressTime + 2000 > System.currentTimeMillis()){
            finishQuiz();
        }else{
            Toast.makeText(this, "Bấm Back lần nữa để thoát nhé !", Toast.LENGTH_LONG).show();
        }

        backPressTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cdtm!=null)
            cdtm.cancel();
    }
}

