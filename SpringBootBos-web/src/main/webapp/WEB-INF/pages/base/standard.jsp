<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收排标准</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd(){
		//alert("增加...");
		$('#name').val('');
		$('#minweight').numberbox('setValue', null); 	
		$('#maxweight').numberbox('setValue', null); 	
		$('#id').val('') ;
		
		$('#addStandardWindow').window("open");
	}
	
	function doView(){
		alert("查看...");
	}
	
	function doDelete(){
		// 判断是否选择表格数据 
		var rows = $('#grid').datagrid('getSelections');
		if(rows.length>0){
			// 提交删除form 
			//$('#delForm').submit();
			//给用户一个提示框操作
			$.messager.confirm("提示","你是不是要删除你选中的数据",function(r){
				 if(r){
					 var array=new Array();
					 //进行得到所有的选中的数据的列表
					 for(var i=0;i<rows.length;i++){
						 array.push(rows[i].id);
					 }
					 var ids=array.join(",");
					 //进行发送请求给服务器
					 location.href="${pageContext.request.contextPath}/standard/standard_delete.action?id="+ids;
				 }
			});
		}else{
			$.messager.alert("请先选中你要删除的数据");
		}
		
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '标准名称',
		width : 120,
		align : 'center'
	}, {
		field : 'minWeight',
		title : '最小重量',
		width : 120,
		align : 'center'
	}, {
		field : 'maxWeight',
		title : '最大重量',
		width : 120,
		align : 'center'
	}, {
		field : 'user.username',
		title : '操作人',
		width : 120,
		align : 'center',
		formatter : function(value,rowData, rowIndex){ 
			// value 表示匹配了当前属性的值
			// rowData 代表整行数据 
			// rowIndex 代表行号 
			if(rowData.user!=null){
				return rowData.user.username;
			}
		}
	}, {
		field : 'opDate',
		title : '操作时间',
		width : 160,
		align : 'center',
		formatter : function(value ,rowData, rowIndex){
		    var date = new Date(value);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();
            var h = date.getHours();
            var M = date.getMinutes();
            var s = date.getSeconds();
            return y + '-' +m + '-' + d+" "+h+":"+M+":"+s;
			return value;
		}
	}, {
		field : 'user.station',
		title : '操作单位',
		width : 200,
		align : 'center',
		formatter : function(value,rowData, rowIndex){ 
			//alert(value);
			// value 表示匹配了当前属性的值
			// rowData 代表整行数据 
			// rowIndex 代表行号 
			if(rowData.user!=null){
				return rowData.user.station;
			}
		}
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 收派标准数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [20,30,50],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/standard/list.action", 
			/* url : "${pageContext.request.contextPath}/json/standard.json",  */
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加收派标准窗口
		$('#addStandardWindow').window({
            title: '添加收派标准',
            width: 400,
            modal: true,
            shadow: true,
            closed: true,
            height: 400,
            resizable:false
        });
		
		$("#editStandardWindow").window({
			title: '修改收派标准',
            width: 400,
            modal: true,
            shadow: true,
            closed: true,
            height: 400,
            resizable:false
		});
		
		
		
		$("#edit").click(function(){
				if($("#editstandardForm").form("validate")){
					$.messager.confirm("提示","你确定要修改数据吗?",function(r){
						if(r){
							//进行修改请求，修改数据
							$("#editstandardForm").submit();
						}
					});
				}else{
					$.messager.alert('警告','表单存在非法数据，请重新输入','warning');
				}
		});
		
	});
	
	// 修改数据 
	function doDblClickRow(rowIndex, rowData){ // rowIndex行号，rowData 双击行数据
		// form回显
		/* $('#name').val(rowData.name);
		$('#minweight').numberbox('setValue', rowData.minweight); 	
		$('#maxweight').numberbox('setValue', rowData.maxweight); 	
		$('#id').val(rowData.id) ; */
		
		alert(rowData.opDate);
		// 弹出修改窗口
		$('#editStandardWindow').window('open');
		
		$("#editStandardWindow").form("load",rowData);
	}
	
	// 点击保存按钮，提交标准form
	function commitStandardForm(){
		// 先判断form 是否通过校验，如果通过 ，提交表单 
		 if($('#standardForm').form('validate')){// 执行EasyUI 校验方法
			// 通过校验 
			$('#standardForm').submit();
		}else{
			// 没通过校验
			$.messager.alert('警告','表单存在非法数据，请重新输入','warning');
		} 
		
		/* if($('#standardForm').form('validate')){
			//弹出确认框
			$.messager.confirm("提示","你确定要")
		}else{
			// 没通过校验
			$.messager.alert('警告','表单存在非法数据，请重新输入','warning');
		} */
	}
	
	
		
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
    <form id="delForm" action="${pageContext.request.contextPath }/standard_delBatch.action" method="post">
	    <div region="center" border="false">
	    		<table id="grid"></table>
		</div>
    </form>
	
	<div class="easyui-window" title="添加收派标准" id="addStandardWindow" collapsible="false" minimizable="false" maximizable="false" style="top:100px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="javascript:commitStandardForm();" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="standardForm" action="${pageContext.request.contextPath }/standard/standard_save.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派标准信息
							<input type="hidden" name="id" id="id" />
						</td>
					</tr>
					<tr>
						<td>标准名称</td>
						<td><input id="name" name="name" type="text" class="easyui-validatebox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>最小重量</td>
						<td><input id="minWeight" name="minWeight" type="text" class="easyui-numberbox"  /></td>
					</tr>
					<tr>
						<td>最大重量</td>
						<td><input id="maxWeight" name="maxWeight" type="text" class="easyui-numberbox" /></td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	
	<!-- 修改窗口的操作 -->
	<div class="easyui-window" title="修改收派标准" id="editStandardWindow" collapsible="false" minimizable="false" maximizable="false" style="top:100px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editstandardForm" action="${pageContext.request.contextPath }/standard/standard_edit.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派标准信息
							<input type="hidden" name="id" id="id" />
						</td>
					</tr>
					<tr>
						<td>标准名称</td>
						<td><input id="name" name="name" type="text" class="easyui-validatebox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>最小重量</td>
						<td><input id="minWeight" name="minWeight" type="text" class="easyui-numberbox"  /></td>
					</tr>
					<tr>
						<td>最大重量</td>
						<td><input id="maxWeight" name="maxWeight" type="text" class="easyui-numberbox" /></td>
					</tr>
					
					<tr>
						<td>操作日期</td>
						<td><input id="opDate" name="opDate" type="text" class="easyui-calendar" /></td>
					</tr>			   
					</table>
			</form>
		</div>
	</div>
</body>
</html>