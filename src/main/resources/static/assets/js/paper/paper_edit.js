var paperId = $("tbody input").data("id");

// 查看试题按钮点击事件
$(".show_questions").on("click",function () {
    $.ajax({
        url: base_url + "getPaperById?paperId=" + paperId,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_questions_info(response.extend.result.questions);
            }
        },
        error: function(a, b) {
            layer.msg('系统错误');
        }
    });
});

// 查看答案按钮点击事件
$(".show_key").on("click",function () {
    $.ajax({
        url: base_url + "getPaperById?paperId=" + paperId,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_questions_key(response.extend.result.questions);
            }
        },
        error: function(a, b) {
            layer.msg('系统错误');
        }
    });
})

/**
 * 显示带有答案的题目信息的方法
 * @param questionsInfo
 */
function show_questions_key(questionsInfo) {
    // count 当前页的题目数量
    var count = 0;
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
    // qKeyArray 存放当前页所有题目的答案
    var qKeyArray = [];
    // questionsHtmlRes 字符串 用来动态生成题目信息
    var questionsKeyHtmlRes = "";
    $.each(questionsInfo,function (i,item) {
        count++;
        qTitleArray.push(item["title"]);
        qAArray.push(item["optionA"]);
        qBArray.push(item["optionB"]);
        qCArray.push(item["optionC"]);
        qDArray.push(item["optionD"]);
        qKeyArray.push(item["questionKey"]);
    });
    if (count>0){
        for (var i=0;i<count;i++){
            questionsKeyHtmlRes +=  "<h4>"+(i+1)+"."+qTitleArray[i]+"。（&nbsp;&nbsp;）</h4>"+
                "<p>A."+qAArray[i]+"</p>"+
                "<p>B."+qBArray[i]+"</p>"+
                "<p>C."+qCArray[i]+"</p>"+
                "<p>D."+qDArray[i]+"</p>"+
                "<p style='color: #FF5722'>参考答案："+qKeyArray[i]+"</p>"
        }
        questionsKeyHtmlRes += "<div class='modal-footer'><button type='button' class='btn btn-default myClose'>Close</button><button type='button' class='btn btn-primary'>前往打印</button></div>";
        questionsKeyHtmlRes += "<script>$('.myClose').on('click',function() {layer.closeAll();})</script>";
        var info = layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['620px','600px'], //宽高
            anim: 6,//0-6的动画形式，-1不开启
            content: questionsKeyHtmlRes
        });
    } else {
        layer.msg('系统错误', {
            time: 2000, //2s后自动关闭
        });
    }
}

/**
 * 显示题目信息的方法
 * @param questionsInfo
 */
function show_questions_info(questionsInfo) {
    // count 当前页的题目数量
    var count = 0;
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
    // questionsHtmlRes 字符串 用来动态生成题目信息
    var questionsHtmlRes = "";
    $.each(questionsInfo,function (i,item) {
        count++;
        qTitleArray.push(item["title"]);
        qAArray.push(item["optionA"]);
        qBArray.push(item["optionB"]);
        qCArray.push(item["optionC"]);
        qDArray.push(item["optionD"]);
    });
    if (count>0){
        for (var i=0;i<count;i++){
            questionsHtmlRes +=  "<h4>"+(i+1)+"."+qTitleArray[i]+"。（&nbsp;&nbsp;）</h4>"+
                "<p>A."+qAArray[i]+"</p>"+
                "<p>B."+qBArray[i]+"</p>"+
                "<p>C."+qCArray[i]+"</p>"+
                "<p>D."+qDArray[i]+"</p><br />";
        }
        questionsHtmlRes += "<div class='modal-footer'><button type='button' class='btn btn-default myClose'>Close</button><button type='button' class='btn btn-primary'>前往打印</button></div>";
        questionsHtmlRes += "<script>$('.myClose').on('click',function() {layer.closeAll();})</script>";
        var info = layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['620px','600px'], //宽高
            anim: 6,//0-6的动画形式，-1不开启
            content: questionsHtmlRes
        });
    } else {
        layer.msg('系统错误', {
            time: 2000, //2s后自动关闭
        });
    }
}