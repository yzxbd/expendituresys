<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>家庭消费记录管理系统</title>
    <link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>
<div id="login-box">
    <form action="login" method="post">
        <h1>家庭消费记录系统</h1>
        <div class="form">
            <div class="item">
                <i class="fa fa-user-circle-o" aria-hidden="true">用户名：</i>
                <input type="text" placeholder="Username" required name="username"/>
            </div>
            <div class="item">
                <i class="fa fa-key" aria-hidden="true">密&nbsp;&nbsp;&nbsp;&nbsp;码：</i>
                <input type="password" placeholder="Password" required name="password"/>
            </div>
        </div>
        <button type="submit">登录</button>
    </form>
</div>
</body>
</html>
