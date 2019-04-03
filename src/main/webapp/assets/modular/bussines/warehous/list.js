layui.use(['layer', 'table', 'ax', 'laydate','layedit','form','admin'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;
    var admin = layui.admin

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
            {field: 'productName', sort: true, title: '项目名称'},
            {field: 'productPerson', sort: true, title: '项目负责人'},
            {field: 'companyName', sort: true, title: '企业名称'},
            {field: 'companyOrgNo', sort: true, title: '信用代码'},
            {field: 'industryType', sort: true, title: '所属行业'},
            {field: 'crtTime', sort: true, title: '提交时间'},
            {field: 'warehousStatus', sort: true, title: '状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 180}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WarehousList.search = function () {
        var queryData = {};
        queryData['companyName'] = $("#companyName").val();
        queryData['productName'] = $("#productName").val();
        queryData['productPerson'] = $("#productPerson").val();
        queryData['companyOrgNo'] = $("#companyOrgNo").val();
        queryData['crtTime'] = $("#crtTime").val();
        queryData['industryType'] = $("#industryType").val();
        table.reload(WarehousList.tableId, {where: queryData});
    };

    /**
     * 导出excel按钮
     */
    WarehousList.exportExcel = function (data) {
        var checkRows = table.checkStatus(WarehousList.tableId);
        table.exportFile(['导出全部数据'],data, 'xls');
    };




    //渲染时间选择框
    laydate.render({
        elem: '#crtTime'
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


    // 导出excel
    $('#btnExcel').click(function () {
        $.ajax({
            url:Feng.ctxPath + '/warehous/downExcel',
            type:'POST',
            data:{
                companyName : $("#companyName").val(),
                companyOrgNo :$("#companyOrgNo").val(),
                productName: $("#productName").val(),
                productPerson :$("#productPerson").val(),
                crtTime:$("#crtTime").val(),
                industryType:$("#industryType").val(),
            },
            success:function(data){
                WarehousList.exportExcel(data);
            }
        })
    });


    /**
     * 详情
     * @param data
     */
    WarehousList.onDetail = function(data){
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '查看详情',
            offset: '20pxs',
            area: ['53%', '600px;'],   //宽高
            content: Feng.ctxPath + '/warehous/warehous_detail?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(WarehousList.tableId);
            }
        });
    };


    /**
     * 下载附件
     */
    WarehousList.onDownload = function(data){
        console.info(data);
        var isAnnex = data.isAnnex;
        if(isAnnex==0){
            layer.alert("用户并未上传附件");
            return false;
        }
        var itemNo = data.itemNo;
        console.info(data.itemNo);
        var url = Feng.ctxPath + '/warehous/down';
        var forms = $("<form></form>").attr("action", url).attr("method", "post");
        forms.append($("<input></input>").attr("type", "hidden").attr("name", "itemNo").attr("value", itemNo));
        forms.appendTo('body').submit().remove();
    };

    /**
     * 审核
     * @param data 点击按钮时候的行数据
     */
    WarehousList.onReivew = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/warehous/reivew?app=reivew", function () {
                Feng.success("审核成功!");
                table.reload(WarehousList.tableId);
            }, function (data) {
                Feng.error("审核失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否审核 ", operation);
    };

    /**
     * 退回
     * @param data 点击按钮时候的行数据
     */
    WarehousList.onRefund = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/warehous/refund?app=refund", function () {
                Feng.success("退回成功!");
                table.reload(WarehousList.tableId);
            }, function (data) {
                Feng.error("退回失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否退回 ", operation);
    };


    // 工具条点击事件
    table.on('tool(' + WarehousList.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'detail') {
            WarehousList.onDetail(data);
        }else if(layEvent =='reivew'){
            WarehousList.onReivew(data);
        }else if(layEvent =='refund'){
            WarehousList.onRefund(data);
        }else if(layEvent =='downLoad'){
            WarehousList.onDownload(data);
        }

    });
});
