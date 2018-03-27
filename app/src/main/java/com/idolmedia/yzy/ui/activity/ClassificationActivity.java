package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.ClassEntity;
import com.idolmedia.yzy.ui.adapter.ClassTagdapter;
import com.idolmedia.yzy.ui.mvp.contract.ClassificationContract;
import com.idolmedia.yzy.ui.mvp.model.ClassificationModel;
import com.idolmedia.yzy.ui.mvp.presenter.ClassificationPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/13.
 */

public class ClassificationActivity extends BaseActivity<ClassificationPresenter, ClassificationModel> implements ClassificationContract.View, ClassTagdapter.OnItemOnclickListener {
    @BindView(R.id.image_close)
    ImageView imageClose;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.right_txt)
    TextView rightTxt;
    @BindView(R.id.right_img)
    ImageView rightImg;
    @BindView(R.id.liner_top)
    LinearLayout linerTop;
    ClassTagdapter tagdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_classification;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.all_class));
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.Classification_p(hashMap);
        rightTxt.setText(getString(R.string.all_shop));
        rightTxt.setVisibility(View.VISIBLE);
        rightImg.setVisibility(View.GONE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
    public void Classification_v(ClassEntity t) {
        if (t.getResultCode() == 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            tagdapter = new ClassTagdapter();
            recyclerView.setAdapter(tagdapter);
            tagdapter.setNewData(t.getDatas());
            tagdapter.setitemOnclickListener(this);
        } else {

        }

    }

    @Override
    public void OnItemOnclick(String id, String title) {
        Log.e("OnItemOnclick", id);
        Bundle bundle = new Bundle();
        bundle.putString("dictionaries_id", id);
        bundle.putString("title", title);
        startActivity(ClassSearchResultActivity.class, bundle);
    }

    @OnClick({R.id.image_close, R.id.right_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.right_txt:
                Bundle bundle = new Bundle();
                bundle.putString("dictionaries_id","");
                bundle.putString("title", "全部商品");
                startActivity(ClassSearchResultActivity.class, bundle);
                break;
        }
    }
}
