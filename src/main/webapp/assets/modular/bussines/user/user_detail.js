
layui.use(['layer', 'form', 'admin', 'ax','layedit'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    var layedits = layui.layedit;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    //获取信息
    var ajax = new $ax(Feng.ctxPath + "/user/detail/" + Feng.getUrlParam("id"));
    var result = ajax.start();
    console.info(result);
    $("#industryType").val(result.industryType);
    $("#companyUrlAdd").attr("src",result.companyUrlAdd);
    form.val('userForm', result);
    $("#companyUrlAdd").click(function () {
       // var html = "<img src=\""+result.companyUrlAdd+"\" />";
        admin.putTempData('formOk', false);
        var img = '<img src="'+result.companyUrlAdd+'" style="width:80%;height:80%;">';
        layer.open({
            type: 1,//Page层类型
            shade: 0.6 ,//遮罩透明度
            area: ['100%', '100%'],   //宽高
            maxmin: false ,//允许全屏最小化
            anim: 1 ,//0-6的动画形式，-1不开启
            content: img
        });


    });


});