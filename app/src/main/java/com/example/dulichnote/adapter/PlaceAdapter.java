package com.example.dulichnote.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.dulichnote.R;
import com.example.dulichnote.model.PlaceItem;

import java.util.List;

public class PlaceAdapter extends PagerAdapter {


    private final List<PlaceItem> listPlace;

    private final Context context;
    private final View.OnClickListener event;

    public PlaceAdapter(List<PlaceItem> listPlace, Context context, View.OnClickListener event) {
        this.listPlace = listPlace;
        this.context = context;
        this.event = event;
    }


    @Override
    public int getCount() {
        return listPlace.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_place, container,false);

        ImageView ivPlace = v.findViewById(R.id.iv_photo);
        TextView tvName = v.findViewById(R.id.tv_name);
        TextView tvDesc = v.findViewById(R.id.tv_desc);

        PlaceItem item = listPlace.get(position);
        Log.e("TAG", "" + item.toString());
        Glide.with(context).load(item.linkPhoto).into(ivPlace);
        tvName.setText(item.name);
        tvDesc.setText(item.desc);
        ivPlace.setTag(item);

        ivPlace.setOnClickListener(event);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
