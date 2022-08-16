<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="../css/videosUpload.css">
    <link rel="stylesheet" type="text/css" href="../datetimepicker/jquery.datetimepicker.css">
</head>
<body>
<div class="main_title">消费记录</div>
<div  class="main_body">
    <div class="nav_title autoH color_909090">
        <label>消费记录</label>
        <span class="jiantou"></span>
        <label class="color_0e6fb6">添加消费记录</label>
    </div>
    <!-- <div class="main video-upload color_666 rows">	 -->
    <div class="main video-upload rows">
        <form id="addForm">
             <%--<div><label>支出类型：</label><span><input type="text" name="name"></span></div>--%>
            <div><label>支出人姓名：</label>
                <span>
					<select id="username" class="select" size="1" name="user_id">
						<%--<option value="1">UID</option>
						<option value="2">JAVA</option>
						<option value="3">WEB</option>--%>
					</select>
                </span>
            </div>
            <div><label>支出类型：</label>
                <span>
					<select id="typename" class="select" size="1" name="type_id">
                        <%--<option value="1">UID</option>
                        <option value="2">JAVA</option>
                        <option value="3">WEB</option>--%>
                    </select>
                </span>
            </div>
            <div><label>详细描述：</label><span><input type="text" name="description"></span></div>
            <div><label>支出资金(元)：</label>
                <span>
					<input id="price" type="text" name="price" />
                </span>
            </div>
             <div>
                 <label>支出时间:</label>
                 <span>
                     <input id="time" type="text" readonly="readonly"/>
                 </span>
             </div>
            <div>
                <input class="btn_blue" type="button" value=添加>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../datetimepicker/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="../js/upload.js"></script>
<script type="text/javascript">
    $(".main_title").on("click",function(){
        $('#iframe', window.parent.document).attr("src","");
    })
</script>

</body>
</html>
