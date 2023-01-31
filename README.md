# course-design

## introfuction
- 本科数据库系统课程设计。存储记录使用

本系统形式为**Web应用**，采用MVC（Model, View, Control）架构，分为模型层，视图层以及控制层。

本系统中数据库采用MySQL；模型层和控制层采用java结合Spring Boot框架实现，采取基于内存实现的键值型非关系（NoSQL）数据库Redis实现对session登录的token验证，以保证不会有多人同时登陆同一账户，利用spring security 实现开放端口的权限认证以保证端口的访问安全，这部分被称作后端；视图层采取HTML，以Vue前端JavaScript语言框架结合Vuex状态管理模式库，采取MVVM（Model，View，View-Model）页面架构，以实现数据变化自动更新绑定的View以及View的操作更新绑定的数据，利用Vue Router 实现页面的动态路由挂载，实现页面和用户权限的分离，进一步保证用户权限和用户视图的控制。用Element UI组件库以实现页面组件的可重用性，这部分被称作前端。前后端实现分离，利用Axios的http封装请求库以http网络通信协议的方式传递json数据文件，实现前后端的信息传递和交互。

# afterwards
数据库数据类型可见部分课程报告