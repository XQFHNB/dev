package com.example.owo.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * 图片加载工具
 */
public class MyImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {


// 使用ImageLoader加载本地图片
        DisplayImageOptions options = new DisplayImageOptions.Builder()//
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)//图片大小刚好满足控件尺寸
                .cacheInMemory(false) //
                .cacheOnDisk(false) //
                .build();//

        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage("file://" + path, imageView, options);

    }

    @Override
    public void clearMemoryCache() {

    }
}
