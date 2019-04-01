package com.tahn.quizapplicationv3.ConstructData;

import com.tahn.quizapplicationv3.R;
import com.tahn.quizapplicationv3.objectClass.Grammar;

import java.util.ArrayList;

public class ConstructGrammar {

    private static ArrayList<Grammar> grammarArrayList = new ArrayList<>();

    public static ArrayList<Grammar> returnArrayGrammarData(){

        grammarArrayList.add(new Grammar("Mạo Từ", R.drawable.maotu));
        grammarArrayList.add(new Grammar("Trợ Động Từ", R.drawable.trodongtu));
        grammarArrayList.add(new Grammar("Mệnh Đề Quan Hệ", R.drawable.menhdequanhe));
        grammarArrayList.add(new Grammar("Tính Từ Kép", R.drawable.tinhtukep));
        grammarArrayList.add(new Grammar("Modal Verb", R.drawable.modalverb));
        return grammarArrayList;
    }



}
