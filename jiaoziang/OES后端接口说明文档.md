# OES后端接口说明文档

## 班级

#### 添加班级

接口序号：MGL_class_001

接口名称：addClass

接口链接：/addClass

输入：className

处理描述：添加班级信息

输出：状态码 成功200,true/失败500,false

作者：MGL

备注：×

#### 删除班级

接口序号：MGL_class_002

接口名称：deleteClass

接口链接：/deleteClass

输入：classId

处理描述 删除班级信息

输出：状态码 成功200,true/失败500,false

作者：MGL

备注：×

#### 修改班级

接口序号：MGL_class_003

接口名称：updateClass

接口链接：/updateClass

输入：className,classId

处理描述：修改班级信息

输出：状态码 成功200,true/失败500,false

作者：MGL

备注：×

#### 查找一页班级

接口序号：MGL_class_004

接口名称：selectPageClass

接口链接：/getPageClass

输入：currentPage

处理描述：查找一页班级的信息

输出：状态码 成功200,true,List&lt;MyClass>/失败500,false

作者：MGL

备注：×

#### 根据关键词查找一页班级

接口序号：MGL_class_005

接口名称：getPageClassByKeyWord

接口链接：/selectPageClassByKeyWord

输入：currentPage,keyWord

处理描述：根据关键词查找一页班级的信息

输出：状态码 成功200,true,List&lt;MyClass>/失败500,false

作者：MGL

备注：×

#### 根据id查找班级

接口序号：MGL_class_006

接口名称：getClassByClassId

接口链接：/getClassByClassId

输入：classId

处理描述：根据id查找班级的信息

输出：状态码 成功200,true,MyClass/失败500,false

作者：MGL

备注：×

#### 批量删除班级

接口序号：MGL_class_007

接口名称：batchDeleteClassById

接口链接：/batchDeleteClassById

输入：classIds

处理描述 批量删除班级信息

输出：状态码 成功200,true/失败500,false

作者：MGL

备注：×

## 课程

#### 添加课程

接口序号：MGL_lesson_001

接口名称：addLesson

接口链接：/addLesson

输入：lessonName

处理描述：添加课程信息

输出：状态码 成功200,true/失败500,false

作者：MGL

备注：×

#### 删除课程

接口序号：MGL_lesson_002

接口名称：deleteLesson

接口链接：/deleteLesson

输入：lessonId

处理描述 删除课程信息

输出：状态码 成功200,true/失败500,false

作者：MGL

备注：×

#### 修改课程

接口序号：MGL_lesson_003

接口名称：updateLesson

接口链接：/updateLesson

输入：lessonName,lessonId

处理描述：修改课程信息

输出：状态码 成功200,true/失败500,false

作者：MGL

备注：×

#### 查找一页班级

接口序号：MGL_lesson_004

接口名称：getPageLesson

接口链接：/selectPageLesson

输入：currentPage

处理描述：查找一页班级的信息

输出：状态码 成功200,true,List&lt;Lesson>/失败500,false

作者：MGL

备注：×

#### 根据关键词查找一页班级

接口序号：MGL_lesson_005

接口名称：getPageLessonByKeyWord

接口链接：/selectPageLessonByKeyWord

输入：currentPage,keyWord

处理描述：根据关键词查找一页班级的信息

输出：状态码 成功200,true,List&lt;Lesson>/失败500,false

作者：MGL

备注：×

## 用户

#### 添加用户

接口序号：MGL_User_001

接口名称：addUser

接口链接：/addUser

输入：account,realName,password,classId,mobile,image

处理描述：添加用户信息

输出：状态码 成功200,true/失败500,false

作者：MGL

备注：×

#### 删除用户

接口序号：MGL_User_002

接口名称：deleteUser

接口链接：/deleteUser

输入：account

处理描述 删除用户信息

输出：状态码 成功200,true/失败500,false

作者：MGL

备注：×

#### 修改用户

接口序号：MGL_User_003

接口名称：updateUser

接口链接：/updateUser

输入：account,realName,password,mobile,image

处理描述：修改用户信息

输出：状态码 成功200,true/失败500,false

作者：MGL

备注：×

#### 查找一页用户

接口序号：MGL_User_004

接口名称：getPageUser

接口链接：/getPageUser

输入：currentPage

处理描述：查找一页用户的信息

输出：状态码 成功200,true,List&lt;User>/失败500,false

作者：MGL

备注：×

#### 根据关键词查找一页用户

接口序号：MGL_User_005

接口名称：getPageUserByKeyWord

接口链接：/getPageUserByKeyWord

输入：currentPage,keyWord

处理描述：根据关键词查找一页用户的信息

输出：状态码 成功200,true,List&lt;User>/失败500,false

作者：MGL

备注：×







