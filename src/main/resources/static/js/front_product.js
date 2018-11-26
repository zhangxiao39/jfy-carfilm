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
$(function() {
	$("#myModal").on("hidden.bs.modal", function() {
		$("#p_name").text("");
		$("#p_category").text("");
		$("#p_type").text("");
		$("#p_color").text("");
		$("#p_madein").text("");
		$("#p_c1").text("");
		$("#p_c2").text("");
		$("#p_c3").text("");
		$("#p_c4").text("");
		$("#p_qb").text("");
		$("#p_price").text("");
		$("#p_size").text("");
	});
});