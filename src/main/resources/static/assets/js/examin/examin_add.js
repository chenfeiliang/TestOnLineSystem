// 批量加入点击事件
$("#add_all").on("click",function () {
    var accounts = $(this).data("ids");
    $.get(base_url + "getUserByAccounts?accounts=" + accounts,function (response) {
        if (response.msg){
            $(".init_tr").empty();
            add_lists(response.extend.result);
        }
    });
});

function add_lists(userInfo) {
    var count = 0;
    var accountArray = [];
    var realNameArray = [];
    var mobileArray = [];
    var imageArray = [];
    // questionHtmlRes 字符串 用来动态生成题目信息表格<tr>
    var usersHtmlRes = "";
    $.each(userInfo,function (i,item) {
        count++;
        accountArray.push(item["account"]);
        realNameArray.push(item["realName"]);
        mobileArray.push(item["mobile"]);
        imageArray.push(item["image"]);
    });
    if (count>0){
        for (var i = 0;i<count;i++){
            // 生成题目信息表格HTML代码
            usersHtmlRes += "<tr><td class='text-center'><input type='checkbox' data-id='"+accountArray[i]+"'></td>"+
                "<td class='text-center'><img class='avatar' src='"+imageArray[i]+"'></td>"+
                "<td class='text-center'>"+accountArray[i]+"</td>"+
                "<td class='text-center'>"+realNameArray[i]+"</td>"+
                "<td class='text-center'>"+mobileArray[i]+"</td>"+
                "<td class='text-center'><a href='javascript:void(0);' class='btn btn-info btn-xs del_paper_list' data-id='"+accountArray[i]+"'>移出列表</a></td></tr>";
        }
        usersHtmlRes += "<script src='../assets/js/paper/add_list.js'></script>";
        $("#list_body").append(usersHtmlRes);
    }
}


// 清空列表点击事件
$("#emptyMyList").on("click",function () {
    emptyMyList();
});
// 生成试卷点击事件
$("#create_examin").on("click",function () {
    var list_body = $("#list_body input");
    var title = $("#examin_title");
    var paperId = $("#use_paper");
    var lessonId = $("#use_lesson");
    var startTime = $("#startTime");
    var endTime = $("#endTime");
    var type = $("#type");
    // 表单数据监测
    if (title.val().length == 0){
        layer.msg('请输入考场名称!');
        return;
    } else if (list_body.size() == 0){
        layer.msg('请至少选择一个用户');
        return;
    }s
    // 存放待添加的ids
    var idsArray = [];
    for (var i = 0;i<list_body.size();i++){
        idsArray.push(list_body.eq(i).data("id"));
    }
    $.post(base_url + "addExam",{title:title.val(),paperId:paperId.val(),lessonId:lessonId.val(),accounts:idsArray.toString(),bTime:startTime.val().toString(),eTime:endTime.val().toString(),type:type.val()},function (response) {
        if (response.msg == "true"){
            // 清除表单中的数据
            title.val("");
            emptyMyList();
            // 添加课程成功 提示信息
            // 提示信息
            layer.confirm('考场开设成功！',{
                btn: ['继续开设','查看考场'] //按钮
            }, function(){
                $(location).attr("href","examin_add.html");
            }, function(){
                $(location).attr("href","examin_show.html");
            });
        } else {
            // 添加课程失败 提示信息
            layer.msg('考场设置失败,意料之外的情况！', {icon: 2},{
                time: 3000, //3s后自动关闭
            });
        }
    });
});

function emptyMyList() {
    $("#list_body tr:gt(0)").empty();
    $("#list_body").html("<tr class='init_tr'><td colspan='6' class='text-center'>~暂无待添加项~</td></tr>");
}