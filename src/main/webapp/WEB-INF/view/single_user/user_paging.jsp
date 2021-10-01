<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
    <head>
        <!-- Head -->
        <%@include file="../include/head.jspf"  %>
    </head>
    <body style="padding: 10px">

        <div id="layout">
            <!-- Toggle -->
            <%@include file="../include/toggle.jspf"  %>

            <!-- Menu -->
            <%@include file="../include/menu.jspf"  %>

            <div id="main">
                <div class="header">
                    <h1>User Paging</h1>
                </div>
                <table class="pure-table" style="border: none;">
                    <td valign="top">
                        <!-- 列表 -->
                        <form class="pure-form">
                            <fieldset>
                                <legend>User list</legend>
                                <select name="no" onchange="window.location.href = '${pageContext.request.contextPath}/mvc/user/page/' + this.value;">
                                    <c:forEach var="p" begin="1" end="${ pages }">
                                        <option value="${ p-1 }" ${p-1 == pageNo ? 'selected':''} >${ p }</option>
                                    </c:forEach>
                                </select>
                                <p />
                                <table class="pure-table pure-table-bordered" width="100%">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Password</th>
                                            <th>Birth</th>
                                            <th>Age</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach var="u" items="${ users }">
                                            <tr>
                                                <td>${ u.id }</td>
                                                <td>${ u.name }</td>
                                                <td>${ u.password }</td>
                                                <td>${ u.birth }</td>
                                                <td><c:catch var ="e">${ u.age }</c:catch></td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table> 
                            </fieldset>
                        </form>
                    </td>
                </table>   


            </div>
        </div>

        <!-- Foot -->
        <%@include file="../include/foot.jspf"  %>

    </body>
</html>