package com.idolmedia.yzy.api.retrofit;


import com.idolmedia.yzy.entity.AccountEntity;
import com.idolmedia.yzy.entity.AddressDefaultEntity;
import com.idolmedia.yzy.entity.AddressEntity;
import com.idolmedia.yzy.entity.AuthenticationResultEntity;
import com.idolmedia.yzy.entity.BannerEntity;
import com.idolmedia.yzy.entity.BaseResult;
import com.idolmedia.yzy.entity.CartEntity;
import com.idolmedia.yzy.entity.ClassEntity;
import com.idolmedia.yzy.entity.CommentEntity;
import com.idolmedia.yzy.entity.CommentTypeEntity;
import com.idolmedia.yzy.entity.CommunityCommentDetalisEntity;
import com.idolmedia.yzy.entity.ConfirmOrderEntity;
import com.idolmedia.yzy.entity.DepositEntity;
import com.idolmedia.yzy.entity.CommodityEntity;
import com.idolmedia.yzy.entity.IDoEntity;
import com.idolmedia.yzy.entity.InformationDetalisEntity;
import com.idolmedia.yzy.entity.InformationEntity;
import com.idolmedia.yzy.entity.LogisticsEntity;
import com.idolmedia.yzy.entity.MessageClassEntity;
import com.idolmedia.yzy.entity.MessageEntity;
import com.idolmedia.yzy.entity.MyBeanEntity;
import com.idolmedia.yzy.entity.MyFollowIdoEntity;
import com.idolmedia.yzy.entity.MyOrderDetalisEntity;
import com.idolmedia.yzy.entity.MyOrderEntity;
import com.idolmedia.yzy.entity.MyReleaseInfoEntity;
import com.idolmedia.yzy.entity.MyFollowOfficialEntity;
import com.idolmedia.yzy.entity.OldOrderEntity;
import com.idolmedia.yzy.entity.PayBuyEntity;
import com.idolmedia.yzy.entity.PayEntity;
import com.idolmedia.yzy.entity.PreferentiaEntity;
import com.idolmedia.yzy.entity.PreferentialHotEntity;
import com.idolmedia.yzy.entity.ProductclassEntity;
import com.idolmedia.yzy.entity.RankingEntity;
import com.idolmedia.yzy.entity.ReturnFreightEntity;
import com.idolmedia.yzy.entity.SearchHotEntity;
import com.idolmedia.yzy.entity.SupportDetalisEntity;
import com.idolmedia.yzy.entity.SupportEntity;
import com.idolmedia.yzy.entity.UpdateVersionEntity;
import com.idolmedia.yzy.entity.UserEntity;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

public interface YZYAPi {

    /**
     *注册
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.REGISTER)
    Observable<UserEntity> Register(@FieldMap HashMap<String,Object> map);

    /**
     *验证码
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.GETCODE)
    Observable<BaseResult> GetCode(@FieldMap HashMap<String,Object> map);

    /**
     *登录
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.LOGIN)
    Observable<UserEntity>Login(@FieldMap HashMap<String,Object> map);

    /**
     *验证码登录
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.CodeLogin)
    Observable<UserEntity>CodeLogin(@FieldMap HashMap<String,Object> map);

    /**
     *验证码登录
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.AuthenticationPhone)
    Observable<String>AuthenticationPhone(@FieldMap HashMap<String,Object> map);

    /**
     *第三方登录
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.ThirdPartyLogin)
    Observable<UserEntity>ThirdPartyLogin(@FieldMap HashMap<String,Object> map);

    /**
     *快捷登录
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.AUTOLOGIN)
    Observable<UserEntity>AutoLogin(@FieldMap HashMap<String,Object> map);

    /**
     *快捷登录
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.BACKPASS)
    Observable<BaseResult>BackPass(@FieldMap HashMap<String,Object> map);

    /**
     *爱豆随机列表
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.SelectStars)
    Observable<IDoEntity>SelectStars(@FieldMap HashMap<String,Object> map);

    /**
     *关注爱豆
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.FollowIdo)
    Observable<BaseResult>FollowIdo(@FieldMap HashMap<String,Object> map);

    /**
     *关注爱豆
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.FollowThirdParty)
    Observable<BaseResult>FollowThirdParty(@FieldMap HashMap<String,Object> map);


    /**
     *爱豆搜索
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.IdoSearch)
    Observable<IDoEntity>IdoSearch(@FieldMap HashMap<String,Object> map);


    /**
     *爱豆明星列表
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.BingPhone)
    Observable<UserEntity>BingPhone(@FieldMap HashMap<String,Object> map);

    /**
     *更新头像
     *
     */
    @Multipart
    @POST(AppConstant.UpdateHead)
    Observable<String>UpdateHead(@Part List<MultipartBody.Part> partList);

