# 基于Socket的Java网络聊天室

原项目链接：https://github.com/leo6033/Chat_Room

有关原项目介绍可访问作者的[博客园](https://www.cnblogs.com/csu-lmw/p/10981374.html)，如有帮助请为原项目作者加一个star

# 功能介绍
基于CS架构实现
## 服务器端程序功能：
1. 用户管理：服务器端负责实现用户注册、用户信息更新和用户注销等操作，确保对用户账号进行唯一性验证，并在数据库中妥善存储和维护用户基本信息。
2. 登录管理：承担处理客户端登录请求的任务，核实用户名和密码的有效性。支持实名登录与匿名登录两种方式，并实时根据账户状态（在线/离线）进行更新。
3. 聊天记录管理：采用SQLite数据库系统以保存所有用户的聊天记录，包括群聊和私聊信息，方便用户查询历史聊天内容。同时，为了保证数据安全，应设置数据加密存储及访问控制机制。
4. 聊天文件管理：服务器需具备接收、存储和转发聊天过程中产生的各类文件的能力，如图片、文档等，并有效管理文件资源，防止恶意占用服务器空间。
5. 匿名实名设置：允许用户根据自身需求选择是否使用匿名方式进行聊天，服务器需要确保这一设置的安全性和隐私保护功能。
6. IPV6端口设置：服务器端设计时考虑到未来网络环境的发展，支持IPv6协议，允许用户自定义服务端运行的IPv6端口，实现跨平台、跨网络环境的无缝连接。
## 客户端程序功能：
1. 登录与匿名登录：客户端提供界面供用户输入账号密码进行登录，同时也支持无需注册即可使用的匿名登录模式。
2. 群聊功能：用户可以在公共聊天室进行群组交流，客户端通过Socket连接接收并展示其他用户发送的消息，并将用户输入的消息传输至服务器，由服务器广播给所有在线用户。
3. 私聊功能：用户可以发起一对一的私人对话，客户端能够向指定用户发送私密消息，并能接收到对方回复的私聊内容。
4. 聊天记录另存：用户可选择将聊天记录导出或保存到本地计算机，便于日后查阅。
5. 持久化聊天记录：当用户退出后再次登录，客户端应能从服务器获取先前的聊天记录，确保用户在不同会话间的连续体验。
6. emoji表情符号：聊天界面上集成丰富多样的emoji表情符号输入功能，增强用户在文字交流中的情感表达。
7. 文件传送：客户端支持发送和接收各种文件，涵盖但不限于图片、文档等，实现便捷高效的文件共享功能。