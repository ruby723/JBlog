<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
	<div id="header">
		<h1>${blogvo.title }</h1>
		<jsp:include page="/WEB-INF/views/blog/admin/include/header.jsp" />
	</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postvo.title }</h4>
					<p>${postvo.contents }<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postlist }" var="i" varStatus="status">
						<li><a href="${pageContext.request.contextPath}/blog/${id}/${i.category_no}/${i.no}">${i.title }</a> <span>${i.reg_date }</span>	</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/${blogvo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categorylist }" var="i" varStatus="status">
					<li><a href="${pageContext.request.contextPath}/blog/${id}/${i.no}">${i.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>