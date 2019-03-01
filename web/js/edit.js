//点击收藏笔记窗口的按钮1
function likeEditNote1() {
    var noteTitle=$("#input_note_title").val();
    var noteBody=um.getContent();
    sendLikeNote(noteTitle,noteBody);
}

//点击收藏笔记窗口的按钮2
function sendLikeNote(noteTitle,noteBody) {
    var userId=getCookie("userId");
    if(userId ){
        $.ajax({
            url:path+"note/addLikeNote.do",
            data:{"userId":userId,"noteTitle":noteTitle,"noteBody":noteBody,"status":3},
            type:"post",
            dataType:"json",
            success:function(result){
                alertCloseBookWindow();
                alert(result.msg);
            },
            error:function(){
                alert("收藏失败!")
            }
        });
    }
}


//弹出收藏笔记窗口
function alertEditLikeNote() {
    var $li=$(this).parent().parent();
    var shareId=$li.data("shareId");
    if(!shareId){
        alert("当前笔记不存在!");
    }else {
        //弹出收藏笔记框
        $("#can").load("alert/alert_like.html",encoding='utf-8');
        $("#modalBasic_5").show();
        //显示背景
        $(".opacity_bg").show();
    }
}

//点击已收藏笔记显示笔记内容
function loadLikeNoteBody() {
    var noteId=$(this).data("noteId");
    if(!noteId){
        alert("无效的笔记");
    }else{
        //用于设置选中效果
        $("#pc_part_7 a").removeClass("checked");
        var $a=$(this).find("a");
        $a.addClass("checked");
        sendLoadNoteBody(noteId);
    }
}
//点击参与活动的笔记显示笔记内容
function loadActivedNoteBody() {
    var noteId=$(this).data("noteId");
    if(!noteId){
        alert("无效的笔记");
    }else{
        //用于设置选中效果
        $("#pc_part_8 a").removeClass("checked");
        var $a=$(this).find("a");
        $a.addClass("checked");
        sendLoadNoteBody(noteId);
    }
}
//点击搜索结果中的分享笔记显示分享笔记内容
function loadShareBody() {
    var shareId=$(this).data("shareId");
    if(!shareId){
        alert("无效的笔记");
    }else{
        //用于设置选中效果
        $("#pc_part_6 a").removeClass("checked");
        var $a=$(this).find("a");
        $a.addClass("checked");
        $.ajax({
            url:path+"share/searchShareById.do",
            data:{"shareId":shareId},
            type:"post",
            dataType:"json",
            success:function(result){
                //设置笔记标题
                $("#input_note_title").val(result.data.cn_share_title);
                //设置笔记内容
                um.setContent(result.data.cn_share_body);
            },
            error:function(){
                alert("显示笔记内容出错了...");
            }
        });
    }
}

/*加载活动笔记*/
function loadActivedNotes() {
    //获取cookie
    var userId=getCookie("userId");
    //判断是否获取到有效的userId
    if(!userId){  //若cookie中没有userId,则提示并返回登录界面
        alert("登录账户失效,请重新登录!");
        window.location.href="log_in.html";
    }else{
        $("#pc_part_7 a").removeClass("checked");
        $("#pc_part_8 a").removeClass("checked");
        $("#pc_part_6 a").removeClass("checked");
        $("#notes a").removeClass("checked");
        $.ajax({
            url:path+"note/loadNotes.do",
            data:{"userId":userId,"userStatus":4},
            type:"get",
            dataType:"json",
            success:function(result){
                if(result.status==0){
                    $("#pc_part_2").hide();
                    $("#pc_part_6").hide();
                    $("#pc_part_4").hide();
                    $("#pc_part_7").hide();
                    $("#pc_part_8").show();
                    $("#pc_part_8 .contacts-list").html("");
                    var list=result.data;
                    for(var i=0,len=list.length;i<len;i++){
                        var noteId=list[i].cn_note_id;
                        var noteTitle=list[i].cn_note_title;
                        createActivedNoteLi(noteId,noteTitle)
                    }
                }else if(result.status==1){
                    alert(result.msg);
                }
            },
            error:function(){
                alert("加载活动笔记出错了...")
            }
        });
    }
}

