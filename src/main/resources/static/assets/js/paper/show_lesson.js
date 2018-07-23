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

/**
 * 生成下拉列表的方法
 * @param lesson_info 课程信息
 */
function show_lesson_info(lesson_info) {
    // lessonHtmlRes 字符串 用来动态生成下拉列表
    var lessonHtmlRes = "";
    var listLessonHtmlRes = "";
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
        lessonHtmlRes += "<option value='0'>全部课程</option>";
        for (var i=0;i<count;i++){
            // 生成下拉框课程信息
            lessonHtmlRes += "<option value='"+lessonIdArray[i]+"'>"+lessonNameArray[i]+"</option>";
            listLessonHtmlRes += "<option value='"+lessonIdArray[i]+"'>"+lessonNameArray[i]+"</option>";
        }
        $("#all_lesson").html(lessonHtmlRes);
        $("#show_all_lesson").html(listLessonHtmlRes);
        $("#use_lesson").html(listLessonHtmlRes);
    } else {
        $("#all_lesson").html("<option>~暂无课程信息~</option>");
        $("#show_all_lesson").html("<option>~暂无课程信息~</option>");
        $("#use_lesson").html("<option>~暂无课程信息~</option>");
    }
}