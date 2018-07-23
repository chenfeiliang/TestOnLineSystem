
var id ;

var  questions;

var  totalTime;

var  paperIdTotal ;

var questionIdTotal;

var subFlag = false;

$(function(){
    id = getUrlParam('id');
    getPaper();
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
        url: "http://localhost:8080/getWrongByExamId",
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
/*                alert(data.extend.errorInfo);*/
                window.location.href="index.html";
            }

        },
        error:function(){
           /* alert("fail");*/
        }
    });
}


function WriteQuestion(){

    $.each(questions,function(index,item){

        if(item.isWrong=="1"){

            var qtitle = $("<div></div>").append((index+1)+". "+item.title).attr("class","title").attr("id","title"+(index+1));

            var inputA = $("<input />").addClass("opCheckBox").attr("data","a").attr("name","option"+(index+1)).attr("type","radio").css("background","red");
            var qliA = 	$("<li></li>").addClass("optionA option").append(item.optionA)

            var inputB = $("<input />").addClass("opCheckBox").attr("data","b").attr("name","option"+(index+1)).attr("type","radio");
            var qliB = 	$("<li></li>").addClass("optionB option").append(item.optionB)

            var inputC = $("<input />").addClass("opCheckBox").attr("data","c").attr("name","option"+(index+1)).attr("type","radio");
            var qliC = 	$("<li></li>").addClass("optionC option").append(item.optionC)

            var inputD = $("<input />").addClass("opCheckBox").attr("data","d").attr("name","option"+(index+1)).attr("type","radio");
            var qliD = 	$("<li></li>").addClass("optionD option").append(item.optionD)


            var rkey = item.questionKey;

            switch(rkey){
                case "a":
                    qliA.css("background","#9FE9E9");
                    break;
                case "b":
                    qliB.css("background","#9FE9E9");
                    break;
                case "c":
                    qliC.css("background","#9FE9E9");
                    break;
                case "d":
                    qliD.css("background","#9FE9E9");
                    break;
            }

            var wkey = item.wrongKey;

            switch(wkey){
                case "a":
                    inputA.attr("checked","true");
                    qliA.css("background","red");
                    break;
                case "b":
                     inputB.attr("checked","true");
                    qliB.css("background","red");
                    break;
                case "c":
                    inputC.attr("checked","true");
                    qliC.css("background","red");
                    break;
                case "d":
                    inputD.attr("checked","true");
                    qliD.css("background","red");
                    break;
            }

            qliA.append(inputA);
            qliB.append(inputB);
            qliC.append(inputC);
            qliD.append(inputD);

            var qol = $("<ol></ol>").addClass("optionOl").attr("type","A").attr("id","optionOl"+(index+1)).append(qliA).append(qliB).append(qliC).append(qliD);
            $("#titleDiv").append(qtitle).append(qol);
        }



    });

    $(".title").hide();
    $(".optionOl").hide();

    showQuestion(1);

    for(var i = 1 ; i<=questions.length;i++){

        if(questions[i-1].isWrong=="1"){
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



