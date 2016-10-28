package com.zwj.basedemo.constant;

/**
 * Created by Administrator on 2016/5/31.
 */
public interface UrlConstant {

//    String DOMAIN = "wx.chetongxiang.com";
//    String BASE_URL = "http://wx.chetongxiang.com";
//    String DOMAIN = "wxtest.chetongxiang.net";
//    String BASE_URL = "http://wxtest.chetongxiang.net";
    String DOMAIN = "192.168.0.218:8082";
    String BASE_URL = "http://192.168.0.218:8082";
//    String DOMAIN = "demo.chetongxiang.com";
//    String BASE_URL = "http://demo.chetongxiang.com";

    /**
     * 进口车服务器地址
     */
    String IMPORTCAR_BASE_URL = "http://192.168.0.198:100";

    // ============= 车源数据相关url start =====================
    /**
     * 首页车源数据
     */
//    String GET_HOME_DATA_URL = BASE_URL + "/Common/Car/RequestHomeData";

    /**
     * 搜索车源(买车界面)
     */
//    String POST_SEARCH_CAR = BASE_URL + "/common/car/SearchCar";
    String POST_SEARCH_CAR = BASE_URL + "/common/car/SearchCarForMobile";

    /**
     * 获取车辆详情（车辆详情页）
     * 参数：CarNo
     */
    String GET_CAR_DATA = BASE_URL + "/common/car/GetCardata";

    /**
     * 帮买广场
     */
    String POST_SEARCH_PUBLISH = BASE_URL + "/Pureg/SearchPublish";

    /**
     * 获取当前共多少条“求购成功”的信息
     */
    String POST_GET_BUY_COMPLETED_COUNT = BASE_URL + "/Pureg/GetBuyCompletedCount";

    /**
     * 获取当前共多少条有效的求购信息
     */
    String POST_GET_VALID_COUNT = BASE_URL + "/Pureg/GetValidCount";

    /**
     * 获取当前共多少条“求购中”信息
     */
    String POST_GET_BUYING_COUNT = BASE_URL + "/Pureg/GetBuyingCount";

    // ------------- 车源数据相关url end ------------------------


    // ============= 车商信息相关url start =====================
    /**
     * 获取联盟商诚信信息
     */
    String GET_CREDIT_INFO_BY_ALLIANCE_CODE = BASE_URL
            + "/common/Car/GetCreditInfoByAllianceCode";

    /**
     * 获取个人诚信信息
     */
    String GET_CAR_CREDIT_INFO = BASE_URL
            + "/common/car/GetCarCreditInfoByCarNo";

    /**
     * 获取指定店铺的车辆列表
     */
    String POST_SALE_MAN_GET_CAR_LIST_URL = BASE_URL
            + "/common/car/SaleManGetCarList";
    // ------------- 车商信息相关url end --------------------------


    // ============= 品牌相关url start =====================
    /**
     * 根据品牌id获取车系
     */
    String GET_SERIES_BY_BRAND_ID = BASE_URL + "/Common/Carbrand/GetSeries";

    /**
     * 根据品牌及车系获取车辆型号
     * 参数:brandid,seriesid
     */
    String GET_SPEC = BASE_URL + "/carbrand/GetSpecByBrandSeries";
    // ------------- 品牌相关url end --------------------------

    // ============= 检测报告相关url start =====================
    /**
     * 根据检测报告code获取检测报告信息
     */
    String GET_TEST_REPORT_WITH_CODE_URL = BASE_URL
            + "/Alliance/TestReport/GetTestReportWithCode";

    /**
     * 添加检测报告车辆信息
     */
    String POST_ADD_TEST_REPORT_CAR_INFO = BASE_URL
            + "/Alliance/TestReportCarInfo/AddTestReportCarInfo";

    /**
     * 上传检测报告
     */
    String POST_WRITE_TEST_REPORT = BASE_URL
            + "/Alliance/TestReport/WriteTestReport";

    String UPLOAD_TEST_REPORT_PIC = BASE_URL + "/file/UpLoadTestReportPic"; // 上传检测报告图片
    // ------------- 检测报告相关url end --------------------------


