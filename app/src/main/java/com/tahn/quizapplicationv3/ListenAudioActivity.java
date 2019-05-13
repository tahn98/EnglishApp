package com.tahn.quizapplicationv3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tahn.quizapplicationv3.ConstructData.ConstructListen;
import com.tahn.quizapplicationv3.objectClass.Listen;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListenAudioActivity extends AppCompatActivity {
    int key;
    MediaPlayer audio;
    SeekBar sbSong;
    ImageButton btnPlay;
    TextView txtView,curTime,totalTime,txtScript;
    Button btnScript;
    ArrayList<Listen> arrayListen = new ArrayList<>();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_audio);
        sbSong=(SeekBar) findViewById(R.id.seekBarSong);
        btnPlay=(ImageButton) findViewById(R.id.btnPlay);
        txtView=(TextView) findViewById(R.id.txtView);
        curTime=(TextView)  findViewById(R.id.curTime);
        totalTime=(TextView) findViewById(R.id.totalTime);
        txtScript=(TextView) findViewById(R.id.txtScript);
        btnScript=(Button) findViewById(R.id.btnScript);

        arrayListen= ConstructListen.returnArrayListenData();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        key = (int) bundle.get("Listen_Case");
        txtView.setText(arrayListen.get(key).getName());
        audio = MediaPlayer.create(ListenAudioActivity.this,arrayListen.get(key).getFileAudio());

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!audio.isPlaying()) {
                    audio.start();
                    btnPlay.setImageResource(R.drawable.pause);
                }
                else
                {
                    audio.pause();
                    btnPlay.setImageResource(R.drawable.play);
                }
                setTotalTime();
                updateTime();
            }
        });
        sbSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                audio.seekTo(sbSong.getProgress());

            }
        });
//        InputStream inputStream = getResources().openRawResource(R.raw.school);
        InputStream inputStream= getResources().openRawResource(arrayListen.get(key).getFileScript());

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        btnScript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtScript.getText().toString()=="") {
                    txtScript.setText(byteArrayOutputStream.toString());
                    txtScript.setMovementMethod(new ScrollingMovementMethod());
                }
                else
                {
                    txtScript.setText("");
                }
            }
        });


    }
    private void setTotalTime()
    {
        SimpleDateFormat timeFormat=new SimpleDateFormat("mm:ss");
        totalTime.setText(timeFormat.format(audio.getDuration()));
        sbSong.setMax(audio.getDuration());
    }
    private void updateTime()
    {
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat timeFormat=new SimpleDateFormat("mm:ss");
                sbSong.setProgress(audio.getCurrentPosition());
                curTime.setText(timeFormat.format(audio.getCurrentPosition()));
                handler.postDelayed(this,500);
            }
        },100);
    }

    @Override
    protected void onStop() {
        audio.pause();
        btnPlay.setImageResource(R.drawable.play);

        super.onStop();
    }
}
