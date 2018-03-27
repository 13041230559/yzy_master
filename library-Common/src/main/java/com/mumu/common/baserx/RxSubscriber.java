package com.mumu.common.baserx;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.mumu.common.R;
import com.mumu.common.base.BaseApplication;
import com.mumu.common.utils.LoadingDialog;
import com.mumu.common.utils.NetWorkUtil;

import org.json.JSONException;

import rx.Subscriber;

import static android.content.ContentValues.TAG;

/**
 * 订阅封装
 */

/********************使用例子********************/

public abstract class RxSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog=true;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog= true;
    }
    public void hideDialog() {
        this.showDialog= true;
    }

    public RxSubscriber(Context context, String msg,boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog=showDialog;
    }
    public RxSubscriber(Context context) {
        this(context, BaseApplication.getAppContext().getString(R.string.loading),true);
    }
    public RxSubscriber(Context context,boolean showDialog) {
        this(context, BaseApplication.getAppContext().getString(R.string.loading),showDialog);
    }

    @Override
    public void onCompleted() {
        if (showDialog)
            LoadingDialog.cancelDialogForLoading();
    }
    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                LoadingDialog.showDialogForLoading((Activity) mContext,msg,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onNext(T t) {
        try {
            if (showDialog)
                LoadingDialog.cancelDialogForLoading();
            _onNext(t);
        } catch (JSONException e) {
            Log.e(TAG, "onNext: "+e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (showDialog)
            LoadingDialog.cancelDialogForLoading();
        e.printStackTrace();
        //网络
        if (!NetWorkUtil.isNetConnected(mContext)) {
            _onError(mContext.getString(R.string.no_net));
        }
        //服务器
        else if (e instanceof ServerException) {
            _onError(e.getMessage());
        }
        //其它
        else if (e instanceof JSONException){
            _onError(mContext.getString(R.string.json_error));
        }else if(e instanceof NumberFormatException){
            _onError(mContext.getString(R.string.json_error));
        }else {
            _onError(mContext.getString(R.string.net_error));
        }
        Log.e(TAG, "onNext: "+e.toString());
    }

    protected abstract void _onNext(T t) throws JSONException;

    protected abstract void _onError(String message);

}
