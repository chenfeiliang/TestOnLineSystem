/**
 *  查询用户信息的方法
 * @param currentPage 当前页
 */
function select_user(currentPage){
    // alert("查询用户信息ok");
    // alert("当前页："+currentPage);
    $.ajax({
        url: base_url + "getPageUser?currentPage=" + currentPage,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_user_info(response.extend.result.list,response.extend.result["pages"],currentPage,response.extend.result.navigatepageNums);
            }
        },
        error: function(a, b) {
            layer.msg('系统错误');
        }
    });
}

function select_user_byClass(classId,currentPage) {
    if (classId == 0){
        select_user(currentPage);
    } else {
        $.ajax({
            url: base_url + "getUserByClassId?classId=" + classId + "&currentPage=" + currentPage,
            type: "GET",
            dataType:"json",
            success: function (response) {
                if (response.msg){
                    show_user_info(response.extend.result.list,response.extend.result["pages"],currentPage,response.extend.result.navigatepageNums);
                }
            },
            error: function(a, b) {
                layer.msg('系统错误');
            }
        });
    }
}

/**
 * 显示用户信息的方法
 * @param userInfo 当前页的用户信息
 * @param pageCount 总页数
 * @param currentPage 当前页
 * @param pageArray 页码
 */
function show_user_info(userInfo,pageCount,currentPage,pageArray) {
    // alert("显示用户信息ok");
    // count 当前页的用户数量
    var count = 0;
    // userImageArray 存放当前页所有用户的头像路径
    var userImageArray = [];
    // userAccountArray 存放当前页所有用户的用户名
    var userAccountArray = [];
    // userRealNameArray 存放当前页所有用户真实姓名
    var userRealNameArray = [];
    // userClassNameArray 存放当前页所有用户所属的班级
    var userClassNameArray = [];
    // userMobileCArray 存放当前页所有用户的手机号
    var userMobileCArray = [];
    // userHtmlRes 字符串 用来动态生成用户信息表格<tr>
    var userHtmlRes = "";
    // pageHtmlRes 字符串 用来动态生成页码
    var pageHtmlRes = "";
    $.each(userInfo,function (i,item) {
        count++;
        item["image"] == null ? userImageArray.push("../assets/img/default.png") : userImageArray.push(item["image"]);
        userAccountArray.push(item["account"]);
        item["realName"] == null ? userRealNameArray.push("不正常用户") : userRealNameArray.push(item["realName"]);
        item["myClass"] == null ? userClassNameArray.push("JAVA") : userClassNameArray.push(item["myClass"]["className"]);
        item["mobile"] == null ? userMobileCArray.push("暂无手机号") : userMobileCArray.push(item["mobile"]);
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
        pageHtmlRes += "<script src='../assets/js/user/page.js' />"
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
        pageHtmlRes += "<script src='../assets/js/user/page.js' />"
        $(".pagination").html(pageHtmlRes);
    }
    // ======生成用户信息表格======
    if (count>0){
        $("thead input").data("cp",currentPage);
        for (var i=0;i<count;i++){
            // 生成题目信息表格HTML代码
            userHtmlRes += "<tr><td class='text-center'><input type='checkbox' data-id='"+userAccountArray[i]+"'></td>"+
                "<td class='text-center'><img class='avatar' src='"+userImageArray[i]+"'></td>"+
                "<td class='text-center'>"+userAccountArray[i]+"</td>"+
                "<td class='text-center'>"+userRealNameArray[i]+"</td>"+
                "<td class='text-center'>"+userClassNameArray[i]+"</td>"+
                "<td class='text-center'>"+userMobileCArray[i]+"</td>"+
                "<td class='text-center'><a href='javascript:void(0);' class='btn btn-info btn-xs up_user' data-id='"+userAccountArray[i]+"'>编辑</a><a href='javascript:void(0);' class='btn btn-danger btn-xs del_one' data-id='"+userAccountArray[i]+"'>删除</a></td></tr>";
        }
        // 引入自定义js
        userHtmlRes += "<script src='../assets/js/checkbox.js' /><script src='../assets/js/user/user_edit.js'></script>";
        $("tbody").html(userHtmlRes);
        layer.msg('加载完毕',{
            time: 1000, //1s后自动关闭
        });
    } else {
        $("tbody").html("<tr><td colspan='7' class='text-center'>~暂无相关班级用户数据~</td></tr>");
    }
}

/**
 * 删除的方法
 * @param url接口名称和对应id连接的字符串
 * @param cp 当前页
 */
function del_user(url,cp){
    var _url = base_url + url;
    //询问框
    layer.confirm('你确定要删除吗?', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.get(_url,function (response) {
            if (response.msg){
                // 删除成功
                // 显示提示信息
                layer.msg('删除成功', {icon: 1},{
                    time: 3000, //3s后自动关闭
                });
                // 隐藏批量删除按钮
                $("#del_all").hide();
                // 全选按钮不被选中
                $("thead input").prop("checked",false);
                // 重新查询用户
                select_user(cp);
            } else {
                layer.msg('系统错误', {
                    time: 2000, //2s后自动关闭
                });
            }
        });
    }, function(){
        layer.msg('取消删除', {
            time: 2000, //2s后自动关闭
        });
    });
}

/**
 * 显示当前account用户对应信息的方法
 * @param account 当前要编辑的用户的account
 */
function show_now_user(account) {
    $(location).attr("href","user_up.html?userAccount=" + account);
}

/**
 * 关键字查询
 * @param kw 关键字
 * @param currentPage 当前页
 */
function select_user_byKeyWord(kw,currentPage) {
    $.ajax({
        url: base_url + "getPageUserByKeyWord?keyword=" + kw + "&currentPage=" + currentPage,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_user_info(response.extend.result.list,response.extend.result["pages"],currentPage,response.extend.result.navigatepageNums);
            }
        },
        error: function(a, b) {
            layer.msg('系统错误');
        }
    });
}