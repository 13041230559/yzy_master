package com.idolmedia.yzy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.ui.adapter.GridImageAdapter;
import com.idolmedia.yzy.ui.mvp.contract.ReleaseContract;
import com.idolmedia.yzy.ui.mvp.model.ReleaseModel;
import com.idolmedia.yzy.ui.mvp.presenter.ReleasePresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.DebugUtil;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.luck.picture.lib.config.PictureConfig.LUBAN_COMPRESS_MODE;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2018/3/7 21:12
 * 描述：
 */

public class CommodityEvaluationActivity extends BaseActivity<ReleasePresenter, ReleaseModel> implements ReleaseContract.View {
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
    @BindView(R.id.iamge_shop)
    ImageView iamgeShop;
    @BindView(R.id.txt_shop_name)
    TextView txtShopName;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.recyclerView)
    RecyclerView iamgeRecycler;
    @BindView(R.id.iamge_very_good)
    ImageView iamgeVeryGood;
    @BindView(R.id.txt_very_good)
    TextView txtVeryGood;
    @BindView(R.id.iamge_good)
    ImageView iamgeGood;
    @BindView(R.id.txt_good)
    TextView txtGood;
    @BindView(R.id.iamge_no_good)
    ImageView iamgeNoGood;
    @BindView(R.id.txt_no_good)
    TextView txtNoGood;
    GridImageAdapter adapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<String> updateList = new ArrayList<>();
    int maxSelectNum = 9;
    String orderNum, commodityIamge, commodityName,shopcommon_id;
    boolean veryGood, Good, noGood;

    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity_evaluation;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText("订单评价");
        orderNum = getIntent().getStringExtra("order_no");
        commodityIamge = getIntent().getStringExtra("iamge");
        commodityName = getIntent().getStringExtra("name");
        shopcommon_id= getIntent().getStringExtra("shopcommon_id");
        rightTxt.setText("提交");
        rightTxt.setVisibility(View.VISIBLE);
        rightImg.setVisibility(View.GONE);
        iamgeRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setSelectMax(maxSelectNum);
        iamgeRecycler.setAdapter(adapter);
        veryGood = true;
        iamgeVeryGood.setImageResource(R.mipmap.img_views_love_selected_yzy);
        txtVeryGood.setTextColor(getResources().getColor(R.color.fc4f4f));
        Glide.with(this).load(commodityIamge).into(iamgeShop);
        txtShopName.setText(commodityName);
        RxView.clicks(rightTxt)
                .throttleFirst(5, TimeUnit.SECONDS)   //五秒钟之内只取一个点击事件，防抖操作
                .subscribe(aVoid -> {
                    if(!TextUtils.isEmpty(editContent.getText().toString())){
                        HashMap<String,Object> hashMap=new HashMap();
                        hashMap.put("FKEY", PutUtils.FKEY());
                        hashMap.put("content",editContent.getText().toString().trim());
                        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                        hashMap.put("order_no",orderNum);
                        hashMap.put("shopcommon_id",shopcommon_id);
                        hashMap.put("comment_from",1);
                        if(veryGood){
                            hashMap.put("satisfaction",1);
                        }else if(Good){
                            hashMap.put("satisfaction",2);
                        }else if(noGood){
                            hashMap.put("satisfaction",3);
                        }
                        mPresenter.ReleaseComment_p(RetrofitHttp.onUpload(hashMap,updateList));
                    }else {
                        ToastUitl.show("请提出你的宝贵意见",1000);
                    }

                });
    }

    private void onReset() {
        veryGood=false;
        Good=false;
        noGood=false;
        iamgeVeryGood.setImageResource(R.mipmap.img_views_love_normal_yzy);
        txtVeryGood.setTextColor(getResources().getColor(R.color.c3));
        iamgeGood.setImageResource(R.mipmap.img_views_like_normal_yzy);
        txtGood.setTextColor(getResources().getColor(R.color.c3));
        iamgeNoGood.setImageResource(R.mipmap.img_views_cry_normal_yzy);
        txtNoGood.setTextColor(getResources().getColor(R.color.c3));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        /*orderNum = intent.getStringExtra("order_no");
        commodityIamge = intent.getStringExtra("iamge");
        commodityName = intent.getStringExtra("name");*/
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = () -> {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(maxSelectNum)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(3)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .previewImage(true)
                .compress(true)// 是否压缩
                .compressMode(LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .selectionMedia(selectList)// 是否传入已选图片
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                //.scaleEnabled()// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
        /*    case R.id.right_txt:
                if(!TextUtils.isEmpty(editContent.getText().toString())){
                    HashMap<String,Object> hashMap=new HashMap();
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("content",editContent.getText().toString().trim());
                    hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                    hashMap.put("order_no",orderNum);
                    hashMap.put("shopcommon_id",shopcommon_id);
                    hashMap.put("comment_from",1);
                    if(veryGood){
                        hashMap.put("satisfaction",1);
                    }else if(Good){
                        hashMap.put("satisfaction",2);
                    }else if(noGood){
                        hashMap.put("satisfaction",3);
                    }
                    mPresenter.ReleaseComment_p(RetrofitHttp.onUpload(hashMap,updateList));
                }else {
                    ToastUitl.show("请提出你的宝贵意见",1000);
                }
                break;*/
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
        ToastUitl.show(msg, 1000);

    }

    @Override
    public void ReleaseComment_v(BaseResult t) {
        ToastUitl.show(t.getMsg(),1000);
        if(t.getResultCode()==1){
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    updateList = new ArrayList<>();
                    selectList = PictureSelector.obtainMultipleResult(data);
                    for (int i = 0; i < selectList.size(); i++) {
                        String path = "";
                        LocalMedia media = selectList.get(i);
                        int mimeType = media.getMimeType();
                        if (media.isCut() && !media.isCompressed()) {
                            // 裁剪过
                            path = media.getCutPath();
                            Log.i("裁剪地址::", media.getCutPath());
                            Log.i("原图地址::", media.getPath());
                        } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                            // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                            path = media.getCompressPath();
                            Log.i("压缩地址::", media.getCompressPath());
                        } else {
                            // 原图
                            path = media.getPath();
                        }
                        updateList.add(path);
                        Log.i("图片真实路径::", new File(path).toString());
                    }
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    DebugUtil.i("TAG", "onActivityResult:" + selectList.size());
                    break;
            }
        }
    }

    @OnClick({R.id.iamge_very_good, R.id.iamge_good, R.id.iamge_no_good})
    public void onViewClicked1(View view) {
        switch (view.getId()) {
            case R.id.iamge_very_good:
                onReset();
                veryGood = true;
                iamgeVeryGood.setImageResource(R.mipmap.img_views_love_selected_yzy);
                txtVeryGood.setTextColor(getResources().getColor(R.color.fc4f4f));
                break;
            case R.id.iamge_good:
                onReset();
                Good=true;
                iamgeGood.setImageResource(R.mipmap.img_views_like_selected_yzy);
                txtGood.setTextColor(getResources().getColor(R.color.fc4f4f));

                break;
            case R.id.iamge_no_good:
                onReset();
                noGood=true;
                iamgeNoGood.setImageResource(R.mipmap.img_views_cry_selected_yzy);
                txtNoGood.setTextColor(getResources().getColor(R.color.fc4f4f));
                break;
        }
    }
}