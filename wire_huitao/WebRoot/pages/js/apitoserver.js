API = {
	ajaxPost : function(url, data, success, error) {
		$.ajax({
			type:'POST',
			url : url,
			data : data,
			success : success,
			error : error,
			dataType : 'json'
		});
	},
	base : function(url, params, callFunc) {
		API.ajaxPost(url, params, function(resp) {
			var result = {
				success : true,
				data : resp
			}
			callFunc(result);
		}, function(request) {
			var msg;
			switch (request.status) {
			case 404:
				msg = "未找到,请联系管理员";
				break;
			case 500:
				msg = "服务器错误,请联系管理员";
				break;
			default:
				msg = "请求出错,请退出后重新登录";
			}
			var result = {
				success : false,
				msg : msg
			}
			callFunc(result);
		});
	}
}
var  serverAPI={
	getUserInfo:function(params,resultFun){
		var userInfo= window.sessionStorage.getItem('userInfo');
		if(userInfo!=undefined && userInfo!=null && userInfo!="undefined" && userInfo!=""&&userInfo!="null"){
			console.log('11111111')
			resultFun(userInfo);
		}else{
			 
			 API.base('/sys/user!findUserInfo.action', params, function(rsp){
		            if(rsp.data!=undefined && rsp.data.userInfo!=undefined){
		            	console.log('222222222222') 
		            	var userInfo=JSON.stringify(rsp.data.userInfo);
		            	window.sessionStorage.setItem('userInfo',userInfo);
		                resultFun(userInfo);
		            }else{
		                  if( rsp.msg!=undefined){
		                     //alert(rsp.msg);
		                	  common.errorhandler(rsp.msg)
		                  }
		                
		            }
		       });
		}
		
	},
	getUserInfoClient:function(params,resultFun){
		var userInfo= window.sessionStorage.getItem('userInfo');
		if(userInfo!=undefined && userInfo!=null && userInfo!="undefined" && userInfo!=""&&userInfo!="null"){
			resultFun(userInfo);
			console.log('11111111-------')
		}else{
			console.log('222222222222------') 
			 API.base('/login/user!findUserInfo.action', params, function(rsp){
		            if(rsp.data!=undefined && rsp.data.userInfo!=undefined){
		            	var userInfo=JSON.stringify(rsp.data.userInfo);
		            	window.sessionStorage.setItem('userInfo',userInfo);
		                resultFun(userInfo);
		            }else{
		                  if( rsp.msg!=undefined){
		                     //alert(rsp.msg);
		                     console.log(rsp.msg);
		                    // common.errorhandler(rsp.msg)
		                     
		                  }
		                
		            }
		       });
		}
		
	}
	
	
};
/*var params1={
		'pageSize':2,
		'pageNo':1
	}*/
//出售中
/*API.base('/sys/items!searchItemOnsale.action', params1, function(rsp){
	console.log(rsp);
});*/
//仓库中
/*API.base('/sys/items!searchItemInventory.action', params1, function(rsp){
	console.log(rsp);
});*/

//

/*var params2={"activity": {
    "actEnd": '2016-05-20 15:21:11', 
    "actId": '', 
    "actName": '测试2', 
    "actRemark": '备注2', 
    "actStart": '2016-05-20 15:21:11', 
    "actType": 1, 
    "activityDetailList": [
        {
            "actDetailId": '', 
            "actId": "", 
            "activityGoodDetailList": [
                {
                    "actDetailId": "", 
                    "activityPrice": "", 
                    "currentNum": "", 
                    "detailUrl": "", 
                    "isSaleOut": "", 
                    "oldNum": "", 
                    "overWay": "", 
                    "pic_url": "http://image/test1.jpg", 
                    "price": "", 
                    "title": "", 
                    "type": "", 
                    "useroperation": "add"
                }, 
                {
                    "actDetailId": "", 
                    "activityPrice": "", 
                    "currentNum": "", 
                    "detailUrl": "", 
                    "isSaleOut": "", 
                    "oldNum": "", 
                    "overWay": "", 
                    "pic_url": "http://image/test2.jpg", 
                    "price": "", 
                    "title": "", 
                    "type": "", 
                    "useroperation":"add"
                }
            ], 
            "endTime": "", 
            "startTime": "", 
            "useroperation": "add"
        }
    ], 
    "banner": 1, 
    "couponReceivingConditions": 1, 
    "couponShow": 1, 
    "coupons": 1, 
    "sellerNick": '小叮当_淘淘', 
    "state": 1, 
    "useroperation": 'add'
}
}

//新增活动

API.base('/sys/activity!operationActivity.action',{data:JSON.stringify(params2)} , function(rsp){
	console.log(rsp);
})*/

/*var params3={"activity": {
    "actId": '',  
    "sellerNick": '小叮当_淘淘', 
    "useroperation": 'delete'
}
}
//删除活动
API.base('/sys/activity!operationActivity.action',{data:JSON.stringify(params3)} , function(rsp){
	console.log(rsp);
})*/

/*var params4={"activity": {
    "actEnd": '2016-05-20 15:21:11', 
    "actId": '28', 
    "actName": '测试4', 
    "actRemark": '备注2', 
    "actStart": '2016-05-20 15:21:11', 
    //"actType": 2, 
    "activityDetailList": [
        {
            "actDetailId": '8', 
            "actId": "28", 
            "activityGoodDetailList": [
                {
                	"id": "27",
                    "actDetailId": "8", 
                    "activityPrice": "", 
                    "currentNum": "", 
                    "detailUrl": "", 
                    "isSaleOut": "", 
                    "oldNum": "", 
                    "overWay": "", 
                    "pic_url": "http://image/test1.jpg", 
                    "price": "", 
                    "title": "", 
                    "type": "", 
                    "useroperation": "delete"
                }, 
                {
                	"id": "28",
                    "actDetailId": "8",  
                    "activityPrice": "", 
                    "currentNum": "", 
                    "detailUrl": "", 
                    "isSaleOut": "", 
                    "oldNum": "", 
                    "overWay": "", 
                    "pic_url": "http://image/test2.jpg", 
                    "price": "", 
                    "title": "", 
                    "type": "1", 
                    "useroperation":"delete"
                }
            ], 
            "endTime": "2016-05-20 15:21:11", 
            "startTime": "", 
            "useroperation": "delete"
        }
    ], 
    "banner": 1, 
    "couponReceivingConditions": 1, 
    "couponShow": 1, 
    "coupons": 1, 
    "sellerNick": '小叮当_淘淘', 
    "state": 1, 
    "useroperation": ''
}
}
//更新活动
API.base('/sys/activity!operationActivity.action',{data:JSON.stringify(params4)} , function(rsp){
	console.log(rsp);
})*/
/*
 //单个活动查询
 var params5={
		'actId':29
	}
API.base('/sys/activity!findActivityById.action',params5 , function(rsp){
	console.log(rsp);
})*/
//

/*//活动list
var params6={"activity": {
    "sellerNick": '小叮当_淘淘',
    "state":0
}
};

API.base('/sys/activity!findActivitiesList.action',{data:JSON.stringify(params6)} , function(rsp){
	console.log(rsp);
});
//查询用户信息

var params7={
'sellerNick':2
}
//出售中
API.base('/sys/user!findUserInfo.action', params7, function(rsp){
console.log(rsp);
});*/

