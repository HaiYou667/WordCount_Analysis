<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%
	String basePath=request.getContextPath()+"/";
%><html>
<head><title>统计文本中的字符数</title>
	<meta charset="UTF-8">
	<style type="text/css">
		body{
			background-color: #ccc;
			background-image: url(../img/mainbg.jpg);
			background-size: 120%;
		}
		.tablebox{
			/* background-color: #00f; */
			width: 700px;
			height: 400px;
			margin-left: 300px;
			margin-top: 60px;
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.1.min.js"></script>
</head>
<body>
	<div class="tablebox">
		<form method="post" enctype="multipart/form-data">
		
	<input type="hidden" name="filename" id="filename" value="${filename}">
		
		<hr>
			<table width="80%">
			<tr>
				<td>确定--统计文本中的各类字符数？</td>
				<td></td>
				<td><input type="submit" value="确定"></td>
			</tr>
			<tr>
				<td>注：本次查询统计基于功能选项1</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>统计结果：${count}</td>
				<td>基于文件：${file}</td>
				<td>耗时：${totaltime}</td>
			</tr>
			<tr>
				<td>字母：${letter}</td>
				<td>数字：${digit}</td>
				<td></td>
			</tr>
			<tr>
				<td>空格：${blank}</td>
				<td>其他：${other}</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><a href="${pageContext.request.contextPath}/html/countCharacterNumber.html"  target="main">>>返回上一级</a></td>
			</tr>		
		</table>
		<hr>
		</form>
	</div>
</body>
</html>