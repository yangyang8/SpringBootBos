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
<script type="text/javascript">
		var goldData=0; //用来控件的全局变量
		function toAdd(){
			//alert("增加一行数据....")
			//增加一行之后进行开始编辑
			toSave();
			//给这个按键增加一行
			$("#datagri3").datagrid("insertRow",
			  {
				index: goldData,	//行号从0开始，表示的是第一行
			  	row: {
			  		//增加一行数据
			  		/* id:"4343624543",
			  		name: '杨王',
			  		age: 30,
			  		address: '广东理工学院' */
			  	}
			 });
			
			$("#datagri3").datagrid("beginEdit",goldData);
			//$("#datagri3").datagrid('beginEdit',goldData);
		}
		
		function dbClickUpdate(rowIndex, rowData){
			toSave();
			goldData=rowIndex;
			alert(goldData);
			$("#datagri3").datagrid('beginEdit',goldData);
		}
		
		function toSave(){
			$("#datagri3").datagrid("endEdit",goldData);
		}
		
		//得到选中的行的数据
		function updateDate(){
			//$("#datagri3").datagrid("beginEdit",goldData);
			var rows=$("#datagri3").datagrid("getSelections");
			if(rows.length==1){
				//rows[0]  getRowIndex 得到行的数据
				var index=$("#datagri3").datagrid("getRowIndex",rows[0]);
				alert(index);
				goldData=index;
				$("#datagri3").datagrid('beginEdit',goldData);
			}else{
				//给用户一个提示
				$.messager.alert("提示","你只能选中一行数据?","warning");
			}
		} 
		
		$(function(){
			/* 使用easyui提供的api来操作datagri */
			$("#datagri3").datagrid({
				/* 先定义标题 ,一个json表示一个列的标题数据，但是不管是那种方式都在标题头加上列加上field的这个属性 */
				columns:[[
					       {title:"编号",field:'id',checkbox:true,width:250,align:'center'},//进行设置那些列可以进行编辑的,这个不能加编辑属性editor
					       {title:"姓名",field:'name',width:250,align:'center',
					    			editor :{
					    				type : 'validatebox',
					    				options : {
					    					required: true
					    				}
					    			}},
					       {title:"年龄",field:'age',width:250,align:'center',
					    	   editor:{type:'validatebox',options:{}}},
					       {title:"地址",field:'address',width:250,align:'center',
					    	   editor:{type:'validatebox',options:{}}}
				      ]],
				      url:"${pageContext.request.contextPath }/json/datagri.json",
				      method:"get",//发送ajax请求的方式
				      singleSelect:true ,//让多选框变成单选框
				      rownumbers:true , //增加一行角标
				      toolbar:[  //增加菜单栏选项
				               {text:"增加",iconCls:'icon-add',
				    	  		handler:toAdd},
				               {text:"修改",iconCls:'icon-edit',handler:updateDate},
				               {text:"保存",iconCls:'icon-save',handler:toSave},
				               {text:"删除",iconCls:'icon-remove'},
				               {text:"查看",iconCls:'icon-search'}
				               ],
				      pagination:true,     //设置分页栏操作
				      pagePosition:'bottom',  //在页底显示分页栏
				      onDblClickRow:dbClickUpdate,
				      fit:true
				        
			});
		});
	</script>

</head>
<body>
	<table id="datagri3"></table>
	</body>
</html>