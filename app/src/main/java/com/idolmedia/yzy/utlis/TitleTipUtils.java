package com.idolmedia.yzy.utlis;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

import com.idolmedia.yzy.R;

/**
 * 项目名称：com.idolmedia.yzy.utlis
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/6 11:10
 * 描述：
 */

public class TitleTipUtils {

    /**
     * 用在显示标题的时候在标题的开头加上标签
     *
     * @param context        上下文
     * @param textView       需要显示的view
     * @param tip            提示文字 标签
     * @param result         标题
     * @param tipTextSize    标签的大小 (一般这个标签的大小要小于title的大小)
     * @param resutlTextSize 标题的文字大小
     * @param radius         圆角半径
     */
    public static SpannableStringBuilder titleTipUtils(Context context, String tip, String result, float tipTextSize, int resutlTextSize, int radius,int color) {

        SpannableStringBuilder builder = new SpannableStringBuilder(tip + " "+ result);
        //构造文字背景圆角
        RadiusBackgroundSpan span = new RadiusBackgroundSpan(context.getResources().getColor(color)
                , context.getResources().getColor(R.color.white), radius, (int) spToPixels(context, resutlTextSize));
        builder.setSpan(span, 0, tip.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //构造文字大小
        AbsoluteSizeSpan spanSize = new AbsoluteSizeSpan((int) spToPixels(context, tipTextSize));
        builder.setSpan(spanSize, 0, tip.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //构造文字大小
        AbsoluteSizeSpan spanSizeLast = new AbsoluteSizeSpan((int) spToPixels(context, resutlTextSize));
        builder.setSpan(spanSizeLast, tip.length(), builder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        SpannableStringBuilder sb = new SpannableStringBuilder();
        sb.append(builder);
        //sb.append(result);
          return  sb;
    }

    /**
     * SP 转 Pixels
     *
     * @param context 上下文
     * @param sp      sp 字体大小
     * @return pixels
     */
    public static float spToPixels(Context context, float sp) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return sp * scaledDensity;
    }
}
