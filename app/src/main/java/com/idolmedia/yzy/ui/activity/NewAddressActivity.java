package com.idolmedia.yzy.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.AddressEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.JsonBean;
import com.idolmedia.yzy.ui.mvp.contract.NewAddressContract;
import com.idolmedia.yzy.ui.mvp.model.NewAddressModel;
import com.idolmedia.yzy.ui.mvp.presenter.NewAddressPresenter;
import com.idolmedia.yzy.utlis.UserLoginUtil;
import com.idolmedia.yzy.utlis.PutUtils;
import com.mumu.common.base.BaseActivity;
import com.mumu.common.utils.JsonUtils;
import com.mumu.common.utils.KeyBordUtil;
import com.mumu.common.utils.ToastUitl;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ielse.view.SwitchView;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/11 14:40
 * 描述：  修改/新增/地址
 */

public class NewAddressActivity extends BaseActivity<NewAddressPresenter, NewAddressModel> implements NewAddressContract.View {
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
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.txt_city)
    TextView txtCity;
    @BindView(R.id.ed_detailedaddrss)
    EditText edDetailedaddrss;
    @BindView(R.id.switch_button)
    SwitchView switchButton;
    @BindView(R.id.liner_address)
    LinearLayout linerAddress;
    @BindView(R.id.txt_go)
    TextView txtGo;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private boolean isLoaded = false;
    private boolean IsNew = false;
    private String Cityname="";
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    int isNew;
    AddressEntity.AddressDataBean dataBean;
    int area_id=0;
    @Override
    public int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        tvTitle.setText(getString(R.string.add_address));
        switchButton.setColor(getResources().getColor(R.color.f3ac7c9), getResources().getColor(R.color.f3ac7c9));
        isNew=   getIntent().getIntExtra("isNew",0);
        if(isNew==0){
            tvTitle.setText(getString(R.string.add_address));
        }else {
            tvTitle.setText(getString(R.string.update_address));
            dataBean=getIntent().getParcelableExtra("address");
            edName.setText(dataBean.getConsignee());
            edPhone.setText(dataBean.getPhone());
            txtCity.setText(dataBean.getCity_name().replaceAll("YZY",""));
            Cityname=dataBean.getCity_name();
            edDetailedaddrss.setText(dataBean.getDetail_address());
            switchButton.setOpened(dataBean.getIs_default()==0?true:false);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_close, R.id.txt_go,R.id.txt_city,R.id.switch_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_close:
                finish();
                break;
            case R.id.txt_go:
                if(isNew==0){
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                    hashMap.put("detail_address", edDetailedaddrss.getText().toString().trim());//详细地址
                    hashMap.put("is_default", switchButton.isOpened() ? 0 : 1);
                    hashMap.put("city_name", Cityname);//省市区  格式如下：河北省YZY石家庄市YZY裕华区
                    hashMap.put("consignee", edName.getText().toString().trim());
                    hashMap.put("phone", edPhone.getText().toString().trim());
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("area_id", area_id);
                    mPresenter.NewAddress_p(hashMap);
                }else {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("virtualuser_id", UserLoginUtil.IsUserId());
                    hashMap.put("detail_address", edDetailedaddrss.getText().toString().trim());//详细地址
                    hashMap.put("is_default", switchButton.isOpened() ? 0 : 1);
                    hashMap.put("city_name", Cityname);//省市区  格式如下：河北省YZY石家庄市YZY裕华区
                    hashMap.put("consignee", edName.getText().toString().trim());
                    hashMap.put("phone", edPhone.getText().toString().trim());
                    hashMap.put("FKEY", PutUtils.FKEY());
                    hashMap.put("address_id",dataBean.getAddress_id());
                    hashMap.put("area_id",area_id==0?dataBean.getArea_id():area_id);
                   mPresenter.EditAddress_p(hashMap);
                }

                break;
            case R.id.txt_city:
                KeyBordUtil.hideSoftKeyboard(view);
                mHandler.sendEmptyMessage(MSG_LOAD_DATA);
                if (isLoaded) {
                    ShowPickerView();
                } else {
                    //  Toast.makeText(JsonDataActivity.this,"Please waiting until the data is parsed",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.switch_button:
                if(switchButton.isOpened()){
                    AlertDialog dialog;
                    dialog = new AlertDialog.Builder(this).create();
                    dialog.setMessage("是否确定设置该地址为默认地址?");
                    dialog.setCancelable(false);
                    dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", (dialog1, which) -> {
                        switchButton.setOpened(true);
                    });
                    dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", (dialog12, which) -> {
                        switchButton.setOpened(false);
                    });
                    dialog.show();

                }

                break;
        }
    }

 /*   private HashMap HttpHashMap(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("virtualuser_id", IsCheckLogin.IsUserId());
        hashMap.put("detail_address",edDetailedaddrss.getText().toString().trim());//详细地址
        hashMap.put("is_default", switchButton.isOpened() ? 0 : 1);
        hashMap.put("city_name", Cityname);//省市区  格式如下：河北省YZY石家庄市YZY裕华区
        hashMap.put("consignee", edName.getText().toString().trim());
        hashMap.put("phone", edPhone.getText().toString().trim());
        hashMap.put("FKEY", PutUtils.FKEY());
        hashMap.put("area_id", 110102);

        return hashMap;
    }
*/
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
    public void NewAddress_v(BaseResult t) {
        ToastUitl.show(t.getMsg(),1000);
        if(t.getResultCode()==1){
            setResult(Activity.RESULT_OK);
            finish();
        }

    }


    private void ShowPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            String tx = options1Items.get(options1).getPickerViewText() +
                    options2Items.get(options1).get(options2) +
                    options3Items.get(options1).get(options2).get(options3);
            Cityname=options1Items.get(options1).getPickerViewText()+  options2Items.get(options1).get(options2)+ options3Items.get(options1).get(options2).get(options3);
            area_id=options1Items.get(options1).getCode();
            txtCity.setText(tx);
            //  Toast.makeText(JsonDataActivity.this,tx,Toast.LENGTH_SHORT).show();
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了

                        thread = new Thread(() -> {
                            // 写子线程中的操作,解析省市区数据
                            initJsonData();
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    break;

            }
        }
    };

    private void initJsonData() {//解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = JsonUtils.getJson(this, "city.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getChildren().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getChildren().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getChildren().get(c).getChildren() == null
                        || jsonBean.get(i).getChildren().get(c).getChildren().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getChildren().get(c).getChildren().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getChildren().get(c).getChildren().get(d).getCity_name();

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }


}