    // ============= 城市相关url start =====================
    /**
     * 获取已开通的城市信息
     */
    String GET_OPEN_CITY_INFOS = BASE_URL + "/direct/GetHomeProvinceList";

    // ------------- 城市相关url end --------------------------

    String GET_CITYS_BY_PARENT_CITY_ID_URL = BASE_URL
            + "/city/GetCitysByParentCityID"; // 根据上一级的城市获取子城市

    // ============= 查询相关url start =====================
    /**
     * 查询
     * 参数：keyword
     */
    String QUERY_URL = BASE_URL + "/Common/Carbrand/SearchBrand";

    /**
     * 查询店铺
     */
    String QUERY_SHOP_URL = BASE_URL
            + "/alliance/alliance/GetAllianceByKeyword";

    // ------------- 查询相关url end --------------------------

    // ============= 商家入驻 url start =====================
    /**
     * 获取入驻商家数量
     */
    String GET_ALLIANCE_COUNT = BASE_URL + "/Alliance/Alliance/GetAllianceCount";

    /**
     * 商家入驻
     */
    String POST_ADD_JOIN_MESSAGE = BASE_URL + "/JoinMessage/JoinMessage/AddJoinMessage";
    // ------------- 商家入驻 url end --------------------------

    // ============= other url start =====================
    String GET_BANNER_URL = "http://app.chetongxiang.com/download/json/app_banner.json";
    String GET_CAR_WORRY_SELL_WITH_CODE = BASE_URL
            + "/Common/CarWorrySell/GetCarWorrySellWithCode"; // 获取急售车源详情

    /**
     * 获取车辆信息(确认订单)
     */
    String GET_CAR_INFO = BASE_URL + "/order/GetCarInfo";

    /**
     * 提交买车订单
     */
    String POST_SUBMIT_ORDER = BASE_URL + "/Order/userbuy";

    /**
     * 获取各个版本的最新信息(apk以及一些数据包)
     */
    String GET_VERSION = BASE_URL + "/Common/VersionMange/GetVersion";
    // ------------- other url end --------------------------


    /****************** 个人中心 ***************************/
    /********************
     * 登录
     *****************/
    // 登录
    String LOGIN = BASE_URL + "/account/OutAndAllanceLogin";
    // 注销
    String GET_LOGIN_OUT = BASE_URL + "/account/wxunbinding";
    // 获取短信验证码
    String GET_VERIFY = BASE_URL + "/common/message/SendValiadeCode";
    // 验证短信验证码
    String POST_VERIFY = BASE_URL + "/common/message/ValideMessageCode";
    // 使用短信验证码登录
    String LOGIN_WITH_VERIFY = BASE_URL + "/account/AppLogin";
    /************************
     * 买车订单
     *************************/
    // 获取买车订单
    String USER_GET_ORDER_LIST = BASE_URL + "/order/UserGetOrderList";
    // 支付预付款
    String POST_USER_PRE_PAY = BASE_URL + "/Order/UserPrePay";
    // 使用优惠券
    String POST_USE_COUPON = BASE_URL + "/Order/OrderUseDiscountPolicy";
    // 支付购车款
    String POST_PAY_ALL = BASE_URL + "/Order/UserPayAll";
    // 根据订单号获取订单信息
    String GET_ORDER_INFO_WITH_CODE = BASE_URL + "/order/GetOrderInfoWithCode";
    // 确认撤销订单
    String POST_REVOKE_CONFIRM = BASE_URL + "/order/UserRevokeRequest";
    // 买家评价
    String POST_USER_FEEDBACK = BASE_URL + "/order/UserFeedback";
    /********************
     * 卖车订单*******************
     */
    // 获取卖车订单
    String CAR_OWNER_GET_ORDER_LIST = BASE_URL + "/order/CarOwnerGetOrderList";
    // 申请撤单
    String POST_CAR_OWNER_REVOKE_REQUEST = BASE_URL
            + "/order/CarOwnerRevokeRequest";
    // 修改成交价
    String POST_UPDATE_ORDER = BASE_URL + "/order/CarOwnerUpdateOrder";
    // 确认订单
    String POST_ORDER_CONFIRM = BASE_URL + "/order/SellerConfirmPrice";
    /**
     * 获取服务费
     */
    String POST_GET_SERVICE_FEE = BASE_URL + "/order/GetServiceFee";
    /**
     * 卖家评价
     */
    String POST_CAROWNER_FEED_BACK = BASE_URL + "/order/CarOwnerFeedback";

