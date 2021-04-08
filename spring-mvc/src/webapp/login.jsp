<%--
  Created by IntelliJ IDEA.
  User: 百无禁忌
  Date: 2021/3/3
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ${message}
    <form action="/main" method="post">
        用户名：<input name="username"/><br/>
        密&nbsp;码：<input name="password" type="password"/><br/>
        <input type="submit" value="登录">
    </form>
</body>
</html>
