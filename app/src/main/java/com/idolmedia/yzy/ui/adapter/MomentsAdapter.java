package com.idolmedia.yzy.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.idolmedia.yzy.utlis.MultyPicView;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：com.idolmedia.yzy.ui.adapter
 * 创建人：JOKER.WANG
 * 创建时间：2017/12/7 17:24
 * 描述： 评论 adpater
 */

public class MomentsAdapter extends RecyclerView.Adapter<MomentsAdapter.ViewHolder> {


    public MomentsAdapter() {


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.include_moments, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int type=getItemViewType(position);
        switch (type){
            case 0:
                break;
            case 1:
                holder.multyPicView.setClickCallback(holder.callback);
                holder.multyPicView.setVisibility(View.VISIBLE);
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;

        }

    }


    @Override
    public int getItemCount() {
        return 0;
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        MultyPicView multyPicView;
        MultyPicView.ClickCallback callback;
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.nick)
        TextView nick;
        @BindView(R.id.nick_time)
        TextView nickTime;
        @BindView(R.id.iamge_like)
        ImageView iamgeLike;
        @BindView(R.id.txt_context)
        TextView txtContext;
        @BindView(R.id.item_discover_moments_viewStub)
        ViewStub viewStub;
        @BindView(R.id.txt_ll)
        TextView txtLl;
        @BindView(R.id.txt_pl)
        TextView txtPl;
        @BindView(R.id.txt_good)
        TextView txtGood;
        @BindView(R.id.iamge_sns)
        ImageView imageView2;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
            switch (0){
                case 0:
                    viewStub.setVisibility(View.GONE);
                    break;
                case 1:
                    viewStub.setLayoutResource(R.layout.view_discover_moments_view_stub_image);
                    viewStub.inflate();
                    MultyPicView tempmultyPicView=itemView.findViewById(R.id.item_discover_moments_multy_pic);
                    if(tempmultyPicView!=null){
                        multyPicView=tempmultyPicView;
                        multyPicView.setMaxChildCount(MultyPicView.MAX_IMG_COUNT);
                        callback=new  MultyClickCallback(this);
                    }
                    break;
                case 2:
                    viewStub.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    viewStub.setVisibility(View.VISIBLE);
                    break;
                default:
                    viewStub.setVisibility(View.GONE);
                    break;
            }
        }
    }

    class MultyClickCallback implements MultyPicView.ClickCallback {

        private RecyclerView.ViewHolder holder;

        public MultyClickCallback(RecyclerView.ViewHolder holders) {
            this.holder = holders;
        }

        @Override
        public void callback(int index, String[] str) {
         /*   int position = getItemPosition(holder);
            if(position < 0 ){
                return;
            }
            int item_type = getItemType(position);
            if(item_type < 0){
                return;
            }*/
/*

            if (listener != null) {
                listener.onOriginal(index,position,getItem(position));
            }
*/

//            if (item_type != TYPE_VIDEO) {
//                if (listener != null) {
//                    listener.onOriginal(index, position, null);
//                }
//            } else {
//                if (listener != null) {
//                    listener.onVideo(position,
//                            (TreasuryDetilasBean) getItem(position));
//                }
//            }
        }

        @Override
        public void onLongCall(int index, String[] str) {
//            int position = holder.getAdapterItemPosition();
//            if(position < 0 ){
//                return;
//            }
//            if(listener != null){
//                listener.Collection(position, (TreasuryDetilasBean) getItem(position));
//            }
        }
    }


}
