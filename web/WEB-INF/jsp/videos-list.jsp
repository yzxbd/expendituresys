<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="../css/videosList.css">
    <link rel="stylesheet" type="text/css" href="../css/jquery.page.css">
    <link rel="stylesheet" type="text/css" href="../datetimepicker/jquery.datetimepicker.css">
</head>
<body>
<div class="normal">
    <div class="main_title">消费记录</div>
    <div  class="main_body">
        <div class="nav_title autoH color_909090">
            <label>消费记录</label>
            <span class="jiantou"></span><label class="color_0e6fb6">查看消费记录</label>
        </div>
        <!-- 右侧内容 -->
        <div class="content">
            <form id="searchForm" style="display:inline;">
                <!-- 选项组 -->
                <div class="options">
                    <div>
                        <label>消费类型:</label>
                        <span>
                            <input class="find" type="text" name="type_id" placeholder="请输入信息">
                        </span>
                    </div>
                    <div>
                        <label>支出人:</label>
                        <span>
                            <input class="find" type="text" name="user_id" placeholder="请输入信息">
                        </span>
                    </div>
                    <div>
                        <label>支出时间:</label>
                        <span>
                            <input class="find" type="text" name="time" placeholder="请输入信息">
                        </span>
                    </div>
                    <div>
                        <input type="button" class="datetime" value="-请选择-">
                        <input type="hidden" name="page" id="currentPage" value="1"/>
                    </div>
                    <div class="search">搜索</div>
                </div>
            </form>

            <div class="c_main">
                <table id="pTb" cellspacing="0" cellpadding="0">
                    <tr class="thead">

                        <td class="col_10">ID</td>
                        <td class="col_12">消费类型</td>
                        <td class="col_12">支出金额</td>
                        <td class="col_12">支出人</td>
                        <td class="col_10">支出时间</td>
                        <td>操作</td>
                    </tr>
                   <%-- <tr>

                        <td class="col_10">001</td>
                        <td class="col_12">UID</td>
                        <td class="col_20">PS CC 2017启动界面实现方式</td>
                        <td class="col_12">¥35.00</td>
                        <td class="col_12">会飞的猪</td>
                        <td class="col_10">2017.8.21</td>
                        <td><span class="edit"></span>/<span class="delete"></span></td>
                    </tr>--%>
                </table>
                <!-- 分页 -->
                <%--<div class="Page navigation">
                    <ul class="pagination">
                        <li><span class="prev">上一页</span></li>
                        <li><span class="curr">1</span></li>
                        <li><span>2</span></li>
                        <li><span>3</span></li>
                        <li><span>4</span></li>
                        <li><span>5</span></li>
                        <li><span class="next">下一页</span></li>
                    </ul>--%>
                    <div class="Page navigation">
                        <div class="tcdPageCode page_1"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 遮罩层 -->
<div class="editShade hide">
    <div class="shade">
    </div>
    <div class="shade_main rows">
        <div class="shade_title">修改信息</div>
        <div class="shade_content">
            <form id="updateForm" method="post">
                <div>
                    <label>ID:</label>
                    <span>
                        <input readonly="readonly"  type="text" name="id">
                    </span>
                </div>
                <div><label>消费类型:</label>
                    <span>
                        <select  id="typename" class="select" size="1" name="type_id">

                        </select>
                    </span>
                </div>
                <div>
                    <label>详细描述:</label>
                    <span><input type="text" name="description"></span>
                </div>
                <div><label>支出资金：</label>
                    <span>
					<input id="price"  type="text" name="price" value="0"/>
					</span>
                </div>
                <div>
                    <label>支出人:</label>
                    <span>
                        <select id="username" class="select" size="1" name="user_id">

                        </select>
                    </span>
                </div>
                <div>
                    <label>支出时间:</label>
                    <span>
						<input type="text" name="time" class="datetime" id="timeInp">
					</span>
                </div>
                <div class="btns">
                    <input class="save" type="submit" value="保存">
                    <input class="cancel" type="submit" value="取消">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../datetimepicker/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="../js/jquery.page.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/product.js"></script>
<script type="text/javascript">

    $('.datetime').datetimepicker({
        language:  'zh-CN',//语言
        timepicker:false,//不选时间
        format:'Y-m-d'//日期格式
    });

    //点击复选框切换勾选状态图
    $(".col_5 label").on("click",function(){
        if($(this).hasClass("checked")){
            $(this).removeClass("checked");
        }else{
            $(this).addClass("checked");
        }
    })

    //点击编辑
    // $(".edit").on("click",function(){
    //     $(".editShade").removeClass("hide").addClass("show");
    // })

    //点击保存和关闭
    /*$(".save").on("click",function(){
        $(".editShade").removeClass("show").addClass("hide");

    })*/

    $(".cancel").on("click",function(){
        $(".editShade").removeClass("show").addClass("hide");
    })

    //关闭右侧
    $(".main_title").on("click",function(){
        $('#iframe', window.parent.document).attr("src","");
    })
</script>
</html>
