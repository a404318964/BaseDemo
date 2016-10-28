package com.zwj.basedemo.constant;


public interface Constant {

    long LOSE_EFFICICY_TIME = 120*60 * 1000;


    /************************************
     * 参数
     *************************************/

    // 车源参数
    String PARAM_CATALOG_ID = "CatalogID"; // 目录ID
    String PARAM_PRICE = "PriceID"; // 价格
    String PARAM_BRAND = "Brand"; // 品牌
    String PARAM_CITY_ID = "CityID"; // 城市id
    String PARAM_COLOR = "Color";
    String PARAM_IS_URGENT = "IsUrgent"; // 降价急售
    String PARAM_PAGE_NO = "PageNo"; // 第几页
    String PARAM_PAGE_NUMBER = "pageNum";
    String PARAM_STYLE = "Style"; // 车型
    String PARAM_SERIES = "Series"; // 车系
    String PARAM_CAR_YEAR = "CarYearID"; // 车龄
    String PARAM_MILEAGE = "MileageID"; // 行驶里程
    String PARAM_GEAR_BOX = "GearBox"; // 变速箱
    String PARAM_OUTPUT_VOLUME = "OutputVolume"; // 排放量
    String PARAM_DISCHARGE_STANDARD = "DischargeStandard"; // 排放标准
    String PARAM_COUNTRY = "Country"; // 国别
    String PARAM_SORT = "Sort";
    String PARAM_CAR_NO = "CarNo"; // 车辆编号
    // String PARAM_INCLUDE_FLAG = "IncludeFlag"; // 区域
    String PARAM_QUASI_NEW_CAR = "QuasiNewCar"; // 准新车
    //	String PARAM_WOMEN_CAR = "WomenCar";
    String PARAM_SEVEN_SEAT = "SevenSeat";
    String PARAM_ALLIANCE_CODE = "AllianceCode";
    String PARAM_TEST_REPORT_CODE = "TestReportCode";
    String PARAM_PUREG_FLAG = "PuregFlag";

    // 进口车
    String PARAM_CAR_FLAG = "CarFlag";
    String PARAM_PRICE_START = "Price_Start";
    String PARAM_PRICE_END = "Price_End";
    String PARAM_AGIOCAR = "AgioCar";
    String PARAM_HOTCAR = "HotCar";

    String CAR_FLAG_TEXT = "CarFlagText";
    String HOTCAR_TEXT = "HotCarText";

    String BRAND_NAME = "brandName"; // 品牌名称
    String SERIES_NAME = "seriesName"; // 车系名称
    String SPEC_NAME = "specName"; // 车辆型号名称
    String SPEC_ID = "specId";
    String COLOR_NAME = "colorName";
    String PRICE_SECTION = "priceSection";
    String CAR_YEAR_TEXT = "carYearText";
    String BRAND_AND_SERIES = "brandAndSeries"; // 品牌和车系的拼接
    String ALL = "all";// 品牌+车系+车型的拼接
    String STYLE_NAME = "styleName"; // 车型名称
    String MILEAGE_TEXT = "mileageText";
    String GEAR_BOX_TEXT = "GearBoxText";
    String OUTPUT_VOLUME_TEXT = "OutputVolumeText";
    String DISCHARGE_STANDARD_TEXT = "DischargeStandardText";
    String COUNTRY_NAME = "CountryName";
    String CITY_NAME = "cityName";
    String CAR_PIC_BEAN_LIST = "carPicBeanList";
    String SEARCH_BRAND_AND_SERIES_BEAN = "searchBrandAndSeriesBean";
    String CURRENT_CITY = "currentCity";
    String CURRENT_CITY_ID = "currentCityId";
    String TEST_REPORT = "carDetailBean";
    String TEST_REPORT_DETAIL_LIST = "testReportDetailBeanList";
    String REPORT_CAR_SURFACE_CASE_LIST = "reportCarSurfaceCaseList";
    String REPORT_CAR_INFO = "reportCarInfoBean";
    String IS_NEED_GET_SPEC = "isNeedGetSpec";
    String IS_HIDE_HOT_BRAND = "isHideHotBrand";
    String IS_HIDE_NO_LIMIT = "isHideNoLimit";
    String CAR_COUNT = "carCount";

//    int PICTURE = 0X000011;
    String NO_QUERY_RESULT = "-101"; // 没有搜索出相关结果时返回

    int REQUEST_CODE_SELECT_BRAND = 12345;
    int REQUEST_CODE_MORE_FILTER_CONDITION = 12346;
    int REQUEST_CODE_SELECT_COLOR = 12347;
    int REQUEST_CODE_SELECT_CITY = 12348;
    int REQUEST_CODE_SEARCH_CAR = 12349;
    int REQUEST_CODE_FILL_GUACA_REPORT = 12350;
    int REQUEST_CODE_MESSAGE_DETAIL = 12351;     // 进入消息详情

