<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BookWorld-Add user</title>
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
			<p class="welcome" style="position:relative;left:600px"><%=session.getAttribute("admin_name") %>, welcome to bookworld! &nbsp;&nbsp;<a href="<s:url action='logout'/>">Log out</a></p>
		<% }else{}%>
	</div>
	<br>
	<a href="<s:url action='index'/>">
		<img src="images/logo-site.png" style="position:relative;left:70px;top:20px">
	</a>
	<form action="search.jsp" method="post" style="border-style:3px #00A39F;position:relative;left:1000px;top:-30px">	    
		<input type="text" name="searchKey" value="Search From Here" style="border-style:1px #00A39F;height:30px;width:300px;position:relative;top:-10px;right:-5px"/>
		<a href="search.jsp">
			<img src="images/btn-search-go-2x.gif">
		</a>
	</form>
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
			<s:form action="updateUserResult" type="post" namespace="/">
				<label class="reg">nickname</label><label class="error"><s:fielderror><s:param>accError</s:param></s:fielderror></label><br>
				<label><input class="text" type="text" name="user.account_no" value="<s:property value='user.account_no'/>"/></label><br>
				<label class="reg">username</label><label class="error"><s:fielderror><s:param>nameError</s:param></s:fielderror></label><br>
				<label><input class="text"  type="text" name="user.userName" value="<s:property value='user.userName'/>"/></label><br>
				<label class="reg">password</label><label class="error"><s:fielderror><s:param>passwordError</s:param></s:fielderror></label><br>
				<label><input class="text"  type="password" name="user.password" value="<s:property value='user.password'/>"/></label><br>
				<label class="reg">address<br><input  class="text" name="user.address" value="<s:property value='user.address'/>"/></label><br>
				<label class="reg">postcode</label><label class="error"><s:fielderror><s:param>postcodeError</s:param></s:fielderror></label><br>
				<label><input  class="text" name="user.postcode" value="<s:property value='user.postcode'/>"/></label><br>
				<label class="reg">email</label><label class="error"><s:fielderror><s:param>emailError</s:param></s:fielderror></label><br>
				<label><input  class="text" name="user.email" value="<s:property value='user.email'/>"/></label><br>
				<input class="menuButton" style="margin-left:80px" type="submit" value="Update"/>
				<input class="menuButton" style="margin-left:50px" type="button" value="Give Up" onClick="window.location.reload('getUser.action')"/>
			</s:form>
		</div>
	</div>
</body>
</html>