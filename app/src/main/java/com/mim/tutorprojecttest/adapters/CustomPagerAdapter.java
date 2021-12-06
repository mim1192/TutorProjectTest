package com.mim.tutorprojecttest.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.mim.tutorprojecttest.R;
import com.mim.tutorprojecttest.classes.Detail;


import java.util.ArrayList;

public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<Detail> list = new ArrayList<>();


    public CustomPagerAdapter(Context context, ArrayList<Detail> mlist) {

        mContext = context;
        list = mlist;

    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        Detail modelObject = list.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.pager_item, collection, false);

        TextView id = itemView.findViewById(R.id.id);
        TextView name = itemView.findViewById(R.id.name);
        TextView date = itemView.findViewById(R.id.date);
        TextView classis = itemView.findViewById(R.id.classis);
        TextView height = itemView.findViewById(R.id.height);


        id.setText(modelObject.getId());
        name.setText(modelObject.getName());
        date.setText(modelObject.getDate());
        classis.setText(modelObject.getClass_());
        height.setText(modelObject.getHeight());


        collection.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return list.size();
    }


}