package com.idolmedia.yzy.utlis;

import android.content.Context;
import android.widget.ImageView;

import com.idolmedia.yzy.view.RoundImageView;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Created by Administrator on 2017/11/6.
 */

public abstract class  ImageLoader implements ImageLoaderInterface<RoundImageView> {


    @Override
    public RoundImageView createImageView(Context context) {
        RoundImageView imageView = new RoundImageView(context);
        return imageView;
    }
}
