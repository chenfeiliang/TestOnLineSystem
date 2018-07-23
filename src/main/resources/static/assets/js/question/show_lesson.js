$.ajax({
    url: base_url + "getAllLesson",
    type: "GET",
    dataType:"json",
    success: function (response) {
        if (response.msg){
            // 将所有课程信息显示在下拉列表中
            show_lesson_info(response.extend.result);
        }
    },
    error: function(a, b) {
        layer.msg('系统错误',{
            time: 3000, //3s后自动关闭
        });
    }
});

function show_lesson_info(lesson_info) {
    // lessonHtmlRes 字符串 用来动态生成下拉列表
    var lessonHtmlRes = "";
    // count 课程数量
    var count = 0;
    // lessonIdArray 存放所有课程的id
    var lessonIdArray = [];
    // lessonNameArray 存放所有课程的名称
    var lessonNameArray = [];
    $.each(lesson_info,function (i,item) {
        count++;
        lessonIdArray.push(item["lessonId"]);
        lessonNameArray.push(item["lessonName"]);
    });
    // ======生成下拉框课程信息======
    if (count > 0){
        for (var i=0;i<count;i++){
            // 生成下拉框课程信息
            lessonHtmlRes += "<option value='"+lessonIdArray[i]+"'>"+lessonNameArray[i]+"</option>";
        }
        $("#lessonId").html(lessonHtmlRes);
    } else {
        $("#lessonId").html("<option>~暂无课程信息~</option>");
    }
}