package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/1 12:29
 * 描述：我关注的爱豆
 */

public class MyFollowIdoEntity  {

    /**
     * msg : 查询成功
     * totalRow : 7
     * totalPage : 1
     * datas : [{"fans_count":"0","idol_id":3035,"ido_name":"艾玛·沃特森","userloveidol_id":130,"ido_head_img":"http://img3.nanrenwo.net/uploads/150413/7302-150413134KI20_ae7b18_200_200.jpg"},{"fans_count":"0","idol_id":2832,"ido_name":"阿米尔·侯赛因·汗","userloveidol_id":131,"ido_head_img":"http://img6.nanrenwo.net/uploads/150914/7302-150914142234454_8de179_200_200.jpg"},{"fans_count":"0","idol_id":2831,"ido_name":"艾伦","userloveidol_id":132,"ido_head_img":"http://img1.nanrenwo.net/uploads/171012/8478-1G012100FG28_700064_200_200.jpg"},{"fans_count":"0","idol_id":2830,"ido_name":"安宰贤","userloveidol_id":133,"ido_head_img":"http://img1.nanrenwo.net/uploads/150126/4908-1501261H14M25-lp_e7b172_200_200.jpg"},{"fans_count":"0","idol_id":2823,"ido_name":"艾菲","userloveidol_id":145,"ido_head_img":"http://img1.nanrenwo.net/uploads/150505/7302-150505144049455_b55c43_200_200.jpg"},{"fans_count":"0","idol_id":2824,"ido_name":"安心亚","userloveidol_id":194,"ido_head_img":"http://img7.nanrenwo.net/uploads/150505/7302-150505142F2459_f2c9b1_200_200.jpg"},{"fans_count":"0","idol_id":2825,"ido_name":"安吉丽娜·朱莉","userloveidol_id":195,"ido_head_img":"http://img3.nanrenwo.net/uploads/151105/7302-151105140F2413_e45b74_200_200.jpg"}]
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
         * fans_count : 0
         * idol_id : 3035
         * ido_name : 艾玛·沃特森
         * userloveidol_id : 130
         * ido_head_img : http://img3.nanrenwo.net/uploads/150413/7302-150413134KI20_ae7b18_200_200.jpg
         */

        private int fans_count;
        private int idol_id;
        private String ido_name;
        private int userloveidol_id;
        private String ido_head_img;
        private boolean isAtted;

        public boolean isAtted() {
            return isAtted;
        }

        public void setAtted(boolean atted) {
            isAtted = atted;
        }


        public int getFans_count() {
            return fans_count;
        }

        public void setFans_count(int fans_count) {
            this.fans_count = fans_count;
        }

        public int getIdol_id() {
            return idol_id;
        }

        public void setIdol_id(int idol_id) {
            this.idol_id = idol_id;
        }

        public String getIdo_name() {
            return ido_name;
        }

        public void setIdo_name(String ido_name) {
            this.ido_name = ido_name;
        }

        public int getUserloveidol_id() {
            return userloveidol_id;
        }

        public void setUserloveidol_id(int userloveidol_id) {
            this.userloveidol_id = userloveidol_id;
        }

        public String getIdo_head_img() {
            return ido_head_img;
        }

        public void setIdo_head_img(String ido_head_img) {
            this.ido_head_img = ido_head_img;
        }
    }
}
