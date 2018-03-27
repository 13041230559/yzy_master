package com.idolmedia.yzy.utlis;

import android.content.Context;
import android.content.Intent;

import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.activity.LoginActivity;

/**
 * Created by Administrator on 2017/8/13.
 */

public class UserLoginUtil {

    public   static Boolean Islogin(Context context){
        UserEntity userBean= (UserEntity) SharedUtil.getObjectFromJsonString("UserEntity",UserEntity.class);
        if(userBean==null){
            Intent  intent =new Intent(context,LoginActivity.class);
            context.startActivity(intent);
            return false;
        }
        return true;
    }

public   static UserEntity loginEntity(){
    UserEntity userBean= (UserEntity) SharedUtil.getObjectFromJsonString("UserEntity",UserEntity.class);
        return userBean;
    }

    public   static String Sessionkey(){
        UserEntity userBean= (UserEntity) SharedUtil.getObjectFromJsonString("UserEntity",UserEntity.class);
        if(userBean!=null){
            String sessionkey=userBean.getDatas().getSession_key();
            return  sessionkey;
        }
        return  "";
    }

    public   static String HeadImge(){
        UserEntity userBean= (UserEntity) SharedUtil.getObjectFromJsonString("UserEntity",UserEntity.class);
        if(userBean!=null){
            String sessionkey=userBean.getDatas().getHead_img();
            return  sessionkey;
        }
        return  "";
    }
    public   static String IsUserId() {
        UserEntity userBean = (UserEntity) SharedUtil.getObjectFromJsonString("UserEntity", UserEntity.class);
        if (userBean != null) {
            return userBean.getDatas().getVirtualuser_id();
        }
        return "";
    }

    public   static boolean IsSelectIdo() {
        UserEntity userBean = (UserEntity) SharedUtil.getObjectFromJsonString("UserEntity", UserEntity.class);
        if (userBean != null) {
            return userBean.isFirstCome();
        }
        return true;
    }

    public   static String IsUserName() {
        UserEntity userBean = (UserEntity) SharedUtil.getObjectFromJsonString("UserEntity", UserEntity.class);
        if (userBean != null) {
            return userBean.getDatas().getNick_name();
        }
        return "";
    }
}
