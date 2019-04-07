package com.tahn.quizapplicationv3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class ArticleActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        pdfView = findViewById(R.id.pdfArticles);
        pdfView.fromAsset("articles.pdf").load();
    }
}
