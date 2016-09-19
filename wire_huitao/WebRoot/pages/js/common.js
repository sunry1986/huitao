console.log('common222');
var common={};
common.URL='http://huitao.ews.m.jaeapp.com/pages/html/';
common.auth_URL='https://oauth.taobao.com/authorize?response_type=code&client_id=23384603&view=wap&redirect_uri=http://huitao.ews.m.jaeapp.com/login/user.action';
common.GetLength = function(str) {
    ///<summary>获得字符串实际长度，中文2，英文1</summary>
    ///<param name="str">要获得长度的字符串</param>
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
    return realLength;
};
common.cSubString = function(str,lens) {
    ///<summary>获得字符串实际长度，中文2，英文1</summary>
    ///<param name="str">要获得长度的字符串</param>
	
    var realLength = 0, len = str.length, charCode = -1;

    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
        if(realLength>=lens){
        	if(realLength>lens)
        		realLength=realLength-2
        	return str.substring(0,realLength)
        	
        }
    }
    return str;
   
};
common.urlSearch = function () 
{
   var name,value; 
   var str=location.href; //取得整个地址栏
   var num=str.indexOf("?") 
   str=str.substr(num+1); //取得所有参数   stringvar.substr(start [, length ]
   str=str.split('#')[0];
   var arr=str.split("&"); //各个参数放到数组里
   for(var i=0;i < arr.length;i++){ 
    num=arr[i].indexOf("="); 
    if(num>0){ 
     name=arr[i].substring(0,num);
     value=arr[i].substr(num+1);
     this[name]=value;
     } 
    } 
   return this;
}
common.errorhandler=function(msg){
	if(/重新登录/.test(msg)){
		console.log('-----------showquan--------------');
		window.location.href=common.auth_URL;
	}else if(/\<html\>/.test(msg)){
		$('html').html(msg);
		
	}else{
		
		$.toast(msg);
	}
	
}
common.consolelogfn=function(data){
	console.log('---------------start---------------');
	if(typeof data=='object'){
		for(var i in data){
			console.log('-------------'+i)
			if(typeof data[i]=='object'){
				
				console.log(JSON.stringify(data[i]))
			}else{
				
				console.log(data[i])	
			}
    		
		}
	}else{
		console.log(data);
	}
	console.log('---------------over---------------');
}  
common.getCookie= function(cookieName)  
{  
    var cookieValue = document.cookie;  
    var cookieStartAt = cookieValue.indexOf(""+cookieName+"=");  
    if(cookieStartAt==-1)  
    {  
        cookieStartAt = cookieValue.indexOf(cookieName+"=");  
    }  
    if(cookieStartAt==-1)  
    {  
        cookieValue = null;  
    }  
    else  
    {  
        cookieStartAt = cookieValue.indexOf("=",cookieStartAt)+1;  
        cookieEndAt = cookieValue.indexOf(";",cookieStartAt);  
        if(cookieEndAt==-1)  
        {  
            cookieEndAt = cookieValue.length;  
        }  
        cookieValue = decodeURIComponent(cookieValue.substring(cookieStartAt,cookieEndAt));//解码latin-1  
    }  
    return cookieValue;  
}
common.getDate=function(datearr){
	var year,month,day,hours,minutes;
	if(datearr.year<1000){
		year=1900+parseInt(datearr.year);
	}else{
		year=datearr.year;
		
	}
	month=parseInt(datearr.month)+1;
	if(month<10){
		
		month='0'+month;
	}
	day=datearr.date;
	hours=datearr.hours;
	minutes=datearr.minutes;
	if(minutes<10){
		minutes='0'+minutes;
		
	};
	return year+"-"+month+"-"+day+" "+hours+":"+minutes;
	
}
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

if(Tida){
	tbjs();	

};
var imgHelper
function tbjs(){
	var info=Tida.appinfo;
	var testflag=sessionStorage.getItem('testflag');
	testflag=typeof testflag == 'undefined' ? 0 : 1;
	if(info.isTmall||info.isTaobao){
		Tida.ready({
			console:1,
		    // interactId:"", // 互动实列ID type string 若无抽奖模块，此参数无须传入。给错误的实例ID会走错误流程
		    // module: ["device", "media", "server", "social", "widget", "sensor", "share", "buy", "draw", "im", "calendar", "prize"], // 应用所需要的模块。非必选，默认加载所有模块。支持的模块见注释。
			module: ["device", "media", "server", "social", "widget", "sensor", "share", "buy", "draw","award", "im", "calendar"],
		   //  sellerNick:"小叮当_淘淘" // 商家名称 
		    // shopId:123 // 店铺ID 从url中取 可选
		}, function(){
		    // TODO

		    Tida.isLogin(function(data){
		    	console.log('login'+data);
		    	common.consolelogfn(data);
		    	

		    		if(data.isLogin=='false'){
		    			Tida.doAuth(true, function(data){
						    if(data.finish){
						        // 授权成功 可以顺利调用需要授权的接口了
						    	var options = {
						    		    
						    	};
						    	 console.log('here????????????????');
						    	Tida.mixNick(options, function (data) {
						    		console.log('111111111111111111111111');
						    		common.consolelogfn(data)
						    	});
						    	
						    }else {
						        Tida.toast("呀！授权失败了");
						    }
						});
		    		}else{
		    			
				    	var options = {
				    		    
				    	};
				    	 console.log('here????????????????');
				    	Tida.mixNick(options, function (data) {
				    		console.log('sssssssssssssssssssss');
				    		common.consolelogfn(data);
				    	});
				    	
		    		}
		    	
		    }); 
		})

		
	}

if(typeof lib!='undefined'){
	

	imgHelper = lib.img({
		'class':'lib-img',//图片class
		'dataSrc':'data-src',//图片真实src 的data字段
		'size': '200x200',//cdn尺寸
		'sharpen': 's150',//锐化参数
		'q': ['q50', 'q30'],//图片质量[非弱网，弱网]
		'enableLazyload':true,//是否开启懒加载功能，默认true,
		'lazyHeight': 0,//[可选]，预加载当前屏幕以下lazyHeight内的图片，默认0
		'lazyWidth': 0,//[可选]，预加载当前屏幕右边lazyWidth内的图片，默认0
		'enalbeIOSWifiLoadMore':false//ios&&wifi情况下 是否取消懒加载,采用一次性加载，默认false
		});
}

}
