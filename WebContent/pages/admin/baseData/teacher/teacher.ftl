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
		<!-- ace styles -->
		<link rel="stylesheet" href="/bsxykq/pages/assets/css/ace.min.css" id="main-ace-style" />

		<link rel="stylesheet" href="/bsxykq/pages/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="/bsxykq/pages/assets/css/ace-rtl.min.css" />
		<script src="/bsxykq/pages/assets/js/ace-extra.min.js"></script>
	</head>
	<style>
	dt{
		font-weight:100;
	}
	</style>

	<body class="no-skin" style="background-color:white;overflow-x:hidden">
		<div class="main-content">
			<div class="title" >
				教师基础数据
			</div>
			<form action="list.jhtml" method="get" id="listForm">
			<div class="bar">
				<a id="addIcon" class="iconButton" href="teacher_add.jhtml">添加教师</a>
				<a id="importBtn" class="iconButton" href="import_from_excel.jhtml">Excel导入数据</a>
				<a id="exportBtn" class="iconButton" href="#" onclick="export_to_excel()">导出到Excel</a>
				<a id="refreshBtn" class="iconButton" href="#">刷新</a>
				<a id="searchBtn" class="iconButton" href="#">开始查询</a>
			</div>
			<br></br>
			<div class="searchDiv">
			<dl>
				<dt>学院:</dt>
				<dd>
				<select name="institute" >
				<option value="" selected = "selected">请选择...</option>
				[#list data as data]
				<option value="${data.id}" [#if institute == data.id ]selected="selected"[/#if]>${data.institute}</option>
				[/#list]
				</select>
				</dd>
			</dl>
			<dl>
				<dt>教师姓名:</dt>
				<dd>
				<input class="text" name="name" value="${name!''}"/>
				</dd>
			</dl>
			<dl>
				<dt>电话:</dt>
				<dd>
				<input class="text" name="phone" value="${phone!''}"/>
				</dd>
			</dl>
			</div>
			</form>
			<table id="sample-table-1"  class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">
							<label class="position-relative">
							<input type="checkbox" class="ace" />
							<span class="lbl"></span>
							</label>
						</th>
						<th>教师姓名</th>
						<th>职称</th>
						<th>所属学院</th>
						<th>性别</th>
						<th>联系方式</th>
						<th>邮箱</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				[#list list as data]
					<tr>
						<td class="center">
							<label class="position-relative">
								<input type="checkbox" class="ace" />
								<span class="lbl"></span>
								</label>
						</td>
						<td>
							${(data.name)!""}
						</td>
						<td>${(data.professional)!"" }</td>
						<td>${data.instituteName!}</td>
						<td>${(data.sex)!""}</td>
						<td>${(data.phone)!""}</td>
						<td>${(data.email)!""}</td>
						<td><a href="teacher_edit.jhtml?id=${data.id}">编辑</a></td>
					</tr>
				[/#list]
				</tbody>
			</table>
		</div>

			


		<script src="/bsxykq/pages/assets/js/jquery/jquery-2.1.1.min.js"></script>


		<script type="text/javascript">
			window.jQuery || document.write("<script src='/bsxykq/pages/assets/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='/bsxykq/pages/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="/bsxykq/pages/assets/bootstrap/3.2.0/js/bootstrap.min.js"></script>

		<script src="/bsxykq/pages/assets/js/jquery.dataTables.min.js"></script>
		<script src="/bsxykq/pages/assets/js/jquery.dataTables.bootstrap.js"></script>

		<script src="/bsxykq/pages/assets/js/ace-elements.min.js"></script>
		<script src="/bsxykq/pages/assets/js/ace.min.js"></script>

		<script type="text/javascript">
			jQuery(function($) {
					$("#searchBtn").click(function(){
						$("#listForm").submit();
					});
					$("#refreshBtn").click(function(){
						$("#listForm").submit();
					});
				$(document).on('click', 'th input:checkbox' , function(){
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox')
					.each(function(){
						this.checked = that.checked;
						$(this).closest('tr').toggleClass('selected');
					});
				});
			})
			
			function export_to_excel(){
				$("#listForm").attr("action","export_to_excel.jhtml");
				$("#listForm").submit();
				$("#listForm").attr("action","list.jhtml");
			}
		</script>
	</body>
</html>
