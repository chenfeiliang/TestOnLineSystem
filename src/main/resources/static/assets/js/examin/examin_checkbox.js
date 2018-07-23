// 初始化复选框数据数组
var questionIdsArray = [];
// 避免重复获取jQ对象
var empty_btn = $("#add_all");
var list_head_input = $("#user_list_head input");
var list_body_input = $("#user_list_body input");
// 初始全选
list_head_input.prop("checked",false);
// 每有一个checkbox被选中,将对应questionId存入dataArray数组中
list_body_input.on("change", function () {
    // 根据HTML自定义属性获取对应question的id
    var id = $(this).data("id");
    // 如果被选中则加入数组,取消选中则移出数组
    if ($(this).prop("checked")) {
        questionIdsArray.push(id);
    } else {
        // 第一个参数：移出id对应下标的数组元素
        // 第二个参数：移出一个元素
        questionIdsArray.splice(questionIdsArray.indexOf(id), 1);
        // 如果全选按钮被选中,则取消其选中状态
        if (list_head_input.prop("checked")){
            list_head_input.prop("checked",false);
        }
    }
    // 动态显示批量删除按钮 根据dataArray数组长度是否为0确定
    (questionIdsArray.length == 0) ? empty_btn.fadeOut() : empty_btn.fadeIn();
    (questionIdsArray.length == list_body_input.length) ? list_head_input.prop("checked",true) : list_head_input.prop("checked",false);
    // 动态修改批量删除按钮HTML5属性值便于获取ids组
    empty_btn.data("ids", questionIdsArray);
});
// 全选按钮
list_head_input.on("change",function () {
    // 获取全选按钮选中状态
    var check = $(this).prop("checked");
    // 清空数组,避免重复添加
    questionIdsArray = [];
    // 动态显示批量删除按钮 根据dataArray数组长度是否为0确定
    (questionIdsArray.length == 0) ? "" : empty_btn.fadeIn();
    // 使tbody中的checkbox状态与全选按钮相同,同时触发其change事件,向数组中添加元素
    list_body_input.prop("checked",check).trigger("change");
});