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
<script type="text/javascript">
	$(function(){
		//页面加载完成之后进行执行                                  图标 error x,question ? ,info i,warning !.
				//消息提示框
				//$.messager.alert("标题","内容","info");
		//消息确认框
		/* $.messager.confirm("标题","你确定要退出系统吗?",function(r){
			//r 为消息的点击选择，也就是是否选择了确定，还是取消操作
			alert(r);
		}); */
		
		//消息显示框的操作，这个是在我们系统登录时，给我们用户一个显示欢迎回来的消息框,这个的参数是一个json串
		$.messager.show(
				{title:"消息提示",msg:"欢迎回来",timeout:5000,showType:'slide' }); //表示以滑动的方式在页面当中显示5s
		
	});
</script>
</head>
<body>

</body>
</html>