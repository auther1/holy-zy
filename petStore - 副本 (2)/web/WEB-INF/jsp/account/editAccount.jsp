<%--
  Created by IntelliJ IDEA.
  User: MrBhite
  Date: 2022/11/4
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="editAccount" method="post">
    <h3>User Information</h3>

    <table>
        <tr>
            <td>User ID:</td>
            <td>${sessionScope.account.username}</td>
        </tr>
        <tr>
            <td>New password:</td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td>Repeat password:</td>
            <td><input type="text" name="repeatedPassword"></td>
        </tr>
        <tr>
            <td colspan="2">${requestScope.errorMsg}</td>
        </tr>
    </table>
    <%@ include file="accountFields.jsp"%>
        <input type="submit" value="Save Account Information">
    </form>
    <a href="listOrders">My Orders</a>

<%@ include file="../common/bottom.jsp"%>
