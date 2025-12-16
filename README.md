
Tlias管理系统项目采用Spring框架构建，提供登录、部门管理、员工管理、员工工作经历管理、权限管理、数据同步等功能。

### 软件架构
后端：Java + Spring Boot + Spring MVC + Maven   
前端：Vue + ElementPlus + Axios
数据库：MySQL + MyBatis
部署：阿里云服务器 + Docker + Nginx 

### 架构设计与依赖管理：

梳理核心业务需求，设计后端三层架构（Controller、Service、Dao）;

定义8+核心数据模型（员工信息、工作经历、部门等）;

基于Maven完成项目依赖管理与全生命周期管控；

### 核心核心接口开发：

基于 Spring MVC + MyBatis 实现 14 + 核心接口（用户登录、部门 / 员工 CRUD、工作经历关联操作等），覆盖数据增删改查全场景；

集成 PageHelper 完成分页查询功能，优化员工数据检索效率，支持批量操作与条件筛选；

### 事务管理与异常处理：

基于 Spring 事务注解实现 “员工基础信息 + 工作经历” 同步更新，保证操作原子性，失败自动回滚；

设计全局异常处理器，统一接口错误返回格式；

### 权限验证和接口规范：

基于 JWT 实现登录令牌生成与校验，配合全局拦截器完成接口权限拦截，杜绝未授权访问；

遵循 RESTful 风格设计接口，通过 Apifox 生成标准化接口文档，降低前后端联调成本；

### 前端适配与部署上线：
通过 Vue + ElementPlus 完成前端组件联调，通过 Axios 实现前后端数据异步通信，基于 Router 控制页面跳转逻辑，代码见 https://github.com/shishishi27/Tlias_Management_System_Vue

采用 Docker 容器化打包项目，项目部署于阿里云服务器，请访问：http://101.37.76.182/

账号：linchong 密码：123456