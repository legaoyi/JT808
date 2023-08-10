//单个服务部署时使用
var management_api_server_servlet_path = "";
var message_api_server_servlet_path = "";
var lbs_api_server_servlet_path = "";
var report_api_server_servlet_path = "";
var officerCar_api_server_servlet_path = "";
var bus_api_server_servlet_path = "";

//设备故障类报警，多次连续告警保存一次
var devicefaultAlarmInfoMap = new JsMap();
devicefaultAlarmInfoMap.put(4, "GNSS模块发生故障");
devicefaultAlarmInfoMap.put(5, "GNSS天线未接或被剪断");
devicefaultAlarmInfoMap.put(6, "GNSS天线短路");
devicefaultAlarmInfoMap.put(7, "终端主电源欠压");
devicefaultAlarmInfoMap.put(8, "终端主电源掉电");
devicefaultAlarmInfoMap.put(9, "终端LCD或显示器故障");
devicefaultAlarmInfoMap.put(10, "TTS模块故障");
devicefaultAlarmInfoMap.put(11, "摄像头故障");
devicefaultAlarmInfoMap.put(12, "道路运输证IC卡模块故障");
devicefaultAlarmInfoMap.put(24, "车辆VSS");

//驾驶行为报警
var drivingBehaviorAlarmInfoMap = new JsMap();
drivingBehaviorAlarmInfoMap.put(1, "超速报警");
drivingBehaviorAlarmInfoMap.put(2, "疲劳驾驶");
drivingBehaviorAlarmInfoMap.put(13, "超速预警");
drivingBehaviorAlarmInfoMap.put(14, "疲劳驾驶预警");
drivingBehaviorAlarmInfoMap.put(18, "当天累计驾驶超时");
drivingBehaviorAlarmInfoMap.put(19, "超时停车");
drivingBehaviorAlarmInfoMap.put(20, "进出区域");
drivingBehaviorAlarmInfoMap.put(21, "进出路线");
drivingBehaviorAlarmInfoMap.put(22, "路段行驶时间不足/过长");
drivingBehaviorAlarmInfoMap.put(23, "路线偏离报警");
drivingBehaviorAlarmInfoMap.put(31, "非法开车门");

//车辆紧急重要告警
var carUrgentAlarmInfoMap = new JsMap();
carUrgentAlarmInfoMap.put(0, "紧急报警");
carUrgentAlarmInfoMap.put(3, "危险预警");
carUrgentAlarmInfoMap.put(25, "车辆油量异常");
carUrgentAlarmInfoMap.put(26, "车辆被盗");
carUrgentAlarmInfoMap.put(27, "车辆非法点火");
carUrgentAlarmInfoMap.put(28, "车辆非法位移");
carUrgentAlarmInfoMap.put(29, "碰撞预警");
carUrgentAlarmInfoMap.put(30, "侧翻预警");


var carAlarmInfoMap = new JsMap();
carAlarmInfoMap.putAll(devicefaultAlarmInfoMap);
carAlarmInfoMap.putAll(drivingBehaviorAlarmInfoMap);
carAlarmInfoMap.putAll(carUrgentAlarmInfoMap);
//carAlarmInfoMap.put(15, "未定义");
//carAlarmInfoMap.put(16, "未定义");
//carAlarmInfoMap.put(17, "未定义");
carAlarmInfoMap.put(32, "原地设防报警(平台)");
carAlarmInfoMap.put(33, "出围栏报警(平台)");
carAlarmInfoMap.put(34, "进围栏报警(平台)");
carAlarmInfoMap.put(35, "围栏超速报警(平台)");
carAlarmInfoMap.put(36, "超速报警(平台)");
carAlarmInfoMap.put(37, "路线偏离告警(平台)");
carAlarmInfoMap.put(38, "进入路线告警(平台)");
carAlarmInfoMap.put(39, "路线超速告警(平台)");
carAlarmInfoMap.put(40, "急加速(平台)");
carAlarmInfoMap.put(41, "急刹车(平台)");
carAlarmInfoMap.put(42, "急转弯(平台)");
carAlarmInfoMap.put(45, "空挡滑行报警(平台)")
carAlarmInfoMap.put(49, "车辆加油提示(平台)");


