##模块介绍
+ -zz-api #api模块，存放系统api，供需要的地方引入，数据中间模块
    + zz-system-api #系统api接口，存放个个feign，如果feign数量过多，则建议新建模块。
+ zz-common #公共模块 公共工具模块，或者类似通用的技术模块
    + zz-common-region #公共基础模块，含有实体类等，除db以外都可引入，因为db中jpa带有注解
    + zz-domain #实体类管理
        + zz-domain-authotiry #权限实体类
        + zz-domain-common #公共类
    + zz-log #日志公共模块，目前没太好的想法，没写
    + zz-office #office模块
        + zz-excel #excel公共模块，提供excel读写操作，包含web等
    + zz-redis #redis封装类，含有redis基础操作
    + zz-security #鉴权核心模块，基本所有controller都需要引入，进行统一的安全认证
    + zz-http #http请求模块
        + zz-http-okhttps #http核心代码
        + zz-http-okhttps-gson #采用GSON作为解析的http请求工具，参考文档http://okhttps.ejlchina.com/v2/foundation.html   
    + zz-netty #netty区域
        + zz-netty-client #netty客户端
        + zz-netty-server #netty服务端
+ zz-db-template #db公共模块 数据库操作公共库，类似JPA，mybatis的常规操作的封装。
    + zz-db-jpa #jpa封装注解，jpa基本操作，以及复杂查询简单封装，
+ zz-gateway #网关服务，结合了sentinel限流
+ zz-sentinel-sashboard #阿里提供的sentinel控制台
+ zz-test #测试数据模块
+ zz-jwt #jwt权限模块，含有权限调用，等用户的相关数据
+ zz-moduls #业务模块，业务在此处进行编写
    + zz-base-api #基础业务模块（用户，菜单，角色等）
+ zz-nacos-all #nacos中心的源码部分。


2020-09-17:  
    1. 修改实体类存放结构，新增zz-domain模块，存放所有实体类，便于共享和管理，zz-domain下有两个模块，一个是公共类，一个是权限类，
       公共类存放类似ResultVo，BaseDoMain等公用实体类，权限类为基础服务所用的模块。
    2. 修改zz-secutiry，新增自定义配置，详情见zz-secutiry文档更细。
