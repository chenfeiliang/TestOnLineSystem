// 获取当前页
var cp = $("thead input").data("cp");
// 添加按钮点击事件
$("#add_class").on("click",function () {
    // alert("添加按钮点击事件ok");
    // alert($("#add_class").text());
    var text = $(this).text();
    text.substr(text.lastIndexOf("加") + 1) === "新的" ? $(location).attr("href","class_manage.html") : add_class();
});
// 保存提交按钮点击事件
$("#update_class").on("click",function () {
    up_class(cp);
})
// 批量删除按钮点击事件
$("#del_all").on("click",function () {
    // alert("批量删除点击按钮ok");
    // 获取id数组
    var classIds = $(this).data("ids");
    del_class("batchDeleteClassById?classIds=" + classIds,cp);
})
