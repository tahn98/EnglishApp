package com.tahn.quizapplicationv3.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tahn.quizapplicationv3.R;
import com.tahn.quizapplicationv3.objectClass.Listen;

import java.util.ArrayList;

public class ListenAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Listen> listenArrayList;

    public ListenAdapter(Context context, int layout, ArrayList<Listen> listenArrayList) {
        this.context = context;
        this.layout = layout;
        this.listenArrayList = listenArrayList;
    }

    @Override
    public int getCount() {
        return listenArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);
        TextView txtName=(TextView) convertView.findViewById(R.id.txtName);
        Listen listen=listenArrayList.get(position);
        txtName.setText(listen.getName());
        return convertView;
    }
}
