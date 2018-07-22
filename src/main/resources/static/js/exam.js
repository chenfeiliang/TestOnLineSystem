
var id ;

var  questions;

var  totalTime;

var  paperIdTotal ;

var questionIdTotal;

var subFlag = false;

$(function(){
	 id = getUrlParam('id');
	 getPaper();
	 setInterval(function(){timeChange()},1000);
});





function show (){
	if($("#cardDiv").css("display")=="block")
	{
		$("#cardDiv").hide();
		$("#showIcon").attr("class","glyphicon glyphicon-menu-up")
				
	}else{
		$("#cardDiv").show();
		$("#showIcon").attr("class","glyphicon glyphicon-menu-down")
	}		
}

function getUrlParam(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r!=null) return unescape(r[2]); return null; //返回参数值
} 

function getPaper()
{
		$.ajax({
			url: "http://localhost:8080/getCardByExamId",
			type:"GET",
			async:false,
			data:{examinId:id},
			dataType:"json",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",//防止乱码
			success:function(data){		
				if(data.code==200){
                    $("#userSpan").append(data.extend.user);
					$("#titleSpan").append(data.extend.result.title);
					questions = data.extend.result.questions;
					totalTime = data.extend.result.time;
					paperIdTotal = data.extend.result.paperId;
					WriteQuestion();					
				}else{
				    alert(data.extend.errorInfo);
					window.location.href="index.html";
				}

			},
			error:function(){
			   alert("fail");
			 }
	});	
}

function timeChange(){
	totalTime = totalTime-1;
	if(totalTime<=0){
		totalTime = 0 ;
		if(!subFlag){
			finish();
			alert("时间到！");
			subFlag = true;			
		}
	}
	getTimes(totalTime);
}

function getTimes(time){
	
	$("#hourSpan").text('');
	$("#minuteSpan").text('');
	$("#secondSpan").text('');
	
	var hour = parseInt(time/3600);
	var temp2 = time%3600;
	var minute = parseInt(temp2/60);
	var temp3 = temp2%60;
	var s = parseInt(temp3);
	
	if(hour<10){
		hour = "0" + hour;
	}
	if(minute<10){
		minute = "0" + minute;
	}
	if(s<10){
		s = "0" + s;
	}
	
	$("#hourSpan").append(hour);
	$("#minuteSpan").append(minute);
	$("#secondSpan").append(s);
}

function WriteQuestion(){

            $.each(questions,function(index,item){
            	
            	var qtitle = $("<div></div>").append((index+1)+". "+item.title).attr("class","title").attr("id","title"+(index+1));
            	
				var inputA = $("<input />").addClass("opCheckBox").attr("data","a").attr("name","option"+(index+1)).attr("type","radio");
				var qliA = 	$("<li></li>").addClass("optionA option").append(item.optionA).append(inputA);
				
				var inputB = $("<input />").addClass("opCheckBox").attr("data","b").attr("name","option"+(index+1)).attr("type","radio");
				var qliB = 	$("<li></li>").addClass("optionB option").append(item.optionB).append(inputB);
				
				var inputC = $("<input />").addClass("opCheckBox").attr("data","c").attr("name","option"+(index+1)).attr("type","radio");
				var qliC = 	$("<li></li>").addClass("optionC option").append(item.optionC).append(inputC);
				
				var inputD = $("<input />").addClass("opCheckBox").attr("data","d").attr("name","option"+(index+1)).attr("type","radio");
				var qliD = 	$("<li></li>").addClass("optionD option").append(item.optionD).append(inputD);
				
				var qol = $("<ol></ol>").addClass("optionOl").attr("type","A").attr("id","optionOl"+(index+1)).append(qliA).append(qliB).append(qliC).append(qliD);
				
               $("#titleDiv").append(qtitle).append(qol);

            });	
            $(".title").hide();
            $(".optionOl").hide();
            
            showQuestion(1);
            
            for(var i = 1 ; i<=questions.length;i++){
	
				var cardSpan = $("<span></span>").append(i);

				var cardLi = $("<li ></li>");/*onclick='cardliClick(this)'*/
				
            	if(i==1){
            		cardLi.addClass("selectedCard");
            	} 	
            	
            	cardLi.attr("id","cardId"+i);
            		
            	cardLi.addClass("cardID").append(cardSpan).attr("onclick","showQuestion("+i+");");
		
            	$("#cardUl").append(cardLi);
            }
}

function showQuestion(qid){
	 questionIdTotal = qid;
	 $(".cardID").removeClass("selectedCard");
	 $("#cardId"+qid).addClass("selectedCard");
	 $(".title").hide();
     $(".optionOl").hide();
     $("#title"+qid).show();
     $("#optionOl"+qid).show();
}

function nextQuestion(){
	var qidtemp = questionIdTotal+1;
	
	if(qidtemp>questions.length){
		qidtemp = questions.length;
	}

	showQuestion(qidtemp);
}

function beforeQuestion(){
	var qidtemp = questionIdTotal-1;
	
	if(qidtemp<1){
		qidtemp = 1;
	}

	showQuestion(qidtemp);	
}

function finish(){
	
	var examId = id;
	var paperId = paperIdTotal;
	
	var options =  "";
	
	for(var i = 1 ; i <= questions.length;i++){
		var flag = false;
		var ops = document.getElementsByName("option"+i);
		for(var j = 0 ;j< ops.length;j++){
			if(ops[j].checked){
				options = options+ops[j].getAttribute("data")+",";
				flag = true;
				break;
			}
		}
		if(!flag){
			options = options+"-1"+",";
		}
	}
	options = options.substring(0,options.length-1);

	$.ajax({
			url: "http://localhost:8080/addCard",
			type:"POST",
			data:{
				examinId:examId,
				paperId:paperId,
				options:options
			},
			dataType:"json",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",//防止乱码
			success:function(data){		
				if(data.code==200){
					alert("试卷提交完成");
					window.location.href="index.html";
				}else{
                    alert(data.extend.errorInfo);
                }
			},
			error:function(){
			   alert("fail");
			 }
	});	
}


