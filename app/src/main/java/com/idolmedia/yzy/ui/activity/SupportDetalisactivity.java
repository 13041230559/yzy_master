package com.idolmedia.yzy.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.ConfirmOrderEntity;
import com.idolmedia.yzy.entity.DepositEntity;
import com.idolmedia.yzy.entity.ProductclassEntity;
import com.idolmedia.yzy.ui.adapter.CommodityTagAdapter;
import com.idolmedia.yzy.ui.adapter.PageAdpater;
import com.idolmedia.yzy.ui.fragment.RankingListFragment;
import com.idolmedia.yzy.ui.fragment.SupportFragment;
import com.idolmedia.yzy.ui.mvp.contract.CommodityDetailsContract;
import com.idolmedia.yzy.ui.mvp.model.CommodityDetailsModel;
import com.idolmedia.yzy.ui.mvp.presenter.CommodityDetailsPresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.view.popwindow.NumberButton;
import com.idolmedia.yzy.view.popwindow.PopItemAction;
import com.idolmedia.yzy.view.popwindow.PopWindow;
import com.jakewharton.rxbinding.view.RxView;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.DisplayUtil;
import com.mumu.common.utils.FormatDate;
import com.mumu.common.utils.ToastUitl;
import com.mumu.common.widget.StatusBarCompat;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/14 19:21
 * 描述：
 */

