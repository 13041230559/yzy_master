package com.idolmedia.yzy.ui.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.UserEntity;
import com.idolmedia.yzy.ui.activity.AddressListActivity;
import com.idolmedia.yzy.ui.activity.AuthenticationresultsActivity;
import com.idolmedia.yzy.ui.activity.LoginActivity;
import com.idolmedia.yzy.ui.activity.LuckyRecordActivity;
import com.idolmedia.yzy.ui.activity.MyBeanActivity;
import com.idolmedia.yzy.ui.activity.MyFansActivity;
import com.idolmedia.yzy.ui.activity.MyIdoActivity;
import com.idolmedia.yzy.ui.activity.MyOrderListActivity;
import com.idolmedia.yzy.ui.activity.MyReleaseActivity;
import com.idolmedia.yzy.ui.activity.OldOrderActivity;
import com.idolmedia.yzy.ui.activity.SelectidActivity;
import com.idolmedia.yzy.ui.activity.SettingActivity;
import com.idolmedia.yzy.ui.activity.UpdateHeadActivity;
import com.idolmedia.yzy.ui.activity.UpdateNameActivity;
import com.idolmedia.yzy.ui.mvp.contract.MeContract;
import com.idolmedia.yzy.ui.mvp.model.MeModel;
import com.idolmedia.yzy.ui.mvp.presenter.MePresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.SharedUtil;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.widget.PullZoomView;
import com.mumu.common.base.BaseFragment;
import com.mumu.common.utils.ToastUitl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.luck.picture.lib.config.PictureConfig.LUBAN_COMPRESS_MODE;

/**
 * Created by Administrator on 2017/10/31.
 *      我的
 *
 */

public class MeFragment extends BaseFragment<MePresenter, MeModel> implements MeContract.View {

