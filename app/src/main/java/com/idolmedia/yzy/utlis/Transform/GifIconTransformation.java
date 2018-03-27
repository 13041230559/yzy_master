package com.idolmedia.yzy.utlis.Transform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.app.YzyApplication;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2017/11/13.
 */

public class GifIconTransformation extends BitmapTransformation {
    private boolean isGif = false;
    private static Paint paint;
    private static Bitmap gifbmp;

    public GifIconTransformation(Context context, String url) {
        super(context);
        if (url.toLowerCase().endsWith(".gif")) isGif = true;
    }

    public GifIconTransformation(Context context, boolean isGif) {
        super(context);
        this.isGif = isGif;
    }

    public String getId() {
        return " com.idolmedia.yzy.utlis.Transform.GifIconTransformation";//xxxxx为对应的包名，用做独立标识
    }

    static {
        gifbmp = BitmapFactory.decodeResource(YzyApplication.getAppContext().getResources(), R.mipmap.ic_launcher);
        paint = new Paint();
        paint.setColor(Color.parseColor("#469de6"));
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        if(isGif) return addGifIcon(toTransform);
        else return addGifIcon(toTransform);
    }


    private Bitmap addGifIcon(Bitmap oldbitmap) {
        int width = oldbitmap.getWidth();
        int height = oldbitmap.getHeight();

        int gifbmpWidth = gifbmp.getWidth();
        int gifbmpHeight = gifbmp.getHeight();
        Canvas canvas = new Canvas(oldbitmap);
        canvas.drawBitmap(oldbitmap, 0, 0, null);
        canvas.drawRect(width - gifbmpWidth - 18, height - gifbmpHeight, width, height, paint);
        canvas.drawBitmap(gifbmp, width - gifbmpWidth - 9, height - gifbmpHeight, null);
        //canvas.save(Canvas.ALL_SAVE_FLAG);
        //canvas.restore();

        return oldbitmap;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }
}
