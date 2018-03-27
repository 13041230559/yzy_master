package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/15 18:18
 * 描述：
 */

public class ClassEntity implements Parcelable {

    /**
     * msg : 查询成功
     * datas : [{"name":"官方周边","dictionaries_id":"8f1f10135fca417f81f50ff300dd63f8","sub_dicts":[{"name":"小卡","dictionaries_id":"65ec6be997434231a527bf07579b4f21"},{"name":"笔记本","dictionaries_id":"771e2ba185b741169e706ce86ee12541"}]},{"name":"艺人","dictionaries_id":"c70522bc85614912814409e2e9f5ccb5","sub_dicts":[{"name":"EXO","dictionaries_id":"52ab919abbed455483c2281e6fa617b8"},{"name":"鹿晗","dictionaries_id":"5a61507e35f944c9949da79a9af99588"}]}]
     * resultCode : 01
     */

    private String msg;
    private int resultCode;
    private List<DatasBean> datas;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * name : 官方周边
         * dictionaries_id : 8f1f10135fca417f81f50ff300dd63f8
         * sub_dicts : [{"name":"小卡","dictionaries_id":"65ec6be997434231a527bf07579b4f21"},{"name":"笔记本","dictionaries_id":"771e2ba185b741169e706ce86ee12541"}]
         */

        private String name;
        private String dictionaries_id;
        private List<SubDictsBean> sub_dicts;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDictionaries_id() {
            return dictionaries_id;
        }

        public void setDictionaries_id(String dictionaries_id) {
            this.dictionaries_id = dictionaries_id;
        }

        public List<SubDictsBean> getSub_dicts() {
            return sub_dicts;
        }

        public void setSub_dicts(List<SubDictsBean> sub_dicts) {
            this.sub_dicts = sub_dicts;
        }

        public static class SubDictsBean {
            /**
             * name : 小卡
             * dictionaries_id : 65ec6be997434231a527bf07579b4f21
             */

            private String name;
            private String dictionaries_id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDictionaries_id() {
                return dictionaries_id;
            }

            public void setDictionaries_id(String dictionaries_id) {
                this.dictionaries_id = dictionaries_id;
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeInt(this.resultCode);
        dest.writeList(this.datas);
    }

    public ClassEntity() {
    }

    protected ClassEntity(Parcel in) {
        this.msg = in.readString();
        this.resultCode = in.readInt();
        this.datas = new ArrayList<DatasBean>();
        in.readList(this.datas, DatasBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ClassEntity> CREATOR = new Parcelable.Creator<ClassEntity>() {
        @Override
        public ClassEntity createFromParcel(Parcel source) {
            return new ClassEntity(source);
        }

        @Override
        public ClassEntity[] newArray(int size) {
            return new ClassEntity[size];
        }
    };
}
