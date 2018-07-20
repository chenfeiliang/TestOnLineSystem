// 单个删除按钮点击事件
$(".del_one").on("click",function () {
    // alert("单个删除按钮点击事件ok");
    var id = $(this).data("id");
    var cp = $("thead input").data("cp");
    del_question("deleteQuestion?questionId=" + id,cp);
});
// 编辑班级按钮点击事件
$(".up_question").on("click",function () {
    // alert("编辑班级按钮点击事件ok");
    var questionId = $(this).data("id");
    show_now_question(questionId);
});
// 批量删除按钮点击事件
$("#del_all").on("click",function () {
    // alert("批量删除点击按钮ok");
    // 获取id数组
    var questionIds = $(this).data("ids");
    // 获取当前页
    var cp = $("thead input").data("cp");
    del_question("batchDeleteQuestionById?questionIds=" + questionIds,cp);
});


// 表格中td的点击事件
// 处理字符过长显示问题
function show_title(qTitle) {
    layer.alert(qTitle);
}
function show_A(A) {
    layer.alert(A);
}
function show_B(B) {
    layer.alert(B);
}
function show_C(C) {
    layer.alert(C);
}
function show_D(C) {
    layer.alert(C);
}