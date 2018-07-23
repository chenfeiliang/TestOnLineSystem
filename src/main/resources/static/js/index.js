$(function(){
	getExam();
});


function getExam(){
	$.ajax({
			url: "http://localhost:8080/getExamByAccount",
			type:"GET",
			dataType:"json",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",//防止乱码
			success:function(data){
			    $("#userSpan").append(data.extend.user);
	            //遍历数据数据
	            $.each(data.extend.result,function(index,item){
	                var tr1 = $("<tr></tr>").attr("align","center").attr("class","tr1 row");  
	                var idTd = $("<td></td>").append(index).addClass("col-md-2");
	                var titleTd= $("<td></td>").append($("<a></a>").append(item.title).attr("href","examBefore.html?id="+item.examinId)).addClass("col-md-2");
	                var timeTd = $("<td></td>>").append(item.beginTime+"&nbsp;&nbsp;--&nbsp;&nbsp;"+item.endTime).addClass("col-md-8");
	                
	                tr1.append(idTd).append(titleTd).append(timeTd);
	                
	                $("#tableBody").append(tr1);
	            });
				
				
				
			},
			error:function(){
			   /*alert("fail");*/
			 }
	});	
}

