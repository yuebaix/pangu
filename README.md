# PanGu 盘古

> The name comes from the creator of universe in ancient chinese mythology.<br/>
> 以中国古代神话中创世神命名


## What PanGu Is? 盘古是什么？

* [x] A Normal form of framework code. 框架代码范式。
* [x] A general solution for building website applications. 构建网站应用的通用解决方案。
* [x] Meeting the core challenges of building high-performance websites. 应对构建高性能网站的核心挑战。
* [x] Solid and less dependent framework code. 坚实少依赖的框架代码。
* [x] Enhancements based on springboot. 基于springboot进行增强。
* [x] Out of the box and easy to use. 开箱即用且易于使用。
* [x] Convenient for secondary development and expansion. 便于二次开发与扩展。
* [x] Exploration of reactive programing and coroutines. 对响应式与协程的探索。


## Structure

```text
.
├─pangu-common (base code 基础代码)
├─pangu-core (frame code 框架代码)
├─pangu-web (web code 网站基础代码)
├─pangu-spring-boot-starter (starter for pangu 盘古starter)
├─pangu-ready-spring-boot-starter (starter for pangu and more enhancements 封装盘古与更多增强starter)
└─pangu-test (integrated test code 集成测试代码)
```


## Feature

Website Enhancements | OpenApi UI | Api Log | More

网站增强特性 | 接口UI | 接口日志 | 更多

PanGu has already added these starters below up. And there's gonna be more.

盘古已经引入了下面这些starter。并且还会有更多。

```text
pangu-spring-boot-starter     ---> power to make web development sweet
springfox-boot-starter        ---> openapi doc for web interfaces
knife4j-spring-ui             ---> nice ui for openapi doc
logbook-spring-boot-starter   ---> record all requests and responses in an efficient way
```


## Getting Started

### _1. Add dependency(Must). 添加依赖(必须)。_

Gradle

```groovy
implementation 'com.yuebaix:pangu-ready-spring-boot-starter:0.0.1'
```

Maven

```xml
<dependency>
    <groupId>com.yuebaix</groupId>
    <artifactId>pangu-ready-spring-boot-starter</artifactId>
    <version>0.0.1</version>
</dependency>
```

### _2. Manage dependencies(Recommend). 依赖管理(推荐)。_

For gradle:

1. Copy [pangu-ready-denpendencies.gradle](https://github.com/yuebaix/pangu/blob/main/pangu-ready-spring-boot-starter/integrate/pangu-ready-denpendencies.gradle) to your local src root path.
2. Rename it to denpendencies.gradle.
3. Then apply it. 

```groovy
apply from: "${rootDir}/denpendencies.gradle"
```

Notice:
1. Rely on 'io.spring.dependency-management' gradle plugin，依赖'io.spring.dependency-management'插件。
2. Built with SpringCloud Hoxton. But [pangu-ready-denpendencies-greenwich](https://github.com/yuebaix/pangu/blob/main/pangu-ready-spring-boot-starter/integrate/pangu-ready-denpendencies-greenwich.gradle) is also provided. 使用SpringCLoud Hoxton构建，但是也提供了SpringCloud Greenwich版依赖的支持。

For maven:

Sadly there's no plan to support it. You could build one with maven dependencyManagement. If you want to contribute. Let me know.
并没有长期计划支持maven。你自己可以用maven dependencyManagement构建一个。如果你有兴趣贡献代码，请联系我。

### _3. Config to meet your flavor(Unnecessary). 配置一下满足你的口味(非必须)。_

Here's the default configuration. 下面是默认的配置

```properties
pangu.starter.enabled=true
pangu.starter.ctxHolder.enabled=true
pangu.starter.banner.enabled=true
pangu.starter.banner.springboot.enabled=false
pangu.starter.banner.pangu.enabled=true

pangu.readystarter.enabled=true
pangu.readystarter.swagger.enabled=true
pangu.readystarter.logbook.enabled=true
pangu.readystarter.logbook.config.obfuscate-body-params=token,password,mobile,email
```

### _4. Kickstart. 启动。_

Run your springboot application. Console output will look like below. 启动你的Springboot应用。控制台输出会像下面这样。

![Console Output](https://docs.yuebaix.com/img/host/console_output.png)

### _5. Enjoy. 愉快玩耍吧。😉_


## Roadmap

![](https://mermaid.ink/img/eyJjb2RlIjoiZ2FudHRcbmRhdGVGb3JtYXQgWVlZWS1NTS1ERFxudGl0bGUgUGFuR3UgUm9hZG1hcFxuc2VjdGlvbiBpbml0IGZyYW1lXG5hcmNoZXR5cGU6IGFjdGl2ZSwgMV8xLCAyMDIxLTA5LTA0LCAxNWRcbnNlY3Rpb24gYnVpbGQgZnJhbWVcbmNvbmNlcHQ6IGFjdGl2ZSwgMl8xLCBhZnRlciAxXzEsIDMwZFxuY29uZmlnOiBhY3RpdmUsIDJfMiwgYWZ0ZXIgMV8xLCAzMGRcbmV2ZW50OiAyXzMsIGFmdGVyIDFfMSwgMzBkXG5jb25jdXJyZW50OiAyXzQsIGFmdGVyIDFfMSwgMzBkXG5zZXJ2ZXI6IDJfNSwgYWZ0ZXIgMV8xLCAzMGRcbmNhY2hlOiAyXzYsIGFmdGVyIDFfMSwgMzBkXG5pbzogMl83LCBhZnRlciAxXzEsIDMwZFxuc2VjdGlvbiBidWlsZCB3ZWIgc29sdXRpb25cbm5ldDogM18xLCBhZnRlciAyXzEsIDMwZFxucmJhYzogM18xLCBhZnRlciAyXzEsIDMwZCIsIm1lcm1haWQiOnsidGhlbWUiOiJkZWZhdWx0In0sInVwZGF0ZUVkaXRvciI6ZmFsc2UsImF1dG9TeW5jIjp0cnVlLCJ1cGRhdGVEaWFncmFtIjpmYWxzZX0)


## LICENSE

[![Blush License](http://img.shields.io/badge/license-blush-white?style=for-the-badge&labelColor=black&color=white)](https://github.com/yuebaix/pangu/blob/main/LICENSE)


## Maintainers

[![Gmail](https://img.shields.io/badge/-yuebaix@outlook.com-c14438?style=for-the-badge&logo=Gmail&logoColor=white)](mailto:yuebaix@outlook.com)


## Give a Star! ⭐

If you like or are using this project to learn or start your solution, please give it a star. Thanks!<br/>
觉得不错的话，别忘 star 👏


## Changelog

| Date       | Version         | Description                   |
| ---------- | --------------- | ----------------------------- |
| 2021-09-20 | 0.0.1           | first release<br />第一次发布 |
| 2021-09-04 | 0.0.1-SNAPSHOTT | init project<br />项目初始化  |
