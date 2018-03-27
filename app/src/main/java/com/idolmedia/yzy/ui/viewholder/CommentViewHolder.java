package com.idolmedia.yzy.ui.viewholder;

import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
/**
 * Created by yiw on 2016/8/16.
 */
public abstract class CommentViewHolder extends RecyclerView.ViewHolder{
    public final static int TYPE_URL = 1;
    public final static int TYPE_IMAGE = 2;
    public final static int TYPE_VIDEO = 3;

    public ImageView headIv;
    public TextView nameTv;
    public TextView timeTv;
    public ImageView lickIv;
    public TextView content;

    public TextView browseBtn;
    public TextView commentBtn;
    public TextView goodBtn;
    public ImageView snsBtn;
    public TextView goodLickBtn;
    public int viewType;
    public  RelativeLayout relativeComment;
    public CommentViewHolder(View itemView, int viewType) {
        super(itemView);
        this.viewType = viewType;
        ViewStub viewStub = (ViewStub) itemView.findViewById(R.id.viewStub);
        initSubView(viewType, viewStub);
        headIv=(ImageView) itemView.findViewById(R.id.iamge_portrait);
        nameTv=(TextView)itemView.findViewById(R.id.txt_nick_name);
        timeTv=(TextView)itemView.findViewById(R.id.txt_release_time);
        lickIv=(ImageView) itemView.findViewById(R.id.iamge_like);
        browseBtn=(TextView)itemView.findViewById(R.id.txt_ll);
        commentBtn=(TextView)itemView.findViewById(R.id.txt_pl);
        goodBtn=(TextView)itemView.findViewById(R.id.txt_good);
        content=(TextView)itemView.findViewById(R.id.txt_content);
        snsBtn=(ImageView)itemView.findViewById(R.id.iamge_sns);
        goodLickBtn=(TextView)itemView.findViewById(R.id.txt_good_lick);
        relativeComment=(RelativeLayout) itemView.findViewById(R.id.comment_bottom);
    }
    public abstract void initSubView(int viewType, ViewStub viewStub);


}
