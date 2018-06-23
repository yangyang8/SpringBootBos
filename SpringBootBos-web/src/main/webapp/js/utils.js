/**
 * 
 */

//增加保存功能的操
function save(fromId){
			//进行提交表单的基本的操作，如数据的校对操作
			var b=$("#"+fromId+"").validatebox();
			if(b){
				$.messager.confirm("提示","你确定要录入这些数据吗?",function(r){
					
					if(r){
						//进行提交表单
						$("#"+fromId+"").submit();
					}
				});
				
			}else{
				$.messager.alert("警告","你输入的数据的格式不正确!","warning");
		}
}