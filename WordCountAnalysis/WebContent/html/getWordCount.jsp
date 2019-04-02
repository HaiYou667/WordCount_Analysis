<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%
	String basePath=request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>统计词频并显示结果</title>
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
		<form action="/WordCountAnalysis/wordcount/wcanalysis?cmd=getfilename"  method="post"  enctype="multipart/form-data">
		
		<hr>
			<table width="80%">
			<tr>
				<td>请粘贴需统计分析文件的绝对路径(.txt)：</td>
				<td><input type="text" name="filename" id="filename" value="${filename}"></td>
				<td><input type="submit" value="确定"></td>
				
			</tr>
			<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			<tr>
				<td>文件路径：${filename}</td>
				<td>单词种类：${ordlistsize}</td>
				<td>耗时：${totaltime}</td>
			</tr>
			<c:forEach items="${ordlist}" var="list">
				<tr>
					<td>${list.key}</td>
					<td>${list.value}</td>
					<td></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td><a href="${pageContext.request.contextPath}/html/getWordCount.html"  target="main">>>返回上一级</a></td>
			</tr>	
		</table>
		<hr>
		</form>
	</div>
</body>
</html>