package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/18 12:27
 * 描述：
 */

public class CommentEntity {

    /**
     * msg : 查询成功
     * totalRow : 5
     * totalPage : 1
     * datas : [{"comment_count":0,"create_time":"2018-02-05 18:18:51","isPrised":"false","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/0245a81d8bb14ef4a87124e930340857.jpg","nick_name":"我是一直娱！","look_count":0,"commentPics":["http://otkny7iog.bkt.clouddn.com/commonPic/24958039976b4df1ac7d0161a2752f7c.jpg"],"comment_id":46,"prise_count":0,"content":"温暖人心的温暖"},{"comment_count":0,"create_time":"2018-02-05 17:37:30","isPrised":"false","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/0245a81d8bb14ef4a87124e930340857.jpg","nick_name":"我是一直娱！","look_count":0,"commentPics":[],"comment_id":45,"prise_count":0,"content":"来吧，互相伤害吧"},{"comment_count":0,"create_time":"2017-12-13 14:23:09","isPrised":"false","head_img":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","nick_name":"11111111111","look_count":0,"commentPics":[],"comment_id":44,"prise_count":0,"content":"坎坎坷坷扩扩扩可二二二"},{"comment_count":1,"create_time":"2017-12-13 14:23:09","isPrised":"false","head_img":"","nick_name":"CHEN","look_count":0,"commentPics":["http://otkny7iog.bkt.clouddn.com/feedback/18bddeb810704355a097d499db6d3cee.jpg"],"comment_id":14,"prise_count":0,"content":"坎坎坷坷扩扩扩可二二二"},{"comment_count":2,"create_time":"2017-12-13 14:23:09","isPrised":"false","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/0245a81d8bb14ef4a87124e930340857.jpg","nick_name":"我是一直娱！","look_count":0,"commentPics":["http://otkny7iog.bkt.clouddn.com/feedback/c0314ad28d234a1aac71e0ad43661343.jpg"],"comment_id":13,"prise_count":0,"content":"看； 看啊看到酸辣粉克拉"}]
     * resultCode : 01
     */

    private String msg;
    private int totalRow;
    private int totalPage;
    private int resultCode;
    private List<DatasBean> datas;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
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

    public static class DatasBean implements Parcelable {
        /**
         * comment_count : 0
         * create_time : 2018-02-05 18:18:51
         * isPrised : false
         * head_img : http://otkny7iog.bkt.clouddn.com/headImg/0245a81d8bb14ef4a87124e930340857.jpg
         * nick_name : 我是一直娱！
         * look_count : 0
         * commentPics : ["http://otkny7iog.bkt.clouddn.com/commonPic/24958039976b4df1ac7d0161a2752f7c.jpg"]
         * comment_id : 46
         * prise_count : 0
         * content : 温暖人心的温暖
         */

        private int comment_count;
        private String create_time;
        private boolean isPrised;
        private String head_img;
        private String nick_name;
        private int look_count;
        private int comment_id;
        private int prise_count;
        private String content;
        private List<String> commentPics;

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public boolean getIsPrised() {
            return isPrised;
        }

        public void setIsPrised(boolean isPrised) {
            this.isPrised = isPrised;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getLook_count() {
            return look_count;
        }

        public void setLook_count(int look_count) {
            this.look_count = look_count;
        }

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
        }

        public int getPrise_count() {
            return prise_count;
        }

        public void setPrise_count(int prise_count) {
            this.prise_count = prise_count;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getCommentPics() {
            return commentPics;
        }

        public void setCommentPics(List<String> commentPics) {
            this.commentPics = commentPics;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.comment_count);
            dest.writeString(this.create_time);
            dest.writeByte(this.isPrised ? (byte) 1 : (byte) 0);
            dest.writeString(this.head_img);
            dest.writeString(this.nick_name);
            dest.writeInt(this.look_count);
            dest.writeInt(this.comment_id);
            dest.writeInt(this.prise_count);
            dest.writeString(this.content);
            dest.writeStringList(this.commentPics);
        }

        public DatasBean() {
        }

        protected DatasBean(Parcel in) {
            this.comment_count = in.readInt();
            this.create_time = in.readString();
            this.isPrised = in.readByte() != 0;
            this.head_img = in.readString();
            this.nick_name = in.readString();
            this.look_count = in.readInt();
            this.comment_id = in.readInt();
            this.prise_count = in.readInt();
            this.content = in.readString();
            this.commentPics = in.createStringArrayList();
        }

        public static final Parcelable.Creator<DatasBean> CREATOR = new Parcelable.Creator<DatasBean>() {
            @Override
            public DatasBean createFromParcel(Parcel source) {
                return new DatasBean(source);
            }

            @Override
            public DatasBean[] newArray(int size) {
                return new DatasBean[size];
            }
        };
    }
}
