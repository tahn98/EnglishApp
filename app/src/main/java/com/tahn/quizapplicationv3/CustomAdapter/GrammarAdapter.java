package com.tahn.quizapplicationv3.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tahn.quizapplicationv3.R;
import com.tahn.quizapplicationv3.objectClass.Grammar;

import java.util.ArrayList;

public class GrammarAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Grammar> grammarArrayList;

    public GrammarAdapter(Context context, ArrayList<Grammar> grammarArrayList){
        this.context = context;
        this.grammarArrayList = grammarArrayList;
    }

    @Override
    public int getCount() {
        return grammarArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return grammarArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_main_custom, parent, false);
        }

        TextView txtItemName = convertView.findViewById(R.id.txtName);
        ImageView imgView = convertView.findViewById(R.id.img_View);
        txtItemName.setText(grammarArrayList.get(position).getName());
        imgView.setImageResource(grammarArrayList.get(position).getImgId());

        return convertView;
    }
}
