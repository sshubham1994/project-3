<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@page import="java.util.Calendar"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
html {
	height: 100%;
	box-sizing: border-box;
}

*, *:before, *:after {
	box-sizing: inherit;
}

body {
	position: relative;
	margin: 0;
	min-height: 100%;
}

.footer {
	position: fixed;
	right: 0;
	bottom: 0;
	color: gainsboro;
	left: 0;
	display: block;
	/*  padding: 0.5rem; */
	box-shadow: 0px 0px 0px 0px #244a4a;
	text-align: center;
	z-index: 2500;
}
</style>

<title></title>
</head>
<body>

<!-- <div class="video-wrap">
		<div id="video">
		<video id="bgvid" autoplay loop muted>
		<source src="/ORSProject3/stars.mp4"" type="video/mp4">
		</video></div> -->

	<%
		Calendar c = Calendar.getInstance();
	%>

	<div class="footer"
		style="background-color: rgba(28, 35, 49, 0.85); height: 40px;">
		<p style="font-size: 16px; padding-top: 10px;">
			Copyright &copy; <%=c.getWeekYear()%> RAYS Technologies  <font color="wheat">
				</font> </b>
		</p>
	</div>



</body>
</html>