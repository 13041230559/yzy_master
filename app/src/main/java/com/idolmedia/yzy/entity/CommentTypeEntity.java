package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/18 12:30
 * 描述：
 */

public class CommentTypeEntity {
    private List<CommentTypeBean> comment_type;

    public List<CommentTypeBean> getComment_type() {
        return comment_type;
    }

    public void setComment_type(List<CommentTypeBean> comment_type) {
        this.comment_type = comment_type;
    }

    public static class CommentTypeBean {
        /**
         * satis_name : 全部
         * count : 34
         * satisfaction : 0
         */

        private String satis_name;
        private int count;
        private int satisfaction;

        public String getSatis_name() {
            return satis_name;
        }

        public void setSatis_name(String satis_name) {
            this.satis_name = satis_name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getSatisfaction() {
            return satisfaction;
        }

        public void setSatisfaction(int satisfaction) {
            this.satisfaction = satisfaction;
        }
    }
}
