/**
 *  查询题库信息的方法
 * @param currentPage 当前页
 */
function select_questions(currentPage){
    // alert("查询用户信息ok");
    // alert("当前页："+currentPage);
    $.ajax({
        url: base_url + "getPageQuestion?currentPage=" + currentPage,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_questions_info(response.extend.result.list,response.extend.result["pages"],currentPage,response.extend.result.navigatepageNums,false);
            }
        },
        error: function(a, b) {
            layer.msg('系统错误');
        }
    });
}

/**
 * 根据课程id查找对应题库的方法
 * @param currentPage 当前页
 * @param lessonId 课程id
 */
function select_questions_byLessonId(currentPage,lessonId) {
    if (lessonId == 0){
        select_questions(currentPage);
    } else {
        $.ajax({
            url: base_url + "getQuestionByLessonId?currentPage=" + currentPage + "&lessonId=" + lessonId,
            type: "GET",
            dataType:"json",
            success: function (response) {
                if (response.msg){
                    show_questions_info(response.extend.result.list,response.extend.result["pages"],currentPage,response.extend.result.navigatepageNums,true);
                }
            },
            error: function(a, b) {
                layer.msg('系统错误');
            }
        });
    }
}

/**
 * 显示题目信息的方法
 * @param userInfo 当前页的题目信息
 * @param pageCount 总页数
 * @param currentPage 当前页
 * @param pageArray 页码
 * @param ByLessonIdFlag 标志
 */
function show_questions_info(questionsInfo,pageCount,currentPage,pageArray,ByLessonIdFlag = false) {
    // alert("显示用户信息ok");
    // count 当前页的用户数量
    var count = 0;
    // questionIdArray 存放所有题目的id
    var questionIdArray = [];
    // questionTitleArray 存放当前页所有题目描述
    var questionTitleArray = [];
    // questionHtmlRes 字符串 用来动态生成题目信息表格<tr>
    var questionHtmlRes = "";
    // pageHtmlRes 字符串 用来动态生成页码
    var pageHtmlRes = "";
    $.each(questionsInfo,function (i,item) {
        count++;
        questionIdArray.push(item["questionId"]);
        questionTitleArray.push(item["title"]);
    });
    // 确定当前页数
    currentPage = (currentPage > pageCount) ? pageCount : currentPage;
    // ======分页，生成页码======
    if (pageCount == 1){
        $(".question_page").html(pageHtmlRes);
    } else if (pageCount < 6 && pageCount != 1){
        // 总页数小于5页 只有1页不分页
        for (var i=1;i<=pageCount;i++){
            if (i == currentPage){
                pageHtmlRes += "<li class='active'><a href=\"javascript:void(0);\" class='page' data-cp='"+i+"'>"+i+"</a></li>";
            } else {
                pageHtmlRes += "<li><a href=\"javascript:void(0);\" class='page' data-cp='"+i+"'>"+i+"</a></li>";
            }
        }
        // 根据关键字标志引入自定义分页js
        pageHtmlRes += ByLessonIdFlag ? "<script src='../assets/js/paper/bylessonid_page.js' />" : "<script src='../assets/js/paper/page.js' />";
        $(".question_page").html(pageHtmlRes);
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
        $(".question_page").html(pageHtmlRes);
    }
    // ======生成题目信息表格======
    if (count>0){
        $(".questions").data("cp",currentPage);
        for (var i=0;i<count;i++){
            // 生成题目信息表格HTML代码
            questionHtmlRes += "<tr><td class='text-center'><input type='checkbox' data-id='"+questionIdArray[i]+"'></td>"+
                "<td class='text-center'>"+questionTitleArray[i]+"</td>"+
                "<td class='text-center'><a href='javascript:void(0);' class='btn btn-info btn-xs complete_question' data-id='"+questionIdArray[i]+"'>完整试题</a></td>"+
                "<td class='text-center'><a href='javascript:void(0);' class='btn btn-info btn-xs add_paper_list' data-id='"+questionIdArray[i]+"'>加入列表</a></td></tr>";
        }
        // 引入自定义js
        questionHtmlRes += "<script src='../assets/js/paper/question_checkbox.js' /><script src='../assets/js/paper/complete_question.js'></script>";
        $("#questions").html(questionHtmlRes);
        layer.msg('题库加载完毕',{
            time: 1000, //1s后自动关闭
        });
    } else {
        $("#questions").html("<tr><td colspan='4' class='text-center'>~暂无相关题库数据~</td></tr>");
    }
}