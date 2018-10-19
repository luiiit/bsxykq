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
		<script src="/bsxykq/pages/assets/js/ace-extra.min.js"></script>
		<script type="text/javascript">
		function update(){
			if($("#name").val()==""){
				$.message("warn","学院名称不能为空");
				return false;
			}
			$.ajax({
				url:"/bsxykq/admin/institute/institute_update.jhtml",
				data:$("#inputForm").serialize(),
				type: "post",
				dataType: "json",
				cache: false,
				success: function(message) {
					if(message.type == "success"){
						$.message("success","修改成功");
						setTimeout(function() {
			    			location.href="list.jhtml";
						}, 3000);
					}else{
						$.message("warn","此教学秘书已负责其他学院");
					}
				}
			})
		}
		</script>
	</head>
	<body class="no-skin" style="background-color:white;overflow-x:hidden">
		<div class="main-content">
			<div class="title" >
				编辑学院
			</div>
		<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${map.id}"></input>
		<ul id="tab" class="tab" style="margin:0px 0px 10px 0px;">
			<li>
				<input type="button" value="基本信息" class="current"/>
			</li>
		</ul>
		<table class="input" style="margin-left:47px;">
			<tr>
				<th style="width:80px">
					<span class="requiredField">*</span>学院名称:
				</th>
				<td  width="270">
					<input class="text" name="name" id="name" value="${map.name}"/>
				</td>
				<th style="width:80px">
					<span class="requiredField">*</span>教学秘书:
				</th>
				<td  width="270">
					<select name="secretary" class="text">
					[#list list as data]
						<option value="${data.id}" [#if data.id == map.secretary] selected="selected" [/#if]>${data.name}</option>
						[/#list]
					</select>
				</td>
			</tr>
		</table>
		<table class="input btn-table">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" class="button" value="保存" onclick="update()" />
					<input type="button" class="button back-button" value="返回" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
	</div>
	</body>
</html>