    Unbinder unbinder;
    @BindView(R.id.ss_zoom)
    ImageView ssZoom;
    @BindView(R.id.iamge_head)
    ImageView iamgeHead;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_userid)
    TextView txtUserid;
    @BindView(R.id.tv_fans_number)
    TextView tvFansNumber;
    @BindView(R.id.tv_my_fans)
    TextView tvMyFans;
    @BindView(R.id.tv_ido_number)
    TextView tvIdoNumber;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.ll_action_button)
    LinearLayout llActionButton;
    @BindView(R.id.txt_certification)
    TextView txtCertification;
    @BindView(R.id.ss_header)
    RelativeLayout ssHeader;
    @BindView(R.id.liner_myorder)
    LinearLayout linerMyorder;
    @BindView(R.id.ss_content)
    NestedScrollView ssContent;
    @BindView(R.id.pzv)
    PullZoomView pzv;
    @BindView(R.id.iamge_grade)
    ImageView iamgeGrade;
    @BindView(R.id.txt_login)
    TextView txtLogin;
    @BindView(R.id.liner_login_show)
    LinearLayout linerLoginShow;
    @BindView(R.id.liner_my_fans)
    LinearLayout linerMyFans;
    @BindView(R.id.liner_my_ido)
    LinearLayout linerMyIdo;
    @BindView(R.id.liner_address)
    LinearLayout linerAddress;
    @BindView(R.id.liner_my_release)
    LinearLayout linerMyRelease;
    @BindView(R.id.liner_my_yudu)
    LinearLayout linerMyYudu;
    @BindView(R.id.liner_my_setup)
    LinearLayout linerMySetup;
    @BindView(R.id.liner_lucky)
    LinearLayout linerLucky;
    @BindView(R.id.my_beannumber)
    TextView myBeannumber;
    public static final int UPDATEMSG = 2;
    boolean autoLogin;
    @BindView(R.id.liner_old_myorder)
    LinearLayout linerOldMyorder;
    @BindView(R.id.txt_please_login)
    TextView txtPleaseLogin;
    @BindView(R.id.txt_my_follow)
    TextView txtMyFollw;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        pzv.setIsParallax(false);
        pzv.setIsZoomEnable(true);
        pzv.setSensitive(1.5f);
        pzv.setZoomTime(500);
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_edit_yzy);
        //设置drawable的位置,宽高
        drawable.setBounds(0, 0, 60, 60);
        txtName.setCompoundDrawables(null, null, drawable, null);
        Glide.with(this).load(R.mipmap.default_bg).apply(new RequestOptions().circleCrop()).into(iamgeHead);
        txtCertification.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!autoLogin) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("session_key", UserLoginUtil.Sessionkey());
            mPresenter.AutoLogin_p(PutUtils.Put(map));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.txt_certification, R.id.iamge_head, R.id.txt_login, R.id.liner_my_fans, R.id.liner_my_ido,R.id.txt_please_login,R.id.txt_my_follow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_certification:
                break;
            case R.id.iamge_head:
                Intent intent = new Intent(getActivity(), UpdateHeadActivity.class);
                startActivityForResult(intent, UPDATEMSG);
                break;
            case R.id.txt_login:
                startActivity(LoginActivity.class);
                break;
            case R.id.liner_my_fans:
                 startActivity(MyFansActivity.class);
                break;
            case R.id.liner_my_ido:
                startActivity(MyIdoActivity.class);
                break;
            case R.id.txt_please_login:
                startActivity(LoginActivity.class);
                break;
            case R.id.txt_my_follow:
                startActivity(MyIdoActivity.class);
                break;
        }
    }


    @OnClick({R.id.liner_myorder, R.id.liner_address, R.id.liner_my_release, R.id.liner_my_yudu, R.id.liner_my_setup, R.id.liner_lucky, R.id.txt_name, R.id.liner_old_myorder})
    public void onViewClicked1(View view) {
        switch (view.getId()) {
            case R.id.liner_myorder:
                startActivity(MyOrderListActivity.class);
                break;
            case R.id.liner_lucky:
                startActivity(LuckyRecordActivity.class);
                break;
            case R.id.liner_address:
                startActivity(AddressListActivity.class);

                break;
            case R.id.liner_my_release:
                startActivity(MyReleaseActivity.class);
                break;
            case R.id.liner_my_yudu:
                startActivity(MyBeanActivity.class);
                break;
            case R.id.liner_my_setup:
                startActivity(SettingActivity.class);
                break;

            case R.id.txt_name:
                startActivityForResult(UpdateNameActivity.class, UPDATEMSG);
                break;
            case R.id.liner_old_myorder:
                startActivity(OldOrderActivity.class);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<String> list = new ArrayList<>();
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
                    }
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
                case UPDATEMSG:
                    boolean isUpdate = data.getBooleanExtra("isUpdate", false);
                    if (isUpdate) {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("session_key", UserLoginUtil.Sessionkey());
                        mPresenter.AutoLogin_p(PutUtils.Put(map));
                    }
                    break;


            }
        }
    }

    private void onSelectImage() {
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(getActivity())
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.THIRD_GEAR、Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮 true or false
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .compressMode(LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                .glideOverride(200, 200)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .isGif(false)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
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
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.showLong(msg);
        autoLogin = false;
    }

    @Override
    public void AutoLogin_v(UserEntity t) {
        if (t.getResultCode() == 1) {
            autoLogin = true;
            txtMyFollw.setVisibility(View.VISIBLE);
            txtCertification.setVisibility(View.VISIBLE);
            txtPleaseLogin.setVisibility(View.GONE);
            linerLoginShow.setVisibility(View.VISIBLE);
            SharedUtil.saveObjecToString("UserEntity", t);
            txtName.setText(t.getDatas().getNick_name());
            txtUserid.setText("ID:" + t.getDatas().getVirtualuser_id());
            Glide.with(getActivity()).load(t.getDatas().getHead_img()).apply(new RequestOptions().error(R.mipmap.me_bj).transform(new BlurTransformation(4, 1))).into(ssZoom);
            Glide.with(getActivity()).load(t.getDatas().getHead_img()).apply(new RequestOptions().circleCrop().error(R.mipmap.default_bg)).into(iamgeHead);

            //9	is_attestation	是否认证：0未认证，1已认证;2:已拒绝;3:审核中
            if (t.getDatas().getIs_attestation() == 0) {
                txtCertification.setText(getString(R.string.no_authenticated));
            } else if (t.getDatas().getIs_attestation() == 1) {
                txtCertification.setText(getString(R.string.authenticated));
            } else if (t.getDatas().getIs_attestation() == 2) {
                txtCertification.setText(getString(R.string.hasrefused));
            } else if (t.getDatas().getIs_attestation() == 3) {
                txtCertification.setText(getString(R.string.audit));
            }
            txtCertification.setOnClickListener(view -> {
                if (t.getDatas().getIs_attestation() == 0) {
                    startActivityForResult(SelectidActivity.class, UPDATEMSG);
                } else if (t.getDatas().getIs_attestation() == 2) {
                    startActivity(AuthenticationresultsActivity.class);
                }else if (t.getDatas().getIs_attestation() == 1){
                    ToastUitl.show("您是认证用户，如需修改请联系一直娱",2000);
                }


            });

            myBeannumber.setText(t.getDatas().getMoney_no() + "");
            tvFansNumber.setText(String.valueOf(t.getDatas().getFans_number()));
            tvIdoNumber.setText(String.valueOf(t.getDatas().getM_concern_number()));
        }

    }


}
