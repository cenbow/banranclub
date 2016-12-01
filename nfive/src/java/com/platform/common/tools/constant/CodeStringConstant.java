package com.platform.common.tools.constant;

/**
 * CodeString常量类
 *
 * @author hercules.chen
 */
public class CodeStringConstant {

	// 是否
	public static final String TRUE_FALSE_FLAG = "1000";
	// 是否代码的是
	public static final String CS_1000_TRUE = "100000000001";
	// 是否代码的否
	public static final String CS_1000_FALSE = "100000000002";

	// 用户锁定标识
	public static final String CS_3000_LOCK_FLAG = "3000";
	// 用户锁定标识 未锁定
	public static final String CS_3000_LOCK_FLAG_KEY = "300000000001";

	// 资源纬度
	public static final String RESOURCE_TYPE = "3001";

	/** 资源功能类型 3002 */
	public static final String RESOURCE_FUC_TYPE = "3002";
	/** 资源功能类型 300200000000 未输入 */
	public static final String RESOURCE_FUC_NOT_INPUT = "300200000000";
	/** 资源功能类型 300200000001 菜单类 */
	public static final String RESOURCE_FUC_TYPE_MENU = "300200000001";
	/** 资源功能类型 300200000002 操作类 */
	public static final String RESOURCE_FUC_TYPE_OPERATOR = "300200000002";
	/** 资源功能类型 300200000003 数据类 */
	public static final String RESOURCE_FUC_TYPE_DATA = "300200000003";
	/** 资源功能类型 300299999998 其他 */
	public static final String RESOURCE_FUC_TYPE_OTHER = "300299999998";
	/** 资源功能类型 300299999999 错误类 */
	public static final String RESOURCE_FUC_TYPE_ERROR = "300299999999";



	// 公众号类型
	public static final String ACCOUNT_TYPE = "5010";
    //未输入
    public static final String  ACCOUNT_TYPE_NOTINPUT="501000000000";
    //订阅号
    public static final String ACCOUNT_TYPE_SUBSCRIBE="501000000001";
    //服务号
    public static final String ACCOUNT_TYPE_SERVICE="501000000002";

	/** 菜单动作类型 */
	public static final String MENU_TYPE = "5011";
	/** 501100000000 未输入 */
	public static final String MENU_TYPE_NOTINPUT = "501100000000";
	/** 501100000001 链接页面 */
	public static final String MENU_TYPE_HREF = "501100000001";
	/** 501100000002 图文消息 */
	public static final String MENU_TYPE_IMAGE_TEXT = "501100000002";
	/** 501100000003 文本消息 */
	public static final String MENU_TYPE_TEXT = "501100000003";
	/** 501100000004 图片消息 */
	public static final String MENU_TYPE_IMAGE = "501100000004";
	/** 501100000005 音频消息 */
	public static final String MENU_TYPE_AUDIO = "501100000005";
	/** 501100000006 视频消息 */
	public static final String MENU_TYPE_VIDEO = "501100000006";
    /** 501100000006 自定义消息 */
    public static final String MENU_TYPE_ACTION = "501100000007";
	/** 501199999998 其它 */
	public static final String MENU_TYPE_OTHER = "501199999998";
	/** 501199999999 错误 */
	public static final String MENU_TYPE_ERROR = "501199999999";

	/** 素材附件类型 **/
	public static final String CS_5012_MATERIAL_TYPE = "5012";
	public static final String CS_5012_MATERIAL_TYPE_IMG = "501200000001";// 图片类型
	public static final String CS_5012_MATERIAL_TYPE_VOICE = "501200000002";// 声音类型
	public static final String CS_5012_MATERIAL_TYPE_VIDEO = "501200000003";// 视频类型
	public static final String CS_5012_MATERIAL_TYPE_THUMB = "501200000004";// 缩略图类型
	public static final String CS_5012_MATERIAL_TYPE_OTHER = "501299999998";// 其他类型

	// 图片规格
	public static final String IMAGE_SPEC = "5013";

	// 图文素材类型
	public static final String IMAGE_TEXT_TYPE = "5051";
	public static final String IMAGE_TEXT_TYPE_SINGLE = "505100000001";
	public static final String IMAGE_TEXT_TYPE_MULTI = "505100000002";

	// 关键字回复类型
	public static final String KEYWORK_TYPE = "5052";
	// 关键字回复类型的未输入
	public static final String CS_5052_REPLAY_NOT_IN = "505200000000";
	// 关键字回复类型的图文消息
	public static final String CS_5052_REPLAY_ARTICLE_MSG = "505200000001";
	// 关键字回复类型的文本消息
	public static final String CS_5052_REPLAY_TEXT_MSG = "505200000002";
	// 关键字回复类型的图片消息
	public static final String CS_5052_REPLAY_PICTURE_MSG = "505200000003";
	// 关键字回复类型的音频消息
	public static final String CS_5052_REPLAY_VOICE_MSG = "505200000004";
	// 关键字回复类型的视频消息
	public static final String CS_5052_REPLAY_VIDEO_MSG = "505200000005";
	// 关键字回复类型的其他
	public static final String CS_5052_REPLAY_OTHER_MSG = "505299999998";
	// 关键字回复类型的错误
	public static final String CS_5052_REPLAY_ERROR_MSG = "505299999999";

