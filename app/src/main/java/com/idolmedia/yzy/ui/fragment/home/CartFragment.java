package com.idolmedia.yzy.ui.fragment.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CartEntity;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.entity.FreightEntity;
import com.idolmedia.yzy.ui.activity.CommodityDetailsActivity;
import com.idolmedia.yzy.ui.activity.SubmitOrderCartActivity;
import com.idolmedia.yzy.ui.adapter.CartAdapter;
import com.idolmedia.yzy.ui.adapter.InvalidAdapter;
import com.idolmedia.yzy.ui.adapter.RecommendAdapter;
import com.idolmedia.yzy.ui.mvp.contract.CartContract;
import com.idolmedia.yzy.ui.mvp.model.CartModel;
import com.idolmedia.yzy.ui.mvp.presenter.CartPresenter;
import com.idolmedia.yzy.utlis.DividerGridItemDecoration;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;
import com.mumu.common.base.BaseFragment;
import com.mumu.common.utils.DisplayUtil;
import com.mumu.common.utils.FormatUtil;
import com.mumu.common.utils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/31.
 */

public class CartFragment extends BaseFragment<CartPresenter,CartModel>implements CartContract.View, CartAdapter.CheckInterface, CartAdapter.ModifyCountInterface, CartAdapter.GroupEditorListener {


