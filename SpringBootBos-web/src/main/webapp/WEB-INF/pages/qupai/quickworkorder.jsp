<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作单快速录入</title>
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
	var editIndex ;
	
	function doAdd(){
		if(editIndex != undefined){
			$("#grid").datagrid('endEdit',editIndex);
			//doSave();
			//doAfterEdit
		}
		if(editIndex==undefined){
			//alert("快速添加电子单...");
			$("#grid").datagrid('insertRow',{
				index : 0,
				row : {}
			});
			$("#grid").datagrid('beginEdit',0);
			editIndex = 0;
		}
	}
	
	function doSave(){
		alert("msgg");
		//保存数据，向服务器发送数据
		//var editors=$("#grid").datagrid("getChanges",{type:"validatebox"});
		//var editors2=$("#grid").datagrid("acceptChanges");
		$("#grid").datagrid('endEdit',editIndex);
		//console.info(editors);
		//console.info(editors2);
		//alert(editors);$
		//$("")
	}
	
    function doAfterEdit(rowIndex, rowData, changes){
		//console.info(rowData);
		//console.info(changes);
		editIndex = undefined;
		//给服务器发送修改的数据
		$.ajax({
			   type: "POST",
			   data:rowData,
			   dataType:"json",
			   url: "/workManager/saveWorkManagerDate.action",
			   success: function(msg){
			     //alert( "Data Saved: " + msg );
			     alert(msg+"成功的分支");
			     //alert(msg.success+"成功的分支...")
			     if(msg == 1){
			    	 alert("成功了...")
			     }else{
			    	 $.messager.alert("警告","编号输入冲突?","error");
					 $("#grid").datagrid('beginEdit',rowIndex);
			     }
			   },
			   error:function(msg){
				   alert(msg+"失败了....");
				   $.messager.alert("警告","编号输入冲突?","error");
				   $("#grid").datagrid('beginEdit',rowIndex);
			   }
			});
    }
    
	function doCancel(){
		if(editIndex!=undefined){
			$("#grid").datagrid('cancelEdit',editIndex);
			if($('#grid').datagrid('getRows')[editIndex].id == undefined){
				$("#grid").datagrid('deleteRow',editIndex);
			}
			editIndex = undefined;
		}
	}
	
	//工具栏
	var toolbar = [ {
		id : 'button-add',	
		text : '新增一行',
		iconCls : 'icon-edit',
		handler : doAdd
	}, {
		id : 'button-cancel',
		text : '取消编辑',
		iconCls : 'icon-cancel',
		handler : doCancel
	}, {
		id : 'button-save',
		text : '保存',
		iconCls : 'icon-save',
		handler : doSave
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		title : '工作单号',
		width : 120,
		align : 'center',
		editor :{
			type : 'validatebox',
			options : {
				required: true
			}
		}
	}, {
		field : 'arriveCity',
		title : '到达地',
		width : 120,
		align : 'center',
		editor :{
			type : 'validatebox',
			options : {
				required: true
			}
		}
	},{
		field : 'product',
		title : '产品',
		width : 120,
		align : 'center',
		editor :{
			type : 'validatebox',
			options : {
				required: true
			}
		}
	}, {
		field : 'num',
		title : '件数',
		width : 120,
		align : 'center',
		editor :{
			type : 'numberbox',
			options : {
				required: true
			}
		}
	}, {
		field : 'weight',
		title : '重量',
		width : 120,
		align : 'center',
		editor :{
			type : 'validatebox',
			options : {
				required: true
			}
		}
	}, {
		field : 'floadReqr',
		title : '配载要求',
		width : 220,
		align : 'center',
		editor :{
			type : 'validatebox',
			options : {
				required: true
			}
		}
	}] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 收派标准数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url :  "/workManager/pageListWorkManager.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow,
			onAfterEdit : doAfterEdit
		});
	});

	function doDblClickRow(rowIndex, rowData){
		//alert("双击表格数据...");
		$('#grid').datagrid('endEdit',editIndex);
		doSave();//进行保存数据
		//console.info(rowIndex);
		$('#grid').datagrid('beginEdit',rowIndex);
		editIndex = rowIndex;
	}
</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
</body>
</html>