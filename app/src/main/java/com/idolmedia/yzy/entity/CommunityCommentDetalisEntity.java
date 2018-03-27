package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/18 12:27
 * 描述：社区评论详情
 */

public class CommunityCommentDetalisEntity {


    /**
     * msg : 查询成功
     * totalRow : 2
     * totalPage : 1
     * datas : [{"reply_comment_id":24,"create_time":"2018-02-07 11:58:10","nick_name":"xiaoxiao","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/d406325b666540f1a6fc94fc82f8257b.jpg","reply_virtualuser_id":4297,"reply_content":"93852"},{"reply_comment_id":23,"create_time":"2018-02-07 11:58:04","nick_name":"xiaoxiao","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/d406325b666540f1a6fc94fc82f8257b.jpg","reply_virtualuser_id":4297,"reply_content":"12233"}]
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

    public static class DatasBean {
        /**
         * reply_comment_id : 24
         * create_time : 2018-02-07 11:58:10
         * nick_name : xiaoxiao
         * head_img : http://otkny7iog.bkt.clouddn.com/headImg/d406325b666540f1a6fc94fc82f8257b.jpg
         * reply_virtualuser_id : 4297
         * reply_content : 93852
         */

        private int reply_comment_id;
        private String create_time;
        private String nick_name;
        private String head_img;
        private int reply_virtualuser_id;
        private String reply_content;

        public int getReply_comment_id() {
            return reply_comment_id;
        }

        public void setReply_comment_id(int reply_comment_id) {
            this.reply_comment_id = reply_comment_id;
        }
        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public int getReply_virtualuser_id() {
            return reply_virtualuser_id;
        }

        public void setReply_virtualuser_id(int reply_virtualuser_id) {
            this.reply_virtualuser_id = reply_virtualuser_id;
        }

        public String getReply_content() {
            return reply_content;
        }

        public void setReply_content(String reply_content) {
            this.reply_content = reply_content;
        }
    }
}
