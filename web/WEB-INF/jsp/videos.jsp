<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>家庭消费记录系统</title>
    <link rel="stylesheet" type="text/css" href="../css/videos.css">
</head>
<body>
<!-- 头部logo	 -->
<h2>一只小笨蛋</h2>
<!-- 主体内容 -->
<div class="main">
    <!-- 左侧导航 -->
    <div class="main_left">
        <div class="li_title">用户管理<span class="down"></span></div>
        <ul>
            <li data-src="/expendituresys/user/user-admin">家庭成员</li>
        </ul>
        <div class="li_title">消费记录<span class="down"></span></div>
        <ul>
            <li data-src="/expendituresys/record/videos-upload">添加消费记录</li>
            <li data-src="/expendituresys/record/videos-list">查看消费记录</li>
        </ul>

    </div>
    <!-- 右侧内容 -->
    <div class="main_right">
        <iframe frameborder="0" scrolling="yes" style="background:url('../images/welcome.png') no-repeat; background-size: 100%;" src="" id="iframe"></iframe>
    </div>
</div>
<!-- 尾部签名 -->
<div class="footer">
    <ul>
        <li>关于我们</li>
        <li>最新动态</li>
        <li>代理合作</li>
        <li>一只小笨蛋有限公司</li>
        <li>@2022</li>
        <li>京ICP备</li>
        <li>12345678号</li>
    </ul>
</div>
</body>
</html>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/videos.js"></script>

