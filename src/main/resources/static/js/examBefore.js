
var id ;
var totalTime ;
$(function(){
	 id = getUrlParam('id');
	 $("#beginExamA").attr("href","exam.html?id="+id);
	 getTimeAndTitle();
	 if(totalTime==0){
	 	$("#daySpan").append(0);
		$("#hourSpan").append(0);
		$("#minuteSpan").append(0);
		$("#secondSpan").append(0);
	 }
	 else{
	 		 setInterval(function(){timeChange()},1000)
	 }
});

function beginExam(){
	if(totalTime>0){
		alert("考试尚未开始，请耐心等待");
	}else{
		window.location.href="exam.html?id="+id;
	}
}

function getUrlParam(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r!=null) return unescape(r[2]); return null; //返回参数值
} 

function getTimeAndTitle()
{
		$.ajax({
			url: "http://localhost:8080/getExamTitleAndTitleByExamId",
			type:"GET",
			async:false, 
			data:{examinId:id},
			dataType:"json",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",//防止乱码
			success:function(data){			
				$("#titleSpan").append(data.extend.result.title);
				totalTime = data.extend.result.time;		
			},
			error:function(){
			   alert("fail");
			 }
	});	
}

function getTimes(time){
	
	$("#daySpan").text('');
	$("#hourSpan").text('');
	$("#minuteSpan").text('');
	$("#secondSpan").text('');
	
	var day = parseInt(time/86400);

	
	var temp1 = time%86400;
	var hour = parseInt(temp1/3600);

	
	var temp2 = temp1%3600;
	var minute = parseInt(temp2/60);
	
	
	var temp3 = temp2%60;
	var s = parseInt(temp3);

	$("#daySpan").append(day);
	$("#hourSpan").append(hour);
	$("#minuteSpan").append(minute);
	$("#secondSpan").append(s);
}

function timeChange(){
	totalTime = totalTime-1;
	if(totalTime<=0){
		totalTime = 0 ;
	}
	getTimes(totalTime);
}
