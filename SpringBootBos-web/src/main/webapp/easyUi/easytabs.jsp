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
</head>
<body class="easyui-layout">
<!-- 进行划分区域操作,最多只能划分为5个,也就是北，南，西，东，中 -->
	<div style="height:100px" data-options="region:'north'" title="天龙国际物流系统">区域北部</div>
	<div title="菜单" style="width:200px" data-options="region:'west'">
		<div class="easyui-accordion" data-options="fit:true">
			<!-- 折叠面板的操作  -->
			<div title="用户管理">
				<!-- 进行动态给我们的tabs当中增加选项操作 -->
				<a id="useradd" href="#" class="easyui-linkbutton">增加用户</a>
				<script type="text/javascript">
					<!-- 表示页面加载完之后进行如下的初始化的操作 -->
					$(function(){
						//alert("111");
						$("#useradd").click(function(){
							var b=$("#mytab").tabs("exists","增加用户");
							if(b){
								$("#mytab").tabs("select","增加用户")
							}else{
								$("#mytab").tabs("add",{
									title:"增加用户",
									closable:true,
									iconCls:"icon-cut",
									content:"<iframe frameborder='0'  width='1600px' height='900px' src='https://www.baidu.com'></iframe>"
								})
							}
						});
					});
				</script>
			</div>
			<div title="系统管理">面板二</div>
			<div title="资源管理">面板三</div>
		</div>
	</div>
	<div data-options="region:'center'">
	    <!-- 进行表格的开发操作 -->
	    <div id="mytab" class="easyui-tabs" data-options="fit:true">
	    	<!-- 折叠面板的操作  -->
			<div data-options="closable:true,iconCls:'icon-edit'" title="用户管理" >面板一</div>
			<div data-options="iconCls:'icon-cut'" title="系统管理">面板二</div>
	    </div>
	
	</div>
	<div style="width:100px" data-options="region:'east'">区域东部</div>
	<div style="height:50px" data-options="region:'south'">区域南部</div>
</body>
</html>