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
    var userList = {
        tableId: "userTable"   //表格id
    };

    /**
     * 初始化表格的列
     */
    userList.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true,sort: true, title: 'ID'},
            {field: 'industryType', sort: true, title: '行业类型'},
            {field: 'userName', sort: true, title: '用户名'},
            {field: 'userRealName', sort: true, title: '真实姓名'},
            {field: 'companyName', sort: true, title: '企业名称'},
            {field: 'companyCode', sort: true, title: '组织代码编号'},
            {field: 'companyPerson', sort: true, title: '法人'},
            {field: 'userPhone', sort: true, title: '手机号'},
            {field: 'userEmail', sort: true, title: '邮箱'},
            {field: 'userStatus', sort: true, title: '状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作', width: 165}
        ]];
    };


    userList.search = function () {
        var queryData = {};
        queryData['industryType'] = $("#industryType").val();
        queryData['userStatus']=$("#userStatus").val();
        queryData['companyName']=$("#companyName").val();
        queryData['userName'] = $("#userName").val();
        table.reload(userList.tableId, {where: queryData});
    };


    userList.onDetilOn = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '查看详情',
            area: ['55%', '300px'],   //宽高
            content: Feng.ctxPath + '/user/user_detail?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(userList.tableId);
            }
        });
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    userList.onEditDept = function (data) {
        admin.putTempData('formOk', false);
        console.info(data.id);
        console.info(data);
        top.layui.admin.open({
            type: 2,
            title: '修改注册用户',
            area: ['45%', '800px'],   //宽高
            content: Feng.ctxPath + '/user/user_edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(userList.tableId);
            }
        });
    };

    /**
     * 点击删除部门
     *
     * @param data 点击按钮时候的行数据
     */
    userList.onDeleteDept = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/user/delete", function () {
                Feng.success("删除成功!");
                table.reload(userList.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除 ", operation);
    };

    /**
     * 审核
     */
    userList.onReview = function(data){
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/user/review", function () {
                Feng.success("审核通过");
                table.reload(userList.tableId);
                $("#review").remove();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否通过审核 ", operation);
    }

    /**
     * 导出excel按钮
     */
    userList.exportExcel = function () {
        var checkRows = table.checkStatus(userList.tableId);
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
        elem: '#' + userList.tableId,
        url: Feng.ctxPath + '/user/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: userList.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        userList.search();
    });

    // 搜索按钮点击事件
    $('#btnClean').click(function () {
        userList.cleanLog();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        userList.openAddDept();
    });



    // 工具条点击事件
    table.on('tool(' + userList.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        console.info("data:"+data);
        if (layEvent === 'detail') {
            userList.onDetilOn(data);
        }else if (layEvent === 'edit') {
            userList.onEditDept(data);
        } else if (layEvent === 'delete') {
            userList.onDeleteDept(data);
        } else if(layEvent =='review'){
            userList.onReview(data);
        }

    });
});
