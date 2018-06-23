<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BOS主界面</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<!-- 导入ztree类库 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"
	type="text/css" />
<script
	src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	// 初始化ztree菜单
	$(function() {
		var setting = {
			data : {
				simpleData : { // 简单数据 
					enable : true
				}
			},
			callback : {
				onClick : onClick
			}
		};
		
		// 基本功能菜单加载
		$.ajax({
			url : '${pageContext.request.contextPath}/permission/getMenuPermissionData.action',
			type : 'GET',
			dataType : 'text',
			success : function(data) {
				var zNodes = eval("(" + data + ")");
				$.fn.zTree.init($("#treeMenu"), setting, zNodes);//给这个菜单树设置数据
			},
			error : function(msg) {
				alert('菜单加载异常!');
			}
		});
		
		// 系统管理菜单加载
		$.ajax({
			url : '${pageContext.request.contextPath}/json/admin.json',
			type : 'GET',
			dataType : 'text',
			success : function(data) {
				var zNodes = eval("(" + data + ")");
				$.fn.zTree.init($("#adminMenu"), setting, zNodes);
			},
			error : function(msg) {
				alert('菜单加载异常!');
			}
		});
		
		// 页面加载后 右下角 弹出窗口
		/**************/
		window.setTimeout(function(){
			$.messager.show({
				title:"消息提示",
				msg:'欢迎登录，超级管理员！ <a href="javascript:void" onclick="top.showAbout();">联系管理员</a>',
				timeout:5000
			});
		},3000);
		/*************/
		
		$("#btnCancel").click(function(){
			$('#editPwdWindow').window('close');
		});
		
		
		//注册修改密码的点击确定事件，进行修改密码
		$("#btnEp").click(function(){
			//alert("修改密码");
			
			//增加校验规则,在对应的对话框当中增加校验规则
			
			
			//首先要先校验我们的表单数据填写是否符合校验规则
			//做表单字段验证吗, 返回true当所有字段是有效的. 他的方法是用validatebox插件.
			var b=$("#checkDataForm").form("validate");
			if(b){
				
				//然后进行校验填写的修改密码和确认修改密码是否相等
				var pass=$("#txtNewPass").val();
				var repass=$("#txtRePass").val();
				//alert(pass+"==="+repass);
				if(pass == repass){
					//alert("你的密码和确认密码一致");
					//最后进行向我们的服务器发送ajax请求去修改数据库当中的密码
					$.ajax({
						  url: "/editPassword",
						  async: true,
						  data: {"password":pass},
						  success: function(data){
							//接收服务器返回的数据，进行判断修改密码是否成功,也就是设置成功标志位
							if(data=='1'){
								//成功则关闭页面
								$("#editPwdWindow").window('close');
							}else{
								//给用户一个提示信息
								$.messager.alert("提示信息","修改密码不成功!"+data,"error");
							}
						  }
						});
				}else{
					//alert("你的密码和确认密码不一致");
					//给用户设置提示信息
					//$.messager.alert('My Title','Here is a info message!','info');  
					$.messager.alert("提示信息","你的密码和确认密码不一致!","error");
				}
				
				
			}
			
		});
		
		/*$("#treeMenu").click(function(){
			//location.href="${pageContext.request.contextPath}/permission/getMenuPermissionData.action";
			//alert("xxxxx");
		});*/
	});

	function onClick(event, treeId, treeNode, clickFlag) {
		// 判断树菜单节点是否含有 page属性
		if (treeNode.page!=undefined && treeNode.page!= "") {
			var content = '<div style="width:100%;height:100%;overflow:hidden;">'
					+ '<iframe src="'
					+ treeNode.page
					+ '" scrolling="auto" style="width:100%;height:100%;border:0;" ></iframe></div>';
			if ($("#tabs").tabs('exists', treeNode.name)) {// 判断tab是否存在
				$('#tabs').tabs('select', treeNode.name); // 切换tab
				var tab = $('#tabs').tabs('getSelected'); 
				$('#tabs').tabs('update', {
				    tab: tab,
				    options: {
				        title: treeNode.name,
				        content: content
				    }
				});
			} else {
				// 开启一个新的tab页面
				$('#tabs').tabs('add', {
					title : treeNode.name,
					content : content,
					closable : true
				});
			}
		}
	}

	/*******顶部特效 *******/
	/**
	 * 更换EasyUI主题的方法
	 * @param themeName
	 * 主题名称
	 */
	changeTheme = function(themeName) {
		var $easyuiTheme = $('#easyuiTheme');
		var url = $easyuiTheme.attr('href');
		var href = url.substring(0, url.indexOf('themes')) + 'themes/'
				+ themeName + '/easyui.css';
		$easyuiTheme.attr('href', href);
		var $iframe = $('iframe');
		if ($iframe.length > 0) {
			for ( var i = 0; i < $iframe.length; i++) {
				var ifr = $iframe[i];
				$(ifr).contents().find('#easyuiTheme').attr('href', href);
			}
		}
	};
	// 退出登录
	function logoutFun() {
		$.messager
		.confirm('系统提示','您确定要退出本次登录吗?',function(isConfirm) {
			if (isConfirm) {
				//location.href = '${pageContext.request.contextPath }/loginIn.action';
				location.href = '/loginOut.action';
			}
		});
	}
	// 修改密码的点击事件，这个事件在我们的菜单当中有
	function editPassword() {
		//editPwdWindow为我们的打开一个对象框对象，本身是一个大的Div
		$('#editPwdWindow').window('open');
	}
	// 版权信息
	function showAbout(){
		$.messager.alert("天网国际系统 v1.0","管理员邮箱: yangyang@qq.com");
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height:80px;padding:10px;background:url('./images/header_bg.png') no-repeat right;">
		<div id="sessionInfoDiv"
			style="position: absolute;right: 5px;top:10px;">
			[<strong>超级管理员 ${currentUser.username} </strong>]，欢迎你！
		</div>
		<div style="position: absolute; right: 5px; bottom: 10px; ">
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_pfMenu',iconCls:'icon-ok'">更换皮肤</a>
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'">控制面板</a>
		</div>
		<div id="layout_north_pfMenu" style="width: 120px; display: none;">
			<div onclick="changeTheme('default');">default</div>
			<div onclick="changeTheme('gray');">gray</div>
			<div onclick="changeTheme('black');">black</div>
			<div onclick="changeTheme('bootstrap');">bootstrap</div>
			<div onclick="changeTheme('metro');">metro</div>
		</div>
		<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
			<div onclick="editPassword();">修改密码</div>
			<div onclick="showAbout();">联系管理员</div>
			<div class="menu-sep"></div>
			<div onclick="logoutFun();">退出系统</div>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'菜单导航'"
		style="width:200px" >
		<div class="easyui-accordion" fit="true" border="false" >
			<div title="基本功能"  data-options="iconCls:'icon-mini-add'"  style="overflow:auto">
				<ul id="treeMenu" class="ztree"></ul>
			</div>
			<div title="系统管理" data-options="iconCls:'icon-mini-add'" style="overflow:auto">  
				<ul id="adminMenu" class="ztree"></ul>
			</div>
		</div>
	</div>
	<div data-options="region:'center'">
		<div id="tabs" fit="true" class="easyui-tabs" border="false">
			<div title="消息中心" id="subWarp"
				style="width:100%;height:100%;overflow:hidden">
				<iframe src="${pageContext.request.contextPath }/page_common_home.action"
					style="width:100%;height:100%;border:0;"></iframe>
				<%--				这里显示公告栏、预警信息和代办事宜--%>
			</div>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="height:50px;padding:10px;background:url('./images/header_bg.png') no-repeat right;">
		<table style="width: 100%;">
			<tbody>
				<tr>
					<td style="width: 300px;">
						<div style="color: #999; font-size: 8pt;">
							天龙国际 | Powered by <a href="http://www.hailong.com/">hailong.com</a>
						</div>
					</td>
					<td style="width: *;" class="co1"><span id="online"
						style="background: url(${pageContext.request.contextPath }/images/online.png) no-repeat left;padding-left:18px;margin-left:3px;font-size:8pt;color:#005590;">在线人数:1</span>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<!--修改密码窗口,是一个大的窗口布局控件 easyui-window-->
    <div id="editPwdWindow" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 160px; padding: 5px;
        background: #fafafa">
        <!-- 使用easyui的 布局把这个页面分成二个部分
        	同时这个region="center"是我们的data-options:"region="center""的简化形式
        -->
        <div class="easyui-layout" fit="true">
          
	         <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
	            <!-- 为了使用我们的easyui的form表单校验，我们要给这个校验框当中增加一个form标签 -->
				<form id="checkDataForm">
	                <table cellpadding=3>
	                    <tr>
	                        <td>新密码：</td>
	                        <!-- 
	                        easyui-validatebox表示的是给这个密码框增加一个校验规则样式
	                        	具体的规则有4种
	                        		required="required" 表示不为空
									定义的验证规则是通过使用要求和validType属性,这是规则已经实现:
										email: 正则表达式匹配电子邮件规则。 
										url: 正则表达式匹配的URL规则。 
										length[0,100]: 在x和x字符允许。 
										remote['http://.../action.do','paramName']: 发送ajax请求做验证值,返回“true”当成功。 
	                        	
	                         -->
	                        <td><input id="txtNewPass"  required="required" data-options="validType:'length[4,14]'"  class="easyui-validatebox txt01" type="Password" /></td>
	                    </tr>
	                    <tr>
	                        <td>确认密码：</td>
	                        <td><input id="txtRePass"  required="required" data-options="validType:'length[4,14]'"  class="easyui-validatebox txt01" type="Password"/></td>
	                    </tr>
	                </table>
	            </form>
	        </div>
            
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
            	<!-- 通过id来进行注册我们确定按键的点击事件 -->
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >确定</a> 
                <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>
</body>
</html>