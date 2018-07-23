/**
 *  查询试卷信息的方法
 * @param currentPage 当前页
 */
function select_paper(currentPage){
    // alert("查询试卷信息ok");
    // alert("当前页："+currentPage);
    $.ajax({
            url: base_url + "getPagePaper?currentPage=" + currentPage,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_paper_info(response.extend.result.list,response.extend.result["pages"],currentPage,response.extend.result.navigatepageNums);
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
function show_paper_info(paperInfo,pageCount,currentPage,pageArray) {
    // alert("显示试卷信息ok");
    // count 当前页的试卷数量
    var count = 0;
    // paperIdArray 存放当前页所有试卷的Id
    var paperIdArray = [];
    // paperTitleArray 存放当前页所有试卷的名称
    var paperTitleArray = [];
    // questionIdsArray 存放当前页试卷的所有题目
    var questionIdsArray = [];
    // paperLessonNameArray 存放当前页所有试卷所属的课程
    var paperLessonNameArray = [];
    // paperCreatorArray 存放当前页所有试卷的创建人
    var paperCreatorArray = [];
    // paperHtmlRes 字符串 用来动态生成试卷信息表格<tr>
    var paperHtmlRes = "";
    // pageHtmlRes 字符串 用来动态生成页码
    var pageHtmlRes = "";
    $.each(paperInfo,function (i,item) {
        count++;
        paperIdArray.push(item["paperId"]);
        paperTitleArray.push(item["title"]);
        questionIdsArray.push(item["questionIds"]);
        paperLessonNameArray.push(item["lesson"]["lessonName"]);
        paperCreatorArray.push(item["creator"]);
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
        pageHtmlRes += "<script src='../assets/js/paper/page.js' />"
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
        pageHtmlRes += "<script src='../assets/js/paper/page.js' />"
        $(".pagination").html(pageHtmlRes);
    }
    // ======生成用户信息表格======
    if (count>0){
        $("thead input").data("cp",currentPage);
        for (var i=0;i<count;i++){
            // 生成题目信息表格HTML代码
            paperHtmlRes += "<tr><td class='text-center'><input type='checkbox' data-id='"+paperIdArray[i]+"'></td>"+
                "<td class='text-center'>"+paperTitleArray[i]+"</td>"+
                "<td class='text-center'>"+paperLessonNameArray[i]+"</td>"+
                "<td class='text-center'>"+paperCreatorArray[i]+"</td>"+
                "<td class='text-center'><a href='javascript:;' class='btn btn-info btn-xs show_questions' data-id='"+paperIdArray[i]+"'>查看试题</a></td>"+
                "<td class='text-center'><a href='javascript:;' class='btn btn-info btn-xs show_key' data-id='"+paperIdArray[i]+"'>查看答案</a></td>"+
                "<td class='text-center'><a href='javascript:void(0);' class='btn btn-info btn-xs up_paper' data-id='"+paperIdArray[i]+"'>编辑</a>&nbsp;&nbsp;<a href='javascript:void(0);' class='btn btn-danger btn-xs del_one' data-id='"+paperIdArray[i]+"'>删除</a></td></tr>";
        }
        // 引入自定义js
        paperHtmlRes += "<script src='../assets/js/checkbox.js' /><script src='../assets/js/paper/paper_edit.js'></script>";
        $("tbody").html(paperHtmlRes);
        layer.msg('加载完毕',{
            time: 1000, //1s后自动关闭
        });
    } else {
        $("tbody").html("<tr><td colspan='7' class='text-center'>~暂无相关试卷~</td></tr>");
    }
}