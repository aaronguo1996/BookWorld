<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><s:property value="book.bookName"/></title>
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
		<% if(session.getAttribute("curr_id")!=null){ %>
			<p class="welcome" style="position:relative;left:400px"><%=session.getAttribute("curr_user") %>, welcome to bookworld! &nbsp;&nbsp;
			<a href="<s:url action='displayOrder'/>">My orders </a> | <a href="<s:url action='shoppingCar'/>">My shopping cart </a>
			 | <a href="<s:url action='displayProfile'/>">My profile</a> | <a href="<s:url action='logout'/>">Log out</a></p>
		<% }else{ %>
			<p class="welcome" style="position:relative;left:600px">Please log in to get more information!</p>
		<% } %>
	</div>
	<br>
	<a href="<s:url action='index'/>">
		<img src="images/logo-site.png" style="position:relative;left:70px;top:20px">
	</a>
	<form action="search" method="post" style="border-style:3px #00A39F;position:relative;left:1000px;top:-30px">	    
		<input type="text" name="searchKey" value="Search From Here" style="border-style:1px #00A39F;height:30px;width:300px;position:relative;top:-10px;right:-5px"/>
		<input class="search"type="submit" value="Search"/>
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
	<p style="font-size:7pt;position:relative;left:70px"><a href="index.jsp">Home </a> >> <a href="prose.jsp"> <s:property value="book.catagory"/></a></p>
	<div class="leftOne">
		<table>
			<tr>
				<td class="displayImgOne"><img class="displayOne" src="<s:property value='book.picture'/>"/></td>
			</tr>
		</table>
	</div>
	<div class="middleOne">
		<table style="margin-left:50px">
			<tr><td class="displayHeadOne"><s:property value="book.bookName"/></td></tr>
			<tr><td class="displayWriterOne">by <s:property value="book.writer"/></td></tr>
			<tr><td class="display"/></tr>
		</table>
		<table style="margin-left:50px">
			<tr>
				<td class="displayPriceOne">You Pay $ <s:property value="book.price"/></td>
			    <td class="displayPrice"><input type="button" class="buyButton" value="Buy Now" onClick="location.href='addToCar.action?bookid=<s:property value='book.id'/>'"/></td>
			</tr>
			<tr>
				<td class="displayWriterOne">Preview</td>
				<td class="displayContent"/>
			</tr>
		</table>
	</div>
	<div class="rightOne">
		<table>
			<tr><td class="displayWriterOne">Detailed Infomation</td></tr>
			<tr><td class="displayOne">ISBN: <s:property value="book.isbn"/></td></tr>
			<tr><td class="displayOne">Catagory: <s:property value="book.catagory"/></td></tr>
			<tr><td class="displayOne">Publication Year: <s:property value="book.date"/></td></tr>
			<tr><td class="displayOne">Publisher: <s:property value="book.publisher"/></td></tr>
			<tr><td class="displayOne">Remaining: <s:property value="book.remaining"/></td></tr>
		</table>
	</div>
</body>
</html>