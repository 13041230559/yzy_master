package com.idolmedia.yzy.utlis;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.view.RoundImageView;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2017/7/29.
 */

//轮播图加载
public class GlideImageLoaderRadius extends com.idolmedia.yzy.utlis.ImageLoader {

    @Override
    public void displayImage(Context context, Object path, RoundImageView imageView) {
         Glide.with(context).load(path).apply(new RequestOptions().error(R.mipmap.default_bg)).into(imageView);
    }
}