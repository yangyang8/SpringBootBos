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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
<script type="text/javascript">
	
	$(function(){
		
		$("#myupload").upload({
			name: 'testFile',
			action: 'bbb'
			
		});
	});
	
</script>
</head>
<body>
	<!-- 进行演示我们的文件上传的基本的操作 -->
	<form action="xxxx" enctype="multipart/form-data">
		<input type="file" name="浏览文件"> </br>
		<input type="submit" value="文件上传"> 
	</form>
	</br>
	<!-- 下面开始操作高级使用jquery 的ocupload的工具类来操作 -->
	<input type="button" id="myupload" value="上传"> 
</body>
</html>