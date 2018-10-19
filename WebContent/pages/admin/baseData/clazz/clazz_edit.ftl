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
				$.message("warn","班级名称不能为空");
				return false;
			}
			$.ajax({
				url:"/bsxykq/admin/clazz/clazz_update.jhtml",
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
				编辑班级信息
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
					<span class="requiredField">*</span>名称:
				</th>
				<td  width="270">
				<input type="hidden" name="id" value="${clazz.id}"/>
					<input  class="text" name="name" id="name" value="${clazz.name}" readonly="readonly"/>
				</td>
				<th style="width:80px">
					<span class="requiredField">*</span>简称:
				</th>
				<td  width="270">
					<input  class="text" name="simpleName" id="simpleName" value="${clazz.simpleName}" readonly="readonly"/>
				</td>
			</tr>
				
				<tr>
				<th style="width:80px">
					<span class="requiredField">*</span>学院:
				</th>
				<td  width="270">
				<input class="text" value="${clazz.instituteName}" readonly="readonly" />
				</td>
				<th style="width:80px">
					<span class="requiredField">*</span>教学秘书:
				</th>
				<td  width="270">
				<input name="secretary" value="" id="secretaryId" type="hidden"/>
				<input id="secretary" class="text" readonly="readonly" value="${clazz.secretaryName}"/>
				</td>
				</tr>
				<tr>
			
				<th style="width:80px">
					<span class="requiredField">*</span>专业:
				</th>
				<td  width="270">
					<input  class="text" value="${clazz.majorName}" readonly="readonly"/>
				</td>
					
				<th style="width:80px">
					<span class="requiredField">*</span>辅导员:
				</th>
				<td  width="270">
					<select name="mainTeacher" id="mainTeacher" class="text">
					[#list data as data]
					<option value="${data.id}"[#if clazz.major == data.id] selected="selected" [/#if]>${data.name}</option>
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
