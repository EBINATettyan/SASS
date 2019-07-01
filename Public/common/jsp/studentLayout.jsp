<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String userId = (String) session.getAttribute("userId");
%>

<body>

	<nav class="navbar navbar-inverse">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">slider system</a>
		</div>

		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"> <span
					class="glyphicon glyphicon-align-justify"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#">ユーザ名：<%=userId%></a></li>
					<li><a href="#" onClick="topCheck()">ホーム画面に戻る</a></li>
					<li><a href="#" onClick="check()">ログアウト</a></li>
				</ul></li>
		</ul>
	</nav>

	<script src="../common/bootstrap/js/jquery.js"></script>
	<script src="../common/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function topCheck() {
			if (window.confirm('ホーム画面に戻ります')) {
				location.href = "GoToHomePageUserServlet";
				return true;
			} else {
				window.alert('キャンセルされました');
				return false;
			}
		}
	</script>
	<script type="text/javascript">
		function check() {
			if (window.confirm('ログアウトしてよろしいでしょうか？(ログイン画面に戻ります)')) {
				location.href = "LogoutUserServlet";
				return true;
			} else {
				window.alert('キャンセルされました');
				return false;
			}
		}
	</script>