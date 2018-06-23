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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<!-- 使用datagri的三种使用方式 -->
	<!-- 方式一，将静态HTML渲染成datagri样式，如果 -->
	<table class="easyui-datagrid">
		<thead>
			<tr>
				<!-- 在easyUi的 datagri当中如果要出现数据，则要加上data-option="field"属性-->
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'age'">年龄</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>001</td>
				<td>李离</td>
				<td>23</td>
			</tr>
			<tr>
				<td>002</td>
				<td>杨是</td>
				<td>45</td>
			</tr>
		</tbody>
	</table>
	
	<hr/>
	<!-- 下面使用方式二来开发我们datagri，第二方式是通过发送ajax来获取json数据 -->
	<table data-options="url:'${pageContext.request.contextPath }/json/datagri.json'" class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'age'">年龄</th>
			</tr>
		</thead>
	
	</table>
	
	<hr/>
	
	<table id="datagri3">
		
	</table>
	<script type="text/javascript">
		$(function(){
			/* 使用easyui提供的api来操作datagri */
			$("#datagri3").datagrid({
				/* 先定义标题 ,一个json表示一个列的标题数据，但是不管是那种方式都在标题头加上列加上field的这个属性 */
				columns:[[
					       {title:"编号",field:'id',checkbox:true,width:250,align:'center'},
					       {title:"姓名",field:'name',width:250,align:'center'},
					       {title:"年龄",field:'age',width:250,align:'center'},
					       {title:"地址",field:'address',width:250,align:'center'}
				      ]],
				      url:"${pageContext.request.contextPath }/json/datagri.json",
				      method:"get",//发送ajax请求的方式
				      singleSelect:true ,//让多选框变成单选框
				      rownumbers:true , //增加一行角标
				      toolbar:[  //增加菜单栏选项
				               {text:"增加",iconCls:'icon-add',
				    	  		handler:function(){  //给这个增加按键绑定事件
				            	   alert("给这个增加按键绑定事件");
				               }},
				               {text:"修改",iconCls:'icon-edit'},
				               {text:"删除",iconCls:'icon-remove'},
				               {text:"查看",iconCls:'icon-search'}
				               ],
				      pagination:true,     //设置分页栏操作
				      pagePosition:'bottom'   //在页底显示分页栏
				        
			});
		});
	</script>
</body>
</html>