package com.tahn.quizapplicationv3.ConstructData;

import com.tahn.quizapplicationv3.R;
import com.tahn.quizapplicationv3.objectClass.Grammar;

import java.util.ArrayList;
import java.util.Arrays;

public class ConstructGrammar {

    private static ArrayList<Grammar> grammarArrayList = new ArrayList<>(
            Arrays.asList(new Grammar("Modal Verbs", R.drawable.mdv),
                    new Grammar("Passive Voice", R.drawable.psv),
                    new Grammar("Imperative", R.drawable.imperative),
                    new Grammar("Infinity And Gerund", R.drawable.ig),
                    new Grammar("Conditional Tense", R.drawable.cdt),
                    new Grammar("If Clause",R.drawable.ifcla),
                    new Grammar("Time Clause", R.drawable.timc),
                    new Grammar("Relative Clause", R.drawable.rel),
                    new Grammar("Direct - Indirect Object", R.drawable.dio),
                    new Grammar("Report Speech", R.drawable.resp))
    );

    public static ArrayList<Grammar> returnArrayGrammarData(){
        return grammarArrayList;
    }
}
