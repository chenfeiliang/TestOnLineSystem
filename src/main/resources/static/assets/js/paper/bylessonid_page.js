var paper_lessonId = $("#all_lesson").val();
$(".page").on("click",function () {
    var currentPage = $(this).data("cp");
    select_questions_byLessonId(currentPage,paper_lessonId);
});
// 上一页
$(".pre").on("click",function () {
    var prePage = $(this).data("pre");
    select_questions_byLessonId(prePage,paper_lessonId);
});
// 下一页
$(".next").on("click",function () {
    var nextPage = $(this).data("next");
    select_questions_byLessonId(nextPage,paper_lessonId);
});