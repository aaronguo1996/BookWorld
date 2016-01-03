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
			<p class="welcome" style="position:relative;left:600px"><%=session.getAttribute("curr_user") %>, welcome to bookworld! &nbsp;&nbsp;<a href="<s:url action='shoppingCar'/>">My shopping cart </a> | <a href="<s:url action='logout'/>">Log out</a></p>
		<% }else{ %>
			<p class="welcome" style="position:relative;left:600px">Please log in to get more information!</p>
		<% } %>
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
	<p style="font-size:7pt;position:relative;left:70px"><a href="index.jsp">Home </a> >> <a href="displayOrder.action"> My Orders</a></p>
	<div>
		<div class="left"></div>
		<div class="right">
			<table style="margin-left:70px;">
				<s:iterator value="orders" id="index" status="status">
					<tr style="height:50px" class="odd" >
						<td colspan="2" style="text-align:left">Date: <s:property value="#index.Date"/></td>
					</tr>
					<tr>
						<td class="headNo">No.</td><td class="head">Picture</td><td class="head">Book Name</td>
						<td class="head">Price</td><td class="head">Quantity</td><td class="head">Operations</td>
					</tr>
					<s:iterator value="orderBooks[#status.index]" id="column" status="st">
							<s:if test="%{#st.index % 2 == 0}">
								<tr class="odd">
							</s:if>
							<s:else>
								<tr class="even">
							</s:else>
								<td><s:property value="%{#st.index+1}"/></td>
								<td><img class="display" src="<s:property value='key.picture'/>"/></td>
								<td><s:property value="key.bookName"/></td>
								<td>$ <s:property value="key.price"/></td>
								<td><s:property value="value"/></td>
								<s:if test="%{#st.index % orderBooks[#status.index].size() == 0}">
								<td rowspan="<s:property value='orderBooks[#status.index].size()'/>"><a class="car" href="deleteOrder?orderid=<s:property value='#index.id'/>">Delete</a></td>
								</s:if>
							</tr>
					</s:iterator>
					<tr>
						<td class="head"></td>
						<td class="head"></td>
						<td class="head"></td>
						<td class="head"></td>
						<td class="head" sytle="position:relative;:900px"colspan="2">Total Price: $<s:property value="#index.totalMoney"/></td>
					</tr>
					<tr><td><br></td></tr>
				</s:iterator>
			</table>
		</div>
	</div>
</body>
</html>