/*加载收藏笔记*/
function loadLikeNotes() {
    //获取cookie
    var userId=getCookie("userId");
    //判断是否获取到有效的userId
    if(!userId){  //若cookie中没有userId,则提示并返回登录界面
        alert("登录账户失效,请重新登录!");
        window.location.href="log_in.html";
    }else{
        $("#pc_part_7 a").removeClass("checked");
        $("#pc_part_8 a").removeClass("checked");
        $("#pc_part_6 a").removeClass("checked");
        $("#notes a").removeClass("checked");
        $.ajax({
            url:path+"note/loadNotes.do",
            data:{"userId":userId,"userStatus":3},
            type:"post",
            dataType:"json",
            success:function(result){
                if(result.status==0){
                    $("#pc_part_2").hide();
                    $("#pc_part_6").hide();
                    $("#pc_part_4").hide();
                    $("#pc_part_8").hide();
                    $("#pc_part_7").show();
                    $("#pc_part_7 .contacts-list").html("");
                    var list=result.data;
                    for(var i=0,len=list.length;i<len;i++){
                        var noteId=list[i].cn_note_id;
                        var noteTitle=list[i].cn_note_title;
                        createLikeNoteLi(noteId,noteTitle)
                    }
                }else if(result.status==1){
                    alert(result.msg);
                }
            },
            error:function(){
                alert("加载收藏笔记出错了...")
            }
        });
    }
}

/*撤销删除*/
function replay() {
    //获取参数
    var userId=getCookie("userId");
    var $li=$(this).parent().parent();
    var noteId=$li.data("noteId");
    if(!userId){
        alert("当前用户信息已过期,请重新登录");
        window.location.href="log_in.html";
    }else if(!noteId || noteId==""){
        alert("无效的笔记!");
    }else {
        //发送请求
        if(confirm("确认撤销删除?")){
            $.ajax({
                url: path + "note/updateStatus.do",
                data: {"noteId":noteId,"noteStatus":1},
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.status == 0) {
                        $li.remove();
                        alert(result.msg);
                    } else if (result.status == 1) {
                        alert(result.msg);
                    }
                },
                error: function () {
                    alert("撤销删除出错!");
                }
            });
        }
    }
}

/*彻底删除*/
function deleteAgin() {
    //获取参数
    var userId=getCookie("userId")
    var $li=$(this).parent().parent();
    var noteId=$li.data("noteId");
    if(!userId){
        alert("当前用户信息已过期,请重新登录");
        window.location.href="log_in.html";
    }else if(!noteId || noteId==""){
        alert("无效的笔记!");
    }else {
        //发送请求
        if (confirm("确认彻底删除?")) {
            $.ajax({
                url:path+"note/delNotes.do",
                data:{"noteId":noteId},
                type:"post",
                dataType:"json",
                success:function(result){
                    if (result.status == 0) {
                        $li.remove();
                        alert(result.msg);
                    } else if (result.status == 1) {
                        alert(result.msg);
                    }
                },
                error:function(){
                    alert("彻底删除笔记失败!");
                }
            });
        }
    }
}

