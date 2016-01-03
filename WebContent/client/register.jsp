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
	<input type="button" class="menuButton" value="BestSelling">
	<input type="button" class="menuButton" value="Novel">
	<input type="button" class="menuButton" value="Literature">
	<input type="button" class="menuButton" value="Poem">
	<input type="button" class="menuButton" value="Prose">
	<input type="button" class="menuButton" value="Study">
	<input type="button" class="menuButton" value="Dictionary">
</form>
<br><br>
<%
	Integer tmp = (Integer)session.getAttribute("curr_id");
	if(tmp != null)
		session.removeAttribute("curr_id");
	String t = (String)session.getAttribute("curr_user");
	if(t != null)
		session.removeAttribute("curr_user");
%>
<p style="font-size:7pt;position:relative;left:70px"><a href="index.jsp">Home </a> >> <a href="index.jsp"> Register</a></p>
<div>
	<div style="float:left;width:40%;height:100%">
		<s:form class="info" action="regSuccess" method="post" namespace="/" theme="simple">
			<p class="bigTitle">Create Your Account</p>
			<p class="text">Welcome to Bookworld - the place where books come<br>
			from! To keep this quick and simple we only need your <br>
			name, email, address and a password to register. We'll <br>
			then your details safe, and save you from re- <br>
			entering keep the details the next time you order.</p>
			<label class="reg">nickname</label><label class="error"><s:fielderror><s:param>accError</s:param></s:fielderror></label><br>
			<label><input class="text" type="text" name="user.account_no"/></label><br>
			<label class="reg">username</label><label class="error"><s:fielderror><s:param>nameError</s:param></s:fielderror></label><br>
			<label><input class="text"  type="text" name="user.userName"/></label><br>
			<label class="reg">password</label><label class="error"><s:fielderror><s:param>passwordError</s:param></s:fielderror></label><br>
			<label><input class="text"  type="password" name="user.password"/></label><br>
			<label class="reg">password confirm<br><input class="text" type="password" name="password"/></label><br>
			<label class="reg">address<br><input  class="text" name="user.address"/></label><br>
			<label class="reg">postcode</label><label class="error"><s:fielderror><s:param>postcodeError</s:param></s:fielderror></label><br>
			<label><input  class="text" name="user.postcode"/></label><br>
			<label class="reg">email</label><label class="error"><s:fielderror><s:param>emailError</s:param></s:fielderror></label><br>
			<label><input  class="text" name="user.email"/></label><br>
			<label class="reg">email confirm<br><input  class="text" name="email"/></label><br>
			<input class="menuButton" style="margin-left:300px" type="submit" value="Submit"/>
		</s:form>		
	</div>
	<div style="float:left;width:5%;height:100%;">
		<ul><li style="border:none;height:1100px;width:1px;background-color:#00A39F;list-style-type:none"></li></ul>
	</div>
	<div style="float:left;width:55%;height:100%">
		<s:form action="login" method="post" namespace="/" theme="simple">
			<p class="bigTitle">Already Have An Account</p>
			<p class="text">If you already register for an account<br>
			just log in and enjoy yourself!</p>
			<label class="reg">nickname</label><label class="error"><s:fielderror><s:param>loginError</s:param></s:fielderror></label><br>
			<label><input class="text" type="text" name="userName"/></label><br>
			<label class="reg">password<br><input class="text"  type="password" name="password"/></label><br>
			<input class="menuButton" style="margin-left:300px" type="submit" value="Log In"/>
			<br><img style="position:relative;top:300px;"src="images/background.png"/>
		</s:form>
	</div>
</div>
</body>
<s:head />
</html>