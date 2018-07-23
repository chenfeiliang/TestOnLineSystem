// 避免多次重复获取jq对象
var simple = $("#simple");
var commonly = $("#commonly");
var difficulty = $("#difficulty");
$('#system_addPaper').on('click', function () {
    // 输入检测
    if (simple.val().length == 2){
        if (parseInt(simple.val()) !== 10) {
            layer.msg('请输入正确的题目数量', {
                time: 2000, //2s后自动关闭
            });
            return;
        }
    } else if (simple.val().length >= 3){
        layer.msg('请输入正确的题目数量', {
            time: 2000, //2s后自动关闭
        });
        return;
    }
    if (commonly.val().length == 2){
        if (parseInt(commonly.val()) !== 10) {
            layer.msg('请输入正确的题目数量', {
                time: 2000, //2s后自动关闭
            });
            return;
        }
    } else if (commonly.val().length >= 3){
        layer.msg('请输入正确的题目数量', {
            time: 2000, //2s后自动关闭
        });
        return;
    }
    if (difficulty.val().length == 2){
        if (parseInt(difficulty.val()) !== 10) {
            layer.msg('请输入正确的题目数量', {
                time: 2000, //2s后自动关闭
            });
            return;
        }
    } else if (difficulty.val().length >= 3){
        layer.msg('请输入正确的题目数量', {
            time: 2000, //2s后自动关闭
        });
        return;
    }
    if (parseInt(simple.val()) + parseInt(commonly.val()) + parseInt(difficulty.val()) === 10){
        $.post("http://192.168.43.105:8080/addPaperByRandom",
            {title: $("#title").val(),lessonId: $("#all_lesson_aside").val(),level1Count: $("#simple").val(),level2Count: $("#commonly").val(),level3Count:$("#difficulty").val()},
            function (response) {
                if (response.msg){
                    // 清除表单中的数据
                    $("#title").val("");
                    $("#simple").val("");
                    $("#commonly").val("");
                    $("#difficulty").val("");
                    // title.val("");
                    // optionA.val("");
                    // optionB.val("");
                    // optionC.val("");
                    // optionD.val("");
                    // // 添加课程成功 提示信息
                    layer.confirm('出卷成功！是否继续出卷？', {
                        btn: ['继续出卷','查看试卷'] //按钮
                    }, function(){
                        $(location).attr("href","paper_add.html");
                    }, function(){
                        $(location).attr("href","paper_show.html");
                    });
                } else {
                    layer.msg('添加失败！', {icon: 2},{
                        time: 3000, //3s后自动关闭
                    });
                }
        });
    } else {
        layer.msg('请输入正确的题目数量', {
            time: 2000, //2s后自动关闭
        });
        return;
    }
});
$(".close_sys_add").on("click",function () {
    layer.closeAll();
})