    /***********************
     * 我的车源
     ****************************/
    // 获取联盟商的车源
    String GET_MY_CAR = BASE_URL + "/common/car/GetAllianceCarList";
    // 获取个人的车源
    String GET_MY_CAR_OF_PERSON = BASE_URL + "/common/car/GetCarsByUserID";
    // 下架->根据CarNo获取车辆信息
    String GET_CAR_DATA_FOR_UPDATE = BASE_URL
            + "/common/car/GetCardataForUpdate";
    // 车辆下架
    String POST_CAR_REVOKE = BASE_URL + "/common/car/PostCarRevoke";
    // 发布车源
    String POST_CAR_PUBLISH = BASE_URL + "/common/car/Publish";
    // 上传车辆图片
    String POST_UPLOAD_PIC = BASE_URL + "/common/file/UpLoadImgByBase64ForCar";
    // 确认当前车架号是否被使用
    String POST_IF_FRAME_EXIST = BASE_URL + "/car/CheckIfExsitFrame";
    // 编辑车源
    String POST_EDIT_CAR = BASE_URL + "/common/car/updatecar";
    // 确认车源
    String POST_CONFIRM_CAR = BASE_URL + "/common/car/ConfirmPublishCarModel";
    // 重新发布车源
    String POST_REPUBLISH_CAR = BASE_URL + "/common/car/RepublishCar";
    // 删除图片
    String POST_DELETE_IMG = BASE_URL + "/file/DelImage";
    // 获取未确定车型的数据
    String POST_UNCURTAIN_DATA = BASE_URL + "/car/GetCatalogPara";
    /**
     * *********************优惠券
     **********************************/
    // 获取优惠券列表
    String GET_COUPON = BASE_URL
            + "/DiscountPolicy/DiscountPolicy/UserGetDiscountPolicy";
    /***************************
     * 车源统计
     ******************************/
    // 获取车源统计
    String GET_CAR_STATISTIC = BASE_URL + "/common/car/GetMyCarDetailForUser";
    /*****************************
     * 急售发布
     ***************************************/
    // 获取急售车源列表
    String GET_WORRY_CAR = BASE_URL
            + "/Common/CarWorrySell/GetCarWorrySellListWithFlag";
    // 获取指导价
    String POST_GET_GUIDE_PRICE = BASE_URL + "/common/car/GetGuidePrice";
    // 发布急售车源
    String POST_PUBLISH_FASTSELL = BASE_URL
            + "/Common/CarWorrySell/PublishWorryCar";

    /******************************
     * 委托评估
     *******************/
    // 获取委托评估数据
    String GET_DELEGATE = BASE_URL
            + "/Alliance/AppraiserOrder/UserGetOrderList";

    /******************
     * 个人账户模块
     **********************/
    // 当前账户是否设置过交易密码
    String GET_IS_SET_PASSWORD = BASE_URL
            + "/account/CheckCurrentUserIfHasTradePwd";
    // 获得联盟商的信息
    String GET_ALLIANCE_INFO = BASE_URL + "/account/GetCurrentAllianceinfo";
    // 获取当前用户的信息
    String GET_CURRENT_USER_INFO = BASE_URL + "/account/GetCurrentUserinfo";
    // 设置交易密码
    String GET_SET_PASSWORD = BASE_URL + "/account/UpdatingUser";
    // 修改交易密码
    String POST_CHANGE_PASSWORD = BASE_URL + "/account/ChangeTradePwd";
    // 确认提现
    String POST_EXTRACTION = BASE_URL
            + "/Financial/FinanceNeedPay/PostExtraction";
    // 绑定银行卡
    String POST_UPDATE_BANK = BASE_URL + "/account/UpdatingUser";
    // 上传头像到阿里云服务器
    String POST_UPDATE_HEAD_PIC = BASE_URL
            + "/common/file/UpLoadImgByBase64ForUser";

