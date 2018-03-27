package com.idolmedia.yzy.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.api.retrofit.RetrofitHttp;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.InformationDetalisEntity;
import com.idolmedia.yzy.ui.adapter.CommunityAdapter;
import com.idolmedia.yzy.ui.mvp.contract.InformationDetailContract;
import com.idolmedia.yzy.ui.mvp.model.InformationDetailModel;
import com.idolmedia.yzy.ui.mvp.presenter.InformationDetailPresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.LogUtil;
import com.mumu.common.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.luck.picture.lib.config.PictureConfig.LUBAN_COMPRESS_MODE;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/30 15:22
 * 描述：资讯详情
 */

public class InformationDetalisActivity extends BaseActivity<InformationDetailPresenter, InformationDetailModel> implements InformationDetailContract.View, OnRefreshListener, OnLoadmoreListener,UMShareListener {
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
    String shopcommon_id;
    String image_url="";
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_source)
    TextView txtSource;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.txt_lick_count)
    TextView txtLickCount;
    @BindView(R.id.txt_good_lick)
    TextView txtGoodLick;
    CommunityAdapter communityCommentAdapter;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.txt_send)
    TextView txtSend;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    int pageNo = 1;
    @BindView(R.id.iamge_add)
    ImageView iamgeAdd;
    @BindView(R.id.nested_scrollview)
    NestedScrollView nestedScrollview;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<String> updateList = new ArrayList<>();
    public static final String SHARE_URL = "http://www.withfans.com/webProgress/webPage/zxDetail.html?id=%s&picUrl=%s";
    private  final  int RELEASE=10000;
    @Override
    public int getLayoutId() {
        return R.layout.activity_information_detalis;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText("资讯详情");
        rightImg.setVisibility(View.VISIBLE);
        rightImg.setImageResource(R.mipmap.icon_share_yzy);
        smartLayout.setOnRefreshListener(this);
        smartLayout.setOnLoadmoreListener(this);
        //   rightImg.setImageResource(R.mipmap);
        shopcommon_id = getIntent().getStringExtra("shopcommon_id");
        image_url=getIntent().getStringExtra("image_url");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("shopcommon_id", shopcommon_id);
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("shop_type", "Information");
        mPresenter.InformationDetail_p(hashMap);
        hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("shopcommon_id", shopcommon_id);
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("comment_from", "3");
        mPresenter.CommentList_p(hashMap);
        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.addJavascriptInterface(new JavaScriptInterface(this), "imagelistner");
        webView.setWebViewClient(new MyWebViewClient());
        communityCommentAdapter = new CommunityAdapter(this, true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(communityCommentAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        communityCommentAdapter.setOnItemClickListener(new CommunityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }

            @Override
            public void onItemGoodClick(int position, int lickcount, int comment_id) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("virtualuser_id", UserLoginUtil.IsUserId());
                map.put("FKEY", PutUtils.FKEY());
                map.put("comment_id", comment_id);
                map.put("prise_from", "4");
                mPresenter.GoodClick_p(map, position, lickcount);
            }
        });
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
    public void InformationDetail_v(InformationDetalisEntity t) {
        if (t.getResultCode() == 1) {
         /*   String css = "<style type=\"text/css\"> img {" +
                    "width:100%;" +//限定图片宽度填充屏幕
                    "height:auto;" +//限定图片高度自动
                    "}" +
                    "body {" +
                    "margin-right:15px;" +//限定网页中的文字右边距为15px(可根据实际需要进行行管屏幕适配操作)
                    "margin-left:15px;" +//限定网页中的文字左边距为15px(可根据实际需要进行行管屏幕适配操作)
                    "margin-top:15px;" +//限定网页中的文字上边距为15px(可根据实际需要进行行管屏幕适配操作)
                    "font-size:15px;" +//限定网页中文字的大小为40px,请务必根据各种屏幕分辨率进行适配更改
                    "word-wrap:break-word;"+//允许自动换行(汉字网页应该不需要这一属性,这个用来强制英文单词换行,类似于word/wps中的西文换行)
                    "}" +
                    "</style>";*/
            String css = "<style type=\"text/css\"> img {" +
                    "width:100%;" +//限定图片宽度填充屏幕
                    "height:auto;" +//限定图片高度自动
                    "}" +
                    "body {" +
                    "font-size:15px;" +//限定网页中文字的大小为40px,请务必根据各种屏幕分辨率进行适配更改
                    "word-wrap:break-word;" +//允许自动换行(汉字网页应该不需要这一属性,这个用来强制英文单词换行,类似于word/wps中的西文换行)
                    "}" +
                    "</style>";

            String html = "<html><header>" + css + "</header>" + t.getDatas().getShop_detail() + "</html>";
            txtLickCount.setText(String.valueOf(t.getDatas().getPopularity_count()));
            txtGoodLick.setText(String.valueOf(t.getDatas().getLike_count()));
            webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
            txtTitle.setText(t.getDatas().getShop_name());
            txtSource.setText(t.getDatas().getCreate_time() + "资讯来源:   " + t.getDatas().getShop_from());
            Link link = new Link(t.getDatas().getShop_from())
                    .setTextColor(getResources().getColor(R.color.main_color))                  // optional, defaults to holo blue
                    .setTextColorOfHighlightedLink(getResources().getColor(R.color.main_color)) // optional, defaults to holo blue
                    .setHighlightAlpha(.4f)                                     // optional, defaults to .15f
                    .setUnderlined(false)
                    .setBold(false);
            LinkBuilder.on(txtSource)
                    .addLink(link)
                    .build(); // creat
            if (t.getDatas().getIsPrised()) {
                Drawable drawable = getResources().getDrawable(R.mipmap.icon_zan_red_yzy);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                txtGoodLick.setCompoundDrawables(null, drawable, null, null);
            } else {
                Drawable drawable = getResources().getDrawable(R.mipmap.icon_zan_grey_yzy);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                txtGoodLick.setCompoundDrawables(null, drawable, null, null);
            }
        }

    }

    @Override
    public void CommentList_v(CommentEntity t) {
        if (t.getResultCode() == 1) {
            if (pageNo == 1) {
                communityCommentAdapter.setDatas(t.getDatas());
            } else {
                communityCommentAdapter.getDatas().addAll(t.getDatas());
            }
            communityCommentAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void GoodClick_v(BaseResult t, int position, int linkcount) {
        ToastUitl.show(t.getMsg(), 1000);
        if (t.getResultCode() == 1) {
            ((CommentEntity.DatasBean) communityCommentAdapter.getDatas().get(position)).setIsPrised(true);
            ((CommentEntity.DatasBean) communityCommentAdapter.getDatas().get(position)).setPrise_count(linkcount + 1);
            communityCommentAdapter.notifyItemChanged(position);
        }

    }

    @Override
    public void GoodClick_v(BaseResult t) {
        ToastUitl.show(t.getMsg(), 1000);
        if (t.getResultCode() == 1) {
            Drawable drawable = getResources().getDrawable(R.mipmap.icon_zan_red_yzy);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            txtGoodLick.setCompoundDrawables(null, drawable, null, null);
            //txtGoodLick.setText(String.valueOf(t.getDatas().getLike_count()));
            txtGoodLick.setText(String.valueOf((Integer.parseInt(txtGoodLick.getText().toString().trim()) + 1)));
        }
    }

    @Override
    public void ReplyComment_v(BaseResult t) {
        if (t.getResultCode() == 1) {
            editContent.setText("");
            updateList.clear();
            HashMap hashMap = new HashMap<>();
            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
            hashMap.put("shopcommon_id", shopcommon_id);
            hashMap.put("FKEY", PutUtils.FKEY());
            hashMap.put("pageNo", "1");
            hashMap.put("pageSize", "10");
            hashMap.put("comment_from", "3");
            mPresenter.CommentList_p(hashMap);

          /*  // communityCommentAdapter.getDatas().add
            CommentEntity.DatasBean newBean=new CommentEntity.DatasBean();
            newBean.setIsPrised(false);
            newBean.setPrise_count(0);
            newBean.setHead_img(IsCheckLogin.HeadImge());
            newBean.setNick_name(IsCheckLogin.IsUserName());
            newBean.setComment_id(((CommentEntity.DatasBean)communityCommentAdapter.getDatas().get(0)).getComment_id()+1);
            newBean.setContent(editContent.getText().toString().trim());
            newBean.setCreate_time(FormatUtil.dateToStrLong(new Date()));
            newBean.setCommentPics(new ArrayList<>());
            communityCommentAdapter.getDatas().add(0,newBean);
            communityCommentAdapter.notifyItemInserted(0);
            recyclerView.scrollToPosition(0);
         //   communityCommentAdapter.notifyItemRangeChanged(0,0);*/
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_close, R.id.right_img, R.id.iamge_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.right_img:
                Share(image_url,txtTitle.getText().toString().toString(),String.valueOf(shopcommon_id));
                break;
            case R.id.iamge_add:
                Bundle bundle =new Bundle();
                bundle.putBoolean("isRelease",false);
                bundle.putString("shopcommon_id", shopcommon_id);
                startActivityForResult(ReleaseActivity.class,bundle,RELEASE);
              //  onSelectImage();
                break;
        }
    }

    public void Share(String iamge, String title, String Shopcommon_id) {

        UMImage thumb;
        if (iamge == null) {
            thumb = new UMImage(this, R.mipmap.ic_launcher);
        } else {
            try {
                thumb = new UMImage(this, iamge);
                UMWeb web = new UMWeb(String.format(SHARE_URL, Shopcommon_id, iamge, new Random().nextInt(10000)));
                web.setThumb(thumb);
                web.setDescription(getResources().getString(R.string.app_name));
                web.setTitle(title == null ? "一直娱" : title);
                new ShareAction(this)
                        .setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                        .setCallback(this)
                        .withMedia(web)
                        .withMedia(web)
                        .open();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void onSelectImage() {
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .maxSelectNum(3)// 最大图片选择数量
                .minSelectNum(3)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.THIRD_GEAR、Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(false)// 是否显示拍照按钮 true or false
                .isZoomAnim(false)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .compressMode(LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                .glideOverride(200, 200)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .isGif(false)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .cropCompressQuality(90)// 裁剪压缩质量 默认90 int
                .compressMaxKB(1024)//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效 int
                .compressWH(Luban.CUSTOM_GEAR, Luban.CUSTOM_GEAR) // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效  int
                .rotateEnabled(false) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

    }


    @OnClick({R.id.txt_send, R.id.txt_good_lick})
    public void onViewClicked1(View view) {

        switch (view.getId()) {
            case R.id.txt_send:
                if (!TextUtils.isEmpty(editContent.getText().toString().trim())) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("content", editContent.getText().toString().trim());
                    hashMap.put("shopcommon_id", shopcommon_id);
                    hashMap.put("comment_from", "3");
                    hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                    mPresenter.ReplyComment_p(RetrofitHttp.onUpload(hashMap, updateList));
                } else {
                    ToastUitl.show("评论内容为空", 1000);

                }
                break;
            case R.id.txt_good_lick:
                HashMap<String, Object> map = new HashMap<>();
                map.put("virtualuser_id", UserLoginUtil.IsUserId());
                map.put("FKEY", PutUtils.FKEY());
                map.put("shopcommon_id", shopcommon_id);
                map.put("prise_from", "3");
                mPresenter.GoodClick_p(map);
                break;
        }


    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNo++;
        Log.e("pageNo", String.valueOf(pageNo));
        HashMap hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("shopcommon_id", shopcommon_id);
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("comment_from", "3");
        mPresenter.CommentList_p(hashMap);
        refreshlayout.finishLoadmore(1000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNo = 1;
        HashMap hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("shopcommon_id", shopcommon_id);
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("pageNo", pageNo);
        hashMap.put("pageSize", "10");
        hashMap.put("comment_from", "3");
        mPresenter.CommentList_p(hashMap);
        refreshlayout.finishRefresh(1000);
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
                    // adapter.setList(selectList);
                    //adapter.notifyDataSetChanged();
                    LogUtil.loge("TAG", "onActivityResult:" + selectList.size());
                    break;
            }
        }
    }

    /**
     * @param platform 平台类型
     * @descrption 分享开始的回调
     */
    @Override
    public void onStart(SHARE_MEDIA platform) {
    }

    /**
     * @param platform 平台类型
     * @descrption 分享成功的回调
     */

    @Override
    public void onResult(SHARE_MEDIA platform) {
        ToastUitl.showShort("分享成功");
    }

    /**
     * @param platform 平台类型
     * @param t        错误原因
     * @descrption 分享失败的回调
     */
    @Override
    public void onError(SHARE_MEDIA platform, Throwable t) {
        Log.e("分享", t.getMessage());
        ToastUitl.showShort("分享失败" + t.getMessage());

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {

    }


    public static class JavaScriptInterface {
        private List<LocalMedia> selectList = new ArrayList<>();
        private Context context;

        public JavaScriptInterface(Context context) {
            this.context = context;
        }

        //点击图片回调方法
        //必须添加注解,否则无法响应
        @JavascriptInterface
        public void openImage(String[] imgs,int i,String img) {
            int postion=0;
            selectList.clear();
            for (int s=0;s<imgs.length;s++){
                if(img.equals(imgs[s])){
                    postion=s;
                }
                LocalMedia    localMedia=new LocalMedia();
                localMedia.setPath(imgs[s]);
                selectList.add(localMedia);
            }
            Log.i("TAG", "响应点击事件!");
            Log.e("openImage",img+"");
            Log.e("下标",i+"");

            // 预览图片 可自定长按保存路径
            PictureSelector.create((Activity) context).externalPicturePreview(postion, "/idolmedia",  selectList);

        }
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();//重置webview中img标签的图片大小
            // html加载完成之后，添加监听图片的点击js函数
            addImageClickListner();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    private void addImageClickListner() {
        // 这段js函数的功能就是，遍历所有的img节点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\");var imgSrcs = new Array(); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "     imgSrcs[i] = objs[i].src;  objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelistner.openImage(imgSrcs,i,this.src);  " +
                "    }  " +
                "}" +
                "})()");
    }

    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private void imgReset() {
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
    }

}
