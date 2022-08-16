$(function(){
    //loadProducts();
    searchProducts();
    delProduct();
    loadCourse();
    loadContent();
    loadOneProduct();
    loadRecords(1);
    updateRecord()
});


function loadOneProduct(){
    $("table").on("click",".edit",function(){
        loadType();
        $(".editShade").removeClass("hide").addClass("show");
        $.ajax({
            url:"/expendituresys/record/loadProduct",
            data:{id:$(this).data("id")},
            dataType:"json",
            success:function(res){
                //回显表达数据
                if(res.code==200){
                    let r = res.data;
                    $("#updateForm input[name='id']").val(r.id);
                    $("#username").val(r.userId);
                    $("#userId").trigger("change");
                    $("#typeId").attr("data-id",r.typeId);
                    $("#typename").val(r.typeId);
                    /*$.each(res,function(index,r){
                        let option = '<option value="'+r.id+'">'+r.name+'</option>';
                        $("#typeId").append(option);
                    });*/
                    //模拟用户手动触发change事件
                    $("#typename").trigger("change");
                    $("#updateForm input[name='description']").val(r.description);
                    $("#price").val(r.price);
                    $("#updateForm input[name='time']").val(r.time);
                }
            },
            error:function(){
                alert("系统繁忙!");
            }
        });
    })
}

function delProduct(){
    $("table").on("click",".delete",function(){
        if(window.confirm("确定要删除吗？")){
            $.ajax({
                url:"/expendituresys/record/del",
                data:{
                    //id:$(this).data("id")
                    id:$(this).attr("data-id")
                },
                dataType:"json",
                success:function(res){
                    //更新表格的数据
                    if(res.code==200){
                        loadRecords(1);
                    }
                },
                error:function(){
                    alert("系统繁忙!");
                }
            });
        }
    });
}

function updateRecord(){
    $(".editShade .save").on("click",function(){
        //隐藏修改的div
        $(".editShade").removeClass("show").addClass("hide");
        //更新表记录
        $.ajax({
            url:"/expendituresys/record/update",
            type:"post",
            data:$("#updateForm").serialize(),
            dataType:"json",
            success:function(res){
                if(res.code==200){
                    //更新表格数据
                    loadAdmins(currentPage);
                }
            },
            error:function(){alert("系统繁忙!");}
        });
    })
}

function searchProducts(){
    $(".search").click(function(){
        //将时间按钮中的数据同步到隐藏框中
        $("#ctime").val($("#datetime").val());
        //使用ajax向服务器发送请求获取数据
        loadProducts();

    });
}

function loadProducts(){
    $.ajax({
        url:"/expendituresys/record/list",
        data:$("#searchForm").serialize(),
        dataType:"json",
        success:function(res){
            if(res.code==200){
                $(".mytr").remove();
                let data = res.data;
                $.each(data,function(index,r){
                    let trstr = '<tr class="mytr">';
                    trstr += '<td class="col_10">'+(index+1)+'</td>';
                    trstr += '<td class="col_12">'+r.typeName+'</td>';
                    trstr += '<td class="col_12">¥'+r.record.price+'</td>';
                    trstr += '<td class="col_12">'+r.userName+'</td>';
                    trstr += '<td class="col_10">'+r.record.time+'</td>';
                    trstr += '<td><span data-id="'+r.record.id+'" class="edit"></span>&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<span data-id="'+r.record.id+'" class="delete"></span></td>';
                    trstr += '</tr>';
                    $("#pTb").append(trstr);
                });
            }
        },
        error:function(){alert("系统繁忙");}
    });
}

//使用ajax向服务器发送异步请求，获取集合数据，并更新页面
function loadRecords(page){
    $.ajax({
        url:"/expendituresys/record/listForPage",
        data:{
            page:page
        },
        dataType:"json",
        success:function(res){
            if(res.code==200){
                let data = res.data.list;
                let totalPages = res.data.totalPages;//总页数
                //加载页码数据
                $(".tcdPageCode").createPage({
                    pageCount:totalPages,
                    current:page,
                    backFn:function(p){
                        currentPage = p;
                        //更新表格数据
                        loadRecords(p);
                    }
                });
                //清空上一页的数据
                $(".mytr").remove();
                //将表头的样式清空
                $(".thead .col_5 label").removeClass("checked");
                //加载表格数据
                $.each(data,function(index,r){
                    let trstr = '<tr class="mytr">';
                    trstr += '<td class="col_10">'+(index+1)+'</td>';
                    trstr += '<td class="col_12">'+r.typeName+'</td>';
                    trstr += '<td class="col_12">¥'+r.record.price+'</td>';
                    trstr += '<td class="col_12">'+r.userName+'</td>';
                    trstr += '<td class="col_10">'+r.record.time+'</td>';
                    trstr += '<td><span data-id="'+r.record.id+'" class="edit"></span>&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<span data-id="'+r.record.id+'" class="delete"></span></td>';
                    trstr += '</tr>';
                    $("#pTb").append(trstr);
                });
            }
        },
        error:function(){alert("系统繁忙!");}
    });
}

