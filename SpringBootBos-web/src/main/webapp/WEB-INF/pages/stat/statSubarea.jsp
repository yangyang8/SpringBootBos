<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 引入echarts的js文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts.common.min.js"></script>	
<script type="text/javascript">
	$(function(){
        $.ajax({
        	  type: "POST",
        	  url: "/subarea/getSubareaData.action",
        	  async: false,
        	  dataType:"json",
        	  success: function(data){
        		  var name=new Array();
        		  for(var i=0;i<data.length;i++){
        			  name.push(data[i].name);
        		  }
        		  var myChart = echarts.init(document.getElementById('subareaData'));
        			var option = {
        				    title : {
        				        text: '统计区域分区分布情况',
        				        x:'center',
        				        top:'10%'
        				    },
        				    tooltip : {
        				        trigger: 'item',
        				        formatter: "{a} <br/>{b} : {c} ({d}%)"
        				    },
        				    legend: {
        				        orient: 'vertical',
        				        left: '25%',
        				        top:'20%',
        				        data: name
        				    },
        				    series : [
        				        {
        				            name: '各省份分区数统计',
        				            type: 'pie',
        				            radius : '55%',
        				            center: ['50%', '50%'],
        				            data:data,
        				            itemStyle: {
        				                emphasis: {
        				                    shadowBlur: 10,
        				                    shadowOffsetX: 0,
        				                    shadowColor: 'rgba(255,255, 255,0.5)'
        				                }
        				            }
        				        }
        				    ]
        				};
        			// 使用刚指定的配置项和数据显示图表。
        	        myChart.setOption(option);
        	  }
        	});
	});
</script>	
</head>
<body>
	<div id="subareaData" align="center" style="width: 100%;height: 700px">
		
	</div>
</body>
</html>