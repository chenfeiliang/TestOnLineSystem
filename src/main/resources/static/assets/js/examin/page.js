$(".page").on("click",function () {
    var currentPage = $(this).data("cp");
    select_examin(currentPage);
});
// 上一页
$(".pre").on("click",function () {
    var prePage = $(this).data("pre");
    select_examin(prePage);
});
// 下一页
$(".next").on("click",function () {
    var nextPage = $(this).data("next");
    select_examin(nextPage);
})