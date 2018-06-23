<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>显示物流中转地图</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#map_canvas{width:100%;height:750px;}
		#result {width:100%}
	</style>
	<script src="http://api.map.baidu.com/api?v=2.0&ak=mTo2yniglihELVGQCrsr3YAL037tSS39"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/LuShu/1.2/src/LuShu_min.js"></script>

</head>
<body> 
	<div id="map_canvas"></div> 
	<div id="result"></div>
	<button id="run">开始</button> 
	<button id="stop">停止</button> 
	<button id="pause">暂停</button> 
	<button id="hide">隐藏信息窗口</button> 
	<button id="show">展示信息窗口</button> 
	<script> 
	var map = new BMap.Map('map_canvas');
	map.enableScrollWheelZoom();//开启鼠标滚轮缩放
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 13);//中心点,13为放大的倍数
	var lushu;
	// 实例化一个驾车导航用来生成路线
    var drv = new BMap.DrivingRoute('北京', {
        onSearchComplete: function(res) {
            if (drv.getStatus() == BMAP_STATUS_SUCCESS) {
                var plan = res.getPlan(0);
                var arrPois =[];
                for(var j=0;j<plan.getNumRoutes();j++){
                    var route = plan.getRoute(j);
                    arrPois= arrPois.concat(route.getPath());
                }
                map.addOverlay(new BMap.Polyline(arrPois, {strokeColor: '#111'}));
                map.setViewport(arrPois);
                
                lushu = new BMapLib.LuShu(map,arrPois,{
                defaultContent:"",//"从天安门到百度大厦"
                autoView:true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
                //http://lbsyun.baidu.com/jsdemo/img/car.png 车辆图片
                icon  : new BMap.Icon('${pageContext.request.contextPath }/images/car.png', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)}),
                /*  icon  : new BMap.Icon('http://lbsyun.baidu.com/jsdemo/img/car.png', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)}),*/
                speed: 4500,
                enableRotation:true,//是否设置marker随着道路的走向进行旋转
               /*  landmarkPois: [
                   {lng:116.314782,lat:39.913508,html:'加油站',pauseTime:2},
                   {lng:116.315391,lat:39.964429,html:'高速公路收费<div><img src="http://map.baidu.com/img/logo-map.gif"/></div>',pauseTime:3},
                   {lng:116.381476,lat:39.974073,html:'肯德基早餐<div><img src="http://ishouji.baidu.com/resource/images/map/show_pic04.gif"/></div>',pauseTime:2}
                ] */});          
            }
        }
    });
	drv.search('高要市', '百度大厦');//查询路径
	//绑定事件
	$("run").onclick = function(){
		lushu.start();
	}
	$("stop").onclick = function(){
		lushu.stop();
	}
	$("pause").onclick = function(){
		lushu.pause();
	}
	$("hide").onclick = function(){
		lushu.hideInfoWindow();
	}
	$("show").onclick = function(){
		lushu.showInfoWindow();
	}
	function $(element){
		return document.getElementById(element);
	}
</script> 
</body> 
</html>