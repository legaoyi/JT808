
一、软件运行环境
      1、操作系统：操作系统支持window以及linux等，建议使用linux，64位centOS 7以上版本
      2、Java环境：平台的运行依赖java环境，安装jdk1.8版本
      3、ActiveMQ环境：平台的运行依赖ActiveMQ环境，ActiveMQ安装最新版本

二、创建数据库legaoyi_iov，执行数据库脚本：legaoyi_iov.sql  

三、配置文件
      软件conf目前下的所有配置文件都必须保留，不能删除，否则将会导致软件无法运行。使用软件时，请根据实际情况修改conf目录下的配置文件application.properties中的activemq配置部分，如下：

      #activemq 配置
      spring.activemq.broker-url=tcp://localhost:61616
      spring.activemq.user=admin
      spring.activemq.password=admin

     #mysql db
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
     spring.datasource.url=jdbc:mysql://localhost:3306/legaoyi_iov?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai
     spring.datasource.username=root
     spring.datasource.password=root

四、软件运行
      1、window：双击bin/startup.bat文件即可启动；
      2、linux：使用命令启动（注：启动前先赋予该文件startup.sh执行权限，chmod 777 startup.sh）： nohup ./bin/startup.sh &

五、服务elink-iov-platform-web运行后，登录地址为：http://localhost:28080/login.do

六、消息网关中间件elink-iov-message-gateway，接入车载设备