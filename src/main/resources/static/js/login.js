var classes ;
function login(){
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/login",
        data:{
            account:$("#loginName").val(),
            password:$("#Possword").val()
        },
        dataType:"json",
        success:function(data){    //data为后台返回的数据
            if(data.code=="200"){
                alert("登录成功");
                window.location.href="index.html";
            }else{
                alert(data.extend.result);
            }
        },
        error:function(jqXHR){
            alert("发生错误"+jqXHR.status);
        }
    })
}

function IsNull(){
    var str = document.getElementById('loginName').value.trim();
    if(str.length==0){
        document.getElementById("msg5") .innerHTML="用户名或密码不能为空!";
    }
    else {
        document.getElementById("msg5").innerHTML="";
    }
}


function phone(){
    var str = document.getElementById('exampleInputPassword3').value.trim();
    if(!str.match(/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/)){
        document.getElementById("msg2").innerHTML="请输入正确的手机号";
    }
    else {
        document.getElementById("msg2").innerHTML="";
    }
}

function key(){
    var str = document.getElementById('exampleInputPassword4').value.trim();
    if(str.length<2 || str.length>16){
        //alert('请输入正确的密码');//请将“文本框”改成你需要验证的属性名称!
        document.getElementById("msg").innerHTML="请输入正确的密码";
    }
    else {
        document.getElementById("msg").innerHTML="";
    }
}

function key3(){
    var str = document.getElementById('exampleInputPassword1').value.trim();
    if(str.length<2 || str.length>16){
        //alert('请输入正确的密码');//请将“文本框”改成你需要验证的属性名称!
        document.getElementById("msg3").innerHTML="请输入正确的用户名";
    }
    else {
        document.getElementById("msg3").innerHTML="";
    }
}

function key4(){
    var str = document.getElementById('exampleInputPassword2').value.trim();
    if(str.length<2 || str.length>16){
        //alert('请输入正确的密码');//请将“文本框”改成你需要验证的属性名称!
        document.getElementById("msg4").innerHTML="请输入正确的姓名";
    }
    else {
        document.getElementById("msg4").innerHTML="";
    }
}
function register(){
    getClasses();
    $(".classOption").remove();
    $.each(classes,function(index,item){
        var op = $("<option></option>").addClass("classOption").attr("value",item.classId).append(item.className);
        $("#classSelect").append(op);
    });
}

function getClasses(){
    if (classes==null){
        $.ajax({
            type:"GET",
            url:"http://localhost:8080/getAllClass",
            async:false,
            dataType:"json",
            success:function(data){    //data为后台返回的数据
                if(data.code=="200"){
                    classes = data.extend.result;
                }
            },
            error:function(jqXHR){
                alert("发生错误"+jqXHR.status);
            }
        })
    }
}

function send() {
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/register",
        data:{
            account  : $('#exampleInputPassword1').val(),
            realName : $('#exampleInputPassword2').val(),
            mobile: $('#exampleInputPassword3').val(),
            password: $('#exampleInputPassword4').val(),
            classId: $('#classSelect').val()
        },
        dataType:"json",
        success:function(data){    //data为后台返回的数据
            if(data.code=="200"){
                alert("注册成功");
                $('#myModal').modal('hide')
            }
            else{
                alert(data.extend.result);
            }
        },
        error:function(jqXHR){
            alert("发生错误"+jqXHR.status);
        }
    })

}
