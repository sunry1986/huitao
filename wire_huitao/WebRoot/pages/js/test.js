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
				msg = "未找到";
				break;
			case 500:
				msg = "服务器错误";
				break;
			default:
				msg = "请求出错";
			}
			var result = {
				success : false,
				msg : msg
			}
			callFunc(result);
		});
	}
}
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
});*/
//查询用户信息

/*var params7={
'sellerNick':2
}
//查询用户信息
API.base('/sys/user!findUserInfo.action', params7, function(rsp){
console.log(rsp);
});*/
//二维码生成
/*var params8={
		'content':"http://puyun.ews.m.jaeapp.com",
		'name':"小叮当_淘淘的活动"
		}
API.base('/sys/items!createQrcode.action', params8, function(rsp){
	console.log(rsp);
});*/

//注册活动6201930e5d84d3d3edde283c2e4c59619ed06dfhb7c5628740700517
/*var params9={
		'entryUrl':"http://puyun.ews.m.jaeapp.com",
		'bizId':"小叮当_淘淘的活动",
		'description':"description",
		'name':"name",
		'picture':"picture",
		'hasValidTime':"hasValidTime",
		'endTime':"2016-07-20 13:00:00",
		'startTime':"2016-07-21 13:00:00",
		'actId'1
		}
API.base('/sys/items!registerActivity.action', params9, function(rsp){
	console.log(rsp);
});*/
//关闭活动
/*var params10={
		'bizId':"",
		'actId'1
		}
API.base('/sys/items!unRegisterActivity.action', params10, function(rsp){
	console.log(rsp);
});*/
//推广淘小铺isv 活动到微淘feed
/*var params11={
		'bizId':"",
		}
API.base('/sys/items!feedWeiTao.action', params11, function(rsp){
	console.log(rsp);
});*/
//流量平台用户签约情况查询
/*var params12={
		'userNick':"小叮当_淘淘的活动",
		}
API.base('/sys/items!walletSign.action', params12, function(rsp){
	console.log(rsp);
});*/
//流量发放
/*var params13={
		'buyerNick':"小叮当_淘淘",
		'realNick':"",
		'serial':"",
		'flow':"",
		'reason':"",
		'always':""
		}
API.base('/sys/items!sendFlow.action', params13, function(rsp){
	console.log(rsp);
});*/
//流量发放(用户id)
/*var params14={
		'userId':"",
		'serial':"",
		'flow':"",
		'reason':"",
		'always':""
		}
API.base('/sys/items!FlowPublishByUserId.action', params14, function(rsp){
	console.log(rsp);
});*/

//log日志保存
/*var params15={
		'activityId':"29",
		'buyerNick':"aaa",
		'condition':"1",
		'sellerNick':"小叮当_淘淘",
		}
API.base('/sys/activitylog!addActivityLog.action', params15, function(rsp){
	console.log(rsp);
});*/
//log日志查询
/*var params15={
		'activityId':"29",
		'buyerNick':"aaa",
		'condition':"1",
		'sellerNick':"小叮当_淘淘",
		}
API.base('/sys/activitylog!findActivityLog.action', params15, function(rsp){
	console.log(rsp);
});*/

//收藏并赠送流量
/*var params16={
		'buyerNick':"小叮当_淘淘",
		'activityId':"2",
		}
API.base('/client/client!shoucang.action', params16, function(rsp){
	  console.log(rsp);
});*/

var params3={"activity": {
"actId": 44,  
"state":2 , 
"useroperation": 'update'
}
}
//更新活动，只更新某个字段
API.base('/sys/activity!operationActivity.action',{data:JSON.stringify(params3)} , function(rsp){
console.log(rsp);
})
