//加载活动信息
function loadActivity(){
	$.ajax({
		"url":path+"activity/loadactivity.do",
		"type":"get",
		"dataType":"json",
		"success":function(result){
			if(result.status==1){
				alert(result.msg);
			}else if(result.status==0){
				var list=result.data;
				var $li="";
				for(var i=0,len=list.length;i<len;i++){
                    var rol=i%4;
                    if(rol==0){
                        rol='bg-primary';
                    }else if(rol==1){
                        rol='bg-danger';
                    }
                    else if(rol==2){
                        rol='bg-inverse';
                    }else{
                        rol='bg-warning';
                    }
                    var activity_id=list[i].cn_activity_id;
                    var	title=list[i].cn_activity_title;
                    var	body=list[i].cn_activity_body;
                    var	endTime=list[i].cn_activity_end_time;
					$li+="<div class='col-sm-4'> " +
							"<div id='contentfeeds"+i+"class='panel panel-animated panel-default animated fadeInUp' style='visibility: visible;'> "+
								"<div class='panel-body bordered-bottom'> "+
									"<div class='no-padding jumbotron "+rol+"'> "+
										"<p class='lead'>" +
											"<a href='activity_detail.html#"+activity_id+"'>"+title+"</a>" +
										"</p>" +
									"</div>" +
									"<p class='text-muted'>"+body+"</p>" +
									"<div class='text-muted'>" +
										"<small style='color:red;'>活动结束时间:"+endTime+"</small>" +
									"</div>" +
								"</div>" +
							"</div>" +
						"</div>";
                //<div id="contentfeeds'+i+'" class="panel panel-animated panel-default animated fadeInUp" style="visibility: visible;"><div class="panel-body bordered-bottom"><div class="no-padding jumbotron '+rol+'"><p class="lead"><a href="activity_detail.html#'+a[i].cnActivityId+'">'+a[i].cnActivityTitle+'</a></p></div><p class="text-muted">'+a[i].cnActivityBody+'</p><div class="text-muted"><small style="color:red;">活动结束时间:'+a[i].cnActivityEndTime+'</small></div></div></div>
                   /* $("#col_0").html($("#col_0").html()+title+"</br>");
					$("#col_1").html($("#col_1").html()+body+"</br>");
					$("#col_2").html($("#col_2").html()+endTime+"</br>");*/
				}
                $("#row-activity").append($li);
			}
		},
		"error":function(){
			alert("加载活动信息出错!");
		}
	});
}