var carSignalStatusInfoMap = new JsMap();
carSignalStatusInfoMap.put(0, "近光灯信号");
carSignalStatusInfoMap.put(1, "远光灯信号");
carSignalStatusInfoMap.put(2, "右转向灯信号");
carSignalStatusInfoMap.put(3, "左转向灯信号");
carSignalStatusInfoMap.put(4, "制动信号");
carSignalStatusInfoMap.put(5, "倒档信号");
carSignalStatusInfoMap.put(6, "雾灯信号");
carSignalStatusInfoMap.put(7, "示廓灯");
carSignalStatusInfoMap.put(8, "喇叭信号");
carSignalStatusInfoMap.put(9, "空调状态");
carSignalStatusInfoMap.put(10, "空挡状态");
carSignalStatusInfoMap.put(11, "缓速器工作");
carSignalStatusInfoMap.put(12, "ABS工作");
carSignalStatusInfoMap.put(13, "加热器工作");
carSignalStatusInfoMap.put(14, "离合器状态");

var carIoStatusMap = new JsMap();
carIoStatusMap.put(0, "深度休眠状态");
carIoStatusMap.put(1, "休眠状态");

var carStatusOffInfoMap = new JsMap();
carStatusOffInfoMap.put(0, "关机");
carStatusOffInfoMap.put(1, "未定位");
carStatusOffInfoMap.put(2, "北纬");
carStatusOffInfoMap.put(3, "东经");
carStatusOffInfoMap.put(4, "营运");
carStatusOffInfoMap.put(5, "经纬度未加密");
carStatusOffInfoMap.put(6, "未定义");
carStatusOffInfoMap.put(7, "未定义");
carStatusOffInfoMap.put(8, "未定义");
carStatusOffInfoMap.put(9, "未定义");
carStatusOffInfoMap.put(10, "车辆油路正常");
carStatusOffInfoMap.put(11, "车辆电路正常");
carStatusOffInfoMap.put(12, "车门解锁");
carStatusOffInfoMap.put(13, "门1关");
carStatusOffInfoMap.put(14, "门2关");
carStatusOffInfoMap.put(15, "门3关");
carStatusOffInfoMap.put(16, "门4关");
carStatusOffInfoMap.put(17, "门5关");
carStatusOffInfoMap.put(18, "未使用GPS卫星进行定位");
carStatusOffInfoMap.put(19, "未使用北斗卫星进行定位");
carStatusOffInfoMap.put(20, "未使用GLONASS卫星进行定位");
carStatusOffInfoMap.put(21, "未使用Galileo卫星进行定位");

var carStatusOnInfoMap = new JsMap();
carStatusOnInfoMap.put(0, "开机");
carStatusOnInfoMap.put(1, "定位");
carStatusOnInfoMap.put(2, "南纬");
carStatusOnInfoMap.put(3, "西经");
carStatusOnInfoMap.put(4, "停运");
carStatusOnInfoMap.put(5, "经纬度加密");
carStatusOnInfoMap.put(6, "未定义");
carStatusOnInfoMap.put(7, "未定义");
carStatusOnInfoMap.put(8, "未定义");
carStatusOnInfoMap.put(9, "未定义");
carStatusOnInfoMap.put(10, "车辆油路断开");
carStatusOnInfoMap.put(11, "车辆电路断开");
carStatusOnInfoMap.put(12, "车门加锁");
carStatusOnInfoMap.put(13, "门1开(前门)");
carStatusOnInfoMap.put(14, "门2开(中门)");
carStatusOnInfoMap.put(15, "门3开(后门)");
carStatusOnInfoMap.put(16, "门4开(驾驶席门)");
carStatusOnInfoMap.put(17, "门5开");
carStatusOnInfoMap.put(18, "使用GPS卫星进行定位");
carStatusOnInfoMap.put(19, "使用北斗卫星进行定位");
carStatusOnInfoMap.put(20, "使用GLONASS卫星进行定位");
carStatusOnInfoMap.put(21, "使用Galileo卫星进行定位");