    /****************************
     * 设置模块
     ******************************/
    // 意见反馈
    String POST_FEEDBACK = BASE_URL + "/System/Feedback/PostInfo";

    /***************************
     * 我要卖车
     **********************/
    // 获取在售车源数量
    String GET_SELLING_COUNT = BASE_URL + "/Common/car/GetSellingCount";
    // 提交卖车申请
    String POST_SELL_CAR_ASK = BASE_URL
            + "/JoinMessage/JoinMessage/AddJoinMessage";


    /***
     * 一键帮买
     */
    String POST_FAST_BUY = BASE_URL + "/Pureg/PostPuregInfo";

    /**
     * 一键帮卖
     */
    String POST_FAST_SELL = BASE_URL + "/SalesDemand/PublishCar";


    // 消息
    /**
     * 获取当前登陆用户的系统消息列表
     */
    String GET_MSG_BY_USER_ID = BASE_URL + "/System/SysMessage/GetMsgByUserID";

    /**
     * 删除系统消息
     */
    String POST_DELETE_MSG_BY_ID = BASE_URL + "/System/SysMessage/DeleteMsg";

    /**
     * 批量删除系统消息
     */
    String POST_DELETE_MSG_LIST = BASE_URL + "/System/SysMessage/DeleteMsgList";

    /**
     * 设置消息为已读
     */
    String POST_SET_MSG_READED = BASE_URL + "/System/SysMessage/SetReaded";

    /**
     * 批量设置消息为已读
     */
    String POST_SET_READED_FOR_LIST = BASE_URL + "/System/SysMessage/SetReadedForList";

    /**
     * 获取未读的消息数量
     */
    String GET_UNREADED_MSG_COUNT = BASE_URL + "/System/SysMessage/GetUnReadedMsgCount";

    // ******************** 进口车 begin ********************
    /**
     * 进口车车源列表
     */
    String POST_IMPORTCAR_SEARCH_CAR = IMPORTCAR_BASE_URL + "/api_car/SearchCar";

    /**
     * 获取进口车辆详情
     */
    String GET_IMPORT_CAR_DETAIL = IMPORTCAR_BASE_URL + "/api_car/GetCarInfo";

    /**
     * 提交订单(进口车)
     */
    String POST_ADD_ORDER = IMPORTCAR_BASE_URL + "/api_order/AddOrder";
    // ==================== 进口车 end ====================


    // ******************** 新车 begin ********************
    /**
     * 查询车系列表
     */
    String POST_NEWCAR_SEARCH_SERIES = IMPORTCAR_BASE_URL + "/API_NewCar/SearchSeries";

    /**
     * 查询车系列表
     */
    String POST_NEWCAR_GET_SERIES_CAR_LIST = IMPORTCAR_BASE_URL + "/API_NewCar/GetSeriesCarList";

    /**
     * 获取推荐车源列表
     */
    String POST_NEWCAR_GET_RECOMMEND_CAR_SERY = IMPORTCAR_BASE_URL + "/API_NewCar/GetRecommendCarSery";

    /**
     * 获取车源详细信息
     */
    String POST_NEWCAR_GET_CAR_INFO = IMPORTCAR_BASE_URL + "/API_NewCar/GetCarinfo";

    /**
     * 获取车源图片信息
     */
    String POST_NEWCAR_GET_CAR_PICS = IMPORTCAR_BASE_URL + "/API_NewCar/GetCarPics";

    /**
     * 提交订单
     */
    String GET_NEWCAR_ADD_ORDER = IMPORTCAR_BASE_URL + "/API_NewCarOrder/AddOrder";

    /**
     * 获取车源参数配置信息
     */
    String POST_NEWCAR_GET_CAR_PARAMS_WITH_CARNO = IMPORTCAR_BASE_URL + "/API_NewCar/GetCarParamsWithCarNo";

    /**
     * 获取车源视频信息
     */
    String POST_NEWCAR_GET_CAR_VIDEOS = IMPORTCAR_BASE_URL + "/API_NewCar/GetCarVideos";
    // ==================== 新车 end ====================
}