public class SupportDetalisactivity extends BaseActivity<CommodityDetailsPresenter, CommodityDetailsModel> implements CommodityDetailsContract.View, SupportFragment.OnIScrollViewListener, UMShareListener {

    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.image_close)
    ImageView imageClose;
    @BindView(R.id.rela_close)
    RelativeLayout relaClose;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.iamge_cart)
    ImageView iamgeCart;
    @BindView(R.id.rela_cart)
    RelativeLayout relaCart;
    @BindView(R.id.iamge_shaer)
    ImageView iamgeShaer;
    @BindView(R.id.rela_shaer)
    RelativeLayout relaShaer;
    @BindView(R.id.top_title)
    LinearLayout topTitle;
    @BindView(R.id.txt_collectNum)
    TextView txtCollectNum;
    @BindView(R.id.liner_collection)
    LinearLayout linerCollection;
    @BindView(R.id.txt_go)
    TextView txtGo;
    @BindView(R.id.liner_bottom)
    LinearLayout linerBottom;
    private ArrayList<Fragment> fragmentslist;
    String shopcommon_id;
    String shop_type;
    PageAdpater pageAdpater;
    int selectProductclass = 0;
    int Slide = 0;
    public static final String SHARE_URL = "http://www.withfans.com/webProgress/webPage/productDetail.html?id=%s&shop_type=%s&random=%d&from=android";

    @Override
    public int getLayoutId() {
        return R.layout.activity_supportdetalis;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);

    }

    @Override
    public void initView() {
        shopcommon_id = getIntent().getStringExtra("shopcommon_id");
        shop_type = getIntent().getStringExtra("shop_type");

        topTitle.setPadding(0, DisplayUtil.getStatusBarHeight(this) + getResources().getDimensionPixelOffset(R.dimen.dp_5), 0, 0);
        StatusBarCompat.translucentStatusBar(this);
        String[] title_name = new String[]{"周边", "排行榜"};
        for (int i = 0; i < title_name.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(title_name[i]));
        }
        tabLayout.setVisibility(View.INVISIBLE);
        fragmentslist = new ArrayList<>();
        fragmentslist.add(SupportFragment.getInstance(shop_type, shopcommon_id));
        fragmentslist.add(RankingListFragment.getInstance(2, String.valueOf(shopcommon_id)));
        pageAdpater = new PageAdpater(getSupportFragmentManager(), fragmentslist, title_name);
        viewPage.setAdapter(pageAdpater);
        tabLayout.setupWithViewPager(viewPage);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPage.setCurrentItem(tab.getPosition());
                if (tab.getPosition() > 0) {
                    topTitle.setBackgroundColor(getResources().getColor(R.color.main_color));
                    tabLayout.setVisibility(View.VISIBLE);
                    relaClose.setBackgroundColor(getResources().getColor(R.color.transparent));
                    relaShaer.setBackgroundColor(getResources().getColor(R.color.transparent));
                    relaCart.setBackgroundColor(getResources().getColor(R.color.transparent));
                } else {
                    if (Slide <= 0) {
                        //顶部图处于最顶部，标题栏透明
                        tabLayout.setVisibility(View.INVISIBLE);
                        topTitle.setBackgroundColor(Color.argb(0, 245, 49, 109));
                        relaClose.setBackground(getResources().getDrawable(R.drawable.circular_b));
                        relaShaer.setBackground(getResources().getDrawable(R.drawable.circular_b));
                        relaCart.setBackground(getResources().getDrawable(R.drawable.circular_b));
                    } else if (Slide > 0) {
                        //滑动过程中，渐变
                        tabLayout.setVisibility(View.VISIBLE);
                        float scale = (float) Slide / 60;//算出滑动距离比例
                        float alpha = (255 * scale);//得到透明度
                        alpha = (float) 0.5;
                        topTitle.setBackgroundColor(getResources().getColor(R.color.main_color));
                        // txtTitle.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                        relaClose.setBackgroundColor(getResources().getColor(R.color.transparent));
                        relaShaer.setBackgroundColor(getResources().getColor(R.color.transparent));
                        relaCart.setBackgroundColor(getResources().getColor(R.color.transparent));
                    } else {
                        tabLayout.setVisibility(View.INVISIBLE);
                        //过顶部图区域，标题栏定色
                        topTitle.setBackgroundColor(Color.argb(255, 245, 49, 109));
                        //    txtTitle.setTextColor(getResources().getColor(R.color.white));
                        //  relaClose.setBackground(getResources().getDrawable(R.drawable.yuan_b));
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("singTask", "singTask");
        shopcommon_id = intent.getStringExtra("shopcommon_id");
        shop_type = intent.getStringExtra("shop_type");
    }

    @Override
    public void ObservableScrollView(int l, int t, int oldl, int oldt) {
        Slide = t;
        if (t <= 0) {
            //顶部图处于最顶部，标题栏透明
            tabLayout.setVisibility(View.INVISIBLE);
            topTitle.setBackgroundColor(Color.argb(0, 245, 49, 109));
            relaClose.setBackground(getResources().getDrawable(R.drawable.circular_b));
            relaShaer.setBackground(getResources().getDrawable(R.drawable.circular_b));
            relaCart.setBackground(getResources().getDrawable(R.drawable.circular_b));
        } else if (t > 0) {
            //滑动过程中，渐变
            tabLayout.setVisibility(View.VISIBLE);
            float scale = (float) t / 60;//算出滑动距离比例
            float alpha = (255 * scale);//得到透明度
            alpha = (float) 0.5;
            topTitle.setBackgroundColor(getResources().getColor(R.color.main_color));
            // txtTitle.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            relaClose.setBackgroundColor(getResources().getColor(R.color.transparent));
            relaShaer.setBackgroundColor(getResources().getColor(R.color.transparent));
            relaCart.setBackgroundColor(getResources().getColor(R.color.transparent));
            //  relaShop.setBackgroundColor(getResources().getColor(R.color.transparent));
        } else {
            tabLayout.setVisibility(View.INVISIBLE);
            //过顶部图区域，标题栏定色
            topTitle.setBackgroundColor(Color.argb(255, 245, 49, 109));
            //    txtTitle.setTextColor(getResources().getColor(R.color.white));
            //  relaClose.setBackground(getResources().getDrawable(R.drawable.yuan_b));
        }
        //     Log.e("ObservableScrollView", l + "==" + "==" + t + "===" + oldl + "===" + oldt);

    }

    @Override
    public void SlideViewPage(int page) {
        viewPage.setCurrentItem(page);
        topTitle.setBackgroundColor(getResources().getColor(R.color.main_color));
    }

    @Override
    public void ShareWeb(String iamge, String title, String Shopcommon_id) {
        relaShaer.setOnClickListener(view -> Share(iamge, title, Shopcommon_id));
    }

    public void Share(String iamge, String title, String Shopcommon_id) {

        UMImage thumb;
        if (iamge == null) {
            thumb = new UMImage(this, R.mipmap.ic_launcher);
        } else {
            try {
                thumb = new UMImage(this, iamge);
                UMWeb web = new UMWeb(String.format(SHARE_URL, Shopcommon_id, shop_type, new Random().nextInt(10000)));
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

    @Override
    public void CommodityClass(DepositEntity t) {
        if (t.getDatas().getIsPrised()) {
            Drawable drawable = getResources().getDrawable(R.mipmap.icon_love_selected_yzy);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            txtCollectNum.setCompoundDrawables(drawable,null , null, null);
        } else {
            Drawable drawable = getResources().getDrawable(R.mipmap.icon_love_yzy);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            txtCollectNum.setCompoundDrawables(drawable,null , null, null);
        }
        txtCollectNum.setText(String.valueOf(t.getDatas().getLike_count()));
        if (!t.getDatas().getIsPrised()) {
            txtCollectNum.setOnClickListener(view -> {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("FKEY", PutUtils.FKEY());
                hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                hashMap.put("shopcommon_id", t.getDatas().getShopcommon_id());
                hashMap.put("prise_from", 1);
                mPresenter.GoodClick_p(hashMap, t.getDatas().getLike_count());
            });
        }
        long endtime = FormatDate.StrToDate(t.getDatas().getEnd_time()).getTime();
        long thistime = FormatDate.StrToDate(t.getDatas().getCurrent_time()).getTime();
        long startime = FormatDate.StrToDate(t.getDatas().getStart_time()).getTime();
        if(endtime-thistime<=0){
            txtGo.setText("活动已结束");
            txtGo.setBackgroundColor(getResources().getColor(R.color.cccccc));
            txtGo.setFocusable(false);
            txtGo.setEnabled(false);
            txtGo.setClickable(false);
        }else if(startime>thistime) {
            txtGo.setText("活动未开始");
            txtGo.setBackgroundColor(getResources().getColor(R.color.cccccc));
            txtGo.setFocusable(false);
            txtGo.setEnabled(false);
            txtGo.setClickable(false);

        }else {
            RxView.clicks(txtGo)
                    .throttleFirst(2, TimeUnit.SECONDS)   //两秒钟之内只取一个点击事件，防抖操作
                    .subscribe(aVoid -> ProductclassPop(t, selectProductclass, false, true));
        }

    }

    private  void setPopData( PopWindow.Builder popWindow,DepositEntity t, boolean isFreight,List<DepositEntity.CataPdBean> list,int position,ImageView image_chart, TextView txt_section, TextView txt_stock, TextView txt_type,TextView txt_limitbuy,NumberButton numberButton,boolean isAddCart,TextView txt_go,TextView txt_zk_price){
        Glide.with(this).load(list.get(position).getCatalog_img()).apply(new RequestOptions().error(R.mipmap.default_bg)).into(image_chart);
        txt_section.setText("￥" + list.get(position).getCurrent_price());
        txt_stock.setText(String.format(getString(R.string.stock), list.get(position).getSurplus_no()));
        //txt_type.setText(getString(R.m)list.get(position).getCatalog_title());
        txt_type.setText(String.format(getString(R.string.shop_type),list.get(position).getCatalog_title()));
        txt_zk_price.setText("￥" +list.get(position).getOriginal_price());
        txt_zk_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线 //中间横线);
        if (list.get(position).getLimit_buy() > 0) {
            txt_limitbuy.setVisibility(View.VISIBLE);
            txt_limitbuy.setText(String.format(getString(R.string.limit), list.get(position).getLimit_buy(),list.get(position).getBuyedCount()));
        } else {
            txt_limitbuy.setVisibility(View.INVISIBLE);
        }
        numberButton.setCurrentNumber(1)
                .setBuyMax(list.get(position).getLimit_buy()==0?list.get(position).getSurplus_no():list.get(position).getLimit_buy()-list.get(position).getBuyedCount())
                .setInventory(list.get(position).getSurplus_no())
                .setOnWarnListener(new NumberButton.OnWarnListener() {
                    @Override
                    public void onWarningForInventory(int inventory) {
                        ToastUitl.show("该商品库存不足", 1000);
                    }
                    @Override
                    public void onWarningForBuyMax(int max) {
                        if(list.get(position).getLimit_buy()==0){
                            ToastUitl.show("该商品库存不足", 1000);
                        }else {

                            ToastUitl.show(String.format(getString(R.string.limit_xg),list.get(position).getLimit_buy()), 1000);
                        }

                    }
                });

        txt_go.setOnClickListener(view -> {
            if (isAddCart) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                hashMap.put("from_id", t.getDatas().getVirtualuser_id()); //来源ID
                hashMap.put("from_name", t.getDatas().getNick_name());//来源名称
                hashMap.put("shopcommon_id", shopcommon_id);//商品ID
                if (list != null) {
                    hashMap.put("ssc_id", list.get(position).getSsc_id());//商品分类ID
                    hashMap.put("unint_price", list.get(position).getCurrent_price());//单价
                } else {
                    ToastUitl.show("有BUG", 1000);
                }
                hashMap.put("quantity_count", numberButton.getNumber());//单价
                hashMap.put("FKEY", PutUtils.FKEY());//单价
                mPresenter.AddCart_p(hashMap);
            } else {
                Log.e("ssc_id", list.get(position).getSsc_id() + "");
                ConfirmOrderEntity confirmOrderEntity = new ConfirmOrderEntity();
                List<ConfirmOrderEntity.ShopDataBean> listshopData = new ArrayList<>();
                ConfirmOrderEntity.ShopDataBean shopDataBean = new ConfirmOrderEntity.ShopDataBean();
                ConfirmOrderEntity.ShopDataBean.ShopsBean shops = new ConfirmOrderEntity.ShopDataBean.ShopsBean();
                List<ConfirmOrderEntity.ShopDataBean.ShopsBean> listShops = new ArrayList<>();
                List<ConfirmOrderEntity.ShopDataBean.ShopsBean.CartInfoBean> listCartInfo = new ArrayList<>();
                ConfirmOrderEntity.ShopDataBean.ShopsBean.CartInfoBean cartInfoBean = new ConfirmOrderEntity.ShopDataBean.ShopsBean.CartInfoBean();
                shopDataBean.setHead_img(t.getDatas().getHead_img());
                shopDataBean.setFrom_name(t.getDatas().getNick_name());
                shopDataBean.setFrom_id(Integer.parseInt(t.getDatas().getVirtualuser_id()));
                shops.setShopcommon_id(t.getDatas().getShopcommon_id());
                shops.setShop_name(t.getDatas().getShop_name());
                shops.setShop_label(t.getDatas().getShop_label());
                cartInfoBean.setQuantity_count(numberButton.getNumber());
                cartInfoBean.setShop_name(t.getDatas().getShop_name());
                cartInfoBean.setShop_label(t.getDatas().getShop_label());
                cartInfoBean.setCatalog_title(list.get(position).getCatalog_title());
                cartInfoBean.setCaralog_img(list.get(position).getCatalog_img());
                cartInfoBean.setSsc_id(list.get(position).getSsc_id());
                cartInfoBean.setOriginal_price(list.get(position).getOriginal_price());
                cartInfoBean.setUnint_price(list.get(position).getCurrent_price());
                listCartInfo.add(cartInfoBean);
                shops.setCartInfo(listCartInfo);
                listShops.add(shops);
                shopDataBean.setShops(listShops);
                listshopData.add(shopDataBean);
                confirmOrderEntity.setShopData(listshopData);
                Bundle bundle = new Bundle();
                bundle.putParcelable("buy", confirmOrderEntity);
                bundle.putBoolean("isRetainage", false);
                //  bundle.putString("ordernum", ordernum);
                bundle.putBoolean("isFreight", isFreight);
                startActivity(SubmitOrderBuyactivity.class, bundle);
            }
            popWindow.create().dismiss();
        });
    }


    private void ProductclassPop(DepositEntity t, int selectposition, boolean isAddCart, boolean isFreight) {
        View customView = View.inflate(this, R.layout.select_commodity_popup, null);
        PopWindow.Builder popWindow = new PopWindow.Builder(this);
        popWindow.setStyle(PopWindow.PopWindowStyle.PopUp)
                .addItemAction(new PopItemAction("选项"))
                .setView(customView)
                .show();
        RecyclerView tagRecyclerView = customView.findViewById(R.id.tag_recycler_view);
        ImageView image_chart = customView.findViewById(R.id.image_chart);
        TextView txt_section = customView.findViewById(R.id.txt_section);
        TextView txt_stock = customView.findViewById(R.id.txt_stock);
        TextView txt_type = customView.findViewById(R.id.txt_type);
        TextView txt_go = customView.findViewById(R.id.txt_go);
        TextView txt_limitbuy = customView.findViewById(R.id.txt_limitbuy);
        TextView txt_zk_price = customView.findViewById(R.id.txt_zk_price);
        ImageView iamge_colse = customView.findViewById(R.id.iamge_colse);
        NumberButton numberButton = customView.findViewById(R.id.number_button);
        iamge_colse.setOnClickListener(v -> popWindow.create().dismiss());
        CommodityTagAdapter commodityTagAdapter=new CommodityTagAdapter();

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(SupportDetalisactivity.this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        tagRecyclerView.setLayoutManager(layoutManager);
        tagRecyclerView.setAdapter(commodityTagAdapter);
        commodityTagAdapter.setNewData(t.getCata_pd());
        numberButton.setCurrentNumber(1);
        Glide.with(this).load(t.getImgs().get(0).getPic_url()).apply(new RequestOptions().error(R.mipmap.default_bg)).into(image_chart);
        txt_section.setText("￥"+(TextUtils.isEmpty(t.getDatas().getBetween_price())?t.getDatas().getCurrent_price():t.getDatas().getBetween_price()));
        txt_stock.setText(String.format(getString(R.string.stock), t.getDatas().getSurplus_no()));
        //txt_type.setText(getString(R.m)list.get(position).getCatalog_title());
        txt_type.setText(String.format(getString(R.string.shop_type),t.getDatas().getShop_name()));
        txt_zk_price.setText("￥" +t.getDatas().getOriginal_price());
        txt_zk_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线 //中间横线);
        commodityTagAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()){
                case R.id.select_item:
                    List<DepositEntity.CataPdBean> list = (List<DepositEntity.CataPdBean>)adapter.getData();
                    for(DepositEntity.CataPdBean cataPdBean:list){
                        cataPdBean.setCheck(false);
                    }
                    if(list.get(position).getSurplus_no()>0){
                        setPopData(popWindow,t,isFreight,t.getCata_pd(),position,image_chart,txt_section,txt_stock,txt_type,txt_limitbuy,numberButton,isAddCart,txt_go,txt_zk_price);
                        ((DepositEntity.CataPdBean) adapter.getData().get(position)).setCheck(true);
                        commodityTagAdapter.notifyDataSetChanged();
                    }

                    break;

            }
        });
        txt_go.setOnClickListener(view -> ToastUitl.show("请选择分类",1000));
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
    public void ProductclassList_v(ProductclassEntity t) {

    }

    @Override
    public void Deposit_v(DepositEntity t) {

    }

    @Override
    public void AddCart_v(BaseResult t) {

    }

    @Override
    public void GoodClick_v(BaseResult t, int linkcount) {
        if (t.getResultCode() == 1) {
            ToastUitl.show(t.getMsg(), 1000);
            Drawable drawable = getResources().getDrawable(R.mipmap.icon_love_selected_yzy);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            txtCollectNum.setCompoundDrawables(drawable,null , null, null);
            txtCollectNum.setText(String.valueOf(++linkcount ));
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

    @OnClick(R.id.image_close)
    public void onViewClicked() {
        finish();
    }
}
