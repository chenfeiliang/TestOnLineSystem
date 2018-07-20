var kw = $("#keyword").val();
$(".page").on("click",function () {
    var currentPage = $(this).data("cp");
    select_question_byKeyWord(kw,currentPage);
});
// 上一页
$(".pre").on("click",function () {
    var prePage = $(this).data("pre");
    select_question_byKeyWord(kw,prePage);
});
// 下一页
$(".next").on("click",function () {
    var nextPage = $(this).data("next");
    select_question_byKeyWord(kw,nextPage);
});