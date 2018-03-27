package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.MessageClassEntity;
import com.idolmedia.yzy.entity.MessageEntity;
import com.idolmedia.yzy.ui.adapter.MessageAdapter;
import com.idolmedia.yzy.ui.mvp.contract.MessageContract;
import com.idolmedia.yzy.ui.mvp.model.MessageModel;
import com.idolmedia.yzy.ui.mvp.presenter.MessagePresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/14 11:01
 * 描述：
 */

public class MessageListActivity extends BaseActivity<MessagePresenter, MessageModel> implements MessageContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
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
    MessageAdapter msgAdapter;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;

    @Override
    public int getLayoutId() {
        return R.layout.message_list_activity;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);

    }

    @Override
    public void initView() {

        /**
         *  app:srlEnablePureScrollMode="true"
         app:srlEnableLoadmore="true"
         */
        smartLayout.setEnableLoadmore(true);
        smartLayout.setEnablePureScrollMode(true);
        tvTitle.setText("消息");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.MessageList_p(hashMap);
        msgAdapter = new MessageAdapter();
        recyclerView.setAdapter(msgAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        msgAdapter.setEmptyView(R.layout.empty_view,recyclerView);
        msgAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("name", String.valueOf(((MessageEntity.DatasBean) adapter.getData().get(position)).getTitle()));
            bundle.putString("message_type_id", String.valueOf(((MessageEntity.DatasBean) adapter.getData().get(position)).getMessage_type_id()));
            startActivity(MessageDetalisActivity.class, bundle);
            //  LogUtil.logi("",((MessageEntity.DatasBean)messageAdapter.getData().get(position)).getMessage_type_id());
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.image_close)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.show(msg, 1000);
    }

    @Override
    public void MessageList_v(MessageEntity t) {
        if (t.getResultCode() == 1) {
            msgAdapter.setNewData(t.getDatas());
        }

    }

    @Override
    public void QueryMessageList_v(MessageClassEntity t) {

    }

    @Override
    public void ToRead_v(BaseResult t, int position, int have_read) {

    }

}
