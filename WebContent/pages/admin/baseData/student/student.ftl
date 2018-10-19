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
	<style>
	dt{
		font-weight:100;
	}
	</style>
	<script type="text/javascript">
	function selectClazz_front(e){
		$.dialog({
			title:"查询班级",
			width:900,
			height:458,
			content: "<iframe  name='iframePager_clazz' src='select_clazz.jhtml' width='100%' height=520px' style='border:0px'><\/iframe>",
			ok: "确定",
			cancel: "取消",
			onOk: function() {
				var rows = iframePager_clazz.window.childMethod();
				if(rows.length>0){
					var row = rows[0];
					$("#clazzId").val(row.id);
					$("#clazz").val(row.name);
				}
			},
			onShow:function(){
				$(".xxDialog").css("margin-top","-110px");
			}
		});
	};
	</script>
	</head>
	<body class="no-skin" style="background-color:white;overflow-x:hidden">
		<div class="main-content">
			<div class="title" >
				学生基础数据
			</div>
			<form action="list.jhtml" method="get" id="listForm">
			<div class="bar">
				<a id="addIcon" class="iconButton" href="student_add.jhtml">添加学生</a>
				<a id="importBtn" class="iconButton" href="import_from_excel.jhtml">Excel导入数据</a>
				<a id="exportBtn" class="iconButton" href="#" onclick="export_to_excel()">导出到Excel</a>
				<a id="refreshBtn" class="iconButton" href="#">刷新</a>
				<a id="searchBtn" class="iconButton" href="#">开始查询</a>
			</div>
			<br></br>
			<div class="searchDiv">
			<dl>
				<dt>班级:</dt>
			<dd>
			<div class="search">
				<input class="text" name="clazz" id="clazz" value="${clazz!}"   />
				<input type="button" class="searchBtn" value="" onclick="selectClazz_front()" />
				<input type="hidden" name="clazzId" id="clazzId"  value="${clazzId!}"/>
			</div>
			</dd>
			</dl>  
			
			<dl>
				<dt>专业:</dt>
				<dd>
				<input class="text" name="major" value="${major!''}"/>
				</dd>
			</dl>
			<dl>
				<dt>学院:</dt>
				<dd>
				<input class="text" name="institute" value="${institute!''}"/>
				</dd>
			</dl>
			<dl>
				<dt>学生姓名:</dt>
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
			<dl>
				<dt>学号:</dt>
				<dd>
				<input class="text" name="no" value="${no!''}"/>
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
						<th>姓名</th>
						<th>照片</th>
						<th>学院</th>
						<th>专业</th>
						<th>班级</th>
						<th>性别</th>
						<th>手机号</th>
						<th>邮箱</th>
						<th>父母手机号</th>
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
						<td>
							${(data.path)!""}
						</td>
						<td>${(data.instituteName)!"" }</td>
						<td>${(data.majorName)!""}</td>
						<td>${(data.clazzName)!""}</td>
						<td>${(data.sex)!""}</td>
						<td>${(data.phone)!""}</td>
						<td>${(data.email)!""}</td>
						<td>${data.pphone}</td>	
						<td><a href="student_edit.jhtml?id=${data.id}">编辑</a></td>
					</tr>
				[/#list]
				</tbody>
			</table>
		</div>

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
				$("#listForm").attr("method","post");
				$("#listForm").submit();
				$("#listForm").attr("action","list.jhtml");
				$("#listForm").attr("method","get");
			}
		</script>
	</body>
</html>
