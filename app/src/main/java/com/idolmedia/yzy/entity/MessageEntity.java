package com.idolmedia.yzy.entity;

import java.util.List;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/23 18:23
 * 描述： 消息
 */

public class MessageEntity {


    /**
     * msg : 查询成功
     * datas : [{"not_read_count":1,"create_time":"2018-01-31 11:04:35","message_type_id":1,"title":"系统","pic_url":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","content":"一直娱项目2.0上线"},{"not_read_count":1,"create_time":"2018-01-31 11:23:46","message_type_id":2,"title":"物流","pic_url":"https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100","content":"物流消息"}]
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
         * not_read_count : 1
         * create_time : 2018-01-31 11:04:35
         * message_type_id : 1
         * title : 系统
         * pic_url : https://q.qlogo.cn/qqapp/1106213827/39D0958FDDFB5A24CDF21CC77F59309F/100
         * content : 一直娱项目2.0上线
         */

        private int not_read_count;
        private String create_time;
        private int message_type_id;
        private String title;
        private String pic_url;
        private String content;

        public int getNot_read_count() {
            return not_read_count;
        }

        public void setNot_read_count(int not_read_count) {
            this.not_read_count = not_read_count;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getMessage_type_id() {
            return message_type_id;
        }

        public void setMessage_type_id(int message_type_id) {
            this.message_type_id = message_type_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
