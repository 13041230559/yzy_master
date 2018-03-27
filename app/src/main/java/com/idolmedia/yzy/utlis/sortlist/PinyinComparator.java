package com.idolmedia.yzy.utlis.sortlist;

import android.util.Log;

import com.idolmedia.yzy.entity.IDoEntity;

import java.util.Comparator;

/**
 * 用来对ListView中的数据根据A-Z进行排序，前面两个if判断主要是将不是以汉字开头的数据放在后面
 */
public class PinyinComparator implements Comparator<IDoEntity.DatasBean> {
/*
    @Override
    public int compare(IDoEntity.DatasBean t1, IDoEntity.DatasBean t2) {
        if(t1==null||t2==null){return 0;}
        Log.e("t1",t1.getSortLetters()+"    name"+t1.getIdo_name());
        Log.e("t2",t2.getSortLetters()+"    name"+t2.getIdo_name());
        if(t1.getSortLetters()==null||t2.getSortLetters()==null){return 0;}
        if (t1.getSortLetters().equals("@") || t2.getSortLetters().equals("#")) {
            return -1;
        } else if (t1.getSortLetters().equals("#") || t2.getSortLetters().equals("@")) {
            return 1;
        } else {
            return t1.getSortLetters().compareTo(t2.getSortLetters());
        }
    }
*/


 public int compare(IDoEntity.DatasBean o1, IDoEntity.DatasBean o2) {
        //这里主要是用来对ListView里面的数据根据ABCDEFG...来排序

        if (o1.getSortLetters().equals("@") || o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")
                || o2.getSortLetters().equals("@")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }
}
