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
		<input type="button" class="menuButton" value="BestSelling" >
		<input type="button" class="menuButton" value="Novel" >
		<input type="button" class="menuButton" value="Literature">
		<input type="button" class="menuButton" value="Poem">
		<input type="button" class="menuButton" value="Prose">
		<input type="button" class="menuButton" value="Study">
		<input type="button" class="menuButton" value="Dictionary">
	</form>
	<br><br>
	<p style="font-size:7pt;position:relative;left:70px"><a href="index.jsp">Home </a> >> <a href="index.jsp"> BookWorld</a></p>
	<div>
		<div class="left" style="overflow:hidden;zoom:1;">
			<table>
			</table>
		</div>
		<div class="right" style="margin-left:200px">
			<h1 class="head">Welcome to Book World!</h1>
			<table style="border:3px solid #00A39F">
				<tr>
					<td class="head">New Releases</td>
				</tr>
				<tr>
					<s:iterator value="newBooks" id="column" status="st">
						<s:if test="%{#st.index % 4 == 0}">
							<tr>
						</s:if><s:else></s:else>
						<td>
							<br>
							<table style="margin-left:50px">
								<tr><td class="displayImg"><a href="display.action?id=<s:property value='#column.id'/>"><img src="<s:property value='#column.picture'/>" class="display"/></a></td></tr>							
								<tr><td class="displayHead"><a class="book" href="display.action?id=<s:property value='#column.id'/>"><s:property value="#column.bookName"/></a></td></tr>
								<tr><td class="display">by <s:property value="#column.writer"/></td></tr>
								<tr><td class="displayPrice">You Pay $<s:property value="#column.price"/></td></tr>	
								<% if(session.getAttribute("curr_id")!=null){%>
								<tr><td class="displayPrice"><input type="button" class="buyButton" value="Buy" onClick="location.href='addToCar.action?bookid=<s:property value='#column.id'/>'"/></td></tr>
								<% }else{ %>
								<tr><td class="displayPrice"><input type="button" class="buyButton" value="Buy" onClick="location.href='<s:url action='smallLogin'/>'"/></td></tr>
								<% } %>			
							</table>
						</td>
						<s:if test="%{#st.index % 4 == 3}">
							</tr>
						</s:if><s:else></s:else>
					</s:iterator>
				</tr>			
			</table>
		</div>
	</div>
</body>
</html>