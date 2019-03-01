/*对user属性的相关操作*/

/*
 * 单击返回上一界面
 */
function goback(){
    if(!getCookie("userId")){
        window.location.href="log_in.html";
    }else{
        window.history.back(-1);
    }
}

/*
 * 回车键调用修改密码
 */
function enterChangePwd(event){
    if(event.keyCode==13){
        change_pwd();
    }
}

/*
 * 确认修改密码
 */
function change_pwd(){
    //获取参数
    var userId=getCookie("userId");
    var last_password=$("#last_password").val().trim();
    var new_password=$("#new_password").val().trim();
    var final_password=$("#final_password").val().trim();
    if(!userId){
        alert("尚未登录账号");
        window.location.href="log_in.html";
    }else{
        $("#warning_1").hide();
        $("#warning_2").hide();
        $("#warning_3").hide();
        //检查输入信息格式
        var ok=true;
        if(last_password==""){
            $("#warning_1>span").text("请输入原密码");
            $("#warning_1").show();
            ok=false;
        }
        if(new_password==""){
            $("#warning_2>span").text("密码不能为空");
            $("#warning_2").show();
            ok=false;
        }else if(new_password.length<6){
            $("#warning_2>span").text("密码不能小于6位");
            $("#warning_2").show();
            ok=false;
        }
        if(final_password==""){
            $("#warning_3>span").text("请输入确认密码");
            $("#warning_3").show();
            ok=false;
        }else if(new_password!=final_password){
            $("#warning_3>span").text("输入密码不一致");
            $("#warning_3").show();
            ok=false;
        }
        //格式检查通过
        if(ok){
            $.ajax({
                url:path+"/user/changePwd.do",
                data:{"userId":userId,"lastPwd":last_password,"newPwd":new_password},
                type:"post",
                dataType:"json",
                success:function(result){
                    if(result.status==0){  //修改成功
                        alert(result.msg);
                        delCookie("userId");
                        window.location.href="log_in.html";
                    }else if(result.status==1){  //密码错误
                        $("#warning_1>span").text(result.msg);
                        $("#warning_1").show();
                    }
                },
                error:function(){
                    alert("修改密码出错!");
                }
            });
        }
    }
}

/*enter键触发登录功能*/
function enterUserLogin(event) {
    if(event.keyCode==13){
        userLogin();
    }
}

/*enter触发用户注册*/
function enterUserRegist(event) {
    if (event.keyCode == 13) {
        userRegist();
    }
}

/*用户注册*/
function userRegist() {

    //隐藏提示信息
    $("#warning_1").hide();
    $("#warning_2").hide();
    $("#warning_3").hide();
    $("#warning_4").hide();

    //获取参数
    var username=$("#regist_username").val().trim();
    var nickname=$("#nickname").val().trim();
    var password=$("#regist_password").val().trim();
    var final_password=$("#final_password").val().trim();

    //格式检测
    var ok = true;
    if(check_null(username)){
        $("#warning_1 > span").text("用户名不能为空!");
        $("#warning_1").show();
        ok=false;
    }
    if(check_null(nickname)){
        $("#warning_2 > span").text("昵称不能为空!");
        $("#warning_2").show();
        ok=false;
    }
    if(password.length < 6){
        $("#warning_3 > span").text("密码长度不能低于6位!");
        $("#warning_3").show();
        ok=false;
    }
    if(!(password==final_password)){
        $("#warning_4 > span").text("两次密码输入不一致!");
        $("#warning_4").show();
        ok=false;
    }

    //格式验证通过
    if(ok){
        $.ajax({
            url:path+"user/regist.do",
            data:{"username":username,"nickname":nickname,"password":password,"final_password":final_password},
            type:"post",
            dataType:"json",
            success:function(result){
                if(result.status==0){
                    //注册成功
                    alert(result.msg);
                    //返回登录界面
                    $("#back").click();
                }
                if(result.status==1){
                    //已存在用户名,注册失败
                    $("#warning_1>span").text(result.msg);
                    $("#warning_1").show();
                }
            },
            error:function(){
                alert("注册出错,请重新注册!")
            }
        });
    }

}

/*用户登录*/
function userLogin() {
    //清空提示信息
    $("#span_count").text("");
    $("#span_pwd").text("");

    //获取参数
    var name =$("#count").val().trim();
    var password = $("#password").val().trim();

    //用户名/密码非空检查
    var flag = true;
    if(check_null(name)){
        $("#span_count").text("用户名不能为空");
        flag = false;
    }
    if(check_null(password)){
        $("#span_pwd").text("密码不能为空");
        flag = false;
    }

    //非空检查通过,则发送请求
    if(flag){
        $.ajax({
            url:path+"user/login.do",
            data:{"name":name,"password":password},
            type:"post",
            dataType:"json",
            success:function(result){
                if(result.status==0){
                    //返回了正确数据
                    //将userId添加到cookie
                    var userId=result.data.cn_user_id;
                    addCookie("userId",userId,2);
                    window.location.href=path+"edit.html";
                }
                if(result.status==1){
                    //用户名不存在
                    $("#span_count").text(result.msg);
                }
                if(result.status==2){
                    //密码错误
                    $("#span_pwd").text(result.msg);
                }
            },
            error:function(){
                alert("登录出错,请重新登录!");
            }
        });
    }

}