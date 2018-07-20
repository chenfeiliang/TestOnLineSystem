var lesson_name = $("#lesson_name");
/**
 *  查询课程信息的方法
 * @param currentPage 当前页
 */
function select_lesson(currentPage){
    // alert("查询课程信息ok");
    // alert("当前页："+currentPage);
    $.ajax({
        url: base_url + "getPageLesson?currentPage=" + currentPage,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_lesson_info(response.extend.result.list,response.extend.result["pages"],currentPage,response.extend.result.navigatepageNums);
            }
        },
        error: function(a, b) {
            alert("error");
        }
    });
}

/**
 * 显示课程信息的方法
 * @param classInfo 当前页的课程信息
 * @param pageCount 总页数
 * @param currentPage 当前页
 * @param pageArray 页码
 */
function show_lesson_info(lessonInfo,pageCount,currentPage,pageArray) {
    // alert("显示课程信息ok");
    // count 当前页的课程数量
    var count = 0;
    // lessonIdArray 存放当前页所有课程的id
    var lessonIdArray = [];
    // lessonNameArray 存放当前页所有课程的名称
    var lessonNameArray = [];
    // lessonHtmlRes 字符串 用来动态生成课程信息表格<tr>
    var lessonHtmlRes = "";
    // pageHtmlRes 字符串 用来动态生成页码
    var pageHtmlRes = "";
    $.each(lessonInfo,function (i,item) {
        count++;
        lessonIdArray.push(item["lessonId"]);
        lessonNameArray.push(item["lessonName"]);
    });
    // 确定当前页数
    currentPage = (currentPage > pageCount) ? pageCount : currentPage;
    // ======分页，生成页码======
    if (pageCount == 1){
        $(".pagination").html(pageHtmlRes);
    } else if (pageCount < 6 && pageCount != 1){
        // 总页数小于5页 只有1页不分页
        for (var i=1;i<=pageCount;i++){
            if (i == currentPage){
                pageHtmlRes += "<li class='active'><a href=\"javascript:void(0);\" class='page' data-cp='"+i+"'>"+i+"</a></li>";
            } else {
                pageHtmlRes += "<li><a href=\"javascript:void(0);\" class='page' data-cp='"+i+"'>"+i+"</a></li>";
            }
        }
        // 引入自定义分页js
        pageHtmlRes += "<script src='../assets/js/page.js' />"
        $(".pagination").html(pageHtmlRes);
    } else if (pageCount >= 6) {
        // 总页数大于5页
        pageHtmlRes = "<li class='begin'><a href=\"javascript:void(0);\" class='page' data-cp='1'>首页</a></li>";
        if (currentPage > 3){
            pageHtmlRes += "<li><a href=\"javascript:void(0);\" class='pre' data-pre='"+(currentPage-1)+"'>上一页</a></li><li class='disabled'><a href=\"javascript:void(0);\">...</a></li>";
        }
        for (var i=pageArray[0];i<=(pageArray[0] + pageArray.length - 1);i++){
            if (i == currentPage){
                pageHtmlRes += "<li class='active'><a href=\"javascript:void(0);\" class='page' data-cp='"+i+"'>"+i+"</a></li>";
            } else {
                pageHtmlRes += "<li><a href=\"javascript:void(0);\" class='page' data-cp='"+i+"'>"+i+"</a></li>";
            }
        }
        if (currentPage < (pageCount - 2)){
            pageHtmlRes += "<li class='disabled'><a href=\"javascript:void(0);\">...</a></li><li class='next' data-next='"+(currentPage+1)+"'><a href=\"javascript:void(0);\">下一页</a></li>";
        }
        pageHtmlRes += "<li class='end'><a href=\"javascript:void(0);\" class='page' data-cp='"+pageCount+"'>尾页</a></li>";
        // 引入自定义分页js
        pageHtmlRes += "<script src='../assets/js/lesson/page.js' />"
        $(".pagination").html(pageHtmlRes);
    }
    // ======生成课程信息表格======
    if (count>0){
        $("thead input").data("cp",currentPage);
        for (var i=0;i<count;i++){
            // 生成课程信息表格HTML代码
            lessonHtmlRes += "<tr><td class='text-center'><input type='checkbox' data-id='"+lessonIdArray[i]+"'></td><td class='text-center l_name'>"+lessonNameArray[i]+"</td><td class='text-center'><a href='javascript:void(0);' class='btn btn-info btn-xs up_lesson' data-id='"+lessonIdArray[i]+"'>编辑</a><a href='javascript:void(0);' class='btn btn-danger btn-xs del_one' data-id='"+lessonIdArray[i]+"'>删除</a></td></tr>";
        }
        // 引入自定义js
        lessonHtmlRes += "<script src='../assets/js/checkbox.js' /><script src='../assets/js/lesson/lesson_del_up.js'></script>";
        $("tbody").html(lessonHtmlRes);
    } else {
        $("tbody").html("<tr><td colspan='3' class='text-center'>~暂无数据~</td></tr>");
    }
}

