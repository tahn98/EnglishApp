package com.tahn.quizapplicationv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class ArticleActivity extends AppCompatActivity {

    PDFView pdfView;

    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        key = (String) bundle.get("Grammar_Case");

        switch (key){
            case "article":
                pdfView = findViewById(R.id.pdfArticles);
                pdfView.fromAsset("articles.pdf").load();
                break;
            case "hello":
                Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();
                break;
        }


    }
}
