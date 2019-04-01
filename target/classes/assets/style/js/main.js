var Main = function() {
	"use strict";
    var runManiNav = function() {
		//下拉框
		if($('.dropdown').length){
			$(".dropdown").hover(
				function() {
					$('.dropdown-menu', this).fadeIn("fast");
					$(this).addClass("open");
				},
				function() {
					$('.dropdown-menu', this).fadeOut("fast");
					$(this).removeClass("open");
				});
		}
        //响应式导航--infinitypush.js
        function responsive() {
            if($(window).width() <= 991){
                console.log('mobile navigation');
                $('#mobile-navigation').infinitypush({
                    offcanvasleft: false
                });
                $('body').addClass('mobile');
                $('body').removeClass('desktop');

            } else {
                console.log('desktop navigation');
                $('#mobile-navigation').infinitypush({
                    destroy:true
                });
                $('body').removeClass('mobile');
                $('body').addClass('desktop');
            }
        }
        function windowResize(){
            $(window).resize(function(){
                responsive();
            });
        }
        responsive();
        windowResize();
    };

		//// 设置头部链接
		var setHeadLink = function(){
		var info = $('#tblj').text();
		if (info == '新闻中心') {
			$('#nav0').removeClass('active');
            $('#nav1').addClass('active');
	    } else if (info == '科创服务') {
	    	$('#nav0').removeClass('active');
	        $('#nav2').addClass('active');
	    } else if (info == '行业信息') {
	    	$('#nav0').removeClass('active');
	        $('#nav3').addClass('active');
	    } 
    };
	
    return {
        //main function to initiate template pages
        init : function() {
            runManiNav();
			setHeadLink();
        }
    };

	var runManiNav = function() {
		//演出场馆鼠标划过自动下拉菜单
		if($('.dropdown').length){
    		$(".dropdown").hover(
        		function() { 
        			$('.dropdown-menu', this).fadeIn("fast");
        			$(this).addClass("open");
	        	},
	        	function() { 
	        		$('.dropdown-menu', this).fadeOut("fast");
	    			$(this).removeClass("open");
    		});
		}
	};
	var hideNav = function() {
		$('.navbar-collapse').click(function(){
			$('.index-navbar-collapse').hide()
		});
	
	}

	return {
		//main function to initiate template pages
		init : function() {
			runManiNav();
			hideNav();
		}
	};
}();

