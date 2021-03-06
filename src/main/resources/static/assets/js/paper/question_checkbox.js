/**
 * 提取公用js
 * 完成单选和全选功能
 */
// 初始化复选框数据数组
var dataArray = [];
// 避免重复获取jQ对象
var add_btn = $("#add_all");
var tbody_input = $("#questions input");
var thead_input = $(".questions");
thead_input.prop("checked",false);

// 每有一个checkbox被选中,将对应用户id存入dataArray数组中
tbody_input.on("change", function () {
    // 根据HTML自定义属性获取对应user的id
    var id = $(this).data("id");
    // 如果被选中则加入数组,取消选中则移出数组
    if ($(this).prop("checked")) {
        dataArray.push(id);
    } else {
        // 第一个参数：移出id对应下标的数组元素
        // 第二个参数：移出一个元素
        dataArray.splice(dataArray.indexOf(id), 1);
        // 如果全选按钮被选中,则取消其选中状态
        if (thead_input.prop("checked")){
            thead_input.prop("checked",false);
        }
    }
    // 动态显示批量删除按钮 根据dataArray数组长度是否为0确定
    (dataArray.length == 0) ? add_btn.fadeOut() : add_btn.fadeIn();
    (dataArray.length == tbody_input.length) ? thead_input.prop("checked",true) : thead_input.prop("checked",false);
    // 动态修改批量删除按钮HTML5属性值便于获取ids组
    add_btn.data("ids", dataArray);
});
// 全选按钮
thead_input.on("change",function () {
    // 获取全选按钮选中状态
    var check = $(this).prop("checked");
    // 清空数组,避免重复添加
    dataArray = [];
    // 动态显示批量删除按钮 根据dataArray数组长度是否为0确定
    (dataArray.length == 0) ? "" : add_btn.fadeIn();
    // 使tbody中的checkbox状态与全选按钮相同,同时触发其change事件,向数组中添加元素
    tbody_input.prop("checked",check).trigger("change");
});