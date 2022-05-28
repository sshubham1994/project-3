
<%@page import="in.co.rays.proj3.ctl.MarksheetMeritListCtl"%>
<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@page import="in.co.rays.proj3.dto.MarksheetDTO"%>
<%@page import="in.co.rays.proj3.model.ModelFactory"%>
<%@page import="in.co.rays.proj3.model.MarksheetModelInt"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<jsp:useBean id="marksheetdto" class="in.co.rays.proj3.dto.MarksheetDTO"
	scope="request"></jsp:useBean>
<jsp:useBean id="model"
	class="in.co.rays.proj3.model.MarksheetModelHibImpl" scope="request"></jsp:useBean>


<html>
<head>
<title>Marksheet List</title>
<!--    favicon-->
<link rel="shortcut icon"
	href="/ORSProject3/theam_wel/image/fav-icon.png" type="image/x-i">

<style type="text/css">
body {
	background-image:url("/ORSProject3/image/overlay.png"), url("/ORSProject3/image/custem.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}  
video {
	position: fixed;
	top: 50%;
	left: 50%;
	min-width: 100%;
	min-height: 100%;
	width: auto;
	height: auto;
	z-index: -100;
	transform: translateX(-50%) translateY(-50%);
	background: url('') no-repeat;
	background-size: cover;
	transition: 1s opacity;
}

.row, .container-fluid {
	margin-left: 0px !important;
	margin-right: 0px !important;
}

.table-hover tbody tr:hover td {
	background-color: #0064ff36;
}
</style>
</head>

<body>

	<%@include file="Header.jsp"%>

	<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="post">
	
	<!-- <div class="video-wrap">
		<div id="video">
		<video id="bgvid" autoplay loop muted>
		<source src="/ORSProject3/stars.mp4"" type="video/mp4">
		</video></div></div>
 -->
		<br>
		<div class="container">
			<div class="row">
				<div class="panel"
					style="background-color: rgba(248, 222, 210, 0.85); margin-bottom: 150px;">
					<div class="panel-body">
						<div align="center">

							
							<H2>
								<span class="glyphicon glyphicon-list"></span><b> Marksheet
									List</b>
							</H2>
							<hr style="height: 2px; color: #000000;">
						</div>

						<div class="text-center">


							<%
								if (ServletUtility.getSuccessMessage(request).length() > 0) {
							%>

							<div class="text-center col-md-offset-4">
								<h2 style="position: absolute;; margin-top: 10px; top: 240px;"">
									<font color="green"><i style="margin-left: 25px;"><%=ServletUtility.getSuccessMessage(request)%></i></font>
								</h2>
							</div>


							<%
								} else {
							%>

							<div class="text-center col-md-offset-4">
								<h2 style="position: absolute; margin-top: 10px; top: 240px;">
									<font color="brown"><i style="margin-left: 25px;"><%=ServletUtility.getErrorMessage(request)%></i>
									</font>
								</h2>
							</div>
							<%
								}
							%>
						</div>

						<br> <br> <br>

						<div class="container-fluid text-center">
							<div class="form-inline">

								<div class="form-group  text-center">
									<label class="control-label" for="name">Student Name :</label>
									<input type="text" class="form-control" name="name" size=15
										placeholder="Enter Marksheet Name"
										value="<%=ServletUtility.getParameter("name", request)%>">
								</div>
								&emsp;&emsp;


								<div class="form-group text-center">
									<label class="control-label" for="rollNo">Roll No. :</label> <input
										type="text" name="rollNo" class="form-control"
										placeholder="Enter " size=15
										value="<%=ServletUtility.getParameter("rollNo", request)%>">
								</div>

								<div class="form-group">
									<button type="submit" name="operation"
										class=" form-control btn btn-primary"
										value="<%=MarksheetMeritListCtl.OP_SEARCH%>">
										<span class="glyphicon glyphicon-search"></span> Search
									</button>

									<button type="submit" name="operation"
										class=" form-control btn btn-warning"
										value="<%=MarksheetMeritListCtl.OP_RESET%>">
										<span class="	glyphicon glyphicon-refresh"></span> Reset
									</button>

								</div>
							</div>
							<hr>

							<%
							if (userdto.getRoleId() == RoleDTO.ADMIN || userdto.getRoleId() == RoleDTO.FACULTY) {
							%>
							<div style="float: right">
								<div class="col-sm-3" style="margin-left: 4%;">
									<button type="submit" name="operation"
										value="<%=MarksheetMeritListCtl.OP_DELETE%>"
										class="btn btn-danger">
										<span class="glyphicon glyphicon-trash"></span>
										<%=MarksheetMeritListCtl.OP_DELETE%>
									</button>
								</div>
							</div>
							<div style="float: left">
								<div class="col-sm-3">
									<button type="submit" name="operation"
										value="<%=MarksheetMeritListCtl.OP_NEW%>"
										class="btn btn-primary">
										<span class="glyphicon glyphicon-plus"></span>
										<%=MarksheetMeritListCtl.OP_NEW%>
									</button>
								</div>
							</div>

						</div>
						<%
						}
						%>
						<br>


						<%
						List list = ServletUtility.getList(request);
						if (list == null || list.size() == 0) {
						%>
						<table align="center">
							<tr>
								<td>

									<button type="submit" name="operation"
										class=" form-control btn btn-warning"
										value="<%=MarksheetMeritListCtl.OP_BACK%>"
										style="width: 150px; height: 47px; font-size: 16px; background-color: gray;">
										<span style="margin-right: 7px;"
											class="	glyphicon glyphicon-folder-open"></span> Back
									</button>

								</td>

							</tr>
						</table>

						<%
						} else {
						%>


						<div class="box-body table-responsive">

							<table class="table table-bordered table-striped table-hover">
								<thead>
									<tr>
										<th style="text-align: center;"><input type="checkbox"
											id="mainbox" onchange="selectAll(this)"> Select All</th>
										<th style="text-align: center;">S.No.</th>
										<th style="text-align: center;">RollNo</th>
										<th style="text-align: center;">Name</th>
										<th style="text-align: center;">Physics</th>
										<th style="text-align: center;">Chemistry</th>
										<th style="text-align: center;">Maths</th>
										<th style="text-align: center;">Total</th>
										<th style="text-align: center;">Percentage (%)</th>
										<th style="text-align: center;">Grade</th>
										<th style="text-align: center;">Division</th>
										<th style="text-align: center;">Result</th>
										<th style="text-align: center;">Edit</th>

									</tr>
								</thead>



								<%
								MarksheetModelInt s = ModelFactory.getInstance().getMarksheetModel();
								List l = s.list();
								int count = l.size();
								int pageNo = ServletUtility.getPageNo(request);
								int pageSize = ServletUtility.getPageSize(request);
								int index = ((pageNo - 1) * pageSize);

								list = ServletUtility.getList(request);
								Iterator<MarksheetDTO> it = list.iterator();
								while (it.hasNext()) {

									MarksheetDTO dto = it.next();
									String grade = null;
									String result = null;
									long phyMarks = DataUtility.getLong(dto.getPhysics());
									long chemMarks = DataUtility.getLong(dto.getChemistry());
									long MathMarks = DataUtility.getLong(dto.getMaths());
									long totalMarks = (phyMarks + chemMarks + MathMarks);

									float percentage = (float) totalMarks / 3;
									percentage = percentage * 100;
									percentage = Math.round(percentage);
									percentage = percentage / 100;

									if (phyMarks >= 35 && chemMarks >= 35 && MathMarks >= 35) {
										if (totalMarks >= 195) {
									grade = "A";
									result = "Pass";
										} else if (totalMarks < 195 && totalMarks >= 150) {
									grade = "B";
									result = "Pass";
										} else {
									grade = "C";
									result = "Pass";
										}

									} else {
										grade = "D";
										result = "Fail";
									}

									String div = null;
									if (percentage >= 60) {
										div = "First";
									} else if (percentage >= 45 && percentage < 60) {
										div = "Second";
									} else if (percentage >= 33 && percentage < 45) {
										div = "Third";
									} else if (percentage < 33) {
										div = "Fail";
									}
								%>

								<tbody>
									<tr>
										<td align="center"><input type="checkbox" name="ids"
											value="<%=dto.getId()%>"></td>
										<td align="center"><%=++index%></td>
										<td align="center"><%=dto.getRollNo()%></td>
										<td align="center"><%=dto.getName()%></td>
										<td align="center"><%=dto.getPhysics()%></td>
										<td align="center"><%=dto.getChemistry()%></td>
										<td align="center"><%=dto.getMaths()%></td>
										<td align="center"><%=totalMarks%></td>
										<td align="center"><%=percentage%></td>
										<td align="center"><%=grade%></td>
										<td align="center"><%=div%></td>
										<%
										if (div.equalsIgnoreCase("Fail")) {
										%>
										<td align="center" style="color: red;">FAIL</td>
										<%
										} else {
										%>
										<td align="center" style="color: green;">PASS</td>
										<%
										}
										%>

										<td style="size: 20%; text-align: center;">
											<%
											if (userdto.getId() ==1) {
											%> --- <%
											} else {
											%><a href="MarksheetCtl?id=<%=dto.getId()%>"> <span
												class="glyphicon glyphicon-edit"></span></a> <%
 }
 %>
										</td>


										<input type="hidden" name="pageNo" value="<%=pageNo%>">
										<input type="hidden" name="pageSize" value="<%=pageSize%>">

									</tr>
								</tbody>
								<%
								}
								%>


							</table>
						</div>

						<div>

							<div style="float: right">

								<div class="col-sm-4"">
									<button type="submit" name="operation"
										value="<%=MarksheetMeritListCtl.OP_NEXT%>"
										<%=(index == count || list.size() < pageSize) ? "disabled" : ""%>
										class="btn btn-primary"><%=MarksheetMeritListCtl.OP_NEXT%>
										<span class="glyphicon glyphicon-chevron-right"></span>
									</button>
								</div>

							</div>

							<div style="float: left">

								<div class="col-sm-4"">
									<button type="submit" name="operation"
										value="<%=MarksheetMeritListCtl.OP_PREVIOUS%>"
										<%=(pageNo == 1) ? "disabled" : ""%> class="btn btn-primary">
										<span class="glyphicon glyphicon-chevron-left"></span>
										<%=MarksheetMeritListCtl.OP_PREVIOUS%>
									</button>
								</div>

							</div>
						</div>
						<hr>

						<%
						}
						%>
					
	</form>

	<br>
	<%@include file="Footer.jsp"%>
</body>
</html>
