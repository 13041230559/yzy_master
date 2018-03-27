package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.MainActivity;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.IDoEntity;
import com.idolmedia.yzy.ui.adapter.SortAdapter;
import com.idolmedia.yzy.ui.mvp.contract.SelectIdoListContract;
import com.idolmedia.yzy.ui.mvp.model.SelectIdoListModel;
import com.idolmedia.yzy.ui.mvp.presenter.SelectIdoListPresenter;
import com.idolmedia.yzy.utlis.PutUtils;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.sortlist.EditTextWithDel;
import com.idolmedia.yzy.utlis.sortlist.PinyinComparator;
import com.idolmedia.yzy.utlis.sortlist.PinyinUtils;
import com.idolmedia.yzy.utlis.sortlist.SideBar;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.ToastUitl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/16 17:43
 * 描述：
 */

public class SelectStarListActivity extends BaseActivity<SelectIdoListPresenter, SelectIdoListModel> implements SelectIdoListContract.View {
    @BindView(R.id.filter_edit)
    EditTextWithDel filterEdit;
    @BindView(R.id.lv_contact)
    RecyclerView sortListView;
    @BindView(R.id.sidrbar)
    SideBar sideBar;
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
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    @BindView(R.id.login_btn)
    Button loginBtn;
    private SortAdapter adapter;
    private List<IDoEntity.DatasBean> SourceDateList;
    LinearLayoutManager linearLayoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_starlist;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        rightImg.setVisibility(View.INVISIBLE);
        tvTitle.setText(getString(R.string.select_ido));
        HashMap<String, Object> map = new HashMap<>();
        map.put("FKEY", PutUtils.FKEY());
        map.put("virtualuser_id", UserLoginUtil.IsUserId());
        mPresenter.IdoSearch_p(map);
        //initDatas();
        sideBar.setTextView(dialog);
        linearLayoutManager = new LinearLayoutManager(this);
        initEvents();
    }

    private void initEvents() {
        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(s -> {
            //该字母首次出现的位置
            int position = adapter.getPositionForSection(s.charAt(0));
            if (position != -1) {
                linearLayoutManager.scrollToPositionWithOffset(position, 0);
            }
        });

        //根据输入框输入值的改变来过滤搜索
        filterEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

/*    private void initDatas() {
        //   sideBar.setTextView(dialog);
    }

    private void setAdapter() {
        // SourceDateList = filledData(getResources().getStringArray(R.array.contacts));
    }*/

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<IDoEntity.DatasBean> mSortList = new ArrayList<>();
        if (TextUtils.isEmpty(filterStr)) {
            mSortList = SourceDateList;
        } else {
            mSortList.clear();
            for (IDoEntity.DatasBean sortModel : SourceDateList) {
                String name = sortModel.getIdo_name();
                if (name.toUpperCase().indexOf(filterStr.toString().toUpperCase()) != -1 || PinyinUtils.getPingYin(name).toUpperCase().startsWith(filterStr.toString().toUpperCase())) {
                    mSortList.add(sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(mSortList, new PinyinComparator());
        adapter.setNewData(mSortList);
    }

    private List<IDoEntity.DatasBean> filledData(List<IDoEntity.DatasBean> date) {
        List<IDoEntity.DatasBean> mSortList = new ArrayList<>();
        ArrayList<String> indexString = new ArrayList<>();
        for (int i = 0; i < date.size(); i++) {
            IDoEntity.DatasBean sortModel = new IDoEntity.DatasBean();
            sortModel.setIdo_name(date.get(i).getIdo_name());
            sortModel.setIdo_head_img(date.get(i).getIdo_head_img());
            sortModel.setIdol_id(date.get(i).getIdol_id());
            sortModel.setIsAtted(date.get(i).isIsAtted());
            String pinyin = PinyinUtils.getPingYin(date.get(i).getIdo_name());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
                if (!indexString.contains(sortString)) {
                    indexString.add(sortString);
                }
            } else {
                sortModel.setSortLetters("#");
                indexString.add(sortString);
            }
            mSortList.add(sortModel);
        }
        Collections.sort(indexString);
        //sideBar.setIndexText(indexString);
        return mSortList;
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

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.showLong(msg);
    }


    @Override
    public void IdoSearch_v(IDoEntity t) {
        SourceDateList = filledData(t.getDatas());
        //  Log.e("SourceDateList", SourceDateList.size() + "");
        Collections.sort(SourceDateList, new PinyinComparator());
        sortListView.setLayoutManager(linearLayoutManager);
        adapter = new SortAdapter(SourceDateList);
        sortListView.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.iamge_like:
                    HashMap<String, Object> hashMap1 = new HashMap<>();
                    hashMap1.put("virtualuser_id", UserLoginUtil.IsUserId());
                    hashMap1.put("idol_id", ((IDoEntity.DatasBean) adapter.getData().get(position)).getIdol_id());
                    hashMap1.put("attention_type", ((IDoEntity.DatasBean) adapter.getData().get(position)).isIsAtted() ? 1 : 0);
                    hashMap1.put("FKEY", PutUtils.FKEY());
                    mPresenter.FollowIdo_p(hashMap1, position, ((IDoEntity.DatasBean) adapter.getData().get(position)).isIsAtted());
                    break;
            }
        });
    }

    @Override
    public void FollowIdo_v(BaseResult t, int position, boolean isIsAtted) {
        if (t.getResultCode() == 1) {

            ToastUitl.show(t.getMsg(), 1000);
            adapter.getData().get(position).setIsAtted(!isIsAtted);
            adapter.notifyItemChanged(position);

        }

    }

    @OnClick({R.id.image_close, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.login_btn:
                startActivity(MainActivity.class);
                break;
        }
    }
/*
    @OnClick(R.id.image_close,)
    public void onViewClicked() {
        finish();
    }

    @OnClick(R.id.login_btn)
    public void onViewClicked() {
    }*/
}
