package com.idolmedia.yzy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.HashMapEntity;
import com.idolmedia.yzy.utlis.PutUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.luck.picture.lib.config.PictureConfig.LUBAN_COMPRESS_MODE;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/29 19:58
 * 描述：
 */

public class UploadAuthenActivity extends BaseActivity {
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
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_id)
    EditText editId;
    @BindView(R.id.txt_go)
    TextView txtGo;
    @BindView(R.id.image_z)
    ImageView imageZ;
    @BindView(R.id.image_onclick_z)
    ImageView imageOnclickZ;
    @BindView(R.id.image_f)
    ImageView imageF;
    @BindView(R.id.image_onclick_f)
    ImageView imageOnclickF;
    List<String> list = new ArrayList<>();
    boolean isCheck_Just=true;
    String Just="",Back="";
    int type;
    HashMap hashMap=new HashMap();
    HashMapEntity hashMapEntity;
    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication_id;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        type=  getIntent().getIntExtra("type", 0);
        hashMapEntity=(HashMapEntity)getIntent().getSerializableExtra("hashMap");
        if(type==0){
            tvTitle.setText("身份证认证");
            editName.setHint("请输真实姓名");
            editId.setHint("请输入18位身份证号码");
        }else if (type==1){
            tvTitle.setText("营业执照认证");
            editName.setHint("请输公司名称或公司全称");
            editId.setHint("请输入企业或公司登记号");
        }
        if(hashMapEntity!=null){
            hashMap=hashMapEntity.getHashMap();
            for(Object key:hashMap.keySet()){
                Log.e("key",hashMap.get(key).toString());
                //builder.addFormDataPart(key,map.get(key));
            }
            editName.setText(hashMap.get("name").toString());
            editId.setText(hashMap.get("id_number").toString());
            Just=  hashMap.get("positiveImage").toString();
            Back= hashMap.get("reverseSideImage").toString();

            Glide.with(this).load(Just).apply(new RequestOptions().centerCrop().error(R.mipmap.default_bg)).into(imageZ);
            Glide.with(this).load(Back).apply(new RequestOptions().centerCrop().error(R.mipmap.default_bg)).into(imageF);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_close, R.id.txt_go})
    public void onViewClicked(View view) {
        HashMapEntity hashMapEntity = new HashMapEntity();
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.image_close:
                hashMapEntity.setHashMap(hashMap);
                intent.putExtra("hashMap", hashMapEntity);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            case R.id.txt_go:
                if(TextUtils.isEmpty(editName.getText().toString().trim())||TextUtils.isEmpty(editId.getText().toString().trim())||TextUtils.isEmpty(Just)||TextUtils.isEmpty(Back)){
                    ToastUitl.show("请填写认证信息",1000);
                    return;
                }
                hashMap.put("positiveImage",Just);
                hashMap.put("reverseSideImage",Back);
                hashMap.put("name",editName.getText().toString().trim());
                hashMap.put("id_number", editId.getText().toString().trim());
                hashMapEntity.setHashMap(hashMap);
                intent.putExtra("hashMap", hashMapEntity);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }
    }

    @OnClick({R.id.image_onclick_z, R.id.image_onclick_f})
    public void onViewClicked1(View view) {
        switch (view.getId()) {
            case R.id.image_onclick_z:
                isCheck_Just=true;
                onSelectImage();
                break;
            case R.id.image_onclick_f:
                isCheck_Just=false;
                onSelectImage();
                break;
        }
    }


    private void onSelectImage() {
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.THIRD_GEAR、Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮 true or false
                .isZoomAnim(false)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/YZY")// 自定义拍照保存路径,可不填
                .enableCrop(false)// 是否裁剪 true or false
                .compress(false)// 是否压缩 true or false
                .compressMode(LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                .glideOverride(200, 200)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .isGif(false)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(false)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                //  .selectionMedia(t)// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .cropCompressQuality(90)// 裁剪压缩质量 默认90 int
                .compressMaxKB(1024)//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效 int
                .compressWH(Luban.CUSTOM_GEAR, Luban.CUSTOM_GEAR) // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效  int
                .rotateEnabled(false) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    list.clear();
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
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
                        list.add(path);
                        Log.i("图片真实路径::", new File(path).toString());
                        if(isCheck_Just){
                            Just=list.get(0);
                             Glide.with(this).load(list.get(0)).apply(new RequestOptions().centerCrop().error(R.mipmap.ic_launcher)).into(imageZ);
                         imageOnclickZ.setImageResource(R.mipmap.icon_right_red_yzy);

                        }else {
                            Back=list.get(0);
                            Glide.with(this).load(list.get(0)).apply(new RequestOptions().centerCrop().error(R.mipmap.ic_launcher)).into(imageF);
                            imageOnclickF.setImageResource(R.mipmap.icon_right_red_yzy);
                        }
                    }
                    // Glide.with(this).load(list.get(0)).apply(new RequestOptions().centerCrop().error(R.mipmap.ic_launcher)).into(iamgeHead);

                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的

                    break;

            }
        }
    }
}
