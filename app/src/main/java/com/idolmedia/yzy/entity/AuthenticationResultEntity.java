package com.idolmedia.yzy.entity;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/12 12:44
 * 描述：
 */

public class AuthenticationResultEntity {

    /**
     * msg : 成功
     * datas : {"id_number":"123456789123456789","create_time":"2018-03-09 12:12:32","attestation_type":1,"phone":"13362391540","attestation_status":1,"nickname":"小小","name":"小小","attestation_id":26,"description":"99","virtualuser_id":4312}
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
         * id_number : 123456789123456789
         * create_time : 2018-03-09 12:12:32
         * attestation_type : 1
         * phone : 13362391540
         * attestation_status : 1
         * nickname : 小小
         * name : 小小
         * attestation_id : 26
         * description : 99
         * virtualuser_id : 4312
         */

        private String id_number;
        private String create_time;
        private int attestation_type;
        private String phone;
        private int attestation_status;
        private String nickname;
        private String name;
        private int attestation_id;
        private String description;
        private int virtualuser_id;

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getAttestation_type() {
            return attestation_type;
        }

        public void setAttestation_type(int attestation_type) {
            this.attestation_type = attestation_type;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getAttestation_status() {
            return attestation_status;
        }

        public void setAttestation_status(int attestation_status) {
            this.attestation_status = attestation_status;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAttestation_id() {
            return attestation_id;
        }

        public void setAttestation_id(int attestation_id) {
            this.attestation_id = attestation_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getVirtualuser_id() {
            return virtualuser_id;
        }

        public void setVirtualuser_id(int virtualuser_id) {
            this.virtualuser_id = virtualuser_id;
        }
    }
}
