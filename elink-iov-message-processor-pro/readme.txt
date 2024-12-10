
/bin 目录为打包好的java demo程序，运行在java环境中(jdk1.8)、rabbitMQ或kafka；
/src  目录为java demo程序源码，开发环境maven，java(jdk1.8)、rabbitMQ或kafka；
/html 为视频直播demo程序，包括视频指令下发、拉流播放等；

该demo配合网关程序，可以演示车辆注册上线，上报GPS信息、主动安全告警、附件上传以及视频直播等流程


一、演示车辆注册上线，上报GPS信息流程需下载JT808协议网关
     1、elink-iov-jt808-2013-message-gateway
二、演示视频直播流程需下载JT808协议网关以及JT1078协议流媒体网关
     1、elink-iov-jt1078-2016-message-gateway 或者 elink-iov-tjsatl-2017-message-gateway等
     2、elink-iov-jt1078-vedio-gateway 

三、演示主动安全告警以及附件上传需下载苏标协议网关以及苏标附件网关
      1、elink-iov-tjsatl-2017-message-gateway 或者 elink-iov-db32t3610-2019-message-gateway 等
      2、elink-iov-tjsatl-2017-attachment-gateway