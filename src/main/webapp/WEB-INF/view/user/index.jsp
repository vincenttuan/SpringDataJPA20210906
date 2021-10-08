<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Head -->
    <%@include file="../include/head.jspf" %>
</head>
<body style="padding: 20px">

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
				<form:form class="pure-form"
						   modelAttribute="user"
						   method="POST"
						   action="${ pageContext.request.contextPath }/mvc/user/">
					<fieldset>
						<legend>User form</legend>
						<form:input path="id" readonly="true" /><p />
						<form:input path="name" placeholder="請輸入使用者名稱" /><p />
						<form:input path="password" placeholder="請輸入密碼" /><p />
						<form:input path="birth" type="date" /><p />
						
						<button type="submit" 
								class="pure-button pure-button-primary">Submit</button>
						<button type="reset"
								class="pure-button pure-button-primary">Reset</button>
					</fieldset>
					
				</form:form>
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