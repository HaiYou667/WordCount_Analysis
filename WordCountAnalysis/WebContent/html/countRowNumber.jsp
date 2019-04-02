<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%
	String basePath=request.getContextPath()+"/";
%>
<html>
<head>
	<title>统计文本行数</title>
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
		<form action="/WordCountAnalysis/wordcount/wcanalysis?cmd=countRowNumber"  method="post"  
		enctype="multipart/form-data">
		<hr>
			<table width="80%">
			<tr>
				<td>确认--统计文本中的行数?</td>
				<td></td>
				<td><input type="submit" value="确定"></td>
			</tr>
			<tr>
				<td>注：本次查询统计基于功能选项1</td>
				<td>文件：${file}</td>
				<td></td>
			</tr>
			<tr>
				<td>总行数：${rownumber}</td>
				<td>统计耗时:${totaltime}</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><a href="${pageContext.request.contextPath}/html/countRowNumber.html"  target="main">>>返回上一级</a></td>
			</tr>	
		</table>
		<hr>
		</form>
	</div>
</body>
</html>