/*加载回收站笔记*/
function loadRollbackNotes() {
    //获取cookie
    var userId=getCookie("userId");
    //判断是否获取到有效的userId
    if(!userId){  //若cookie中没有userId,则提示并返回登录界面
        alert("登录账户失效,请重新登录!");
        window.location.href="log_in.html";
    }else{
        $("#pc_part_7 a").removeClass("checked");
        $("#pc_part_8 a").removeClass("checked");
        $("#pc_part_6 a").removeClass("checked");
        $("#notes a").removeClass("checked");
        $.ajax({
            url:path+"note/loadRollback.do",
            data:{"userId":userId},
            type:"post",
            dataType:"json",
            success:function(result){
                if(result.status==0){
                    $("#pc_part_2").hide();
                    $("#pc_part_6").hide();
                    $("#pc_part_7").hide();
                    $("#pc_part_8").hide();
                    $("#pc_part_4").show();
                    $("#pc_part_4 .contacts-list").html("");
                    var list=result.data;
                    for(var i=0,len=list.length;i<len;i++){
                        var noteId=list[i].cn_note_id;
                        var noteTitle=list[i].cn_note_title;
                        createRollbackNoteLi(noteId,noteTitle)
                    }
                }
                if(result.status==1){
                    alert(result.msg);
                }
            },
            error:function(){
                alert("打开回收站失败,请稍后再试!");
            }
        });
    }
}

/*删除空的笔记本*/
function delBook() {
    //获取cookie
    var userId=getCookie("userId");
    //判断是否获取到有效的userId
    if(!userId){  //若cookie中没有userId,则提示并返回登录界面
        alert("登录账户失效,请重新登录!");
        window.location.href="log_in.html";
    }else{
        var $li=$(this).parent().parent();
        var bookId=$li.data("bookId");
        if(confirm("确定删除选中笔记本?")){
            $.ajax({
                url:path+"book/del.do",
                data:{"bookId":bookId},
                type:"post",
                dataType:"json",
                success:function(result){
                    if(result.status==0){
                        alert(result.msg);
                        $li.remove();
                    }
                },
                error:function(){
                    alert("删除出错,请重新操作!");
                }
            });
        }
        return false;
    }
}

/*1.显示所有笔记本*/
function loadUserBooks() {
    //获取cookie
    var userId=getCookie("userId");
    //判断是否获取到有效的userId
    if(!userId){  //若cookie中没有userId,则提示并返回登录界面
        alert("登录账户失效,请重新登录!");
        window.location.href="log_in.html";
    }else{
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
                        createBookLi(bookId,bookName);
                    }
                }
            },
            "error":function(){
                alert("获取笔记本信息异常!");
            }
        });
    }
}

/*2.显示用户名*/
function loadUserName() {
    //获取userId
    var userId=getCookie("userId");
    if(!userId){
        //判断是否有有效的用户
        alert("登录账户失效,请重新登录!");
        window.location.href="log_in.html";
    }else{
        $.ajax({
            "url":path+"user/findById.do",
            "data":"userId="+userId,
            "type":"post",
            "dataType":"json",
            "success":function(result){
                if(result.status==1){
                    alert(status.msg);
                }else if(result.status==0){
                    $("#user_menu").text(result.data.cn_user_name);
                }
            },
            "error":function(){
                alert("获取用户信息失败");
            }
        });
    }
}

/*3.退出登录*/
function userLogout() {
    delCookie("userId");
    if(!getCookie("userId")){
        alert("已退出当前账户");
    }
    window.location.href="log_in.html";
}

/*4.显示笔记本中的所有笔记*/
function loadUserNotes() {
    $("#pc_part_6").hide();
    $("#pc_part_4").hide();
    $("#pc_part_7").hide();
    $("#pc_part_8").hide();
    $("#pc_part_2").show();
    //设置选中效果
    $("#books a").removeClass("checked");
    var $sel=$(this).find("a");
    $sel.addClass("checked");
    //获取参数
    var bookId=$(this).data("bookId");
    if(!bookId){
        alert("无效的笔记本");
    }else{
        $("#notes").empty();
        $("#pc_part_7 a").removeClass("checked");
        $("#pc_part_8 a").removeClass("checked");
        $("#pc_part_6 a").removeClass("checked");
        $("#notes a").removeClass("checked");
        //发送ajax请求
        $.ajax({
            "url":path+"note/loadnote.do",
            "data":"bookId="+bookId,
            "type":"post",
            "dataType":"json",
            "success":function(result){
                if(result.status==1){  //当前笔记本中没有笔记
                    alert(result.msg);
                    //var del=$("<input type='button' value='X'  style='position: relative;left: 50%;' class='btn btn-default'>");
                    del=$("<button type='button' class='btn btn-default btn-xs btn_position btn_delete'><i class='fa fa-times'></i></button>")
                    $sel.find("button").remove();
                    $sel.append(del);
                }else if(result.status==0){  //查询到了相应的笔记
                    $sel.find("button").remove();
                    var notes=result.data;
                    var len=notes.length;
                    for(var i=0;i<len;i++){
                        var noteTitle=notes[i].cn_note_title;
                        var noteId=notes[i].cn_note_id;
                        createNoteLi(noteTitle,noteId);
                    }
                }
            },
            "error":function(){
                alert("查询笔记出错!");
            }
        });
    }
}

