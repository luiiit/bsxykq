<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查询教师</title>
		<link rel="stylesheet" href="/bsxykq/pages/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="/bsxykq/pages/assets/font-awesome/4.1.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="/bsxykq/pages/assets/css/google-font.css" />
		<link rel="stylesheet" href="/bsxykq/pages/assets/css/ace.min.css" id="main-ace-style" />
		<link rel="stylesheet" href="/bsxykq/pages/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="/bsxykq/pages/assets/css/ace-rtl.min.css" />
		<script src="/bsxykq/pages/assets/js/ace-extra.min.js"></script>
</head>
<body class="no-skin" style="background-color:white;overflow-x:hidden" >
	<form id="listForm" action="select_teacher.jhtml" method="get">
	<input  type="hidden" name="cid" value="${cid}"/>
	<div class="bar">		</div>
		<div class="searchDiv">
			<dl><dt>教师姓名：</dt>
	    			<dd>
	    			<input type="text" class="text" id="name" name="name" value ="${name!}"/>
	    		</dd></dl>
				<div class="search-btn"><a href="javascript:;" id="searchBtn" class="iconButton" style="margin-right:77px;">搜 索</a></div>
			</div>

	<table id="sample-table-1"  class="table table-striped table-bordered table-hover">   
			<tr>
				<th class="check no_drag">
					<input type="checkbox" id="selectAll"/>
				</th>
				<th>
					<a href="javascript:;" class="sort">教师名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort">职称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort">所属学院</a>
				</th>
			</tr>
			[#list data as teacher]
				<tr id="${teacher.id}" cname="${teacher.name!}" >
					<td>
						<input type="checkbox" name="ids" value="${teacher.id}"  />
					</td>
					<td>
						${teacher.name!}
					</td>
					<td>
						${teacher.professional!}
					</td>
					<td>
						${teacher.instituteName!}
					</td>
				</tr>
			[/#list]
	</table>
</form>
<script src="/bsxykq/pages/assets/js/jquery/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
			jQuery(function($) {
					$("#searchBtn").click(function(){
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
<script type="text/javascript">



//子框方法 返回选中的ids字符串
function childMethod(){
	    var rows = [];
        $("input[name='ids']:checkbox").each(function(){ 
            if(this.checked){
            	var row = $(this).closest("tr"); 
            	var id= row.attr("id");
            	var name= row.attr("cname");
            	
            	rows.push({id:id,name:name});
            	console.log(rows);
            	//改为不选中
            	$(this).attr("checked",false);
            }
        })
	return rows;
};

$().ready(function() {
	
});
</script>
</body>
</html>