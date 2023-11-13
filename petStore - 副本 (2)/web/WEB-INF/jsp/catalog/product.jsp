<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../common/top.jsp"%>

<div id="BackLink">
  <a href="category?categoryId=${sessionScope.category.name}">Return to ${sessionScope.category.name}</a>>
</div>

<div id="Catalog">

  <h2>${sessionScope.product.name}</h2>

  <table>
    <tr>
      <th>Item ID</th>
      <th>Product ID</th>
      <th>Description</th>
      <th>List Price</th>
      <th>&nbsp</th>
    </tr>
    <c:forEach var="item" items="${sessionScope.itemList}">
      <tr>
        <td><a href="item?itemID=${item.itemId}">${item.itemId}</a></td>
        <td>${item.product.productId}</td>
        <td>${item.attribute1} ${item.attribute2} ${item.attribute3}
            ${item.attribute4} ${item.attribute5} ${item.product.name}</td>
        <td><fmt:formatNumber value="${item.listPrice}" pattern="$#,##0.00" /></td>
        <td><a href="addItemToCart?workingItemId=${item.itemId}" class="Button">Add to Cart</a></td>
      </tr>
    </c:forEach>
  </table>

</div>

<%@ include file="../common/bottom.jsp"%>