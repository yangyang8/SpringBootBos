<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程定义列表</title>
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
    
/* 		//定义列
		var columns = [ [ {
			field : 'id',
			title:"流程编号"
		},{
			field : 'name',
			title : '流程名称',
			width : 120,
			align : 'center'
		}, {
			field : 'key',
			title : '流程名称',
			width : 120,
			align : 'center'
		}, {
			field : 'version',
			title : '版本号',
			width : 120,
			align : 'center'
		}, {
			field : 'viewpng',
			title : '查看流程图',
			width : 120,
			align : 'center'
		} ] ]; */

	$(function(){
		$("#grid").datagrid({
			striped : true,
			rownumbers : true,
			singleSelect : true,
			fitColumns : true,
			toolbar : [
				{
					id : 'deploy',
					text : '发布新流程',
					iconCls : 'icon-add',
					handler : function(){
						location.href = "${pageContext.request.contextPath}/page_workflow_processdefinitionDeploy.action";
					}
				}
			]
		});
	});
</script>	
</head>
<body class="easyui-layout">
  <div region="center" >
  	<table id="grid" class="easyui-datagrid">  
  		<thead>
  			<tr>
  				<th data-options="field:'id'" width="120">流程编号</th>
  				<th data-options="field:'name'" width="200">流程名称</th>
  				<th data-options="field:'key'" width="100">流程名称</th>
  				<th data-options="field:'version'" width="80">版本号</th>
  				<th data-options="field:'viewpng'" width="200">查看流程图</th>
  			</tr>
  		</thead>
  		<tbody>
  			<c:forEach items="${processDefinitionsList}" var="processDefinition">
	  				<!-- 在循环过程中 ，将  processDefinition 对象，同时放入 root 和 map 中-->
	  				<tr>
	  					<td>
	  						<!-- <s:property value="id"/> 从root找
	  						<s:property value="#processDefinition.id"/> 从map找 -->
	  						<input type="hidden" name="id" value="${processDefinition.id}">
	  						<span>${processDefinition.id}</span>
	  					</td>
	  					<td><span>${processDefinition.name}</span></td>
	  					<td><span>${processDefinition.key}</span></td>
	  					<td><span>${processDefinition.version}</span></td>
	  					<td>
	  						
	  						<%-- <c:if test="imageResourceName!=null">
		  						<s:a action="processdefinition_viewpng" namespace="/" cssClass="easyui-linkbutton" data-options="iconCls:'icon-search'">查看流程图
		  							<s:param name="deploymentId" value="deploymentId"></s:param>
		  							<s:param name="imageResourceName" value="imageResourceName"></s:param>
		  						</s:a>
	  						</c:if> --%>
	  						<c:if test="${processDefinition.imageName!=null}">
		  						<a href="/process/processdefinition_viewpng.action?deploymentId=${processDefinition.deploymentId}&imageResourceName=${processDefinition.imageName}" 
		  							class="easyui-linkbutton" 
		  						    data-options="iconCls:'icon-search'">查看流程图</a>
	  						</c:if>
	  					</td>
	  				</tr>
  			
  			</c:forEach>
  		</tbody>
  	</table>
  </div>
</body>
</html>