layui.use(['layer', 'table', 'ax', 'laydate'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;

    /**
     * 系统管理--操作日志
     */
    var DemandList = {
        tableId: "demandTable"   //表格id
    };

    /**
     * 初始化表格的列
     */
    DemandList.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'ID', hide: true, sort: true, title: 'ID'},
            {field: 'companyName', sort: true, title: '单位名称'},
            {field: 'demandPerson', sort: true, title: '联系人'},
            {field: 'demandPhone', sort: true, title: '联系电话'},
            {field: 'productTrade', sort: true, title: '所属行业'},
            {field: 'demandType', sort: true, title: '需求类型'},
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
        url: Feng.ctxPath + '/demand/list',
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
