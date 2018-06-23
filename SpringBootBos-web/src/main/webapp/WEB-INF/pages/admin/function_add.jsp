<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

<script type="text/javascript" src="${pageContext.request.contextPath }/js/utils.js"></script>
<script type="text/javascript">
	$(function(){
		// 点击保存
	 	$('#save').click(function(){
			//location.href='${pageContext.request.contextPath}/page_admin_function.action';
			//先进行校验操作
			//var r=$("#functionForm").form("validatebox");
	 		save("functionForm");
		}); 
		
	});
</script>	
</head>
<body class="easyui-layout">
<div data-options="region:'north'">
	<div class="datagrid-toolbar">
		<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
	</div>
</div>
<div data-options="region:'center'">
	<form id="functionForm" method="post" action="/permission/saveOrUpdatePermission.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">功能权限信息</td>
					</tr>
					<tr>
						<td width="200">关键字</td>
						<td>
							<input type="text" name="code" class="easyui-validatebox" data-options="required:true" />						
						</td>
					</tr>
					<tr>
						<td>名称</td>
						<td><input type="text" name="name" class="easyui-validatebox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>访问路径</td>
						<td><input type="text" name="page"  /></td>
					</tr>
					<tr>
						<td>是否生成菜单</td>
						<td>
							<select name="isGenernatemenu" class="easyui-combobox">
								<option value="0">不生成</option>
								<option value="1">生成</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>优先级</td>
						<td>
							<input type="text" name="zindex" class="easyui-numberbox" data-options="required:true" />
						</td>
					</tr>
					<tr>
						<td>父功能点</td>
						<td>
							<!-- <input name="parentPermission.id" class="easyui-combobox" 
							data-options="valueField:'id',textField:'name',url:'/permission/ajaxAllTPermission.action'"/>
								下面变成一个下拉树的形式展现出我们的权限树，这样子可以生动的展现出来权限之间的关系数据
							 -->
							<input name="parentPermission.id" class="easyui-combotree" style="width:250px;" 
							data-options="valueField:'id',textField:'name',url:'/permission/ajaxAllTPermission.action'"/>
						</td>
					</tr>
					<tr>
						<td>描述</td>
						<td>
							<textarea name="description" rows="4" cols="60"></textarea>
						</td>
					</tr>
					</table>
			</form>
</div>
</body>
</html>