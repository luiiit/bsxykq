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
		<script type="text/javascript" src="/bsxykq/js/WdatePicker.js"></script>
		<script src="/bsxykq/pages/assets/js/ace-extra.min.js"></script>
		<script type="text/javascript">
		function update(){
			$("#submitBtn").attr("disabled","disabled");
			if($("#name").val()==""){
				$.message("warn","课程名称不能为空");
				$("#submitBtn").removeAttr("disabled");
				return false;
			}
			if($("#workDay").val()==""){
				$.message("warn","请选择周几上课");
				$("#submitBtn").removeAttr("disabled");
				return false;
			}
			if($("#workDay").val()==""){
				$.message("warn","请选择周几上课");
				$("#submitBtn").removeAttr("disabled");
				return false;
			}
			if($("#clazzId").val()==""){
				$.message("warn","请选择上课班级");
				$("#submitBtn").removeAttr("disabled");
				return false;
			}
			if($("#teacherId").val()==""){
				$.message("warn","请选择上课教师");
				$("#submitBtn").removeAttr("disabled");
				return false;
			}
			if($("#beginDate1").val()==""){
				$.message("warn","请选择课程开始日期");
				$("#submitBtn").removeAttr("disabled");
				return false;
			}
			if($("#endDate1").val()==""){
				$.message("warn","请选择课程结束日期");
				$("#submitBtn").removeAttr("disabled");
				return false;
			}
			if($("#beginTime1").val()==""){
				$.message("warn","请选择课程开始时间");
				$("#submitBtn").removeAttr("disabled");
				return false;
			}
			if($("#endTime1").val()==""){
				$.message("warn","请选择课程结束时间");
				$("#submitBtn").removeAttr("disabled");
				return false;
			}
			if($("#room").val()==""){
				$.message("warn","请选择上课教室");
				$("#submitBtn").removeAttr("disabled");
				return false;
			}
			$.ajax({
				url:"/bsxykq/admin/course/course_update.jhtml",
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
						$("#submitBtn").removeAttr("disabled");
					}
				}
			})
		}
		
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
		
		function selectTeacher_front(e){
			if($("#clazzId").val() == ""){
				$.message("warn","请先选择班级");
				return false;
			}
			
			var cid = $("#clazzId").val();
			$.dialog({
				title:"查询班级",
				width:900,
				height:458,
				content: "<iframe  name='iframePager_teacher' src='select_teacher.jhtml?cid="+cid+"' width='100%' height=520px' style='border:0px'><\/iframe>",
				ok: "确定",
				cancel: "取消",
				onOk: function() {
					var rows = iframePager_teacher.window.childMethod();
					if(rows.length>0){
						var row = rows[0];
						$("#teacherId").val(row.id);
						$("#teacher").val(row.name);
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
				修改课程
			</div>
		<form id="inputForm" action="save.jhtml" method="post">
		<ul id="tab" class="tab" style="margin:0px 0px 10px 0px;">
			<li>
				<input type="button" value="基本信息" class="current"/>
			</li>
		</ul>
		<table class="input" style="margin-left:47px;">
			<tr>
				<th style="width:80px">
					<span class="requiredField">*</span>课程名称:
				</th>
				<td  width="270">
					<input  type="hidden" name = "id" value="${course.id!}"/>
					<input class="text" name="name" id="name" value="${course.courseName!}"/>
				</td>
				<th style="width:80px">
					<span class="requiredField">*</span>周几上课:
				</th>
				<td  width="270">
					<select name="weekDay" class="text" id="workDay" >
				<option value="" selected = "selected">请选择...</option>
				<option value="1" [#if course.weekDay == 1] selected="selected" [/#if]  >周一</option>
				<option value="2"  [#if course.weekDay == 2] selected="selected" [/#if] >周二</option>
				<option value="3"  [#if course.weekDay == 3] selected="selected" [/#if] >周三</option>
				<option value="4" [#if course.weekDay == 4] selected="selected" [/#if]  >周四</option>
				<option value="5" [#if course.weekDay == 5] selected="selected" [/#if]  >周五</option>
				</select>
				</td>
				
			</tr>
			<tr>
			<th style="width:80px">
					<span class="requiredField">*</span>上课班级:
				</th>
				<td  width="270">
						<div class="search">
						<input class="text" name="clazz" id="clazz"   style="margin-top:20px" value="${course.clazzName!}"/>
						<input type="button" class="searchBtn" value="" onclick="selectClazz_front()" />
						<input type="hidden" name="clazzId" id="clazzId"  value="${course.clazz!}"/>
				</div>
				</td>
				<th style="width:80px">
					<span class="requiredField">*</span>任课教师:
				</th>
				<td  width="270">
				<div class="search">
						<input class="text" name="teacher" id="teacher"   style="margin-top:20px" value="${course.teacherName!}"/>
						<input type="button" class="searchBtn" value="" onclick="selectTeacher_front()" />
						<input type="hidden" name="teacherId" id="teacherId"  value="${course.teacher!}"/>
				</div>
				</td>
			</tr>
			<tr>
				<th style="width:80px">
					<span class="requiredField">*</span>开始日期:
				</th>
				<td  width="270">
				<input id="beginDate1" name="beginDate" class="text Wdate"  onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" type="text" value="${course.beginDate?string("yyyy-MM-dd")}" />
				</td>
        			<th style="width:80px">
					<span class="requiredField">*</span>结束日期:
				</th>
				<td  width="270">
				<input id="endDate1" name="endDate" class="text Wdate"  onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'beginDate1\')}'});" type="text" value="${course.endDate?string("yyyy-MM-dd")}" />
				</td>
			</tr>
			<tr>
				<th style="width:80px">
					<span class="requiredField">*</span>开始时间:
				</th>
				<td  width="270">
				<input id="beginTime1" name="beginTime" class="text Wdate" value="${course.beginTime!}" onfocus="WdatePicker({dateFmt: 'HH:mm:ss'});" type="text"  />
				</td>
        			<th style="width:80px">
					<span class="requiredField">*</span>结束时间:
				</th>
				<td  width="270">
				<input id="endTime1" name="endTime" class="text Wdate" value="${course.endTime!}" onfocus="WdatePicker({dateFmt: 'HH:mm:ss', minDate: '#F{$dp.$D(\'beginTime1\')}'});" type="text"  />
				</td>
			</tr>
			<tr>
			<th style="width:80px">
					<span class="requiredField">*</span>上课教室:
				</th>
				<td  width="270">
					<input class="text" name="room" id="room" value="${course.room}"/>
				</td>
			</tr>
		</table>
		<table class="input btn-table">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" class="button" value="保存" onclick="update()" id="submitBtn" />
					<input type="button" class="button back-button" value="返回" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
	</div>
	</body>
</html>
