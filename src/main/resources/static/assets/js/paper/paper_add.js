// 批量加入点击事件
$("#add_all").on("click",function () {
    layer.msg('开发中...');
});
// 清空列表点击事件
$("#emptyMyList").on("click",function () {
    emptyMyList();
});
// 生成试卷点击事件
$("#create_paper").on("click",function () {
    var list_body = $("#list_body input");
    var title = $("#paper_name");
    var question_key = $(".haskey");
    // 表单数据监测
    if (title.val().length == 0){
        layer.msg('请输入试卷名称!');
        return;
    } else if (list_body.size() == 0){
        layer.msg('请至少选择一道试题!');
        return;
    }
    // 存放待添加的ids
    var idsArray = [];
    // 存放答案
    var keyArray = [];
    for (var i = 0;i<list_body.size();i++){
        idsArray.push(list_body.eq(i).data("id"));
    }
    for (var j = 0;j<question_key.size();j++){
        keyArray.push(question_key.eq(j).data("key").toLowerCase());
    }
    $.post(base_url + "addPaperByMan",{title:title.val(),lessonId:$("#show_all_lesson").val(),questionIds:idsArray.toString(),answer:keyArray.toString(),creator:sessionStorage.getItem("adminName")},function (response) {
        if (response.msg == "true"){
            // 清除表单中的数据
            title.val("");
            emptyMyList();
            // 添加课程成功 提示信息
            // 提示信息
            layer.confirm('出卷成功!是否继续出卷',{
                btn: ['继续出卷','查看试卷'] //按钮
            }, function(){
                $(location).attr("href","paper_add.html");
            }, function(){
                $(location).attr("href","paper_show.html");
            });
        } else {
            // 添加课程失败 提示信息
            layer.msg('出卷失败,意料之外的情况！', {icon: 2},{
                time: 3000, //3s后自动关闭
            });
        }
    });
});

function emptyMyList() {
    $("#list_body tr:gt(0)").empty();
    $("#list_body").html("<tr class='init_tr'><td colspan='4' class='text-center'>~暂无待添加项~</td></tr>");
}