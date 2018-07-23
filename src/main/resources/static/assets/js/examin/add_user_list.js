$(".add_user_list").on("click",function () {
    var userAccount = $(this).data("id");
    $.ajax({
        url: base_url + "getUserByAccount?account=" + userAccount,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                $(".init_tr").empty();
                add_list(response.extend.result);
            }
        },
        error: function(a, b) {
            layer.msg('系统错误');
        }
    });
});

function add_list(userInfo) {
    // questionIdArray 存放所有题目的id
    var account = userInfo["account"];
    // questionTitleArray 存放当前页所有题目描述
    var realName = userInfo["realName"];
    var mobile = userInfo["mobile"];
    var image = userInfo["image"];
    // questionHtmlRes 字符串 用来动态生成题目信息表格<tr>
    var userHtmlRes = "";
    // 生成题目信息表格HTML代码
    userHtmlRes += "<tr><td class='text-center'><input type='checkbox' data-id='"+account+"'></td>"+
        "<td class='text-center'><img class='avatar' src='"+image+"'></td>"+
        "<td class='text-center'>"+account+"</td>"+
        "<td class='text-center'>"+realName+"</td>"+
        "<td class='text-center'>"+mobile+"</td>"+
        "<td class='text-center'><a href='javascript:void(0);' class='btn btn-info btn-xs del_paper_list' data-id='"+account+"'>移出列表</a></td></tr>"+
        "<script src='../assets/js/paper/add_list.js'></script>";
    $("#list_body").append(userHtmlRes);
}