function showProduct(id) {
	$.ajax({
		url : "/showproduct/" + id,
		type : "get",
		success : function(data) {
			$("#p_name").text(data.name);
			$("#p_category").text(data.category);
			$("#p_type").text(data.type);
			$("#p_color").text(data.color);
			$("#p_madein").text(data.madein);
			$("#p_c1").text(data.c1);
			$("#p_c2").text(data.c2);
			$("#p_c3").text(data.c3);
			$("#p_c4").text(data.c4);
			$("#p_qb").text(data.qb + "年");
			$("#p_price").text(data.price + "元");
			$("#p_size").text(data.size + "米");
		},
		error : function(e) {
			alert("错误！！");
		}
	});
}
function validform() {
	return $("#myform").validate({
		rules : {
			name : {
				required : true,
			}
		},
		messages : {
			name : {
				required : "请输入产品名称",
			}
		}
	});
}
$(validform());
$(function() {
	$("#category").change(function() {
		var index = this.selectedIndex;
		$("#image").val("p"+index);
	});
	$('#btn_submit').click(function() {
		if (validform().form()) {
			$.ajax({
				url : "/admin/product",
				type : $("#mode").val(),
				data : $("#myform").serialize(),
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "保存成功！",
							text : "成功保存了一条质保信息。",
							type : "success"
						}, function() {
							window.location.reload();
						})
					} else {
						swal({
							title : "保存失败",
							text : "保存失败！",
							type : "error"
						})
					}
				},
				error : function(e) {
					alert("错误！！");
				}
			});
		} else {

		}
	});
	$("#myModal").on("hidden.bs.modal", function() {
		$("#name").val("");
		$("#color").val("");
		$("#madein").val("");
		$("#type").val("");
		$("#qb").val("");
		$("#price").val("");
		$("#size").val("");
		$("#c1").val("");
		$("#c2").val("");
		$("#c3").val("");
		$("#c4").val("");
		$("#id").val(id);
		$("#id").val("0");
		$("#mode").val("POST");
		validform().resetForm();
	});
});
function showInfo(id) {
	var select_category = document.getElementById("category");
	$.ajax({
		url : "/admin/product/" + id,
		type : "get",
		success : function(data) {
			$("#name").val(data.name);
			$("#color").val(data.color);
			$("#madein").val(data.madein);
			$("#type").val(data.type);
			$("#qb").val(data.qb);
			$("#price").val(data.price);
			$("#size").val(data.size);
			$("#c1").val(data.c1);
			$("#c2").val(data.c2);
			$("#c3").val(data.c3);
			$("#c4").val(data.c4);
			$("#id").val(id);
			// setOption(select_category,data.category);
			$("#category").val(data.category);
			$("#isshow").val(data.isshow);
			$("#isstock").val(data.isstock);
			$("#mode").val("PUT");

		},
		error : function(e) {
			alert("错误！！");
		}
	});
}
function deleteInfo(row,id) {
	swal({
		title : "您确定要删除这条信息吗",
		text : "删除后将无法恢复，请谨慎操作！",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "是的，我要删除！",
		cancelButtonText : "让我再考虑一下…",
		closeOnConfirm : false,
		closeOnCancel : false
	}, function(isConfirm) {
		if (isConfirm) {
			$.ajax({
				url : "/admin/product/" + id,
				type : "delete",
				success : function(data) {
					if (data == "ok") {
						swal({
							title : "删除成功！",
							text : "成功删除了一条质保信息。",
							type : "success"
						}, function() {
							window.location.reload();
							 //$(row).parent().parent().remove();
							 //$('.footable').footable();
						})
					} else {
						swal({
							title : "删除失败",
							text : "删除失败！",
							type : "error"
						})
					}
				},
				error : function(e) {
					alert("错误！！");
				}
			});
		} else {
			swal({
				title : "已取消",
				text : "您取消了删除操作！",
				type : "error"
			})
		}
	})
}
function setOption(obj, v) {
	for (var i = 0; i < obj.options.length; i++) {
		if (obj.options[i].innerHTML == v) {
			obj.options[i].selected = true;
			break;
		}
	}
}