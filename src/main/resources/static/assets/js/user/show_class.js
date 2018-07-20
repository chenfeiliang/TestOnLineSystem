$.ajax({
    url: base_url + "getAllClass",
    type: "GET",
    dataType:"json",
    success: function (response) {
        if (response.msg){
            // 将所有课程信息显示在下拉列表中
            show_class_info(response.extend.result);
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
 * @param class_info
 */
function show_class_info(class_info) {
    // classHtmlRes 字符串 用来动态生成下拉列表
    var classHtmlRes = "";
    // count 题目数量
    var count = 0;
    // classIdArray 存放所有题目的id
    var classIdArray = [];
    // classNameArray 存放所有题目的名称
    var classNameArray = [];
    $.each(class_info,function (i,item) {
        count++;
        classIdArray.push(item["classId"]);
        classNameArray.push(item["className"]);
    });
    // ======生成下拉框课程信息======
    if (count > 0){
        classHtmlRes += "<option value='0'>全部用户</option>";
        for (var i=0;i<count;i++){
            // 生成下拉框课程信息
            classHtmlRes += "<option value='"+classIdArray[i]+"'>"+classNameArray[i]+"</option>";
        }
        $("#all_class").html(classHtmlRes);
    } else {
        $("#all_class").html("<option>~暂无班级信息~</option>");
    }
}