/*5.显示笔记内容*/
function loadNoteBody() {
    var noteId=$(this).data("noteId");
    if(!noteId){
        alert("无效的笔记");
    }else{
        //用于设置选中效果
        $("#notes a").removeClass("checked");
        var $a=$(this).find("a");
        $a.addClass("checked");
        sendLoadNoteBody(noteId);
    }
}
//发送加载笔记内容的请求
function sendLoadNoteBody(noteId){
    $.ajax({
        "url":path+"note/loadbody.do",
        "data":"noteId="+noteId,
        "type":"post",
        "dataType":"json",
        "success":function(result){
            if(result.status==0){
                //设置笔记标题
                $("#input_note_title").val(result.data.cn_note_title);
                //设置笔记内容
                um.setContent(result.data.cn_note_body);
            }else if(result.status==1){
                alert(result.msg);
            }
        },
        "error":function(){
            alert("读取笔记内容失败");
        }
    });
}

/*6.保存修改后的笔记内容*/
function updateNote() {
    //获取参数:被选中的笔记id,笔记标题,笔记内容
    var noteId=$("#notes a.checked").parent().data("noteId");
    var likeNoteId=$("#pc_part_7 a.checked").parent().data("noteId");
    var activedNoteId=$("#pc_part_8 a.checked").parent().data("noteId");
    var shareId=$("#pc_part_6 a.checked").parent().data("shareId");
    var noteTitle=$("#input_note_title").val();
    var noteBody=um.getContent();
    if(shareId){
        alert("共享笔记不能更改!");
    }else if(!noteId && !likeNoteId && !activedNoteId){
        alert("无效的笔记");
    }else if(noteTitle==""){
        alert("请输入笔记名称");
    }else{
        if(likeNoteId){
            noteId=likeNoteId;
        }else if(activedNoteId){
            noteId=activedNoteId;
        }
        $.ajax({
            "url":path+"note/update.do",
            "data":{"noteId":noteId,"noteTitle":noteTitle,"noteBody":noteBody},
            "type":"post",
            "dataType":"json",
            "success":function(result){
                if(result.status==0){
                    var $a=$("#notes a.checked");
                    var $checked_like=$("#pc_part_7 a.checked");
                    var $checked_activited=$("#pc_part_8 a.checked");
                    if($a.text()!=""){
                        $a.html('<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">'+
                            '</i>'+noteTitle+
                            '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">'+
                            '<i class="fa fa-chevron-down"></i>'+
                            '</button>');
                    }else if ($checked_like.text()!=""){
                        $checked_like.html('<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">' +
                                '</i>' + noteTitle )
                    }else if($checked_activited.text()!=""){
                        $checked_activited.html('<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">' +
                                '</i>' + noteTitle )
                    }else{
                        alert("请选中有效的笔记")
                    }
                    alert(result.msg);
                }else if(result.status==1){
                    alert(result.msg);
                }
            },
            "error":function(){
                alert("更新笔记出错!");
            }
        });
    }
}

