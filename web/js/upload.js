$(function(){
    loadCourse();
    //loadContent();
    loadType();
    addProduct();
});
//给上传的按钮绑定点击事件，当用户点击按钮时，使用ajax向服务器发送请求
function addProduct(){
    $(".btn_blue").click(function(){
        $.ajax({
            url:"/expendituresys/record/add",
            type:"post",
            data:$("#addForm").serialize(),
            dataType:"json",
            success:function(res){
                if(res.code==200){
                    //alert(res.msg);
                    //重置表单数据
                    $("#addForm")[0].reset();
                }
            },
            error:function(){alert("系统繁忙!")}
        });
    });
}


$('#time').datetimepicker({
    timepicker:false,
    format:'Y-m-d'
});
