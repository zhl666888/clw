<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html class="panel-fit">
<head>
<title>car</title>
	<link rel="Stylesheet" href="${ctxStatic}/clw/css/site.css" />
	<link rel="Stylesheet" href="${ctxStatic}/clw/css/easyui.css" />
	<link rel="Stylesheet" href="${ctxStatic}/clw/css/icon.css" />
	 <script type="text/javascript" src="${ctxStatic}/clw/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/clw/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctxStatic}/clw/js/jquery.easyui.min.js"></script>
    <script type="text/javascript"  src="http://webapi.amap.com/maps?v=1.3&key=fc9896bd4dc7807a5ad9aa8f8361a192"></script>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=fc9896bd4dc7807a5ad9aa8f8361a192&plugin=AMap.Geocoder"></script>
   
</head>
<body class="easyui-layout">
<!--  <div id="carMess" style="padding-top:10px;float:left">
     <div id="buttonShow" class="button-group">
     <input id="setCarPosition" type="button" class="button"  value="定位监控"/>
     </div>
  </div> -->
	<div data-options="region:'north',split:true " style="height: 100px">
   			定位监控
	</div>
  <div data-options="region:'south',split:true " style="overflow: hidden; height: 143px">
    <div id="tb" style="margin-bottom: 5px; padding: 5px 0 5px 40px; height: 20px">
      <div id="toolbarleft" style="float:left"></div>
      <div id="toolbarright" style="float: right; padding: 2px 0 0 5px;">
      <input id="tb-text7" type="button" onclick="selectCar()" class="btnOrange easyui-tooltip tooltip-f" style="width: 80px" data-options="position: 'top'" value="选择车辆"/>
      </div>
    </div>
</div>
 <div data-options="region:'center'">
 <div id="container" style="width:100%; height:100%;"></div>
  </div>
  
  <div id="wSelCarNew" closed="true" class="easyui-window" title="车辆选择" data-options="iconCls:'icon-save'" style="overflow:hidden;width:500px;height:363px;padding:10px;">
		<div id="carGrid">
		</div>
		<a href="#"  id="btnSetCarsNew" class="easyui-linkbutton l-btn l-btn-small l-btn-plain" data-options="iconCls:'icon-ok',plain:true" onclick="CallOk()">
		<span>确定</span>
		</a>
	</div>
<script>
$(function () {
	addMap();
	//connCommunicator();
});
function addMap(){
    map = new AMap.Map('container', {
        // 设置中心点
        center: [125.35, 43.88],
        // 设置缩放级别
        zoom: 10
    });
    //在地图中添加ToolBar插件
    map.plugin(["AMap.ToolBar"], function () {
        toolBar = new AMap.ToolBar({
            ruler: true,
            direction: false,
            autoPosition: false,
            useNative: true
        });
        map.addControl(toolBar);
    });
}
function connCommunicator(){
	 $.ajax({  
    type : "post",  
    url : '${ctx}/clw/carMonitor/connCommunicator',
    success: function(data) {
    },
	error: function(request) {
    	alert("出错");
	}
});
}
function selectCar() {
	$('#wSelCarNew').window('open');
	$('#carGrid').datagrid({
	    height: 280,
	    url: '${ctx}/clw/carMonitor/carList',
	    method: 'POST',
	  //  queryParams: { 'id': id },
	   // idField: '产品ID',
	    striped: true,
	    fitColumns: true,
	    singleSelect: false,
	    rownumbers: true,
	    pagination: false,
	    nowrap: false,
	    pageSize: 10,
	    pageList: [10, 20, 50, 100, 150, 200],
	    showFooter: true,
	    columns: [[
	        { field: 'ck', checkbox: true },
	        { field: 'carno', title: '车辆', width: 180, align: 'left' },
	        { field: 'cid', title: 'carid', width: 180, align: 'left',hidden:true } 
	    ]],
	    onBeforeLoad: function (param) {
	    },
	    onLoadSuccess: function (data) {
	    	//data=eval("("+data+")");
	    },
	    onLoadError: function () {
	        
	    },
	    onClickCell: function (rowIndex, field, value) {
	        
	    }
	});
	/* $.ajax({  
        type : "post",  
        url : '${ctx}/clw/carMonitor/selectCar',
        data:{},
        success: function(data) {
        	if(data){
        	// var obj = eval('('+ data +')');
        	alert(data[0].carno);
        	}else{
        	}
        },
    	error: function(request) {
        	alert("出错");
    	}
    });  */
    //CarSelect(false, CallBackFun, []);
}

//车辆选择完毕确定按钮Click事件函数
function CallOk() {
	var checkedId=GetclickValues();
	/* if(checkedId){
		alert("ss");
		return false;
	} */
	var checkedid=checkedId.join(",");
	//alert(checkid);
	$.ajax({  
    type : "post",  
    url : '${ctx}/clw/carMonitor/carPosition',
    data:{checkedid:checkedid},
    success: function(data) {
    	if(data){
    	var obj = eval('('+ data +')');
   		//alert(obj.rows.length);
   		for(var i=0;i<obj.rows.length;i++){
       	var lng=obj.rows[i].lng;
       	var lat=obj.rows[i].lat;
       	addMarker(lng,lat);
    	}
    	}else{
    	}
    },
	error: function(request) {
    	alert("出错");
	}
}); 

    $('#wSelCarNew').window('close');
}
//将选中的多选框对应的id收集起来
var GetclickValues = function () {
	var checkedItems = $('#carGrid').datagrid('getChecked');
	var names = [];
	$.each(checkedItems, function(index, item){
		//alert(index);
	names.push(item.cid);
	});               
	names.join(",");
    return names;
}
// 实例化点标记
function addMarker(lng,lat) {
       marker = new AMap.Marker({
           icon: "${ctxStatic}/clw/image/che1_3.png",
           position: [lng,lat]
       });
       marker.setMap(map);
     map.setFitView()
   }
 </script>
   
</body>
</html>