/*7.弹出创建笔记本的窗口*/
function alertAddBookWindow() {
    //弹出新建笔记本对话框
    $("#can").load("alert/alert_notebook.html",encoding='utf-8');
    //显示背景
    $(".opacity_bg").show();
}

/*8.关闭对话框*/
function alertCloseBookWindow() {
    //关闭笔记本对话框
    $("#can").html("");
    //隐藏背景
    $(".opacity_bg").hide();
}

/*9.增加一个笔记本*/
function addBook() {
    //获取参数
    var userId=getCookie("userId");
    var bookName=$("#input_notebook").val();
    if(!userId){
        alert("当前用户信息已过期,请重新登录");
        window.location.href="log_in.html";
    }else if(!bookName || bookName==""){
        alert("笔记本名不能空");
    }else{
        $.ajax({
            url:path+"book/add.do",
            data:{"userId":userId,"bookName":bookName},
            type:"post",
            dataType:"json",
            success:function(result){
                if(result.status==0){
                    var bookId=result.data;  //获取返回的笔记本id
                    createBookLi(bookId,bookName);  //并将笔记本添加到列表中
                    alert(result.msg);  //弹出提示信息
                    alertCloseBookWindow();
                }else{
                    alert("创建失败!");
                }
            },
            error:function(){
                alert("新建笔记本出错!");
            }
        });
    }
}

/*10.弹出创建笔记的窗口*/
function alertAddNoteWindow() {
    //检测是否已选中笔记本
    if($("#books a.checked").length==0){
        alert("请选择有效的笔记本");
    }else {
        //弹出新建笔记对话框
        $("#can").load("alert/alert_note.html",encoding='utf-8');
        //显示背景
        $(".opacity_bg").show();
    }
}

/*11.增加一条笔记*/
function addNote() {
    $("#add_noteTitle_msg").text("");
    //获取参数
    var userId=getCookie("userId");
    var bookId=$("#books a.checked").parent().data("bookId");
    var noteTitle=$("#input_note").val().trim();
    if(!userId){
        alert("当前用户信息已过期,请重新登录");
        window.location.href="log_in.html";
    }else if(!noteTitle || noteTitle==""){
        $("#add_noteTitle_msg").text("笔记名不能为空");
    }else{
        $.ajax({
            url:path+"note/add.do",
            data:{"userId":userId,"bookId":bookId,"noteTitle":noteTitle},
            type:"post",
            dataType:"json",
            success:function(result){
                if(result.status==0){
                    $("#books a.checked").find("input:button").remove();
                    var noteId=result.data;  //获取返回的noteId
                    createNoteLi(noteTitle,noteId);  //在列表里添加笔记信息
                    alert(result.msg);  //提示信息
                    alertCloseBookWindow();  //关闭新建笔记对话框
                }else{
                    alert("创建失败!")
                }
            },
            error:function(){
                alert("新建笔记出错!");
            }
        });
    }
}

/*12.弹出重命名窗口*/
function alertModifyBookNameWindow() {
    //弹出修改名称窗口
    $("#can").load("alert/alert_rename.html");
    //显示背景
    $(".opacity_bg").show();
}

/*13.修改选定笔记本的名称*/
function renameBook() {
    $("#rename_book_msg").text("");
    //获取参数
    var userId=getCookie("userId");
    var $li=$("#books a.checked").parent();
    var bookId=$li.data("bookId");
    var bookName=$li.text().trim();
    var afterBookName=$("#input_notebook_rename").val();
    if(!userId){
        alert("当前用户信息已过期,请重新登录");
        window.location.href="log_in.html";
    }else if($li.length==0){
        alert("请选择有效的笔记本");
    }else if(afterBookName==""){
        $("#rename_book_msg").text("笔记本名称不能为空!");
    }else if(bookName==afterBookName){
        $("#rename_book_msg").text("请输入不同的名称!");
    }else{
        $.ajax({
            url:path+"book/rename.do",
            data:{"bookId":bookId,"afterBookName":afterBookName},
            type:"post",
            dataType:"json",
            success:function(result){
                if(result.status==0){
                    $li.html('<a class="checked">'+
                        '<i class="fa fa-book" title="online" rel="tooltip-bottom">'+
                        '</i>'+afterBookName+
                        '</a>');
                    alert(result.msg);
                    alertCloseBookWindow();
                }else if(result.status==1){
                    alert(result.msg);
                }
            },
            error:function(){{
                alert("重命名笔记本出错!");
            }}
        });
    }
}

