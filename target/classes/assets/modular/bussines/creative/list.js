layui.use(['layer', 'table', 'ax', 'laydate','admin'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;
    var admin = layui.admin;


    var CreativeList = {
        tableId: "creativeTable"   //表格id
    };

    /**
     * 初始化表格的列
     */
    CreativeList.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'ID'},
            {field: 'itemNo', hide: true, sort: true, title: 'itemNo'},
            {field: 'productName', sort: true, title: '项目名称'},
            {field: 'productPerson', sort: true, title: '项目负责人'},
            {field: 'companyName', sort: true, title: '企业名称'},
            {field: 'orgNo', sort: true, title: '信用代码'},
            {field: 'industryType', sort: true, title: '所属行业'},
            {field: 'crtTime', sort: true, title: '提交时间'},
            {field: 'creativeStatus', sort: true, title: '状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作', width: 180}
        ]];
    };

    /**
     * 点击查询按钮
     */
    CreativeList.search = function () {
        var queryData = {};
        queryData['creativeTile'] = $("#creativeTile").val();
        queryData['productName'] = $("#productName").val();
        queryData['productPerson'] = $("#productPerson").val();
        queryData['companyName'] = $("#companyName").val();
        queryData['orgNo'] = $("#orgNo").val();
        queryData['industryType'] = $("#industryType").val();
        table.reload(CreativeList.tableId, {where: queryData});
    };

    /**
     * 导出excel按钮
     */
    CreativeList.exportExcel = function (data) {
        var checkRows = table.checkStatus(CreativeList.tableId);
        table.exportFile(['导出全部数据'],data, 'xls');
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
        elem: '#' + CreativeList.tableId,
        url: Feng.ctxPath + '/creative/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: CreativeList.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        CreativeList.search();
    });

    // 搜索按钮点击事件
    $('#btnClean').click(function () {
        CreativeList.cleanLog();
    });





    // 导出excel
    $('#btnExcel').click(function () {
        $.ajax({
            //url:Feng.ctxPath + '/creative/downExcel',
            url:Feng.ctxPath + '/creative/down',
            type:'POST',
            data:{
                creativeTile: $("#creativeTile").val(),
                productName:$("#productName").val(),
                productPerson:$("#productPerson").val(),
                companyName: $("#companyName").val(),
                orgNo:$("#orgNo").val(),
                industryType:$("#industryType").val()
            },
            success:function(data){
                CreativeList.exportExcel(data);
            }
        })
    });

    /**
     * 详情
     * @param data
     */
    CreativeList.onDetail = function(data){
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '查看详情',
            offset: '20pxs',
            area: ['53%', '600px;'],   //宽高
            content: Feng.ctxPath + '/creative/creative_detail?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(CreativeList.tableId);
            }
        });
    };


    /**
     * 下载附件
     */
    CreativeList.onDownload = function(data){
        console.info(data);
        var isAnnex = data.isAnnex;
        if(isAnnex==0){
            layer.alert("用户并未上传附件");
            return false;
        }
        var itemNo = data.itemNo;
        console.info(data.itemNo);
        var url = Feng.ctxPath + '/creative/down';
        var forms = $("<form></form>").attr("action", url).attr("method", "post");
        forms.append($("<input></input>").attr("type", "hidden").attr("name", "itemNo").attr("value", itemNo));
        forms.appendTo('body').submit().remove();
    };

    /**
     * 审核
     * @param data 点击按钮时候的行数据
     */
    CreativeList.onReivew = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/creative/reivew?app=reivew", function () {
                Feng.success("审核成功!");
                table.reload(CreativeList.tableId);
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
    CreativeList.onRefund = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/creative/refund?app=refund", function () {
                Feng.success("退回成功!");
                table.reload(CreativeList.tableId);
            }, function (data) {
                Feng.error("退回失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否退回 ", operation);
    };

    // 工具条点击事件
    table.on('tool(' + CreativeList.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'detail') {
            CreativeList.onDetail(data);
        }else if(layEvent =='reivew'){
            CreativeList.onReivew(data);
        }else if(layEvent =='refund'){
            CreativeList.onRefund(data);
        }else if(layEvent =='downLoad'){
            CreativeList.onDownload(data);
        }

    });
});
