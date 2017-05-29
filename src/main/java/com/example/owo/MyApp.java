package com.example.owo;

import android.app.Application;
import android.graphics.Bitmap;

import com.example.owo.utils.Common;
import com.example.owo.utils.MyImageLoader;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.File;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.toolsfinal.StorageUtils;

/**
 * @author XQF
 * @created 2017/5/28
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();





        ///////////////////////////图片相关/////////////////////////
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(),
                Common.IMG_CACHE_PATH);

        //设置主题
        //ThemeConfig theme = ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(getApplicationContext().getResources().getColor(R.color.themeColor))
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        CoreConfig coreConfig = new CoreConfig.Builder(this, new MyImageLoader(), theme)
                .setFunctionConfig(functionConfig)
                .setTakePhotoFolder(cacheDir)
                //.setPauseOnScrollListener(new UILPauseOnScrollListener(false, true))
                .build();
        GalleryFinal.init(coreConfig);

        //universal-image-loader
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder() //
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)//图片大小刚好满足控件尺寸
                .showImageForEmptyUri(R.drawable.add) //
                .showImageOnFail(R.drawable.add) //
                .cacheInMemory(true) //
                .cacheOnDisk(true) //
                .build();//
        ImageLoaderConfiguration config = new ImageLoaderConfiguration//
                .Builder(getApplicationContext())//
                .defaultDisplayImageOptions(defaultOptions)//
                .diskCache(new UnlimitedDiskCache(cacheDir))//自定义缓存位置
                // default为使用HASHCODE对UIL进行加密命名， 还可以用MD5(new Md5FileNameGenerator())加密
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024)//
                .diskCacheFileCount(100)// 缓存一百张图片
                .writeDebugLogs()//
                .build();//
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config);
    }


}
