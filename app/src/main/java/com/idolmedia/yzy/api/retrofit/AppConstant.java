package com.idolmedia.yzy.api.retrofit;

/**
 * Created by Administrator on 2017/7/28.
 */

public interface AppConstant {
  String APP_ID="wx2d80c6a71ca20ebc";

  String FKEY="wx2d80c6a71ca20ebc";
  /**
   * 主路径
   */
  //上线路径
  String BASEURL ="https://www.withfans.com/FHADMINM/";
  //测试路径
// String BASEURL ="http://192.168.3.145:1081/FHADMINM/";

  /**
   * 验证码
   */
  String GETCODE ="appRegister/sendVCode";
  /**
   * 注册
   */
  String REGISTER="appRegister/phoneEmail";
  /**
   * 登录
   */
  String LOGIN="appLogin/phone_email";
  /**
   * 自动登录
   */
  String AUTOLOGIN="appLogin/autoLogin";
  /**
   * 找回密码
   */
  String BACKPASS="appLogin/findPassword";

  /***
   * 第三方登录
   */
  String ThirdPartyLogin= "appLogin/third_party";
  /***
   * 手机号快捷登录
   */
  String CodeLogin="appLogin/quick_login";
  /***
   * 验证手机号接口
   */
  String AuthenticationPhone="appRegister/auth_checkCode";

  /***
   *
   *爱豆明星列表
   */
  String SelectStars="appAidouStar/recommend_stars";

  /***
   *
   *关注爱豆
   */
  String FollowIdo="appAidouStar/attention_cancel_stars";

  /***
   *
   *关注官方第三方
   */
  String FollowThirdParty="appUserCenter/attention_third_auth";

  /***
   *
   *搜索爱豆
   */
  String IdoSearch="appAidouStar/search_all_stars";

  /***
   *
   *绑定手机号
   */
  String BingPhone="appLogin/binding_phone";


  /***
   *
   *更新头像
   */
  String UpdateHead="appUserCenter/update_head_img";
  /***
   *
   * banner轮播图
   */
  String Banner="appBanner/queryBannerShow";
  /***
   *
   * 更改昵称
   */
  String UpateNickName="appUserCenter/update_nickname";

  /***
   *
   *  认证接口
   *
   */
  String  Authentication="appUserCenter/apply_authentication";
  /***
   *
   *  娱豆列表接口
   *
   */
  String MyYDMoneyList="appUserCenter/myYDMoneyList";

  /***
   *
   *  推荐页商品列表
   *
   */

  String RecommendList="appProduct/queryHomeProductList";

  /****
   *
   *分类商品
   *
   */
  String Classification="appProduct/queryProductCatagory";
  /***
   *商品搜索
   *
   */
  String Search="appProduct/queryProListBySearchName";

  /****
   *
   *分类搜索
   *
   */
  String ClassSearch="appProduct/queryProListByDicId";
  /****
   *
   *娱页商品列表（普通商品+定金商品）
   *
   */
  String Yu="appProduct/queryYUProductList";
  /****
   *
   *定金商品详情页
   *
   */
  String DepositcommodityDetails="appProduct/queryDJDetail";

  /****
   *
   *普通商品详情页
   *
   */
  String CommodityDetails="appProduct/queryProDetail";

  /****
   *
   *普通商品规格分类
   *
   */
  String CommoditySpecification="appProduct/queryProCatagory";

  /****
   *
   *普通商品规格分类
   *
   */
  String Participationrecord="appProduct/queryShopRecordList";

  /****
   *
   *用户订单
   *
   */
  String UserOrder="appOrder/queryOrderList";


  /****
   *
   *用户订单详情
   *
   */
  String UserOrderDetails="appOrder/queryOrderDetails";

  /****
   *
   *取消订单
   *
   */
  String CancelOrder="appOrder/cancelOrder";



  /****
   *
   *确认收货
   *
   */
  String ConfirmReceipt="appOrder/confirmDelivery";



  /****
   *
   *支付签名
   *
   */
  String PaySign="appPay/getSignName";

  /****
   *
   *物流信息
   *
   */
  String Logistics="appOrder/queryExpressInfo";
  /****
   *
   *排行榜
   *
   */
  String Ranking="appProduct/queryShopRecordList";

  /***
   *
   *意见反馈
   *
   */
  String FeedBack="appFeedBack/feedback";
  /****
   *
   * 特惠
   */
  String Preferential="appProduct/queryDiscProList";

  /****
   *
   * 特惠 推荐
   */
  String Preferentialhot="appProduct/queryRecommendDiscProList";

  /***
   *活动应援
   *
   */
  String SupportActivity="appProduct/queryActivityProList";

  /***
   *活动应援详情
   *
   */
  String SupportActivityDetails="appProduct/querySupportDetail";


  /***
   *热搜词
   *
   */
  String HotSearch="appProduct/queryHotSearchList";

  /***
   *添加收货地址
   *
   */
  String AddAddress="appAddress/addAddress";

  /***
   *
   * 收货地址列表
   */
  String AddressList="appAddress/addressList";

  /***
   *
   * 删除收货地址
   */
  String DelAddress="appAddress/addressDelete";

  /***
   *
   * 修改收货地址
   */
  String EditAddress="appAddress/addressEdit";

