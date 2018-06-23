<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入shiro的权限相关的标签开发，这个权限的标签相关的开发主要应用在前端控制 -->
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
<script type="text/javascript">
	function doAdd(){
		//alert("增加...");
		$('#addStaffWindow').window("open");  //事件发生时调用页面显示方法显示
	}
	
	function doView(){
		alert("查看...");
	}
	
	function doDelete(){
		//alert("删除数据");
		//getSelections none 返回所有选定的行,当没有记录被选中,我将返回空数组。 
		//获取我们用户选中的删除快递员的数据
		var rows=$("#grid").datagrid("getSelections"); //返回的是一个数组对象
		
		if(rows.length>0){
			//给用户一个提示操作
			$.messager.confirm("提示","你确定删除选中的快递员的信息吗?",function(r){
				if(r){
					//得到选中的数据的id
					var array=new Array();
					for(var i=0;i<rows.length;i++){
						array.push(rows[i].id);
					}
				/* 	  type: "POST",
					   url: "some.php",
					   data: "name=John&location=Boston",
					   success: function(msg){
					     alert( "Data Saved: " + msg ); */

					//向服务器 发送ajax请求，请服务器逻辑删除快递员数据
				/* 	$.ajax(
							{
								type: "POST",
								url: "/staff/deleteStaffByLogic.action",
								data: "ids="+array,
								 async:true, 
								async:false, //不管是同步发送ajax请求，还是异步发送ajax请求，他都不刷新界面，所以不能发送ajax请求
								success:function(data){
									//给用户一个提示，删除成功
										$.messager.show({
												title:'提示',  	
												msg:'删除快递员成功',  	
												timeout:5000,  	
												showType:'slide' 	
										}
									)
								},
							    error:function(data){
							    	alert("删除失败");
							    }
							}) */
							
					//alert("删除成功")
					//把数组对象转成字符串
					var ids=array.join(","); //表示一个的取出数组当中的数据，然后在他们的中间加入seperator,当中最后的一个除外
					//所以使用原生的访问请求操作,是一个get请求
					location.href="/staff/OperatorStaffByLogic.action?ids="+ids+"&deltag="+"1"; //会自动刷新界面，重新加载数据  1表示逻辑删除
				}
			});
		}else{
			$.messager.alert("提示","请选中你要删除的快递员的信息","info");
		}
		//alert(rows.length);
	}
	
	function doRestore(){
		//alert("将取派员还原...");
		//得到from表单当中选中的数据
		var rows=$("#grid").datagrid("getSelections");
		if(rows.length){
			
			//给用户一个确认提示框
			$.messager.confirm("提示","你确定要还原快递员的基本数据吗?",function(r){
					if(r){
						var array=new Array();
						for(var i=0;i<rows.length;i++){
							array.push(rows[i].id);
						}
						var ids=array.join(",");
						location.href="/staff/OperatorStaffByLogic.action?ids="+ids+"&deltag="+"0"; //会自动刷新界面，重新加载数据 0表示启用
					}
			})
		}else{
			$.messager.alert("提示","请选择你要操作的快递员数据" ,"warning");
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
	}, 
	//这个shiro标签可以使用js,html,jsp当中,表示的是如果有staff-delete标签则会如果他标签内的数据
	<shiro:hasPermission name="staff-delete">
	{
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	},
	</shiro:hasPermission>
	{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [ {
		field : "id",
		checkbox : true,
	},{
		field : "username",
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : "telephone",
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : "haspda",
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : "deltag",
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已作废";
			}
		}
	}, {
		field : "standard",
		title : '取派标准',
		width : 120,
		align : 'center',
		formatter: function(value,row,index){
		     if(row.standard!=null){
		         return row.standard.name;
		     } else {
		        return "无标准";
		     }  			
		} 
	}, {
		field : "station",
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true, 
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "/staff/list.action",
			method:"post",
			idField : 'id',  //这个属性要加上，因为这个属性是表示把那个字段当中是唯一的标识，然后传给用户
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口，实现取派员窗口页面加载完成之后显示
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		/* 给我们的保存增加响应事件 */
		$("#save").click(function(){
			//进行校验我们的表单中的数据
			var b=$("#addStaffForm").form("validate");
				if(b){
					//进行提交表单的请求
					$("#addStaffForm").submit();
				}
		});
		
		/* 增加重置功能 */
		$("#reset").click(function(){
				//进行调用easy-ui当中提供的操作的方法进行重置操作
				$("#editStaffForm").form("reset");
		
		});
		
		/* 清空功能操作 */
		$("#clear").click(function(){
				//进行调用easy-ui当中提供的操作的方法进行清空操作
				$("#addStaffForm").form("clear");
		
		});
		
		//定义校验规则
		var reg=/^1[3|4|5|7|8][0-9]{9}$/;
		/* 定义一个手机号码合理性的校验操作  */
		$.extend($.fn.validatebox.defaults.rules, { 

				//telephone为我们定义的校验规则的名字，用来下面的引用他的唯一标识
				telephone: { 
					validator:function(value,param){ 
						return reg.test(value); 
					}, 
					message: '你输入的手机号码不合理' 
				} 

			}); 
		
		//在页面加载完成之后执行
		//实现让修改取派员窗口页面先不显示，等到有事件触发时才显示
		$('#editStaffWindow').window({
		        title: '修改取派员',  //取派员窗口页面的标题
		        width: 400,  
		        modal: true,      //覆盖，也就是让下面页面失去焦点
		        shadow: true,	
		        closed: true,     //页面加载完成之后进行隐藏
		        height: 400,
		        resizable:false		//让这个页面不能进行改变大小
		    });
		
		
		$("#edit").click(function(){
			//alert("xxxx");
			//进行取出修改表单
			var r=$("#editStaffForm").form("validate");
			if(r){
				//输入数据合理则
				//发送提交表单请求
				$("#editStaffForm").submit();
			}else{
				//给用户一个提示
				$.messager.alert("提示","你输入的修改的数据格式不正确!","error");
			}
			
		});
		
		$.ajax({
			  type: "GET",
			  url: "/staff/getStandards.action",
			  dataType:"json",  //一定要加上这个，不会转成字符串的操作
			  success: function(msg){
				  //alert(msg.length);
				  for(var i=0;i<msg.length;i++){
					  var options ="<option value='"+msg[i].id+"'>"+msg[i].name+"</option>"
					 
					  $("#selectStandards").append(options);
				  }
				 
			  }

			});
		
		
	});
	


	//注册我们单击行的点击事件，主要是是用来进行修改我们的单行的事件
	function doDblClickRow(rowIndex, rowData){
		//alert("双击表格数据...");
		//alert(rowIndex+"::"+rowData.username);
		$('#editStaffWindow').window("open");  //事件发生时调用页面显示方法显示
		//$("#sid").val(rowData.standard.name);
		//进行回显数据，也就是选中的行的数据显示在指定的form表单当中，这里不用以前的方法，而是使用easy-ui的from的方法来操作
		$("#editStaffWindow").form("load",rowData);//form表单数据进行重要加载，然后根据传入参数的json数据，根据他的名字显示在from表单当中
		//对于复杂的数据的类型只能是自己的手动的设置他的值了
		//alert(rowData.standard.name);
		$("#ssid").combobox("select",rowData.standard.name);
		//$("#station").val(rowData.station);
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
				<a id="clear" icon="icon-cancel" href="#" class="easyui-linkbutton" plain="true" >清空</a>
			</div>
		</div>
		<%-- 
		
		<shiro:authenticated></shiro:authenticated> 标签表示的是先来看这个用户是否已经登录，如果登录了才会进行标签内的相关操作，但是我们的
		在登录之后才会看到这些了，所以这个标签的作用不大
		
		--%>
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addStaffForm" action="/staff/addStaff.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="username" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<!-- 做一个手机号码合理性的扩展校验 -->
						<td><input type="text" name="telephone" data-options="validType:'telephone'" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<!-- <input type="text" name="standard.id" class="easyui-combobox" 
							 data-options="valueField:'id',textField:'name',url:'/staff/getStandards.action'"/>   -->
							 <!-- <select id="selectStandards"></select> -->
							<input type="text" name="sid" class="easyui-combobox" 
							data-options="valueField:'id',textField:'name',url:'/staff/getStandards.action'" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
	
	<!-- 
		下面是实现我们的快递员的修改功能页面代码
	 -->
	 <div class="easyui-window" title="对收派员进行修改" id="editStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
				<a id="reset" icon="icon-reload" href="#" class="easyui-linkbutton" plain="true" >重置</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editStaffForm" action="/staff/editStaff.action" method="post">
				<input type="hidden" name="id"><!-- 增加隐藏域，封装我们的Id的数据 -->
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="username" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<!-- 做一个手机号码合理性的扩展校验 -->
						<td><input type="text" name="telephone" data-options="validType:'telephone'" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" id="station" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							 <input type="text" id="ssid" name="sid" class="easyui-combobox" 
							data-options="valueField:'id',textField:'name',url:'/staff/getStandards.action'" required="true"/>   
							<!-- <select id="selectStandards"></select> -->
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>	