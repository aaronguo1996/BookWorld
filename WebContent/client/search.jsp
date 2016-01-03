<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Result | Book World</title>
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
<p style="font-size:7pt;position:relative;left:70px"><a href="index.jsp">Home </a> >> <a href="search.jsp"> Book</a></p>
<div>
	<p style="font-size:15pt;position:relative;left:70px">Search Results</p>
	<table style="border:3px solid #00A39F">
				<tr>
					<td class="headNo">No.</td><td class="head">Picture</td><td class="head">ISBN</td><td class="head">Book Name</td>
					<td class="head">Category</td><td class="head">Writer</td><td class="head">Price</td><td class="head">Operation</td>
				</tr>
				<tr>
					<s:iterator value="searchBooks" id="column" status="st">
						<s:if test="%{(#st.index % 2 == 0)}">
							<tr class="odd">
						</s:if>
						<s:else>
							<tr class="even">
						</s:else>
							<td><s:property value="%{#st.index+1}"/></td>
							<td><img src="<s:property value='#column.picture'/>" class="display"/></td>
							<td><s:property value="#column.isbn"/></td>		
							<td><a class="book" href="display.action?id=<s:property value='#column.id'/>"><s:property value="#column.bookName"/></a></td>
							<td><s:property value="#column.catagory"/></td>
							<td><s:property value="#column.writer"/></td>
							<td>$<s:property value="#column.price"/></td>
							<td><a class="tree" href="addToCar.action?bookid=<s:property value='#column.id'/>">BUY</a></td>
						</tr>
					</s:iterator>
				</tr>
			</table>
</div>
</body>
</html>