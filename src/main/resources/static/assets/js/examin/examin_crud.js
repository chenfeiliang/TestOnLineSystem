/**
 * 查询考场的方法
 * @param currentPage
 */
function select_examin(currentPage){
    // alert("当前页："+currentPage);
    $.ajax({
        url: base_url + "getPageExam?currentPage=" + currentPage,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_examin_info(response.extend.result["list"],response.extend.result["pages"],currentPage,response.extend.result.navigatepageNums);
            }
        },
        error: function(a, b) {
            layer.msg('系统错误');
        }
    });
}

/**
 * 显示考场信息的方法
 * @param examinInfo 当前页的考场信息
 * @param pageCount 总页数
 * @param currentPage 当前页
 * @param pageArray 页码
 */
function show_examin_info(examinInfo,pageCount,currentPage,pageArray) {
    var count = 0;
    var examinIdArray = [];
    var examinTitleArray = [];
    var paperTitleArray = [];
    var lessonNameArray = [];
    var accountsArray = [];
    var typeArray = [];
    var beginTimeArray = [];
    var endTimeArray = [];
    var examinHtmlRes = "";
    var pageHtmlRes = "";
    var beginTimeArrayFormat = "";
    var endTimeArrayFormat = "";
    $.each(examinInfo,function (i,item) {
        count++;
        examinIdArray.push(item["examinId"]);
        examinTitleArray.push(item["title"]);
        paperTitleArray.push(item["paper"]["title"]);
        lessonNameArray.push(item["lesson"]["lessonName"]);
        accountsArray.push(item["accounts"]);
        typeArray.push(item["type"]);
        var d = new Date(item["beginTime"]);
        beginTimeArrayFormat = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "+d.getHours()+":"+d.getMinutes();
        beginTimeArray.push(beginTimeArrayFormat);
        var d1 = new Date(item["endTime"]);
        endTimeArrayFormat = d1.getFullYear()+"-"+(d1.getMonth()+1)+"-"+d1.getDate()+" "+d1.getHours()+":"+d1.getMinutes();
        endTimeArray.push(endTimeArrayFormat);
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
        pageHtmlRes += "<script src='../assets/js/examin/page.js' />"
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
        pageHtmlRes += "<script src='../assets/js/examin/page.js' />"
        $(".pagination").html(pageHtmlRes);
    }
    // ======生成班级信息表格======
    if (count>0){
        $("thead input").data("cp",currentPage);
        for (var i=0;i<count;i++){
            // 生成考场信息表格HTML代码
            examinHtmlRes += "<tr><td class='text-center'><input type='checkbox' data-id='"+examinIdArray[i]+"'></td>" +
                "<td class='text-center'>"+examinTitleArray[i]+"</td>" +
                "<td class='text-center'>"+paperTitleArray[i]+"</td>" +
                "<td class='text-center'>"+lessonNameArray[i]+"</td>" +
                "<td class='text-center'><a href='javascript:;' class='btn btn-info btn-xs show_users_account' data-id='"+accountsArray[i]+"'>查看考生</a></td>"+
                "<td class='text-center'>"+typeArray[i]+"</td>" +
                "<td class='text-center'>"+beginTimeArray[i]+"</td>" +
                "<td class='text-center'>"+endTimeArray[i]+"</td>" +
                "<td class='text-center'><a href='javascript:void(0);' class='btn btn-info btn-xs up_examin' data-id='"+examinIdArray[i]+"'>编辑</a><a href='javascript:void(0);' class='btn btn-danger btn-xs del_one' data-id='"+examinIdArray[i]+"'>删除</a></td></tr>";
        }
        // 引入自定义js
        examinHtmlRes += "<script src='../assets/js/checkbox.js' /><script src='../assets/js/examin/show_users_account.js'></script>";
        $("tbody").html(examinHtmlRes);
    } else {
        $("tbody").html("<tr><td colspan='9' class='text-center'>~暂无数据~</td></tr>");
    }
}