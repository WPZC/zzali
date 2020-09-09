##模块介绍
+ -zz-api #api模块，存放系统api，供需要的地方引入，数据中间模块
    + zz-system-api #系统api接口，存放个个feign，如果feign数量过多，则建议新建模块。
+ zz-common #公共模块 公共工具模块，或者类似通用的技术模块
    + zz-common-region #公共基础模块，含有实体类等，除db以外都可引入，因为db中jpa带有注解
    + zz-log #日志公共模块，未写
    + zz-office #office模块
        + zz-excel #excel公共模块，提供excel读写操作，包含web等
    + zz-redis #redis封装类，含有redis基础操作
    + zz-security #鉴权核心模块，基本所有controller都需要引入，进行统一的安全认证
+ zz-db #db服务模块，供zz-system-api等模块调用
    + zz-api-authority #权限，用户等基础db模块
+ zz-db-template #db公共模块 数据库操作公共库，类似JPA，mybatis的常规操作的封装。
    + zz-db-jpa #jpa封装注解，jpa基本操作，以及复杂查询简单封装，
+ zz-gateway #网关服务，结合了sentinel限流
+ zz-sentinel-sashboard #阿里提供的sentinel控制台
+ zz-test #测试数据模块
+ zz-jwt #jwt权限模块，含有权限调用，等用户的相关数据
+ zz-moduls #业务模块，业务在此处进行编写
+ zz-nacos-all #nacos中心的源码部分。
