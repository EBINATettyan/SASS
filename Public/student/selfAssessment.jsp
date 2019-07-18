<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%
	int dateId = (int) session.getAttribute("dateId");
	String title = (String) session.getAttribute("title");
	String time = (String) session.getAttribute("time");
	int countId = (int) request.getAttribute("countId");
	int checkId = (int) session.getAttribute("checkId");
	@SuppressWarnings("unchecked")
	ArrayList<Float> result_outlier = (ArrayList<Float>) session.getAttribute("result_outlier");
	float outlierMinimum = result_outlier.get(0);
	float outlierMaximum = result_outlier.get(1);
%>

<!DOCTYPE html>
<html>
<head>
<link href="../common/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../common/bootstrap/css/bootstrap_slider.css" rel="stylesheet">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>自己評価画面</title>
</head>

<div class="container">

	<jsp:include page="/Public/common/jsp/studentLayout.jsp" />

	<form name='form1' enctype="multipart/form-data">
		<div class="col-xs-1 col-sm-1 col-md-1"></div>
		<div class="col-xs-10 col-sm-10 col-md-10">
			<div class="jumbotron">
					<h4><%=title%>の振り返り（<%=countId%>）：
					</h4>
				<br>
				<div class="col-xs-10 col-sm-10 col-md-10">
					<input id="comp" name="comp_value" type="text" data-slider-min="1" data-slider-max="10" data-slider-step="1" data-slider-value="1" />
				</div>
				<div class="col-xs-2 col-sm-2 col-md-2">
					<span id="compCurrentSliderValLabel">value:<span id="compSliderVal">1</span></span>
				</div>
				<br> <br>
			</div>

			<div style="text-align: left">
				<!-- ファイルのアップロード -->
				<div class="input-group">
					<input type="file" name="fl" style="width: 100%" id="fileImage" required> <img id="preview" width="350" height="250">
				</div>
			</div>

			<%if(checkId == 0){ %>
			<div id="ex_box_div_a" style="text-align: right">
				<div>
					<button type="button" class="btn btn-success" onclick="funcPrompt();">submit</button>
				</div>
			</div>
			<%} %>
			<!-- 表示非表示切り替え -->
			<div id="ex_box_div_b" style="display: none;">
				<div class="form-group">
					<h4>なぜ、そのような自己評価をしたのか、理由を記述してください</h4>
					<textarea class="form-control" name="comp<%=countId%>Comment" rows="5" style="width: 100%"></textarea>
				</div>
				<div style="text-align: right">
					<button type="button" class="btn btn-warning" onclick="getReset()">reset</button>
					<button type="submit" class="btn btn-success" onclick="getComment()">submit</button>
					<br> <br>
				</div>
			</div>
		</div>

		<div class="col-xs-1 col-sm-1 col-md-1"></div>
		<input type="hidden" name="countId" value=<%=countId%>>
	</form>
</div>

<script src="../common/bootstrap/js/jquery.js"></script>
<script src="../common/bootstrap/js/bootstrap_slider.js"></script>
<script type="text/javascript">
	$("#comp").slider();
	$("#comp").on("slide", function(slideEvt) {
		$("#compSliderVal").text(slideEvt.value);
	});
</script>
<script type="text/javascript">
function funcPrompt() {
	var result = document.getElementById('comp').value;
		if (result <<%=outlierMinimum%>	|| result > <%=outlierMaximum%>	) {
			alert('your data is detecting now!!');
			$("#ex_box_div_a").hide("normal");
			$("#ex_box_div_b").show("normal");
		} else {
			$("#ex_box_div_a").show("normal");
			$("#ex_box_div_b").hide("normal");
			document.form1.action = "RecieveSelfAssessmentStudentServlet";
			document.form1.method = "post";
			document.form1.submit();
		}
	}
</script>
<script type="text/javascript">
	function getComment() {
		document.form1.action = "RecieveSelfAssessmentStudentServlet";
		document.form1.method = "post";
		document.form1.submit();
	}
</script>
<script type="text/javascript">
	function getReset() {
		document.form1.action = "ResetSelfAssessmentStudentServlet";
		document.form1.method = "post";
		document.form1.submit();
	}
</script>
<script type="text/javascript">
	$('#fileImage').on('change', function(e) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#preview").attr('src', e.target.result);
		}
		reader.readAsDataURL(e.target.files[0]);
	});
</script>
</html>