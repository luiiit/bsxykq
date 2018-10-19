<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>百色学院考勤管理系统</title>
		<meta name="description" content="Static &amp; Dynamic Tables" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="/bsxykq/pages/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="/bsxykq/pages/assets/font-awesome/4.1.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="/bsxykq/pages/assets/css/google-font.css" />
		<link rel="stylesheet" href="/bsxykq/pages/assets/css/ace.min.css" id="main-ace-style" />
		<link rel="stylesheet" href="/bsxykq/pages/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="/bsxykq/pages/assets/css/ace-rtl.min.css" />
		<script src="/bsxykq/pages/assets/js/jquery/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="/bsxykq/js/showMember.js"></script>
	</head>
<script type="text/javascript">
$().ready(function() {
	$("#file").change(function()
        {
            var arrs=$(this).val().split('\\');
            var filename=arrs[arrs.length-1];
            if(filename != ""){
            	$(".fileName").html(filename);
            }
            else{
            	$(".fileName").html("未选择任何文件");
            }
        });
        
       var explorer = window.navigator.userAgent.toLowerCase(); 
      if (explorer.indexOf("msie") >= 0 || explorer.indexOf("Firefox") >= 0) {
		$(".file input").css("fontSize","100px");
		}
		else if(explorer.indexOf("chrome") >= 0){
			$(".file input").css("fontSize","0");
		}
		$("#submit_button").click(function(){
			if($("#file").val() == ""){
				$.message("warn","请选择导入文件");
				return false;
			}
			else{
				$("#submit_button").attr("disabled",true);
				var formData = new FormData($('#inputForm')[0]);
				$.ajax({
					url:"importExcel.jhtml",
					type:"post",
					data:formData,
					 processData: false,
       				 contentType: false,
					dataType:"json",
					cache:false,
					success:function(message){
						if(message.type == "success"){
							$.message("success",message.msg);
							setTimeout(function (){
								location.href="list.jhtml";
							},3000);
						}
						else{
							var html = '<div style="max-height:250px;overflow-y:auto;">'+message.msg+'</div>';
							$.dialog({
								type:"warn",
								content:html,
								width:450,
								ok:"确定",
								cancel:null
							});
							$("#submit_button").attr("disabled",false);
						}
					}
				})
			}
		})
		
	
});
</script>
<style type="text/css">
	input.loadBtn{
		height:30px;
		min-width:90px;
		padding:0 15px;
		border-radius:3px;
	}
.file {
	    position: relative;
	    display: inline-block;
	    background: #02aae9;
	    border: 1px solid #02aae9;
	    border-radius: 3px;
	    padding: 0px 15px;
	    overflow: hidden;
	    color: #fff;
	    text-indent: 0;
	    line-height: 28px;
	    font-size:13px;
	    cursor:pointer;
	}
	.file input {
	    position: absolute;
	    font-size: 100px;
	    width:90px;
	    right: 0;
	    top: 0;
	    bottom:0;
	    left:0;
	    height:30px;
	    z-index:1;
	    opacity: 0;
	    filter:alpha(opacity=0);
	    cursor:pointer;
	}
	.file:hover{
		color:#fff;
	}
	.fileName{
		display:inline-block;
		min-width:120px;
		color:#999;
		vertical-align:top;	
		margin:8px 8px 0 5px;	
	}
</style>
<body class="no-skin" style="background-color:white;overflow-x:hidden">
	<div class="title">
		&nbsp;Excel导入教师信息
	</div>
	<form id="inputForm" action="importExcel.jhtml" method="post" enctype="multipart/form-data">
		<ul id="tab" class="tab" style="margin:0px 0px 10px 0px;">
			<li>
				<input type="button" value="基础信息" class="current"/>
			</li>
		</ul>
			<table class="input tabContent" >
				<tr>
					<th>
						<span class="requiredField">*</span>选择Excel文件:
					</th>
					<td style="text-align:left">
						<a href="javascript:;" class="file">选择文件
	    					<input id="file" type="file" name="file"/>
						</a>
						<div class="fileName">未选择任何文件</div>
					</td>
				<th>
					下载文件模板:
				</th>
				<td>
					<input type="button" value="模板下载" class="button loadBtn" onclick="window.location='../../pages/admin/excel/courseTemp.xls'" />
				</td>
			</tr>
			</table>
			<table class="input btn-table">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" id="submit_button" class="button" value="确定" />
					<input type="button" class="button back-button" value="返回" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
	</body>
</html>
