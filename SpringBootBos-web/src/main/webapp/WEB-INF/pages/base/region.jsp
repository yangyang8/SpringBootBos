<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>区域设置</title>
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
	<!-- 引入ocupload的基本的操作 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
<script type="text/javascript">
	function doAdd(){
		$('#addRegionWindow').window("open");
	}
	
	function doView(){
		alert("查看...");
	}
	
	function doDelete(){
		alert("删除...");
		//得到他们所有选择的数据，最快的方式是直接进行提交form表单的数据,给这个datagrid增加一个from表单
		
		//先得到选择的数据
		var rows=$("#grid").datagrid("getSelections");
		if(rows.length>0){
			$.messager.confirm("提示","你确定要删除所选的数据吗?",function(r){
				if(r){
					$("#deleteRegion").submit();
				}
			});
		}else{
			$.messager.alert("警告","请选择你要删除的数据","warning");
		}
	}
	
	//工具栏
	var toolbar = [ {
		id : 'button-edit',	
		text : '查看',
		iconCls : 'icon-edit',
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
	}, {
		id : 'button-import',
		text : '导入',
		iconCls : 'icon-redo'
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'province',
		title : '省',
		width : 120,
		align : 'center'
	}, {
		field : 'city',
		title : '市',
		width : 120,
		align : 'center'
	}, {
		field : 'district',
		title : '区',
		width : 120,
		align : 'center'
	}, {
		field : 'postcode',
		title : '邮编',
		width : 120,
		align : 'center'
	}, {
		field : 'shortcode',
		title : '简码',
		width : 120,
		align : 'center'
	}, {
		field : 'citycode',
		title : '城市编码',
		width : 200,
		align : 'center'
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
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "/region/pageList.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加、修改区域窗口
		$('#addRegionWindow').window({
	        title: '添加修改区域',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		//页面加载完成之后才调用我们的upload一键上传的功能
		$("#button-import").upload({
			name: 'loadRegionFile',
			action: '/region/loadRegionInfoFile.action'			
		});
		
		//增加保存功能的操
		$("#regsionSava").click(function(){
			//进行提交表单的基本的操作，如数据的校对操作
			var b=$("#addFromRegion").validatebox();
			if(b){
				$.messager.confirm("提示","你确定要录入这些数据吗?",function(r){
					
					if(r){
						//进行提交表单
						$("#addFromRegion").submit();
					}
					
				});
				
			}else{
				$.messager.alert("警告","你输入的数据的格式不正确!","warning");
			}
		});
		
		//在这个加载完成之后先设置这个页面不显示操作
		$("#editRegionWindow").window({
			title:"修改区域数据",
			modal:true,
			draggable:true,
			closed:true,
			closable:true,
			shadow:true,
			resizable:false,
			minimizable:true,
			maximizable:true,
			height: 400,
			width: 400
		});
		
		//修改页面的还原操作
		$("#backregsionedit").click(function(){
			alert("修改页面的还原操作");
		});
		
		//保存页面的清空操作
		$("#resetRegsionSava").click(function(){
			alert("保存页面的清空操作");
		});
		
		//修改页面的保存功能实现
		$("#regsionedit").click(function(){
			var b=$("#editFromRegion").validatebox();
			if(b){
				$.messager.confirm("提示","你确定要修改这些数据吗?",function(r){
					if(r){
						//进行提交表单
						$("#editFromRegion").submit();
					}
				});
				
			}else{
				$.messager.alert("警告","你输入的数据的格式不正确!","warning");
			}
			
		});
		
	});

	function doDblClickRow(rowIndex, rowData){
		//alert("双击表格数据...");
		//进行显示数据
		//得到选中的行
		//alert(rowData.id);
		//进行打开一个修改的页面
		$("#editRegionWindow").window("open");//打开一个修改页面
		//进行数据from表单载入数据
		$("#editFromRegion").form("load",rowData);
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<form action="/region/deleteRegion.action" id="deleteRegion">
		<div region="center" border="false">
	    		<table id="grid"></table>
		</div>
	</form>
	<div class="easyui-window" title="区域添加修改" id="addRegionWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="regsionSava" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
				<a id="resetRegsionSava" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >重置</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addFromRegion" action="/region/saveOrUpdateRegion.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">区域信息</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="province" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="city" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>区</td>
						<td><input type="text" name="district" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>邮编</td>
						<td><input type="text" name="postcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>简码</td>
						<td><input type="text" name="shortcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>城市编码</td>
						<td><input type="text" name="citycode" class="easyui-validatebox" required="true"/></td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	
	<!-- 进行页面的修改操作的页面如下 -->
	
	<div class="easyui-window" title="区域修改" id="editRegionWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="regsionedit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >修改</a>
				<a id="backregsionedit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >还原</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editFromRegion" action="/region/saveOrUpdateRegion.action" method="post">
				<input type="hidden" name="id" /> 
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">区域信息</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="province" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="city" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>区</td>
						<td><input type="text" name="district" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>邮编</td>
						<td><input type="text" name="postcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>简码</td>
						<td><input type="text" name="shortcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>城市编码</td>
						<td><input type="text" name="citycode" class="easyui-validatebox" required="true"/></td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	
</body>
</html>