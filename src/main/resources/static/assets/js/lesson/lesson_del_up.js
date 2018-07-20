// 单个删除按钮点击事件
$(".del_one").on("click",function () {
    // alert("单个删除按钮点击事件ok");
    var id = $(this).data("id");
    var cp = $("thead input").data("cp");
    del_lesson("deleteLesson?lessonId=" + id,cp);
})
// 编辑班级按钮点击事件
$(".up_lesson").on("click",function () {
    // alert("编辑班级按钮点击事件ok");
    var lessonId = $(this).data("id");
    show_now_lesson(lessonId);
})