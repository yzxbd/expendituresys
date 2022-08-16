let currentPage = 1;
//页面加载完毕，会立即调用该function
$(function(){
    //获取admin列表数据
    loadAdmins(1);
    addAdmin();
    loadOneAdmin();
    updateAdmin();
    delOneAdmin();
    checkboxChanged();
    delMany();
});

//点击复选框切换勾选状态图
function checkboxChanged(){
    //给table的表头label绑定点击事件
    $("table").on("click",".thead .col_5 label",function(){
        if($(this).hasClass("checked")){
            $(this).removeClass("checked");
            //删除表体的label样式
            $(".mytr .col_5 label").removeClass("checked");
        }else{
            $(this).addClass("checked");
            //新增表体的label样式
            $(".mytr .col_5 label").addClass("checked");
        }
    })

    //给table的表体label绑定点击事件
    $("table").on("click",".mytr .col_5 label",function(){
        if($(this).hasClass("checked")){
            $(this).removeClass("checked");
        }else{
            $(this).addClass("checked");
        }
        //判断表体中选中label的长度与所有tr的长度是否相等
        //相等-->表头label是选中的状态
        //不相等-->表头label是为选中的状态
        let totalSize = $(".mytr").length;
        let $label = $(".mytr .col_5 label[class~='checked']");
        if($label.length<totalSize){
            $(".thead .col_5 label").removeClass("checked");
        }else{
            $(".thead .col_5 label").addClass("checked");
        }
    })
}

//删除数据
function delAdmin(ids){
    let flag = window.confirm("确定要删除吗?");
    if(!flag){
        return;
    }
    //删除服务器上的数据
    $.ajax({
        url:"/expendituresys/user/del",
        data:{"id":ids},
        dataType:"json",
        traditional:true,//禁止jquery对参数做深度序列化的处理
        success:function(res){
            if(res.code==200){
                //alert(res.msg);
                loadAdmins(1);
            }
        },
        error:function(){
            alert("系统繁忙!");
        }
    });
}


//删除单个admin
function delOneAdmin(){
    $("table").on("click",".delete",function(){
        let ids =[$(this).data("id")];
        delAdmin(ids);
    });
}

//批量删除
function delMany(){
    $(".deleteMany").click(function(){
        //获取选中的label(表体)  数组
        let $label = $(".mytr .col_5 label[class~='checked']");
        if($label.length==0){
            alert("请选择所要删除的记录");
            return;
        }
        //获取选中行记录的adminId值
        let ids = [];
        $label.each(function(index,obj){
           let id =  $(obj).data("id");
           ids.push(id);
        });
        delAdmin(ids);
    });
}

function updateAdmin(){
    $(".editShade .save").on("click",function(){
        //隐藏修改的div
        $(".editShade").removeClass("show").addClass("hide");
        //更新表记录
        $.ajax({
            url:"/expendituresys/user/update",
            type:"post",
            data:$("#editForm").serialize(),
            dataType:"json",
            success:function(res){
                if(res.code==200){
                    //alert(res.msg);
                    //更新表格数据
                    loadAdmins(currentPage);
                }
            },
            error:function(){alert("系统繁忙!");}
        });
    })
}

//点击编辑
function loadOneAdmin(){
    $("table").on("click",".edit",function(){
        $(".editShade").removeClass("hide").addClass("show");
        //向服务器发送异步请求，获取管理员信息，并回显到输入框中
        $.ajax({
            url:"/expendituresys/user/load",
            data:{id:$(this).data("id")},//$(this).attr("data-id")  data-*
            dataType:"json",
            success:function(res){
                if(res.code==200){
                    //清空上一次的数据
                    $("#editForm")[0].reset();
                    let admin = res.data;
                    $("#editForm input[name='id']").val(admin.id);
                    $("#editForm input[name='username']").val(admin.username);
                    $("#editForm input[name='realname']").val(admin.realname);
                    $("#editForm input[name='password']").val(admin.password);
                    //回显示角色
                    $.each(admin.roles,function(index,role){
                        $("#editForm [':checkbox'][value='"+role.id+"']").prop("checked",true);
                    });
                }
            },
            error:function(){alert("系统繁忙!");}
        });
    })
}

function addAdmin(){
    $(".addShade .save").on("click",function(){
        $(".addShade").removeClass("show").addClass("hide");
        //使用ajax向服务发送异步请求，将用户输入的管理员信息保存到数据库中
        $.ajax({
            url:"/expendituresys/user/add",
            type:"post",
            data:$("#addForm").serialize(),//获取表单数据
            dataType:"json",
            success:function(res){
                if(res.code==200){
                    //alert(res.msg);
                    //更新表格
                    loadAdmins(1);
                }
            },
            error:function(){alert("系统繁忙!");}
        });
    })
}

//使用ajax向服务器发送异步请求，获取集合数据，并更新页面
function loadAdmins(page){
    $.ajax({
        url:"/expendituresys/user/list",
        data:{
            page:page
        },
        dataType:"json",
        success:function(res){
            if(res.code==200){
                let data = res.data;
                let totalPages = res.data.totalPages;//总页数
                //加载页码数据
                $(".tcdPageCode").createPage({
                    pageCount:totalPages,
                    current:page,
                    backFn:function(p){
                        currentPage = p;
                        //更新表格数据
                        loadAdmins(p);
                    }
                });
                //清空上一页的数据
                $(".mytr").remove();
                //将表头的样式清空
                $(".thead .col_5 label").removeClass("checked");
                //加载表格数据
                $.each(data.list,function(index,user){
                    //拼tr
                    let trstr = '<tr class="mytr">'
                    trstr += '<td class="col_5"><label class="check" data-id="'+user.id+'"></label></td>';
                    trstr += '<td class="col_5">'+((page-1)*2+(index+1))+'</td>';
                    trstr += '<td class="col_20">'+user.username+'</td>';
                    trstr += '<td class="col_20">'+user.password+'</td>';
                    trstr += '<td><span data-id="'+user.id+'" class="edit" style="cursor: pointer;"></span>&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<span data-id="'+user.id+'" class="delete" style="cursor: pointer;"></span></td>';
                    trstr += '</tr>';
                    $("#adminTb").append(trstr);
                });
            }
        },
        error:function(){alert("系统繁忙!");}
    });
}