    @BindView(R.id.tile_view)
    View tileView;
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
    @BindView(R.id.expand_listview)
    ExpandableListView expandListview;
    @BindView(R.id.select_all)
    CheckBox selectAll;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_totalnuber)
    TextView txtTotalnuber;
    Unbinder unbinder;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    @BindView(R.id.ll_cart)
    LinearLayout llCart;
    @BindView(R.id.layout_empty_shopcart)
    LinearLayout empty;
    private double mtotalPrice = 0.00;
    private int mtotalCount = 0;
    //false就是编辑，ture就是完成
    private boolean flag;
    private List<CartEntity.CartDataBean> groups; //组元素的列表
    //private Map<String, List<GoodsInfo>> childs; //子元素的列表
    private CartAdapter cartAdapter;
    Link link;
    RecyclerView footer_recycler,invalidView_recycler;
    String cartItemId,nickId;
    List<FreightEntity> freList;
    private  boolean updateCartNumber;

    int groupPosition,childPosition;
    View view,InvalidView,customView,empty_view;
    InvalidAdapter invalidAdapter;
    TextView InvalidNumber,ClearInvalid;
    RecyclerView InvalidRecycler;
    RecommendAdapter recommendAdapter;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_shopcart;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        DisplayUtil.setWidgetHeight(tileView, DisplayUtil.getStatusBarHeight(getActivity()));
        tileView.setBackgroundColor(getResources().getColor(R.color.main_color));
        imageClose.setVisibility(View.INVISIBLE);
        rightTxt.setVisibility(View.VISIBLE);
        rightImg.setVisibility(View.GONE);
        tvTitle.setText("购物车");
        rightTxt.setText("编辑");
        link = new Link("￥0.0")
                .setTextColor(getResources().getColor(R.color.fc4f4f))                  // optional, defaults to holo blue
                .setHighlightAlpha(.4f)                                     // optional, defaults to .15f
                .setUnderlined(false)
                .setBold(false);
        LinkBuilder.on(txtPrice)
                .addLink(link)
                .build(); // creat
         empty_view=View.inflate(getActivity(), R.layout.empty_cart_view, null);
         ImageView imgNullCart=empty_view.findViewById(R.id.iamge_no_view);
         TextView  txtNullCart=empty_view.findViewById(R.id.txt_no_data);
        imgNullCart.setImageResource(R.mipmap.icon_kong_car_yzy);
        txtNullCart.setText("购物车空空的,快去逛逛吧!");
        InvalidView = View.inflate(getActivity(), R.layout.invalid_recyclerview, null);
        InvalidRecycler=InvalidView.findViewById(R.id.recyclerView);
        InvalidNumber=InvalidView.findViewById(R.id.txt_invalid_number);
        ClearInvalid=InvalidView.findViewById(R.id.txt_invalid_clear);
        expandListview.addFooterView(InvalidView);
       // expandListview.addHeaderView(empty_view);
        customView = View.inflate(getActivity(), R.layout.include_guesslike_recyclerview, null);
        expandListview.addFooterView(customView);
        footer_recycler = customView.findViewById(R.id.recyclerView);
        invalidAdapter=new InvalidAdapter();
        InvalidRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        InvalidRecycler.setAdapter(invalidAdapter);
        InvalidRecycler.setNestedScrollingEnabled(false);

        recommendAdapter= new RecommendAdapter(R.layout.recommend_item);
        footer_recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        footer_recycler.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        footer_recycler.setNestedScrollingEnabled(false);
        footer_recycler.setAdapter(recommendAdapter);
        recommendAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("shop_type",((CommodityEntity.DatasBean)adapter.getData().get(position)).getShop_type());
            bundle.putString("shopcommon_id", String.valueOf(((CommodityEntity.DatasBean)adapter.getData().get(position)).getShopcommon_id()));
            startActivity(CommodityDetailsActivity.class, bundle);

        });

    }

    private void initEvents(List<CartEntity.CartDataBean> list) {
        //  actionBarEdit.setOnClickListener(this);
        cartAdapter = new CartAdapter(getActivity());
        cartAdapter.setCheckInterface(this);//关键步骤1：设置复选框的接口
        cartAdapter.setModifyCountInterface(this); //关键步骤2:设置增删减的接口
        cartAdapter.setGroupEditorListener(this);//关键步骤3:监听组列表的编辑状态
        expandListview.setGroupIndicator(null); //设置属性 GroupIndicator 去掉向下箭头
        cartAdapter.setNewData(list);
        expandListview.setAdapter(cartAdapter);
        for (int i = 0; i < cartAdapter.getGroupCount(); i++) {
            expandListview.expandGroup(i); //关键步骤4:初始化，将ExpandableListView以展开的方式显示
        }
        expandListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int firstVisiablePostion = view.getFirstVisiblePosition();
                int top = -1;
                View firstView = view.getChildAt(firstVisibleItem);
                //  UtilsLog.i("childCount="+view.getChildCount());//返回的是显示层面上的所包含的子view的个数
                if (firstView != null) {
                    top = firstView.getTop();
                }
                //  UtilsLog.i("firstVisiableItem="+firstVisibleItem+",fistVisiablePosition="+firstVisiablePostion+",firstView="+firstView+",top="+top);
                if (firstVisibleItem == 0 && top == 0) {
                    //    mPtrFrame.setEnabled(true);
                } else {
                    //    mPtrFrame.setEnabled(false);
                }
            }
        });
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

    /**
     * 设置购物车的数量
     */
    private void setCartNum() {
        int count = 0;
        for (int i = 0; i < groups.size(); i++) {
            CartEntity.CartDataBean group = groups.get(i);
            group.setCheck(selectAll.isChecked());
            List<CartEntity.CartDataBean.CartsItemsBean> Childs = group.getCartsItems();
            for (CartEntity.CartDataBean.CartsItemsBean childs : Childs) {
                count++;
            }
        }

        //购物车已经清空
        if (count == 0) {
            clearCart();
        } else {
            //  shoppingcatNum.setText("购物车(" + count + ")");
        }

    }




    private void clearCart() {
       /* shoppingcatNum.setText("购物车(0)");
        actionBarEdit.setVisibility(View.GONE);
        llCart.setVisibility(View.GONE);
        empty_shopcart.setVisibility(View.VISIBLE);//这里发生过错误*/
    }

    /**
     * 删除操作
     * 1.不要边遍历边删除,容易出现数组越界的情况
     * 2.把将要删除的对象放进相应的容器中，待遍历完，用removeAll的方式进行删除
     */
    private void doDelete() {

        StringBuilder delBuilder=new StringBuilder();

        List<CartEntity.CartDataBean> toBeDeleteGroups = new ArrayList<>(); //待删除的组元素
        for (int i = 0; i < groups.size(); i++) {
            CartEntity.CartDataBean group = groups.get(i);
            if (group.isCheck()) {
                toBeDeleteGroups.add(group);
            }
            List<CartEntity.CartDataBean.CartsItemsBean> toBeDeleteChilds = new ArrayList<>();//待删除的子元素
            List<CartEntity.CartDataBean.CartsItemsBean> child = group.getCartsItems();
            for (int j = 0; j < child.size(); j++) {
                if (child.get(j).isCheck()) {
                    //  toBeDeleteChilds.add(child.get(j));
                    delBuilder.append(child.get(j).getCartitems_id()+",");
                }
            }

            //  child.removeAll(toBeDeleteChilds);
        }

        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("cartitems_ids",delBuilder.toString().substring(0,delBuilder.toString().lastIndexOf(",")).toString());
        mPresenter.DelCart_p(hashMap);
/*
      //  groups.removeAll(toBeDeleteGroups);
        //重新设置购物车
        setCartNum();
        cartAdapter.notifyDataSetChanged();*/
    }

    /**
     * @return 判断组元素是否全选
     */
    private boolean isCheckAll() {
        for (CartEntity.CartDataBean group : groups) {
            if (!group.isCheck()) {
                return false;
            }
        }
        return true;
    }


    private void setVisiable() {
        if (flag) {
            rightTxt.setText("完成");
            txtTotalnuber.setText("删除(" + mtotalCount + ")");
            txtTotalnuber.setBackgroundColor(getResources().getColor(R.color.main_color));
            txtPrice.setVisibility(View.INVISIBLE);
        } else {
            rightTxt.setText("编辑");
            txtTotalnuber.setText("结算(" + mtotalCount + ")");
            txtTotalnuber.setBackgroundColor(getResources().getColor(R.color.orange));
            txtPrice.setVisibility(View.VISIBLE);
        }
    }
    /**
     * 计算商品总价格，操作步骤
     * 1.先清空全局计价,计数
     * 2.遍历所有的子元素，只要是被选中的，就进行相关的计算操作
     * 3.给textView填充数据
     */
    private void calulate() {
        mtotalPrice = 0.00;
        mtotalCount = 0;
        freList=new ArrayList<>();
        FreightEntity freightEntity;
        List<FreightEntity.DatasBean>  datasBeanList;
        StringBuilder  cartItemIdBuilder = new StringBuilder();
        for (int i = 0; i < groups.size(); i++) {
            CartEntity.CartDataBean group = groups.get(i);
            // List<GoodsInfo> child = childs.get(group.getId());
            List<CartEntity.CartDataBean.CartsItemsBean> child = group.getCartsItems();
            datasBeanList=new ArrayList<>();
            freightEntity=new FreightEntity();
            freightEntity.setFrom_id(String.valueOf(group.getFrom_id()));
            for (int j = 0; j < child.size(); j++) {
                CartEntity.CartDataBean.CartsItemsBean good = child.get(j);
                if (good.isCheck()) {
                    cartItemIdBuilder.append(good.getCartitems_id()+",");
                    Log.e("cartItemIdBuilder",cartItemIdBuilder.toString());
                    //获取订单详情
                    cartItemId= cartItemIdBuilder.toString().substring(0,cartItemIdBuilder.toString().lastIndexOf(",")).toString();
                    FreightEntity.DatasBean datasBean=new FreightEntity.DatasBean();
                    datasBean.setCartItems_id(String.valueOf(good.getCartitems_id()));
                    datasBeanList.add(datasBean);
                    mtotalCount++;
                    mtotalPrice += good.getCurrent_price() * good.getQuantity_count();

                }
                freightEntity.setShop(datasBeanList);
            }
            freList.add(freightEntity);
        }

        txtPrice.setText( String.format(getString(R.string.total), FormatUtil.FormatPirce(mtotalPrice)));
        if (flag) {
            rightTxt.setText("完成");
            txtTotalnuber.setText("删除(" + mtotalCount + ")");
        } else {
            rightTxt.setText("编辑");
            txtTotalnuber.setText("结算(" + mtotalCount + ")");
        }
        if (mtotalCount == 0) {
            setCartNum();
        } else {
            //shoppingcatNum.setText("购物车(" + mtotalCount + ")");
        }

        link = new Link("￥" +FormatUtil.FormatPirce(mtotalPrice))
                .setTextColor(getResources().getColor(R.color.fc4f4f))                  // optional, defaults to holo blue
                .setHighlightAlpha(.4f)                                     // optional, defaults to .15f
                .setUnderlined(false)
                .setBold(false);
        LinkBuilder.on(txtPrice)
                .addLink(link)
                .build(); // creat

    }


    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        CartEntity.CartDataBean group = groups.get(groupPosition);
        //    List<GoodsInfo> child = childs.get(group.getId());
        List<CartEntity.CartDataBean.CartsItemsBean> child = group.getCartsItems();
        for (int i = 0; i < child.size(); i++) {
            // child.get(i).setIs_check(isChecked?1:2);
            child.get(i).setCheck(isChecked);
        }
        if (isCheckAll()) {
            selectAll.setChecked(true);//全选
        } else {
            selectAll.setChecked(false);//反选
        }
        cartAdapter.notifyDataSetChanged();
        calulate();


    }

    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        boolean allChildSameState = true; //判断该组下面的所有子元素是否处于同一状态
        // StoreInfo group = groups.get(groupPosition);
        //List<GoodsInfo> child = childs.get(group.getId());
        CartEntity.CartDataBean group = groups.get(groupPosition);
        //    List<GoodsInfo> child = childs.get(group.getId());
        List<CartEntity.CartDataBean.CartsItemsBean> child = group.getCartsItems();
        for (int i = 0; i < child.size(); i++) {
            //不选全中
            if ((child.get(i).isCheck()) != isChecked) {
                allChildSameState = false;
                break;
            }
        }

        if (allChildSameState) {
            group.setCheck(isChecked);//如果子元素状态相同，那么对应的组元素也设置成这一种的同一状态
        } else {
            group.setCheck(false);//否则一律视为未选中
        }

        if (isCheckAll()) {
            selectAll.setChecked(true);//全选
        } else {
            selectAll.setChecked(false);//反选
        }

        cartAdapter.notifyDataSetChanged();
        calulate();
    }

    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        //CartEntity.CartDataBean item= (CartEntity.CartDataBean) cartAdapter.getGroup(groupPosition);
        CartEntity.CartDataBean.CartsItemsBean good = (CartEntity.CartDataBean.CartsItemsBean) cartAdapter.getChild(groupPosition, childPosition);
        HashMap  hashMap =new HashMap();
        updateCartNumber=true;
        this.groupPosition=groupPosition;
        this.childPosition=childPosition;
        view=showCountView;
        int count = good.getQuantity_count();
        count++;
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("cartitems_id",good.getCartitems_id());
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("count",count);
        mPresenter.UpdateCart_p(hashMap,groupPosition,childPosition,showCountView,String.valueOf(count),String.valueOf(count));

    }
    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        CartEntity.CartDataBean.CartsItemsBean good = (CartEntity.CartDataBean.CartsItemsBean) cartAdapter.getChild(groupPosition, childPosition);
        int count = good.getQuantity_count();
        if (count == 1) {
            return;
        }
        updateCartNumber=false;
        this.groupPosition=groupPosition;
        this.childPosition=childPosition;
        view=showCountView;
        HashMap  hashMap =new HashMap();
        count--;
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("cartitems_id",good.getCartitems_id());
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("count",count);
        mPresenter.UpdateCart_p(hashMap,groupPosition,childPosition,showCountView,String.valueOf(count),String.valueOf(count));
    }


    @Override
    public void doUpdate(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        CartEntity.CartDataBean.CartsItemsBean good = (CartEntity.CartDataBean.CartsItemsBean) cartAdapter.getChild(groupPosition, childPosition);
      /*
        UtilsLog.i("进行更新数据，数量" + count + "");
        ((TextView) showCountView).setText(String.valueOf(count));
        cartAdapter.notifyDataSetChanged();
        calulate();*/
        int count = good.getQuantity_count();
        HashMap  hashMap =new HashMap();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("cartitems_id",good.getCartitems_id());
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("count",count++);
        mPresenter.UpdateCart_p(hashMap,groupPosition,childPosition,showCountView,String.valueOf(count),String.valueOf(count));
    }

    @Override
    public void setNumber(int groupPosition, int childPosition, View showCountView, boolean isChecked, String setnumber) {
        CartEntity.CartDataBean.CartsItemsBean good = (CartEntity.CartDataBean.CartsItemsBean) cartAdapter.getChild(groupPosition, childPosition);
        int count = good.getQuantity_count();
        HashMap  hashMap =new HashMap();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("cartitems_id",good.getCartitems_id());
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("count",setnumber);
        mPresenter.UpdateCart_p(hashMap,groupPosition,childPosition,showCountView,String.valueOf(count),setnumber);
    }

    @Override
    public void onItemClick(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        if (!isChecked) {
            CartEntity.CartDataBean.CartsItemsBean good = (CartEntity.CartDataBean.CartsItemsBean) cartAdapter.getChild(groupPosition, childPosition);
            Bundle bundle = new Bundle();
            bundle.putString("shop_type",good.getShop_type());
            bundle.putString("shopcommon_id", String.valueOf(good.getShopcommon_id()));
            startActivity(CommodityDetailsActivity.class, bundle);
        }
    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {

    }

    @Override
    public void groupEditor(int groupPosition) {

    }


    @Override
    public void onResume() {
        super.onResume();
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.CartList_p(hashMap);
        hashMap=new HashMap<>();
        hashMap.put("pageNo",1);
        hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
        hashMap.put("pageSize",10);
        hashMap.put("FKEY", PutUtils.FKEY());
        mPresenter.RecommendCartList_p(hashMap);

    }

    /**
     * 全选和反选
     * 错误标记：在这里出现过错误
     */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            //  StoreInfo group = groups.get(i);
            CartEntity.CartDataBean group = groups.get(i);
            group.setCheck(selectAll.isChecked());
            // List<GoodsInfo> child = childs.get(group.getId());
            List<CartEntity.CartDataBean.CartsItemsBean> child = group.getCartsItems();
            for (int j = 0; j < child.size(); j++) {
                child.get(j).setCheck(selectAll.isChecked());//这里出现过错误
            }
        }
        cartAdapter.notifyDataSetChanged();
        calulate();
    }

    @OnClick({R.id.right_txt, R.id.select_all, R.id.txt_totalnuber})
    public void onViewClicked(View view) {
        AlertDialog dialog;
        switch (view.getId()) {
            case R.id.right_txt:
                flag = !flag;
                setVisiable();
                break;
            case R.id.select_all:
                doCheckAll();
                break;
            case R.id.txt_totalnuber:
                if (flag) {
                    if (mtotalCount == 0) {
                        ToastUitl.show("请选择要删除的商品", 1000);
                        return;
                    }
                    dialog = new AlertDialog.Builder(getActivity()).create();
                    dialog.setMessage("确认要删除该商品吗?");
                    dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", (dialog1, which) -> doDelete());
                    dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", (dialog12, which) -> {
                        return;
                    });
                    dialog.show();
                } else {
                    if (mtotalCount == 0) {
                        ToastUitl.show("请选择要支付的商品", 1000);
                        return;
                    }
                    Gson gson=new Gson();
                    Bundle bundle=new Bundle();
                    bundle.putString("cartitemid",cartItemId);
                    bundle.putString("freightData", gson.toJson(freList) );
                    startActivity(SubmitOrderCartActivity.class,bundle);
                    //ToastUitl.show("跳转到支付页面", 1000);

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
        ToastUitl.show(msg,1000);
    }

    @Override
    public void CartList_v(CartEntity t) {
        if(t.getResultCode()==1){
            selectAll.setChecked(false);
            initEvents(t.getCartData());
            groups=cartAdapter.ListCartSize();
            flag=false;
            calulate();
            txtTotalnuber.setBackgroundColor(getResources().getColor(R.color.orange));
            txtPrice.setVisibility(View.VISIBLE);
            if(t.getCartData().size()!=0){
                expandListview.removeHeaderView(empty_view);
            }else {
                expandListview.removeHeaderView(empty_view);
                expandListview.addHeaderView(empty_view);
            }

            if(t.getInvalidCartsData().size()>0){
                InvalidNumber.setText(String.format(getString(R.string.invalid_number),t.getInvalidCartsData().size()));
                invalidAdapter.setNewData(t.getInvalidCartsData());
                ClearInvalid.setOnClickListener(view -> {
                    HashMap<String,Object> hashMap=new HashMap<>();
                    hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("cartitems_ids",ClearInvalid(t.getInvalidCartsData()));
                    mPresenter.ClearInvalid_p(hashMap);
                });
            }else {
                expandListview.removeFooterView(InvalidView);
            }

        }

    }

    private  String ClearInvalid(List<CartEntity.InvalidCartsDataBean> invalid){
        StringBuilder invalidBuilder = new StringBuilder();
        for(CartEntity.InvalidCartsDataBean invalidBean:invalid){
            invalidBuilder.append(invalidBean.getCartitems_id()+",");
            // cartItemIdBuilder.toString().substring(0,cartItemIdBuilder.toString().lastIndexOf(",")).toString();

        }


        return invalidBuilder.toString().substring(0,invalidBuilder.toString().lastIndexOf(",")).toString();
    }

    @Override
    public void UpdateCart_v(BaseResult t, int groupPosition, int childPosition, View showCountView, String number, String setNumber) {

        if(t.getResultCode()==1){
            ToastUitl.show("修改成功",1000);
            CartEntity.CartDataBean.CartsItemsBean good = (CartEntity.CartDataBean.CartsItemsBean) cartAdapter.getChild(groupPosition, childPosition);
            //   ((TextView)showCountView).setText(setNumber);
            good.setQuantity_count(Integer.parseInt(setNumber));
            cartAdapter.notifyDataSetChanged();
            calulate();
        }else {
            ToastUitl.show(t.getMsg(),1000);
        }
    }


    @Override
    public void DelCart_v(BaseResult t) {
        ToastUitl.show(t.getMsg(),1000);
        if(t.getResultCode()==1){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
            hashMap.put("FKEY", PutUtils.FKEY());
            mPresenter.CartList_p(hashMap);

        }
    }

    @Override
    public void ClearInvalid_v(BaseResult t) {
        if(t.getResultCode()==1){
            expandListview.removeFooterView(InvalidView);
        }else {
            ToastUitl.show(t.getMsg(),1000);
        }
    }

    @Override
    public void RecommendCartList_v(CommodityEntity t) {
        if(t.getResultCode()==1){
            if(t.getDatas().size()>0){
                recommendAdapter.setNewData(t.getDatas());
            }else {
                expandListview.removeFooterView(customView);
            }

        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
            hashMap.put("FKEY", PutUtils.FKEY());
            mPresenter.CartList_p(hashMap);
        }else {
            if(flag){
                flag=false;
            }
            rightTxt.setText("编辑");
            txtTotalnuber.setText("结算(" + mtotalCount + ")");
            selectAll.setChecked(false);
        }
    }



}
