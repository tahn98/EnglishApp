package com.tahn.quizapplicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

        pdfView = findViewById(R.id.pdfArticles);
        switch (key){
            case "modal":
                pdfView.fromAsset("modal_verbs.pdf").load();
                break;
            case "passive":
                pdfView.fromAsset("passive_voice.pdf").load();
                break;
            case "imp":
                pdfView.fromAsset("imperative.pdf").load();
                break;
            case "inf_and_ge":
                pdfView.fromAsset("infinitive_gerund.pdf").load();
                break;
            case "cond":
                pdfView.fromAsset("conditional_tense.pdf").load();
                break;
            case "ifc":
                pdfView.fromAsset("if.pdf").load();
                break;
            case "timecl":
                pdfView.fromAsset("time_clause.pdf").load();
                break;
            case "rel":
                pdfView.fromAsset("relative_clauses.pdf").load();
                break;
            case "reps":
                pdfView.fromAsset("reported_speech.pdf").load();
                break;
        }
    }
}
