微服务架构是一种新型的系统架构。其设计思路是，将单体架构系统拆分为多个可以相
互调用、配合的独立运行的小程序。这每个小程序对整体系统所提供的功能就称为一个微服
务。
由于每个微服务都是独立运行的，所以每个微服务都独自占用一个进程。微服务间采用
轻量级的 HTTP RESTful 协议通信。每个微服务程序不受编程语言的限制，整个系统关心的是
微服务程序所提供的具体服务，并不关心其具体的实现。每个微服务可以有自己独立的数据
库。即可以操作自己的独立数据库，也可以操作整体系统的数据库。


1.spring cloud 微服务之间是采用 HTTP RESTful 协议通信。

2.微服务用 yml 配置文件

3.eureka 的自我保护机制（默认开启）
    Self Preservation 机制即自我保护机制。在 Eureka 服务页面中看到如下红色字体内容，
表示当前 EurekaServer 启动了自我保护机制，进入了自我保护模式。
    自我保护机制关闭

4.eureka 集群中 服务较少是（小于阈值）关闭自我保护机制，才能看的见

5.eureka 集群中
    eureka:
      instance:
        hostname:
    如果hostname 也配置成eureka可以显示。不配置或者配置成localhost都（DS Replicas）不能显示


6.openfeign 应用 注意事项
    1.spring:
        main:
          allow-bean-definition-overriding: true
      这个需要设置为 true

    2.FeignClients("spring-cloud-provider")
        value 属性 必须是  微服务名称（spring.application.name）

    3.服务提供的参数要有注解
       比如
            @RequestParam
            @PathVariable
            @RequestBody  (请求体是json 才能自动转换为 对象)
            ...

