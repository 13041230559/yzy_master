package com.idolmedia.yzy.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.IDoEntity;
import com.idolmedia.yzy.utlis.sortlist.ContactSortModel;

import java.util.List;

public class SortAdapter  extends BaseQuickAdapter<IDoEntity.DatasBean,BaseViewHolder> implements SectionIndexer {



    private List<IDoEntity.DatasBean> list = null;

    public SortAdapter( @Nullable List<IDoEntity.DatasBean> data) {
        super(R.layout.item_contact, data);
         this.list =data;
    }

    @Override
    protected void convert(BaseViewHolder helper, IDoEntity.DatasBean item) {
        int section = getSectionForPosition(helper.getAdapterPosition());
        if (helper.getAdapterPosition() == getPositionForSection(section)) {
            helper.setText(R.id.tv_catagory,item.getSortLetters());
            helper.getView(R.id.tv_catagory).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_catagory).setVisibility(View.GONE);
        }
          helper.setText(R.id.tv_city_name,item.getIdo_name());
          Glide.with(mContext).load(item.getIdo_head_img()).apply(new RequestOptions().circleCrop()).into(((ImageView)helper.getView(R.id.head_iamge)));
        if(item.isIsAtted()){
            ((ImageView) helper.getView(R.id.iamge_like)).setImageResource(R.mipmap.icon_like_like);
            //  Glide.with(mContext).load(R.mipmap.icon_like_like).into((ImageView)helper.getView(R.id.iamge_like));
        }else {
            ((ImageView) helper.getView(R.id.iamge_like)).setImageResource(R.mipmap.icon_attention_add_yzy);
            //Glide.with(mContext).load(R.mipmap.icon_like_add).into((ImageView)helper.getView(R.id.iamge_like));
        }
        helper.addOnClickListener(R.id.iamge_like);
    }

    @Override
    public Object[] getSections() {
        return  null;
    }

    @Override
    public int getPositionForSection(int section) {
        for (int i = 0; i <getItemCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }










/*extends BaseAdapter implements SectionIndexer {

  private List<IDoEntity.DatasBean> list = null;
    private Context mContext;

    public SortAdapter(Context mContext, List<IDoEntity.DatasBean> list) {
        this.mContext = mContext;
        this.list = list;
    }


    public void updateListView(List<IDoEntity.DatasBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final IDoEntity.DatasBean mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_contact, null);
            viewHolder.tvTitle = view.findViewById(R.id.tv_city_name);
            viewHolder.iamge_head = view.findViewById(R.id.head_iamge);
            view.setTag(viewHolder);
            viewHolder.tvLetter = view.findViewById(R.id.tv_catagory);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        int section = getSectionForPosition(position);

        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.getSortLetters());
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }
        Log.e("toUpperCase()",list.get(position).getSortLetters()+"");
        viewHolder.tvTitle.setText(this.list.get(position).getIdo_name());
        Glide.with(viewHolder.iamge_head).load(this.list.get(position).getIdo_head_img()).apply(new RequestOptions().circleCrop()).into(viewHolder.iamge_head);
        return view;

    }


    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
        ImageView iamge_head;
    }

    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public Object[] getSections() {
        return null;
    }
}*/
}