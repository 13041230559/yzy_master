package com.mumu.common.base;

/**
 * Created by MuMu on 2016/12/18/0018.
 */

import android.content.Context;

import com.mumu.common.baserx.RxManager;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<T, E> {
    public E mModel;
    public  WeakReference<Context> mContext;
    public T mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void onStart() {
      /*  WeakReference<Context> abcWeakRef = new WeakReference<Context>(mContext);
        abcWeakRef.get()*/
    }

    public void onDestroy() {
        mRxManage.clear();
    }

    public void setContext(Context context){
       mContext= new WeakReference<>(context);

    }
    public  Context getContext(){

        return mContext.get();
    }
}
