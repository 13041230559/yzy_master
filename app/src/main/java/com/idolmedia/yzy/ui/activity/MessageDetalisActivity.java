package com.idolmedia.yzy.ui.activity;

import android.content.Intent;
import android.net.Uri;
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
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.InformationEntity;
import com.idolmedia.yzy.entity.MessageClassEntity;
import com.idolmedia.yzy.entity.MessageEntity;
import com.idolmedia.yzy.ui.adapter.QueryMessageAdapter;
import com.idolmedia.yzy.ui.mvp.contract.MessageContract;
import com.idolmedia.yzy.ui.mvp.model.MessageModel;
import com.idolmedia.yzy.ui.mvp.presenter.MessagePresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/5 16:01
 * 描述：
 */

public class MessageDetalisActivity extends BaseActivity<MessagePresenter, MessageModel> implements MessageContract.View, OnRefreshListener, OnLoadmoreListener {
    @BindView(R.id.image_close)
    ImageView imageClose;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.right_txt)
    TextView rightTxt;
    @BindView(R.id.right_img)
    ImageView rightImg;
    @BindView(R.id.liner_top)
    LinearLayout linerTop;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    String message_type_id, name;
    QueryMessageAdapter queryMessageAdapter;
    int pageNo = 1;

    @Override
    public int getLayoutId() {
        return R.layout.message_list_activity_no;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        smartLayout.setOnRefreshListener(this);
        smartLayout.setOnLoadmoreListener(this);
        message_type_id = getIntent().getStringExtra("message_type_id");
        name = getIntent().getStringExtra("name");
        tvTitle.setText(name);
    /*    else {*/
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
            hashMap.put("pageNo", "1");
            hashMap.put("pageSize", "10");
            hashMap.put("FKEY", PutUtils.FKEY());
            hashMap.put("message_type_id", message_type_id);
            mPresenter.QueryMessageList_p(hashMap);
            queryMessageAdapter = new QueryMessageAdapter();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(queryMessageAdapter);
            queryMessageAdapter.setEmptyView(R.layout.empty_view,recyclerView);
            queryMessageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    MessageClassEntity.DatasBean datasBean=(MessageClassEntity.DatasBean) adapter.getData().get(position);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("message_virtualuser_id", ((MessageClassEntity.DatasBean) adapter.getData().get(position)).getMessage_virtualuser_id());
                    map.put("FKEY", PutUtils.FKEY());
                    map.put("virtualuser_id", UserLoginUtil.IsUserId());
                    mPresenter.ToRead_p(map,position,((MessageClassEntity.DatasBean) adapter.getData().get(position)).getHave_read());
                    if(datasBean.getClick_type()==0){
                        if(message_type_id.equals("3")){
                            Intent intent=new Intent(Intent.ACTION_VIEW);
                            String url="http://sj.qq.com/myapp/detail.htm?apkName=com.idolmedia.yzy";
                            intent.setData(Uri.parse(url));
                            startActivity(intent);

                        }
                    }else if(datasBean.getClick_type()==1){
                        if(datasBean.getShop_type().equals("support")){
                            Bundle bundle=new Bundle();
                            bundle.putString("shopcommon_id",datasBean.getShopcommon_id());
                            bundle.putString("shop_type",datasBean.getShop_type());
                            startActivity(SupportDetalisactivity.class,bundle);
                        }else if(datasBean.getShop_type().equals("information")){
                            Bundle bundle=new Bundle();
                            bundle.putString("shopcommon_id",datasBean.getShopcommon_id());
                            bundle.putString("image_url",((MessageClassEntity.DatasBean)adapter.getData().get(position)).getPic_url());
                            startActivity(InformationDetalisActivity.class,bundle);
                        }else {
                            Bundle bundle=new Bundle();
                            bundle.putString("shopcommon_id",String.valueOf(datasBean.getShopcommon_id()));
                            bundle.putString("shop_type",datasBean.getShop_type());
                            bundle.putString("ordernum",datasBean.getOrder_num());
                            startActivity(CommodityDetailsActivity.class,bundle);
                        }
                    }else if(datasBean.getClick_type()==2){
                        Bundle bundle=new Bundle();
                        bundle.putString("order_num",datasBean.getOrder_num());
                        bundle.putString("iamge",datasBean.getPic_url());
                        bundle.putString("shippercode",datasBean.getShippercode());
                        bundle.putString("express_no",datasBean.getExpress_no());
                        startActivity(LogisticsActivity.class,bundle);
                    }else if(datasBean.getClick_type()==3){

                    }
                }
            });





    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        message_type_id = intent.getStringExtra("message_type_id");
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
        ToastUitl.show(msg, 1000);
    }

    @Override
    public void MessageList_v(MessageEntity t) {

    }

    @Override
    public void QueryMessageList_v(MessageClassEntity t) {
        if (t.getResultCode() == 1) {
            if (pageNo == 1) {
                queryMessageAdapter.setNewData(t.getDatas());
            } else {
                queryMessageAdapter.addData(t.getDatas());


            }
        }

    }

    @Override
    public void ToRead_v(BaseResult t,int position ,int have_read) {
        if (t.getResultCode() == 1) {
            ((MessageClassEntity.DatasBean)queryMessageAdapter.getData().get(position)).setHave_read(1);
            queryMessageAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("message_type_id", message_type_id);
        mPresenter.QueryMessageList_p(hashMap);
        refreshlayout.finishLoadmore(1000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo = 1;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("message_type_id", message_type_id);
        mPresenter.QueryMessageList_p(hashMap);
        refreshlayout.finishRefresh(1000);
    }

    @OnClick(R.id.image_close)
    public void onViewClicked() {
        finish();
    }
}
