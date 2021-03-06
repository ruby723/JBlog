<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js" type = "text/javascript" ></script>
	
<script>
$(function(){
	btn = $('#btn-check');
	btn.click(function(){
		var id = $("#id").val();
		if(id == ""){
			return;
		}
		
		$.ajax({
			url: "/blog03/user/api/checkid?id=" + id,
			type: "get",
			dataType: "json",
			
			error:function(xhr,status,e) {
				console.error(status,e);
			},
			
			success:function(response) {
				console.log(response);
				
				if(response.result != "success"){
					console.error(response.message);
					reutrn;
				}
				
				if(response.data){
					alert("존재하는 아이디입니다. 다른 아이디를 사용해주세요.");
					$("#id").val("");
					$("#id").focus("");
					return;
				}
				$("#btn-check").hide();
				$("#img-check").show();
			}
		});
	});
});
</script>

</head>
<body>
	<div class="center-content">
		<h1 class="logo" style="background-image:url(${pageContext.request.contextPath}/assets/images/logo.jpg)">JBlog</h1>
		<ul class="menu">
			<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
			<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath}/blog">내블로그</a></li>
		</ul>
		<form:form  class="join-form" 
					id="join-form" method="post" 
					modelAttribute="userVo" 
					action="${pageContext.request.contextPath}/user/join">
					
			<label class="block-label" for="name">이름</label>
			
			<form:input path="name" />
			
			<div style="color:#f00; text-align:left; padding-left:0"><form:errors path="name"/></div>
			
			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id"/>
			
			<form:button />
			<input type="button" id="btn-check"value="중복체크">
			<img id="img-check" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<div style="color:#f00; text-align:left; padding-left:0">
				<form:errors path="id" />
			</div>
					
			<label class="block-label" for="password"><spring:message code="user.join.label.password" /></label>
			<form:password path="password"/>
			<div style="color:#f00; text-align:left; padding-left:0">
				<form:errors path="password" />
			</div>
					
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
