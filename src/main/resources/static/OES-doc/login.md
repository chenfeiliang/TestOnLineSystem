# 登录页面login.html

### 业务1：用户名输入框失去焦点后，显示用户头像
1、客户端：`get` 方式请求服务端,带有参数 `username`。
    
2、服务端接收参数 `username` 返回该 `username` 对应的 `image` 地址；如果不存在该 `username` 返回 `false`。

### 业务2：登录跳转
1、客户端：`post`方式请求服务端，带有参数 `username` 和 `password`。

2、服务端：接收参数并校验，如果用户名和密码正确，判断classid的值，
如果大于0，返回

类型 `admin`

管理员姓名 `username`

头像 `avatar`

题目的数量 `question_count`

试卷的数量 `paper_count`

班级的数量 `class_count`

用户的数量 `user_count`

考试的数量 `examin_count`

{type:"admin",username:"xxx",avatar:"xxx",...},

如果小于等于0，则返回

类型 `user`

...

考试前台需要的数据

...

{type:"user",...}

如果用户名或密码不正确，则返回 `false`。