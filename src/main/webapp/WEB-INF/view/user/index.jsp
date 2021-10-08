<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Head -->
    <%@include file="../include/head.jspf" %>
</head>
<body>

<div id="layout">
    <!-- Menu toggle -->
    <%@include file="../include/toggle.jspf" %>

    <!-- Menu -->
    <%@include file="../include/menu.jspf" %>

    <div id="main">
        <div class="header">
            <h1>User</h1>
            <h2>CRUD</h2>
        </div>
		
		<table>
			<td valign="top">
				<!-- User 表單 -->
				
			</td>
			<td valign="top">
				<!-- User 列表 -->
			</td>
		</table>
        
    </div>
</div>

<!-- Foot -->
<%@include file="../include/foot.jspf" %>

</body>
</html>