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
				课程信息
			</div>
			<form action="list.jhtml" method="get" id="listForm">
			<div class="bar">
				<a id="addIcon" class="iconButton" href="course_add.jhtml">添加课程信息</a>
				<a id="addIcon" class="iconButton" href="toImportExcel.jhtml">导入课程信息</a>
				<a id="refreshBtn" class="iconButton" href="#">刷新</a>
				<a id="searchBtn" class="iconButton" href="#">开始查询</a>
			</div>
			<br></br>
			<div class="searchDiv">
			<dl>
				<dt>课程名称:</dt>
				<dd>
				<input class="text" name="courseName" value="${courseName!}"/>
				</dd>
			</dl>
			<dl>
				<dt>任课教师:</dt>
				<dd>
				<input class="text" name="teacher" value="${teacher!}"/>
				</dd>
			</dl>
			<dl>
				<dt>周几上课:</dt>
				<dd>
				<select name="weekDay" class="text" >
				<option value="" selected = "selected">请选择...</option>
				<option value="1" [#if weekDay=="1"] selected="selected" [/#if]>周一</option>
				<option value="2" [#if weekDay=="2"] selected="selected" [/#if]>周二</option>
				<option value="3" [#if weekDay=="3"] selected="selected" [/#if]>周三</option>
				<option value="4" [#if weekDay=="4"] selected="selected" [/#if]>周四</option>
				<option value="5" [#if weekDay=="5"] selected="selected" [/#if]>周五</option>
				</select>
				</dd>
			</dl>
			<dl>
				<dt>上课班级:</dt>
				<dd>
				<input class="text" name="clazz" value="${clazz!}"/>
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
						<th>课程名称</th>
						<th>任课教师</th>
						<th>上课班级</th>
						<th>开始日期</th>
						<th>结束日期</th>
						<th>周几上课</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>上课教室</th>
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
							${data.courseName!}
						</td>
						<td>${data.teacherName!}</td>
						<td>${data.clazzName!}</td>
						<td>${data.beginDate?string("yyyy-MM-dd")}</td>
						<td>${data.endDate?string("yyyy-MM-dd")}</td>
						<td>
							[#if data.weekDay == 1]
							周一
							[#elseif data.weekDay == 2]
							周二
							[#elseif data.weekDay == 3]
							周三
							[#elseif data.weekDay == 4]
							周四
							[#elseif data.weekDay == 5]
							周五
							[/#if]
						</td>
						<td>${data.beginTime!}</td>
						<td>${data.endTime!}</td>
						<td>${data.room!}</td>
						<td><a href="course_edit.jhtml?id=${data.id!}">编辑</a></td>
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
		</script>
	</body>
</html>
