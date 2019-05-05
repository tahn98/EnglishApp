package com.tahn.quizapplicationv3;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.tahn.quizapplicationv3.objectClass.WordNote;

import java.util.ArrayList;
import java.util.List;


public class WordFragment extends Fragment {

    List<WordNote> wordNoteList;
    DatabaseHelper dbHelper;
    FragmentListener listener;
    DatabaseManager dbManager;
    SimpleCursorAdapter adapter;
    final String[] from = new String[]{dbHelper._ID, dbHelper.TITLE};
    final int[] to = new int[]{R.id.id, R.id.listTitle};

    public WordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(getActivity());
        dbManager.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_word, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        wordNoteList = new ArrayList<>();
        wordNoteList = dbManager.getAllWord();
        super.onViewCreated(view, savedInstanceState);

        final ListView listView = view.findViewById(R.id.listView);
        Cursor cursor = dbManager.fetch();
        adapter = new SimpleCursorAdapter(getActivity(), R.layout.adapter, cursor, from, to, 0);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listener != null){
                    String word = wordNoteList.get(position).getWord();
                    String desc = wordNoteList.get(position).getDesc();
                    listener.onItemClick(word, desc);
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String[] items = {"Remove"};
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select Function");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                dbManager.delete(pos + 1);
                                Cursor cursor = dbManager.fetch();
                                adapter = new SimpleCursorAdapter(getActivity(), R.layout.adapter, cursor, from, to, 0);
                                listView.setAdapter(adapter);
                                break;
                        }
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setOnFragmnentListener(FragmentListener listener){
        this.listener = listener;
    }
}
