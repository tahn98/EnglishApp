package com.tahn.quizapplicationv3;

import android.content.Context;
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

import com.tahn.quizapplicationv3.dataController.DatabaseAccess;
import com.tahn.quizapplicationv3.objectClass.Question;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILIS = 30000;

    private TextView getTextViewQuestion;
    private Context context;

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirm;

    private ColorStateList textColorDefaultRb;
    private ColorStateList getTextColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Mapping();

        textColorDefaultRb = rb1.getTextColors();
        getTextColorDefaultCd = textViewCountDown.getTextColors();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        questionList = databaseAccess.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
                        checkAnswer();
                    }else{
                        Toast.makeText(getApplicationContext(), "Vui lòng chọn đáp án !", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion(){
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if(questionCounter < questionCountTotal){
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);

            answered = false;
            buttonConfirm.setText("Confirm");

            timeLeftInMillis = COUNTDOWN_IN_MILIS;
            startCountDown();
        }else{
            finishQuiz();
            //finish();
        }
    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis / 1000)/60;
        int seconds = (int) (timeLeftInMillis/1000)%60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if(timeLeftInMillis < 10000){
            textViewCountDown.setTextColor(Color.RED);
        }
        else{
            textViewCountDown.setTextColor(getTextColorDefaultCd);
        }
    }

    private void checkAnswer(){
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if(answerNr == currentQuestion.getAnswerNr()){
            score++;
            textViewScore.setText("Score: " + score);
        }

        showSolution();
    }

    private void showSolution(){
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Đáp Án A Đúng");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Đáp Án B Đúng");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Đáp Án C Đúng");
                break;
        }

        if(questionCounter < questionCountTotal){
            buttonConfirm.setText("Next");
        }
        else{
            buttonConfirm.setText("Finish");
        }
    }

    private void finishQuiz(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
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

    public void Mapping(){
        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewScore = findViewById(R.id.textviewScore);
        textViewQuestionCount = findViewById(R.id.textviewQuestionCount);
        textViewCountDown = findViewById(R.id.textViewCountDown);
        rbGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        buttonConfirm = findViewById(R.id.buttonConfirm);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}