/**
 * 检查课程名称是否为空的方法
 */
function check_lessonName() {
    if (lesson_name.val().length == 0){
        // 显示提示信息
        layer.msg('请输入课程名称！', {icon: 2},{
            time: 3000, //3s后自动关闭
        });
        return false;
    }
    return true;
}


/**
 * 添加课程的方法
 * @param url
 */
function add_lesson() {
    // alert("添加课程ok");
    // "http://192.168.43.239:8080/addLesson"
    var _url = base_url + "addLesson";
    // 表单校验
    if (!check_lessonName()){return;}
    $.post(_url,$("#edit_lesson").serialize(),function (response) {
        if (response.msg){
            // 清除表单中的数据
            lesson_name.val("");
            // 添加课程成功 提示信息
            layer.msg('添加成功！', {icon: 1},{
                time: 3000, //3s后自动关闭
            });
            // 重新查询课程
            select_lesson(1);
        } else {
            // 添加课程失败 提示信息
            layer.msg('添加失败！', {icon: 2},{
                time: 3000, //3s后自动关闭
            });
        }
    });
}


/**
 * 删除的方法
 * @param url接口名称和对应id连接的字符串
 * @param cp 当前页
 */
function del_lesson(url,cp){
    // alert("删除的方法ok");
    var _url = base_url + url;
    //询问框
    layer.confirm('你确定要删除吗?', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.get(_url,function (response) {
            if (response.msg){
                // 删除成功
                // 显示提示信息
                layer.msg('删除成功！', {icon: 1},{
                    time: 3000, //3s后自动关闭
                });
                // 隐藏批量删除按钮
                $("#del_all").hide();
                // 全选按钮不被选中
                $("thead input").prop("checked",false);
                // 重新查询课程
                select_lesson(cp);
            }
        });
    }, function(){
        layer.msg('取消删除', {
            time: 2000, //2s后自动关闭
        });
    });
}

/**
 * 显示当前id课程对应信息的方法
 * @param classId 当前要编辑的课程的id
 */
function show_now_lesson(lessonId) {
    // alert("更新的方法ok");
    // http://192.168.43.239:8080/getLessonByLessonId?lessonId=
    var _url = base_url + "getLessonByLessonId?lessonId=" + lessonId;
    // alert(_url);
    $.get(_url,function (response) {
        if (response.msg){
            // console.log(response.extend.result.className);
            var lessonId = response.extend.result.lessonId;
            var lessonName = response.extend.result.lessonName;
            $("#lesson_title").html("编辑课程《" + lessonName + "》");
            lesson_name.val(lessonName);
            lesson_name.data("id",lessonId);
            $("#update_lesson").show();
            $("#add_lesson").html("添加新的");
        }
    });
}

/**
 * 更新班级的方法
 * @param url
 */
function up_lesson(cp) {
    // alert("更新课程的方法ok");
    // "http://192.168.43.239:8080/updateLesson"
    var _url = base_url + "updateLesson";
    var l_Name = lesson_name.val();
    var l_Id = lesson_name.data("id");
    // 表单校验
    if (!check_lessonName()){return;}
    $.post(_url,{lessonName: l_Name,lessonId: l_Id},function (response) {
        if (response.msg){
            // 更新成功
            // 显示更新完成后的课程信息
            show_now_lesson(l_Id);
            // 提示信息
            layer.msg('更新成功！', {icon: 1},{
                time: 3000, //3s后自动关闭
            });
            // 重新查询课程
            select_lesson(cp);
        } else {
            // 更新失败 提示信息
            layer.msg('更新失败！', {icon: 2},{
                time: 3000, //3s后自动关闭
            });
        }
    });
}