/*14.显示三个菜单选项*/
function noteMenu() {
    var note_menu=$(this).parents("li").find("div");
    note_menu.slideToggle(300);
    return false;//阻止冒泡事件
}

/*15.隐藏下拉菜单*/
function hideMenu() {
    $("#notes div").hide();
}

/*16.笔记共享*/
function shareNote(){
    //获取参数
    var userId=getCookie("userId");
    var $li=$(this).parents("li");
    var noteId=$li.data("noteId");
    if(!userId){
        alert("当前用户信息已过期,请重新登录");
        window.location.href="log_in.html";
    }else if(!noteId){
        alert("无效的笔记");
    }else{
        $.ajax({
            url:path+"share/add.do",
            data:{"noteId":noteId},
            type:"post",
            dataType:"json",
            success:function(result){
                var noteTitle=$li.text();
                if(result.status==0){
                    var $i=$('<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">'+
                        '</i>'+noteTitle+
                        '<i class="fa fa-sitemap"></i></button></dt>'+
                        '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">'+
                        '<i class="fa fa-chevron-down"></i>'+
                        '</button>');
                    $li.find("a").html($i);
                }else if(result.status==1){
                    alert(result.msg);
                }
            },
            error:function(){
                alert("分享笔记出错!");
            }
        });
    }
}

/*17.弹出确认删除笔记窗口*/
function alertDeleteNoteWindow() {
    $("#can").load("alert/alert_delete_note.html");
    $(".opacity_bg").show();
}

/*18.更改笔记状态*/
function updateNoteStatus() {
    //获取参数
    var userId=getCookie("userId");
    var $li=$("#notes a.checked").parent();
    var noteId=$li.data("noteId");
    if(!userId){
        alert("当前用户信息已过期,请重新登录");
        window.location.href="log_in.html";
    }else if(!noteId || noteId==""){
        alert("无效的笔记!");
    }else {
        //发送请求
        $.ajax({
            url: path+"note/updateStatus.do",
            data: {"noteId":noteId,"noteStatus":2},
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.status == 0) {
                    $li.remove();
                    alert(result.msg);
                    alertCloseBookWindow();
                } else if (result.status == 1) {
                    alert(result.msg);
                    alertCloseBookWindow();
                }
            },
            error: function () {
                alert("删除笔记出错!");
            }
        });
    }
}

/*19.搜索分享笔记*/
function searchNote(event){
    //获取参数
    var userId=getCookie("userId");
    var code=event.keyCode;  //获取用户点击的按键
    var keyword=$("#search_note").val().trim();
    if(!userId){
        alert("当前用户信息已过期,请重新登录");
        window.location.href="log_in.html";
    }else{
        if(code==13){
            //清空之前的查询列表
            $("#pc_part_6 .contacts-list").html("");
            page=1;
            loadPageShare(keyword,page);
        }
    }
}
//发送ajax请求加载列表
function loadPageShare(keyword,page){
    $("#pc_part_7 a").removeClass("checked");
    $("#pc_part_8 a").removeClass("checked");
    $("#pc_part_6 a").removeClass("checked");
    $("#notes a").removeClass("checked");
    $.ajax({
        url:path+"share/search.do",
        data:{"keyword":keyword,"page":page},
        type:"get",
        dataType:"json",
        success:function(result){
            if(result.status==0){
                $("#pc_part_2").hide();
                $("#pc_part_4").hide();
                $("#pc_part_7").hide();
                $("#pc_part_8").hide();
                $("#pc_part_6").show();
                var list=result.data;
                for(var i=0,len=list.length;i<len;i++){
                    var shareId=list[i].cn_share_id;
                    var shareTitle=list[i].cn_share_title;
                    createSearchNoteLi(shareId,shareTitle);
                }
            }else if(result.status==1){
                alert(result.msg);
            }
        },
        error:function(){
            alert("搜索笔记出错!");
        }
    });
}

