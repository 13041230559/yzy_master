package com.idolmedia.yzy.view.popwindow;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idolmedia.yzy.R;


/**
 * Created by HMY on 2016/9/10.
 */
public class PopWindowHelper {

    public static void setTitleAndMessage(TextView titleTv, int titleColor, int titleTextSize, int messageColor, int messageTextSize, CharSequence title, CharSequence message) {
        titleTv.setMinHeight(titleTextSize * 3);

        if (!TextUtils.isEmpty(title) && TextUtils.isEmpty(message)) {
            titleTv.setTextColor(titleColor);
            titleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
            titleTv.getPaint().setFakeBoldText(true);
            titleTv.setText(title);
        } else if (TextUtils.isEmpty(title) && !TextUtils.isEmpty(message)) {
            titleTv.setTextColor(messageColor);
            titleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, messageTextSize);
            titleTv.setText(message);
        } else if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(message)) {
            SpannableString titleSs = new SpannableString(title + "\n" + message);
            titleSs.setSpan(new ForegroundColorSpan(titleColor), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleSs.setSpan(new AbsoluteSizeSpan(titleTextSize), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleSs.setSpan(new StyleSpan(Typeface.BOLD), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            titleSs.setSpan(new ForegroundColorSpan(messageColor), title.length(), titleSs.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleSs.setSpan(new AbsoluteSizeSpan(messageTextSize), title.length(), titleSs.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleTv.setText(titleSs);
            titleTv.setLineSpacing(0.0f, 1.2f);
        } else {
            titleTv.setVisibility(View.GONE);
        }
    }

    public static void refreshBackground(ViewGroup container, boolean isShowCircleBackground) {
        if (container.getChildAt(0).getVisibility() == View.VISIBLE) {

            if (container.getChildCount() >= 3) {
                for (int i = 1; i < container.getChildCount(); i++) {
                    View child = container.getChildAt(i);
                    if (child instanceof PopItemView) {
                        if (isShowCircleBackground) {
                            if (i == container.getChildCount() - 1) {
                                child.setBackgroundResource(R.drawable.tan_chuang_yi);
                            } else {
                                child.setBackgroundResource(R.drawable.tan_chuang_si);
                            }
                        } else {
                            child.setBackgroundResource(R.drawable.tan_chuang_si);
                        }
                    }
                }
            }
        } else {
            if (container.getChildCount() == 3) {

                container.removeViewAt(1);

                container.getChildAt(1).setBackgroundResource(R.drawable.tan_chuang_er);
            } else if (container.getChildCount() > 3) {

                container.removeViewAt(1);

                for (int i = 1; i < container.getChildCount(); i++) {
                    View child = container.getChildAt(i);
                    if (child instanceof PopItemView) {
                        if (isShowCircleBackground) {
                            if (i == 1) {
                                child.setBackgroundResource(R.drawable.tan_chuang_wu);
                            } else if (i == container.getChildCount() - 1) {
                                child.setBackgroundResource(R.drawable.tan_chuang_yi);
                            } else {
                                child.setBackgroundResource(R.drawable.tan_chuang_si);
                            }
                        } else {
                            child.setBackgroundResource(R.drawable.tan_chuang_si);
                        }
                    }
                }
            }
        }
    }

    private static boolean isLineView(View view) {
        String tag = (String) view.getTag();
        if (PopLineView.TAG_LINE_VIEW.equals(tag)) {
            return true;
        }
        return false;
    }
}
