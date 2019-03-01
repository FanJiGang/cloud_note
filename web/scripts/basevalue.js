/**
 * 用于封装公共的属性
 */

//path:项目路径,http://localhost:8080/cloud_note/
var path = "http://localhost:8080/cloud_note/";

//检查对象是否为空/空值/未定义
function check_null(name) {
    if(name==null || name==undefined || name==""){
        return true;
    }else {
        return false;
    }
}

//检查是否有用户登录了
function checkLogined() {
    var userId=getCookie("userId");
    if(!userId){
        alert("当前用户信息已过期,请重新登录");
        window.location.href="log_in.html";
    }
}