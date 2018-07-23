// 点击事件 根据id查找题目
$(".list_complete_question").on("click",function () {
    var questionId = $(this).data("id");
    $.ajax({
        url: base_url + "getQuestionByQuestionId?questionId=" + questionId,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_list_questionInfo(response.extend.result);
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
function show_list_questionInfo(questionInfo) {
    var questionsKeyHtmlRes =  "<h4>&nbsp;&nbsp;"+questionInfo["title"]+"（&nbsp;）</h4>"+
        "<p>&nbsp;&nbsp;&nbsp;&nbsp;A、"+questionInfo["optionA"]+"</p>"+
        "<p>&nbsp;&nbsp;&nbsp;&nbsp;B、"+questionInfo["optionB"]+"</p>"+
        "<p>&nbsp;&nbsp;&nbsp;&nbsp;C、"+questionInfo["optionC"]+"</p>"+
        "<p>&nbsp;&nbsp;&nbsp;&nbsp;D、"+questionInfo["optionD"]+"</p>"+
        "<p style='color: #FF5722'>&nbsp;&nbsp;&nbsp;&nbsp;参考答案："+questionInfo["questionKey"].toUpperCase()+"</p>"

    questionsKeyHtmlRes += "<div class='modal-footer'><button type='button' class='btn btn-default myClose'>Close</button><button type='button' class='btn btn-primary'>移除此题</button></div>";
    questionsKeyHtmlRes += "<script>$('.myClose').on('click',function() {layer.closeAll();})</script>";
    var info = layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['620px','330px'], //宽高
        anim: 6,//0-6的动画形式，-1不开启
        content: questionsKeyHtmlRes
    });
}

$(".del_paper_list").on("click",function () {
    $(this).parent().parent().empty();
    if ($("#list_body input").size() == 0){
        $("#list_body").html("<tr class='init_tr'><td colspan='6' class='text-center'>~暂无待添加项~</td></tr>");
    }
    // else {
    //     // $("#add_list").empty();
    // }
});