package com.live.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class TxtUtils {

    public static void setTextView(TextView textView,String word){
        if(textView != null && !TextUtils.isEmpty(word)){
            textView.setText(word);
        }
    }

    public static void setImageView(Context context, ImageView imageView, String image) {
        if (imageView != null) {
            Glide.with(context).load(image).into(imageView);
        }
    }

}
