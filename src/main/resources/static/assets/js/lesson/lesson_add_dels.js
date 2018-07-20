// 添加按钮点击事件
$("#add_lesson").on("click",function () {
    // alert("添加按钮点击事件ok");
    // alert($("#add_class").text());
    var text = $(this).text();
    text.substr(text.lastIndexOf("加") + 1) === "新的" ? $(location).attr("href","lesson_manage.html") : add_lesson();
});
// 保存提交按钮点击事件
$("#update_lesson").on("click",function () {
    // 获取当前页
    var cp = $("thead input").data("cp");
    up_lesson(cp);
})
// 批量删除按钮点击事件
$("#del_all").on("click",function () {
    // alert("批量删除点击按钮ok");
    // 获取id数组
    var lessonIds = $(this).data("ids");
    // 获取当前页
    var cp = $("thead input").data("cp");
    del_lesson("batchDeleteLessonById?lessonIds=" + lessonIds,cp);
})