var deviceMessageInfoMap = new JsMap();
deviceMessageInfoMap.put("0001", "终端通用应答");
deviceMessageInfoMap.put("8001", "平台通用应答 ");
deviceMessageInfoMap.put("0002", "终端心跳");
deviceMessageInfoMap.put("8003", "补传分包请求");
deviceMessageInfoMap.put("0100", "终端注册");
deviceMessageInfoMap.put("8100", "终端注册应答");
deviceMessageInfoMap.put("0003", "终端注销");
deviceMessageInfoMap.put("0102", "终端鉴权");
deviceMessageInfoMap.put("8103", "设置终端参数");
deviceMessageInfoMap.put("8104", "查询终端参数");
deviceMessageInfoMap.put("0104", "查询终端参数应答");
deviceMessageInfoMap.put("8105", "终端控制");
deviceMessageInfoMap.put("8106", "查询指定终端参数 ");
deviceMessageInfoMap.put("8107", "查询终端属性");
deviceMessageInfoMap.put("0107", "查询终端属性应答 ");
deviceMessageInfoMap.put("8108", "下发终端升级包");
deviceMessageInfoMap.put("0108", "终端升级结果通知 ");
deviceMessageInfoMap.put("0200", "位置信息汇报");
deviceMessageInfoMap.put("8201", "位置信息查询");
deviceMessageInfoMap.put("0201", "位置信息查询应答");
deviceMessageInfoMap.put("8202", "临时位置跟踪控制");
deviceMessageInfoMap.put("8203", "工确认报警消息");
deviceMessageInfoMap.put("8300", "文本信息下发");
deviceMessageInfoMap.put("8301", "事件设置 ");
deviceMessageInfoMap.put("0301", "事件报告");
deviceMessageInfoMap.put("8302", "提问下发");
deviceMessageInfoMap.put("0302", "提问应答");
deviceMessageInfoMap.put("8303", "信息点播菜单设置");
deviceMessageInfoMap.put("0303", "信息点播/取消 ");
deviceMessageInfoMap.put("8304", "信息服务");
deviceMessageInfoMap.put("8400", "电话回拨 ");
deviceMessageInfoMap.put("8401", "设置电话本");
deviceMessageInfoMap.put("8500", "车辆控制");
deviceMessageInfoMap.put("0500", "车辆控制应答");
deviceMessageInfoMap.put("8600", "设置圆形区域");
deviceMessageInfoMap.put("8601", "删除圆形区域");
deviceMessageInfoMap.put("8602", "设置矩形区域");
deviceMessageInfoMap.put("8603", "删除矩形区域");
deviceMessageInfoMap.put("8604", "设置多边形区域");
deviceMessageInfoMap.put("8605", "删除多边形区域 ");
deviceMessageInfoMap.put("8606", "设置路线");
deviceMessageInfoMap.put("8607", "删除路线");
deviceMessageInfoMap.put("8700", "行驶记录仪数据采集");
deviceMessageInfoMap.put("0700", "行驶记录仪数据上传");
deviceMessageInfoMap.put("8701", "行驶记录仪参数下传");
deviceMessageInfoMap.put("0701", "电子运单上报");
deviceMessageInfoMap.put("0702", "驶员身份信息采集上报");
deviceMessageInfoMap.put("8702", "上报驾驶员身份信息请求");
deviceMessageInfoMap.put("0704", "定位数据批量上传");
deviceMessageInfoMap.put("0705", "CAN总线数据上传");
deviceMessageInfoMap.put("0800", "多媒体事件信息上传");
deviceMessageInfoMap.put("0801", "多媒体数据上传");
deviceMessageInfoMap.put("8800", "多媒体数据上传应答");
deviceMessageInfoMap.put("8801", "摄像头立即拍摄");
deviceMessageInfoMap.put("0805", "摄像头立即拍摄应答");
deviceMessageInfoMap.put("8802", "存储多媒体数据检索");
deviceMessageInfoMap.put("0802", "存储多媒体数据检索应答 ");
deviceMessageInfoMap.put("8803", "存储多媒体数据上传 ");
deviceMessageInfoMap.put("8804", "录音开始 ");
deviceMessageInfoMap.put("8805", "单条存储多媒体数据检索上传");
deviceMessageInfoMap.put("8900", "数据下行透传");
deviceMessageInfoMap.put("0900", "数据上行透传");
deviceMessageInfoMap.put("0901", "数据压缩上报");
deviceMessageInfoMap.put("8A00", "平台RSA公钥");
deviceMessageInfoMap.put("0A00", "终端RSA公钥");
deviceMessageInfoMap.put("8F01", "远程升级指令");
deviceMessageInfoMap.put("0F02", "查询升级文件属性");
deviceMessageInfoMap.put("8F02", "查询升级文件属性应答");
deviceMessageInfoMap.put("0F03", "请求升级文件数据包");
deviceMessageInfoMap.put("8F03", "请求升级文件数据包应答");
deviceMessageInfoMap.put("8F04", "升级包异常通知");
deviceMessageInfoMap.put("0F04", "升级结果通知");

