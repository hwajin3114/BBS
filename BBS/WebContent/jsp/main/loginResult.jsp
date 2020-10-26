<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginResult</title>
</head>
<body>
	<jsp:include page="../menu/mainMenu.jsp"></jsp:include>
	<hr>
	<div align="center">
		<!-- request객체는 자바 객체가 아니라 서블릿 객체이므로 vo.getName()이 아니라 vo.name(EL표현식)으로 가져와야한다. -->
		<c:if test="${vo.name eq null }">
			<h1>로그인에 실패하셨습니다.</h1>
		</c:if>
		<!-- ne : not equal : != -->
		<!-- jstl 中 c:if에는 else가 없어서 잘 안씀 -->
		<!-- param : 서버가 request 객체를 만들때 가지고 있던 값. form에서 보낸 값 -->
		<!-- vo : 결과값을 새로 담은 값. 내가 만든 변수 -->
		<c:if test="${vo.name ne null }">
			<h1>${vo.name }님 어서오세요!</h1>
			<!-- 
			<h3>아이디 : ${vo.id }</h3>
			<h3>이름 : ${vo.name }</h3>
			<h3>주소 : ${vo.address }</h3>
			<h3>전화 번호 : ${vo.tel }</h3>
			<h3>가입 일자 : ${vo.enterdate }</h3>
			<h3>권한 : ${vo.author }</h3>
			 -->
		</c:if>
	</div>
</body>
</html>