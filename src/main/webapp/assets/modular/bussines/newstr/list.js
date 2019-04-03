layui.use(['layer', 'table', 'admin','ax', 'laydate'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;
    var admin = layui.admin;

    /**
     *
     */
    var newsList = {
        tableId: "newsTable"   //表格id
    };

    /**
     * 初始化表格的列
     */
    newsList.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true,sort: true, title: 'ID'},
            {field: 'newType', sort: true, title: '行业类型'},
            {field: 'newTitle', sort: true, title: '标题'},
            {field: 'newTime', sort: true, title: '创建时间'},
            {field: 'newUptTime', sort: true, title: '修改时间'},
            {field: 'newStatus', sort: true, title: '行业状态'},
            {field: 'operator', sort: true, title: '操作人'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 100}
        ]];
    };


    newsList.search = function () {
        var queryData = {};
        queryData['newType'] = $("#newType").val();
        table.reload(newsList.tableId, {where: queryData});
    };



    /**
     * 弹出添加
     */
    newsList.openAddDept = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改成果转换',
            area: ['45%', '800px'],   //宽高
            content: Feng.ctxPath + '/newstr/newstr_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(newsList.tableId);
            }
        });
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    newsList.onEditDept = function (data) {
        admin.putTempData('formOk', false);
        console.info(data.id);
        console.info(data);
        top.layui.admin.open({
            type: 2,
            title: '修改成果转换',
            area: ['45%', '800px'],   //宽高
            content: Feng.ctxPath + '/newstr/newstr_update?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(newsList.tableId);
            }
        });
    };

    /**
     * 点击删除部门
     *
     * @param data 点击按钮时候的行数据
     */
    newsList.onDeleteDept = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/newstr/delete", function () {
                Feng.success("删除成功!");
                table.reload(newsList.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除 ", operation);
    };

    /**
     * 导出excel按钮
     */
    newsList.exportExcel = function () {
        var checkRows = table.checkStatus(newsList.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };


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
        elem: '#' + newsList.tableId,
        url: Feng.ctxPath + '/newstr/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: newsList.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        newsList.search();
    });

    // 搜索按钮点击事件
    $('#btnClean').click(function () {
        newsList.cleanLog();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        newsList.openAddDept();
    });



    // 工具条点击事件
    table.on('tool(' + newsList.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'detail') {
            newsList.logDetail(data);
        }else if (layEvent === 'edit') {
            newsList.onEditDept(data);
        } else if (layEvent === 'delete') {
            newsList.onDeleteDept(data);
        }

    });
});