    /**************************
     * 我的模块区分点击登录的按钮
     ********************/
    int REQUEST_CODE_LOGIN = 0x1001;
    int REQUEST_CODE_LOGIN_BUY = 0x1002;
    int REQUEST_CODE_LOGIN_SELL = 0x1003;
    int REQUEST_CODE_LOGIN_MINE = 0x1004;
    int REQUEST_CODE_LOGIN_DELEGATE = 0x1005;
    int REQUEST_CODE_LOGIN_COUPON = 0x1006;
    int REQUEST_CODE_LOGIN_STATICS = 0x1007;
    int REQUEST_CODE_LOGIN_ACCOUNT = 0X1008;
    int REQUEST_CODE_LOGIN_HOME_USER = 0X1008;
//    int REQUEST_CODE_LOGIN_FASTSELL = 0x1009;

    /*************************
     * 买车订单中按钮的requestcode
     *****************/
    int REQUEST_CODE_REVOKE = 0X2001;// 撤单
    int REQUEST_CODE_PRE = 0X2002;// 预付款
    int REQUEST_CODE_PAY = 0X2003;// 购车款
    int REQUEST_CODE_RATING = 0x2004;//买家评价
    /*********************
     * 卖车订单的requestcode
     ***************************/
    int REQUEST_CODE_COUPON = 0X3001;// 抵用券
    int REQUEST_CODE_ORDER_REVOKE = 0X3002;// 撤单
    int REQUEST_CODE_ORDER_CONFIRM = 0X3003;// 确认订单
    int REQUEST_CODE_OWNER_RATING = 0X3004;//车主评价
    /********************
     * 我的车源
     **************************************/
    int REQUEST_CODE_UNSHELVE = 0x4001;// 下架
    int REQUEST_CODE_EDIT = 0x4002;// 编辑
    int REQUEST_CODE_WRITE = 0x4003;// 填写检测报告
    int REQUEST_CODE_REPUBLISH = 0x4004;// 重新发布
    int REQUEST_CODE_CONFIRM = 0x4005;// 确认车源
    int REQUEST_CODE_PUBLISH = 0x4006;//发布车源

    /**
     * 手机号码的正则表达式
     */
    String PHONE_REG = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";

    String KEY_ITEM = "item";
    String KEY_FILE = "file";
    String KEY_POSITION = "position";
//    String  KEY_UPDATE="update_buy_car";//表示开启关闭同行价的时候买车界面的刷新

    /**
     * 文件路径
     */
    String FILE_CITY_NAME = "city.json"; // 城市的json包
    String FILE_CHECK_ITEM = "check_item.json"; // 检测项的id、名称json文件名
    String FILE_BRAND = "Brand.Json"; // 品牌json文件名
    //    String FILE_HOT_CITY = "hot_city.json"; // 热门区域json文件路径
    String FILE_BANNER = "banner.txt";
    String FIlE_CITY_DB = "cities.db";
    String FILE_HOT_BRAND_DATA = "host_brand_data.json";
    String FILE_TIME = "time";//session有效期保存的路径
    String FOLDER_IMG_CACHE = "imgcache";//图片缓存的文件夹（在/sdcard/Android/data/<application package>/cache目录下）
    String FILE_GETUI_CLIENT_ID = "gtclientid";     // 个推clientId保存的文件名

    // Cookies
    String COOKIE_NAME = ".AspNet.ApplicationCookie";
    String COOKIE_SESSION = "ASP.NET_SessionId";
    // 保存获取验证码的cookie
//    String COOKIE_VERIFY_NAME = ".AspNet.ApplicationCookie";
//    String COOKIE_VERIFY_SESSION = "ASP.NET_SessionId";

    // 友盟相关参数
    String UM_EVENT_DIAL = "dial"; // 拨打电话事件id
    String UM_EVENT_COMFIRM_ORDER = "confirmOrder"; // 确认订单数据
    String UM_EVENT_BROWSE_CAR = "browseCar"; // 查看汽车

    String UM_EVENT_PARAM_PRICE_SECTION = "priceSection"; // 价格区间

    /**
     * 密码的正则表达式
     */
    String REG_PASSWORD = "^[a-zA-Z0-9_]{4,16}$";

    /**
     * 是否显示同行价
     */
//    String KEY_SHOW_WHOLE = "isShowWhole";

    /**
     * app更新的相关字段名称
     */
    String APP_URL = "appUrl";
    String APP_NAME = "appName";

    String APP_FOLDER_APK = "/apk"; // apk文件在sdcard上的文件目录
    String APP_FOLDER_TEMP_PIC = "/temp_pic"; // 临时图片文件在sdcard上的文件目录

    String SP_KEY_IGNORE_VERSION_CODE = "ignoreVersionCode";

    String THEME_COLOR = "#df2626";
    String PAGE_NUMBER = "16";  // 每页显示的车辆数
}
