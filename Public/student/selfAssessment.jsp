<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%
	int dateId = (int) session.getAttribute("dateId");
	String title = (String) session.getAttribute("title");
	String time = (String) session.getAttribute("time");
	int countId = (int) request.getAttribute("countId");
	@SuppressWarnings("unchecked")
	ArrayList<Float> outlier_result = (ArrayList<Float>) session.getAttribute("outlier_result");
	float outlierMinimum = outlier_result.get(0);
	float outlierMaximum = outlier_result.get(1);
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

	<jsp:include page="/Public/common/jsp/userLayout.jsp" />

	<form action='RecieveSelfAssessmentStudentServlet' method='POST'>
		<div class="col-xs-2 col-sm-2 col-md-2"></div>
		<div class="col-xs-8 col-sm-8 col-md-8">
			<h3>xxxxに関する振り返り</h3>
			<p>
				1：全くそう思わない<input type="radio" name="comp<%=countId%>" onclick="Prompt()" value=1>
			</p>
			<p>
				2<input type="radio" name="comp<%=countId%>" onclick="Prompt()" value=2>
			</p>
			<p>
				3：あまり思わない<input type="radio" name="comp<%=countId%>" onclick="Prompt()" value=3>
			</p>
			<p>
				4<input type="radio" name="comp<%=countId%>" onclick="Prompt()" value=4>
			</p>
			<p>
				5：どちらとも言えない<input type="radio" name="comp<%=countId%>" onclick="Prompt()" value=5>
			</p>
			<p>
				6<input type="radio" name="comp<%=countId%>" onclick="Prompt()" value=6>
			</p>
			<p>
				7：少しそう思う<input type="radio" name="comp<%=countId%>" onclick="Prompt()" value=7>
			</p>
			<p>
				8<input type="radio" name="comp<%=countId%>" onclick="Prompt()" value=8>
			</p>
			<p>
				9：とてもそう思う<input type="radio" name="comp<%=countId%>" onclick="Prompt()" value=9>
			</p>
			<p>
				10<input type="radio" name="comp<%=countId%>" onclick="Prompt()" value=10>
			</p>

			<!-- 表示非表示切り替え -->
			<div id="firstBox">
				<input type="hidden" name="comp<%=countId%>Comment" value="">
				<div style="text-align: right">
					<div>
						<button type="submit" class="btn btn-success">submit</button>
					</div>
				</div>
			</div>
			<!-- 表示非表示切り替え -->
			<div id="secondBox" style="display:none;">
				<p>何があったのでしょうか。振り返ってみましょう。</p>
				<input type="text" name="comp<%=countId%>Comment">
				<div style="text-align: right">
					<div>
						<button type="submit" class="btn btn-success">submit</button>
					</div>
				</div>
			</div>
		</div>

		<div class="col-xs-2 col-sm-2 col-md-2"></div>
		<input type="hidden" name="countId" value=<%=countId%>>
	</form>
</div>

<script type="text/javascript">
	function Prompt() {
		var value = document.getElementById('comp'<%=countId%>).value;
		if(value < <%=outlierMinimum%> || value > <%=outlierMaximum%>) {
			<!--外れ値に該当していたとき -->
			window.alert("異常値検知!!\n");
			//フォーム
			document.getElementById('firstBox').style.display = "none";
			document.getElementById('secondBox').style.display = "";
		}else{
			<!--外れ値に該当していないとき -->
			//フォーム
			document.getElementById('firstBox').style.display = "";
			document.getElementById('secondBox').style.display = "none";
		}
	}
</script>

</html>