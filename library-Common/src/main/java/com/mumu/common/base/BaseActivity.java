package com.mumu.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.mumu.common.BuildConfig;
import com.mumu.common.R;
import com.mumu.common.baserx.RxManager;
import com.mumu.common.utils.ToastUitl;
import com.mumu.common.utils.TypeUtil;
import com.mumu.common.widget.ProgressWheel;
import com.mumu.common.widget.StatusBarCompat;
import com.umeng.analytics.MobclickAgent;
import com.zhy.autolayout.AutoLayoutActivity;
import butterknife.ButterKnife;


/**
 * Created by MuMu on 2016/12/8/0008.
 */



public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AutoLayoutActivity {
    public T mPresenter;
    public E mModel;
    public Context mContext;
    public RxManager mRxManager;
    private TextView tv_hintContent;
    private ProgressWheel progress_view;
    protected View mFooterView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // StatusBarCompat.setStatusBarColor(this,R.color.colorAccent);
        setContentView(getLayoutId());
        // doBeforeSetcontentView();
        // Pus.getInstance(this).onAppStart();
        SetStatusBarColor();
        ButterKnife.bind(this);
        mRxManager = new RxManager();
        mContext = this;
        mPresenter = TypeUtil.getType(this, 0);
        mModel = TypeUtil.getType(this, 1);
        if (mPresenter != null) {
            mPresenter.setContext(this);
        }
        this.initPresenter();
        this.initView();
        //initFooterView();
    }

    private void initFooterView() {
        mFooterView = View.inflate(mContext, R.layout.item_view_load_more, null);
        tv_hintContent = (TextView) mFooterView.findViewById(R.id.tv_hintContent);
        progress_view = (ProgressWheel) mFooterView.findViewById(R.id.progress_view);
    }

    public void setLoadHint(boolean hasMore) {
        if (!hasMore) {
            progress_view.setVisibility(View.GONE);
            tv_hintContent.setVisibility(View.VISIBLE);
        }
    }

    public void setLoadHint(boolean hasMore, String hint) {
        if (!hasMore) {
            progress_view.setVisibility(View.GONE);
            tv_hintContent.setText(hint);
            tv_hintContent.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 默认着色状态栏
        SetStatusBarColor();

    }

    /*********************
     * 子类实现
     *****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //初始化view
    public abstract void initView();


    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }

    /**
     * 带图片的toast
     *
     * @param text
     * @param res
     */
    public void showToastWithImg(String text, int res) {
        ToastUitl.showToastWithImg(text, res);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //debug版本不统计crash
        if (!BuildConfig.DEBUG) {
            //友盟统计
            MobclickAgent.onResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //debug版本不统计crash
        if (!BuildConfig.DEBUG) {
            //友盟统计
            MobclickAgent.onPause(this);
        }
    }


    @Override
    protected void onDestroy() {

        if (mPresenter != null)
            mPresenter.onDestroy();
        mRxManager.clear();
        super.onDestroy();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
