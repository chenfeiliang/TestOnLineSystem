$(".show_users_account").on("click",function () {
   var accounts = $(this).data("id");
   $.ajax({
        url: base_url + "getUserByAccounts?accounts=" + accounts,
        type: "GET",
        dataType:"json",
        success: function (response) {
            if (response.msg){
                show_users_info(response.extend.result);
            }
        },
        error: function(a, b) {
            layer.msg('网络开小差了');
        }
    });
});

/**
 * 显示用户信息的方法
 * @param questionsInfo
 */
function show_users_info(usersInfo) {
    var count = 0;
    var userAccountArray = [];
    var userRealNameArray = [];
    var userMobileArray = [];
    var userClassNameArray = [];
    var usersHtmlRes = "";
    $.each(usersInfo,function (i,item) {
        count++;
        userAccountArray.push(item["account"]);
        userRealNameArray.push(item["realName"]);
        userMobileArray.push(item["mobile"]);
        userClassNameArray.push(item["myClass"]["className"]);
    });
    if (count>0){
        for (var i=0;i<count;i++){
            usersHtmlRes +=  "<br /><p>用户名称："+userAccountArray[i]+"</p>"+
                "<p>真实姓名："+userRealNameArray[i]+"</p>"+
                "<p>联系方式："+userMobileArray[i]+"</p>"+
                "<p>所在班级："+userClassNameArray[i]+"</p><hr />";
        }
        usersHtmlRes += "<div class='modal-footer'><button type='button' class='btn btn-default myClose'>Close</button></div>";
        usersHtmlRes += "<script>$('.myClose').on('click',function() {layer.closeAll();})</script>";
        var info = layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['620px','400px'], //宽高
            anim: 6,//0-6的动画形式，-1不开启
            content: usersHtmlRes
        });
    } else {
        layer.msg('系统错误', {
            time: 2000, //2s后自动关闭
        });
    }
}