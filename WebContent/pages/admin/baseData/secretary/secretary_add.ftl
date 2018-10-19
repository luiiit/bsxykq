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
		function save(){
			$("#submitBtn").attr("disabled","disabled");
			if($("#name").val()==""){
				$.message("warn","姓名不能为空");
				$("#submitBtn").removeAttr("disabled");
				return false;
			}
			if($("#phone").val=="" || $("#email")==""){
				$.message("warn","手机号或邮箱必填一个");
				$("#submitBtn").removeAttr("disabled");
				return false;
			}
			$.ajax({
				url:"/bsxykq/admin/secretary/secretary_save.jhtml",
				data:$("#inputForm").serialize(),
				type: "post",
				dataType: "json",
				cache: false,
				success: function(message) {
					if(message.type == "success"){
						$.message("success","添加成功");
						setTimeout(function() {
			    			location.href="list.jhtml";
						}, 3000);
					}else{
						$.message("warn",message.msg);
					}
				}
			})
		}
		</script>
	</head>
	<body class="no-skin" style="background-color:white;overflow-x:hidden">
		<div class="main-content">
			<div class="title" >
				添加教学秘书信息
			</div>
		<form id="inputForm" action="update.jhtml" method="post">
		<ul id="tab" class="tab" style="margin:0px 0px 10px 0px;">
			<li>
				<input type="button" value="基本信息" class="current"/>
			</li>
		</ul>
		<table class="input" style="margin-left:47px;">
			<tr>
				<th style="width:80px">
					<span class="requiredField">*</span>姓名:
				</th>
				<td  width="270">
					<input  class="text" name="name" id="name" value=""/>
				</td>
				<th style="width:80px">
					<span class="requiredField">*</span>性别:
				</th>
				<td  width="270">
					<select  class="text" name="sex" id="sex" >
					<option value="">请选择...</option>
					<option value="男">男</option>
					<option value="女">女</option>
					</select>
				</td>
			</tr>
				
				<tr>
				<th style="width:80px">
					<span class="requiredField">*</span>手机号:
				</th>
				<td  width="270">
					<input class="text" name="phone" id="phone" value=""/>
				</td>
				<th style="width:80px">
					<span class="requiredField">*</span>邮箱:
				</th>
				<td  width="270">
					<input class="text" name="email" id="email" value=""/>
				</td>
			</tr>
			<tr>
				<th style="width:80px">
					<span class="requiredField">*</span>教职工编号:
				</th>
				<td  width="270">
					<input  class="text" name="no" id="no" value=""/>
				</td>
		</tr>
		</table>
		<table class="input btn-table">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" class="button" value="保存" onclick="save()" id="submitBtn" />
					<input type="button" class="button back-button" value="返回" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
	</div>
	</body>
</html>
