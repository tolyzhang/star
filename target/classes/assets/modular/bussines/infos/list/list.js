layui.use(['layer', 'table', 'ax', 'laydate'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;

    /**
     *
     */
    var DemandList = {
        tableId: "infosTable"   //表格id
    };

    /**
     * 初始化表格的列
     */
    DemandList.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'ID', sort: true, title: 'ID'},
            {field: 'INFO_TYPE', sort: true, title: '行业类型'},
            {field: 'INFO_TITLE', sort: true, title: '标题'},
            {field: 'INFO_TAG', sort: true, title: '行业标签'},
            {field: 'INFO_TIME', sort: true, title: '创建时间'},
            {field: 'INFO_UPT_TIME', sort: true, title: '修改时间'},
            {field: 'OPERATOR', sort: true, title: '操作人'},
            {field: 'INFO_STATUS', sort: true, title: '行业状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 100}
        ]];
    };


    DemandList.search = function () {
        var queryData = {};
        queryData['infoStatus'] = $("#infoStatus").val();
        queryData['infoType'] = $("#infoType").val();
        table.reload(DemandList.tableId, {where: queryData});
    };

    /**
     * 导出excel按钮
     */
    DemandList.exportExcel = function () {
        var checkRows = table.checkStatus(DemandList.tableId);
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
        elem: '#' + DemandList.tableId,
        url: Feng.ctxPath + '/infos/lists',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: DemandList.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        DemandList.search();
    });

    // 搜索按钮点击事件
    $('#btnClean').click(function () {
        DemandList.cleanLog();
    });

    // 工具条点击事件
    table.on('tool(' + DemandList.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'detail') {
            DemandList.logDetail(data);
        }

    });
});
