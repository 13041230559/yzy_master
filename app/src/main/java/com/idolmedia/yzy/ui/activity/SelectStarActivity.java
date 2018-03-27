package com.idolmedia.yzy.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.MainActivity;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.IDoEntity;
import com.idolmedia.yzy.ui.adapter.SelectIdoAdpater;
import com.idolmedia.yzy.ui.mvp.contract.SelectIdoContract;
import com.idolmedia.yzy.ui.mvp.model.SelectIdoModel;
import com.idolmedia.yzy.ui.mvp.presenter.SelectIdoPresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.security.Md5Security;
import com.mumu.common.utils.FormatUtil;
import com.mumu.common.utils.ToastUitl;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/1.
 */

public class SelectStarActivity extends BaseActivity<SelectIdoPresenter,SelectIdoModel> implements SelectIdoContract.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.right_txt)
    TextView rightTxt;
    @BindView(R.id.right_img)
    ImageView rightImg;
    @BindView(R.id.liner_top)
    LinearLayout linerTop;
    @BindView(R.id.image_close)
    ImageView imageClose;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.login_btn)
    Button loginBtn;
    List<IDoEntity> list;
    @BindView(R.id.iamge_rotate)
    ImageView iamgeRotate;
    @BindView(R.id.liner_rotate)
    LinearLayout linerRotate;
    private  int refresh=0;
    SelectIdoAdpater adpater;
    int onClickPosition;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_selectstar;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        HashMap<String,Object> hashMap =new HashMap<>();
        hashMap.put("refresh_count",refresh);
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.SelectStars_p(hashMap);
        imageClose.setVisibility(View.INVISIBLE);
        linerTop.setBackgroundResource(R.color.fc4f4f);
        tvTitle.setText("选择你的爱豆");
        rightTxt.setVisibility(View.VISIBLE);
        rightImg.setVisibility(View.GONE);
        rightTxt.setText("更多");
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adpater = new SelectIdoAdpater();
        recyclerView.setAdapter(adpater);
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_back_right_black_yzy);
        //设置drawable的位置,宽高
        drawable.setBounds(0, 0, 60, 60);
        rightTxt.setCompoundDrawables(null, null, drawable, null);

        adpater.setOnItemChildClickListener((adapter, view, position) -> {
            onClickPosition=position;
            HashMap<String,Object> hashMap1 =new HashMap<>();
            hashMap1.put("virtualuser_id", UserLoginUtil.IsUserId());
            hashMap1.put("idol_id",adpater.getData().get(position).getIdol_id());
            hashMap1.put("attention_type",adpater.getData().get(position).isIsAtted()?1:0);
            hashMap1.put("FKEY", PutUtils.FKEY());
            mPresenter.FollowIdo_m_p(hashMap1);
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.right_txt, R.id.right_img, R.id.liner_rotate, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_txt:
                startActivity(SelectStarListActivity.class);
                break;
            case R.id.right_img:
                break;
            case R.id.liner_rotate:
                HashMap<String,Object> hashMap =new HashMap<>();
                hashMap.put("refresh_count",refresh++);
                hashMap.put("virtualuser_id", 3651);
                hashMap.put("FKEY", Md5Security.getMD5("api"+ FormatUtil.dateToStrLong1(new java.util.Date())+"yizhiyu"));
                mPresenter.SelectStars_p(hashMap);
              /*  //创建旋转动画
                Animation animation = new RotateAnimation(0, 359);
                animation.setDuration(500);
                animation.setRepeatCount(8);//动画的重复次数
                animation.setFillAfter(true);//设置为true，动画转化结束后被应用
                iamgeRotate.startAnimation(animation);//开始动画*/
                break;
            case R.id.login_btn:
                if(UserLoginUtil.loginEntity()!=null){
                    UserLoginUtil.loginEntity().setFirstCome(false);
                }
                finish();
                break;
        }
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.showLong(msg);
    }

    @Override
    public void SelectStars_v(IDoEntity t) {
        if(t.getResultCode()==1){
            adpater.setNewData(t.getDatas());
        }


    }


    @Override
    public void FollowIdo_m_v(BaseResult t) {
        ToastUitl.show(t.getMsg(),1000);
        if(t.getResultCode()==1){
            adpater.getData().get(onClickPosition).setIsAtted(!adpater.getData().get(onClickPosition).isIsAtted());
            adpater.notifyItemChanged(onClickPosition);
        }
    }
}
