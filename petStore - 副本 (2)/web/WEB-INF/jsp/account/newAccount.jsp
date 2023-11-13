<%--
  Created by IntelliJ IDEA.
  User: MrBhite
  Date: 2022/11/4
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="Catalog">
    <form action="register" method="post">

    <h3>User Information</h3>

    <table>
        <tr>
            <td>User ID:</td>
            <td>
                <input type="text" name="username" id="username"  onblur="usernameIsExist();"/>
                <div id="usernameMsg"></div>
                <script type="text/javascript" src="${pageContext.request.contextPath }/js/register.js"></script>
            </td>
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
            <td>CheckCode:</td>
            <td class="inputs">
                <input name="checkCode" type="text" id="checkCode">
                <img id="checkCodeImg" src="checkCode">
                <a href="#" id="changeImg" >Change</a>
            </td>

        </tr>
        <tr>
            <td colspan="2">${requestScope.errorMsg}</td>
        </tr>

    </table>

    <%@ include file="accountFields.jsp"%>

    <stripes:submit name="newAccount" value="Save Account Information" />
        <input type="submit" value="Register Now">

    </form>

</div>

<script>
    document.getElementById("changeImg").onclick = function () {
        document.getElementById("checkCodeImg").src = "checkcode?"+new Date().getMilliseconds();
    }

</script>
<%@ include file="../common/bottom.jsp"%>