  /***
   *
   * 确认订单
   */
  String ConfirmOrder="appAffiremOrder/affirmOrderItems";

  /***
   *
   * 提交订单
   *
   */
  String SubmitOrder="appAffiremOrder/submitOrder";


  /***
   *
   * 计算运费
   */
  String Freight="appAffiremOrder/affirmOrderFreight";
  /***
   *
   * 默认地址
   */
  String DefaultAddress="appAddress/defaultAddress";

  /***
   *
   *购物车列表
   */
  String CartList="appShoppingCart/cartsShoppingList";



  /***
   *
   *清空失效商品
   */
  String Invalid="appShoppingCart/delLostEffectCart";


  /***
   *
   * 添加购物车
   */

  String AddCart=  "appShoppingCart/addCartInfo";
  /***
   *
   *删除购物车
   */
  String DelCart=  "appShoppingCart/delCartInfo";

  /***
   *
   * 商品规格分类
   *
   */
  String Productclass ="appProduct/queryProCatagory";


  /***
   *
   * 修改购物车
   *
   */
  String UpdateCart ="appShoppingCart/editCartInfo";

  /***
   *
   * 立即购买确认下单商品备注信息
   *
   */
  String BuyAdditional ="appProduct/queryAdditional";

  /***
   *
   * 立即购买确认计算运费
   *
   */

  String BuyFreight="appAffiremOrder/computeFreight";

  /***
   *
   * 立即购买确认下单
   *
   */
  String BuyOrder=" appAffiremOrder/submitImmediatelyOrder";
  /***
   *
   * 账号安全
   *
   */
  String AccountSecurity="appUserCenter/myAccountSafe";

  /***
   *
   * 绑定账号
   *
   */
  String BindThirdAccount= "appUserCenter/bindThirdAccount";

  /***
   *
   *手机或者邮箱账号接口
   *
   */
  String BindPhoneEmailAccount= "appUserCenter/bindPOEAccount";

  /***
   *
   * 解绑账号
   *
   */
  String UBindThirdAccount= "appUserCenter/unbindAccount";


  /***
   *
   * 31.5更换邮箱号或者手机账号接口
   *
   */
  String ChangeMain= " appUserCenter/changeMainAccountNo";

  /***
   *
   *31.6新增密码接口
   *
   */
  String AddPass="appUserCenter/addPassword";


  /***
   *
   *31.7修改密码接口
   *
   */
  String UpdatePass="appUserCenter/updatePassword";

  /***
   *
   * 32.3查询所有评论列表（资讯或社区列表）接口
   *
   */
  String CommentList="appComment/queryCommentList";


  /***
   *
   * 评论类型
   *
   */
  String CommentType="appComment/queryCommentType";

  /***
   *
   * 回复
   *
   */
  String ReplyComment="appComment/save";

  /***
   *
   * 回复人
   *
   */
  String CommentUser="appComment/replyComment";


  /***
   *
   * 点赞
   *
   */
  String GoodClick="appPrise/priseOrCancel";

  /***
   *
   * 26.1尾款下单接口
   *
   */
  String RetainageOrder="appAffiremOrder/submitWKOrder";



  /***
   *
   * 购物车推荐
   *
   */
  String RecommendCart="appProduct/queryCarRecordProductList";

  /***
   *
   *
   * 32.1资讯列表接口
   *
   */
  String InformationList="appInformation/queryInformationList";
  /***
   *
   *32.2资讯详情接口
   *
   */
  String InformationDetail="appInformation/queryInformationDetail";


  /***
   *
   *31.1我关注的爱豆列表接口
   *
   */
  String MyIdoList="appAidouStar/myIdoList";

  /***
   *
   *31.2我关注的官方认证列表接口
   *
   */
  String MyAuthList="appAidouStar/myOfficeAuthList";

  /***
   *
   *32.4社区详情数据（社区中用户评论发布内容列表）接口
   *
   */
  String CommunityDetalisList="appComment/queryCommentDetails";
  /***
   *
   * 36.1 消息分类列表接口
   *
   */
  String Message="appMessage/queryMessageTypeList";
  /**
   * 35.2我的发布商品/社区/活动列表接口
   *
   */
    String MyReleaseList="appAuthUser/myPublishLists";

  /**
   * 35.1我的发布商品/社区/活动列表接口
   *
   */
  String MyReleaseInfo="appAuthUser/myPublishInfo";

  /**
   * 12.5 删除订单接口
   */
  String DeleteOrder="appOrder/deleteOrder";

  /**
   * 更新APP
   */
  String UpdateAPP="appMessage/queryMessageTypeList";

  /**
   * 40.4 推荐页检查未读消息的接口
   */
  String Unreadmessage="appMessage/queryNotReadMessage";

  /**
   * 40.2 分页查询消息列表接口
   */
  String QueryMessageList="appMessage/queryMessageList";
  /**
   * 40.3 读取消息接口
   */
  String ToRead="appMessage/toRead";
  /**
   * 5.4 查询用户认证结果信息接口
   */
  String  QueryAuthenticationResult= "appUserCenter/queryAuthenticationInfo";


  /**
   * 历史订单
   */
  String  OldOrderList= "appOrder/queryOldOrderList";

  /**
   * 历史订单
   */
  String  UpdateVersion= "appVersion/updateVersion20";


}
