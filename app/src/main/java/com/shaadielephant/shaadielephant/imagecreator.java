package com.shaadielephant.shaadielephant;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

public class imagecreator {
    Context context;
    public imagecreator(Context c)
    {
        this.context=c;
    }
    public void imagecreatorvendrodetail(String strimageurl,ImageView imgview)
    {

        DisplayImageOptions displayImageOptions= new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(displayImageOptions)
                .build();
        ImageLoader.getInstance().init(configuration);
        String url = "http://javatechig.com/wp-content/uploads/2014/05/UniversalImageLoader-620x405.png";
        ImageLoader.getInstance().displayImage(strimageurl,imgview);
    }
    public void imagecreatorblog(String strimageurl,ImageView imgview)
    {

        DisplayImageOptions displayImageOptions= new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(displayImageOptions)
                .build();
        ImageLoader.getInstance().init(configuration);
        String url = "http://javatechig.com/wp-content/uploads/2014/05/UniversalImageLoader-620x405.png";
        ImageLoader.getInstance().displayImage(strimageurl,imgview);
    }

    // for circular image
    public void imagecreatordesignerspeak(String strimageurl,ImageView imgview)
    {

        DisplayImageOptions displayImageOptions= new DisplayImageOptions.Builder()
                .displayer(new CircleBitmapDisplayer(20))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(displayImageOptions)
                .build();
        ImageLoader.getInstance().init(configuration);
        String url = "http://javatechig.com/wp-content/uploads/2014/05/UniversalImageLoader-620x405.png";
        ImageLoader.getInstance().displayImage(strimageurl,imgview);
    }
    public void imagecreatorvendor(String strimageurl,ImageView imgview)
    {

        DisplayImageOptions displayImageOptions= new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(displayImageOptions)
                .build();
        ImageLoader.getInstance().init(configuration);
        String url = "http://javatechig.com/wp-content/uploads/2014/05/UniversalImageLoader-620x405.png";
        ImageLoader.getInstance().displayImage(strimageurl,imgview);
    }
    public void imagecreatorvenderlist(String strimageurl,ImageView imgview)
    {

        DisplayImageOptions displayImageOptions= new DisplayImageOptions.Builder()

                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(displayImageOptions)
                .build();
        ImageLoader.getInstance().init(configuration);
        String url = "http://javatechig.com/wp-content/uploads/2014/05/UniversalImageLoader-620x405.png";
        ImageLoader.getInstance().displayImage(strimageurl,imgview);
    }

    //for fragment_mypage
    public void imagecreatormyPage(String strimageurl,ImageView imgview)
    {

        DisplayImageOptions displayImageOptions= new DisplayImageOptions.Builder()
                .build();
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(displayImageOptions)
                .build();
        ImageLoader.getInstance().init(configuration);
        String url = "http://javatechig.com/wp-content/uploads/2014/05/UniversalImageLoader-620x405.png";
        ImageLoader.getInstance().displayImage(strimageurl,imgview);
    }
}
