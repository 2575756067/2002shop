package com.example.shopkuang.ui.Details;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.shopkuang.utils.TxtUtils;
import com.github.chrisbanes.photoview.PhotoView;


import java.util.ArrayList;
import java.util.List;

public class BigImageAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<String> list;

    public BigImageAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        PhotoView photoView = new PhotoView(context);
        TxtUtils.setPhotoView(context,photoView,list.get(position));
        container.addView(photoView);//添加进入视图
        return photoView;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
