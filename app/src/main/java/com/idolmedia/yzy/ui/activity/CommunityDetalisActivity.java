package com.idolmedia.yzy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.CommunityCommentDetalisEntity;
import com.idolmedia.yzy.entity.PhotoInfo;
import com.idolmedia.yzy.ui.adapter.CommunityDetalisAdapter;
import com.idolmedia.yzy.ui.mvp.contract.CommunityDetalisContract;
import com.idolmedia.yzy.ui.mvp.model.CommunityDetalisModel;
import com.idolmedia.yzy.ui.mvp.presenter.CommunityDetalisPresenter;
import com.idolmedia.yzy.utlis.MultiImageView;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.KeyBordUtil;
import com.mumu.common.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.socialize.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/2 17:42
 * 描述：
 */

public class CommunityDetalisActivity extends BaseActivity<CommunityDetalisPresenter, CommunityDetalisModel> implements CommunityDetalisContract.View, OnRefreshListener, OnLoadmoreListener {
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
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.txt_send)
    TextView txtSend;
    String comment_id;
    CommentEntity.DatasBean bean;
    CommunityDetalisAdapter adapter;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    @BindView(R.id.txt_comment_lick)
    TextView txtCommentLick;
    @BindView(R.id.txt_good_lick)
    TextView txtGoodLick;
    int pageNo = 1;
    @BindView(R.id.iamge_add)
    ImageView iamgeAdd;
    @BindView(R.id.liner_release_commit)
    LinearLayout linerReleaseCommit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_community_detalis;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText("社区详情");
        comment_id = getIntent().getStringExtra("comment_id");
        bean = getIntent().getParcelableExtra("bean");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", "1");
        hashMap.put("pageSize", "10");
        hashMap.put("comment_id", comment_id);
        mPresenter.CommunityDetalisList_p(hashMap);
        if (bean.getIsPrised()) {
            Drawable drawable = getResources().getDrawable(R.mipmap.icon_zan_red_yzy);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            txtGoodLick.setCompoundDrawables(drawable, null, null, null);
        } else {
            Drawable drawable = getResources().getDrawable(R.mipmap.icon_zan_grey_yzy);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            txtGoodLick.setCompoundDrawables(drawable, null, null, null);
        }
        txtGoodLick.setText(String.valueOf(bean.getPrise_count()));

        adapter = new CommunityDetalisAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        View view = LayoutInflater.from(this).inflate(R.layout.community_comment_item, (ViewGroup) findViewById(android.R.id.content), false);
        adapter.addHeaderView(view);

        ImageView headImage = view.findViewById(R.id.iamge_portrait);
        TextView headName = view.findViewById(R.id.txt_nick_name);
        TextView releaseTime = view.findViewById(R.id.txt_release_time);
        TextView txtContent = view.findViewById(R.id.txt_content);
        ViewStub viewStub = view.findViewById(R.id.viewStub);
        TextView goodLick = view.findViewById(R.id.txt_good_lick);
        RelativeLayout comment_bottom = view.findViewById(R.id.comment_bottom);
        comment_bottom.setVisibility(View.GONE);
        goodLick.setVisibility(View.GONE);
        Glide.with(this).load(bean.getHead_img()).apply(new RequestOptions().circleCrop().error(R.mipmap.default_bg)).into(headImage);
        headName.setText(bean.getNick_name());
        releaseTime.setText(bean.getCreate_time());
        viewStub.setLayoutResource(R.layout.viewstub_imgbody);
        View subView = viewStub.inflate();
        MultiImageView multiImageView = (MultiImageView) subView.findViewById(R.id.multiImagView);
        if (TextUtils.isEmpty(bean.getContent())) {
            txtContent.setVisibility(View.GONE);
        } else {
            txtContent.setText(bean.getContent());
        }
        List<PhotoInfo> photos = new ArrayList<>();
        List<LocalMedia> selectList = new ArrayList<>();
        for (int i = 0; i < bean.getCommentPics().size(); i++) {
            PhotoInfo photoInfo = new PhotoInfo();
            photoInfo.url = bean.getCommentPics().get(i);
            photos.add(photoInfo);
            LocalMedia localMedia = new LocalMedia();
            localMedia.setPath(bean.getCommentPics().get(i));
            selectList.add(localMedia);
        }
        //  final List<PhotoInfo> photos = datasBean.getCommentPics();
        if (photos != null && photos.size() > 0) {
            multiImageView.setVisibility(View.VISIBLE);
            multiImageView.setList(photos);
            multiImageView.setOnItemClickListener((view1, position) -> {

                PictureSelector.create(CommunityDetalisActivity.this).externalPicturePreview(position, "/idolmedia", selectList);
            });
        } else {
            multiImageView.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        android.util.Log.e("GO TO  ZHE LI","comment_id");
        comment_id = intent.getStringExtra("comment_id");
        bean = intent.getParcelableExtra("bean");
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
    public void CommunityDetalisList_v(CommunityCommentDetalisEntity t) {
        if (t.getResultCode() == 1) {
            if (pageNo == 1) {
                adapter.setNewData(t.getDatas());
                recyclerView.smoothScrollToPosition(0);
            } else {
                adapter.addData(t.getDatas());
            }

        }

    }

    @Override
    public void ReplyComment_v(BaseResult t) {
        ToastUitl.show(t.getMsg(), 1000);
        if (t.getResultCode() == 1) {
            editContent.setText("");
            pageNo = 1;
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("FKEY", PutUtils.FKEY());
            hashMap.put("pageNo", "1");
            hashMap.put("pageSize", "10");
            hashMap.put("comment_id", comment_id);
            mPresenter.CommunityDetalisList_p(hashMap);
        }

    }

    @Override
    public void GoodClick_v(BaseResult t) {
        ToastUitl.show(t.getMsg(), 1000);
        if (t.getResultCode() == 1) {
            Drawable drawable = getResources().getDrawable(R.mipmap.icon_zan_red_yzy);
            drawable.setBounds(0, 0, 60, 60);
            txtGoodLick.setCompoundDrawables(drawable, null, null, null);
            txtGoodLick.setText(String.valueOf(bean.getPrise_count() + 1));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.txt_send)
    public void onViewClicked1() {
        if (!TextUtils.isEmpty(editContent.getText().toString().trim())) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("FKEY", PutUtils.FKEY());
            hashMap.put("reply_content", editContent.getText().toString().trim());
            hashMap.put("comment_id", comment_id);
            hashMap.put("reply_virtualuser_id", UserLoginUtil.IsUserId());
            mPresenter.ReplyComment_p(hashMap);
        } else {
            ToastUitl.show("评论内容为空", 1000);

        }


    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("comment_id", comment_id);
        mPresenter.CommunityDetalisList_p(hashMap);
        refreshlayout.finishLoadmore(2000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo = 1;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("comment_id", comment_id);
        mPresenter.CommunityDetalisList_p(hashMap);
        refreshlayout.finishRefresh(2000);
    }

    @OnClick({R.id.image_close, R.id.txt_comment_lick, R.id.txt_good_lick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.txt_comment_lick:
                linerReleaseCommit.setVisibility(View.VISIBLE);
                showSoftInputFromWindow(this,editContent);
                KeyBordUtil.showSoftKeyboard(editContent);
                break;
            case R.id.txt_good_lick:
                HashMap<String, Object> map = new HashMap<>();
                map.put("virtualuser_id", UserLoginUtil.IsUserId());
                map.put("FKEY", PutUtils.FKEY());
                map.put("comment_id", comment_id);
                map.put("prise_from", "4");
                mPresenter.GoodClick_p(map);
                break;
        }
    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}