/*20.显示下一页查询结果*/
function moreSearchShare() {
    //获取参数
    var userId=getCookie("userId");
    var keyword=$("#search_note").val().trim();
    page++;
    if(!userId){
        alert("当前用户信息已过期,请重新登录");
        window.location.href="log_in.html";
    }else{
        //发送ajax请求加载列表
        loadPageShare(keyword,page);
    }
}





/*
 * 创建笔记本li元素
 */
function createBookLi(bookId,bookName){
    var $li=$('<li class="online">'+
        '<a>'+
        '<i class="fa fa-book" title="online" rel="tooltip-bottom">'+
        '</i>'+bookName+
        '</a>'+
        '</li>');
    //将bookId的值与jquery对象绑定
    $li.data("bookId",bookId);
    //将li元素添加到笔记本ul列表区
    $("#books").append($li);
}

/*
 * 创建note列表的方法
 */
function createNoteLi(noteTitle,noteId){
    var $li=$('<li class="online">'+
        '<a>'+
        '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">'+
        '</i>'+noteTitle+
        '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">'+
        '<i class="fa fa-chevron-down"></i>'+
        '</button>'+
        '</a>'+
        '<div class="note_menu" tabindex="-1">'+
        '<dl>'+
        '<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享">'+
        '<i class="fa fa-sitemap"></i></button></dt>'+
        '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除">'+
        '<i class="fa fa-times"></i></button></dt>'+
        '</dl>'+
        '</div>'+
        '</li>');
    $li.data("noteId",noteId);
    $("#notes").append($li);
}

/*
 *创建搜索到的笔记列表
 */
function createSearchNoteLi(shareId,shareTitle) {
    var $li = $('<li class="online">' +
        '<a>' +
        '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">' +
        '</i>' + shareTitle +
        '<button type="button" class="btn btn-default btn-xs btn_position btn_like" title="收藏">' +
        '<i class="fa fa-star-o"></i>' +
        '</button>' +
        '</a>' +
        '</li>');
    $li.data("shareId", shareId);
    $("#pc_part_6 .contacts-list").append($li);
}

/*
 *创建回收站的笔记列表
 */
function createRollbackNoteLi(noteId,noteTitle){

    var $li = $('<li class="disable">' +
        '<a>' +
        '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">' +
        '</i>' + noteTitle +
        '<button type="button" class="btn btn-default btn-xs btn_position btn_delete">' +
        '<i class="fa fa-times">'+
        '</button>'+
        '<button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay">'+
        '</i>'+
        '<i class="fa fa-reply"></i>'+
        '</button>'+
        '</a>' +
        '</li>');
    $li.data("noteId", noteId);
    $("#pc_part_4 .contacts-list").append($li);
}

/*
* 创建收藏笔记列表
*/
function createLikeNoteLi(noteId,noteTitle){
    var $li = $('<li class="disable">' +
            '<a>' +
            '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">' +
            '</i>' + noteTitle +
            '</a>' +
        '</li>');
    $li.data("noteId", noteId);
    $("#pc_part_7 .contacts-list").append($li);
}

/*
* 创建活动笔记列表
*/
function createActivedNoteLi(noteId,noteTitle){
    var $li = $('<li class="disable">' +
        '<a>' +
        '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">' +
        '</i>' + noteTitle +
        '</a>' +
        '</li>');
    $li.data("noteId", noteId);
    $("#pc_part_8 .contacts-list").append($li);
}