//1078协议
deviceMessageInfoMap.put("9003", "查询终端音视频属性");
deviceMessageInfoMap.put("1003", "上传终端音视频属性");
deviceMessageInfoMap.put("9101", "实时音视频传输请求");
deviceMessageInfoMap.put("1005", "终端上传乘客流量");
deviceMessageInfoMap.put("9102", "实时音视频传输控制");
deviceMessageInfoMap.put("9105", "实时音视频传输状态通知");
deviceMessageInfoMap.put("9205", "查询音视频资源列表");
deviceMessageInfoMap.put("1205", "终端上传音视频资源列表");
deviceMessageInfoMap.put("9201", "远程录像回放请求");
deviceMessageInfoMap.put("9202", "远程录像回放控制");
deviceMessageInfoMap.put("9206", "音视频文件上传");
deviceMessageInfoMap.put("1206", "音视频文件上传完成通知");
deviceMessageInfoMap.put("9207", "音视频文件上传控制");
deviceMessageInfoMap.put("9301", "云台旋转");
deviceMessageInfoMap.put("9302", "云台调整焦距控制");
deviceMessageInfoMap.put("9303", "云台调整光圈控制");
deviceMessageInfoMap.put("9304", "云台雨刷控制");
deviceMessageInfoMap.put("9305", "红外补光控制");
deviceMessageInfoMap.put("9306", "云台变倍控制");

//苏标协议
deviceMessageInfoMap.put("9208", "报警附件上传指令");
deviceMessageInfoMap.put("1210", "报警附件信息消息");
deviceMessageInfoMap.put("1211", "文件信息上传");
deviceMessageInfoMap.put("1212", "文件上传完成消息");
deviceMessageInfoMap.put("9212", "文件上传完成消息应答");

var channelMap = new JsMap();
channelMap.put(1, "音视频通道1-车辆正前方");
channelMap.put(2, "音视频通道2-驾驶员");
channelMap.put(3, "音视频通道3-车前门");
channelMap.put(4, "音视频通道4-车厢前部");
channelMap.put(5, "音视频通道5-车厢后部");
channelMap.put(6, "音视频通道6-车后门");
channelMap.put(7, "音视频通道7-行李舱");
channelMap.put(8, "音视频通道8-车辆左侧");
channelMap.put(9, "音视频通道9-车辆右侧");
channelMap.put(10, "通道10-车辆正前方");
channelMap.put(11, "通道11-车辆中部");
channelMap.put(12, "通道12-车中门");
channelMap.put(13, "通道13-驾驶席车门");
channelMap.put(33, "音频通道33-驾驶员");
channelMap.put(36, "音频通道36-车厢前部");
channelMap.put(37, "音频通道37-车厢后部");

var deviceBizStateMap = new JsMap();
deviceBizStateMap.put(0,"离线");
deviceBizStateMap.put(1,"行驶");
deviceBizStateMap.put(2,"停车");
deviceBizStateMap.put(3,"熄火");
deviceBizStateMap.put(4,"无信号");

