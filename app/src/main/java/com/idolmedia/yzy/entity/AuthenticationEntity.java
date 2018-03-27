package com.idolmedia.yzy.entity;

import java.io.Serializable;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/22 17:41
 * 描述：
 */

public class AuthenticationEntity  implements Serializable {

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private  String key;
    private String value;

    public AuthenticationEntity(String key,String value){
        this.key=key;
        this.value=value;
    }
   /* private String virtualuser_id;
    private int attestation_type;
    private String nickname;
    private String phone;
    private String name;
    private String id_number;

    public String getVirtualuser_id() {
        return virtualuser_id;
    }

    public void setVirtualuser_id(String virtualuser_id) {
        this.virtualuser_id = virtualuser_id;
    }

    public int getAttestation_type() {
        return attestation_type;
    }

    public void setAttestation_type(int attestation_type) {
        this.attestation_type = attestation_type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getFKEY() {
        return FKEY;
    }

    public void setFKEY(String FKEY) {
        this.FKEY = FKEY;
    }

    public String getPositiveImage() {
        return positiveImage;
    }

    public void setPositiveImage(String positiveImage) {
        this.positiveImage = positiveImage;
    }

    public String getReverseSideImage() {
        return reverseSideImage;
    }

    public void setReverseSideImage(String reverseSideImage) {
        this.reverseSideImage = reverseSideImage;
    }

    private String FKEY;
    private String positiveImage;
    private String reverseSideImage;*/
}
