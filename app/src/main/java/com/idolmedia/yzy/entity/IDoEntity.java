package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/15 10:46
 * 描述：  爱豆明星
 */

public class IDoEntity  {

    /**
     * msg : 请求成功
     * totalRow : 1367
     * toFirstPage : false
     * datas : [{"isAtted":false,"idol_id":1802,"ido_name":"何炅","ido_head_img":"http://img7.nanrenwo.net/uploads/140925/4908-1409251AZ92J_498791_200_200.jpg"},{"isAtted":false,"idol_id":1803,"ido_name":"何珈好","ido_head_img":"http://img5.nanrenwo.net/uploads/150716/7302-150G6142124L0_5605a6_200_200.jpg"},{"isAtted":false,"idol_id":1804,"ido_name":"韩东君","ido_head_img":"http://img1.nanrenwo.net/uploads/160428/8431-16042Q33559608_c1f9a1_200_200.jpg"},{"isAtted":false,"idol_id":1805,"ido_name":"何润东","ido_head_img":"http://img7.nanrenwo.net/uploads/141124/4908-14112416264J30_18ed54_200_200.jpg"},{"isAtted":false,"idol_id":1806,"ido_name":"霍政谚","ido_head_img":"http://img6.nanrenwo.net/uploads/160309/7302-160309164212B6_823e8f_200_200.jpg"},{"isAtted":false,"idol_id":1807,"ido_name":"胡歌","ido_head_img":"http://img1.nanrenwo.net/uploads/150821/7302-150R11354031I_961a65_200_200.jpg"},{"isAtted":false,"idol_id":1808,"ido_name":"黄有龙","ido_head_img":"http://img2.nanrenwo.net/uploads/160630/8431-160630092S2224_a7209d_200_200.jpg"},{"isAtted":false,"idol_id":1809,"ido_name":"黄灿盛","ido_head_img":"http://img1.nanrenwo.net/uploads/141217/4908-14121GF2061X-lp_1bfcea_200_200.jpg"},{"isAtted":false,"idol_id":1810,"ido_name":"胡静","ido_head_img":"http://img3.nanrenwo.net/uploads/170405/8467-1F4051I01Y27_fc3641_200_200.jpg"}]
     * totalPage : 152
     * resultCode : 01
     */

    private String msg;
    private int totalRow;
    private boolean toFirstPage;
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

    public boolean isToFirstPage() {
        return toFirstPage;
    }

    public void setToFirstPage(boolean toFirstPage) {
        this.toFirstPage = toFirstPage;
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
         * isAtted : false
         * idol_id : 1802
         * ido_name : 何炅
         * ido_head_img : http://img7.nanrenwo.net/uploads/140925/4908-1409251AZ92J_498791_200_200.jpg
         */
                private boolean isAtted;
        private int idol_id;
        private String ido_name;
        private String ido_head_img;
        private String sortLetters;//显示数据拼音的首字母
        public String getSortLetters() {
            return sortLetters;
        }

        public void setSortLetters(String sortLetters) {
            this.sortLetters = sortLetters;
        }
        public boolean isIsAtted() {
            return isAtted;
        }
        public void setIsAtted(boolean isAtted) {
            this.isAtted = isAtted;
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

        public String getIdo_head_img() {
            return ido_head_img;
        }

        public void setIdo_head_img(String ido_head_img) {
            this.ido_head_img = ido_head_img;
        }
    }
}
