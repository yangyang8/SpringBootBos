<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理定区/调度排班</title>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/utils.js"></script>

<script type="text/javascript">
	function doAdd(){
		$('#addDecidedzoneWindow').window("open");
	}
	
	function doEdit(){
		alert("修改...");
	}
	
	function doDelete(){
		alert("删除...");
	}
	
	function doSearch(){
		$('#searchWindow').window("open");
	}
	
	//关联数据操作
	function doAssociations(){
		//先进行得到选中的定区数据
		var rows=$("#grid").datagrid("getSelections");
		var lsize=rows.length;
		if(lsize !=1){
			//给用户一个提示，只能选中一行数据
			$.messager.alert("友情提示","你要选中一行数据进行操作","warning");
		}else{
			$('#customerWindow').window('open');
			//得到选中的数据的Id
			var decidedzoneId=rows[0].id;
			
			/* 清空数据 */
			$("#noassociationSelect").empty();
			$("#associationSelect").empty();
			/* 进行关联客户的数据 
				页面加载完后就获取没有关联客户的数据和已经关联客户的数据
			*/
			$.ajax({
				   type: "POST",
				   url: "/decidedzone/associationCustomer.action",
				   async: true,
				   dataType:"json",
				   data:"decidedzoneId="+decidedzoneId,
				   success:function(msg){
				       for(var i=0;i<msg[0].length;i++){
				    	  var id=msg[0][i].id;
				    	  var name=msg[0][i].name;
				    	  //alert(name);
				    	  var telephone=msg[0][i].telephone;
				    	  name=name+" ("+telephone+")";
				    	  //进行把数据设置到左测
				    	  $("#associationSelect").append("<option value='"+id+"'>"+name+"</option>");
				    	  
				      }
				   }
				});
			
		//$("#noassociationSelect")
			/* 查询所有的没有被关联的数据  */
			$.ajax({
				   type: "POST",
				   url: "/decidedzone/withoutAssociationCustomer.action",
				   async: true,
				   dataType:"json",
				   success:function(msg){
				    //alert(msg);
				    //alert(msg[0].length)
				      //进行遍历数据
				      for(var i=0;i<msg[0].length;i++){
				    	  var id=msg[0][i].id;
				    	  var name=msg[0][i].name;
				    	  //alert(name);
				    	  var telephone=msg[0][i].telephone;
				    	  name=name+" ("+telephone+")";
				    	  //进行把数据设置到左测
				    	  $("#noassociationSelect").append("<option value='"+id+"'>"+name+"</option>");
				    	  
				      }
				   }
				});
			
		}
		
		////////
	}
	
	//工具栏
	var toolbar = [ {
		id : 'button-search',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doSearch
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-edit',	
		text : '修改',
		iconCls : 'icon-edit',
		handler : doEdit
	},{
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-association',
		text : '关联客户',
		iconCls : 'icon-sum',
		handler : doAssociations
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		title : '定区编号',
		width : 120,
		align : 'center',
		checkbox:true
	},{
		field : 'username',
		title : '定区名称',
		width : 120,
		align : 'center'
	}, {
		field : 'staff.username',
		title : '负责人',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.staff.username;
		}
	}, {
		field : 'staff.telephone',
		title : '联系电话',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.staff.telephone;
		}
	}, {
		field : 'staff.station',
		title : '所属公司',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.staff.station;
		}
	} ] ];
	
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
			url : "/decidedzone/pageList.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		//进行设置左移和右移的数据
		
		//进行设置右移数据
		$("#toRight").click(function(){
			//得到选中的数据下拉单当中的数据
			
			//然后得到右侧的数据 id,name,telephone
			//associationSelect
			$("#associationSelect").append($("#noassociationSelect option:selected"));
			
		});
		
		//进行设置左移数据
		$("#toLeft").click(function(){
			//得到选中的数据下拉单当中的数据
			//然后得到右侧的数据 id,name,telephone
			//associationSelect
			$("#noassociationSelect").append($("#associationSelect option:selected"));
			
		});
		
		
		//进行点击关联数据 ///////
		$("#associationBtn").click(function(){
			//alert("xxxxxx");
			var rows=$("#grid").datagrid("getSelections");
			
			//先设置我们的定区的Id
			var id=rows[0].id;
			$("#customerDecidedZoneId").val(id);
			//得到选中的分区的数据分区的数据
			$("#associationSelect option").attr("selected","selected");
			alert("xxxxxxxxxx");
			$("#customerForm").submit();
		});
		
		// 添加、修改定区
		$('#addDecidedzoneWindow').window({
	        title: '添加修改定区',
	        width: 600,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		
		// 增加关联客户的数据
		$('#customerWindow').window({
	        title: '关联客户的数据',
	        width: 600,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		
		
		// 查询定区
		$('#searchWindow').window({
	        title: '查询定区',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		$("#btn").click(function(){
			alert("执行查询...");
		});
		
		//增加分区数据
		$("#save").click(function(){
			//进行提交表单数据
			//$("#savaDecidedzoneFrom").sumbit();
			save("savaDecidedzoneFrom");
		});
		
		//修改定区数据
		//增加分区数据
		$("#edit").click(function(){
			//进行提交表单数据
			//$("#savaDecidedzoneFrom").sumbit();
			save("editDecidedzoneFrom");
		});		
	});

	function doDblClickRow(rowIndex,rowData){
		//alert("双击表格数据...");
		$('#association_subarea').datagrid( {
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			url : "/decidedzone/getAssociationSubarea.action?decidedzoneId="+rowData.id,
			columns : [ [{
				field : 'id',
				title : '分拣编号',
				width : 120,
				align : 'center',
				checkbox:true
			},{
				field : 'province',
				title : '省',
				width : 120,
				align : 'center',
				formatter : function(data,row ,index){
					return row.region.province;
				}
			}, {
				field : 'city',
				title : '市',
				width : 120,
				align : 'center',
				formatter : function(data,row ,index){
					return row.region.city;
				}
			}, {
				field : 'district',
				title : '区',
				width : 120,
				align : 'center',
				formatter : function(data,row ,index){
					return row.region.district;
				}
			}, {
				field : 'addresskey',
				title : '关键字',
				width : 120,
				align : 'center'
			}, {
				field : 'startnum',
				title : '起始号',
				width : 100,
				align : 'center'
			}, {
				field : 'endnum',
				title : '终止号',
				width : 100,
				align : 'center'
			} , {
				field : 'single',
				title : '单双号',
				width : 100,
				align : 'center'
			} , {
				field : 'position',
				title : '位置',
				width : 200,
				align : 'center'
			} ] ]
		});
		
		$('#association_customer').datagrid( {
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			/* url : "/decidedzone/getAssociationCustomer.action?decidedzoneId="+rowData.id, */
		    url : "/decidedzone/getAssociationCustomer.action?decidedzoneId="+rowData.id,
			columns : [[{
				field : 'id',
				title : '客户编号',
				width : 120,
				align : 'center' 
			},{
				field : 'name',
				title : '客户名称',
				width : 120,
				align : 'center'
			}, {
				field : 'station',
				title : '所属单位',
				width : 120,
				align : 'center'
			},{
				field : 'telephone',
				title : '电话号码',
				width : 120,
				align : 'center'
			},{
				field : 'address',
				title : '地址',
				width : 200,
				align : 'center'
			 } 
			]]
		});
		
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div region="south" border="false" style="height:150px">
		<div id="tabs" fit="true" class="easyui-tabs">
			<div title="关联分区" id="subArea"
				style="width:100%;height:100%;overflow:hidden">
				<table id="association_subarea"></table>
			</div>	
			<div title="关联客户" id="customers"
				style="width:100%;height:100%;overflow:hidden">
				<table id="association_customer"></table>
			</div>	
		</div>
	</div>
	
	<!-- 添加 修改分区 -->
	<div class="easyui-window" title="定区添加修改" id="addDecidedzoneWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
				<a id="reset" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >重置</a>
			</div>
		</div>
		
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="savaDecidedzoneFrom" action="/decidedzone/saveSubarea.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">定区信息</td>
					</tr>
					<tr>
						<td>定区编码</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>定区名称</td>
						<td><input type="text" name="username" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>选择快递员</td>
						<td>
							<input class="easyui-combobox" name="staff.id"  
    							data-options="valueField:'id',textField:'username',url:'decidedzone/getStaffs.action'" />  
						</td>
					</tr>
					<tr height="300">
						<td valign="top">关联分区</td>
						<td>
							<table id="subareaGrid"  class="easyui-datagrid" border="false" style="width:300px;height:300px" data-options="url:'/decidedzone/getSubareas.action',fitColumns:true,singleSelect:false">
								<thead>  
							        <tr>  
							            <th data-options="field:'subareaId',width:30,checkbox:true">编号</th>  
							            <th data-options="field:'addresskey',width:150">关键字</th>  
							            <th data-options="field:'position',width:200,align:'right'">位置</th>  
							        </tr>  
							    </thead> 
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 查询定区 -->
	<div class="easyui-window" title="查询定区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="overflow:auto;padding:5px;" border="false">
			<form>
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
					<tr>
						<td>定区编码</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>所属单位</td>
						<td><input type="text" name="staff.station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> </td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 关联客户窗口 要进行基本的操作 -->
	<div class="easyui-window" title="关联客户窗口" id="customerWindow" collapsible="false" closed="true" minimizable="false" maximizable="false" style="top:20px;left:200px;width: 400px;height: 300px;">
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="customerForm" action="${pageContext.request.contextPath }/decidedzone/assigncustomerstodecidedzone.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="3">关联客户</td>
					</tr>
					<tr>
						<td>
							<!-- 没有关联客户的数据 -->
							<input type="hidden" name="id" id="customerDecidedZoneId" />
							<select id="noassociationSelect" multiple="multiple" size="10"></select>
						</td>
						<td>
							<input type="button" value="》》" id="toRight"><br/>
							<input type="button" value="《《" id="toLeft">
						</td>
							<!-- 关联客户的数据  -->
						<td>
							<select id="associationSelect" name="customerIds" multiple="multiple" size="10"></select>
						</td>
					</tr>
					<tr>
						<td colspan="3"><a id="associationBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">关联客户</a> </td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>