$(".add_paper_list").on("click",function () {
    var questionId = $(this).data("id");
    $.ajax({
        url: base_url + "getQuestionByQuestionId?questionId=" + questionId,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                $(".init_tr").empty();
                add_list(response.extend.result);
            }
        },
        error: function(a, b) {
            layer.msg('系统错误');
        }
    });
});

function add_list(questionInfo) {
    // questionIdArray 存放所有题目的id
    var questionId = questionInfo["questionId"];
    // questionTitleArray 存放当前页所有题目描述
    var questionTitle = questionInfo["title"];
    var questionKey = questionInfo["questionKey"];
    // questionHtmlRes 字符串 用来动态生成题目信息表格<tr>
    var questionHtmlRes = "";
    // 生成题目信息表格HTML代码
    questionHtmlRes += "<tr><td class='text-center'><input type='checkbox' data-id='"+questionId+"'></td>"+
        "<td class='text-center haskey' data-key='"+questionKey+"'>"+questionTitle+"</td>"+
        "<td class='text-center'><a href='javascript:void(0);' class='btn btn-info btn-xs list_complete_question' data-id='"+questionId+"'>完整试题</a></td>"+
        "<td class='text-center'><a href='javascript:void(0);' class='btn btn-info btn-xs del_paper_list' data-id='"+questionId+"'>移出列表</a></td></tr>"+
        "<script src='../assets/js/paper/add_list.js'></script>";
    $("#list_body").append(questionHtmlRes);
}

// 点击事件 根据id查找题目
$(".complete_question").on("click",function () {
    var questionId = $(this).data("id");
    $.ajax({
        url: base_url + "getQuestionByQuestionId?questionId=" + questionId,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_question_completeInfo(response.extend.result);
            }
        },
        error: function(a, b) {
            layer.msg('系统错误');
        }
    });
});

/**
 * 显示题目完整信息的方法
 * @param questionInfo
 */
function show_question_completeInfo(questionInfo) {
    var questionsKeyHtmlRes =  "<h4>&nbsp;&nbsp;"+questionInfo["title"]+"（&nbsp;）</h4>"+
        "<p>&nbsp;&nbsp;&nbsp;&nbsp;A、"+questionInfo["optionA"]+"</p>"+
        "<p>&nbsp;&nbsp;&nbsp;&nbsp;B、"+questionInfo["optionB"]+"</p>"+
        "<p>&nbsp;&nbsp;&nbsp;&nbsp;C、"+questionInfo["optionC"]+"</p>"+
        "<p>&nbsp;&nbsp;&nbsp;&nbsp;D、"+questionInfo["optionD"]+"</p>"+
        "<p style='color: #FF5722'>&nbsp;&nbsp;&nbsp;&nbsp;参考答案："+questionInfo["questionKey"].toUpperCase()+"</p>"

    questionsKeyHtmlRes += "<div class='modal-footer'><button type='button' class='btn btn-default myClose'>Close</button><button type='button' class='btn btn-primary'>加入列表</button></div>";
    questionsKeyHtmlRes += "<script>$('.myClose').on('click',function() {layer.closeAll();})</script>";
    var info = layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['620px','330px'], //宽高
        anim: 6,//0-6的动画形式，-1不开启
        content: questionsKeyHtmlRes
    });
}