/**
 *  查询成绩信息的方法
 * @param currentPage 当前页
 */
function select_achievement(currentPage){
    $.ajax({
        url: base_url + "getAchievement?currentPage=" + currentPage,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_achievement_info(response.extend.result.list,response.extend.result["pages"],currentPage,response.extend.result.navigatepageNums);
            }
        },
        error: function(a, b) {
            layer.msg('系统错误');
        }
    });
}

function select_achievement_byClassId(cId,currentPage){
    $.ajax({
        url: base_url + "getAchievement?currentPage=" + currentPage + "&classId=" + cId,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_achievement_info(response.extend.result.list,response.extend.result["pages"],currentPage,response.extend.result.navigatepageNums);
            }
        },
        error: function(a, b) {
            layer.msg('系统错误');
        }
    });
}

/**
 * 显示试卷信息的方法
 * @param paperInfo 当前页的试卷信息
 * @param pageCount 总页数
 * @param currentPage 当前页
 * @param pageArray 页码
 */
function show_achievement_info(achievementInfo,pageCount,currentPage,pageArray) {
    var count = 0;
    var accountArray = [];
    var examinTitleArray = [];
    var lessonNameArray = [];
    var classNameArray = [];
    var scoreArray = [];
    var achievementHtmlRes = "";
    var pageHtmlRes = "";
    $.each(achievementInfo,function (i,item) {
        count++;
        accountArray.push(item["account"]);
        examinTitleArray.push(item["examin"]["title"]);
        lessonNameArray.push(item["lesson"]["lessonName"]);
        classNameArray.push(item["myClass"]["className"]);
        scoreArray.push(item["score"]);
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
        pageHtmlRes += "<script src='../assets/js/page.js' />"
        $(".pagination").html(pageHtmlRes);
    }
    // ======生成用户信息表格======
    if (count>0){
        $("thead input").data("cp",currentPage);
        for (var i=0;i<count;i++){
            // 生成题目信息表格HTML代码
            // data-id='"+paperIdArray[i]+"'
            achievementHtmlRes += "<tr><td class='text-center'><input type='checkbox'></td>"+
                "<td class='text-center'>"+accountArray[i]+"</td>"+
                "<td class='text-center'>"+examinTitleArray[i]+"</td>"+
                "<td class='text-center'>"+lessonNameArray[i]+"</td>"+
                "<td class='text-center'>"+classNameArray[i]+"</td>"+
                "<td class='text-center'>"+scoreArray[i]+"</td>"+
                "<td class='text-center'><a href='javascript:void(0);' class='btn btn-info btn-xs up_paper'>编辑</a>&nbsp;&nbsp;<a href='javascript:void(0);' class='btn btn-danger btn-xs del_one'>删除</a></td></tr>";
        }
        // 引入自定义js
        achievementHtmlRes += "<script src='../assets/js/checkbox.js' />";
        $("tbody").html(achievementHtmlRes);
        layer.msg('加载完毕',{
            time: 1000, //1s后自动关闭
        });
    } else {
        $("tbody").html("<tr><td colspan='7' class='text-center'>~暂无相关成绩信息~</td></tr>");
    }
}