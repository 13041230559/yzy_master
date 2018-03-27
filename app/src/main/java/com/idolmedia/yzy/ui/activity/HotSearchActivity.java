package com.idolmedia.yzy.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.SearchHotEntity;
import com.idolmedia.yzy.ui.adapter.SearchAdapter;
import com.idolmedia.yzy.ui.mvp.contract.HotSearchContract;
import com.idolmedia.yzy.ui.mvp.model.HotSearchModel;
import com.idolmedia.yzy.ui.mvp.presenter.HotSearchPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.SharedPrefsStrListUtil;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/13 13:47
 * 描述：
 */

public class HotSearchActivity extends BaseActivity<HotSearchPresenter, HotSearchModel> implements HotSearchContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;
    TagFlowLayout flowlayout;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.txt_search)
    EditText txtSearch;
    @BindView(R.id.cleartxt_iamge)
    ImageView cleartxtIamge;
    @BindView(R.id.liner_homesearch)
    LinearLayout linerHomesearch;
    @BindView(R.id.right_txt)
    TextView rightTxt;
    @BindView(R.id.right_iamge)
    ImageView rightIamge;
    @BindView(R.id.liner_search)
    LinearLayout linerSearch;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
   ImageView iamgeDel;
    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }


    @Override
    public void initView() {
        rightIamge.setVisibility(View.GONE);
        rightTxt.setVisibility(View.VISIBLE);
        rightTxt.setText("取消");
        rightTxt.setTextSize(13);
        mPresenter.HotSearchList_p(HttpHashMap(0));
        String[] title_name = new String[]{"商品", "认证", "爱豆", "资讯", "活动"};
        for (int i = 0; i < title_name.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(title_name[i]));
        }
        searchAdapter = new SearchAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.setNewData(getKeyword());
        searchAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()){
                case  R.id.iamge_del:
                    removeKeyword((String)adapter.getData().get(position));
                    searchAdapter.setNewData(getKeyword());
               break;
            }
        });
        searchAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle=new Bundle();
            bundle.putString("keywords",(String)adapter.getData().get(position));
            startActivity(SearchResultActivity.class,bundle);
            finish();
        });

        View head_view = View.inflate(this, R.layout.search_title_head, null);
        flowlayout = head_view.findViewById(R.id.flowlayout);
        iamgeDel=head_view.findViewById(R.id.image_reomve);
        searchAdapter.addHeaderView(head_view);
        txtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId== EditorInfo.IME_ACTION_SEND ||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER))
            {
                if(!TextUtils.isEmpty(txtSearch.getText().toString().trim())){
                    setKeyword(txtSearch.getText().toString().trim());
                    Bundle bundle=new Bundle();
                    bundle.putString("keywords",txtSearch.getText().toString().trim());
                    bundle.putString("dictionaries_id","1");
                    startActivity(SearchResultActivity.class,bundle);
                    finish();
                    return true;
                }else {
                    return false;
                }
            }
            return false;
        });
        iamgeDel.setOnClickListener(view -> ShowDialog());
    }
    private void ShowDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("提示");
        dialog.setMessage("是否删除历史记录？");
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", (dialog12, which) -> {
            return;
        });
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", (dialog1, which) -> {
            SharedPrefsStrListUtil.removeStrList(HotSearchActivity.this,"keyword");
            searchAdapter.setNewData(getKeyword());
        });
        dialog.show();
    }

    private  void setKeyword(String key){
        List<String> list;
        list= SharedPrefsStrListUtil.getStrListValue(this,"keyword");
        if(list==null){
            list=new ArrayList<>();
            list.add(key);
            SharedPrefsStrListUtil.putStrListValue(this,"keyword",list);
        }else {
            Set set = new HashSet();
            List newList = new  ArrayList();
            list.add(key);
            for (String cd:list) {
                if(set.add(cd)){
                    newList.add(cd);
                }
            }
            SharedPrefsStrListUtil.putStrListValue(this,"keyword",newList);
        }
    }
    private  List<String> getKeyword(){

        return  SharedPrefsStrListUtil.getStrListValue(this,"keyword");
    }
    private  void removeKeyword(String s){
        SharedPrefsStrListUtil.removeStrListItem(this,"keyword",s);
    }







    private HashMap HttpHashMap(int Position) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("search_type", Position);
        return hashMap;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    private void  HotSearch(SearchHotEntity t,int tabposition){
        if(tabposition==0){
            flowlayout.setAdapter(new TagAdapter(t.getDatas().getProduct()) {
                @Override
                public View getView(FlowLayout parent, int position, Object o) {
                    TextView tv = (TextView) LayoutInflater.from(HotSearchActivity.this).inflate(R.layout.tag_item, flowlayout, false);
                    tv.setTextSize(13);
                    tv.setText(t.getDatas().getProduct().get(position));
                    return tv;
                }
            });

        }else if(tabposition==1){
            flowlayout.setAdapter(new TagAdapter(t.getDatas().getAuth()) {
                @Override
                public View getView(FlowLayout parent, int position, Object o) {
                    TextView tv = (TextView) LayoutInflater.from(HotSearchActivity.this).inflate(R.layout.tag_item, flowlayout, false);
                    tv.setTextSize(13);
                    tv.setText(t.getDatas().getAuth().get(position));
                    return tv;
                }
            });


        }else if(tabposition==2){

            flowlayout.setAdapter(new TagAdapter(t.getDatas().getIdou()) {
                @Override
                public View getView(FlowLayout parent, int position, Object o) {
                    TextView tv = (TextView) LayoutInflater.from(HotSearchActivity.this).inflate(R.layout.tag_item, flowlayout, false);
                    tv.setTextSize(13);
                    tv.setText(t.getDatas().getIdou().get(position));
                    return tv;
                }
            });


        }else if(tabposition==3){
            flowlayout.setAdapter(new TagAdapter(t.getDatas().getInfomation()) {
                @Override
                public View getView(FlowLayout parent, int position, Object o) {
                    TextView tv = (TextView) LayoutInflater.from(HotSearchActivity.this).inflate(R.layout.tag_item, flowlayout, false);
                    tv.setTextSize(13);
                    tv.setText(t.getDatas().getInfomation().get(position));
                    return tv;
                }
            });

        }else if(tabposition==4){
            flowlayout.setAdapter(new TagAdapter(t.getDatas().getActivity()) {
                @Override
                public View getView(FlowLayout parent, int position, Object o) {
                    TextView tv = (TextView) LayoutInflater.from(HotSearchActivity.this).inflate(R.layout.tag_item, flowlayout, false);
                    tv.setTextSize(13);
                    tv.setText(t.getDatas().getActivity().get(position));
                    return tv;
                }
            });
        }
        flowlayout.setOnTagClickListener((view, position, parent) -> {
            setKeyword(flowlayout.getAdapter().getItem(position).toString());
            Bundle bundle=new Bundle();
            bundle.putString("keywords",flowlayout.getAdapter().getItem(position).toString());
            bundle.putInt("tabposition",tabposition);
            startActivity(SearchResultActivity.class,bundle);
            finish();
            return true;
        });
    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.show(msg, 1000);
    }

    @Override
    public void HotSearchList_v(SearchHotEntity t) {
        if (t.getResultCode() == 1) {
            HotSearch(t,0);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    HotSearch(t,tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        }
    }

    @OnClick(R.id.right_txt)
    public void onViewClicked() {
        finish();
    }
}
