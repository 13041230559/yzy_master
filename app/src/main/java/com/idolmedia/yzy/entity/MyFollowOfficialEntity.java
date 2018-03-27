package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/15 13:50
 * 描述： 关注官方认证
 */

public class MyFollowOfficialEntity {

    /**
     * msg : 查询成功
     * totalRow : 2
     * totalPage : 1
     * datas : [{"fans_count":"8","userloveuser_id":32,"attestation_type":2,"nick_name":"娱官方","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/bbbf474f356e4dbaa61d301fdbdba53a.jpg","attention_count":"0","by_virtualuser_id":4314,"isAttend":"true"},{"fans_count":"0","userloveuser_id":28,"attestation_type":2,"nick_name":"自制周边","head_img":"http://otkny7iog.bkt.clouddn.com/headImg/3287526150594571ae5ad8cecc2e21e1.jpg","attention_count":"0","by_virtualuser_id":4315,"isAttend":"true"}]
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
         * fans_count : 8
         * userloveuser_id : 32
         * attestation_type : 2
         * nick_name : 娱官方
         * head_img : http://otkny7iog.bkt.clouddn.com/headImg/bbbf474f356e4dbaa61d301fdbdba53a.jpg
         * attention_count : 0
         * by_virtualuser_id : 4314
         * isAttend : true
         */

        private int fans_count;
        private int userloveuser_id;
        private int attestation_type;
        private String nick_name;
        private String head_img;
        private int attention_count;
        private int by_virtualuser_id;
        private boolean isAttend;

        public int getFans_count() {
            return fans_count;
        }

        public void setFans_count(int fans_count) {
            this.fans_count = fans_count;
        }

        public int getUserloveuser_id() {
            return userloveuser_id;
        }

        public void setUserloveuser_id(int userloveuser_id) {
            this.userloveuser_id = userloveuser_id;
        }

        public int getAttestation_type() {
            return attestation_type;
        }

        public void setAttestation_type(int attestation_type) {
            this.attestation_type = attestation_type;
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

        public int getAttention_count() {
            return attention_count;
        }

        public void setAttention_count(int attention_count) {
            this.attention_count = attention_count;
        }

        public int getBy_virtualuser_id() {
            return by_virtualuser_id;
        }

        public void setBy_virtualuser_id(int by_virtualuser_id) {
            this.by_virtualuser_id = by_virtualuser_id;
        }

        public boolean getIsAttend() {
            return isAttend;
        }

        public void setIsAttend(boolean isAttend) {
            this.isAttend = isAttend;
        }
    }
}
