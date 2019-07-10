<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%
	int dateId = (int) session.getAttribute("dateId");
	String title = (String) session.getAttribute("title");
	String time = (String) session.getAttribute("time");
	int countId = (int) request.getAttribute("countId");
	int checkId = (int) session.getAttribute("checkId");
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

	<form class="form-inline" enctype="multipart/form-data" method="post" action="RecieveSelfAssessmentBeforeStudentServlet">
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
				<input type="file" name="fl" style="width: 100%" id="fileImage" required> <img id="preview" width="350" height="250">
			</div>
			<%
				if (checkId == 0) {
			%>
			<div style="text-align: right">
				<button type="submit" class="btn btn-success">submit</button>
				<br><br>
			</div>
			<%
				}
			%>
		</div>
		<div class="col-xs-2 col-sm-2 col-md-2"></div>
		<input type="hidden" name="countId" value=<%=countId%>>
	</form>
</div>
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