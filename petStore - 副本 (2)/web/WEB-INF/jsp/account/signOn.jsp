<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/top.jsp"%>
<%--用户名输入错误警告
<c:if test="${requestScope.errorMsg ne null}">
    <c:out value="${requestScope.errorMsg}"></c:out>
</c:if>
--%>

<div id="Catalog">
    <form action="signOn" method="post">
        <%--用户名输入错误警告--%>
        <p>${requestScope.errorMsg}</p>
        <p>请输入用户名、密码以及验证码</p>
        <p>
            <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="username"  />
            </tr>
            <tr>
                <td>密码：</td>
                <td><input id="inputpswd" type="password" name="password"  />
                    <img id="passwordSeeImg" src="images/close.png" alt="" style="
                        height: 20px;
                        width: 24px;
                        position: relative;
                        top: 4px;
                        right: 30px;
                        ">
                </td>
            </tr>
            <tr>
                <td>验证码：</td>
                <td><input name="checkCode" type="text" id="checkCode"></td>
            </tr>
            </table>

            <br/>

            <img id="checkCodeImg" src="checkCode" alt="???">
            <a href="#" id="changeImg" >看不清？换一个！</a>
        </p>
        <input type="submit" value="Login">

    </form>
    没有账户?
    <a href="newAccount">点击注册!</a>
</div>
<script>
    document.getElementById("changeImg").onclick = function () {
        document.getElementById("checkCodeImg").src = "checkCode?"+new Date().getMilliseconds();
    }

    var img=document.getElementById("passwordSeeImg");
    var input=document.querySelector("#inputpswd");
    var flag=0;
    img.onclick=function(){
        if(flag){
            input.type="text";
            img.src="images/open.png";
            flag=0;
        }else{
            input.type="password";
            img.src="images/close.png";
            flag=1;
        }
    }

</script>

<%@ include file="../common/bottom.jsp"%>