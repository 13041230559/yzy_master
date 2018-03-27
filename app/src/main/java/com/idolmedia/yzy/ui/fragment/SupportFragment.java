package com.idolmedia.yzy.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.DepositEntity;
import com.idolmedia.yzy.ui.activity.SupportDetalisactivity;
import com.idolmedia.yzy.ui.mvp.contract.CommdDetailsContract;
import com.idolmedia.yzy.ui.mvp.model.CommdDetailsModel;
import com.idolmedia.yzy.ui.mvp.presenter.CommdDetailsPresenter;
import com.idolmedia.yzy.utlis.GlideImageLoader;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.TitleTipUtils;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.view.popwindow.ObservableScrollView;
import com.jakewharton.rxbinding.view.RxView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.widget.PullZoomView;
import com.mumu.common.base.BaseFragment;
import com.mumu.common.utils.FormatDate;
import com.mumu.common.utils.FormatUtil;
import com.mumu.common.utils.ToastUitl;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;

/**
 * 项目名称：com.idolmedia.yzy.ui.fragment
 * 创建人：JOKER.WANG
 * 创建时间：2018/2/5 12:14
 * 描述：
 */

public class SupportFragment extends BaseFragment<CommdDetailsPresenter, CommdDetailsModel> implements CommdDetailsContract.View {

