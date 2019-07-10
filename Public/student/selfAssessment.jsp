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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>自己評価画面</title>
</head>

<div class="container">

	<jsp:include page="/Public/common/jsp/studentLayout.jsp" />

	<form name='form1' enctype="multipart/form-data">
		<div class="col-xs-2 col-sm-2 col-md-2"></div>
		<div class="col-xs-8 col-sm-8 col-md-8">
			<h3><%=title%>の振り返り（<%=countId%>）
			</h3>
			<p>
				<input type="radio" name="comp_value" value=1 required>&nbsp;&nbsp;1：全くそう思わない
			</p>
			<p>
				<input type="radio" name="comp_value" value=2 required>&nbsp;&nbsp;2
			</p>
			<p>
				<input type="radio" name="comp_value" value=3 required>&nbsp;&nbsp;3：あまり思わない
			</p>
			<p>
				<input type="radio" name="comp_value" value=4 required>&nbsp;&nbsp;4
			</p>
			<p>
				<input type="radio" name="comp_value" value=5 required>&nbsp;&nbsp;5：どちらとも言えない
			</p>
			<p>
				<input type="radio" name="comp_value" value=6 required>&nbsp;&nbsp;6
			</p>
			<p>
				<input type="radio" name="comp_value" value=7 required>&nbsp;&nbsp;7：少しそう思う
			</p>
			<p>
				<input type="radio" name="comp_value" value=8 required>&nbsp;&nbsp;8
			</p>
			<p>
				<input type="radio" name="comp_value" value=9 required>&nbsp;&nbsp;9：とてもそう思う
			</p>
			<p>
				<input type="radio" name="comp_value" value=10 required>&nbsp;&nbsp;10
			</p>

			<!-- ファイルのアップロード -->
			<div class="input-group">
				<input type="file" id="file_input" name="fl" size="75" style="display: none;" style="width: 100%"> <span class="input-group-btn">
					<button class="btn btn-default" type="button" onclick="$('#file_input').click();">
						<i class="glyphicon glyphicon-camera"></i>
					</button>
				</span>
				<div class="input-group">
					<input id="file" type="text" class="form-control" required disabled>
				</div>
			</div>

			<%if(checkId == 0){ %>
			<div id="ex_box_div_a" style="text-align: right">
				<div>
					<button type="button" class="btn btn-success" onclick="getRadioValue('comp_value');">submit</button>
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
				</div>
			</div>
		</div>

		<div class="col-xs-2 col-sm-2 col-md-2"></div>
		<input type="hidden" name="countId" value=<%=countId%>>
	</form>
</div>

<script src="../common/js/jquery.js"></script>
<script type="text/javascript">
	function getRadioValue(name) {
		var radios = document.getElementsByName(name);
		var result;
		for (var i = 0; i < radios.length; i++) {
			if (radios[i].checked) {
				result = radios[i].value;
				break;
			}
		}
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
	$(function() {
		$('#file_input').change(function() {
			$('#file').val($(this).val());
		});
	})
</script>
</html>