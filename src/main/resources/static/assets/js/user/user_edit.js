// 单个删除按钮点击事件
$(".del_one").on("click",function () {
    // alert("单个删除按钮点击事件ok");
    var account = $(this).data("id");
    var cp = $("thead input").data("cp");
    del_user("deleteUser?accounts=" + account,cp);
});
// 编辑班级按钮点击事件
$(".up_user").on("click",function () {
    // alert("编辑班级按钮点击事件ok");
    var account = $(this).data("id");
    show_now_user(account);
});
// 批量删除按钮点击事件
$("#del_all").on("click",function () {
    // alert("批量删除点击按钮ok");
    // 获取id数组
    var userAccounts = $(this).data("ids");
    // 获取当前页
    var cp = $("thead input").data("cp");
    del_user("deleteUser?accounts=" + userAccounts,cp);
});