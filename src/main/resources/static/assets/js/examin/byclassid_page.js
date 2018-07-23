var examin_classId = $("#all_class").val();
$(".page").on("click",function () {
    var currentPage = $(this).data("cp");
    select_user_byClass(examin_classId,currentPage);
});
// 上一页
$(".pre").on("click",function () {
    var prePage = $(this).data("pre");
    select_questions_byLessonId(examin_classId,prePage);
});
// 下一页
$(".next").on("click",function () {
    var nextPage = $(this).data("next");
    select_questions_byLessonId(examin_classId,nextPage);
});