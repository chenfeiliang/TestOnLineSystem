$(".page").on("click",function () {
    var currentPage = $(this).data("cp");
    select_questions(currentPage);
});
// 上一页
$(".pre").on("click",function () {
    var prePage = $(this).data("pre");
    select_questions(prePage);
});
// 下一页
$(".next").on("click",function () {
    var nextPage = $(this).data("next");
    select_questions(nextPage);
})