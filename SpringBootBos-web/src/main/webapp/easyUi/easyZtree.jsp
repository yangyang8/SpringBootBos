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
<!-- 下面引入ztree的jar的一般操作 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<!-- 引入ztree样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css">
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
			<div title="系统管理">
				<!-- 在这个面板当中增加xztree的相关的操作 -->
				<ul id="ztree" name="ztree1" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						//在当前页面加载完成之后就会加载这个页面,这个使用的标准的Ztree的开发操作
						//进行准备数据
						var setting={};
						var zNode=[
						           {"name":"节点1","children":[
						                                       {"name":"二级节点树1","icon":"../js/ztree/img/diy/1_close.png"},
						                                       {"name":"二级节点树2"},
						                                       {"name":"二级节点树3"},
						                                       {"name":"二级节点树4"},
						                                     ]},
						           {"name":"节点2"},
						           {"name":"节点3"},
						           {"name":"节点4"}
						           ];
						
						//进行初始化树结点，也就是把结点增加到ul区域当中
						$.fn.zTree.init($("#ztree"), setting, zNode);
					});
				</script>
			</div>
			<div title="资源管理">
				<!-- 这个使用的是一个简单的ztree的操作方法 -->
				<ul id="ztree2" name="ztree2" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						/* 使用简单的ztree的操作的方法  */
						var setting={
								data: {
									simpleData: {
										enable: true //进行设置ztree支持简单ztree的开发操作
									}
								}
								
						};
						var znode=[
						           	{"id":"1","pId":"0","name":"简单节点1"},
						           	{"id":"2","pId":"1","name":"简单节点2"},
						           	{"id":"3","pId":"0","name":"简单节点3"},
						           	{"id":"4","pId":"3","name":"简单节点4"},
						           ];
						//进行初始ztree的操作
						$.fn.zTree.init($("#ztree2"), setting, znode);
					});
				</script>
				
			</div>
			<div title="权限管理">
				<!-- 主要是通过发送ajax请求来加载json数据，使用的还是简单的操作的方式 -->
				<ul id="ztree4" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						//定义ztree的配置信息,使用ztree支持简单操作方式
						var settin={
								data: {
									simpleData: {
										enable: true //进行设置ztree支持简单ztree的开发操作
									}
								}, //配置点击事件，回调事件
								callback: {
									onClick: function(event, treeId, treeNode){ //具体的回调方法
										//进行父结点不增加选项栏操作
										var page=treeNode.page;
									    if(page!=undefined){
									    	//然后进行去重的操作
									    	if($("#mytab").tabs("exists",treeNode.name)){
									    		//则选中
									    		$("#mytab").tabs("select",treeNode.name);
									    	}else{
												//说明是一个叶子结点，要增加到选项栏当中
												//进行给我们的tabs增加选项栏操作
												$("#mytab").tabs("add",
																	{
																		title:treeNode.name,
																		closable:true,
																		content:"<iframe frameborder='0'  width='100%' height='100%' src='https://www.baidu.com'></iframe>"
																	}
																);
									    	}

									    }
										//alert("你点击了"+treeNode.name);

												}
									}
						};
						
						var ajaxUrl="${pageContext.request.contextPath}/json/menu.json";
						//进行发送ajax请求
						//$.getJSON(ajaxUrl,{},function(data){
						/*$.get(ajaxUrl,{},function(data){
							//进行初始ztree的操作
							$.fn.zTree.init($("#ztree4"), settin, data);
						},"json");*/
						/* 
						$.ajax({
							  url: "test.html",
							  cache: false,
							  success: function(html){
							    $("#results").append(html);
							  }
							});
						*/
						$.ajax({
								url:ajaxUrl,
								async:true,
								type: "GET",
								success:function(data){
									$.fn.zTree.init($("#ztree4"), settin, data);
								}
								
							}
						);
						
					})
				</script>
			</div>
			
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