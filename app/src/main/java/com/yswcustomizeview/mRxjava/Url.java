package com.yswcustomizeview.mRxjava;

/**
 * Created by： Ysw on 2016/7/8.16:26.
 */
public class Url {
    /**
     * 阿里云服务器的地址
     */

    public static final String BASEURL = "http://120.24.152.135:8080/kuaidianwebservice/";

    /**
     * 分页菜品列表
     */
    public static final String DISHESLIST = "dishes/dishesAction!findMerchantDishes.as";

    /**
     * 所有菜品列表
     */
    public static final String ALLDISHESLIST = "dishes/dishesAction!findDishesNoPage.as";

    /**
     * 菜品详情
     */
    public static final String DISHESDETAILS = "dishes/dishesAction!findByIdMerchantDishes.as";

    /**
     * 菜品分类
     */
    public static final String DISHESLISTAllTYPE = "wizardchannel/wizardchannelAction!findAllWizardchannel.as";

    /**
     * 菜品口味
     */
    public static final String DISHESLISTTASTE = "taste/tasteAction!findAllTaste.as";

    /**
     * 菜品排序
     */
    public static final String DISHESLISTSORT = "dishessort/dishesSortAction!findAllDishesSort.as";

    /**
     * 提交订单
     */
    public static final String INDENT = "user/orderinfo/userOrderInfoAction!addUserOrderInfo.as";

    /**
     * 取消订单后再次提交订单接口
     */
    public static final String SUBMITINDENTAGAIN = "user/orderinfo/userOrderInfoAction!submitUserOrderInfo.as";

    /**
     * 订单列表接口
     */
    public static final String INDENTLIST = "user/orderinfo/userOrderInfoAction!findUserOrderInfoByPad.as";

    /**
     * 查询自己的订单
     */
    public static final String FINDMYINDENT = "user/orderinfo/userOrderInfoAction!findByOrderDetial.as";

    /**
     * 查询订单
     */
    public static final String FINDINDENT = "user/orderinfo/userOrderInfoAction!findByOrderInfo.as";

    /**
     * 加菜减菜接口
     */
    public static final String ADDDISHES = "user/orderinfo/userOrderInfoAction!userOrderInfoOperateDishes.as";

    /**
     * 取消订单接口 参数orderNum 订单号 token 用户token
     */
    public static final String CALCLEINDENT = "user/orderinfo/userOrderInfoAction!userOrderInfoCancel.as";

    /**
     * 删除订单接口 参数orderNum 订单号 token 用户token
     */
    public static final String DELETEINDENT = "user/orderinfo/userOrderInfoAction!removeUserOrderInfo.as ";

    /**
     * 旁桌列表
     */
    public static final String NEARBYTABLE = "user/orderinfo/userOrderInfoAction!findAuditUserOrderInfo.as";

    /**
     * 旁桌菜品
     */
    public static final String NEARBYDISHES = "user/orderinfo/userOrderInfoAction!findByAuditUserOrderInfo.as";

    /**
     * 查询旁桌菜品-Ysw
     */
    public static final String REFERNEARBYDISHES = "user/orderinfo/userOrderInfoAction!findByOrderDetial.as";

    /**
     * 商家Banner
     */
    public static final String BANNER = "store/banner/storeBannerAction!findAllBanner.as";

    /**
     * 招牌菜
     */
    public static final String SIGNATUREDISHES = "dishes/dishesAction!findIsSignatureDishes.as";

    /**
     * 呼叫服务
     */
    public static final String CALLSERVE = "user/callserve/callServeAction!addCallServe.as";

    /**
     * 注册
     */
    public static final String REGISTER_MEMBER = "clientuser/userinfo/loginAction!register.as";

    /**
     * 短信验证
     */
    public static final String PHONE_MESSAGE = "clientuser/sendphone/sendPhoneAction!sendPhoneNum.as";

    /**
     * 登录
     */
    public static final String LOGIN = "clientuser/userinfo/loginAction!login.as";

    /**
     * 注销接口
     */
    public static final String LOGOUT = "clientuser/userinfo/loginAction!logout.as";

    /**
     * 忘记密码
     */
    public static final String FORGET_PASSWORD = "clientuser/userinfo/loginAction!resetPassword.as";

    /**
     * 修改用户信息
     */
    public static final String CHANGE_USERINFO = "client/userinfo/clientUserInfoAction!updateClientUserInfo.as";

    /**
     * 用户详情接口
     */
    public static final String USERINFO = "client/userinfo/clientUserInfoAction!findUserById.as";

    /**
     * 餐厅简介
     */
    public static final String INTRODUCT = "introduct/introductAction!findAllIntroduct.as";

    /**
     * 用户协议
     */
    public static final String AGREEMENT = "config/agreement/agreementAction!findAgreement.as";

    /**
     * 会员规则 storeId=1
     */
    public static final String VIPRULES = "store/viprules/storeVipRulesAction!findAllVipRules.as";

    /**
     * 版本更新
     */
    public static final String VERSIONUPDATE = "config/version/versionAction!findNewVersion.as";

    /**
     * 版本更新
     */
    public static final String VERSIONINFO = "config/version/versionAction!findNewVersion.as";

    /**
     * 意见反馈接口
     */
    public static final String FEEDBACK = "client/feedback/clientFeedBackAction!add.as";

    /**
     * 帮助手册接口
     */
    public static final String HELP = "client/help/clientHelpAction!findHelp.as";

    /**
     * 菜品排行榜
     */
    public static final String RANK = "dishes/dishesAction!findDishesSales.as";

    /**
     * 菜品排行榜 - 分类
     */
    public static final String RANKSORT = "dishessalessort/dishesSalesSortAction!findAllDishesSalesSort.as";

    /**
     * 特产
     */
    public static final String FEATURES = "dishes/dishesAction!findIsFeaturesDishes.as";

}
