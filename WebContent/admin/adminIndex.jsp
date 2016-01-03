<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Welcome to BOOK WORLD</title>
<link href="css/page.css" rel="stylesheet" type="text/css" />
</head>

<body style="background-color:#D7EDEB">
	<div id="link" align="left" style="margin:0;padding:0;">
		<ul style="list-style-type:none">
			<li class="item"><a href="<s:url action='index'/>">Home</a></li>
			<li class="item"><a href="<s:url action='register'/>">Create Account</a></li>
			<li class="item"><a href="<s:url action='smallLogin'/>">Log in</a></li>
			<li class="item"><a href="about.jsp">About</a></li>
			<li class="last"><a href="help.jsp">Help</a></li>
		</ul>
		<% if(session.getAttribute("admin_name")!=null){ %>
			<p class="welcome" style="position:relative;left:400px"><%=session.getAttribute("admin_name") %>, welcome to bookworld! &nbsp;&nbsp;<a href="<s:url action='logout'/>">Log out</a></p>
		<% }else{}%>
	</div>
	<br>
	<a href="<s:url action='index'/>">
		<img src="images/logo-site.png" style="position:relative;left:70px;top:20px">
	</a>
	<div>
		<div class="left" style="overflow:hidden;zoom:1;">
			<table class="select">
				<tr><td class="content"><br></td></tr>
				<tr><td class="content"><br></td></tr>
				<tr>
					<td class="head">Manage Books</td>
				</tr>
				<tr>
					<td class="context"><a class="tree" href="addBook">Create Book</a></td>
				</tr>
				<tr>
					<td class="context"><a class="tree" href="getBook">Retrieve Book</a></td>
				</tr>
				<tr><td class="content"><br></td></tr>
				<tr><td class="content"><br></td></tr>
				<tr>
					<td class="head">Manage Users</td>
				</tr>
				<tr>
					<td class="context"><a class="tree" href="addUser">Create User</a></td>
				</tr>
				<tr>
					<td class="context"><a class="tree" href="getUser">Retrieve User</a></td>
				</tr>
				<tr><td class="content"><br></td></tr>
				<tr><td class="content"><br></td></tr>
				<tr>
					<td class="head">Statistics</td>
				</tr>
				<tr>
					<td class="context"><a class="tree" href="statUser">By User</a></td>
				</tr>
				<tr>
					<td class="context"><a class="tree" href="statBook">By Book</a></td>
				</tr>
			</table>
			<br>
		</div>
		<div class="right">
			<h1 class="head">Welcome to Book World!</h1>
			<table style="border:3px solid #00A39F">
				<tr>
					<td class="headNo">No.</td><td class="head">Picture</td><td class="head">ISBN</td><td class="head">Book Name</td>
					<td class="head">Category</td><td class="head">Writer</td><td class="head">Price</td><td class="head">Remaining</td><td class="head">Operations</td>
				</tr>
				<tr>
					<s:iterator value="allBooksInPage" id="column" status="st">
						<s:if test="%{(#st.index % 2 == 0)}">
							<tr class="even">
						</s:if>
						<s:else>
							<tr class="odd">
						</s:else>
							<td><s:property value="%{#st.index+1}"/></td>
							<td><img src="<s:property value='#column.picture'/>" class="display"/></td>
							<td><s:property value="#column.isbn"/></td>		
							<td><s:property value="#column.bookName"/></td>
							<td><s:property value="#column.catagory"/></td>
							<td><s:property value="#column.writer"/></td>
							<td>$<s:property value="#column.price"/></td>
							<td><s:property value="#column.remaining"/></td>
							<td>DELETE/UPDATE</td>
						</tr>
					</s:iterator>
				</tr>
			</table>
			<table>
			<%int pageNum = (Integer)session.getAttribute("page"); %>
				<tr>
					<%if(pageNum>1){ %>
					<td><input class="menuButton" style="margin-left:900px;text-align:center" type="button" value="Previous" id="previous" onClick="window.location.reload('adminIndex.action?page=<s:property value='%{page-1}'/>')"/></td>
					<%}
					  if(pageNum<(Integer)session.getAttribute("listSize")&&pageNum==1){
					%>
					<td><input class="menuButton" style="margin-left:1200px;text-align:center" type="button" value="Next" id="next" onClick="window.location.reload('adminIndex.action?page=<s:property value='%{page+1}'/>')"/></td>
					<%}else if(pageNum<(Integer)session.getAttribute("listSize")){ %>
					<td><input class="menuButton" style="margin-left:100px;text-align:center" type="button" value="Next" id="next" onClick="window.location.reload('adminIndex.action?page=<s:property value='%{page+1}'/>')"/></td>
					<%} %>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>