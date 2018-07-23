/**
 *  查询题目信息的方法
 * @param currentPage 当前页
 */
function select_question(currentPage){
    // alert("查询题目信息ok");
    // alert("当前页："+currentPage);
    $.ajax({
        url: base_url + "getPageQuestion?currentPage=" + currentPage,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_question_info(response.extend.result.list,response.extend.result["pages"],currentPage,response.extend.result.navigatepageNums,false);
                layer.msg('加载完毕',{
                    time: 1000, //1s后自动关闭
                });
            }
        },
        error: function(a, b) {
            layer.msg('系统错误');
        }
    });
}

/**
 * 显示题目信息的方法
 * @param questionInfo 当前页的题目信息
 * @param pageCount 总页数
 * @param currentPage 当前页
 * @param pageArray 页码
 * @param keyWordFlag 关键字查询标致
 */
function show_question_info(questionInfo,pageCount,currentPage,pageArray,keyWordFlag = false) {
    // alert("显示题目信息ok");
    // count 当前页的题目数量
    var count = 0;
    // qIdArray 存放当前页所有题目的id
    var qIdArray = [];
    // qTitleArray 存放当前页所有题目的描述
    var qTitleArray = [];
    // qAArray 存放当前页所有题目的A选项
    var qAArray = [];
    // qBArray 存放当前页所有题目的B选项
    var qBArray = [];
    // qCArray 存放当前页所有题目的C选项
    var qCArray = [];
    // qDArray 存放当前页所有题目的D选项
    var qDArray = [];
    // qKeyArray 存放当前页所有题目的标准答案
    var qKeyArray = [];
    // qLevelArray 存放当前页所有题目的难度等级
    var qLevelArray = [];
    // qLessonArray 存放当前页所有题目的所属课程
    var qLessonArray = [];
    // qCreatorArray 存放当前页所有题目的创建人
    var qCreatorArray = [];
    // questionHtmlRes 字符串 用来动态生成题目信息表格<tr>
    var questionHtmlRes = "";
    // pageHtmlRes 字符串 用来动态生成页码
    var pageHtmlRes = "";
    $.each(questionInfo,function (i,item) {
        count++;
        qIdArray.push(item["questionId"]);
        qTitleArray.push(item["title"]);
        qAArray.push(item["optionA"]);
        qBArray.push(item["optionB"]);
        qCArray.push(item["optionC"]);
        qDArray.push(item["optionD"]);
        qKeyArray.push(item["questionKey"]);
        qLevelArray.push(item["questionLevel"]);
        item["lesson"] == null ? qLessonArray.push("Java") : qLessonArray.push(item["lesson"]["lessonName"]);
        qCreatorArray.push(item["creator"]);
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
        // 根据关键字标志引入自定义分页js
        pageHtmlRes += keyWordFlag ? "<script src='../assets/js/question/keyword_page.js' />" : "<script src='../assets/js/question/page.js' />";
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
        pageHtmlRes += "<script src='../assets/js/question/page.js' />"
        $(".pagination").html(pageHtmlRes);
    }
    // ======生成题目信息表格======
    if (count>0){
        $("thead input").data("cp",currentPage);
        var qTitle;
        var qA;
        var qB;
        var qC;
        var qD;
        for (var i=0;i<count;i++){
            // 格式化数据长度
            qTitle = qTitleArray[i].length > 9 ? qTitleArray[i].substr(0,9)+"..." : qTitleArray[i];
            qA = qAArray[i].length > 9 ? qAArray[i].substr(0,9)+"..." : qAArray[i];
            qB = qBArray[i].length > 9 ? qBArray[i].substr(0,9)+"..." : qBArray[i];
            qC = qCArray[i].length > 9 ? qCArray[i].substr(0,9)+"..." : qCArray[i];
            qD = qDArray[i].length > 9 ? qDArray[i].substr(0,9)+"..." : qDArray[i];
            // 生成题目信息表格HTML代码
            questionHtmlRes += "<tr><td class='text-center'><input type='checkbox' data-id='"+qIdArray[i]+"'></td>"+
                "<td class='text-center keyword' onclick=\"show_title('"+qTitleArray[i]+"')\">"+qTitle+"</td>"+
                "<td class='text-center' onclick=\"show_A('"+qAArray[i]+"')\">"+qA+"</td>"+
                "<td class='text-center' onclick=\"show_B('"+qBArray[i]+"')\">"+qB+"</td>"+
                "<td class='text-center' onclick=\"show_C('"+qCArray[i]+"')\">"+qC+"</td>"+
                "<td class='text-center' onclick=\"show_D('"+qDArray[i]+"')\">"+qD+"</td>"+
                "<td class='text-center'>"+qKeyArray[i]+"</td>"+
                "<td class='text-center'>"+qLevelArray[i]+"</td>"+
                "<td class='text-center'>"+qLessonArray[i]+"</td>"+
                "<td class='text-center'>"+qCreatorArray[i]+"</td>"+
                "<td class='text-center'><a href='javascript:void(0);' class='btn btn-info btn-xs up_question' data-id='"+qIdArray[i]+"'>编辑</a><a href='javascript:void(0);' class='btn btn-danger btn-xs del_one' data-id='"+qIdArray[i]+"'>删除</a></td></tr>";
        }
        // 引入自定义js
        questionHtmlRes += "<script src='../assets/js/checkbox.js' /><script src='../assets/js/question/question_edit.js'></script>";
        // 根据关键词标志引入样式js
        questionHtmlRes += keyWordFlag ? "<script src='../assets/js/question/keyword_css.js'></script>" : '';
        $("tbody").html(questionHtmlRes);
    } else {
        $("tbody").html("<tr><td colspan='11' class='text-center'>~暂无数据~</td></tr>");
    }
}

/**
 * 删除的方法
 * @param url接口名称和对应id连接的字符串
 * @param cp 当前页
 */
function del_question(url,cp){
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
                // 重新查询题目
                select_question(cp,false);
            }
        });
    }, function(){
        layer.msg('取消删除', {
            time: 2000, //2s后自动关闭
        });
    });
}

/**
 * 显示当前id题目对应信息的方法
 * @param classId 当前要编辑的题目的id
 */
function show_now_question(questionId) {
    $(location).attr("href","question_up.html?qId=" + questionId);
}

/**
 * 关键字查询
 * @param kw 关键字
 * @param currentPage 当前页
 */
function select_question_byKeyWord(kw,currentPage) {
    $.ajax({
        url: base_url + "getPageQuestionByKeyWord?keyword=" + kw + "&currentPage=" + currentPage,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_question_info(response.extend.result.list,response.extend.result["pages"],currentPage,response.extend.result.navigatepageNums,true);
                layer.msg('搜索完成',{
                    time: 1000, //1s后自动关闭
                });
            }
        },
        error: function(a, b) {
            layer.msg('系统错误');
        }
    });
}