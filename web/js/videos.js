$(function(){
   /*
	li   choosed 选中 蓝色文字 
	     - 对应一级div有choosed  选中 蓝色背景 白色文字
	       -对应 span choosed 选中 白色图片
	    其它同级去除choosed
	div li_title 点击 click 	
	   有二级目录：
	     -有clicked删除      span变成down
	     -没有添加clicked    span变成up
	   没有二级目录：
		 当前添加choosed,其他所有节点去除choosed
   */
	$(".li_title").on("click",function(){
		//判断是否包含二级目录
		var flag = $(this).next("ul").html();
		if(!flag){//不包含二级目录
			$(this).addClass("choosed");
			$(".main_left li").removeClass("choosed");
			var $brothers =$(this).siblings("div");
			$brothers.removeClass("choosed");
			$brothers.find("span").removeClass("choosed");
			//右侧内容更新
			var address =$(this).attr("data-src");

			$("iframe").attr("src",address);
		}else{
			var className =$(this).attr("class");
			if(className.indexOf("clicked")!=-1){
				$(this).removeClass("clicked");
				$(this).find("span").removeClass("up").addClass("down");
			}else{
				$(this).addClass("clicked");
				$(this).find("span").removeClass("down").addClass("up");
			}
		}
		//正在编辑的一级标题的span的颜色为白色
		$("div.choosed").find("span").addClass("choosed");
	});
	$(".main_left li").on("click",function(){
		$("#iframe").removeAttr('style');
		$(".main_left li").removeClass("choosed");
		$(this).addClass("choosed");
		var $titleDom = $(this).parents("ul").prev(".li_title");
		$titleDom.addClass("choosed");
		$titleDom.siblings().removeClass("choosed");
		$titleDom.siblings().find("span").removeClass("choosed");
		//正在编辑的一级标题的span的颜色为白色
		$("div.choosed").find("span").addClass("choosed");	

		//右侧内容更新
		var address =$(this).attr("data-src");
		
		$("iframe").attr("src",address);
	})
	//关闭当前页
	// $(".main_title").on("click",function(){
	// 	$("iframe").attr("src","");
	// })
})