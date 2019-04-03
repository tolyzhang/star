
layui.use(['layer', 'form', 'admin', 'ax','layedit'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    var layedits = layui.layedit;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();


    //建立编辑器
    var indexs = layedits.build('newContent',{
        uploadImage:{
            url:Feng.ctxPath +'/newstr/upload_img',
            type:'post'
        }
    });

    //获取信息
    var ajax = new $ax(Feng.ctxPath + "/newstr/detail/" + Feng.getUrlParam("id"));
    var result = ajax.start();
    console.info(result.newContent);
    $("#newType").val(result.newType);
    var content = result.newContent;
    //layedits.setContent(indexs, "sd",false);
    form.val('newsForm', result);



    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        data.field.newContent = layedits.getContent(indexs);
        data.field.newType = $("#newType").val();
        var ajax = new $ax(Feng.ctxPath + "/newstr/update", function (data) {
            Feng.success("修改成功！");
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("修改失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});