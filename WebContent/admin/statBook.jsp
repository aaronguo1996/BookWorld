<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BookWorld-Book Statistics</title>
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
			<s:form action="statBookResult" type="post" namespace="/">
				<table>
					<tr><td><label class="reg" style="font-size:20pt">Please Choose the type you want to calculate by</label><s:select cssStyle="border:3px solid #00A39F;" id = "searchCat" name="searchCat" 
					list="#{'allBook':'allBook','BookName':'BookName','novel':'Novel','literature':'Literature','poem':'Poem','prose':'Prose','study':'Study','dictionary':'Dictionary'}" 
							  listKey = "key"  listValue="value" headerKey="" headerValue="Choose..."/></td></tr>
					<tr><td><label><input class="text"  type="text" name="bookName" value="<s:property value='bookName'/>"/></label><br></td></tr>
					<tr><td><label class="reg">(Format:yyyy-MM-dd) <br>Begin Date: </label><label class="error"><s:fielderror><s:param>beginError</s:param></s:fielderror></label>
					<label><input class="text"  type="text" name="beginDate"/></label>
					<label class="reg">End Date: </label><label class="error"><s:fielderror><s:param>endError</s:param></s:fielderror></label>
					<label><input class="text"  type="text" name="endDate"/></label><br></td></tr>
					<tr><td><input class="menuButton" style="margin-left:80px" type="submit" value="Calculate"/></td></tr>
				</table>
			</s:form>
			<br>
			<table style="margin-left:100px">
				<tr><td class="content"><br></td></tr>
				<tr><td class="content"><br></td></tr>
				<tr><td><p class="bigTitle">Result</p></td></tr>
				<tr><td class="head">Book/Category Name</td><td class="head">Total Book Amount</td></tr>
				<tr class="odd"><td><s:property value="bookName"/></td><td><s:property value="amount"/></td></tr>
				<tr><td class="content"><br></td></tr>
				<tr><td class="content"><br></td></tr>
				<tr><td class="head">Category Name</td><td class="head">Total Book Amount</td></tr>
				<s:iterator value="amountsByCat" id="index" status="st">
					<s:if test="%{(#st.index % 2 == 0)}">
						<tr class="odd">
					</s:if>
					<s:else>
						<tr class="even">
					</s:else>
					<s:if test="%{(#st.index % 6 == 0)}">
						<td>Novel</td><td><s:property value="#index"/></td></tr>
					</s:if>
					<s:elseif test="%{(#st.index % 6 == 1)}">
						<td>Literature</td><td><s:property value="#index"/></td></tr>
					</s:elseif>
					<s:elseif test="%{(#st.index % 6 == 2)}">
						<td>Poem</td><td><s:property value="#index"/></td></tr>
					</s:elseif>
					<s:elseif test="%{(#st.index % 6 == 3)}">
						<td>Prose</td><td><s:property value="#index"/></td></tr>
					</s:elseif>
					<s:elseif test="%{(#st.index % 6 == 4)}">
						<td>Study</td><td><s:property value="#index"/></td></tr>
					</s:elseif>
					<s:elseif test="%{(#st.index % 6 == 5)}">
						<td>Dictionary</td><td><s:property value="#index"/></td></tr>
					</s:elseif>
				</s:iterator>
			</table>
		</div>
	</div>
</body>
</html>