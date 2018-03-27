package com.idolmedia.yzy.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idolmedia.yzy.R;
import com.mumu.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：com.idolmedia.yzy.ui.activity
 * 创建人：JOKER.WANG
 * 创建时间：2017/11/13 16:57
 * 描述：  秒杀商品
 */

public class SeckillActivity extends BaseActivity {

    @BindView(R.id.image_close)
    ImageView imageClose;
    @BindView(R.id.left_txt)
    TextView leftTxt;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.right_txt)
    TextView rightTxt;
    @BindView(R.id.right_img)
    ImageView rightImg;
    @BindView(R.id.liner_top)
    LinearLayout linerTop;
    @BindView(R.id.iamge_no_view)
    ImageView iamgeNoView;
    @BindView(R.id.txt_no_data)
    TextView txtNoData;

    @Override
    public int getLayoutId() {
        return R.layout.activity_seckill;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        tvTitle.setText("秒杀商品");
        iamgeNoView.setImageResource(R.mipmap.icon_kong_kill);
        txtNoData.setText("敬请期待");
       /* List list = new ArrayList();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509516301704&di=1f029ef4f981c49f70c6228f398d35be&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201610%2F10%2F20161010205516_PiyAv.jpeg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509516301703&di=ba1480faea3e42a7cd8ae563393edd0d&imgtype=0&src=http%3A%2F%2Fi.zeze.com%2Fattachment%2Fforum%2F201701%2F05%2F150207m0capgdre9lna1g4.jpeg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509516301700&di=37e0d22c190d6349ba55ad2b3319da90&imgtype=0&src=http%3A%2F%2Fwww.sznews.com%2Fmb%2Fimages%2Fattachement%2Fjpg%2Fsite3%2F20170306%2FIMG78e3b5a05ef343876809363_small.jpg");
        list.add("http://imgsrc.baidu.com/forum/w%3D580/sign=a9c58ca67c899e51788e3a1c72a6d990/8fe16063f6246b60900ced2fecf81a4c500fa257.jpg");
        list.add("http://img05.tooopen.com/products/20141215/EC17D785-1E06-F2C9-8A4B-4CBE9D0C8B08.gif");
        banner.setImages(list);
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
        List<SeckillEntity> list1=new ArrayList<>();
        for(int i=0;i<5;i++) {
            SeckillEntity seckill = new SeckillEntity("http://img1.imgtn.bdimg.com/it/u=3282896093,1060753938&fm=11&gp=0.jpg", "薛之谦新版专辑", "10娱豆/人次支付", "可以的很强势","");
            list1.add(seckill);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        seckillAdapter=new SeckillAdapter();
        recyclerView.setAdapter(seckillAdapter);
        seckillAdapter.setNewData(list1);
        seckillAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()){
                case R.id.txt_immediately:
                    ToastUitl.show("立即参与",1000);
                    break;
                case R.id.txt_inviting_friends:

            }
        });

        seckillAdapter.setOnItemClickListener((adapter, view, position) -> startActivity(SeckillDetailsActivity.class));*/
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.image_close)
    public void onViewClicked() {
        finish();
    }
}
