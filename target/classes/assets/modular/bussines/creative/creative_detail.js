layui.use(['layer', 'form', 'admin', 'ax','layedit'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
   // admin.iframeAuto();

    //获取信息
    var ajax = new $ax(Feng.ctxPath + "/creative/detail/" + Feng.getUrlParam("id"));
    var result = ajax.start();
    var ajaxt = new $ax(Feng.ctxPath+"/creative/annexImg/"+result.itemNo);
    var resultImg = ajaxt.start();
    var ajaxPart = new $ax(Feng.ctxPath+"/creative/part/"+result.itemNo);
    var resultPart = ajaxPart.start();
    form.val('creativeForm',result);
    var html="";
    for(var i=0;i<resultImg.length;i++){
        html+="<label class=\"layui-form-label\">附件</label>"+
            "<div class=\"layui-input-block\" >"+
            "<img src=\"../"+resultImg[i].annexUrlAdd+"\" alt=\"\" id=\"annexImgst\" style=\"width:80%\" /></div>";
    }
    $("#annexIm").append(html);
    console.info(resultPart);
    var parthtml = "";
    for(var p=0;p<resultPart.length;p++){
        parthtml+="<div class=\"layui-form-item\"><div class=\"layui-inline\">"+
            "<label class=\"layui-form-label\">姓名</label>"+
            "<div class=\"layui-input-block\">"+
            "<input type=\"text\" name=\"partName"+p+"\"  id=\"partName"+p+"\"  " +
            "autocomplete=\"off\" class=\"layui-input\" readonly  value=\""+resultPart[p].partName+"\"/></div></div>"+
            "<div class=\"layui-inline\">"+
            "<label class=\"layui-form-label\">职务/职称</label>"+
            "<div class=\"layui-input-block\">"+
            "<input type=\"text\" name=\"partJob"+p+"\"  id=\"partJob"+p+"\" value=\""+resultPart[p].partJob+"\" autocomplete=\"off\" class=\"layui-input\" readonly /></div></div>"+
            "<div class=\"layui-form-item\"><div class=\"layui-inline\">"+
            "<label class=\"layui-form-label\">身份证号</label>"+
            "<div class=\"layui-input-block\">"+
            "<input type=\"text\" name=\"partCode"+p+"\"  id=\"partCode"+p+"\" value=\""+resultPart[p].partCode+"\" autocomplete=\"off\" class=\"layui-input\" readonly /></div></div>"+
            "<div class=\"layui-inline\">"+
            "<label class=\"layui-form-label\">学历</label>"+
            "<div class=\"layui-input-block\">"+
            "<input type=\"text\" name=\"partEdu"+p+"\"  id=\"partEdu"+p+"\" value=\""+resultPart[p].partEdu+"\" autocomplete=\"off\" class=\"layui-input\" readonly /></div></div>";
    }
    $("#Forpart").append(parthtml);




/*<div class="layui-form-item">
        <div class="layui-inline">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
        <input type="text" name="names"  id="names"
    value="" lay-verify="required" autocomplete="off" class="layui-input" readonly />
    </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">职务/职称</label>
        <div class="layui-input-block">
        <input type="text" name="names"  id="names"
    value="" lay-verify="required" autocomplete="off" class="layui-input" readonly />
    </div>
    </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
        <label class="layui-form-label">身份证号</label>
        <div class="layui-input-block">
        <input type="text" name="names"  id="names"
    value="" lay-verify="required" autocomplete="off" class="layui-input" readonly />
    </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">学历</label>
        <div class="layui-input-block">
        <input type="text" name="names"  id="names"
    value="" lay-verify="required" autocomplete="off" class="layui-input" readonly />
    </div>
    </div>
    </div>
    */
    //
/*<div class="layui-inline">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
        <input type="text" name="names"  id="names"
    value="" lay-verify="required" autocomplete="off" class="layui-input" readonly />
    </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">职务/职称</label>
        <div class="layui-input-block">
        <input type="text" name="names"  id="names"
    value="" lay-verify="required" autocomplete="off" class="layui-input" readonly />
    </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">身份证号</label>
        <div class="layui-input-block">
        <input type="text" name="names"  id="names"
    value="" lay-verify="required" autocomplete="off" class="layui-input" readonly />
    </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">学历</label>
        <div class="layui-input-block">
        <input type="text" name="names"  id="names"
    value="" lay-verify="required" autocomplete="off" class="layui-input" readonly />
    </div>
    </div>*/

  /*  $("#industryType").val(result.industryType);
    $("#companyUrlAdd").attr("src",result.companyUrlAdd);
    form.val('userForm', result);
    $("#companyUrlAdd").click(function () {
       // var html = "<img src=\""+result.companyUrlAdd+"\" />";
        admin.putTempData('formOk', false);
        var img = '<img src="'+result.companyUrlAdd+'" style="width:80%;height:80%;">';
        layer.open({
            type: 1,//Page层类型
            shade: 0.6 ,//遮罩透明度
            area: ['100%', '100%'],   //宽高
            maxmin: false ,//允许全屏最小化
            anim: 1 ,//0-6的动画形式，-1不开启
            content: img
        });*/


    });