    /***
     *
     * banner
     */
    @FormUrlEncoded
    @POST(AppConstant.Banner)
    Observable<BannerEntity>Banner(@FieldMap HashMap<String,Object> map);

    /***
     *
     * 修改昵称
     */
    @FormUrlEncoded
    @POST(AppConstant.UpateNickName)
    Observable<BaseResult>UpateNickName(@FieldMap HashMap<String,Object> map);
    /***
     *
     *  认证接口
     *
     */
    @Multipart
    @POST(AppConstant.Authentication)
    Observable<BaseResult>Authentication(@Part List<MultipartBody.Part> partList);

    /***
     *
     * 娱豆列表接口
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.MyYDMoneyList)
    Observable<MyBeanEntity>MyYDMoneyList(@FieldMap HashMap<String,Object> map);

    /***
     *
     *  推荐页商品列表
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.RecommendList)
    Observable<CommodityEntity>RecommendList(@FieldMap HashMap<String,Object> map);

    /***
     *
     *  海外直邮
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.Yu)
    Observable<CommodityEntity>YuList(@FieldMap HashMap<String,Object> map);


    /****
     *
     *定金商品详情页
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.DepositcommodityDetails)
    Observable<DepositEntity>DeCommdityDetails(@FieldMap HashMap<String,Object> map);
    /****
     *
     *普通商品详情页
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.CommodityDetails)
    Observable<DepositEntity>CommodityDetails(@FieldMap HashMap<String,Object> map);

    /****
     *
     *普通商品规格分类
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.Participationrecord)
    Observable<String>Participationrecord(@FieldMap HashMap<String,Object> map);

    /****
     *
     *排行榜
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.Ranking)
    Observable<RankingEntity>RankingList(@FieldMap HashMap<String,Object> map);

    /****
     *
     *订单
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.UserOrder)
    Observable<MyOrderEntity>UserOrderList(@FieldMap HashMap<String,Object> map);

    /****
     *
     *订单详情
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.UserOrderDetails)
    Observable<MyOrderDetalisEntity>OrderDetailsList(@FieldMap HashMap<String,Object> map);


    /****
     *
     *排行榜
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.Classification)
    Observable<ClassEntity>ClassificationList(@FieldMap HashMap<String,Object> map);

    /****
     *
     *物流
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.Logistics)
    Observable<LogisticsEntity>LogisticsList(@FieldMap HashMap<String,Object> map);

    /****
     *
     *意见反馈
     *
     */
    @Multipart
    @POST(AppConstant.FeedBack)
    Observable<BaseResult>FeedBack(@Part List<MultipartBody.Part> partList);

