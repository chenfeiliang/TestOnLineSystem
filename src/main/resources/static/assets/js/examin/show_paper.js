$.ajax({
    url: base_url + "getAllPaper",
    type: "GET",
    dataType:"json",
    success: function (response) {
        if (response.msg){
            // 将所有课程信息显示在下拉列表中
            show_paper_info(response.extend.result);
        }
    },
    error: function(a, b) {
        layer.msg('系统错误',{
            time: 3000, //3s后自动关闭
        });
    }
});

function show_paper_info(paper_info) {
    // paperHtmlRes 字符串 用来动态生成下拉列表
    var paperHtmlRes = "";
    // count 课程数量
    var count = 0;
    // paperIdArray 存放所有课程的id
    var paperIdArray = [];
    // paperNameArray 存放所有课程的名称
    var paperNameArray = [];
    $.each(paper_info,function (i,item) {
        count++;
        paperIdArray.push(item["paperId"]);
        paperNameArray.push(item["title"]);
    });
    // ======生成下拉框课程信息======
    if (count > 0){
        for (var i=0;i<count;i++){
            // 生成下拉框课程信息
            paperHtmlRes += "<option value='"+paperIdArray[i]+"'>"+paperNameArray[i]+"</option>";
        }
        $("#use_paper").html(paperHtmlRes);
    } else {
        $("#use_paper").html("<option>~暂无试卷信息~</option>");
    }
}