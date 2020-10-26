<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberForm</title>
<style type="text/css">
td input[id] {
	text-align: center;
	width: 95%;
	border: 0;
}
</style>
</head>
<body>
	<jsp:include page="../menu/mainMenu.jsp" />
	<hr>
	<div align="center">
		<div>
			<h1>회원가입</h1>
		</div>
		<div>
			<form id="frm" name="frm" method="post" action="memberInsert.do">
				<table border="1" style="border-collapse: collapse;">
					<tr>
						<th width="150">아이디</th>
						<td width="200" align="center"><input type="text" id="id"
							name="id"></td>
					</tr>
					<tr>
						<th width="150">패스워드</th>
						<td width="200" align="center"><input type="password"
							id="pwd" name="pwd"></td>
					</tr>
					<tr>
						<th width="150">이름</th>
						<td width="200" align="center"><input type="text" id="name"
							name="name"></td>
					</tr>
					<tr>
						<th width="150">주소</th>
						<td width="200" align="center"><input type="text"
							id="address" name="address"></td>
					</tr>
					<tr>
						<th width="150">전화번호</th>
						<td width="200" align="center"><input type="text" id="tel"
							name="tel"></td>
					</tr>
					<tr>
						<th width="150">가입일</th>
						<td width="200" align="center"><input type="date" id="enterdate"
							name="enterdate"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="가입하기">&nbsp;&nbsp;
							<input type="reset" value="취소">

						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>