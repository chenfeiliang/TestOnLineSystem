// 获取参数
var qId = getUrlParam("qId");
// 获取jq对象，避免重复获取
var t = $("#title");
var jq_optionA = $("#optionA");
var jq_optionB = $("#optionB");
var jq_optionC = $("#optionC");
var jq_optionD = $("#optionD");
var jq_questionKey = $("#questionKey");
var jq_questionLevel = $("#questionLevel");
var jq_lessonId = $("#lessonId");
// alert(questionId);
// http://192.168.43.239:8080/getQuestionByQuestionId?questionId=
var _url = base_url + "getQuestionByQuestionId?questionId=" + qId;
$.get(_url,function (response) {
    if (response.msg){
        // console.log(response.extend.result);
        var title = response.extend.result.title;
        // var lessonName = response.extend.result.lesson["lessonName"];
        $("#question_title").html("编辑题目《" + title + "》");
        t.val(title);
        t.data("id",response.extend.result.questionId);
        jq_optionA.val(response.extend.result.optionA);
        jq_optionB.val(response.extend.result.optionB);
        jq_optionC.val(response.extend.result.optionC);
        jq_optionD.val(response.extend.result.optionD);
        jq_questionKey.find("option[value = '"+response.extend.result.questionKey+"']").attr("selected","selected");
        jq_questionLevel.find("option[value = '"+response.extend.result.questionLevel+"']").attr("selected","selected");
        jq_lessonId.find("option[value = '"+response.extend.result.lesson["lessonId"]+"']").attr("selected","selected");
    }
});

// 保存更新按钮点击事件
$("#update_question").on("click",function () {
    $.post(base_url+"updateQuestion",
        {questionId:qId,title:t.val(),
            optionA:jq_optionA.val(),
            optionB:jq_optionB.val(),
            optionC:jq_optionC.val(),
            optionD:jq_optionD.val(),
            questionKey:jq_questionKey.val(),
            questionLevel:jq_questionLevel.val(),
            lessonId:jq_lessonId.val(),
            creator:$.session.get('admin_name')
        },
        function (response) {
        if (response.msg){
            // 更新成功
            // 提示信息
            layer.confirm('当前更新已保存!是否继续编辑该题目', {
                btn: ['继续编辑','查看题目'] //按钮
            }, function(){
                $(location).attr("href","question_up.html?qId="+qId);
            }, function(){
                $(location).attr("href","question_show.html");
            });
        } else {
            // 更新失败 提示信息
            layer.msg('更新失败！', {icon: 2},{
                time: 3000, //3s后自动关闭
            });
        }
    });
});