var carStateMap = new JsMap();
carStateMap.put(0,"已报废");
carStateMap.put(1,"空闲");
carStateMap.put(2,"待出车");
carStateMap.put(3,"出车中");
carStateMap.put(4,"已禁用");


var deviceStateMap = new JsMap();
deviceStateMap.put(0,"未注册");
deviceStateMap.put(1,"已注册");
deviceStateMap.put(2,"离线");
deviceStateMap.put(3,"在线");
deviceStateMap.put(4,"已注销");
deviceStateMap.put(5,"已停用");

//提醒类型：1：审批；2：车险到期；3：年检到期；4：驾驶证到期；5：接单提醒；6：保养；7：报废到期；8：用车超时提醒
var alarmNotifyType=new JsMap();
alarmNotifyType.put(1,"用车审批");
alarmNotifyType.put(2,"车险到期");
alarmNotifyType.put(3,"年检到期");
alarmNotifyType.put(4,"驾驶证到期");
alarmNotifyType.put(5,"接单提醒");
alarmNotifyType.put(6,"保养到期");
alarmNotifyType.put(7,"报废到期");
alarmNotifyType.put(8,"用车超时");

//苏标报警,高级驾驶辅助系统报警信息
var adasAlarmDesc = ["前向碰撞报警","车道偏离报警","车距过近报警","行人碰撞报警","频繁变道报警","道路标识超限报警","障碍物报警","用户自定义报警","用户自定义报警","道路标志识别事件","主动抓拍事件"];
//驾驶员状态监测系统报警信息
var dsmAlarmDesc = ["疲劳驾驶报警","接打电话报警","抽烟报警","分神驾驶报警","驾驶员异常报警","用户自定义报警","用户自定义报警","用户自定义报警","用户自定义报警","自动抓拍事件","驾驶员变更事件"];
//胎压监测系统报警信息
var tpmAlarmDesc = ["用户自定义报警","胎压过高报警","胎压过低报警","胎温过高报警","传感器异常报警","胎压不平衡报警","慢漏气报警","电池电量低报警"];
//盲区监测系统报警信息
var bsdAlarmDesc = ["后方接近报警","左侧后方接近报警","右侧后方接近报警"];

//1078视频告警信息
var videoAlarmDesc = ["视频信号丢失告警","视频信号遮挡告警","存储单元故障告警","其他视频设备故障告警","客车超员告警","异常驾驶行为告警","特殊报警录像达到存储阈值告警"];

var month_ch=["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"];

//设备分组
var group_type_device = 1;
//车辆分组
var group_type_car = 2;

//字典类型,1：实体对象;2：对象描述;3：消息描述;4：消息样例;
var dictionary_entity = 1;
var dictionary_entity_describe = 2;
var dictionary_message_describe = 3;
var dictionary_message_example = 4;

//字典类型,21:车辆颜色;22:车辆品牌;23:车辆级别;24:车辆型号
var dictionary_officers_car_color = 21;
var dictionary_officers_car_brandType = 22;
var dictionary_officers_car_level = 23;
var dictionary_officers_car_brandModel = 24;
var dictionary_officers_car_type = 25;
var dictionary_officers_car_bizType = 26;

//41:用车提醒配置;
var dictionary_officers_car_notify = 41;


//50:驾驶评分配置;
var dictionary_driving_behavior_grade = 50;

//80:流程配置管理;
var dictionary_officers_car_process = 80;

//81:基础角色配置管理;
var dictionary_user_role = 81;

//90:外部接口
var DICTIONARY_PROVIDER_API = 90;

//98:系统字典
var DICTIONARY_SYS_DICTIONARY = 98;

//99：视频网关;
var dictionary_video_gateway = 99;

//设备故障类告警,连续相同报警只存储一次
var device_fault_alarm="1000000000001111111110000";

//驾驶行为违规告警
var driving_behavior_alarm="10000000111111000110000000000110";

//车载设备紧急告警
var device_urgent_alarm="1111110000000000000000000001001";