	// 关键字匹配类型
	public static final String KEYWORK_MATCHING_TYPE = "5053";
	// 关键字匹配类型的未输入
	public static final String CS_5053_MATCH_TYPE_NOT_IN = "505300000000";
	// 关键字匹配类型的模糊匹配
	public static final String CS_5053_MATCH_TYPE_LIKE = "505300000001";
	// 关键字匹配类型的完全匹配
	public static final String CS_5053_MATCH_TYPE_ALL = "505300000002";
	// 关键字匹配类型的其他
	public static final String CS_5053_MATCH_TYPE_OTHER = "505399999998";
	// 关键字匹配类型的错误
	public static final String CS_5053_MATCH_TYPE_ERROR = "505399999999";

	// 特殊回复规则
	public static final String REPLY_RULE = "5054";
	// 特殊回复规则的未输入
	public static final String CS_5054_REPLY_RULE_NOT_IN = "505400000000";
	// 特殊回复规则的首次关注
	public static final String CS_5054_REPLY_RULE_FIRST = "505400000001";
	// 特殊回复规则的关键字无匹配
	public static final String CS_5054_REPLY_RULE_NOT_KEYWORD = "505400000002";
	// 特殊回复规则的未输入
	public static final String CS_5054_REPLY_RULE_OTHER = "505499999998";
	// 特殊回复规则的未输入
	public static final String CS_5054_REPLY_RULE_ERROR = "505499999999";

	// 群发区分
	public static final String CS_5059_SEND_DIST = "5059";
	// 群发区分-未输入
	public static final String CS_5059_SEND_DIST_NOINPUT = "505900000000";
	// 群发区分-微信群发
	public static final String CS_5059_SEND_DIST_WEIXIN = "505900000001";
	// 群发区分-自定义群发
	public static final String CS_5059_SEND_DIST_CUSTOM = "505900000002";
	// 群发区分-其它
	public static final String CS_5059_SEND_DIST_OTHERS = "505999999998";
	// 群发区分-错误
	public static final String CS_5059_SEND_DIST_ERROR = "505999999999";

	// 群发对象
	public static final String CS_5060_CROWD_SEND = "5060";
	// 群发对象-全部用户
	public static final String CS_5060_CROWD_SEND_ALL = "506000000001";
	// 群发对象-微信分组
	public static final String CS_5060_CROWD_SEND_WEIXIN = "506000000002";
	// 群发对象-粉丝群
	public static final String CS_5060_CROWD_SEND_FANS = "506000000003";
	// 群发对象-活动组
	public static final String CS_5060_CROWD_SEND_ACTIVITY = "506000000004";
	// 群发对象-未输入
	public static final String CS_5060_CROWD_SEND_NOINPUT = "506000000000";
	// 群发对象-其他
	public static final String CS_5060_CROWD_SEND_OTHERS = "506099999998";
	// 群发对象-错误
	public static final String CS_5060_CROWD_SEND_ERROR = "506099999999";

	// 群发接口
	public static final String CS_5061_SEND_IF = "5061";
	// 群发接口-未输入
	public static final String CS_5061_SEND_IF_NOINPUT = "506100000000";
	// 群发接口-客服消息接口
	public static final String CS_5061_SEND_IF_CUSIN = "506100000001";
	// 群发接口-高级群发接口
	public static final String CS_5061_SEND_IF_CROWD = "506100000002";
	// 群发接口-其他
	public static final String CS_5061_SEND_IF_OTHERS = "506199999998";
	// 群发接口-错误
	public static final String CS_5061_SEND_IF_ERROR = "506199999999";

	// 微信群发消息类型
	public static final String CS_5062_SEND_TYPE = "5062";
	// 微信群发消息类型-未输入
	public static final String CS_5062_SEND_TYPE_NOINPUT = "506200000000";
	// 微信群发消息类型-文本消息
	public static final String CS_5062_SEND_TYPE_TEXT = "506200000001";
	// 微信群发消息类型-图片消息
	public static final String CS_5062_SEND_TYPE_IMAGE = "506200000002";
	// 微信群发消息类型-图文消息
	public static final String CS_5062_SEND_TYPE_ARTCLE = "506200000003";
	// 微信群发消息类型-音频消息
	public static final String CS_5062_SEND_TYPE_VOICE = "506200000004";
	// 微信群发消息类型-视频消息
	public static final String CS_5062_SEND_TYPE_VIDEO = "506200000005";
	// 微信群发消息类型-其他
	public static final String CS_5062_SEND_TYPE_OTHERS = "506299999998";
	// 微信群发消息类型-错误
	public static final String CS_5062_SEND_TYPE_ERROR = "506299999999";

