package com.idolmedia.yzy.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.DepositEntity;
import com.idolmedia.yzy.entity.MessageEvent;
import com.idolmedia.yzy.ui.activity.CommodityDetailsActivity;
import com.idolmedia.yzy.ui.mvp.contract.CommdDetailsContract;
import com.idolmedia.yzy.ui.mvp.model.CommdDetailsModel;
import com.idolmedia.yzy.ui.mvp.presenter.CommdDetailsPresenter;
import com.idolmedia.yzy.utlis.GlideImageLoader;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.TitleTipUtils;
import com.idolmedia.yzy.view.popwindow.ObservableScrollView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.mumu.common.base.BaseFragment;
import com.mumu.common.utils.FormatDate;
import com.mumu.common.utils.FormatUtil;
import com.mumu.common.utils.ToastUitl;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;

/**
 * 项目名称：com.idolmedia.yzy.ui.fragment
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/14 15:33
 * 描述： 宝贝详情
 */

public class CommdDetailsFragment extends BaseFragment<CommdDetailsPresenter, CommdDetailsModel> implements CommdDetailsContract.View,CountdownView.OnCountdownEndListener {
    Unbinder unbinder;
    String type;
    String shop_id;
    String ordernum;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.iamge_time)
    ImageView iamgeTime;
    @BindView(R.id.txt_shopstate)
    TextView txtShopstate;
    @BindView(R.id.countdownView)
    CountdownView countdownView;
    @BindView(R.id.support_head)
    FrameLayout supportHead;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_deposit_time)
    TextView txtDepositTime;
    @BindView(R.id.txt_retainage_time)
    TextView txtRetainageTime;
    @BindView(R.id.txt_retainage_star)
    TextView txtRetainageStar;
    @BindView(R.id.txt_deposit)
    TextView txtDeposit;
    @BindView(R.id.liner_deposit)
    LinearLayout linerDeposit;
    @BindView(R.id.txt_pricerange)
    TextView txtPricerange;
    @BindView(R.id.txt_sold)
    TextView txtSold;
    @BindView(R.id.iamge_zy)
    ImageView iamgeZy;
    @BindView(R.id.txt_activity)
    TextView txtActivity;
    @BindView(R.id.activity_content)
    TextView activityContent;
    @BindView(R.id.liner_activity)
    LinearLayout linerActivity;
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
    @BindView(R.id.txt_comment)
    TextView txtComment;
    @BindView(R.id.flowlayout)
    TagFlowLayout flowlayout;
    @BindView(R.id.img_pl_head)
    ImageView imgPlHead;
    @BindView(R.id.txt_pl_name)
    TextView txtPlName;
    @BindView(R.id.txt_pl_context)
    TextView txtPlContext;
    @BindView(R.id.txt_pl_titme)
    TextView txtPlTitme;
    @BindView(R.id.liner_content)
    LinearLayout linerContent;
    @BindView(R.id.see_pl)
    TextView seePl;
    @BindView(R.id.txt_ranking)
    TextView txtRanking;
    @BindView(R.id.iamge_first)
    ImageView iamgeFirst;
    @BindView(R.id.iamge_rich)
    ImageView iamgeRich;
    @BindView(R.id.iamge_end)
    ImageView iamgeEnd;
    @BindView(R.id.see_phb)
    TextView seePhb;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.ob_scrolview)
    ObservableScrollView obScrolview;
    @BindView(R.id.commd_liner)
    LinearLayout commdLiner;
    @BindView(R.id.txt_noevaluate)
    TextView txtNoevaluate;


    public static CommdDetailsFragment getInstance(String type, String shopcommon_id, String ordernum) {
        CommdDetailsFragment commdDetailsFragment = new CommdDetailsFragment();
        commdDetailsFragment.type = type;
        commdDetailsFragment.shop_id = shopcommon_id;
        commdDetailsFragment.ordernum = ordernum;
        return commdDetailsFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_commd_details;
    }


    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        setOnItemClickListener((CommodityDetailsActivity) getActivity());
        obScrolview.setOnObservableScrollViewListener((l, t, oldl, oldt) -> {
            if (onIScrollViewListener != null) {
                onIScrollViewListener.ObservableScrollView(l, t, oldl, oldt);
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//支持js
        webView.setWebViewClient(new MyWebViewClient());
        webView.addJavascriptInterface(new JavaScriptInterface(getActivity()), "imagelistner");
        countdownView.setOnCountdownEndListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("shopcommon_id", shop_id);
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("shop_type", type);
        hashMap.put("order_num", ordernum == null ? "" : ordernum);
        if (!type.equals("reserve")) {
            linerDeposit.setVisibility(View.GONE);
            mPresenter.CommdDetails_p(hashMap);
        } else {
            txtPricerange.setVisibility(View.GONE);
            mPresenter.DepositCommdDetails_p(hashMap);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent event) {
        String msg = "onEventMainThread收到了消息：" + event.getMessage_type();
        Log.e("onEventMainThread",msg);
     /*   if (TextUtils.equals("commenttype", event.getMessage_type())) {
            List<DepositEntity.CommentTypeBean> commentTypeBeanList = (List<DepositEntity.CommentTypeBean>) event.getObject();
            Log.e("onEventMainThread", commentTypeBeanList.get(0).getSatis_name());
            flowlayout.setAdapter(new TagAdapter(commentTypeBeanList) {
                @Override
                public View getView(FlowLayout parent, int position, Object o) {
                    TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tag_item, flowlayout, false);
                    tv.setTextSize(12);
                    tv.setText(commentTypeBeanList.get(position).getSatis_name() + "(" + commentTypeBeanList.get(position).getCount() + ")");
                    return tv;
                }
            });
            flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("shopcommon_id", shopcommon_id);
                    hashMap.put("pageNo", pageNo);
                    hashMap.put("pageSize", pageSize);
                    hashMap.put("comment_from", "1");
                    hashMap.put("satisfaction", position == 0 ? "" : position);
                    mPresenter.CommentList_p(hashMap);
                    return true;
                }
            });*/

    }

    @Override
    public void onEnd(CountdownView cv) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("shopcommon_id", shop_id);
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("shop_type", type);
        hashMap.put("order_num", ordernum == null ? "" : ordernum);
        if (!type.equals("reserve")) {
            linerDeposit.setVisibility(View.GONE);
            mPresenter.CommdDetails_p(hashMap);
        } else {
            txtPricerange.setVisibility(View.GONE);
            mPresenter.DepositCommdDetails_p(hashMap);
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
                "img.style.width = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
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


    @OnClick({R.id.see_pl, R.id.see_phb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.see_pl:
                if (onIScrollViewListener != null) {
                    onIScrollViewListener.SlideViewPage(1);
                }
                break;
            case R.id.see_phb:
                if (onIScrollViewListener != null) {
                    onIScrollViewListener.SlideViewPage(2);
                }
                break;
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
        ToastUitl.showLong(msg);
    }




    @Override
    public void CommdDetails_v(DepositEntity t) {
        if (t.getResultCode() == 1) {
            List<String> bannerList = new ArrayList<>();
             List<LocalMedia> selectList = new ArrayList<>();
             for(int i=0;i<t.getImgs().size();i++){

                 bannerList.add(t.getImgs().get(i).getPic_url());
                 LocalMedia    localMedia=new LocalMedia();
                 localMedia.setPath(t.getImgs().get(i).getPic_url());
                 selectList.add(localMedia);
             }


            banner.setImages(bannerList);
            banner.setOnBannerListener(position -> {
                // 预览图片 可自定长按保存路径
                PictureSelector.create((Activity) getActivity()).externalPicturePreview(position, "/idolmedia",  selectList);
            });
            banner.setBannerAnimation(Transformer.Default);
            banner.setDelayTime(3000);
            banner.isAutoPlay(true);
            banner.setImageLoader(new GlideImageLoader());
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setIndicatorGravity(BannerConfig.CENTER);
            banner.start();

            MessageEvent msgEvent= new MessageEvent();
            msgEvent.setMessage_type("commenttype");
            msgEvent.setObject(t.getComment_type());
            EventBus.getDefault().post(msgEvent);
            if (onIScrollViewListener != null) {
                onIScrollViewListener.ShareWeb(t.getImgs().get(0).getPic_url(), t.getDatas().getShop_name(), String.valueOf(t.getDatas().getShopcommon_id()));
                onIScrollViewListener.CommodityClass(t);
                onIScrollViewListener.CommentType(t.getComment_type());
              //
            }
            if (t.getCommont_pd() == null) {
                linerContent.setVisibility(View.GONE);
            }
            if (t.getDatas().getIsForeign() == 2) {
                iamgeZy.setVisibility(View.VISIBLE);
            } else {
                iamgeZy.setVisibility(View.GONE);
            }
            txtSold.setText(String.format(getString(R.string.sold_out), t.getDatas().getSale_no()));
            txtInitiatorName.setText(t.getDatas().getNick_name());
            Glide.with(this).load(t.getDatas().getHead_img()).apply(new RequestOptions().circleCrop()).into(iamgeInitiatorHead);
        //    txtTitle.setText(t.getDatas().getShop_name());
            if(t.getDatas().getShop_label()!=null){

                if(t.getDatas().getShop_label().equals("海外直邮")){
                    txtTitle.setText(TitleTipUtils.titleTipUtils(getActivity(),t.getDatas().getShop_label(),t.getDatas().getShop_name(),12,14,35,R.color.violet));
                   // helper.setText(R.id.txt_title, TitleTipUtils.titleTipUtils(mContext,item.getShop_label(),item.getShop_name(),12,14,35,R.color.violet));
                }else if(t.getDatas().getShop_label().equals("娱自营")){
                  //  helper.setText(R.id.txt_title,TitleTipUtils.titleTipUtils(mContext,item.getShop_label(),item.getShop_name(),12,14,35,R.color.main_color));
                    txtTitle.setText(TitleTipUtils.titleTipUtils(getActivity(),t.getDatas().getShop_label(),t.getDatas().getShop_name(),12,14,35,R.color.main_color));
                }else {
                    txtTitle.setText(TitleTipUtils.titleTipUtils(getActivity(),t.getDatas().getShop_label(),t.getDatas().getShop_name(),12,14,35,R.color.ffc244));
                    //   helper.setText(R.id.txt_title,TitleTipUtils.titleTipUtils(mContext,item.getShop_label(),item.getShop_name(),12,14,35,R.color.ffc244));
                }
            }else {
                txtTitle.setText(t.getDatas().getShop_name());
               // helper.setText(R.id.txt_title,  item.getShop_name());
            }

            if (!type.equals("reserve")) {
                long endtime = FormatDate.StrToDate(t.getDatas().getEnd_time()).getTime();
                long thistime = FormatDate.StrToDate(t.getDatas().getCurrent_time()).getTime();
                long startime = FormatDate.StrToDate(t.getDatas().getStart_time()).getTime();
                txtActivity.setText("优惠");
                activityContent.setText(t.getDatas().getFreePost());
                txtPricerange.setText("￥" + (TextUtils.isEmpty(t.getDatas().getBetween_price()) ?FormatUtil.FormatPirce(Double.parseDouble(t.getDatas().getCurrent_price())) : t.getDatas().getBetween_price()));
                txtInitiatorTime.setText(t.getDatas().getStart_time());
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
            } else {
                txtDeposit.setText(String.format(getString(R.string.deductible), t.getDatas().getReserve_money(), t.getDatas().getDeduction_money()));
                //String.format(getString(R.string.deductible),t.getDatas().getReserve_money(),t.getDatas().getDeduction_money());
                // txtDeposit.setText("定金:"+t.getDatas().getReserve_money()+"可抵:"+t.getDatas().getDeduction_money());
                txtActivity.setText("流程:1.预付定金-2.付尾款-3.发货");
                activityContent.setText("");
              /*  Drawable drawable = getResources().getDrawable(R.mipmap.icon_warning_yellow_yzy);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                activityContent.setCompoundDrawables(null, null, drawable, null);*/
                long this_time = FormatDate.StrToDate(t.getDatas().getCurrent_time()).getTime();
                long reserve_end_time = FormatDate.StrToDate(t.getDatas().getReserve_end_time()).getTime();
                long reserve_start_time = FormatDate.StrToDate(t.getDatas().getReserve_start_time()).getTime();
                int status = t.getDatas().getReserve_status();
                if (this_time <= reserve_end_time) {
                    //定金未开始
                    txtDepositTime.setText(String.format(getString(R.string.star_time), t.getDatas().getReserve_start_time()));
                    txtRetainageTime.setText(String.format(getString(R.string.end_time), t.getDatas().getReserve_end_time()));
                    txtShopstate.setText("距离定金结束到计时：");
                    countdownView.start(reserve_end_time - this_time);
                    txtInitiatorTime.setText(t.getDatas().getReserve_start_time());
                } else if (this_time < reserve_start_time) {
                    //支付定金
                    txtDepositTime.setText(String.format(getString(R.string.star_time), t.getDatas().getReserve_start_time()));
                    txtRetainageTime.setText(String.format(getString(R.string.end_time), t.getDatas().getReserve_end_time()));
                    txtShopstate.setText("距离定金开始倒计时：");
                    countdownView.start(reserve_start_time - this_time);
                    txtInitiatorTime.setText(t.getDatas().getReserve_start_time());
                } else if (status == 2) {
                    long final_end_time = FormatDate.StrToDate(t.getDatas().getFinal_end_time()).getTime();
                    long final_star_time = FormatDate.StrToDate(t.getDatas().getFinal_start_time()).getTime();
                    if (this_time < final_star_time) {
                        //尾款待支付
                        txtDepositTime.setText(String.format(getString(R.string.star_time), t.getDatas().getFinal_start_time()));
                        txtRetainageTime.setText(String.format(getString(R.string.end_time), t.getDatas().getFinal_end_time()));
                        txtShopstate.setText("距离尾款开始到计时：");
                        countdownView.start(final_star_time - this_time);
                        txtInitiatorTime.setText(t.getDatas().getFinal_start_time());
                    } else if (this_time < final_end_time) {
                        //支付尾款
                        txtDepositTime.setText(String.format(getString(R.string.star_time), t.getDatas().getFinal_start_time()));
                        txtRetainageTime.setText(String.format(getString(R.string.end_time), t.getDatas().getFinal_end_time()));
                        txtShopstate.setText("距离尾款结束到计时：");
                        countdownView.start(final_end_time - this_time);
                        txtInitiatorTime.setText(t.getDatas().getFinal_start_time());
                    } else {
                        //尾款结束
                        txtDepositTime.setText(String.format(getString(R.string.star_time), t.getDatas().getFinal_start_time()));
                        txtRetainageTime.setText(String.format(getString(R.string.end_time), t.getDatas().getFinal_end_time()));
                        txtShopstate.setText("尾款已结束：");
                        countdownView.setVisibility(View.GONE);
                        txtInitiatorTime.setText(t.getDatas().getFinal_start_time());
                    }
                } else if (status == 1) {
                    //定金结束
                    txtShopstate.setText("定金已结束：");
                    txtDepositTime.setText(String.format(getString(R.string.star_time), t.getDatas().getReserve_start_time()));
                    txtRetainageTime.setText(String.format(getString(R.string.end_time), t.getDatas().getReserve_end_time()));
                    txtInitiatorTime.setText(t.getDatas().getReserve_start_time());

                } else {
                    //活动已结束
                    txtShopstate.setText("活动已结束：");
                    countdownView.setVisibility(View.INVISIBLE  );
                    txtDepositTime.setText(String.format(getString(R.string.star_time), t.getDatas().getFinal_start_time()));
                    txtRetainageTime.setText(String.format(getString(R.string.end_time), t.getDatas().getFinal_end_time()));
                    txtInitiatorTime.setText(t.getDatas().getFinal_start_time());
                }
            }

         /*   countdownView.setOnCountdownEndListener(cv -> {

            });*/
            webView.loadDataWithBaseURL(null, t.getPhoto_text_detail(), "text/html", "utf-8", null);

            if (t.getCommont_pd().getContent() != null) {
                commdLiner.setVisibility(View.VISIBLE);
                txtNoevaluate.setVisibility(View.GONE);
                Glide.with(this).load(t.getCommont_pd().getHead_img()).apply(new RequestOptions().circleCrop().error(R.mipmap.default_bg)).into(imgPlHead);
                txtPlName.setText(t.getCommont_pd().getNick_name());
                txtPlContext.setText(t.getCommont_pd().getContent());
                txtPlTitme.setText(t.getCommont_pd().getCreat_time());
                 seePl.setVisibility(View.VISIBLE);
                TagAdapter tagAdapter;
                tagAdapter=new TagAdapter(t.getComment_type()) {
                    @Override
                    public View getView(FlowLayout parent, int position, Object o) {
                        TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tag_item, flowlayout, false);
                        tv.setTextSize(12);
                        tv.setText(t.getComment_type().get(position).getSatis_name() + "(" + t.getComment_type().get(position).getCount() + ")");
                        return tv;
                    }
                };
                flowlayout.setAdapter(tagAdapter);
                flowlayout.setOnTagClickListener((view, position, parent) -> {
                    if (onIScrollViewListener != null) {
                        onIScrollViewListener.SlideViewPage(1);
                    }
                    return true;
                });
            } else {
                commdLiner.setVisibility(View.GONE);
                seePl.setVisibility(View.GONE);
                txtNoevaluate.setVisibility(View.VISIBLE);

            }

        }
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


    }

    //定义如下方法给rc设置点击事件
    public interface OnIScrollViewListener {
        void ObservableScrollView(int l, int t, int oldl, int oldt);

        void SlideViewPage(int page);

        void ShareWeb(String iamge, String title, String Shopcommon_id);


        void CommodityClass(DepositEntity t);

        void CommentType(List<DepositEntity.CommentTypeBean> commentTypeBeanList);

        //  void DepositCommodityClass(List<DepositEntity.CataPdBean> commod,int paybtn);
    }
    private OnIScrollViewListener onIScrollViewListener = null;

    public void setOnItemClickListener(OnIScrollViewListener listener) {
        this.onIScrollViewListener = listener;
    }
}
