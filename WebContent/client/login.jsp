<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log in</title>
<link href="css/page.css" rel="stylesheet" type="text/css" />
</head>
<body style="background-color:#D7EDEB">
	<div style="float:left;width:35%;height:100%;border-right:1px solid #00A39F">
		<s:form action="login" method="post" namespace="/" theme="simple">
			<p class="bigTitle">I'm A Shopper</p>
			<p class="text">If you already register for an account<br>
			just log in and enjoy yourself!</p>
			<label class="reg">nickName</label><label class="error"><s:fielderror><s:param>loginError</s:param></s:fielderror></label><br>
			<label><input class="text" type="text" name="userName"/></label><br>
			<label class="reg">password<br><input class="text"  type="password" name="password"/></label><br>
			<input class="menuButton" style="margin-left:300px" type="submit" value="Log In"/><br><br>
			<label class="reg">If you don't have an account, please click <a href="<s:url action='register'/>">here</a></label>
		</s:form>
	</div>
	<div style="float:left;width:55%;height:100%;margin-left:100px;">
		<s:form action="adminLogin" method="post" namespace="/" theme="simple">
			<s:hidden name="page" value="1"/>
			<p class="bigTitle">I'm An Administrator</p>
			<p class="text">Manage your bookstore from here!</p>
			<label class="reg">adminName</label><label class="error"><s:fielderror><s:param>adminLoginError</s:param></s:fielderror></label><br>
			<label><input class="text" type="text" name="adminName"/></label><br>
			<label class="reg">password<br><input class="text"  type="password" name="adminPassword"/></label><br>
			<input class="menuButton" style="margin-left:300px" type="submit" value="Log In"/><br><br>
		</s:form>
	</div>
</body>
</html>