	// 消息发送状态
	public static final String CS_5063_SEND_STATUS = "5063";
	// 消息发送状态-未输入
	public static final String CS_5063_SEND_STATUS_NOINPUT = "506300000000";
	// 消息发送状态-处理中
	public static final String CS_5063_SEND_STATUS_PROCESSING = "506300000001";
	// 消息发送状态-发送成功
	public static final String CS_5063_SEND_STATUS_SUCCESS = "506300000002";
	// 消息发送状态-发送失败
	public static final String CS_5063_SEND_STATUS_FAILED = "506300000003";
	// 消息发送状态-审核失败
	public static final String CS_5063_SEND_STATUS_AUDITFAILURE = "506300000004";
	// 消息发送状态-其他
	public static final String CS_5063_SEND_STATUS_OTHERS = "506399999998";
	// 消息发送状态-错误
	public static final String CS_5063_SEND_STATUS_ERROR = "506399999999";

	// 图文项目类型
	public static final String IMAGE_TEXT_PEOJECT_TYPE = "5055";

	// 图文项发布状态
	public static final String IMAGE_TEXT_RELEASE_STATE = "5056";

	// 微信其他
	public static final String PLATFORM_OTHER = "99999998";

	// 微信错误
	public static final String PLATFORM_ERROR = "99999999";

	// 微信未输入
	public static final String PLATFORM_NOTINPUT = "00000000";

	// 系统管理员
	public static final String SYSTEM_OPERATOR = "9999";

	// 性别选择
	public static final String CS_1015_SEX_FLAG = "1015";
	// 性别未输入
	public static final String CS_1015_SEX_FLAG_NOINPUT = "101500000000";
	// 性别男
	public static final String CS_1015_SEX_FLAG_MALE = "101500000001";
	// 性别女
	public static final String CS_1015_SEX_FLAG_FEMALE = "101500000002";
	// 性别未知
	public static final String CS_1015_SEX_FLAG_UNKNOWN = "101500000003";
	// 性别其他
	public static final String CS_1015_SEX_FLAG_OTHERS = "101599999998";
	// 性别错误
	public static final String CS_1015_SEX_FLAG_ERROR = "101599999999";

	// 同步标识
	public static final String CS_5065_SYNC_FLAG = "5065";
	// 同步中
	public static final String CS_5065_SYNC_ONGING = "506500000001";
	// 同步成功
	public static final String CS_5065_SYNC_SUCCESS = "506500000002";
	// 同步失败
	public static final String CS_5065_SYNC_ERROR = "506500000003";

	// 操作类型（用户行为）
	public static final String CS_5064_USERACTION = "5064";
	// 操作类型未输入（用户行为）
	public static final String CS_5064_USERACTION_NOINPUT = "506400000000";
	// 操作类型菜单（用户行为）
	public static final String CS_5064_USERACTION_MENU = "506400000001";
	// 操作类型关键字（用户行为）
	public static final String CS_5064_USERACTION_KEYWORD = "506400000002";
	// 操作类型图文阅读（用户行为）
	public static final String CS_5064_USERACTION_ARTICLE_READING = "506400000003";
	// 操作类型图文转发（用户行为）
	public static final String CS_5064_USERACTION_ARTICLE_FORWARDING = "506400000004";
	// 操作类型其他（用户行为）
	public static final String CS_5064_USERACTION_OTHERS = "506499999998";
	// 操作类型错误（用户行为）
	public static final String CS_5064_USERACTION_ERROR = "506499999999";

	// =====双12活动使用========================================================
	/** 活动状态【未开始】 */
	public static final String CS_5067_NOT_START = "506700000001";
	/** 活动状态【进行中】 */
	public static final String CS_5067_DOING = "506700000002";
	/** 活动状态【结束】 */
	public static final String CS_5067_END = "506700000003";

	/** 权益类型.本活动方案【507000000001】 */
	public static final String CS_5070_CURRENT = "507000000001";

	/** 一账通修改履历类型 */
	public static final String CS_5066_ACCOUNTCHANGE_TYPE = "5066";
	/** 一账通修改履历类型 ：登录 */
	public static final String CS_5066_ACCOUNTCHANGE_TYPE_LOGIN = "506600000001";

	// ===========================================================================

	// 营销活动类型
	public static final String CS_5068_ACTIVITY_TYPE = "5068";


	// =====登录注册&安全中心========================================================
	/** 证据类型=身份证 */
	public static final String CS_1008_CARD_TYPE_IDCARD = "100800000100";

	//======海报-微预约===============================================================
	/** 动态字段类型【CS:5072】 */
	public static final String CS_5072_DYNAMIC_FIELD = "5072";
	/** 背景音乐类型【CS:5073】 */
	public static final String CS_5073_MUSIC_TYPE = "5073";
	public static final String CS_5073_MUSIC_TYPE_URL = "507300000001";//链接地址
	public static final String CS_5073_MUSIC_TYPE_UPLOAD = "507300000002";//本地上传
	public static final String CS_5073_MUSIC_TYPE_REFERENCE = "507300000003";//引用音乐库
	/** 翻页动画【CS:5074】 */
	public static final String CS_5074_FILP_ANIMATION = "5074";

}
