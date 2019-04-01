layui.use(['layer', 'table', 'ax', 'laydate','layedit','form'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;
    var layedit = layui.layedit;
    var form = layui.form;



    /**
     * 行业信息
     */
    var InfosList = {
        tableId: "infosTable"   //表格id
    };



 /*   /!**
     * 点击查询按钮
     *!/
    DemandList.search = function () {
        var queryData = {};
        queryData['creativeTile'] = $("#creativeTile").val();
        queryData['productName'] = $("#productName").val();
        queryData['productPerson'] = $("#productPerson").val();
        queryData['compamyName'] = $("#compamyName").val();
        queryData['orgNo'] = $("#orgNo").val();
        queryData['creativeType'] = $("#creativeType").val();
        table.reload(CreativeList.tableId, {where: queryData});
    };*/

    /**
     * 导出excel按钮
     */
    InfosList.exportExcel = function () {
        var checkRows = table.checkStatus(InfosList.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    //建立编辑器
    var indexs = layedit.build('infoContent',{
            uploadImage:{
                url:Feng.ctxPath +'/standard/upload_img',
                type:'post'
            }
    });


    // 表单提交事件
    form.on('submit(btnSubmitst)', function (data) {
        data.field.infoContent = layedit.getContent(indexs);
        var ajax = new $ax(Feng.ctxPath + "/standard/add", function (data) {
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

    //渲染时间选择框
    laydate.render({
        elem: '#beginTime'
    });

    //渲染时间选择框
    laydate.render({
        elem: '#endTime'
    });

    /*// 渲染表格
    var tableResult = table.render({
        elem: '#' + InfosList.tableId,
        url: Feng.ctxPath + '/infos/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: InfosList.initColumn()
    });*/

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        InfosList.search();
    });

    // 搜索按钮点击事件
    $('#btnClean').click(function () {
        InfosList.cleanLog();
    });

    // 工具条点击事件
    table.on('tool(' + InfosList.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'detail') {
            InfosList.logDetail(data);
        }

    });
});
