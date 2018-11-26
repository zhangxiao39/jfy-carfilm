/**
 * 
 */
$(function() {
	$('#btn_search').click(function() {
		var _carcode = $("#txt_carcode").val();
		if (_carcode != "") {
			$.ajax({
				url : "/searchqa/" + _carcode,
				type : "GET",
				success : function(data) {
					if (data >= 0) {
						if (data > 0) {
							swal({
								title : "您的车膜质保期",
								text : "剩余：" + data + "天",
								type : "success"
							}, function() {
								// window.location.reload();
							})
						} else {
							swal({
								title : "您的车膜质保期",
								text : "已经过期",
								type : "success"
							}, function() {
								// window.location.reload();
							})
						}
					} else {
						swal({
							title : "提示",
							text : "暂无此车辆信息！",
							type : "error"
						})
					}
				},
				error : function(e) {
					alert("错误！！");
				}
			})
		} else {
			swal({
				title : "提示",
				text : "请输入车牌号码！",
				type : "error"
			})
		}
	});
});