    String shopcommon_id;
    String shop_type;
    int type;
    @BindView(R.id.ss_zoom)
    Banner banner;
    @BindView(R.id.iamge_time)
    ImageView iamgeTime;
    @BindView(R.id.txt_shopstate)
    TextView txtShopstate;
    @BindView(R.id.countdownView)
    CountdownView countdownView;
    @BindView(R.id.ss_header)
    RelativeLayout ssHeader;
    @BindView(R.id.txt_shop_name)
    TextView txtShopName;
    @BindView(R.id.txt_price_section)
    TextView txtPriceSection;
    @BindView(R.id.txt_sell)
    TextView txtSell;
    @BindView(R.id.text_fb)
    TextView textFb;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.txt_ycje)
    TextView txtYcje;
    @BindView(R.id.txt_mbje)
    TextView txtMbje;
    @BindView(R.id.text_liner_support)
    LinearLayout textLinerSupport;
    @BindView(R.id.txt_initiator)
    TextView txtInitiator;
    @BindView(R.id.txt_initiator_name)
    TextView txtInitiatorName;
    @BindView(R.id.iamge_initiator_head)
    ImageView iamgeInitiatorHead;
    @BindView(R.id.txt_inititime)
    TextView txtInititime;
    @BindView(R.id.txt_initiator_time)
    TextView txtInitiatorTime;
    @BindView(R.id.txt_ranking)
    TextView txtRanking;

    @BindView(R.id.see_phb)
    TextView seePhb;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.ss_content)
    ObservableScrollView obScrolview;
    @BindView(R.id.pzv)
    PullZoomView pzv;
    Unbinder unbinder;
    @BindView(R.id.iamge_first)
    ImageView iamgeFirst;
    @BindView(R.id.iamge_rich)
    ImageView iamgeRich;
    @BindView(R.id.iamge_end)
    ImageView iamgeEnd;
    int txtFbWidth, probarWidth;

    public static SupportFragment getInstance(String shop_type, String shopcommon_id) {
        SupportFragment fragment = new SupportFragment();
        fragment.shop_type = shop_type;
        fragment.shopcommon_id = shopcommon_id;
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_support_detalis;
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
        pzv.setOnScrollListener(new PullZoomView.OnScrollListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
            }
        });
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("shopcommon_id", shopcommon_id);
        hashMap.put("shop_type", shop_type);
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        mPresenter.CommdDetails_p(hashMap);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//支持js
        webView.setWebViewClient(new MyWebViewClient());
        webView.addJavascriptInterface(new JavaScriptInterface(getActivity()), "imagelistner");
        setOnItemClickListener((SupportDetalisactivity) getActivity());
        pzv.setOnScrollListener(new PullZoomView.OnScrollListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                super.onScroll(l, t, oldl, oldt);
                if (onIScrollViewListener != null) {
                    onIScrollViewListener.ObservableScrollView(l, t, oldl, oldt);
                }
            }
        });


    }

    /*
* 设置控件所在的位置X，并且不改变宽高，
* X为绝对位置，此时Y可能归0
*/
    public static void setLayoutX(View view, int x) {
       /* ViewGroup.MarginLayoutParams margin=new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x,margin.topMargin, x+margin.width, margin.bottomMargin);*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.setMargins(x, 0, 0, 0);
        view.setLayoutParams(layoutParams);
    }

    @Override
    public void CommdDetails_v(DepositEntity t) {
        if (t.getResultCode() == 1) {
            List<String> bannerList = new ArrayList<>();
            Iterator<DepositEntity.ImgsBean> iterator = t.getImgs().iterator();
            while (iterator.hasNext()) {
                bannerList.add(iterator.next().getPic_url());
            }
            banner.setImages(bannerList);
            banner.setBannerAnimation(Transformer.Default);
            banner.setDelayTime(3000);
            banner.isAutoPlay(true);
            banner.setImageLoader(new GlideImageLoader());
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setIndicatorGravity(BannerConfig.CENTER);
            banner.start();
            //  Glide.with(getActivity()).load(t.getImgs().get(0).getPic_url()).into(ssZoom);
            Glide.with(getActivity()).load(t.getDatas().getHead_img()).apply(new RequestOptions().circleCrop()).into(iamgeInitiatorHead);
          //  countdownView.start(FormatDate.StrToDate(t.getDatas().getEnd_time()).getTime() - FormatDate.StrToDate(t.getDatas().getCurrent_time()).getTime());//  txtContent.setText(t.getDatas().getShop_name());
            if (t.getDatas().getShop_label() != null) {
                if (t.getDatas().getShop_label().equals("海外直邮")) {
                    txtShopName.setText(TitleTipUtils.titleTipUtils(getActivity(), t.getDatas().getShop_label(), t.getDatas().getShop_name(), 12, 14, 35, R.color.violet));
                } else if (t.getDatas().getShop_label().equals("娱自营")) {
                    txtShopName.setText(TitleTipUtils.titleTipUtils(getActivity(), t.getDatas().getShop_label(), t.getDatas().getShop_name(), 12, 14, 35, R.color.main_color));
                } else {
                    txtShopName.setText(TitleTipUtils.titleTipUtils(getActivity(), t.getDatas().getShop_label(), t.getDatas().getShop_name(), 12, 14, 35, R.color.ffc244));
                }
            } else {
                txtShopName.setText(t.getDatas().getShop_name());
            }
            txtInitiatorName.setText(t.getDatas().getNick_name());
            txtInitiatorTime.setText(t.getDatas().getStart_time());
            webView.loadDataWithBaseURL(null, t.getPhoto_text_detail(), "text/html", "utf-8", null);
            txtYcje.setText("￥" + FormatUtil.FormatPirce(Double.parseDouble(t.getDatas().getSupport_money())));
            txtMbje.setText("￥" + FormatUtil.FormatPirce(Double.parseDouble(t.getDatas().getSale_money())));
            txtSell.setText("已售出:" + t.getDatas().getSale_no());
         /*   if(TextUtils.isEmpty(t.getDatas().getBetween_price()).)
            txtPriceSection.setText("￥" + t.getDatas().getOriginal_price());*/
            txtPriceSection.setText("￥" + (TextUtils.isEmpty(t.getDatas().getBetween_price()) ? t.getDatas().getCurrent_price() : t.getDatas().getBetween_price()));
            if (onIScrollViewListener != null) {
                onIScrollViewListener.ShareWeb(t.getImgs().get(0).getPic_url(), t.getDatas().getShop_name(), String.valueOf(t.getDatas().getShopcommon_id()));
                onIScrollViewListener.CommodityClass(t);
            }
            double progress = Double.parseDouble(t.getDatas().getPercent());
            textFb.setText(progress+ "%");
                if((int)progress>7||(int)progress==0){
                    progressbar.setProgress((int) progress);
                }else if((int)progress<=7&&(int)progress>0){
                    progressbar.setProgress(7);
                }
         /*   if ((int) progress > 5) {
                progressbar.setProgress((int) progress);
            }else if((int) progress==0 ){


            } else {
                progressbar.setProgress(5);
            }*/
            ViewTreeObserver vto1 = textFb.getViewTreeObserver();
            vto1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    textFb.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    txtFbWidth = textFb.getWidth();
                }
            });
            ViewTreeObserver vto2 = progressbar.getViewTreeObserver();
            vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    progressbar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    probarWidth = progressbar.getWidth();
                    setLayoutX(textFb, progressbar.getWidth() * progressbar.getProgress() / 100 - (txtFbWidth / 2));
                   /* if (progress <= 5) {
                        setLayoutX(textFb, progressbar.getWidth() * 5 / 100 - (txtFbWidth / 2));
                    } else {
                        setLayoutX(textFb, progressbar.getWidth() * progressbar.getProgress() / 100 - (txtFbWidth / 2));

                    }*/
                    //Log.e("view.getWidth()", progressbar.getWidth() + "");
                    Log.e("setLayoutX", probarWidth + "===" + txtFbWidth);
                }
            });

            if (t.getRecord_pd() != null) {
                if (t.getRecord_pd().size() == 1) {
                    Glide.with(this).load(t.getRecord_pd().get(0).getHead_img()).apply(new RequestOptions().circleCrop().error(R.mipmap.default_bg)).into(iamgeFirst);
                } else if (t.getRecord_pd().size() == 2) {
                    Glide.with(this).load(t.getRecord_pd().get(0).getHead_img()).apply(new RequestOptions().circleCrop().error(R.mipmap.default_bg)).into(iamgeFirst);
                    Glide.with(this).load(t.getRecord_pd().get(1).getHead_img()).apply(new RequestOptions().circleCrop().error(R.mipmap.default_bg)).into(iamgeRich);
                } else if (t.getRecord_pd().size() == 3) {
                    Glide.with(this).load(t.getRecord_pd().get(0).getHead_img()).apply(new RequestOptions().circleCrop().error(R.mipmap.default_bg)).into(iamgeFirst);
                    Glide.with(this).load(t.getRecord_pd().get(1).getHead_img()).apply(new RequestOptions().circleCrop().error(R.mipmap.default_bg)).into(iamgeRich);
                    Glide.with(this).load(t.getRecord_pd().get(2).getHead_img()).apply(new RequestOptions().circleCrop().error(R.mipmap.default_bg)).into(iamgeEnd);

                }
            }

            long endtime = FormatDate.StrToDate(t.getDatas().getEnd_time()).getTime();
            long thistime = FormatDate.StrToDate(t.getDatas().getCurrent_time()).getTime();
            long startime = FormatDate.StrToDate(t.getDatas().getStart_time()).getTime();
            if(endtime-thistime<=0){
                txtShopstate.setText("活动已结束");
                countdownView.setVisibility(View.INVISIBLE);
            }else if(startime>thistime){
                txtShopstate.setText("距离活动开始倒计时：");
                countdownView.setVisibility(View.VISIBLE);
                countdownView.start(startime - thistime);
            }else {
                txtShopstate.setText("距离活动结束倒计时：");
                countdownView.start(endtime - thistime);
            }

        }

    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @OnClick(R.id.see_phb)
    public void onViewClicked() {
        if (onIScrollViewListener != null) {
            onIScrollViewListener.SlideViewPage(1);
        }
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
        public void openImage(String[] imgs, int i, String img) {
            int postion = 0;
            selectList.clear();
            for (int s = 0; s < imgs.length; s++) {
                if (img.equals(imgs[s])) {
                    postion = s;
                }
                LocalMedia localMedia = new LocalMedia();
                localMedia.setPath(imgs[s]);
                selectList.add(localMedia);
            }
            Log.i("TAG", "响应点击事件!");
            Log.e("openImage", img + "");
            Log.e("下标", i + "");
            // 预览图片 可自定长按保存路径
            PictureSelector.create((Activity) context).externalPicturePreview(postion, "/idolmedia", selectList);

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
                "img.style.width = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
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

    //定义如下方法给rc设置点击事件
    public interface OnIScrollViewListener {
        void ObservableScrollView(int l, int t, int oldl, int oldt);

        void SlideViewPage(int page);

        void ShareWeb(String iamge, String title, String Shopcommon_id);


        void CommodityClass(DepositEntity t);

        //  void DepositCommodityClass(List<DepositEntity.CataPdBean> commod,int paybtn);
    }

    public void setOnItemClickListener(OnIScrollViewListener listener) {
        this.onIScrollViewListener = listener;
    }

    private OnIScrollViewListener onIScrollViewListener = null;
}
