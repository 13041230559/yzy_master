package com.idolmedia.yzy.utlis.Transform;

import android.support.v4.view.ViewPager;

import com.idolmedia.yzy.utlis.ViewPageTransformer.DefaultTransformer;
import com.idolmedia.yzy.utlis.ViewPageTransformer.StackTransformer;
import com.idolmedia.yzy.utlis.ViewPageTransformer.ZoomOutTransformer;

/**
 * Created by user on 2018/3/26.
 */

public class Transformer {
    public static Class<? extends ViewPager.PageTransformer> ZoomOut = ZoomOutTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Stack = StackTransformer.class;

}
