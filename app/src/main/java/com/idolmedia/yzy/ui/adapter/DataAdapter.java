package com.idolmedia.yzy.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idolmedia.yzy.R;
import com.idolmedia.yzy.entity.BannerEntity;

/**
 * Created by Dajavu on 25/10/2017.
 */

public class DataAdapter extends BaseQuickAdapter<BannerEntity.DatasBean,BaseViewHolder> {

    public DataAdapter() {
        super(R.layout.item_image);
    }

    @Override
    protected void convert(BaseViewHolder helper, BannerEntity.DatasBean item) {
        Glide.with(mContext).load(item.getPic_url()).apply(new RequestOptions().fitCenter()).into((ImageView)helper.getView(R.id.image));
    }
} /*RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private int[] images = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3,
            R.mipmap.item4,R.mipmap.item5};

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(images[position]);
        holder.imageView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "clicked:" + v.getTag(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }*/
