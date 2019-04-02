<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%
	String basePath=request.getContextPath()+"/";
%>
<html>
<head>
	<title>统计给定单词词频</title>
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
		#main{
			/* background-color:eee; */
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.1.min.js"></script>
	 <!-- ECharts单文件引入 -->  
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.js" ></script>
</head>
<body>
	<div class="tablebox">
		<form method="post" enctype="multipart/form-data">
		
	<input type="hidden" name="filename" id="filename" value="${filename}">
		
		<hr>
			<table width="80%">
				<tr>
					<td>请输入想要统计高频单词个数：</td>
					<td><input type="text" name="wordnumber" id="wordnumber" ></td>
					<td><input type="submit" value="确定"></td>
				</tr>
				<tr>
					<td>注：本次查询统计基于功能选项1</td>
					<td>基于文件：${file}</td>
					<td>耗时：${totaltime}</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>高频词个数：${k}</td>
					<td>结果如下：</td>
					<td></td>
				</tr>
				<c:forEach items="${ordlist}" var="list">
				<tr>
					<td>${list.key}</td>
					<td>${list.value}</td>
					<td></td>
				</tr>
				</c:forEach>
				<tr>
					<td>
						<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	                    <!-- <div id="main" style="width: 800px;height:400px;"></div> -->
	                    <script type="text/javascript">
	                        // 基于准备好的dom，初始化echarts实例
	                        var myChart = ech1arts.init(document.getElementById('main'));
	
	                        // 指定图表的配置项和数据
	                       option={
							    xAxis: {
							        type: 'category',
							        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
							    },
							    yAxis: {
							        type: 'value'
							    },
							    series: [{
							        data: [120, 200, 150, 80, 70, 110, 130],
							        type: 'bar'
							    }]
							};

	                        // 使用刚指定的配置项和数据显示图表。
	                        myChart.setOption(option);
	     
	                    </script>
					</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>	
				<tr>
					<td></td>
					<td></td>
					<td><a href="right.html"  target="main">>>返回首页</a></td>
				</tr>		
			</table>
		<hr>
		</form>
	</div>
	
</body>
</html>