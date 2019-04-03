layui.use(['layer', 'table', 'ax', 'laydate','layedit','form','admin'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;
    var table = layui.table;
    var laydate = layui.laydate;
    var admin = layui.admin



    /**
     * 行业信息
     */
    var ExpertList = {
        tableId: "expertTable"   //表格id
    };

    /**
     * 初始化表格的列
     */
    ExpertList.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'ID'},
            {field: 'itemNo', hide: true, sort: true, title: 'itemNo'},
            {field: 'expertName', sort: true, title: '专家姓名'},
            {field: 'expertWork', sort: true, title: '工作单位'},
            {field: 'industryType', sort: true, title: '所属行业'},
            {field: 'expertJob', sort: true, title: '职称'},
            {field: 'expertProfe', sort: true, title: '专业'},
            {field: 'expertPhone', sort: true, title: '手机号'},
            {field: 'expertEmail', sort: true, title: '邮箱'},
            {field: 'expertTime', sort: true, title: '有效期'},
            {field: 'expertStatus', sort: true, title: '状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作', width: 180}
        ]];
    };



    /**
     * 点击查询按钮
     */
    ExpertList.search = function () {
        var queryData = {};
        queryData['expertName'] = $("#expertName").val();
        queryData['expertWork'] = $("#expertWork").val();
        queryData['expertProfe'] = $("#expertProfe").val();
        queryData['expertJob'] = $("#expertJob").val();
        queryData['expertTime'] = $("#expertTime").val();
        queryData['industryType'] = $("#industryType").val();
        table.reload(ExpertList.tableId, {where: queryData});
    };


    /**
     * 导出excel按钮
     */
    ExpertList.exportExcel = function (data) {
        var checkRows = table.checkStatus(ExpertList.tableId);
        table.exportFile(['导出全部数据'],data, 'xls');
    };


    //渲染时间选择框
    laydate.render({
        elem: '#expertTime'
    });

    //渲染时间选择框
    laydate.render({
        elem: '#endTime'
    });

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ExpertList.tableId,
        url: Feng.ctxPath + '/expert/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ExpertList.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ExpertList.search();
    });

    // 搜索按钮点击事件
    $('#btnClean').click(function () {
        ExpertList.cleanLog();
    });





    // 导出excel
    $('#btnExcel').click(function () {
        $.ajax({
            //url:Feng.ctxPath + '/creative/downExcel',
            url:Feng.ctxPath + '/expert/downExcel',
            type:'POST',
            data:{
               expertName : $("#expertName").val(),
               expertWork :$("#expertWork").val(),
               expertProfe: $("#expertProfe").val(),
               expertJob :$("#expertJob").val(),
                expertTime:$("#expertTime").val(),
               industryType:$("#industryType").val(),
            },
            success:function(data){
                ExpertList.exportExcel(data);
            }
        })
    });

    /**
     * 详情
     * @param data
     */
    ExpertList.onDetail = function(data){
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '查看详情',
            offset: '20pxs',
            area: ['53%', '600px;'],   //宽高
            content: Feng.ctxPath + '/expert/expert_detail?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(ExpertList.tableId);
            }
        });
    };


    /**
     * 下载附件
     */
    ExpertList.onDownload = function(data){
        console.info(data);
        var isAnnex = data.isAnnex;
        if(isAnnex==0){
            layer.alert("用户并未上传附件");
            return false;
        }
        var itemNo = data.itemNo;
        console.info(data.itemNo);
        var url = Feng.ctxPath + '/expert/down';
        var forms = $("<form></form>").attr("action", url).attr("method", "post");
        forms.append($("<input></input>").attr("type", "hidden").attr("name", "itemNo").attr("value", itemNo));
        forms.appendTo('body').submit().remove();
    };

    /**
     * 审核
     * @param data 点击按钮时候的行数据
     */
    ExpertList.onReivew = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/expert/reivew?app=reivew", function () {
                Feng.success("审核成功!");
                table.reload(ExpertList.tableId);
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
    ExpertList.onRefund = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/expert/refund?app=refund", function () {
                Feng.success("退回成功!");
                table.reload(ExpertList.tableId);
            }, function (data) {
                Feng.error("退回失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否退回 ", operation);
    };

    // 工具条点击事件
    table.on('tool(' + ExpertList.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'detail') {
            ExpertList.onDetail(data);
        }else if(layEvent =='reivew'){
            ExpertList.onReivew(data);
        }else if(layEvent =='refund'){
            ExpertList.onRefund(data);
        }else if(layEvent =='downLoad'){
            ExpertList.onDownload(data);
        }

    });
});
