// 获取用户名参数
var accountByUrl = getUrlParam("userAccount");

// 获取jQ对象,避免重复查询
var jq_image = $(".avatar");
var jq_account = $("#account");
var jq_realName = $("#realName");
var jq_mobile = $("#mobile");

// http://192.168.43.239:8080/getUserByAccount?account=jiao1001
// 接口地址
var _url = base_url + "getUserByAccount?account=" + accountByUrl;
// ajax请求
$.get(_url,function (response) {
    if (response.msg){
        var account = response.extend.result.account;
        $("#page_title").html("修改用户《" + account + "》的信息");
        jq_image.prop("src",response.extend.result.image);
        jq_account.val(account);
        jq_realName.val(response.extend.result.realName);
        jq_mobile.val(response.extend.result.mobile);
    } else {
        layer.msg('系统错误', {
            time: 2000, //2s后自动关闭
        });
    }
});

$(".update_user").on("click",function () {
    $.post(base_url+"updateUser",$(".form-horizontal").serialize(),function (response) {
            if (response.msg){
                // 更新成功
                // 提示信息
                layer.confirm('当前更新已保存!是否继续编辑该用户', {
                    btn: ['继续编辑','查看用户'] //按钮
                }, function(){
                    $(location).attr("href","user_up.html?userAccount="+accountByUrl);
                }, function(){
                    $(location).attr("href","users.html");
                });
            } else {
                // 更新失败 提示信息
                layer.msg('更新失败！', {icon: 2},{
                    time: 3000, //3s后自动关闭
                });
            }
        });
});
