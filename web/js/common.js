//给课程方向的select绑定一个change事件
function loadContent(){
    $("#typename").change(function(){
        //获取课程内容
        $.ajax({
            url:"/expendituresys/record/getNames",
            data:{courseId:this.value},
            dataType:"json",
            success:function(res){
                //清空二级select数据
                $("#content").html("");
                //课程内容
                let data = res.data;
                let contentId = $("#content").data("id");
                $.each(data,function(index,obj){
                    //当前遍历的课程内容id与需要回想的课程内容id相等，那么就给当前的option添加selected属性
                    let option = '';
                    if(obj.id == contentId){
                         option = '<option selected value="'+obj.id+'">'+obj.name+'</option>';
                    }else{
                         option = '<option value="'+obj.id+'">'+obj.name+'</option>';
                    }
                    $("#content").append(option);
                });
            },
            error:function(){alert("系统繁忙!")}

        });
    });
}

function loadCourse(){
    $.ajax({
        url:"/expendituresys/user/getNames",
        dataType:"json",
        success:function(res){
            //根据服务器返回的数据生成课程方向的option
            //课程方向
            let data = res.data;
            //data = Object.keys(data)[0];
            data = data[Object.keys(data)[0]]
            $.each(data,function(index,user){
                let option = '<option value="'+user.id+'">'+user.username+'</option>';
                $("#username").append(option);
            });
            //模拟用户手动触发change事件
            $("#username").trigger("change");
        },
        error:function(){alert("系统繁忙!");}
    });
}

function loadType(){
    $.ajax({
        url:"/expendituresys/record/getNames",
        dataType:"json",
        success:function(res){
            //根据服务器返回的数据生成课程方向的option
            //课程方向
            let data = res.data;
            $.each(data,function(index,type){
                let option = '<option value="'+type.id+'">'+type.name+'</option>';
                $("#typename").append(option);
            });
            //模拟用户手动触发change事件
            $("#typename").trigger("change");
        },
        error:function(){alert("系统繁忙!");}
    });
}
