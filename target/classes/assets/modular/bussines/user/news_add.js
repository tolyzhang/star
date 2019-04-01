
layui.use(['layer', 'form', 'admin', 'ax','layedit'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    var layedit = layui.layedit;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    //建立编辑器
    var indexs = layedit.build('newContent',{
        uploadImage:{
            url:Feng.ctxPath +'/news/upload_img',
            type:'post'
        }
    });


    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        data.field.newContent = layedit.getContent(indexs);
        data.field.newType = $("#newType").val();
        var ajax = new $ax(Feng.ctxPath + "/news/add", function (data) {
            Feng.success("添加成功！");
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});