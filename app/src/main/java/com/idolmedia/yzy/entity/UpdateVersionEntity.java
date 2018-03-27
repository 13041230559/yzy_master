package com.idolmedia.yzy.entity;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/21 17:00
 * 描述：
 */

public class UpdateVersionEntity {


    /**
     * msg : 查询成功
     * datas : {"create_time":"Mar 21, 2018 4:33:38 PM","versionSize":"15*1024*1024l","mCheckUrl":"1","content":"【升级】全新2.0模块更新\\r\\n【更新】新增社区\\r\\n【更新】新增定金、尾款模式\\r\\n【更新】新增认证功能\\r\\n【更新】新增爱豆分类功能\\r\\n【优化】物流查询优化\\r\\n【优化】增加用户体验\\r\\n","versionCode":7,"isIgnorable":"true","maxTimes":"0","hasUpdate":"false","updateUrl":"http://imtt.dd.qq.com/16891/77AB5CB24A3C7B9FDECDE9EB2480908F.apk?fsname=com.idolmedia.yzy_1.1.5_5.apk&csr=1bbd","versionNo":"v2.0.0","isAutoInstall":"true","isSilent":"false","isForce":"false","versions_id":1,"md5":"1"}
     * resultCode : 01
     */

    private String msg;
    private DatasBean datas;
    private int resultCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
         * create_time : Mar 21, 2018 4:33:38 PM
         * versionSize : 15*1024*1024l
         * mCheckUrl : 1
         * content : 【升级】全新2.0模块更新\r\n【更新】新增社区\r\n【更新】新增定金、尾款模式\r\n【更新】新增认证功能\r\n【更新】新增爱豆分类功能\r\n【优化】物流查询优化\r\n【优化】增加用户体验\r\n
         * versionCode : 7
         * isIgnorable : true
         * maxTimes : 0
         * hasUpdate : false
         * updateUrl : http://imtt.dd.qq.com/16891/77AB5CB24A3C7B9FDECDE9EB2480908F.apk?fsname=com.idolmedia.yzy_1.1.5_5.apk&csr=1bbd
         * versionNo : v2.0.0
         * isAutoInstall : true
         * isSilent : false
         * isForce : false
         * versions_id : 1
         * md5 : 1
         */

        private String create_time;
        private String versionSize;
        private String mCheckUrl;
        private String content;
        private int versionCode;
        private String isIgnorable;
        private String maxTimes;
        private String hasUpdate;
        private String updateUrl;
        private String versionNo;
        private String isAutoInstall;
        private String isSilent;
        private String isForce;
        private int versions_id;
        private String md5;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getVersionSize() {
            return versionSize;
        }

        public void setVersionSize(String versionSize) {
            this.versionSize = versionSize;
        }

        public String getMCheckUrl() {
            return mCheckUrl;
        }

        public void setMCheckUrl(String mCheckUrl) {
            this.mCheckUrl = mCheckUrl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getIsIgnorable() {
            return isIgnorable;
        }

        public void setIsIgnorable(String isIgnorable) {
            this.isIgnorable = isIgnorable;
        }

        public String getMaxTimes() {
            return maxTimes;
        }

        public void setMaxTimes(String maxTimes) {
            this.maxTimes = maxTimes;
        }

        public String getHasUpdate() {
            return hasUpdate;
        }

        public void setHasUpdate(String hasUpdate) {
            this.hasUpdate = hasUpdate;
        }

        public String getUpdateUrl() {
            return updateUrl;
        }

        public void setUpdateUrl(String updateUrl) {
            this.updateUrl = updateUrl;
        }

        public String getVersionNo() {
            return versionNo;
        }

        public void setVersionNo(String versionNo) {
            this.versionNo = versionNo;
        }

        public String getIsAutoInstall() {
            return isAutoInstall;
        }

        public void setIsAutoInstall(String isAutoInstall) {
            this.isAutoInstall = isAutoInstall;
        }

        public String getIsSilent() {
            return isSilent;
        }

        public void setIsSilent(String isSilent) {
            this.isSilent = isSilent;
        }

        public String getIsForce() {
            return isForce;
        }

        public void setIsForce(String isForce) {
            this.isForce = isForce;
        }

        public int getVersions_id() {
            return versions_id;
        }

        public void setVersions_id(int versions_id) {
            this.versions_id = versions_id;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }
    }
}


