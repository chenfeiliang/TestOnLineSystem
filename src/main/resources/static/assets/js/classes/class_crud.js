var class_name = $("#class_name");
/**
 *  查询班级信息的方法
 * @param currentPage 当前页
 */
function select_class(currentPage){
    // alert("查询班级信息ok");
    // alert("当前页："+currentPage);
    $.ajax({
        url: base_url + "getPageClass?currentPage=" + currentPage,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_class_info(response.extend.result["list"],response.extend.result["pages"],currentPage,response.extend.result.navigatepageNums);
            }
        },
        error: function(a, b) {
            layer.msg('系统错误');
        }
    });
}

/**
 * 显示班级信息的方法
 * @param classInfo 当前页的班级信息
 * @param pageCount 总页数
 * @param currentPage 当前页
 * @param pageArray 页码
 */
function show_class_info(classInfo,pageCount,currentPage,pageArray) {
    // alert("显示班级信息ok");
    // count 当前页的班级数量
    // classIdArray 存放当前页所有班级的id
    // classNameArray 存放当前页所有班级的名称
    // classHtmlRes 字符串 用来动态生成班级信息表格<tr>
    // pageHtmlRes 字符串 用来动态生成页码
    var count = 0;
    var classIdArray = [];
    var classNameArray = [];
    var classHtmlRes = "";
    var pageHtmlRes = "";
    $.each(classInfo,function (i,item) {
        count++;
        classIdArray.push(item["classId"]);
        classNameArray.push(item["className"]);
    });
    // 确定当前页数
    currentPage = (currentPage > pageCount) ? pageCount : currentPage;
    // ======分页，生成页码======
    if (pageCount == 1){
        $(".pagination").html(pageHtmlRes);
    } else if (pageCount < 6 && pageCount != 1){
        // 总页数小于5页
        for (var i=1;i<=pageCount;i++){
            if (i == currentPage){
                pageHtmlRes += "<li class='active'><a href=\"javascript:void(0);\" class='page' data-cp='"+i+"'>"+i+"</a></li>";
            } else {
                pageHtmlRes += "<li><a href=\"javascript:void(0);\" class='page' data-cp='"+i+"'>"+i+"</a></li>";
            }
        }
        // 引入自定义分页js
        pageHtmlRes += "<script src='../assets/js/lesson/page.js' />"
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
        pageHtmlRes += "<script src='../assets/js/page.js' />"
        $(".pagination").html(pageHtmlRes);
    }
    // ======生成班级信息表格======
    if (count>0){
        $("thead input").data("cp",currentPage);
        for (var i=0;i<count;i++){
            // 生成班级信息表格HTML代码
            classHtmlRes += "<tr><td class='text-center'><input type='checkbox' data-id='"+classIdArray[i]+"'></td><td class='text-center c_name'>"+classNameArray[i]+"</td><td class='text-center'><a href='javascript:void(0);' class='btn btn-info btn-xs up_class' data-id='"+classIdArray[i]+"'>编辑</a><a href='javascript:void(0);' class='btn btn-danger btn-xs del_one' data-id='"+classIdArray[i]+"'>删除</a></td></tr>";
        }
        // 引入自定义js
        classHtmlRes += "<script src='../assets/js/checkbox.js' /><script src='../assets/js/classes/class_del_up.js'></script>";
        $("tbody").html(classHtmlRes);
    } else {
        $("tbody").html("<tr><td colspan='3' class='text-center'>~暂无数据~</td></tr>");
    }
}

/**
 * 检查班级名称是否为空的方法
 */
function check_className() {
    if (class_name.val().length == 0){
        // 显示提示信息
        layer.msg('请输入班级名称！', {icon: 2},{
            time: 3000, //3s后自动关闭
        });
        return false;
    }
    return true;
}


/**
 * 添加班级的方法
 * @param url
 */
function add_class() {
    // alert("添加班级ok");
    // "http://192.168.43.239:8080/addClass"
    var _url = base_url + "addClass";
    // 表单校验
    if (!check_className()){return;}
    $.post(_url,$("#edit_class").serialize(),function (response) {
        if (response.msg){
            // 清除表单中的数据
            class_name.val("");
            // 添加班级成功 提示信息
            layer.msg('添加成功', {icon: 1});
            // 重新查询班级
            select_class(1);
        } else {
            // 添加班级失败 提示信息
            layer.msg('添加失败', {icon: 2});
        }
    });
}



/**
 * 删除的方法 传入接口名称 和对应id
 * @param url
 */
function del_class(url,cp){
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
                // 重新查询班级
                select_class(cp);
            }
        });
    }, function(){
        layer.msg('取消删除', {
            time: 2000, //2s后自动关闭
        });
    });
}

/**
 * 显示当前id班级对应信息的方法
 * @param classId 当前要编辑的班级的id
 */
function show_now_class(classId) {
    // alert("更新的方法ok");
    // http://192.168.43.239:8080/getClassByClassId?classId=76
    var _url = base_url + "getClassByClassId?classId=" + classId;
    // alert(_url);
    $.get(_url,function (response) {
        if (response.msg){
            // console.log(response.extend.result.className);
            var classId = response.extend.result.classId;
            var className = response.extend.result.className;
            $("#class_title").html("编辑班级《" + className + "》");
            class_name.val(className);
            class_name.data("id",classId);
            $("#update_class").show();
            $("#add_class").html("添加新的");
        }
    });
}

/**
 * 更新班级的方法
 * @param url
 */
function up_class() {
    // alert("更新班级的方法ok");
    // "http://192.168.43.239:8080/updateClass"
    var _url = base_url + "updateClass";
    var c_Name = class_name.val();
    var c_Id = class_name.data("id");
    // 表单校验
    check_className();
    $.post(_url,{className: c_Name,classId: c_Id},function (response) {
        if (response.msg){
            // 更新成功
            // 显示更新完成后的班级信息
            show_now_class(c_Id);
            // 提示信息 5秒淡出效果
            layer.msg('更新成功', {icon: 1});
            // 重新查询班级
            select_class(1);
        } else {
            // 更新失败 提示信息
            layer.msg('更新失败', {icon: 2});
        }
    });
}