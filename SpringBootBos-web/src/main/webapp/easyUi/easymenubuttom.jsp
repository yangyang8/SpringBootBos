<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>layout的操作</title>
<!-- 引入jquery easyui和样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<!-- 引入中文的本地化的操作  -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<!-- 菜单按钮的操作 -->
	<a class="easyui-menubutton" menu="#mm" data-options="iconCls:'icon-help'" >控制面板</a>
	
	<div id="mm">
		<!-- 每一个下拉选项都是一个Div -->
		<div id="update" data-options="iconCls:'icon-edit'">修改密码</div>
		<script type="text/javascript">
			$(function(){
				$("#update").click(function(){
					alert("你点击了修改密码...")
				});
			});
		</script>
		<div>退出系统</div>
		<div class="menu-sep"></div>  <!-- menu-sep为分割线样式 -->
		<div>联系管理员</div>
	</div>
</body>
</html>