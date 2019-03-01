//点赞
function addUp() {
    var userId=getCookie("userId");
    var $li=$(this).parent().parent();
    var id=$li.data("activityNoteId");
    var $but = $(this);
    changeUpOrDown(id,1,0,$but);
}
//点踩
function addDown() {
    var userId=getCookie("userId");
    var $li=$(this).parent().parent();
    var id=$li.data("activityNoteId");
    var $but = $(this);
    changeUpOrDown(id,0,1,$but);
}
function changeUpOrDown(id,up,down,$but) {
    $.ajax({
        url:path+"noteActivity/upOrDown.do",
        data:{"id":id,"up":up,"down":down},
        type:"post",
        dataType:"json",
        success:function(result){
           if(result.status==0){
               var num=parseInt($but.prop("title"))+1;
               $but.prop("title",num);
               alert(result.msg);
           }else{
               alert("请稍后再试!");
           }
        },
        error:function(){
            alert("出错了");
        }
    });
}


//参与活动
function joinActivity() {
    var userId=getCookie("userId");
    var noteId= $("#select_note a.checked").parent().data("noteId");
    var activity_id=get_activity_id();
    var title= $("#select_note a.checked").text();
    if(!userId){
        //判断是否有有效的用户
        alert("登录账户失效,请重新登录!");
        window.location.href="log_in.html";
    }else if(!noteId){
        alert("请选择有效的笔记");
    }else {
        //更改笔记状态
        $.ajax({
            url:path+"note/updateStatus.do",
            data:{"activityId":activity_id,"noteId":noteId,"noteStatus":4},
            type:"post",
            dataType:"json",
            success:function(result){
                if(result.status==0){
                    closeActivityNote();
                    alert("参与活动成功");
                }else {
                    alert("参与活动失败");
                }
            },
            error:function(){
                alert("参与活动出错了...");
            }
        });
    }
}

//选中笔记
function selNote() {
    $("#select_note a").removeClass("checked");
    $(this).find("a").addClass("checked");
}

//显示相应笔记
function loadNotes() {
    //设置选中效果
    $("#select_notebook a").removeClass("checked");
    var $sel=$(this).find("a");
    $sel.addClass("checked");
    //获取参数
    var bookId=$(this).data("bookId");
    if(!bookId){
        alert("无效的笔记本");
    }else{
        $("#select_note .contacts-list").empty();
        //发送ajax请求
        $.ajax({
            "url":path+"note/loadnote.do",
            "data":"bookId="+bookId,
            "type":"post",
            "dataType":"json",
            "success":function(result){
                if(result.status==1){//当前笔记本中没有笔记
                }else if(result.status==0){  //查询到了相应的笔记
                    var notes=result.data;
                    var len=notes.length;
                    for(var i=0;i<len;i++){
                        var noteTitle=notes[i].cn_note_title;
                        var noteId=notes[i].cn_note_id;
                        createJoinNoteLi(noteTitle,noteId);
                    }
                }
            },
            "error":function(){
                alert("查询笔记出错!");
            }
        });
    }
}

//弹出用户参与活动窗口
function alertActivityNote() {
    $("#modalBasic_15").show();
    //显示背景
    $(".opacity_bg").show();
    //加载用户笔记本
    var userId=getCookie("userId");
    $.ajax({ //发送ajax请求
        "url":path+"book/loadBooks.do",
        "data":"userId="+userId,
        "type":"post",
        "dataType":"json",
        "success":function(result){
            if(result.status==1){  //尚未创建笔记本
                alert(result.msg);
            }else if(result.status==0){
                for(var i=0,len=result.data.length;i<len;i++){
                    //获取笔记本ID
                    var bookId=result.data[i].cn_notebook_id;
                    //获取笔记本名称
                    var bookName=result.data[i].cn_notebook_name;
                    //创建一个笔记本列表的li元素
                    createJoinBookLi(bookId,bookName);
                }
            }
        },
        "error":function(){
            alert("获取笔记本信息异常!");
        }
    });
}

//关闭用户参与活动窗口
function closeActivityNote() {
    $("#modalBasic_15").hide();
    $(".opacity_bg").hide();
    $("#select_notebook .contacts-list").html("");
    $("#select_note .contacts-list").html("");

}

//点击收藏笔记窗口的按钮1
function likeNote() {
    var noteTitle=$("#content_body h4").text().replace(/笔记标题: /,"");
    var noteBody=$("#content_body").text().replace(new RegExp("笔记标题: "+noteTitle),"");
    sendLikeNote(noteTitle,noteBody);
}

