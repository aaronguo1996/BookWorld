<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register | Book World</title>
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
</div>
<br>
<a href="index.jsp"><img src="images/logo-site.png" style="position:relative;left:70px;top:20px"></a>
<form action="search.jsp" method="post" style="border-style:3px #00A39F;position:relative;left:1000px;top:-30px">	    
	<input type="text" name="searchKey" value="Search From Here" style="border-style:1px #00A39F;height:30px;width:300px;position:relative;top:-10px;right:-5px"/>
	<a href="search.jsp"><img src="images/btn-search-go-2x.gif"></a>
</form>
<form style="position:relative;left:70px">
	<input type="button" class="menuButton" value="BestSelling" >
	<input type="button" class="menuButton" value="Novel">
	<input type="button" class="menuButton" value="Literature">
	<input type="button" class="menuButton" value="Poem">
	<input type="button" class="menuButton" value="Prose">
	<input type="button" class="menuButton" value="Study">
	<input type="button" class="menuButton" value="Dictionary">
</form>
<br><br>

<p style="font-size:7pt;position:relative;left:70px"><a href="index.jsp">Home </a> >> <a href="index.jsp"> Register</a></p>
<div>
	<p class="bigTitle">My Profile</p><br><br>
	<table style="margin-left:100px">
		<tr><td><label class="reg">nickname:</label></td><td class="profile"><s:property value='user.account_no'/></td></tr>
		<tr><td><label class="reg">username</label></td><td class="profile"><s:property value='user.userName'/></td></tr>
		<tr><td><label class="reg">password</label></td><td class="profile"><s:property value='user.password'/></td></tr>
		<tr><td><label class="reg">address</label></td><td class="profile"><s:property value='user.address'/></td></tr>
		<tr><td><label class="reg">postcode</label></td><td class="profile"><s:property value='user.postcode'/></td></tr>
		<tr><td><label class="reg">email</label></td><td class="profile"><s:property value='user.email'/></td></tr>
		<tr><td class="profile"></td></tr>
		<tr><td><input class="menuButton" style="margin-left:50px" type="button" value="Edit" onClick="window.location.reload('edit.action')"/></td></tr>
	</table>
</div>
</body>
<s:head />
</html>