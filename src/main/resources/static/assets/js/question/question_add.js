var title = $("#title");
var optionA = $("#optionA");
var optionB = $("#optionB");
var optionC = $("#optionC");
var optionD = $("#optionD");
$("#add_question").on("click",function () {
    // alert("ok");
    $("#creator").val($.session.get('admin_name'));
    var _url = base_url + "addQuestion";
    // 表单校验
    if (!check_form()){return;}
    $.post(_url,$("#edit_question").serialize(),function (response) {
        if (response.msg){
            // 清除表单中的数据
            title.val("");
            optionA.val("");
            optionB.val("");
            optionC.val("");
            optionD.val("");
            // 添加课程成功 提示信息
            layer.confirm('添加成功！是否继续添加？', {
                btn: ['继续添加','查看题目'] //按钮
            }, function(){
                $(location).attr("href","question_add.html");
            }, function(){
                $(location).attr("href","question_show.html");
            });
        } else {
            layer.msg('添加失败！', {icon: 2},{
                time: 3000, //3s后自动关闭
            });
        }
    });
});

/**
 * 检查表单中题目信息是否为空
 */
function check_form() {
    if (title.val().length == 0){
        show_error("请输入题目描述！");
        return false;
    } else if (optionA.val().length == 0){
        show_error("请输入选项A内容！");
        return false;
    }
    else if (optionB.val().length == 0){
        show_error("请输入选项B内容！");
        return false;
    }
    else if (optionC.val().length == 0){
        show_error("请输入选项C内容！");
        return false;
    }
    else if (optionD.val().length == 0){
        show_error("请输入选项D内容！");
        return false;
    } else {
        return true;
    }
}

/**
 * 显示错误提示
 * @param error
 */
function show_error(error) {
    layer.alert(error, {
        icon: 2,
        skin: 'layer-ext-moon'
    })
}