package com.idolmedia.yzy.utlis;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.idolmedia.yzy.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2017/7/29.
 */

//轮播图加载
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //  Glide.with(context).load(path).into(imageView);
             Glide.with(context).load(path)
                     .apply(new RequestOptions().error(R.mipmap.default_bg)).transition(new DrawableTransitionOptions().crossFade(2000)).into(imageView);
                     //.error(R.mipmap.img_found_yzy).placeholder(R.mipmap.yi
    }
}