//弹出收藏笔记窗口
function alertLikeNote() {
    var loginId=getCookie("userId");
    var $li=$(this).parent().parent();
    var activityNoteId=$li.data("activityNoteId");
    if(!activityNoteId){
        alert("当前笔记不存在!");
    }else {
        //弹出收藏笔记框
        $("#can").load("alert/alert_like.html",encoding='utf-8');
        $("#modalBasic_5").show();
        //显示背景
        $(".opacity_bg").show();
    }
}

//显示笔记内容
function showNoteContent() {
    var activityNoteId=$(this).data("activityNoteId");
    if(!activityNoteId){
        alert("当前笔记不存在!");
    }else {
        $("#first_action a").removeClass("checked");
        $(this).find("a").addClass("checked");
        $("#content_body").html("<h4><strong>笔记标题: </strong></h4>");
        $.ajax({
            url:path+"noteActivity/showNoteContent.do",
            data:{"activityNoteId":activityNoteId},
            type:"post",
            dataType:"json",
            success:function(result){
                if(result.status==0){
                    $("#content_body").html("<h4><strong>笔记标题: </strong>"+result.data.cn_note_activity_title+"</h4>"+result.data.cn_note_activity_body);
                }else if(result.status==1){
                    alert(result.msg);
                }
            },
            error:function(){
                alert("查询笔记内容出错了!")
            }
        });
    }

}

//点击更多,显示下一页查询结果
function moreActivityNotes() {
    //获取参数
    var activity_id=get_activity_id();
    activity_page++;
    sendRequest(activity_id,activity_page)
}

//加载活动中的笔记
function loadActivityNotes() {
    var activity_id=get_activity_id();
    activity_page=1;
    if(!activity_id){
        alert("活动不存在");
    }else {
        $("#first_action .contacts-list").html("");
        sendRequest(activity_id,activity_page);
    }
}
//
function sendRequest(activity_id, activity_page) {
    $.ajax({
        url:path+"noteActivity/loadActivityNotes.do",
        data:{"activity_id":activity_id,"activity_page":activity_page},
        type:"post",
        dataType:"json",
        success:function(result){
            if(result.status==0){
                var list=result.data;
                for(var i=0,len=list.length;i<len;i++){
                    var noteId=list[i].cn_note_id;
                    var activityNoteId=list[i].cn_note_activity_id;
                    var activityTitle=list[i].cn_note_activity_title;
                    var activityUp=list[i].cn_note_activity_up;
                    var activityDown=list[i].cn_note_activity_down;
                    createActivityNoteLi(noteId,activityNoteId,activityTitle,activityUp,activityDown);
                }
            }else if (result.status==1){
                alert(result.msg);
            }
        },
        error:function(){
            alert("查询活动笔记出错!");
        }
    });
}


//创建活动笔记列表
function createActivityNoteLi(noteId,activityNoteId,activityTitle,activityUp,activityDown) {
    var $li = $('<li class="online">' +
            '<a>' +
                '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">' +
                '</i>' + activityTitle +
                '<button type="button" class="btn btn-default btn-xs btn_position_3 btn_up" title="'+activityUp+'">' +
                    '<i class="fa fa-thumbs-o-up"></i>' +
                '</button>' +
                '<button type="button" class="btn btn-default btn-xs btn_position_2 btn_down" title="'+activityUp+'">' +
                    '<i class="fa fa-thumbs-o-down"></i>' +
                '</button>' +
                '<button type="button" class="btn btn-default btn-xs btn_position btn_like" title="收藏">' +
                    '<i class="fa fa-star-o"></i>' +
                '</button>' +
            '</a>' +
        '</li>');
    $li.data("activityNoteId", activityNoteId);
    $li.data("noteId",noteId);
    $("#first_action .contacts-list").append($li);

}


//获取URL传值
function get_activity_id(){
    var url=window.location.hash; //获取锚点 #
    url=url.replace(/#/,'');
    return url;
}

/*
 * 创建笔记本li元素
 */
function createJoinBookLi(bookId,bookName){
    var $li=$('<li class="online">'+
        '<a>'+
        '<i class="fa fa-book" title="online" rel="tooltip-bottom">'+
        '</i>'+bookName+
        '</a>'+
        '</li>');
    //将bookId的值与jquery对象绑定
    $li.data("bookId",bookId);
    //将li元素添加到笔记本ul列表区
    $("#select_notebook .contacts-list").append($li);
}

/*
 * 创建笔记li元素
 */
function createJoinNoteLi(noteTitle,noteId){
    var $li=$('<li class="online">'+
        '<a>'+
        '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">'+
        '</i>'+noteTitle+
        '</a>'+
        '</li>');
    $li.data("noteId",noteId);
    $("#select_note .contacts-list").append($li);
}