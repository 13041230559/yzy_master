package com.idolmedia.yzy.entity;

import java.io.Serializable;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/27 11:36
 * 描述：
 */

public class UserEntity implements  Serializable {

    /**
     * msg : 登录成功
     * firstCome : false
     * datas : {"money_no":"0","fans_number":0,"auth_type":"","head_img":"https://wx.qlogo.cn/mmopen/vi_32/ABmeoXcBqKlzM0VG6eyNH6LcKu2gxKfficJc9ovm7vpae9ZrwIILHb3GHaJzkSu0iaJVj9qXw4GbdS4CBHlbMweA/0","nick_name":"sunny","sex":0,"session_key":"2ebd0dcbbc40455b8a57b66bb019352e","is_attestation":0,"virtualuser_id":46205,"m_concern_number":0,"account_no":"17701679983"}
     * resultCode : 01
     */

    private String msg;
    private boolean firstCome;
    private DatasBean datas;
    private int resultCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isFirstCome() {
        return firstCome;
    }

    public void setFirstCome(boolean firstCome) {
        this.firstCome = firstCome;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public static class DatasBean {
        /**
         * money_no : 0
         * fans_number : 0
         * auth_type :
         * head_img : https://wx.qlogo.cn/mmopen/vi_32/ABmeoXcBqKlzM0VG6eyNH6LcKu2gxKfficJc9ovm7vpae9ZrwIILHb3GHaJzkSu0iaJVj9qXw4GbdS4CBHlbMweA/0
         * nick_name : sunny
         * sex : 0
         * session_key : 2ebd0dcbbc40455b8a57b66bb019352e
         * is_attestation : 0
         * virtualuser_id : 46205
         * m_concern_number : 0
         * account_no : 17701679983
         */

        private String money_no;
        private int fans_number;
        private String auth_type;
        private String head_img;
        private String nick_name;
        private int sex;
        private String session_key;
        private int is_attestation;
        private String virtualuser_id;
        private int m_concern_number;
        private String account_no;

        public String getMoney_no() {
            return money_no;
        }

        public void setMoney_no(String money_no) {
            this.money_no = money_no;
        }

        public int getFans_number() {
            return fans_number;
        }

        public void setFans_number(int fans_number) {
            this.fans_number = fans_number;
        }

        public String getAuth_type() {
            return auth_type;
        }

        public void setAuth_type(String auth_type) {
            this.auth_type = auth_type;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getSession_key() {
            return session_key;
        }

        public void setSession_key(String session_key) {
            this.session_key = session_key;
        }

        public int getIs_attestation() {
            return is_attestation;
        }

        public void setIs_attestation(int is_attestation) {
            this.is_attestation = is_attestation;
        }

        public String getVirtualuser_id() {
            return virtualuser_id;
        }

        public void setVirtualuser_id(String virtualuser_id) {
            this.virtualuser_id = virtualuser_id;
        }

        public int getM_concern_number() {
            return m_concern_number;
        }

        public void setM_concern_number(int m_concern_number) {
            this.m_concern_number = m_concern_number;
        }

        public String getAccount_no() {
            return account_no;
        }

        public void setAccount_no(String account_no) {
            this.account_no = account_no;
        }
    }
}
