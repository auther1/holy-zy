<%--
  Created by IntelliJ IDEA.
  User: MrBhite
  Date: 2022/10/31
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</div>

    <div id="Footer">

        <div id="PoweredBy">
            &nbsp<a href="http://www.yuanshen.com">www.csu.edu.cn</a>
        </div>

        <div id="Banner">
            <c:if test="${sessionScope.account!=null}">
                <c:if test="${sessionScope.account.bannerOption}">
                    ${sessionScope.account.bannerName}
                </c:if>
            </c:if>
        </div>

    </div>

<script src="js/productAuto.js"></script>
</body>
</html>