$(".page").on("click",function () {
    var currentPage = $(this).data("cp");
    select_user(currentPage);
});
// 上一页
$(".pre").on("click",function () {
    var prePage = $(this).data("pre");
    select_user(prePage);
});
// 下一页
$(".next").on("click",function () {
    var nextPage = $(this).data("next");
    select_user(nextPage);
})