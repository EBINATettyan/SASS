<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="beans.Date"%>
<%@ page import="java.util.ArrayList"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Date> dateList = (ArrayList<Date>) session.getAttribute("dateList");
%>
<!DOCTYPE html>
<html>
<head>
<link href="../common/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>日時選択画面</title>
</head>

<div class="container">

	<jsp:include page="/Public/common/jsp/studentLayout.jsp" />

	<div class="col-xs-2 col-sm-2 col-md-2"></div>

	<div class="col-xs-8 col-sm-8 col-md-8">
		<div class="alert alert-default" role="alert">
			<h3>該当する日時を選択してください</h3>
		</div>
		<form action="GoToSliderPageUserServlet" method="POST">
			<table class="table">
				<thead>
					<tr>
						<th>タイトル</th>
						<th>日付け</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%
						for (int i = 1; i <= dateList.size(); i++) {
							out.println("<tr>");

							out.println("<td>");
							out.println(dateList.get(i - 1).getTitle());
							out.println("</td>");

							out.println("<td>");
							out.println(dateList.get(i - 1).getTime());
							out.println("</td>");

							out.println("<td>");
							out.println("<button type='submit' name='dateId' value=" + dateList.get(i - 1).getId()
									+ ">進む</button>");
							out.println("</td>");

							out.println("</tr>");
						}
					%>
				</tbody>
			</table>
		</form>
	</div>
	<div class="col-xs-2 col-sm-2 col-md-2"></div>
</div>

</html>