    /**
     *
     * 特惠
     */
    @FormUrlEncoded
    @POST(AppConstant.Preferential)
    Observable<PreferentiaEntity>PreferentialList(@FieldMap HashMap<String,Object> map);
    /**
     *
     * 特惠 推荐
     */
    @FormUrlEncoded
    @POST(AppConstant.Preferentialhot)
    Observable<PreferentialHotEntity>PreferentialhotList(@FieldMap HashMap<String,Object> map);
    /**
     *
     * 应援
     */
    @FormUrlEncoded
    @POST(AppConstant.SupportActivity)
    Observable<SupportEntity>SupportActivitylList(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 应援详情
     */
    @FormUrlEncoded
    @POST(AppConstant.SupportActivityDetails)
    Observable<SupportDetalisEntity>SupportDetails(@FieldMap HashMap<String,Object> map);


    /**
     *
     * 搜索
     */
    @FormUrlEncoded
    @POST(AppConstant.Search)
    Observable<String>SearchList(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 分类搜索
     */
    @FormUrlEncoded
    @POST(AppConstant.ClassSearch)
    Observable<CommodityEntity>ClassSearchList(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 热搜词搜索
     */
    @FormUrlEncoded
    @POST(AppConstant.HotSearch)
    Observable<SearchHotEntity>HotSearchList(@FieldMap HashMap<String,Object> map);


    /**
     *
     * 添加地址
     */
    @FormUrlEncoded
    @POST(AppConstant.AddAddress)
    Observable<BaseResult>AddAddress(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 地址列表
     */
    @FormUrlEncoded
    @POST(AppConstant.AddressList)
    Observable<AddressEntity>AddressList(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 删除地址
     */
    @FormUrlEncoded
    @POST(AppConstant.DelAddress)
    Observable<BaseResult>DelAddress(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 修改地址
     */
    @FormUrlEncoded
    @POST(AppConstant.EditAddress)
    Observable<BaseResult>EditAddress(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 确认订单
     */
    @FormUrlEncoded
    @POST(AppConstant.ConfirmOrder)
    Observable<ConfirmOrderEntity>ConfirmOrder(@FieldMap HashMap<String,Object> map);


    /**
     *
     * 提交订单
     */
    @FormUrlEncoded
    @POST(AppConstant.SubmitOrder)
    Observable<PayEntity>SubmitOrder(@FieldMap HashMap<String,Object> map);
    /**
     *
     * 计算运费
     */
    @FormUrlEncoded
    @POST(AppConstant.Freight)
    Observable<ReturnFreightEntity>Freight(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 获取默认地址
     */
    @FormUrlEncoded
    @POST(AppConstant.DefaultAddress)
    Observable<AddressDefaultEntity>DefaultAddress(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 商品规格分类
     */
    @FormUrlEncoded
    @POST(AppConstant.Productclass)
    Observable<ProductclassEntity>ProductclassList(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 添加购物车
     */
    @FormUrlEncoded
    @POST(AppConstant.AddCart)
    Observable<BaseResult>AddCart(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 购物车列表
     */
    @FormUrlEncoded
    @POST(AppConstant.CartList)
    Observable<CartEntity>CartList(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 购物车列表
     */
    @FormUrlEncoded
    @POST(AppConstant.Invalid)
    Observable<BaseResult>Invalid(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 删除购物车
     */
    @FormUrlEncoded
    @POST(AppConstant.DelCart)
    Observable<BaseResult>DelCart(@FieldMap HashMap<String,Object> map);


    /**
     *
     * 修改购物车
     */
    @FormUrlEncoded
    @POST(AppConstant.UpdateCart)
    Observable<BaseResult>UpdateCart(@FieldMap HashMap<String,Object> map);


    /***
     *
     * 立即购买确认下单商品备注信息
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.BuyAdditional)
    Observable<String>BuyAdditional(@FieldMap HashMap<String,Object> map);


    /**
     *
     * 立即购买确认计算运费
     */
    @FormUrlEncoded
    @POST(AppConstant.BuyFreight)
    Observable<String>BuyFreight(@FieldMap HashMap<String,Object> map);


    /**
     *
     * 立即购买确认下单
     */
    @FormUrlEncoded
    @POST(AppConstant.BuyOrder)
    Observable<PayBuyEntity>BuyOrder(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 支付签名
     */
    @FormUrlEncoded
    @POST(AppConstant.PaySign)
    Observable<String>PaySign(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 取消订单
     */
    @FormUrlEncoded
    @POST(AppConstant.CancelOrder)
    Observable<BaseResult>CancelOrder(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 确认收货
     */
    @FormUrlEncoded
    @POST(AppConstant.ConfirmReceipt)
    Observable<BaseResult>ConfirmReceipt(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 账号安全
     */
    @FormUrlEncoded
    @POST(AppConstant.AccountSecurity)
    Observable<AccountEntity>AccountList(@FieldMap HashMap<String,Object> map);

    /***
     *
     * 绑定账号
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.BindThirdAccount)
    Observable<BaseResult>BindThirdAccount(@FieldMap HashMap<String,Object> map);


    /***
     *
     *手机或者邮箱账号接口
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.BindPhoneEmailAccount)
    Observable<BaseResult>BindPhoneEmailAccount(@FieldMap HashMap<String,Object> map);


    /***
     *
     * 解绑账号
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.UBindThirdAccount)
    Observable<BaseResult>UBindThirdAccount(@FieldMap HashMap<String,Object> map);


    /***
     *
     * 更换主账号或者手机账号接口
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.ChangeMain)
    Observable<BaseResult>ChangeMain(@FieldMap HashMap<String,Object> map);

    /***
     *
     * 31.6新增密码接口
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.AddPass)
    Observable<BaseResult>AddPass(@FieldMap HashMap<String,Object> map);
    /***
     *
     * 31.6新增密码接口
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.UpdatePass)
    Observable<BaseResult>UpdatePass(@FieldMap HashMap<String,Object> map);


    /***
     *
     * 32.3查询所有评论列表（资讯或社区列表）接口
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.CommentList)
    Observable<CommentEntity>CommentList(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 评论类型
     */
    @FormUrlEncoded
    @POST(AppConstant.CommentType)
    Observable<CommentTypeEntity>CommentType(@FieldMap HashMap<String,Object> map);


    /**
     *
     * 评论类型
     */
    @Multipart
    @POST(AppConstant.ReplyComment)
    Observable<BaseResult>ReplyComment(@Part List<MultipartBody.Part> partList);
    /**
     *
     * 评论用户
     */
    @FormUrlEncoded
    @POST(AppConstant.CommentUser)
    Observable<BaseResult>CommentUser(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 点赞
     */
    @FormUrlEncoded
    @POST(AppConstant.GoodClick)
    Observable<BaseResult>GoodClick(@FieldMap HashMap<String,Object> map);


    /**
     *
     * 点赞
     */
    @FormUrlEncoded
    @POST(AppConstant.RetainageOrder)
    Observable<PayBuyEntity>RetainageOrder(@FieldMap HashMap<String,Object> map);


    /**
     *
     * 购物车推荐
     */
    @FormUrlEncoded
    @POST(AppConstant.RecommendCart)
    Observable<CommodityEntity>RecommendCartList(@FieldMap HashMap<String,Object> map);


    /**
     *
     * 资讯列表
     */
    @FormUrlEncoded
    @POST(AppConstant.InformationList)
    Observable<InformationEntity>InformationList(@FieldMap HashMap<String,Object> map);

    /**
     *
     * 资讯列表详情
     */
    @FormUrlEncoded
    @POST(AppConstant.InformationDetail)
    Observable<InformationDetalisEntity>InformationDetail(@FieldMap HashMap<String,Object> map);


    /***
     *
     *31.1我关注的爱豆列表接口
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.MyIdoList)
    Observable<MyFollowIdoEntity>MyIdoList(@FieldMap HashMap<String,Object> map);

    /***
     *
     *31.2我关注的官方认证列表接口
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.MyAuthList)
    Observable<MyFollowOfficialEntity>MyAuthList(@FieldMap HashMap<String,Object> map);

    /***
     *
     *32.4社区详情数据（社区中用户评论发布内容列表）接口
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.CommunityDetalisList)
    Observable<CommunityCommentDetalisEntity>CommunityDetalisList(@FieldMap HashMap<String,Object> map);

    /***
     *
     *36.1 消息分类列表接口
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.Message)
    Observable<MessageEntity>MessageList(@FieldMap HashMap<String,Object> map);

    /***
     *
     *36.1 消息分类列表接口
     *
     */
    @FormUrlEncoded
    @POST(AppConstant.MyReleaseList)
    Observable<String>MyReleaseList(@FieldMap HashMap<String,Object> map);

    /**
     *   39.1我的发布详情接口
     */
    @FormUrlEncoded
    @POST(AppConstant.MyReleaseInfo)
    Observable<MyReleaseInfoEntity>MyReleaseInfo(@FieldMap HashMap<String,Object> map);


    /**
     * 12.5 删除订单接口
     */
    @FormUrlEncoded
    @POST(AppConstant.DeleteOrder)
    Observable<BaseResult>DeleteOrder(@FieldMap HashMap<String,Object> map);


    /**
     *更新APP
     */
    @FormUrlEncoded
    @POST(AppConstant.UpdateAPP)
    Observable<String>UpdateAPP(@FieldMap HashMap<String,Object> map);

    /**
     *未读消息
     */
    @FormUrlEncoded
    @POST(AppConstant.Unreadmessage)
    Observable<String>Unreadmessage(@FieldMap HashMap<String,Object> map);

    /**
     *40.2 分页查询消息列表接口
     */
    @FormUrlEncoded
    @POST(AppConstant.QueryMessageList)
    Observable<MessageClassEntity>QueryMessageList(@FieldMap HashMap<String,Object> map);

    /**
     * 40.3 读取消息接口
     */
    @FormUrlEncoded
    @POST(AppConstant.ToRead)
    Observable<BaseResult>ToRead(@FieldMap HashMap<String,Object> map);

    /**
     * 5.4 查询用户认证结果信息接口
     */
    @FormUrlEncoded
    @POST(AppConstant.QueryAuthenticationResult)
    Observable<AuthenticationResultEntity>QueryAuthenticationResult(@FieldMap HashMap<String,Object> map);

    /**
     * 5.4 查询用户认证结果信息接口
     */
    @FormUrlEncoded
    @POST(AppConstant.OldOrderList)
    Observable<OldOrderEntity>OldOrderList(@FieldMap HashMap<String,Object> map);


    /**
     * 版本更新
     */
    @GET(AppConstant.UpdateVersion)
    Observable<UpdateVersionEntity>UPdateVersion();


}


