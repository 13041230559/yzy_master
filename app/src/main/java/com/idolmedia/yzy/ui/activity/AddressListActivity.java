package com.idolmedia.yzy.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.AddressEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.ui.adapter.AddressAdapter;
import com.idolmedia.yzy.ui.mvp.contract.AddressContract;
import com.idolmedia.yzy.ui.mvp.model.AddressModel;
import com.idolmedia.yzy.ui.mvp.presenter.AddressPresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/11 10:20
 * 描述：
 */

public class AddressListActivity extends BaseActivity<AddressPresenter,AddressModel> implements AddressContract.View {
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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txt_go)
    TextView txtGo;
    AddressAdapter addressAdapter;
    AddressEntity addressEntity;
    List<AddressEntity> list;
    boolean select;
    private final   int UPDATEADDRESS=10000;
    @Override
    public int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText("管理地址");
        select=  getIntent().getBooleanExtra("select",false);
        mPresenter.AddressList_p(HttpHashMap());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addressAdapter=new AddressAdapter();
        recyclerView.setAdapter(addressAdapter);
        addressAdapter.setEmptyView(R.layout.empty_view,recyclerView);
        addressAdapter.setOnItemClickListener((adapter, view, position) ->
                {
                    if(select){
                        Intent  intent=new Intent();
                        intent.putExtra("address",addressAdapter.getData().get(position));
                        setResult(Activity.RESULT_OK,intent);
                        finish();
                    }else {
                        //ToastUitl.show("修改收货地址",1000);
                    }

                }

        );
        addressAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                    switch (view.getId()){
                        case R.id.iamge_update :
                            Bundle bundle  =new Bundle();
                            bundle.putInt("isNew",1);
                            bundle.putParcelable("address",(Parcelable)adapter.getData().get(position));
                            startActivityForResult(NewAddressActivity.class,bundle,UPDATEADDRESS);
                            break;
                        case R.id.iamge_del :
                            AlertDialog dialog;
                            dialog = new AlertDialog.Builder(this).create();
                            dialog.setMessage("确认删除该地址吗?");
                            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", (dialog1, which) -> {
                                HashMap<String,Object> hashMap=new HashMap<>();
                                hashMap.put("address_id",addressAdapter.getData().get(position).getAddress_id());
                                hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                                hashMap.put("FKEY",PutUtils.FKEY());
                                mPresenter.DelAddress_p(hashMap);

                            });
                            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", (dialog12, which) -> {
                                return;
                            });
                            dialog.show();


                            break;
                    }
                }
        );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @OnClick({R.id.image_close, R.id.txt_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.txt_go:
                startActivityForResult(NewAddressActivity.class,UPDATEADDRESS);
                break;
        }
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    private HashMap HttpHashMap(){
        HashMap<String ,Object> hashMap=new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY",PutUtils.FKEY());
        return hashMap;
    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.show(msg,1000);
    }
    @Override
    public void AddressList_v(AddressEntity t) {
        if(t.getResultCode()==1){
            addressAdapter.setNewData(t.getAddressData());
        }
    }

    @Override
    public void DelAddress_v(BaseResult t) {
        ToastUitl.show(t.getMsg(),1000);
        if(t.getResultCode()==1){
            mPresenter.AddressList_p(HttpHashMap());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case UPDATEADDRESS:
                    mPresenter.AddressList_p(HttpHashMap());
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
