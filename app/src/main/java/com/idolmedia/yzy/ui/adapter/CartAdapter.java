package com.idolmedia.yzy.ui.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.CartEntity;
import com.idolmedia.yzy.entity.GoodsInfo;
import com.idolmedia.yzy.entity.StoreInfo;
import com.idolmedia.yzy.utlis.RadiusBackgroundSpan;
import com.idolmedia.yzy.utlis.TitleTipUtils;
import com.idolmedia.yzy.utlis.UtilTool;
import com.idolmedia.yzy.utlis.UtilsLog;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/26.
 * 购物车适配器
 */

public class CartAdapter extends BaseExpandableListAdapter {
    private List<CartEntity.CartDataBean> groups;
    private List<CartEntity.CartDataBean.CartsItemsBean> childrens;
    //这个String对应着StoreInfo的Id，也就是店铺的Id
  //  private Map<String, List<GoodsInfo>> childrens;
    private Context mcontext;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    private GroupEditorListener groupEditorListener;
    private int count = 0;
    private boolean flag = true; //组的编辑按钮是否可见，true可见，false不可见

    public CartAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }
    public  void  setNewData(List<CartEntity.CartDataBean> groups){
             this.groups=groups;
             notifyDataSetChanged();

    }
    public  void  setAddData(List<CartEntity.CartDataBean> groups){
        groups.addAll(groups);

    }
    public List<CartEntity.CartDataBean> ListCartSize(){

        return groups;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
      //  String groupId = groups.get(groupPosition).getFrom_id();
        return groups.get(groupPosition).getCartsItems().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
      //  List<GoodsInfo> childs = childrens.get(groups.get(groupPosition).getId());
        List<CartEntity.CartDataBean.CartsItemsBean> childs=groups.get(groupPosition).getCartsItems();
        return childs.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = View.inflate(mcontext, R.layout.item_shopcat_group, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        /*if (groupPosition == 0) {
            groupViewHolder.linerView.setVisibility(View.GONE);
        }*/
        final CartEntity.CartDataBean group = (CartEntity.CartDataBean) getGroup(groupPosition);
        groupViewHolder.storeName.setText(group.getFrom_name());
        groupViewHolder.storeCheckBox.setOnClickListener(v -> {
            group.setCheck(((CheckBox) v).isChecked());
            checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());
        });
        groupViewHolder.storeCheckBox.setChecked(group.isCheck());
/*
        *//**【文字指的是组的按钮的文字】
         * 当我们按下ActionBar的 "编辑"按钮， 应该把所有组的文字显示"编辑",并且设置按钮为不可见
         * 当我们完成编辑后，再把组的编辑按钮设置为可见
         * 不懂，请自己操作淘宝，观察一遍
         *//*
        if(group.isActionBarEditor()){
            group.setEditor(false);
            groupViewHolder.storeEdit.setVisibility(View.GONE);
            flag=false;
        }else{
            flag=true;
            groupViewHolder.storeEdit.setVisibility(View.VISIBLE);
        }

        *//**
         * 思路:当我们按下组的"编辑"按钮后，组处于编辑状态，文字显示"完成"
         * 当我们点击“完成”按钮后，文字显示"编辑“,组处于未编辑状态
         *//*
        if (group.isEditor()) {
            groupViewHolder.storeEdit.setText("完成");
        } else {
            groupViewHolder.storeEdit.setText("编辑");
        }
        groupViewHolder.storeEdit.setOnClickListener(new GroupViewClick(group, groupPosition, groupViewHolder.storeEdit));*/
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(mcontext, R.layout.item_shopcat_product, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        /**
         * 根据组的编辑按钮的可见与不可见，去判断是组对下辖的子元素编辑  还是ActionBar对组的下瞎元素的编辑
         * 如果组的编辑按钮可见，那么肯定是组对自己下辖元素的编辑
         * 如果组的编辑按钮不可见，那么肯定是ActionBar对组下辖元素的编辑
         */
/*        if (flag) {
            if (groups.get(groupPosition).isEditor()) {
                childViewHolder.editGoodsData.setVisibility(View.VISIBLE);
                childViewHolder.delGoods.setVisibility(View.VISIBLE);
                childViewHolder.goodsData.setVisibility(View.GONE);
            } else {
                childViewHolder.delGoods.setVisibility(View.VISIBLE);
                childViewHolder.goodsData.setVisibility(View.VISIBLE);
                childViewHolder.editGoodsData.setVisibility(View.GONE);
            }
        } else {

            if (groups.get(groupPosition).isActionBarEditor()) {
                childViewHolder.delGoods.setVisibility(View.GONE);
                childViewHolder.editGoodsData.setVisibility(View.VISIBLE);
                childViewHolder.goodsData.setVisibility(View.GONE);
            } else {
                childViewHolder.delGoods.setVisibility(View.VISIBLE);
                childViewHolder.goodsData.setVisibility(View.VISIBLE);
                childViewHolder.editGoodsData.setVisibility(View.GONE);
            }
        }*/


        final CartEntity.CartDataBean.CartsItemsBean child = (CartEntity.CartDataBean.CartsItemsBean) getChild(groupPosition, childPosition);
        if (child != null) {
         /*   if(child.getShop_label()!=null) {
                if (child.getShop_label().equals("海外直邮")) {
                    SpannableStringBuilder spannbulider = new SpannableStringBuilder(child.getShop_label() + "  " + child.getShop_name());
                    spannbulider.setSpan(new RadiusBackgroundSpan(mcontext.getResources().getColor(R.color.violet), mcontext.getResources().getColor(R.color.white), 30, 25), 0, child.getShop_label().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    childViewHolder.txtTitle.setText(spannbulider);
                } else {
                    SpannableStringBuilder spannbulider = new SpannableStringBuilder(child.getShop_label() + "  " + child.getShop_name());
                    spannbulider.setSpan(new RadiusBackgroundSpan(mcontext.getResources().getColor(R.color.main_color), mcontext.getResources().getColor(R.color.white), 30, 25), 0, child.getShop_label().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    childViewHolder.txtTitle.setText(spannbulider);

                }
            }*/

            if(child.getShop_label()!=null){
                if(child.getShop_label().equals("海外直邮")){
                    childViewHolder.txtTitle.setText(TitleTipUtils.titleTipUtils(mcontext,child.getShop_label(),child.getShop_name(),12,14,35,R.color.violet));
                }else if(child.getShop_label().equals("娱自营")){
                    childViewHolder.txtTitle.setText(TitleTipUtils.titleTipUtils(mcontext,child.getShop_label(),child.getShop_name(),12,14,35,R.color.main_color));
                }else {
                    childViewHolder.txtTitle.setText(TitleTipUtils.titleTipUtils(mcontext,child.getShop_label(),child.getShop_name(),12,14,35,R.color.ffc244));
                }
            }else {
                childViewHolder.txtTitle.setText(child.getShop_name());
            }
         //   childViewHolder.txtTitle.setText(child.getShop_label()+child.getShop_name());
            childViewHolder.txtZPrice.setText("￥" + child.getCurrent_price());
            childViewHolder.txtYPrice.setText("￥" + child.getOriginal_price());
            childViewHolder.txtYPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
            childViewHolder.dialogNum.setText(String.valueOf(child.getQuantity_count()));
            Glide.with(mcontext).load(child.getCatalog_img()).apply(new RequestOptions().error(R.mipmap.default_bg)).into(childViewHolder.goodsImage);
            childViewHolder.txtSubtitle.setText(child.getCatalog_title());

            childViewHolder.singleCheckBox.setChecked(child.isCheck());
            childViewHolder.singleCheckBox.setOnClickListener(v -> {
                child.setCheck((((CheckBox) v).isChecked()));
                childViewHolder.singleCheckBox.setChecked(((CheckBox) v).isChecked());
                checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());
            });
            childViewHolder.singleCheckBox.setOnCheckedChangeListener((compoundButton, b) -> Log.e("compoundButton",String.valueOf(b)));

            childViewHolder.dialogIncreaseNum.setOnClickListener(v -> modifyCountInterface.doIncrease(groupPosition, childPosition, childViewHolder.dialogNum, childViewHolder.singleCheckBox.isChecked()));
            childViewHolder.dialogReduceNum.setOnClickListener(v -> modifyCountInterface.doDecrease(groupPosition, childPosition, childViewHolder.dialogNum, childViewHolder.singleCheckBox.isChecked()));
           childViewHolder.linerItem.setOnClickListener(view -> modifyCountInterface.onItemClick(groupPosition,childPosition,childViewHolder.dialogNum,childViewHolder.singleCheckBox.isChecked()));

          childViewHolder.dialogNum.setOnClickListener(view -> {
                      showDialog(groupPosition,childPosition,childViewHolder.dialogNum,childViewHolder.singleCheckBox.isChecked(),child);
          });

   /*   childViewHolder.dialogNum.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                      Log.e("beforeTextChanged",charSequence.toString().trim());
               }

               @Override
               public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 Log.e("onTextChanged",charSequence.toString().trim());
                     modifyCountInterface.setNumber(groupPosition,childPosition,childViewHolder.dialogNum,childViewHolder.singleCheckBox.isChecked(),charSequence.toString().trim());
               }

               @Override
               public void afterTextChanged(Editable editable) {

               }
           });*/
           // childViewHolder.goodsNum.setOnClickListener((View.OnClickListener) v -> showDialog(groupPosition,childPosition,childViewHolder.goodsNum,childViewHolder.singleCheckBox.isChecked(),child));

        }
        return convertView;
    }

    /**
     * @param groupPosition
     * @param childPosition
     * @param showCountView
     * @param isChecked
     * @param child
     */
    private void showDialog(final int groupPosition, final int childPosition, final View showCountView, final boolean isChecked, final CartEntity.CartDataBean.CartsItemsBean child) {
        final AlertDialog.Builder alertDialog_Builder = new AlertDialog.Builder(mcontext);
        View view = LayoutInflater.from(mcontext).inflate(R.layout.dialog_change_num, null);
        final AlertDialog dialog = alertDialog_Builder.create();
        dialog.setView(view);//errored,这里是dialog，不是alertDialog_Buidler
        count = child.getQuantity_count();
        final EditText num = view.findViewById(R.id.dialog_num);
        num.setText(count + "");
        //自动弹出键盘
        dialog.setOnShowListener(dialog1 -> UtilTool.showKeyboard(mcontext, showCountView));
        final TextView increase = (TextView) view.findViewById(R.id.dialog_increaseNum);
        final TextView DeIncrease = (TextView) view.findViewById(R.id.dialog_reduceNum);
        final TextView pButton = (TextView) view.findViewById(R.id.dialog_Pbutton);
        final TextView nButton = (TextView) view.findViewById(R.id.dialog_Nbutton);
        nButton.setOnClickListener(v -> dialog.dismiss());
        pButton.setOnClickListener(v -> {
            int number = Integer.parseInt(num.getText().toString().trim());
            if (number == 0) {
                dialog.dismiss();
            } else {
                UtilsLog.i("数量=" + number + "");
                num.setText(String.valueOf(number));
                child.setQuantity_count(number);
                modifyCountInterface.doUpdate(groupPosition, childPosition, showCountView, isChecked);
                dialog.dismiss();
            }
        });
        increase.setOnClickListener(v -> {
            count++;
            num.setText(String.valueOf(count));
        });
        DeIncrease.setOnClickListener(v -> {
            if (count > 1) {
                count--;
                num.setText(String.valueOf(count));
            }
        });
        dialog.show();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    public GroupEditorListener getGroupEditorListener() {
        return groupEditorListener;
    }

    public void setGroupEditorListener(GroupEditorListener groupEditorListener) {
        this.groupEditorListener = groupEditorListener;
    }

    public CheckInterface getCheckInterface() {
        return checkInterface;
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public ModifyCountInterface getModifyCountInterface() {
        return modifyCountInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }


    static class GroupViewHolder {
        @BindView(R.id.liner_view)
        LinearLayout linerView;
        @BindView(R.id.store_checkBox)
        CheckBox storeCheckBox;
        @BindView(R.id.store_name)
        TextView storeName;
        /*@BindView(R.id.store_edit)
        TextView storeEdit;*/

        public GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 店铺的复选框
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素的位置
         * @param isChecked     组元素的选中与否
         */
        void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变触发的事件
         *
         * @param groupPosition 组元素的位置
         * @param childPosition 子元素的位置
         * @param isChecked     子元素的选中与否
         */
        void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }


    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param groupPosition 组元素的位置
         * @param childPosition 子元素的位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        void doUpdate(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        void setNumber(int groupPosition, int childPosition, View showCountView, boolean isChecked,String TextChangedNubmer);

        void onItemClick(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删除子Item
         *
         * @param groupPosition
         * @param childPosition
         */
        void childDelete(int groupPosition, int childPosition);
    }

    /**
     * 监听编辑状态
     */
    public interface GroupEditorListener {
        void groupEditor(int groupPosition);
    }

    /**
     * 使某个小组处于编辑状态
     */
    private class GroupViewClick implements View.OnClickListener {
        private StoreInfo group;
        private int groupPosition;
        private TextView editor;

        public GroupViewClick(StoreInfo group, int groupPosition, TextView editor) {
            this.group = group;
            this.groupPosition = groupPosition;
            this.editor = editor;
        }

        @Override
        public void onClick(View v) {
            if (editor.getId() == v.getId()) {
                groupEditorListener.groupEditor(groupPosition);
                if (group.isEditor()) {
                    group.setEditor(false);
                } else {
                    group.setEditor(true);
                }

            }
        }
    }
    static class ChildViewHolder {
        @BindView(R.id.single_checkBox)
        CheckBox singleCheckBox;
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_subtitle)
        TextView txtSubtitle;
        @BindView(R.id.dialog_reduceNum)
        TextView dialogReduceNum;
        @BindView(R.id.dialog_num)
        EditText dialogNum;
        @BindView(R.id.dialog_increaseNum)
        TextView dialogIncreaseNum;
        @BindView(R.id.ll_change_num)
        RelativeLayout llChangeNum;
        @BindView(R.id.txt_z_price)
        TextView txtZPrice;
        @BindView(R.id.txt_y_price)
        TextView txtYPrice;
        @BindView(R.id.liner_item)
        LinearLayout linerItem;
      /*  @BindView(R.id.single_checkBox)
        CheckBox singleCheckBox;
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.goods_size)
        TextView goods_size;
        @BindView(R.id.goods_price)
        TextView goodsPrice;
        @BindView(R.id.goods_prime_price)
        TextView goodsPrimePrice;
        @BindView(R.id.goods_buyNum)
        TextView goodsBuyNum;
        @BindView(R.id.goods_data)
        RelativeLayout goodsData;
        @BindView(R.id.reduce_goodsNum)
        TextView reduceGoodsNum;
        @BindView(R.id.goods_Num)
        TextView goodsNum;
        @BindView(R.id.increase_goods_Num)
        TextView increaseGoodsNum;
        @BindView(R.id.goodsSize)
        TextView goodsSize;
        @BindView(R.id.del_goods)
        TextView delGoods;
        @BindView(R.id.edit_goods_data)
        LinearLayout editGoodsData;*/

        public ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }





}
