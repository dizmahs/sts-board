util={};
util.tbl = {};
util.comm = {};
util.semester = {};
util.mask = {};

function jsonAjax(url, targetData, callbackFunc){
	alert(url +" / "+ targetData +" / "+ callbackFunc );
	$.ajax({
		  url: url,
		  data:targetData,
		  method:'post',
		  dataType: 'json',
		  contentType: "application/x-www-form-urlencoded;charset=utf-8",
		  success: function(data) {
			 // callbackFunc(data);
		},
		error:function(data,status,err){
			if(errMsg != undefined && errMsg != null && errMsg != ""){
				alert(errMsg);
			}else{
				alert('Server Error');
			}
			
		}
	});
}

util.jsonAjax = function(paramObj){
	//alert(paramObj);
	var targetUrl = paramObj['targetUrl'];
	var param = paramObj['targetData'];
	var errMsg = paramObj['errMsg'];
	var callbackFunc = eval(paramObj['callbackFunc']);
	//alert(targetUrl +" / "+ param +" / "+ errMsg +" / "+ callbackFunc);
	$.ajax({
		url: targetUrl,
		data:param,
		method:'post',
		dataType: 'json',
		contentType: "application/x-www-form-urlencoded;charset=utf-8",
		success: function(data) {
			callbackFunc(data);
		},
		error:function(data,status,err){
			if(errMsg != undefined && errMsg != null && errMsg != ""){
				alert(errMsg);
			}else{
				alert('Server Error');
			}
			
		}
	});
}