<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: MrBhite
  Date: 2022/11/5
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<h2>My Orders</h2>

<table>
  <tr>
    <th>Order ID</th>
    <th>Date</th>
    <th>Total Price</th>
  </tr>

  <c:forEach var="order" items="${sessionScope.orderList}">
    <tr>
      <td>
        <a href="viewOrder?orderId=${order.orderId}">${order.orderId}</a>
      </td>
      <td>
        <fmt:formatDate value="${order.orderDate}" pattern="yyyy/MM/dd hh:mm:ss" />
      </td>
      <td><fmt:formatNumber value="${order.totalPrice}" pattern="$#,##0.00" />
      </td>
    </tr>
  </c:forEach>
</table>

<%@ include file="../common/bottom.jsp"%>


