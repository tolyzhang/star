layui.use(['layer', 'table', 'ax', 'laydate'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;

    /**
     * 系统管理--操作日志
     */
    var WarehousList = {
        tableId: "warehousTable"   //表格id
    };

    /**
     * 初始化表格的列
     */
    WarehousList.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'ID', hide: true, sort: true, title: 'ID'},
            {field: 'companyName', sort: true, title: '单位名称'},
            {field: 'companyOrgNo', sort: true, title: '组织代码'},
            {field: 'companyLegal', sort: true, title: '企业法人'},
            {field: 'productPerson', sort: true, title: '项目负责人'},
            {field: 'productPhone', sort: true, title: '联系电话'},
            {field: 'productEmail', sort: true, title: '联系邮箱'},
            {field: 'productTrade', sort: true, title: '所属行业'},
            {field: 'warehousStatus', sort: true, title: '入库状态'},
            {field: 'crtTime', sort: true, title: '创建时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 100}
        ]];
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
    WarehousList.exportExcel = function () {
        var checkRows = table.checkStatus(WarehousList.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

 /*   /!**
     * 日志详情
     *!/
    DemandList.logDetail = function (param) {
        var ajax = new $ax(Feng.ctxPath + "/log/detail/" + param.operationLogId, function (data) {
            Feng.infoDetail("日志详情", data.regularMessage);
        }, function (data) {
            Feng.error("获取详情失败!");
        });
        ajax.start();
    };
*/


    //渲染时间选择框
    laydate.render({
        elem: '#beginTime'
    });

    //渲染时间选择框
    laydate.render({
        elem: '#endTime'
    });

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WarehousList.tableId,
        url: Feng.ctxPath + '/warehous/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WarehousList.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WarehousList.search();
    });

    // 搜索按钮点击事件
    $('#btnClean').click(function () {
        WarehousList.cleanLog();
    });

    // 工具条点击事件
    table.on('tool(' + WarehousList.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'detail') {
            WarehousList.logDetail(data);
        }

    });
});
