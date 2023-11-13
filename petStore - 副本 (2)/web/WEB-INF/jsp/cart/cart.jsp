<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

    <div id="Cart">

        <h2>CART</h2>
        <form action="updateCart" method="post" id = "cartTable">
            <table>
                <tr>
                    <th><b>Item ID</b></th>
                    <th><b>Product ID</b></th>
                    <th><b>Description</b></th>
                    <th><b>In Stock?</b></th>
                    <th><b>Quantity</b></th>
                    <th><b>List Price</b></th>
                    <th><b>Total Cost</b></th>
                    <th>&nbsp;</th>
                </tr>

                <c:if test="${sessionScope.cart.numberOfItems == 0}">
                    <tr>
                        <td colspan="8"><b>Your cart is empty.</b></td>
                    </tr>
                </c:if>

                <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}" varStatus="loop">
                    <tr class="bg">
                        <td>
                            <a href="item?itemId=${cartItem.item.itemId}" >${cartItem.item.itemId}</a>
                        </td>
                        <td>
                                ${cartItem.item.product.productId}
                        </td>
                        <td>
                                ${cartItem.item.attribute1} ${cartItem.item.attribute2}
                                ${cartItem.item.attribute3} ${cartItem.item.attribute4}
                                ${cartItem.item.attribute5} ${cartItem.item.product.name}
                        </td>
                        <td>
                                ${cartItem.inStock}
                        </td>
                        <td>
<%--                            <input type="text" id="quantity${loop.index}" onblur="updateCart(${loop.index});" name="${cartItem.item.itemId}" value="${cartItem.quantity}">--%>
<%--                            <div id="cartMsg"></div>--%>
<%--                            <script type="text/javascript" src="${pageContext.request.contextPath }/js/updateCart.js"></script>--%>
                            <input type="text" name="${cartItem.item.itemId}" value="${cartItem.quantity}">
                        </td>
                        <td>
                            <!--format标签显示单价fmt:formatNumber-->
                            <fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00" />
                            <!--<input id="price" value="${cartItem.item.listPrice}" pattern="$#,##0.00" />-->

                        </td>
                        <td>
                            <!--format标签显示总价fmt:formatNumber-->
                            <label id="total">${cartItem.total}</label>

						<fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00" />

                        </td>
                        <td>
                            <a class="Button" id="remove" href="removeCartItem?workingItemId=${cartItem.item.itemId}">Remove</a>
                            <!--
                            <input type="button" id="remove" onclick="remove()" value="Remove"> </input>
                            -->
                        </td>
                    </tr>
                    <script src="${pageContext.request.contextPath }/js/cartChange.js"></script>
                </c:forEach>
                <tr id="lastTR">
                    <td colspan="7" id="lastTD">
<%--                        Sub Total:<label id="subtotal">${sessionScope.cart.subTotal}</label>--%>
<%--                        <!--<fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" />-->--%>
<%--                        <!--<input type="submit" value="Update Cart"/>-->--%>
                        Sub Total: <fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" />
                        <input type="submit" value="Update Cart">
                    </td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </form>

        <c:if test="${sessionScope.cart.numberOfItems > 0}">
            <a class="Button" href="checkOut?itemId=${cartItem.item.itemId}">Proceed to Checkout</a>
        </c:if>
    </div>

    <div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/bottom.jsp"%>




<%--
<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

    <div id="Cart">

        <h2>Shopping Cart</h2>
        <form action="updateCart" method="post">
            <table>
                <tr>
                    <th><b>Item ID</b></th>
                    <th><b>Product ID</b></th>
                    <th><b>Description</b></th>
                    <th><b>In Stock?</b></th>
                    <th><b>Quantity</b></th>
                    <th><b>List Price</b></th>
                    <th><b>Total Cost</b></th>
                    <th>&nbsp;</th>
                </tr>

                <c:if test="${sessionScope.cart.numberOfItems == 0}">
                    <tr>
                        <td colspan="8"><b>Your cart is empty.</b></td>
                    </tr>
                </c:if>

                <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                    <tr>
                        <td><a href="item?itemID=${cartItem.item.itemId}">${cartItem.item.itemId}</a></td>
                        <td>${cartItem.item.product.productId}</td>
                        <td>${cartItem.item.attribute1} ${cartItem.item.attribute2}
                                ${cartItem.item.attribute3} ${cartItem.item.attribute4}
                                ${cartItem.item.attribute5} ${cartItem.item.product.name}</td>
                        <td>${cartItem.inStock}</td>
                        <td><input type="text" name="${cartItem.item.itemId}" value="${cartItem.quantity}"></td>
                        <td><fmt:formatNumber value="${cartItem.item.listPrice}"
                                              pattern="$#,##0.00" /></td>
                        <td><fmt:formatNumber value="${cartItem.total}"
                                              pattern="$#,##0.00" /></td>
                        <td><a href="removeCartItem?workingItemId=${cartItem.item.itemId}" class="Button">Remove</a></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="7">Sub Total:
                        <fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" />
                        <input type="submit" value="Update Cart">
                    </td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </form>

        <c:if test="${sessionScope.cart.numberOfItems > 0}">
            <a href="checkOut" class="Button">Proceed to Checkout</a>
        </c:if></div>

    &lt;%&ndash;
    <div id="MyList">
        <c:if test="${sessionScope.accountBean != null}">
            <c:if test="${!sessionScope.accountBean.authenticated}">
                <c:if test="${!empty sessionScope.accountBean.account.listOption}">
                    <%@ include file="IncludeMyList.jsp"%>
                </c:if>
            </c:if>
        </c:if>
    </div>
    -&ndash;%&gt;

    <div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/bottom.jsp"%>--%>
