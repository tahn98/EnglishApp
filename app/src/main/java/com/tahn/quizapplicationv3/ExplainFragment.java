package com.tahn.quizapplicationv3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ExplainFragment extends Fragment {
    private String word;
    private String desc;

    public ExplainFragment() {
        // Required empty public constructor
    }

    public static ExplainFragment getNewInstance(String word, String desc){
        ExplainFragment explainFragment = new ExplainFragment();
        explainFragment.word = word;
        explainFragment.desc = desc;
        return explainFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explain, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txtNewWord = view.findViewById(R.id.txtNewWord);
        TextView txtExplain = view.findViewById(R.id.txtExplain);
        txtNewWord.setText(word);
        txtExplain.setText(desc);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setOnFragmentListener(FragmentListener listener){

    }
}
